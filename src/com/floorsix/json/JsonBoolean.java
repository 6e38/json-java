/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.io.IOException;
import java.io.OutputStream;

public class JsonBoolean extends Json
{
  private boolean bool;

  public JsonBoolean(String key, boolean bool)
  {
    super(key);
    set(bool);
  }

  @Override
  public void toJson(OutputStream out) throws IOException
  {
    keyToJson(out);

    out.write(String.valueOf(bool).getBytes());
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

