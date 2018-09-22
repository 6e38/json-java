/*
 * Copyright (c) 2018 Nathan Jenne
 */

package com.floorsix.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Simple test runner
 */
public abstract class AbstractTest
{

  /**
   * Runs all the tests in the derived class.
   *
   * User will need to extend this class, instantiate and call this function. Methods beginning with the
   * word {@code test} will be executed as test cases.
   *
   * <code>
   * public static void main(String[] args)
   * {
   *   (new YourExtendedClass()).run();
   * }
   * </code>
   */
  protected void run()
  {
    int testCount = 0;
    int failCount = 0;

    Class c = getClass();
    Method[] methods = c.getDeclaredMethods();

    for (Method m : methods)
    {
      if (m.getName().matches("^test.*"))
      {
        ++testCount;

        System.out.print("Running: " + m.getName() + " ");

        m.setAccessible(true);

        try
        {
          m.invoke(this);
          System.out.println("PASS");
        }
        catch (InvocationTargetException invocationTargetException)
        {
          ++failCount;

          System.out.println("FAIL");

          Throwable e = invocationTargetException.getCause() == null
            ? invocationTargetException
            : invocationTargetException.getCause();

          if (e instanceof AssertionError)
          {
            System.out.print("Assertion Error");
          }
          else
          {
            System.out.print("Test failure");
          }

          if (e.getMessage() != null)
          {
            System.out.print(": " + e.getMessage());
          }

          System.out.println();

          for (StackTraceElement trace : e.getStackTrace())
          {
            //if (!trace.toString().matches("^(sun\\.reflect|java\\.lang\\.reflect)\\..*"))
            System.out.println(">> " + trace);
          }

          System.out.println();
        }
        catch (IllegalAccessException e)
        {
          // This should never happen. The methods should be accessible if used correctly.
          ++failCount;

          System.out.println("FAIL");
          System.out.println("System error: " + e);
          System.out.println();
        }
      }
    }

    if (failCount == 0)
    {
      System.out.println("\nPASS: all tests succeeded (" + testCount + " tests)");
    }
    else
    {
      System.out.println("\nFAIL: " + failCount + " of " + testCount + " tests failed");
    }
  }
}

