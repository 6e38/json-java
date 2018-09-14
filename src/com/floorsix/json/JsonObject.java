/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class JsonObject extends Json
{
  private ArrayList<Json> children;

  public JsonObject(String key)
  {
    super(key);
    children = new ArrayList<Json>();
  }

  @Override
  public void toJson(OutputStream out) throws IOException
  {
    keyToJson(out);

    out.write('{');

    if (children.size() > 0)
    {
      Json first = children.get(0);

      for (Json json : children)
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

    out.write('}');
  }

  public Json get(String key)
  {
    if (key != null)
    {
      for (Json json : children)
      {
        if (json.getKey().equals(key))
        {
          return json;
        }
      }
    }

    return null;
  }

  public JsonNull set(String key)
  {
    Json json = get(key);

    if (json != null)
    {
      if (!(json instanceof JsonNull))
      {
        children.remove(json);
        json = null;
      }
    }

    if (json == null)
    {
      json = new JsonNull(key);
      children.add(json);
    }

    return (JsonNull)json;
  }

  public JsonBoolean set(String key, boolean bool)
  {
    Json json = get(key);

    if (json != null)
    {
      if (json instanceof JsonBoolean)
      {
        ((JsonBoolean)json).set(bool);
      }
      else
      {
        children.remove(json);
        json = null;
      }
    }

    if (json == null)
    {
      json = new JsonBoolean(key, bool);
      children.add(json);
    }

    return (JsonBoolean)json;
  }

  public JsonNumber set(String key, double number)
  {
    Json json = get(key);

    if (json != null)
    {
      if (json instanceof JsonNumber)
      {
        ((JsonNumber)json).set(number);
      }
      else
      {
        children.remove(json);
        json = null;
      }
    }

    if (json == null)
    {
      json = new JsonNumber(key, number);
      children.add(json);
    }

    return (JsonNumber)json;
  }

  public JsonString set(String key, String string)
  {
    Json json = get(key);

    if (json != null)
    {
      if (json instanceof JsonString)
      {
        ((JsonString)json).set(string);
      }
      else
      {
        children.remove(json);
        json = null;
      }
    }

    if (json == null)
    {
      json = new JsonString(key, string);
      children.add(json);
    }

    return (JsonString)json;
  }

  public JsonArray setArray(String key)
  {
    Json json = get(key);

    if (json != null)
    {
      if (json instanceof JsonArray)
      {
        ((JsonArray)json).clear();
      }
      else
      {
        children.remove(json);
        json = null;
      }
    }

    if (json == null)
    {
      json = new JsonArray(key);
      children.add(json);
    }

    return (JsonArray)json;
  }

  public JsonObject setObject(String key)
  {
    Json json = get(key);

    if (json != null)
    {
      if (json instanceof JsonObject)
      {
        ((JsonObject)json).clear();
      }
      else
      {
        children.remove(json);
        json = null;
      }
    }

    if (json == null)
    {
      json = new JsonObject(key);
      children.add(json);
    }

    return (JsonObject)json;
  }

  void set(Json json)
  {
    Json existing = get(json.getKey());

    if (existing != null)
    {
      children.remove(existing);
    }

    children.add(json);
  }

  public void clear()
  {
    children.clear();
  }
}

