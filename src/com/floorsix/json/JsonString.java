
package com.floorsix.json;

public class JsonString extends Json
{
  private String string;

  public JsonString(String key, String string)
  {
    super(key);
    set(string);
  }

  @Override
  public Type getType()
  {
    return Type.String;
  }

  @Override
  public String toJson()
  {
    StringBuilder s = keyToJson();

    /* TODO
     * Characters that need to be escaped in string
     * ", \, /, \b, \f, \n, \r, \t, \u1234
     */

    s.append("\"");
    s.append(string);
    s.append("\"");

    return s.toString();
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

