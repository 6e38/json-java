/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.json;

import java.util.ArrayList;

public abstract class JsonContainer extends Json
{
  protected ArrayList<Json> children;

  private int newlineThreshold;
  private int precision;
  private boolean hasPrecision;

  public JsonContainer(String key)
  {
    super(key);
    children = new ArrayList<Json>();
    newlineThreshold = 10;
    hasPrecision = false;
    precision = 0;
  }

  public JsonContainer(String key, JsonContainer parent)
  {
    super(key);
    children = new ArrayList<Json>();
    newlineThreshold = parent.getNewlineThreshold();
    hasPrecision = parent.isNumberPrecisionSet();
    precision = parent.getNumberPrecision();
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

  public void setNumberPrecision(int precision)
  {
    this.precision = precision;
    hasPrecision = true;

    for (Json child : children)
    {
      if (child instanceof JsonContainer)
      {
        ((JsonContainer)child).setNumberPrecision(precision);
      }
      else if (child instanceof JsonNumber)
      {
        ((JsonNumber)child).setNumberPrecision(precision);
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

  protected int getNumberPrecision()
  {
    return precision;
  }

  protected boolean isNumberPrecisionSet()
  {
    return hasPrecision;
  }

  abstract int countItems();
}

