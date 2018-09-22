/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import com.floorsix.test.AbstractTest;
import java.util.Date;
import java.util.List;

public class Test extends AbstractTest
{
  public static void main(String[] args)
  {
    (new Test()).run();
  }

  void test()
  {
    JsonObject jsonObject;

    try
    {
      jsonObject = new JsonObject(null);
      assert jsonObject.toString().equals("{}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      assert jsonObject.toString().equals("{\n\"k1\": null\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", true);
      assert jsonObject.toString().equals("{\n\"k1\": true\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", false);
      assert jsonObject.toString().equals("{\n\"k1\": false\n}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 3);
      assert jsonObject.toString().equals("{\n\"k1\": 3\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 3.1415926535);
      assert jsonObject.toString().equals("{\n\"k1\": 3.1415926535\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", -3.1);
      assert jsonObject.toString().equals("{\n\"k1\": -3.1\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 3.1);
      assert jsonObject.toString().equals("{\n\"k1\": 3.1\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 3.1415926);
      assert jsonObject.toString().equals("{\n\"k1\": 3.1415926\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 31415926);
      assert jsonObject.toString().equals("{\n\"k1\": 31415926\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 31415926535897l);
      assert jsonObject.toString().equals("{\n\"k1\": 3.1415926535897E+13\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", -31415926535897l);
      assert jsonObject.toString().equals("{\n\"k1\": -3.1415926535897E+13\n}") : jsonObject;

      // The following test was added to catch a bug where the code would replace the 'E' in
      // the *key* with "E+"
      jsonObject = new JsonObject(null);
      jsonObject.set("k1E20", -31415926535897l);
      assert jsonObject.toString().equals("{\n\"k1E20\": -3.1415926535897E+13\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 0.1);
      assert jsonObject.toString().equals("{\n\"k1\": 0.1\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 1E-5);
      assert jsonObject.toString().equals("{\n\"k1\": 1.0E-5\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 10);
      assert jsonObject.toString().equals("{\n\"k1\": 10\n}") : jsonObject;

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
      assert jsonObject.toString().equals("{\n\"k1\": [\ntrue,\nfalse,\n49,\n\"s1\"\n]\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.setObject("k1").setObject("k2").set("k3", "s1");
      assert jsonObject.toString().equals("{\n\"k1\": {\n\"k2\": {\n\"k3\": \"s1\"\n}\n}\n}") : jsonObject;

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
      assert jsonObject.toString().equals("{\n\"k1\": 2\n}");

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
      assert jsonObject.toString().equals("{\n\"k1\": 1\n}");

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

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 0.0000000000012);
      assert jsonObject.toString().equals("{\n\"k1\": 1.2E-12\n}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 2001000000000d);
      assert jsonObject.toString().equals("{\n\"k1\": 2.001E+12\n}") : jsonObject;

      jsonObject = JsonParser.parse("{}");
      assert jsonObject.toString().equals("{}");

      jsonObject = JsonParser.parse("{\"key\":\"value\"}");
      assert jsonObject.toString().equals("{\n\"key\": \"value\"\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 10\n}");
      assert jsonObject.toString().equals("{\n\"key\": 10\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 10.1\n}");
      assert jsonObject.toString().equals("{\n\"key\": 10.1\n}");

      jsonObject = JsonParser.parse("{\n\"key\": -10.1\n}");
      assert jsonObject.toString().equals("{\n\"key\": -10.1\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 10.1e+1\n}");
      assert jsonObject.toString().equals("{\n\"key\": 101\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 10.1e-1\n}");
      assert jsonObject.toString().equals("{\n\"key\": 1.01\n}") : jsonObject;

      jsonObject = JsonParser.parse("{\n\"key\": 0\n}");
      assert jsonObject.toString().equals("{\n\"key\": 0\n}");

      jsonObject = JsonParser.parse("{\n\"key\": 0.1234\n}");
      assert jsonObject.toString().equals("{\n\"key\": 0.1234\n}");

      jsonObject = JsonParser.parse("{\n\"k1\": 1, \"k2\" : 2\n}");
      assert jsonObject.toString().equals("{\n\"k1\": 1,\n\"k2\": 2\n}");

      jsonObject = JsonParser.parse("{\n\"k1\": 1, \"k2\" : 2,\"k3\":\"s3\"\n}");
      assert jsonObject.toString().equals("{\n\"k1\": 1,\n\"k2\": 2,\n\"k3\": \"s3\"\n}");

      jsonObject = JsonParser.parse("{\"k1\":true}");
      assert jsonObject.toString().equals("{\n\"k1\": true\n}");

      jsonObject = JsonParser.parse("{\"k1\":false}");
      assert jsonObject.toString().equals("{\n\"k1\": false\n}");

      jsonObject = JsonParser.parse("{\"k1\":null}");
      assert jsonObject.toString().equals("{\n\"k1\": null\n}");

      jsonObject = JsonParser.parse("{\"k1\":[true,false,null]}");
      assert jsonObject.toString().equals("{\n\"k1\": [\ntrue,\nfalse,\nnull\n]\n}");

      jsonObject = JsonParser.parse("{\"k1\":[]}");
      assert jsonObject.toString().equals("{\n\"k1\": []\n}");

      jsonObject = JsonParser.parse("{\"k1\":[[[]]]}");
      assert jsonObject.toString().equals("{\n\"k1\": [\n[\n[]\n]\n]\n}");

      jsonObject = JsonParser.parse("{\"k1\":[[[\"s\",1]]]}");
      assert jsonObject.toString().equals("{\n\"k1\": [\n[\n[\n\"s\",\n1\n]\n]\n]\n}");

      jsonObject = JsonParser.parse("{\"k1\":{}}");
      assert jsonObject.toString().equals("{\n\"k1\": {}\n}");

      jsonObject = JsonParser.parse("{\"k1\":{\"k2\":null}}");
      assert jsonObject.toString().equals("{\n\"k1\": {\n\"k2\": null\n}\n}");

      jsonObject = JsonParser.parse("{\"k1\":null,\"k1\":true,\"k1\":1,\"k1\":\"s1\",\"k1\":{},\"k1\":[]}");
      assert jsonObject.toString().equals("{\n\"k1\": []\n}") : "Parse test 21";

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

      jsonObject = new JsonObject(null);
      Date date = new Date();
      jsonObject.set("timestamp", date.getTime());
      JsonObject jo = JsonParser.parse(jsonObject.toString());
      Json json = jo.get("timestamp");
      assert json instanceof JsonNumber : json;
      long timestamp = ((JsonNumber)json).getLong();
      Date parseDate = new Date(timestamp);
      assert parseDate.equals(date) : "Dates " + parseDate + " != " + date;
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

    jsonObject = new JsonObject(null);
    jsonObject.set("Interesting string\"\"\n\t\f\b\r\\/", "\"\"\n\t\f\b\r\\/");
    assert jsonObject.toString().equals("{\n\"Interesting string\\\"\\\"\\n\\t\\f\\b\\r\\\\\\/\": " +
        "\"\\\"\\\"\\n\\t\\f\\b\\r\\\\\\/\"\n}") : jsonObject;

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":\"string\\nvalue\"}");
      assert jsonObject.toString().equals("{\n\"k1\": \"string\\nvalue\"\n}") : jsonObject;
    }
    catch (InvalidJsonException e)
    {
      assert false : e;
    }

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":\"string\\nvalue\"}");
      Json j = jsonObject.get("k1");
      assert j instanceof JsonString;
      assert ((JsonString)j).get().equals("string\nvalue") : j;
      assert jsonObject.toString().equals("{\n\"k1\": \"string\\nvalue\"\n}") : jsonObject;
    }
    catch (InvalidJsonException e)
    {
      assert false : e;
    }

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":\"string\\\"value\"}");
      Json j = jsonObject.get("k1");
      assert j instanceof JsonString;
      assert ((JsonString)j).get().equals("string\"value") : j;
      assert jsonObject.toString().equals("{\n\"k1\": \"string\\\"value\"\n}") : jsonObject;
    }
    catch (InvalidJsonException e)
    {
      assert false : e;
    }
  }

  void testNumberPrecision()
  {
    JsonObject json;

    json = new JsonObject(null);
    json.set("k1", 1.9999, 1);
    assert json.toString().equals("{\n\"k1\": 2.0\n}") : json;

    json = new JsonObject(null);
    json.set("k1", 1.9999, 4);
    assert json.toString().equals("{\n\"k1\": 1.9999\n}") : json;

    json = new JsonObject(null);
    json.set("k1", 1, 4);
    assert json.toString().equals("{\n\"k1\": 1.0000\n}") : json;

    json = new JsonObject(null);
    json.set("k1", 1, 0);
    assert json.toString().equals("{\n\"k1\": 1\n}") : json;

    json = new JsonObject(null);
    json.set("k1", 1.333333333, 0);
    assert json.toString().equals("{\n\"k1\": 1\n}") : json;

    json = new JsonObject(null);
    json.set("k1", -1.04, 1);
    assert json.toString().equals("{\n\"k1\": -1.0\n}") : json;

    json = new JsonObject(null);
    json.set("k1", -1.05, 1);
    assert json.toString().equals("{\n\"k1\": -1.1\n}") : json;

    json = new JsonObject(null);
    json.set("k1", 2.05, -1);
    assert json.toString().equals("{\n\"k1\": 2.05\n}") : json;
  }

  void testNumberPrecisionWithJsonArray()
  {
    JsonArray json;

    json = new JsonArray(null);
    json.add(1.9999, 1);
    json.add(2.05, -1);
    json.add(-1.04, 1);
    json.add(-1.05, 1);
    assert json.toString().equals("[\n2.0,\n2.05,\n-1.0,\n-1.1\n]") : json;
  }
}

