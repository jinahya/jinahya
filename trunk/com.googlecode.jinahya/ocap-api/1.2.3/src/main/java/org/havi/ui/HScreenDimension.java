package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   {@link org.havi.ui.HScreenDimension HScreenDimension} denotes a
   screen dimension expressed as a relative value of the screen
   dimensions. Note that since these are relative dimensions they are
   effectively independent of any particular screen's physical
   dimensions, or aspect ratio.

   <p> 
   Note that the extents of the dimension must be positive
   (including zero), but are otherwise unconstrained - and hence may
   denote areas greater in size than the entire screen.

   <p>

   
   Hence,
   <ul>
   <li>(1.0, 1.0) denotes the size of the entire screen.
   <li>(0.5, 0.5) denotes a quarter of the screen.
   </ul>

   Note that in practice, particularly in the case of television, the
   precise dimension may vary slightly due to effects of overscan, etc.

   <p>   
   Note that systems using {@link org.havi.ui.HScreenDimension
   HScreenDimensions} directly should consider the effects of rounding
   errors, etc.

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
  <td>width</td>
  <td>The horizontal extent of the dimension</td>
  <td>no default constructor exists</td>
  <td>{@link org.havi.ui.HScreenDimension#setSize setSize}</td>
  <td>---</td>
</tr>

<tr>
  <td>height</td>
  <td>The vertical extent of the dimension</td>
  <td>no default constructor exists</td>
  <td>{@link org.havi.ui.HScreenDimension#setSize setSize}</td>
  <td>---</td>
</tr>
  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr><td colspan=4>None.</td></tr>
  </table>

 @see HScreenPoint

*/
public class HScreenDimension
    extends Object
{
    public float width;
    public float height;

    /**
     * Creates an {@link org.havi.ui.HScreenDimension
     * HScreenDimension} object.  See the class description for
     * details of constructor parameters and default values.  
     */
    public HScreenDimension(float width, float height)
    {
    }

    /**
     * Set the extents of the HScreenDimension.
     *
     * @param width the horizontal extent of the HScreenDimension
     * @param height the vertical extent of the HScreenDimension
     */
    public void setSize(float width, float height)
    {
    }
}

