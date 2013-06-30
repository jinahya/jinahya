package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   The {@link org.havi.ui.HImageHints HImageHints} object allows an
   application to pass hints to the system how best to tailor an image
   to match a (possibly) restricted {@link
   org.havi.ui.HGraphicsConfiguration HGraphicsConfiguration}.

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
<tr><td></td><td></td><td></td><td></td><td></td></tr>
  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
<tr>
<td>The image type.</td> 
<td>{@link org.havi.ui.HImageHints#NATURAL_IMAGE NATURAL_IMAGE}</td> 
<td>---</td> 
<td>---</td>
</tr>
  </table>

*/

public class HImageHints extends Object 
{
    /**
     * The image is a &quot;natural&quot; scene, with subtle gradations
     * of color, etc.  Suitable for dithering.
     */
    public static final int NATURAL_IMAGE = 0x01;
    
    /**
     * The image is a cartoon, with strong, well-defined, blocks of
     * solid color, etc.  Not suitable for dithering, suitable for
     * nearest color matching.
     */
    public static final int CARTOON = 0x02;
    
    /**
     * The image is business graphics, with strong, well-defined, blocks
     * of solid color, etc.  Not suitable for dithering, suitable for
     * nearest color matching.    
     */
    public static final int BUSINESS_GRAPHICS = 0x03;
    
    /**      
     * The image is a two-tone lineart, with colors varying between
     * foreground and background, etc. Not suitable for dithering.
     * Possibly suitable for color-map adjustment, etc., if
     * applicable.    
     */
    public static final int LINE_ART = 0x04;
    
    /**
     * Creates an HImageHints object. See the class description for
     * details of constructor parameters and default values.  
     */
    public HImageHints()
    {
    }
    
    /**
     * Set the expected type of the image being loaded.  
     * 
     * @param type the expected type of image 
     */
    public void setType(int type)
    {
    }
    
    /**    
     * Get the expected type of the image being loaded.    
     */
    public int getType()
    {
	return(0);
    }
}









