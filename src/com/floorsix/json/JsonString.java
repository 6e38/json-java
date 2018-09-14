/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.io.IOException;
import java.io.OutputStream;

public class JsonString extends Json
{
  private String string;

  public JsonString(String key, String string)
  {
    super(key);
    set(string);
  }

  @Override
  public void toJson(OutputStream out) throws IOException
  {
    keyToJson(out);

    escapedStringToJson(string, out);
  }

  public void set(String string)
  {
    this.string = string;
  }

  public String get()
  {
    return string;
  }
}

