/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonContainer
{
  public JsonArray(String key)
  {
    super(key);
  }

  public JsonArray(String key, JsonContainer parent)
  {
    super(key, parent);
  }

  @Override
  public void toJson(OutputStream out) throws IOException
  {
    keyToJson(out);

    out.write('[');

    if (children.size() > 0)
    {
      boolean writeNewlines = countItems() > getNewlineThreshold();

      Json first = children.get(0);

      for (Json json : children)
      {
        if (json == first)
        {
          if (writeNewlines) { out.write('\n'); }
        }
        else
        {
          out.write(',');
          if (writeNewlines) { out.write('\n'); }
        }

        json.toJson(out);
      }

      if (writeNewlines) { out.write('\n'); }
    }

    out.write(']');
  }

  public Json get(int index)
  {
    Json json = null;

    try
    {
      json = children.get(index);
    }
    catch (IndexOutOfBoundsException e)
    {
    }

    return json;
  }

  public List<Json> getArray()
  {
    return children;
  }

  public JsonBoolean add(boolean bool)
  {
    JsonBoolean json = new JsonBoolean(null, bool);
    children.add(json);
    return json;
  }

  public JsonNumber add(double number)
  {
    JsonNumber json = new JsonNumber(null, number);

    if (isNumberPrecisionSet())
    {
      json.setNumberPrecision(getNumberPrecision());
    }

    children.add(json);
    return json;
  }

  public JsonNumber add(double number, int precision)
  {
    JsonNumber json = new JsonNumber(null, number, precision);
    children.add(json);
    return json;
  }

  public JsonString add(String string)
  {
    JsonString json = new JsonString(null, string);
    children.add(json);
    return json;
  }

  public JsonArray addArray()
  {
    JsonArray json = new JsonArray(null, this);
    children.add(json);
    return json;
  }

  public JsonObject addObject()
  {
    JsonObject json = new JsonObject(null, this);
    children.add(json);
    return json;
  }

  void add(Json json)
  {
    children.add(json);
  }

  @Override
  int countItems()
  {
    int n = 0;

    for (Json child : children)
    {
      if (child instanceof JsonContainer)
      {
        n += ((JsonContainer)child).countItems();
      }
      else
      {
        n++;
      }
    }

    return n == 0 ? 1 : n; // Account for empty container
  }
}

