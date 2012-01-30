package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Graphics;

/**
   The {@link org.havi.ui.HTextLayoutManager HTextLayoutManager} class
   manages the layout and rendering on-screen of a
   &quot;marked-up&quot; string.
   <p>
   Possible implementations of {@link org.havi.ui.HTextLayoutManager
   HTextLayoutManager} could enable the following behaviors:
   <ul>
   <li> Interpreting basic markup, such as changing color or font, and
   forced line breaks.
   <li> Providing text alignment, as in the {@link
   org.havi.ui.HDefaultTextLayoutManager HDefaultTextLayoutManager}.
   <li> Providing text wrapping policies, such as word-wrap.
   <li> Providing text orientations, such as right-to-left, or
   top-to-bottom rendering.
   <li> Providing specialized support for missing characters, or
   fonts.
   <li> Providing specific language support.
   <li> Additional text styles, such as drop capitals or
   &quot;shadow&quot; characters.
   </ul>
   <p>

   HTextLayoutManager supports passing a java.awt.Insets object as 
   argument to the {@link
   org.havi.ui.HTextLayoutManager#render render} method
   to restrict the area in which text may be rendered. If the insets
   are zero, the text is rendered into the area defined by the bounds
   of the {@link org.havi.ui.HVisible HVisible} passed to the {@link
   org.havi.ui.HTextLayoutManager#render render} method.

   <p> 
   The clipping rectangle of the <code>Graphics</code> object passed
   to the {@link org.havi.ui.HTextLayoutManager#render render} method
   is <em>not</em> used to determine the area in which text is
   rendered, as its size and position is not guaranteed to cover the
   entire {@link org.havi.ui.HVisible HVisible} - for example, when
   partial repainting of an {@link org.havi.ui.HVisible HVisible} is
   performed. The diagram below shows a possible scenario:

   <p><table border>
   <tr><td><img src="../../../images/HTextLayoutManager1.gif"></td></tr>
   </table><p>

   The gray area shows the bounds of the {@link org.havi.ui.HVisible
   HVisible}. The green area shows the clipping rectangle of the
   <code>Graphics</code> context, and the dashed lines show the insets
   passed to the {@link org.havi.ui.HTextLayoutManager#render render} 
   method. The text is laid out into the rectangle
   defined by the {@link org.havi.ui.HVisible HVisible} bounds after
   subtracting the insets. However, only the part of the text covered
   by the clipping rectangle is actually drawn to the screen, as shown
   in the diagram below:

   <p><table border>
   <tr><td><img src="../../../images/HTextLayoutManager2.gif"></td></tr>
   </table><p>

   <p>
   The behavior of the render method when the text to be rendered
   does not fit in the current area specified is
   implementation-specific.


   @see org.havi.ui.HDefaultTextLayoutManager 

*/

public interface HTextLayoutManager
{
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
    public void render(String markedUpString, java.awt.Graphics g, HVisible v, java.awt.Insets insets);

}









