/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

public abstract class Json
{
  enum Type {
    Null,
    Boolean,
    Number,
    String,
    Array,
    Object
  };

  protected String key;

  public Json(String key)
  {
    this.key = key;
  }

  public String getKey()
  {
    return key;
  }

  public abstract Type getType();
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

