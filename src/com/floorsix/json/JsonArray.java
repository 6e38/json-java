/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.util.ArrayList;

public class JsonArray extends Json
{
  private ArrayList<Json> array;

  public JsonArray(String key)
  {
    super(key);
    array = new ArrayList<Json>();
  }

  @Override
  public Type getType()
  {
    return Type.Array;
  }

  @Override
  public String toJson()
  {
    StringBuilder s = keyToJson();

    s.append("[");

    if (array.size() > 0)
    {
      Json first = array.get(0);

      for (Json json : array)
      {
        if (json == first)
        {
          s.append("\n");
        }
        else
        {
          s.append(",\n");
        }

        s.append(json.toJson());
      }

      s.append("\n");
    }

    s.append("]");

    return s.toString();
  }

  public Json get(int index)
  {
    Json json = null;

    try
    {
      json = array.get(index);
    }
    catch (IndexOutOfBoundsException e)
    {
    }

    return null;
  }

  public JsonBoolean add(boolean bool)
  {
    JsonBoolean json = new JsonBoolean(null, bool);
    array.add(json);
    return json;
  }

  public JsonNumber add(double number)
  {
    JsonNumber json = new JsonNumber(null, number);
    array.add(json);
    return json;
  }

  public JsonString add(String string)
  {
    JsonString json = new JsonString(null, string);
    array.add(json);
    return json;
  }

  public JsonArray addArray()
  {
    JsonArray json = new JsonArray(null);
    array.add(json);
    return json;
  }

  public JsonObject addObject()
  {
    JsonObject json = new JsonObject(null);
    array.add(json);
    return json;
  }

  void add(Json json)
  {
    array.add(json);
  }
}

