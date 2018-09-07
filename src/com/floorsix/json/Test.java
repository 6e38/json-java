
package json;

public class Test
{
  public static void main(String[] args)
  {
    JsonObject jsonObject = new JsonObject(null);

    jsonObject.add("MyNull");
    jsonObject.add("MyTrueBoolean", true);
    jsonObject.add("MyFalseBoolean", false);
    jsonObject.add("MyNumber314", 3.1415);
    jsonObject.add("MyNumber10", 10);
    jsonObject.add("MyString", "The quick brown fox jumps over the lazy dog");

    jsonObject.addArray("MyEmptyArray");

    jsonObject.addObject("MyEmptyObject");

    JsonArray jsonArray = jsonObject.addArray("MyArray");
    jsonArray.add(true);
    jsonArray.add(false);
    jsonArray.add(49);
    jsonArray.add("String");
    jsonArray.addArray().add(7);
    jsonArray.addObject().add("junk", "here");
    jsonArray.add("End_of_Array");

    jsonObject.addObject("LastObject").addObject("nested").add("key", "value");
    System.out.println(jsonObject);
  }
}

