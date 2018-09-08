/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.util.List;

public class Test
{
  public static void main(String[] args)
  {
    try
    {
      test();
      System.out.println("\nPASS: all tests succeeded");
    }
    catch (AssertionError e)
    {
      System.out.print("\nTest failure");

      if (e.getMessage() != null)
      {
        System.out.print(": " + e.getMessage());
      }

      System.out.println();

      for (StackTraceElement trace : e.getStackTrace())
      {
        System.out.println(">> " + trace);
      }

      System.out.println("\nFAIL");
    }
  }

  private static void test()
  {
    JsonObject jsonObject;

    try
    {
      jsonObject = new JsonObject(null);
      assert jsonObject.toString().equals("{}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      assert jsonObject.toString().equals("{\n\"k1\": null\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", true);
      assert jsonObject.toString().equals("{\n\"k1\": true\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", false);
      assert jsonObject.toString().equals("{\n\"k1\": false\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 3.1415);
      assert jsonObject.toString().equals("{\n\"k1\": 3.1415\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 10);
      assert jsonObject.toString().equals("{\n\"k1\": 10.0\n}") : "Create test 6";

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", "The quick brown fox jumps over the lazy dog");
      assert jsonObject.toString().equals("{\n\"k1\": \"The quick brown fox jumps over the lazy dog\"\n}");

      jsonObject = new JsonObject(null);
      jsonObject.setArray("k1");
      assert jsonObject.toString().equals("{\n\"k1\": []\n}");

      jsonObject = new JsonObject(null);
      jsonObject.setObject("k1");
      assert jsonObject.toString().equals("{\n\"k1\": {}\n}");

      jsonObject = new JsonObject(null);
      JsonArray jsonArray = jsonObject.setArray("k1");
      jsonArray.add(true);
      jsonArray.add(false);
      jsonArray.add(49);
      jsonArray.add("s1");
      assert jsonObject.toString().equals("{\n\"k1\": [\ntrue,\nfalse,\n49.0,\n\"s1\"\n]\n}");

      jsonObject = new JsonObject(null);
      jsonObject.setObject("k1").setObject("k2").set("k3", "s1");
      assert jsonObject.toString().equals("{\n\"k1\": {\n\"k2\": {\n\"k3\": \"s1\"\n}\n}\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1");
      assert jsonObject.toString().equals("{\n\"k1\": null\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", "s1");
      jsonObject.set("k1", "s2");
      assert jsonObject.toString().equals("{\n\"k1\": \"s2\"\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", true);
      jsonObject.set("k1", false);
      assert jsonObject.toString().equals("{\n\"k1\": false\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 1);
      jsonObject.set("k1", 2);
      assert jsonObject.toString().equals("{\n\"k1\": 2.0\n}");

      jsonObject = new JsonObject(null);
      jsonObject.setObject("k1");
      jsonObject.setObject("k1");
      assert jsonObject.toString().equals("{\n\"k1\": {}\n}");

      jsonObject = new JsonObject(null);
      jsonObject.setArray("k1");
      jsonObject.setArray("k1");
      assert jsonObject.toString().equals("{\n\"k1\": []\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1", true);
      assert jsonObject.toString().equals("{\n\"k1\": true\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1", 1);
      assert jsonObject.toString().equals("{\n\"k1\": 1.0\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1", "string");
      assert jsonObject.toString().equals("{\n\"k1\": \"string\"\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.setArray("k1");
      assert jsonObject.toString().equals("{\n\"k1\": []\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.setObject("k1");
      assert jsonObject.toString().equals("{\n\"k1\": {}\n}");

      jsonObject = JsonParser.parse("{}");
      assert jsonObject.toJson().equals("{}");

      jsonObject = JsonParser.parse("{\"key\":\"value\"}");
      assert jsonObject.toJson().equals("{\n\"key\": \"value\"\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 10\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 10.0\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 10.1\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 10.1\n}");

      jsonObject = JsonParser.parse("{\n\"key\": -10.1\n}");
      assert jsonObject.toJson().equals("{\n\"key\": -10.1\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 10.1e+1\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 101.0\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 10.1e-1\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 1.01\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 0\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 0.0\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 0.1234\n}");
      assert jsonObject.toJson().equals("{\n\"key\": 0.1234\n}");

      jsonObject = JsonParser.parse("{\n\"k1\": 1, \"k2\" : 2\n}");
      assert jsonObject.toJson().equals("{\n\"k1\": 1.0,\n\"k2\": 2.0\n}");

      jsonObject = JsonParser.parse("{\n\"k1\": 1, \"k2\" : 2,\"k3\":\"s3\"\n}");
      assert jsonObject.toJson().equals("{\n\"k1\": 1.0,\n\"k2\": 2.0,\n\"k3\": \"s3\"\n}");

      jsonObject = JsonParser.parse("{\"k1\":true}");
      assert jsonObject.toJson().equals("{\n\"k1\": true\n}");

      jsonObject = JsonParser.parse("{\"k1\":false}");
      assert jsonObject.toJson().equals("{\n\"k1\": false\n}");

      jsonObject = JsonParser.parse("{\"k1\":null}");
      assert jsonObject.toJson().equals("{\n\"k1\": null\n}");

      jsonObject = JsonParser.parse("{\"k1\":[true,false,null]}");
      assert jsonObject.toJson().equals("{\n\"k1\": [\ntrue,\nfalse,\nnull\n]\n}");

      jsonObject = JsonParser.parse("{\"k1\":[]}");
      assert jsonObject.toJson().equals("{\n\"k1\": []\n}");

      jsonObject = JsonParser.parse("{\"k1\":[[[]]]}");
      assert jsonObject.toJson().equals("{\n\"k1\": [\n[\n[]\n]\n]\n}");

      jsonObject = JsonParser.parse("{\"k1\":[[[\"s\",1]]]}");
      assert jsonObject.toJson().equals("{\n\"k1\": [\n[\n[\n\"s\",\n1.0\n]\n]\n]\n}");

      jsonObject = JsonParser.parse("{\"k1\":{}}");
      assert jsonObject.toJson().equals("{\n\"k1\": {}\n}");

      jsonObject = JsonParser.parse("{\"k1\":{\"k2\":null}}");
      assert jsonObject.toJson().equals("{\n\"k1\": {\n\"k2\": null\n}\n}");

      jsonObject = JsonParser.parse("{\"k1\":null,\"k1\":true,\"k1\":1,\"k1\":\"s1\",\"k1\":{},\"k1\":[]}");
      assert jsonObject.toJson().equals("{\n\"k1\": []\n}") : "Parse test 21";

      jsonObject = JsonParser.parse("{\"k1\": [0,1,2,3,4]}");
      Json j = jsonObject.get("k1");
      assert j instanceof JsonArray;
      JsonArray ja = (JsonArray)j;
      j = ja.get(0);
      assert j instanceof JsonNumber;
      assert (int)((JsonNumber)j).get() == 0;
      j = ja.get(4);
      assert j instanceof JsonNumber;
      assert (int)((JsonNumber)j).get() == 4;
      j = ja.get(2);
      assert j instanceof JsonNumber;
      assert (int)((JsonNumber)j).get() == 2;
      j = ja.get(5);
      assert j == null;
      List<Json> list = ja.getArray();
      assert list.size() == 5;
      assert list.get(0) instanceof JsonNumber;
      assert (int)((JsonNumber)list.get(1)).get() == 1;
    }
    catch (InvalidJsonException e)
    {
      assert false;
    }

    try
    {
      jsonObject = JsonParser.parse("");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1\"}");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":}");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":1,}");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":1.1.}");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":1,2}");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{k1:1}");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{[}");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{]}");
      assert false;
    }
    catch (InvalidJsonException e) {}

    try
    {
      jsonObject = JsonParser.parse("{[\"k1\":1]}");
      assert false;
    }
    catch (InvalidJsonException e) {}
  }
}

