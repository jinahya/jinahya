package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
   {@link org.havi.ui.HScreenPoint HScreenPoint} denotes a screen
   location expressed as a relative value of the screen
   dimensions. Note that since these are relative dimensions they are
   effectively independent of any particular screen's physical
   dimensions, or aspect ratio.

   <p>   
   The x coordinate is in terms of the ratio of the particular
   horizontal screen location to the entire screen width.

   <p>   
   The y coordinate is in terms of the ratio of the particular
   vertical screen location to the entire screen width.

   <p>  
   All measurements should be taken from the top, left corner of the
   screen, measuring positive dimensions down and to the right.

   <p>   
   Note that x and y coordinates are not constrained - they may be
   negative, or have values greater than one - and hence, may denote
   locations that are not &quot;on- screen&quot;.

   <p>   
   Hence,
   <ul>
   <li> (0.0, 0.0) denotes the top, left hand corner of the screen.
   <li> (1.0, 0.0) denotes the top, right hand corner of the screen.
   <li> (0.5, 0.5) denotes the center (middle) of the screen.
   <li> (0.0, 1.0) denotes the bottom, left hand corner of the screen.
   <li> (1.0, 1.0) denotes the bottom, right hand corner of the screen.
   </ul>
   
   Note that in practice, particularly in the case of television, the
   precise location may vary slightly due to effects of overscan, etc.
   
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
  <td>The horizontal position of the point</td>
  <td>no default constructor exists</td>
  <td>{@link org.havi.ui.HScreenPoint#setLocation setLocation}</td>
  <td>---</td>
</tr>

<tr>
  <td>y</td>
  <td>The vertical position of the point</td>
  <td>no default constructor exists</td>
  <td>{@link org.havi.ui.HScreenPoint#setLocation setLocation}</td>
  <td>---</td>
</tr>
  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr><td colspan=4>None.</td></tr>
  </table>

 @see HScreenRectangle

 */

public class HScreenPoint
    extends Object
{    
    public float x;
    public float y;

    /**
     * Creates an {@link org.havi.ui.HScreenPoint HScreenPoint}
     * object.  See the class description for details of constructor
     * parameters and default values.  
     */
    public HScreenPoint(float x, float y)
    {
    }

    /**
     * Set the location of the HScreenPoint.
     *
     * @param x the horizontal position of the point
     * @param y the vertical position of the point
     */
    public void setLocation(float x, float y)
    {
    }
}

