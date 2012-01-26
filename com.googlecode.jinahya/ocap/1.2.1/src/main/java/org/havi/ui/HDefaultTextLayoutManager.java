package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Dimension;

/**
   The {@link org.havi.ui.HDefaultTextLayoutManager
   HDefaultTextLayoutManager} provides the default text rendering
   mechanism for the {@link org.havi.ui.HStaticText HStaticText}
   {@link org.havi.ui.HText HText} and {@link org.havi.ui.HTextButton
   HTextButton} classes.

   <p> 
   The {@link org.havi.ui.HDefaultTextLayoutManager
   HDefaultTextLayoutManager} handles alignment and justification of
   text in both horizontal and vertical directions as specified by the
   current alignment modes set on {@link org.havi.ui.HVisible
   HVisible}. It does not support scaling of text content, and the
   scaling mode of an associated {@link org.havi.ui.HVisible HVisible}
   is ignored.

   <p> 
   The string passed to the {@link
   org.havi.ui.HDefaultTextLayoutManager#render render} method may be
   multi-line, where each line is separated by a &quot;\n&quot;
   (0x0A). If the string does not fit in the space available, the
   string shall be truncated and an ellipsis (&quot;...&quot;)
   appended to indicate the truncation.

   <p>
   The {@link org.havi.ui.HDefaultTextLayoutManager
   HDefaultTextLayoutManager} should query the {@link
   org.havi.ui.HVisible HVisible} passed to its {@link
   org.havi.ui.HDefaultTextLayoutManager#render render} method to
   determine the basic font to render text in. If the specified font
   cannot be accessed the default behavior is to replace it with the
   nearest builtin font. Each missing character is replaced with an
   &quot;!&quot; character.  
   <p> 
   The antialiasing behavior of {@link
   org.havi.ui.HDefaultTextLayoutManager HDefaultTextLayoutManager} is
   platform dependent.

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr><td colspan=5>None.</td></tr>
  </table>
  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  </table>


  @see HTextLayoutManager
  @see HStaticText
  @see HText
  @see HTextButton 
  @see HTextLook
  @see HVisible 

*/

public class HDefaultTextLayoutManager
    implements HTextLayoutManager
{
    /**
     * Creates an {@link org.havi.ui.HDefaultTextLayoutManager
     * HDefaultTextLayoutManager} object. See the class description
     * for details of constructor parameters and default values.  
     */
    public HDefaultTextLayoutManager()
    {
    }

    /**
     * Returns the minimum size required to render the text content in any
     * possible interaction state of the specified {@link org.havi.ui.HVisible
     * HVisible} component. To achieve this, the maximum width and maximum
     * height of all minimum sizes are returned.
     */
     public Dimension getMinimumSize(HVisible hvisible)
     {
       return(null);
     }


    /**
     * Returns the maximum size required to render the text content in any
     * possible interaction state of the specified {@link org.havi.ui.HVisible
     * HVisible} component. To achieve this, the maximum width and maximum
     * height of all maximum sizes are returned. It is a valid
     * implementation option to return a dimension with a width and height
     * of Short.MAX_VALUE.
     */
     public Dimension getMaximumSize(HVisible hvisible)
     { 
       return(null);
     }


   /**
    * Returns the preferred size to render the text content in any
    * possible interaction state of the specified {@link org.havi.ui.HVisible
    * HVisible} component. To achieve this, the maximum width and maximum
    * height of all preferred sizes are returned.
    */
    public Dimension getPreferredSize(HVisible hvisible)
    {
       return(null);
    }

    /**
     * Render the string. The {@link org.havi.ui.HTextLayoutManager
     * HTextLayoutManager} should use the passed {@link
     * org.havi.ui.HVisible HVisible} object to determine any
     * additional information required to render the string,
     * e.g. <code>Font</code>, <code>Color</code> etc.  
     * <p>
     * The text should be laid out in the layout area, which is
     * defined by the bounds of the specified {@link
     * org.havi.ui.HVisible HVisible}, after subtracting the 
     * insets.  If the insets are <code>null</code> the full bounding
     * rectangle is used as the area to render text into.
     * <p> 
     * The {@link org.havi.ui.HTextLayoutManager
     * HTextLayoutManager} should not modify the clipping rectangle of
     * the <code>Graphics</code> object.
     * 
     * @param markedUpString the string to render.
     * @param g the graphics context, including a clipping rectangle
     * which encapsulates the area within which rendering is
     * permitted. If a valid insets value is passed to this method then
     * text must only be rendered into the bounds of the widget after
     * the insets are subtracted. If the insets value is <code>null</code>
     * then text is rendered into the entire bounding area of the {@link
     * org.havi.ui.HVisible HVisible}. It is implementation specific whether
     * or not the renderer takes into account the intersection of the
     * clipping rectangle in each case for optimization purposes.
     * @param v the {@link org.havi.ui.HVisible HVisible} into which
     * to render. 
     * @param insets the insets  to
     * determine the area in which to layout the text, or <code>null</code>.     
     */
    public void render(String markedUpString, java.awt.Graphics g, HVisible v, java.awt.Insets insets)
    {
        return;
    }

}


