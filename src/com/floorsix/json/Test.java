/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

public class Test
{
  public static void main(String[] args)
  {
    try
    {
      test();
      System.out.println("PASS: all tests succeeded");
    }
    catch (AssertionError e)
    {
      System.out.println("FAIL: " + e.getMessage());
    }
  }

  private static void test()
  {
    JsonObject jsonObject;

    try
    {
      jsonObject = new JsonObject(null);
      assert jsonObject.toString().equals("{}") : "Create test 1";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      assert jsonObject.toString().equals("{\n\"k1\": null\n}") : "Create test 2";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", true);
      assert jsonObject.toString().equals("{\n\"k1\": true\n}") : "Create test 3";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", false);
      assert jsonObject.toString().equals("{\n\"k1\": false\n}") : "Create test 4";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 3.1415);
      assert jsonObject.toString().equals("{\n\"k1\": 3.1415\n}") : "Create test 5";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 10);
      assert jsonObject.toString().equals("{\n\"k1\": 10.0\n}") : "Create test 6";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", "The quick brown fox jumps over the lazy dog");
      assert jsonObject.toString().equals("{\n\"k1\": \"The quick brown fox jumps over the lazy dog\"\n}") :
          "Create test 7";

      jsonObject = new JsonObject(null);
      jsonObject.setArray("k1");
      assert jsonObject.toString().equals("{\n\"k1\": []\n}") : "Create test 8";

      jsonObject = new JsonObject(null);
      jsonObject.setObject("k1");
      assert jsonObject.toString().equals("{\n\"k1\": {}\n}") : "Create test 9";

      jsonObject = new JsonObject(null);
      JsonArray jsonArray = jsonObject.setArray("k1");
      jsonArray.add(true);
      jsonArray.add(false);
      jsonArray.add(49);
      jsonArray.add("s1");
      assert jsonObject.toString().equals("{\n\"k1\": [\ntrue,\nfalse,\n49.0,\n\"s1\"\n]\n}") : "Create test 10";

