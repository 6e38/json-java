
package json;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class JsonParser
{
  public static JsonObject parseFile(String filename)
  {
  }

  public static JsonObject parse(String raw) throws InvalidJsonException
  {
    return parseObject(null, new ByteArrayInputStream(raw.getBytes()));
  }

  private static String parseKey(InputStream in) throws InvalidJsonException
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

  private static JsonObject parseObject(String key, InputStream in) throws InvalidJsonException
  {
    JsonObject json = new JsonObject(key);

    char b = readNextNonWhitespace(in);

    while (true)
    {
      key = parseKey(in);
      b = readNextNonWhitespace(in);

      if (b == ':')
      {
      }
      else
      {
        throw new InvalidJsonException("Expecting ':' after key");
      }
    }

    return json;
  }

  private static char readNextNonWhitespace(InputStream in) throws InvalidJsonException
  {
    while (in.available() > 0)
    {
      char b = (char)in.read();

      switch (b)
      {
        case ' ':
        case '\t':
        case '\n':
        case '\r':
          break;
        default:
          return b;
      }
    }

    throw new InvalidJsonException("Unexpectedly reached end of file");
  }

  private static String readToClosingQuote(InputStream in) throws InvalidJsonException
  {
    StringBuilder s = new StringBuilder();
    boolean escaping = false;

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

    throw new InvalidJsonException("Reached end of file before closing quote");
  }

  private static String readUnicodeDigits(InputStream in) throws InvalidJsonException
  {
    // TODO
    return "[Unicode]";
  }
}

