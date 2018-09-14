/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.io.IOException;
import java.io.OutputStream;

public class JsonNumber extends Json
{
  private double number;

  public JsonNumber(String key, double number)
  {
    super(key);
    set(number);
  }

  @Override
  public void toJson(OutputStream out) throws IOException
  {
    keyToJson(out);

    final double almost0 = 1e-16;

    StringBuilder s = new StringBuilder();

    double fraction = number - (double)(int)number;

    if (Math.abs(fraction) < almost0)
    {
      s.append((long)number);
    }
    else
    {
      s.append(number);
    }

    if (Math.abs(number) >= 10)
    {
      int i = s.indexOf("E");
      if (i != -1)
      {
        s.insert(i + 1, '+');
      }
    }

    out.write(s.toString().getBytes());
  }

  public void set(double number)
  {
    this.number = number;
  }

  public double get()
  {
    return number;
  }

  public double getFloat()
  {
    return (float)number;
  }

  public long getLong()
  {
    return (long)number;
  }

  public int getInt()
  {
    return (int)number;
  }
}

