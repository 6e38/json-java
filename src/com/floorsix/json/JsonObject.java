
package json;

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
  public Type getType()
  {
    return Type.Object;
  }

  @Override
  public String toJson()
  {
    StringBuilder s = keyToJson();

    s.append("{");

    if (children.size() > 0)
    {
      Json first = children.get(0);

      for (Json json : children)
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

    s.append("}");

    return s.toString();
  }

  public Json get(String key)
  {
    for (Json json : children)
    {
      if (json.getKey().equals(key))
      {
        return json;
      }
    }

    return null;
  }

  public JsonNull add(String key)
  {
    JsonNull json = new JsonNull(key);
    children.add(json);
    return json;
  }

  public JsonBoolean add(String key, boolean bool)
  {
    JsonBoolean json = new JsonBoolean(key, bool);
    children.add(json);
    return json;
  }

  public JsonNumber add(String key, double number)
  {
    JsonNumber json = new JsonNumber(key, number);
    children.add(json);
    return json;
  }

  public JsonString add(String key, String string)
  {
    JsonString json = new JsonString(key, string);
    children.add(json);
    return json;
  }

  public JsonArray addArray(String key)
  {
    JsonArray json = new JsonArray(key);
    children.add(json);
    return json;
  }

  public JsonObject addObject(String key)
  {
    JsonObject json = new JsonObject(key);
    children.add(json);
    return json;
  }

  void add(Json json)
  {
    children.add(json);
  }
}

