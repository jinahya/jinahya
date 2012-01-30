package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   {@link org.havi.ui.HScreenRectangle HScreenRectangle} denotes a
   screen area expressed as a relative value of the screen
   dimensions. Note that since these are relative dimensions they are
   effectively independent of any particular screen's physical
   dimensions, or aspect ratio.

   <p>
   Note that the x and y offset coordinates of the top, left corner of
   the area are not constrained - they may be negative, or have values
   greater than one - and hence, may denote an offset location that is
   not &quot;on-screen&quot;. The width and height of the area should
   be positive (including zero), but are otherwise unconstrained - and
   hence may denote areas greater in size than the entire screen.

   <p>
   
   Hence,
   <ul>
   <li>(0.0, 0.0, 1.0, 1.0) denotes the whole of the screen.
   <li>(0.0, 0.0, 0.5, 0.5) denotes the top, left hand quarter of the
       screen.
   <li>(0.5, 0.0, 0.5, 0.5) denotes the top, right hand quarter of the
       screen.
   <li>(0.25, 0.25, 0.5, 0.5) denotes a centered quarter-screen area of
       the screen.
   <li>(0.0, 0.5, 0.5, 0.5) denotes the bottom, left hand quarter of
       the screen.
   <li>(0.5, 0.5, 0.5, 0.5) denotes the bottom, right hand quarter of
       the screen.
   </ul>

   Note that in practice, particularly in the case of television, the
   precise location may vary slightly due to effects of overscan, etc.

   <p>   
   Note that systems using {@link org.havi.ui.HScreenRectangle
   HScreenRectangles} directly should consider the effects of rounding
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
  <td>x</td>
  <td>The horizontal position of the top left corner</td>
  <td>no default constructor exists</td>
  <td>{@link org.havi.ui.HScreenRectangle#setLocation setLocation}</td>
  <td>---</td>
</tr>

<tr>
  <td>y</td>
  <td>The vertical position of the top left corner</td>
  <td>no default constructor exists</td>
  <td>{@link org.havi.ui.HScreenRectangle#setLocation setLocation}</td>
  <td>---</td>
</tr>

<tr>
  <td>width</td>
  <td>The width of the rectangle</td>
  <td>no default constructor exists</td>
  <td>{@link org.havi.ui.HScreenRectangle#setSize setSize}</td>
  <td>---</td>
</tr>

<tr>
  <td>height</td>
  <td>The height of the rectangle</td>
  <td>no default constructor exists</td>
  <td>{@link org.havi.ui.HScreenRectangle#setSize setSize}</td>
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
public class HScreenRectangle
    extends Object
{
    public float x;
    public float y;
    public float width;
    public float height;

    /**
     * Creates an {@link org.havi.ui.HScreenRectangle
     * HScreenRectangle} object.  See the class description for
     * details of constructor parameters and default values.  
     */
    public HScreenRectangle(float x, float y, float width, float height)
    {
    }

    /**
     * Set the location of the top left corner of the
     * HScreenRectangle.
     *
     * @param x the horizontal position of the top left corner
     * @param y the vertical position of the top left corner
     */
    public void setLocation(float x, float y)
    {
    }

    /**
     * Set the size of the HScreenRectangle.
     *
     * @param width the width of the HScreenRectangle
     * @param height the height of the HScreenRectangle
     */
    public void setSize(float width, float height)
    {
    }
}

