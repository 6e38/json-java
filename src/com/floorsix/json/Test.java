
package com.floorsix.json;

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

    System.out.println("=== TEST 2 =====================================================================");

    try
    {
      jsonObject = JsonParser.parse("{}");
      myassert(jsonObject.toJson().equals("{}"), "Parse test 1");

      jsonObject = JsonParser.parse("{\"key\":\"value\"}");
      myassert(jsonObject.toJson().equals("{\n\"key\": \"value\"\n}"), "Parse test 2");

      jsonObject = JsonParser.parse("{\n\"key\": 10\n}");
      myassert(jsonObject.toJson().equals("{\n\"key\": 10.0\n}"), "Parse test 3");

      jsonObject = JsonParser.parse("{\n\"key\": 10.1\n}");
      myassert(jsonObject.toJson().equals("{\n\"key\": 10.1\n}"), "Parse test 4");

      jsonObject = JsonParser.parse("{\n\"key\": -10.1\n}");
      myassert(jsonObject.toJson().equals("{\n\"key\": -10.1\n}"), "Parse test 5");

      jsonObject = JsonParser.parse("{\n\"key\": 10.1e+1\n}");
      myassert(jsonObject.toJson().equals("{\n\"key\": 101.0\n}"), "Parse test 6");

      jsonObject = JsonParser.parse("{\n\"key\": 10.1e-1\n}");
      myassert(jsonObject.toJson().equals("{\n\"key\": 1.01\n}"), "Parse test 7");

      jsonObject = JsonParser.parse("{\n\"key\": 0\n}");
      myassert(jsonObject.toJson().equals("{\n\"key\": 0.0\n}"), "Parse test 8");

      jsonObject = JsonParser.parse("{\n\"key\": 0.1234\n}");
      myassert(jsonObject.toJson().equals("{\n\"key\": 0.1234\n}"), "Parse test 9");

      jsonObject = JsonParser.parse("{\n\"k1\": 1, \"k2\" : 2\n}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": 1.0,\n\"k2\": 2.0\n}"), "Parse test 10");

      jsonObject = JsonParser.parse("{\n\"k1\": 1, \"k2\" : 2,\"k3\":\"s3\"\n}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": 1.0,\n\"k2\": 2.0,\n\"k3\": \"s3\"\n}"), "Parse test 11");

      jsonObject = JsonParser.parse("{\"k1\":true}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": true\n}"), "Parse test 12");

      jsonObject = JsonParser.parse("{\"k1\":false}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": false\n}"), "Parse test 13");

      jsonObject = JsonParser.parse("{\"k1\":null}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": null\n}"), "Parse test 14");

      jsonObject = JsonParser.parse("{\"k1\":[true,false,null]}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": [\ntrue,\nfalse,\nnull\n]\n}"), "Parse test 15");

      jsonObject = JsonParser.parse("{\"k1\":[]}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": []\n}"), "Parse test 16");

      System.out.println(jsonObject);
    }
    catch (InvalidJsonException e)
    {
      System.out.println("FAIL: " + e);
    }
    catch (AssertionError e)
    {
      System.out.println("FAIL: " + e);
    }
  }

  private static void myassert(boolean b)
  {
    if (!b)
    {
      throw new AssertionError();
    }
  }

  private static void myassert(boolean b, String message)
  {
    if (!b)
    {
      throw new AssertionError(message);
    }
  }
}

