/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class Json
{
  protected String key;

  public Json(String key)
  {
    this.key = key;
  }

  public String getKey()
  {
    return key;
  }

  public abstract void toJson(OutputStream out) throws IOException;

  protected void keyToJson(OutputStream out) throws IOException
  {
    if (key != null)
    {
      escapedStringToJson(key, out);
      out.write(':');
      out.write(' ');
    }
  }

  protected void escapedStringToJson(String s, OutputStream out) throws IOException
  {
    /* TODO
     * Handle unicode characters appropriately
     * \u1234
     */

    out.write('"');

    for (byte b : s.getBytes())
    {
      switch (b)
      {
        case '"':
        case '/':
        case '\\':
          out.write('\\');
          out.write(b);
          break;

        case '\b':
          out.write('\\');
          out.write('b');
          break;

        case '\f':
          out.write('\\');
          out.write('f');
          break;

        case '\n':
          out.write('\\');
          out.write('n');
          break;

        case '\r':
          out.write('\\');
          out.write('r');
          break;

        case '\t':
          out.write('\\');
          out.write('t');
          break;

        default:
          out.write(b);
          break;
      }
    }

    out.write('"');
  }

  @Override
  public String toString()
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try
    {
      keyToJson(out);
      toJson(out);
    }
    catch (IOException e)
    {
    }

    return out.toString();
  }
}

