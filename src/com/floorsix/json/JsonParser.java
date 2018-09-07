
package com.floorsix.json;

import java.io.ByteArrayInputStream;
import java.io.PushbackInputStream;
import java.io.IOException;

public class JsonParser
{
  enum NumberState {
    Negative,   // can be digit or -
    Whole,      // can be digit or .
    Fraction,   // can be digit or e or E
    Sign,       // can be + or -
    Exponent,   // can be digit
  };

  public static JsonObject parseFile(String filename)
  {
    return null;
  }

  public static JsonObject parse(String raw) throws InvalidJsonException
  {
    return parseObject(new PushbackInputStream(new ByteArrayInputStream(raw.getBytes())));
  }

  private static String parseKey(PushbackInputStream in) throws InvalidJsonException
  {
    char b = readNextNonWhitespace(in);

    if (b == '"')
    {
      return readToClosingQuote(in);
    }
    else
    {
      throw new InvalidJsonException("Key must be wrapped in quotes");
    }
  }

  private static Json parseValue(String key, PushbackInputStream in) throws InvalidJsonException
  {
    Json json = null;

    char b = readNextNonWhitespace(in);

    try
    {
      switch (b)
      {
        case 'n':
          in.unread(b);
          json = parseNull(key, in);
          break;

        case 't':
          in.unread(b);
          json = parseTrueBoolean(key, in);
          break;

        case 'f':
          in.unread(b);
          json = parseFalseBoolean(key, in);
          break;

        case '-':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          in.unread(b);
          json = parseNumber(key, in);
          break;

        case '"':
          json = parseString(key, in);
          break;

        case '[':
          json = parseArray(key, in);
          break;

        case '{':
          json = parseObject(key, in);
          break;

        default:
          throw new InvalidJsonException("Expecting value");
      }
    }
    catch (IOException e)
    {
      throw new InvalidJsonException("Error reading input while parsing value");
    }

    return json;
  }

  private static JsonNull parseNull(String key, PushbackInputStream in) throws InvalidJsonException
  {
    final String token = "null";

    parseBytes(token, in);
    return new JsonNull(key);
  }

  private static JsonBoolean parseTrueBoolean(String key, PushbackInputStream in) throws InvalidJsonException
  {
    final String token = "true";

    parseBytes(token, in);
    return new JsonBoolean(key, true);
  }

  private static JsonBoolean parseFalseBoolean(String key, PushbackInputStream in) throws InvalidJsonException
  {
    final String token = "false";

    parseBytes(token, in);
    return new JsonBoolean(key, false);
  }

  private static JsonNumber parseNumber(String key, PushbackInputStream in) throws InvalidJsonException
  {
    NumberState state = NumberState.Negative;

    StringBuilder s = new StringBuilder();

    try
    {
      while (true)
      {
        char b = (char)in.read();

        switch (state)
        {
          case Negative:
            if (b == '-' || isDigit(b))
            {
              s.append(b);
              state = NumberState.Whole;
            }
            else
            {
              throw new InvalidJsonException("Bad number");
            }
            break;

          case Whole:
            if (b == '.')
            {
              s.append(b);
              state = NumberState.Fraction;
            }
            else if (isDigit(b))
            {
              s.append(b);
            }
            else
            {
              in.unread(b);
              return new JsonNumber(key, Double.valueOf(s.toString()));
            }
            break;

          case Fraction:
            if (b == 'e' || b == 'E')
            {
              s.append(b);
              state = NumberState.Sign;
            }
            else if (isDigit(b))
            {
              s.append(b);
            }
            else
            {
              in.unread(b);
              return new JsonNumber(key, Double.valueOf(s.toString()));
            }
            break;

          case Sign:
            if (b == '+' || b == '-')
            {
              s.append(b);
              state = NumberState.Exponent;
            }
            else
            {
              throw new InvalidJsonException("Bad exponent");
            }
            break;

          case Exponent:
            if (isDigit(b))
            {
              s.append(b);
            }
            else
            {
              in.unread(b);
              return new JsonNumber(key, Double.valueOf(s.toString()));
            }
            break;
        }
      }
    }
    catch (IOException e)
    {
      throw new InvalidJsonException("Error reading input while parsing number");
    }
  }

  private static JsonString parseString(String key, PushbackInputStream in) throws InvalidJsonException
  {
    String value = readToClosingQuote(in);
    return new JsonString(key, value);
  }