      jsonObject = new JsonObject(null);
      jsonObject.setObject("k1").setObject("k2").set("k3", "s1");
      assert jsonObject.toString().equals("{\n\"k1\": {\n\"k2\": {\n\"k3\": \"s1\"\n}\n}\n}") : "Create test 11";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1");
      assert jsonObject.toString().equals("{\n\"k1\": null\n}") : "Create test 12";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", "s1");
      jsonObject.set("k1", "s2");
      assert jsonObject.toString().equals("{\n\"k1\": \"s2\"\n}") : "Create test 13";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", true);
      jsonObject.set("k1", false);
      assert jsonObject.toString().equals("{\n\"k1\": false\n}") : "Create test 14";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 1);
      jsonObject.set("k1", 2);
      assert jsonObject.toString().equals("{\n\"k1\": 2.0\n}") : "Create test 15";

      jsonObject = new JsonObject(null);
      jsonObject.setObject("k1");
      jsonObject.setObject("k1");
      assert jsonObject.toString().equals("{\n\"k1\": {}\n}") : "Create test 16";

      jsonObject = new JsonObject(null);
      jsonObject.setArray("k1");
      jsonObject.setArray("k1");
      assert jsonObject.toString().equals("{\n\"k1\": []\n}") : "Create test 17";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1", true);
      assert jsonObject.toString().equals("{\n\"k1\": true\n}") : "Create test 18";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1", 1);
      assert jsonObject.toString().equals("{\n\"k1\": 1.0\n}") : "Create test 19";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1", "string");
      assert jsonObject.toString().equals("{\n\"k1\": \"string\"\n}") : "Create test 20";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.setArray("k1");
      assert jsonObject.toString().equals("{\n\"k1\": []\n}") : "Create test 21";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.setObject("k1");
      assert jsonObject.toString().equals("{\n\"k1\": {}\n}") : "Create test 22";

      jsonObject = JsonParser.parse("{}");
      assert jsonObject.toJson().equals("{}") : "Parse test 1";

      jsonObject = JsonParser.parse("{\"key\":\"value\"}");
      assert jsonObject.toJson().equals("{\n\"key\": \"value\"\n}") : "Parse test 2";

      jsonObject = JsonParser.parse("{\n\"key\": 10\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 10.0\n}") : "Parse test 3";

      jsonObject = JsonParser.parse("{\n\"key\": 10.1\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 10.1\n}") : "Parse test 4";

      jsonObject = JsonParser.parse("{\n\"key\": -10.1\n}");
      assert jsonObject.toJson().equals("{\n\"key\": -10.1\n}") : "Parse test 5";

      jsonObject = JsonParser.parse("{\n\"key\": 10.1e+1\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 101.0\n}") : "Parse test 6";

      jsonObject = JsonParser.parse("{\n\"key\": 10.1e-1\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 1.01\n}") : "Parse test 7";

      jsonObject = JsonParser.parse("{\n\"key\": 0\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 0.0\n}") : "Parse test 8";

      jsonObject = JsonParser.parse("{\n\"key\": 0.1234\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 0.1234\n}") : "Parse test 9";

      jsonObject = JsonParser.parse("{\n\"k1\": 1, \"k2\" : 2\n}");
      assert jsonObject.toJson().equals("{\n\"k1\": 1.0,\n\"k2\": 2.0\n}") : "Parse test 10";

      jsonObject = JsonParser.parse("{\n\"k1\": 1, \"k2\" : 2,\"k3\":\"s3\"\n}");
      assert jsonObject.toJson().equals("{\n\"k1\": 1.0,\n\"k2\": 2.0,\n\"k3\": \"s3\"\n}") : "Parse test 11";

      jsonObject = JsonParser.parse("{\"k1\":true}");
      assert jsonObject.toJson().equals("{\n\"k1\": true\n}") : "Parse test 12";

      jsonObject = JsonParser.parse("{\"k1\":false}");
      assert jsonObject.toJson().equals("{\n\"k1\": false\n}") : "Parse test 13";

      jsonObject = JsonParser.parse("{\"k1\":null}");
      assert jsonObject.toJson().equals("{\n\"k1\": null\n}") : "Parse test 14";

      jsonObject = JsonParser.parse("{\"k1\":[true,false,null]}");
      assert jsonObject.toJson().equals("{\n\"k1\": [\ntrue,\nfalse,\nnull\n]\n}") : "Parse test 15";

      jsonObject = JsonParser.parse("{\"k1\":[]}");
      assert jsonObject.toJson().equals("{\n\"k1\": []\n}") : "Parse test 16";

      jsonObject = JsonParser.parse("{\"k1\":[[[]]]}");
      assert jsonObject.toJson().equals("{\n\"k1\": [\n[\n[]\n]\n]\n}") : "Parse test 17";

      jsonObject = JsonParser.parse("{\"k1\":[[[\"s\",1]]]}");
      assert jsonObject.toJson().equals("{\n\"k1\": [\n[\n[\n\"s\",\n1.0\n]\n]\n]\n}") : "Parse test 18";

      jsonObject = JsonParser.parse("{\"k1\":{}}");
      assert jsonObject.toJson().equals("{\n\"k1\": {}\n}") : "Parse test 19";

      jsonObject = JsonParser.parse("{\"k1\":{\"k2\":null}}");
      assert jsonObject.toJson().equals("{\n\"k1\": {\n\"k2\": null\n}\n}") : "Parse test 20";

      jsonObject = JsonParser.parse("{\"k1\":null,\"k1\":true,\"k1\":1,\"k1\":\"s1\",\"k1\":{},\"k1\":[]}");
      assert jsonObject.toJson().equals("{\n\"k1\": []\n}") : "Parse test 21";
    }
    catch (InvalidJsonException e)
    {
      assert false : "Caught unexpected InvalidJsonException";
    }

    try
    {
      jsonObject = JsonParser.parse("");
      assert false : "Exception test 1";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{");
      assert false : "Exception test 2";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"");
      assert false : "Exception test 3";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1");
      assert false : "Exception test 4";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1\"}");
      assert false : "Exception test 5";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":}");
      assert false : "Exception test 6";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":1,}");
      assert false : "Exception test 7";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":1.1.}");
      assert false : "Exception test 8";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":1,2}");
      assert false : "Exception test 9";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{k1:1}");
      assert false : "Exception test 10";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{[}");
      assert false : "Exception test 11";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{]}");
      assert false : "Exception test 12";
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{[\"k1\":1]}");
      assert false : "Exception test 13";
    }
    catch (InvalidJsonException e) {}
  }
}

