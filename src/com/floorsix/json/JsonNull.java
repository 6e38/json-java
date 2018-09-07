/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

public class JsonNull extends Json
{
  public JsonNull(String key)
  {
    super(key);
  }

  @Override
  public Type getType()
  {
    return Type.Null;
  }

  @Override
  public String toJson()
  {
    StringBuilder s = keyToJson();

    s.append("null");

    return s.toString();
  }
}

