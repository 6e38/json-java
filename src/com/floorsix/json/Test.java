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
      assert jsonObject.toString().equals("{\"k1\":null}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", true);
      assert jsonObject.toString().equals("{\"k1\":true}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", false);
      assert jsonObject.toString().equals("{\"k1\":false}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 3);
      assert jsonObject.toString().equals("{\"k1\":3}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 3.1415926535);
      assert jsonObject.toString().equals("{\"k1\":3.1415926535}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", -3.1);
      assert jsonObject.toString().equals("{\"k1\":-3.1}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 3.1);
      assert jsonObject.toString().equals("{\"k1\":3.1}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 3.1415926);
      assert jsonObject.toString().equals("{\"k1\":3.1415926}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 31415926);
      assert jsonObject.toString().equals("{\"k1\":31415926}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 31415926535897l);
      assert jsonObject.toString().equals("{\"k1\":3.1415926535897E+13}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", -31415926535897l);
      assert jsonObject.toString().equals("{\"k1\":-3.1415926535897E+13}") : jsonObject;

      // The following test was added to catch a bug where the code would replace the 'E' in
      // the *key* with "E+"
      jsonObject = new JsonObject(null);
      jsonObject.set("k1E20", -31415926535897l);
      assert jsonObject.toString().equals("{\"k1E20\":-3.1415926535897E+13}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 0.1);
      assert jsonObject.toString().equals("{\"k1\":0.1}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 1E-5);
      assert jsonObject.toString().equals("{\"k1\":1.0E-5}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 10);
      assert jsonObject.toString().equals("{\"k1\":10}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", "The quick brown fox jumps over the lazy dog");
      assert jsonObject.toString().equals("{\"k1\":\"The quick brown fox jumps over the lazy dog\"}");

      jsonObject = new JsonObject(null);
      jsonObject.setArray("k1");
      assert jsonObject.toString().equals("{\"k1\":[]}");

      jsonObject = new JsonObject(null);
      jsonObject.setObject("k1");
      assert jsonObject.toString().equals("{\"k1\":{}}");

      jsonObject = new JsonObject(null);
      JsonArray jsonArray = jsonObject.setArray("k1");
      jsonArray.add(true);
      jsonArray.add(false);
      jsonArray.add(49);
      jsonArray.add("s1");
      assert jsonObject.toString().equals("{\"k1\":[true,false,49,\"s1\"]}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.setObject("k1").setObject("k2").set("k3", "s1");
      assert jsonObject.toString().equals("{\"k1\":{\"k2\":{\"k3\":\"s1\"}}}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1");
      assert jsonObject.toString().equals("{\"k1\":null}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", "s1");
      jsonObject.set("k1", "s2");
      assert jsonObject.toString().equals("{\"k1\":\"s2\"}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", true);
      jsonObject.set("k1", false);
      assert jsonObject.toString().equals("{\"k1\":false}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 1);
      jsonObject.set("k1", 2);
      assert jsonObject.toString().equals("{\"k1\":2}");

      jsonObject = new JsonObject(null);
      jsonObject.setObject("k1");
      jsonObject.setObject("k1");
      assert jsonObject.toString().equals("{\"k1\":{}}");

      jsonObject = new JsonObject(null);
      jsonObject.setArray("k1");
      jsonObject.setArray("k1");
      assert jsonObject.toString().equals("{\"k1\":[]}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1", true);
      assert jsonObject.toString().equals("{\"k1\":true}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1", 1);
      assert jsonObject.toString().equals("{\"k1\":1}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.set("k1", "string");
      assert jsonObject.toString().equals("{\"k1\":\"string\"}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.setArray("k1");
      assert jsonObject.toString().equals("{\"k1\":[]}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1");
      jsonObject.setObject("k1");
      assert jsonObject.toString().equals("{\"k1\":{}}");

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 0.0000000000012);
      assert jsonObject.toString().equals("{\"k1\":1.2E-12}") : jsonObject;

      jsonObject = new JsonObject(null);
      jsonObject.set("k1", 2001000000000d);
      assert jsonObject.toString().equals("{\"k1\":2.001E+12}") : jsonObject;

      jsonObject = JsonParser.parse("{}");
      assert jsonObject.toString().equals("{}");

      jsonObject = JsonParser.parse("{\"key\":\"value\"}");
      assert jsonObject.toString().equals("{\"key\":\"value\"}");

      jsonObject = JsonParser.parse("{\"key\":10}");
      assert jsonObject.toString().equals("{\"key\":10}");

      jsonObject = JsonParser.parse("{\"key\":10.1}");
      assert jsonObject.toString().equals("{\"key\":10.1}");

      jsonObject = JsonParser.parse("{\"key\":-10.1}");
      assert jsonObject.toString().equals("{\"key\":-10.1}");

      jsonObject = JsonParser.parse("{\"key\":10.1e+1}");
      assert jsonObject.toString().equals("{\"key\":101}");

      jsonObject = JsonParser.parse("{\"key\":10.1e-1}");
      assert jsonObject.toString().equals("{\"key\":1.01}") : jsonObject;

      jsonObject = JsonParser.parse("{\"key\":0}");
      assert jsonObject.toString().equals("{\"key\":0}");

      jsonObject = JsonParser.parse("{\"key\":0.1234}");
      assert jsonObject.toString().equals("{\"key\":0.1234}");

      jsonObject = JsonParser.parse("{\"k1\":1, \"k2\" : 2}");
      assert jsonObject.toString().equals("{\"k1\":1,\"k2\":2}");

      jsonObject = JsonParser.parse("{\"k1\":1, \"k2\" : 2,\"k3\":\"s3\"}");
      assert jsonObject.toString().equals("{\"k1\":1,\"k2\":2,\"k3\":\"s3\"}");

      jsonObject = JsonParser.parse("{\"k1\":true}");
      assert jsonObject.toString().equals("{\"k1\":true}");

      jsonObject = JsonParser.parse("{\"k1\":false}");
      assert jsonObject.toString().equals("{\"k1\":false}");

      jsonObject = JsonParser.parse("{\"k1\":null}");
      assert jsonObject.toString().equals("{\"k1\":null}");

      jsonObject = JsonParser.parse("{\"k1\":[true,false,null]}");
      assert jsonObject.toString().equals("{\"k1\":[true,false,null]}");

      jsonObject = JsonParser.parse("{\"k1\":[]}");
      assert jsonObject.toString().equals("{\"k1\":[]}");

      jsonObject = JsonParser.parse("{\"k1\":[[[]]]}");
      assert jsonObject.toString().equals("{\"k1\":[[[]]]}");

      jsonObject = JsonParser.parse("{\"k1\":[[[\"s\",1]]]}");
      assert jsonObject.toString().equals("{\"k1\":[[[\"s\",1]]]}");

      jsonObject = JsonParser.parse("{\"k1\":{}}");
      assert jsonObject.toString().equals("{\"k1\":{}}");

      jsonObject = JsonParser.parse("{\"k1\":{\"k2\":null}}");
      assert jsonObject.toString().equals("{\"k1\":{\"k2\":null}}");

      jsonObject = JsonParser.parse("{\"k1\":null,\"k1\":true,\"k1\":1,\"k1\":\"s1\",\"k1\":{},\"k1\":[]}");
      assert jsonObject.toString().equals("{\"k1\":[]}") : "Parse test 21";

      jsonObject = JsonParser.parse("{\"k1\":[0,1,2,3,4]}");
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
    assert jsonObject.toString().equals("{\"Interesting string\\\"\\\"\\n\\t\\f\\b\\r\\\\\\/\":" +
        "\"\\\"\\\"\\n\\t\\f\\b\\r\\\\\\/\"}") : jsonObject;

    try
    {
      jsonObject = JsonParser.parse("{\"k1\":\"string\\nvalue\"}");
      assert jsonObject.toString().equals("{\"k1\":\"string\\nvalue\"}") : jsonObject;
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
      assert jsonObject.toString().equals("{\"k1\":\"string\\nvalue\"}") : jsonObject;
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
      assert jsonObject.toString().equals("{\"k1\":\"string\\\"value\"}") : jsonObject;
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
    assert json.toString().equals("{\"k1\":2.0}") : json;

    json = new JsonObject(null);
    json.set("k1", 1.9999, 4);
    assert json.toString().equals("{\"k1\":1.9999}") : json;

    json = new JsonObject(null);
    json.set("k1", 1, 4);
    assert json.toString().equals("{\"k1\":1.0000}") : json;

    json = new JsonObject(null);
    json.set("k1", 1, 0);
    assert json.toString().equals("{\"k1\":1}") : json;

    json = new JsonObject(null);
    json.set("k1", 1.333333333, 0);
    assert json.toString().equals("{\"k1\":1}") : json;

    json = new JsonObject(null);
    json.set("k1", -1.04, 1);
    assert json.toString().equals("{\"k1\":-1.0}") : json;

    json = new JsonObject(null);
    json.set("k1", -1.05, 1);
    assert json.toString().equals("{\"k1\":-1.1}") : json;

    json = new JsonObject(null);
    json.set("k1", 2.05, -1);
    assert json.toString().equals("{\"k1\":2}") : json;
  }

  void testNumberPrecisionWithJsonArray()
  {
    JsonArray json;

    json = new JsonArray(null);
    json.add(1.9999, 1);
    json.add(2.05, -1);
    json.add(-1.04, 1);
    json.add(-1.05, 1);
    assert json.toString().equals("[2.0,2,-1.0,-1.1]") : json;
  }

  void testArrayNewlineInsertion()
  {
    JsonArray json;
    json = new JsonArray(null);
    for (int i = 0; i < 10; i++)
    {
      json.add(i);
    }
    assert json.toString().equals("[0,1,2,3,4,5,6,7,8,9]") : json;
    json.add(10);
    assert json.toString().equals("[\n0,\n1,\n2,\n3,\n4,\n5,\n6,\n7,\n8,\n9,\n10\n]") : json;

    json = new JsonArray(null);
    JsonArray json0 = json.addArray();
    json0.add(1);
    json0.add(2);
    json0.add(3);
    JsonArray json1 = json.addArray();
    json1.add(4);
    json1.add(5);
    json1.add(6);
    json.add(7);
    json.add(8);
    json.add(9);
    json.add(10);
    assert json.toString().equals("[[1,2,3],[4,5,6],7,8,9,10]") : json;
    JsonArray json2 = json0.addArray();
    assert json.toString().equals("[\n[1,2,3,[]],\n[4,5,6],\n7,\n8,\n9,\n10\n]") : json;

    json = new JsonArray(null);
    json.addObject().set("k1",1);
    json.addObject();
    json.addObject();
    json.addObject();
    json.addObject();
    json.addObject();
    json.addObject();
    json.addObject();
    json.addObject();
    assert json.toString().equals("[{\"k1\":1},{},{},{},{},{},{},{},{}]") : json;
    json.addObject();
    assert json.toString().equals("[\n{\"k1\":1},\n{},\n{},\n{},\n{},\n{},\n{},\n{},\n{},\n{}\n]") : json;
  }

  void testObjectNewlineInsertion()
  {
    JsonObject json;
    json = new JsonObject(null);
    json.set("a", 1);
    json.set("b", 2);
    json.set("c", 3);
    json.set("d", 4);
    json.set("e", 5);
    assert json.toString().equals("{\"a\":1,\"b\":2,\"c\":3,\"d\":4,\"e\":5}") : json;
    json.set("f", 6);
    assert json.toString().equals("{\n\"a\":1,\n\"b\":2,\n\"c\":3,\n\"d\":4,\n\"e\":5,\n\"f\":6\n}") : json;

    json = new JsonObject(null);
    json.set("a", "b");
    JsonArray json0 = json.setArray("c");
    JsonArray json1 = json.setArray("d");
    json1.add(6);
    json1.add(7);
    json1.add(8);
    json1.add(9);
    json1.add(10);
    assert json.toString().equals("{\"a\":\"b\",\"c\":[],\"d\":[6,7,8,9,10]}") : json;
    json1.add(11);
    assert json.toString().equals("{\n\"a\":\"b\",\n\"c\":[],\n\"d\":[6,7,8,9,10,11]\n}") : json;
    json1.add(12);
    json1.add(13);
    json1.add(14);
    json1.add(15);
    json1.add(16);
    assert json.toString().equals("{\n\"a\":\"b\",\n\"c\":[],\n\"d\":[\n6,\n7,\n8,\n9,\n10,\n11,\n12,\n13,\n14,\n15,\n16\n]\n}") : json;
  }

  void testChangeNewlineThreshold()
  {
    JsonObject json;
    json = new JsonObject(null);
    JsonArray jsona = json.setArray("a");
    jsona.add(2);
    jsona.add(3);
    jsona.add(4);
    jsona.add(5);
    JsonArray jsonb = json.setArray("b");
    jsonb.add(7);
    jsonb.add(8);
    jsonb.add(9);
    jsonb.add(10);
    assert json.toString().equals("{\"a\":[2,3,4,5],\"b\":[7,8,9,10]}") : json;
    jsona.add(11);
    assert json.toString().equals("{\n\"a\":[2,3,4,5,11],\n\"b\":[7,8,9,10]\n}") : json;
    json.setNewlineThreshold(11);
    assert json.toString().equals("{\"a\":[2,3,4,5,11],\"b\":[7,8,9,10]}") : json;
    json.setNewlineThreshold(4);
    assert json.toString().equals("{\n\"a\":[\n2,\n3,\n4,\n5,\n11\n],\n\"b\":[7,8,9,10]\n}") : json;
  }

  void testAddAfterChangeNewlineThreshold()
  {
    JsonObject json;
    json = new JsonObject(null);
    json.setNewlineThreshold(3);
    JsonArray jsona = json.setArray("a");
    jsona.add(2);
    jsona.add(3);
    jsona.add(4);
    jsona.add(5);
    JsonObject jsonb = json.setObject("b");
    jsonb.set("c", 8);
    jsonb.set("d", 10);
    assert json.toString().equals("{\n\"a\":[\n2,\n3,\n4,\n5\n],\n\"b\":{\n\"c\":8,\n\"d\":10\n}\n}") : json;
  }
}

