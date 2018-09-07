
package json;

public class JsonBoolean extends Json
{
  private boolean bool;

  public JsonBoolean(String key, boolean bool)
  {
    super(key);
    set(bool);
  }

  @Override
  public Type getType()
  {
    return Type.Boolean;
  }

  @Override
  public String toJson()
  {
    StringBuilder s = keyToJson();

    s.append(bool);

    return s.toString();
  }

  public void set(boolean bool)
  {
    this.bool = bool;
  }

  public boolean get()
  {
    return bool;
  }
}

