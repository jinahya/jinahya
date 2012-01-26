package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   The {@link org.havi.ui.HFlatMatte HFlatMatte} class represents a
   matte that is constant over space and time. It is specified as a
   floating point value in the range 0.0 to 1.0 where:
   <ul>
   <li>0.0 is fully transparent
   <li>values between 0.0 and 1.0 are partially transparent to the
   nearest supported transparency value.
   <li>1.0 is fully opaque
   </ul>

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
 <tr>
    <td>data</td>
    <td>The transparency value for this flat effect matte.</td>
    <td>1.0</td>
    <td>{@link org.havi.ui.HFlatMatte#setMatteData setMatteData}</td>
    <td>{@link org.havi.ui.HFlatMatte#getMatteData getMatteData}</td>
</tr>
  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr><td colspan=4>None.</td></tr>
  </table>

*/

public class HFlatMatte
    implements HMatte
{
    
    /**
     * Creates an {@link org.havi.ui.HFlatMatte HFlatMatte}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HFlatMatte()
    {
    }

    /**
     * Creates an {@link org.havi.ui.HFlatMatte HFlatMatte}
     * object. See the class description for details of constructor
     * parameters and default values.
     */
    public HFlatMatte(float data)
    {
    }

    /**
     * Sets the data for this matte. Any previously set data is replaced.
     *
     * @param data the data for this matte.
     */
    public void setMatteData(float data)
    {
    }

    /**
     * Returns the data used for this matte.
     *
     * @return the data used for this matte (a single number).
     */
    public float getMatteData()
    {
        return (1.0f);
    }
}

