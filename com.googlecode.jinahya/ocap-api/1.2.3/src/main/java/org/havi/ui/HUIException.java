package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**   
   {@link org.havi.ui.HUIException HUIException} is a generic
   exception that indicates that the desired user-interface mechanism
   cannot be performed for some reason.

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr><td>message</td><td>Message explaining why the exception was thrown</td>
  <td>null</td><td>-</td><td>java.lang.Throwable#getMessage</td></tr>
  </table>
  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr><td colspan=4>None.</td></tr>
  </table>

*/

public class HUIException 
    extends java.lang.Exception
{
    /**
     * Creates an HUIException object. See the class
     * description for details of constructor parameters and default
     * values.  
     */
    public HUIException()
    {
    }

    /**
     * Creates an HUIException object with a specified
     * reason string.
     *
     * @param message the reason why the exception was raised 
     */
    public HUIException(String message)
    {
    }
}

