package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   Thrown when an application requests an {@link
   org.havi.ui.HScreenConfiguration HScreenConfiguration} that cannot
   be satisfied -- either because the {@link
   org.havi.ui.HScreenConfiguration HScreenConfiguration} does not
   have the functionality, or because the requested {@link
   org.havi.ui.HScreenConfiguration HScreenConfiguration} is otherwise
   invalid, e.g. it is an {@link org.havi.ui.HScreenConfiguration
   HScreenConfiguration} due to a different {@link
   org.havi.ui.HScreenDevice HScreenDevice} than the one it is being
   applied to.

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

public class HConfigurationException
    extends java.lang.Exception
{    
    /**
     * Creates an HConfigurationException object. See the class
     * description for details of constructor parameters and default
     * values.  
     */
     public HConfigurationException()
    {
    }

    /**
     * Creates an HConfigurationException object with a specified
     * reason string.
     *
     * @param message the reason why the exception was raised 
     */
    public HConfigurationException(String message)
    {
    }
}

