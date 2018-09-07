/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

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

  public abstract String toJson();

  protected StringBuilder keyToJson()
  {
    StringBuilder s = new StringBuilder();

    if (key != null)
    {
      s.append("\"");
      s.append(key);
      s.append("\": ");
    }

    return s;
  }

  @Override
  public String toString()
  {
    return toJson();
  }
}

