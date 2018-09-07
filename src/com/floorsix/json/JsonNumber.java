
package json;

public class JsonNumber extends Json
{
  private double number;

  public JsonNumber(String key, double number)
  {
    super(key);
    set(number);
  }

  @Override
  public Type getType()
  {
    return Type.Number;
  }

  @Override
  public String toJson()
  {
    StringBuilder s = keyToJson();

    s.append(number);

    return s.toString();
  }

  public void set(double number)
  {
    this.number = number;
  }

  public double get()
  {
    return number;
  }

  public double getFloat()
  {
    return (float)number;
  }

  public long getLong()
  {
    return (long)number;
  }

  public int getInt()
  {
    return (int)number;
  }
}

