package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   An {@link org.havi.ui.HMatteException HMatteException} is an
   exception that is thrown when a Component is unable to support the
   desired {@link org.havi.ui.HMatte HMatte} effect.

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

   @see HMatteLayer
 
*/

public class HMatteException 
    extends HUIException 
{
    /**
     * Creates an HMatteException object. See the class
     * description for details of constructor parameters and default
     * values.  
     */
    public HMatteException()
    {
    }

    /**
     * Creates an HMatteException object with a specified
     * reason string.
     *
     * @param message the reason why the exception was raised 
     */
    public HMatteException(String message)
    {
    }
} 










