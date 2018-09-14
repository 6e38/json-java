/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonArray extends Json
{
  private ArrayList<Json> array;

  public JsonArray(String key)
  {
    super(key);
    array = new ArrayList<Json>();
  }

  @Override
  public void toJson(OutputStream out) throws IOException
  {
    keyToJson(out);

    out.write('[');

    if (array.size() > 0)
    {
      Json first = array.get(0);

      for (Json json : array)
      {
        if (json == first)
        {
          out.write('\n');
        }
        else
        {
          out.write(',');
          out.write('\n');
        }

        json.toJson(out);
      }

      out.write('\n');
    }

    out.write(']');
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

    return json;
  }

  public List<Json> getArray()
  {
    return array;
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

  public void clear()
  {
    array.clear();
  }
}