  private static JsonArray parseArray(String key, PushbackInputStream in) throws InvalidJsonException
  {
    JsonArray json = new JsonArray(key);

    char b = readNextNonWhitespace(in);

    if (b == ']')
    {
      return json;
    }
    else
    {
      try
      {
        in.unread(b);
      }
      catch (IOException e)
      {
        throw new InvalidJsonException("Error reading input while parsing array");
      }
    }

    while (true)
    {
      json.add(parseValue(null, in));

      b = readNextNonWhitespace(in);

      if (b == ']')
      {
        break;
      }
      else if (b != ',')
      {
        throw new InvalidJsonException("Expecting ',' after array value");
      }
    }

    return json;
  }

  private static JsonObject parseObject(PushbackInputStream in) throws InvalidJsonException
  {
    char b = readNextNonWhitespace(in);

    if (b == '{')
    {
      return parseObject(null, in);
    }
    else
    {
      throw new InvalidJsonException("Expecting '{' at start of file");
    }
  }

  private static JsonObject parseObject(String key, PushbackInputStream in) throws InvalidJsonException
  {
    JsonObject json = new JsonObject(key);

    char b = readNextNonWhitespace(in);

    if (b == '}')
    {
      return json;
    }
    else
    {
      try
      {
        in.unread(b);
      }
      catch (IOException e)
      {
        throw new InvalidJsonException("Error reading input while parsing object");
      }
    }

    while (true)
    {
      key = parseKey(in);

      b = readNextNonWhitespace(in);

      if (b == ':')
      {
        json.add(parseValue(key, in));

        b = readNextNonWhitespace(in);

        if (b == '}')
        {
          break;
        }
        else if (b != ',')
        {
          throw new InvalidJsonException("Expecting ',' after value");
        }
      }
      else
      {
        throw new InvalidJsonException("Expecting ':' after key");
      }
    }

    return json;
  }

  private static void parseBytes(String token, PushbackInputStream in) throws InvalidJsonException
  {
    byte[] bytes = token.getBytes();

    try
    {
      for (int i = 0; i < bytes.length; ++i)
      {
        if ((char)in.read() != bytes[i])
        {
          throw new InvalidJsonException("Expecting '" + token + "'");
        }
      }
    }
    catch (IOException e)
    {
      throw new InvalidJsonException("Error reading input while parsing '" + token + "'");
    }
  }

  private static char readNextNonWhitespace(PushbackInputStream in) throws InvalidJsonException
  {
    try
    {
      char b;

      while (true)
      {
        b = (char)in.read();

        if (!isWhitespace(b))
        {
          return b;
        }
      }
    }
    catch (IOException e)
    {
      throw new InvalidJsonException("Error reading input while parsing whitespace");
    }
  }

  private static String readToClosingQuote(PushbackInputStream in) throws InvalidJsonException
  {
    StringBuilder s = new StringBuilder();
    boolean escaping = false;

    try
    {
      while (in.available() > 0)
      {
        char b = (char)in.read();

        if (escaping)
        {
          switch (b)
          {
            default:
            case '"':
            case '\\':
            case '/':
              s.append(b);
              break;

            case 'b':
              // not supported yet
              break;

            case 'f':
              s.append('\f');
              break;

            case 'n':
              s.append('\n');
              break;

            case 'r':
              s.append('\r');
              break;

            case 't':
              s.append('\t');
              break;

            case 'u':
              s.append(readUnicodeDigits(in));
              break;
          }

          escaping = false;
        }
        else
        {
          if (b == '\\')
          {
            escaping = true;
          }
          else if (b == '"')
          {
            return s.toString();
          }
          else
          {
            s.append(b);
          }
        }
      }
    }
    catch (IOException e)
    {
      throw new InvalidJsonException("Error reading input while parsing key");
    }

    throw new InvalidJsonException("Reached end of file before closing quote");
  }

  private static String readUnicodeDigits(PushbackInputStream in) throws InvalidJsonException
  {
    // TODO
    return "[Unicode]";
  }

  private static boolean isDigit(char c)
  {
    return c >= '0' && c <= '9';
  }

  private static boolean isWhitespace(char c)
  {
    return c == ' ' || c == '\t' || c == '\f' || c == '\r' || c == '\n';
  }
}

