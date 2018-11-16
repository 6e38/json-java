/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.util.ArrayList;

public abstract class JsonContainer extends Json
{
  protected ArrayList<Json> children;

  private int newlineThreshold;

  public JsonContainer(String key)
  {
    super(key);
    children = new ArrayList<Json>();
    newlineThreshold = 10;
  }

  public JsonContainer(String key, int newlineThreshold)
  {
    super(key);
    children = new ArrayList<Json>();
    this.newlineThreshold = newlineThreshold;
  }

  public void setNewlineThreshold(int threshold)
  {
    newlineThreshold = threshold;

    for (Json child : children)
    {
      if (child instanceof JsonContainer)
      {
        ((JsonContainer)child).setNewlineThreshold(threshold);
      }
    }
  }

  public void clear()
  {
    children.clear();
  }

  protected int getNewlineThreshold()
  {
    return newlineThreshold;
  }

  abstract int countItems();
}

