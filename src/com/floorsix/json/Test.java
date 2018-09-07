
package com.floorsix.json;

public class Test
{
  public static void main(String[] args)
  {
    JsonObject jsonObject;

    try
    {
      jsonObject = new JsonObject(null);
      myassert(jsonObject.toString().equals("{}"), "Create test 1");

      jsonObject = new JsonObject(null);
      jsonObject.add("k1");
      myassert(jsonObject.toString().equals("{\n\"k1\": null\n}"), "Create test 2");

      jsonObject = new JsonObject(null);
      jsonObject.add("k1", true);
      myassert(jsonObject.toString().equals("{\n\"k1\": true\n}"), "Create test 3");

      jsonObject = new JsonObject(null);
      jsonObject.add("k1", false);
      myassert(jsonObject.toString().equals("{\n\"k1\": false\n}"), "Create test 4");

      jsonObject = new JsonObject(null);
      jsonObject.add("k1", 3.1415);
      myassert(jsonObject.toString().equals("{\n\"k1\": 3.1415\n}"), "Create test 5");

      jsonObject = new JsonObject(null);
      jsonObject.add("k1", 10);
      myassert(jsonObject.toString().equals("{\n\"k1\": 10.0\n}"), "Create test 6");

      jsonObject = new JsonObject(null);
      jsonObject.add("k1", "The quick brown fox jumps over the lazy dog");
      myassert(jsonObject.toString().equals("{\n\"k1\": \"The quick brown fox jumps over the lazy dog\"\n}"),
          "Create test 7");

      jsonObject = new JsonObject(null);
      jsonObject.addArray("k1");
      myassert(jsonObject.toString().equals("{\n\"k1\": []\n}"), "Create test 8");

      jsonObject = new JsonObject(null);
      jsonObject.addObject("k1");
      myassert(jsonObject.toString().equals("{\n\"k1\": {}\n}"), "Create test 9");

      jsonObject = new JsonObject(null);
      JsonArray jsonArray = jsonObject.addArray("k1");
      jsonArray.add(true);
      jsonArray.add(false);
      jsonArray.add(49);
      jsonArray.add("s1");
      myassert(jsonObject.toString().equals("{\n\"k1\": [\ntrue,\nfalse,\n49.0,\n\"s1\"\n]\n}"), "Create test 10");

      jsonObject = new JsonObject(null);
      jsonObject.addObject("k1").addObject("k2").add("k3", "s1");
      myassert(jsonObject.toString().equals("{\n\"k1\": {\n\"k2\": {\n\"k3\": \"s1\"\n}\n}\n}"), "Create test 11");

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

      jsonObject = JsonParser.parse("{\"k1\":[[[]]]}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": [\n[\n[]\n]\n]\n}"), "Parse test 17");

      jsonObject = JsonParser.parse("{\"k1\":[[[\"s\",1]]]}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": [\n[\n[\n\"s\",\n1.0\n]\n]\n]\n}"), "Parse test 18");

      jsonObject = JsonParser.parse("{\"k1\":{}}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": {}\n}"), "Parse test 19");

      jsonObject = JsonParser.parse("{\"k1\":{\"k2\":null}}");
      myassert(jsonObject.toJson().equals("{\n\"k1\": {\n\"k2\": null\n}\n}"), "Parse test 20");

      try
      {
        jsonObject = JsonParser.parse("");
        myassert(false, "Exception test 1");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{");
        myassert(false, "Exception test 2");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{\"");
        myassert(false, "Exception test 3");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{\"k1");
        myassert(false, "Exception test 4");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{\"k1\"}");
        myassert(false, "Exception test 5");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{\"k1\":}");
        myassert(false, "Exception test 6");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{\"k1\":1,}");
        myassert(false, "Exception test 7");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{\"k1\":1.1.}");
        myassert(false, "Exception test 8");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{\"k1\":1,2}");
        myassert(false, "Exception test 9");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{k1:1}");
        myassert(false, "Exception test 10");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{[}");
        myassert(false, "Exception test 11");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{]}");
        myassert(false, "Exception test 12");
      }
      catch (InvalidJsonException e) {}

      try
      {
        jsonObject = JsonParser.parse("{[\"k1\":1]}");
        myassert(false, "Exception test 13");
      }
      catch (InvalidJsonException e) {}

      System.out.println("PASS: All tests passed");
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

