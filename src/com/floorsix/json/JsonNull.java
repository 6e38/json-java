/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.io.IOException;
import java.io.OutputStream;

public class JsonNull extends Json
{
  public JsonNull(String key)
  {
    super(key);
  }

  @Override
  public void toJson(OutputStream out) throws IOException
  {
    keyToJson(out);

    out.write("null".getBytes());
  }
}

