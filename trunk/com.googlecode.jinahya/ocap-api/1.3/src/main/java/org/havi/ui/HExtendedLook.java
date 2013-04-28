package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Dimension;

/**
   The {@link org.havi.ui.HExtendedLook} interface is derived
   from the {@link org.havi.ui.HLook} interface and abstracts
   out the drawing of the look into separate background, border
   and visible data components. The interface allows the programmer to
   derive new looks from the default looks and have control
   over which component parts are drawn. This aids interoperability
   between platforms since no two manufacturer's <code>HLook</code>
   implementation look the same.
 
   @see org.havi.ui.HVisible#setLook
   @see org.havi.ui.HVisible#setLookData
   @see org.havi.ui.HVisible#paint
   @see org.havi.ui.HVisible#setBackgroundMode
   @see org.havi.ui.HVisible#setHorizontalAlignment
   @see org.havi.ui.HVisible#setVerticalAlignment
   @see org.havi.ui.HVisible#setResizeMode
   @see org.havi.ui.HTextLook
   @see org.havi.ui.HGraphicLook
   @see org.havi.ui.HAnimateLook
   @see org.havi.ui.HRangeLook
   @see org.havi.ui.HSinglelineEntryLook
   @see org.havi.ui.HMultilineEntryLook

   @since MHP 1.0.3/1.1.1
*/

public interface HExtendedLook extends HLook
{
    /**
     * The {@link org.havi.ui.HExtendedLook#fillBackground} method
     * paints the component with its current background <code>Color</code>
     * according to the {@link org.havi.ui.HVisible#setBackgroundMode}
     * method of {@link org.havi.ui.HVisible}.
     * <p> 
     * This method is only called from <code>showLook</code> within this
     * <code>HExtendedLook</code> implementation. It's not the intention to call
     * this method directly from outside of the <code>HExtendedLook</code>.
     * <p> 
     * Regardless of the background mode, it is an implementation option for
     * this method to render added decorations which may affect the look's
     * transparency. This method shall not be used to render any HVisible related
     * data or content associated with the HVisible. It is purely for background
     * and decoration only.
     * <p>
     * The <code>fillBackground</code> method should not modify the clipRect
     * (clipping rectangle) of the <code>Graphics</code> object that is passed
     * to it in a way which includes any area not part of that original
     * clipRect. If any modifications are made, the original clipRect shall be 
     * restored.
     * <p> 
     * @param g            the graphics context.
     * @param visible      the visible.
     * @param state the state parameter indicates the state of the visible
     * @see HLook#isOpaque
     * @since MHP 1.0.3/1.1.1
     */
    public void fillBackground(java.awt.Graphics g, HVisible visible, int state);

    /**
     * The {@link org.havi.ui.HExtendedLook#renderBorders} method
     * paints any implementation specific borders according to
     * the {@link org.havi.ui.HVisible#setBordersEnabled} method of
     * {@link org.havi.ui.HVisible}. If borders are drawn, the border
     * decoration shall be rendered within the border area as returned by
     * <code>getInsets</code>. The behavior of this method, when a subclass
     * overrides the method <code>getInsets</code> is undefined, except
     * that it will never draw outside the border area as returned by
     * <code>getInsets</code>.
     * <p> 
     * This method is only called from <code>showLook</code> within this
     * <code>HExtendedLook</code> implementation. It's not the intention to call
     * this method directly from outside of the <code>HExtendedLook</code>.
     * <p> 
     * The {@link org.havi.ui.HExtendedLook#renderBorders}
     * method should not modify the clipRect (clipping rectangle) of the
     * <code>Graphics</code> object that is passed to it in a way
     * which includes any area not part of that original clipRect. If any
     * modifications are made, the original clipRect shall be restored.
     * <p> 
     * @param g            the graphics context.
     * @param visible      the visible.
     * @param state the state parameter indicates the state of the visible
     * @since MHP 1.0.3/1.1.1
     */
    public void renderBorders(java.awt.Graphics g, HVisible visible, int state);

    /**
     * The {@link org.havi.ui.HExtendedLook#renderVisible} method
     * paints any content or other data associated with the look's HVisible. This
     * method shall not be used to render a background nor any other decoration.
     * Its purpose is purely to render content or other value data stored on
     * the HVisible.
     * This may be set via HVisible methods such as <code>setTextContent</code>
     * and <code>setGraphicContent</code>. Rendering shall take place within the
     * bounds returned by the <code>getInsets</code> method.
     * <p> 
     * This method is only called from <code>showLook</code> within this
     * <code>HExtendedLook</code> implementation. It's not the intention to call
     * this method directly from outside of the <code>HExtendedLook</code>.
     * <p> 
     * For looks which draw content (e.g.  {@link org.havi.ui.HTextLook}, {@link
     * org.havi.ui.HGraphicLook} and {@link org.havi.ui.HAnimateLook}),
     * if no content is associated with the component, this method does nothing.
     * <p> 
     * The {@link org.havi.ui.HExtendedLook#renderVisible}
     * method should not modify the clipRect (clipping rectangle) of the
     * <code>Graphics</code> object that is passed to it in a way
     * which includes any area not part of that original clipRect. If any
     * modifications are made, the original clipRect shall be restored.
     * <p> 
     * @param g            the graphics context.
     * @param visible      the visible.
     * @param state the state parameter indicates the state of the visible
     * @since MHP 1.0.3/1.1.1
     */
    public void renderVisible(java.awt.Graphics g, HVisible visible, int state);
    
    /**
     * The {@link org.havi.ui.HExtendedLook#showLook showLook} method is
     * the entry point for repainting the entire {@link
     * org.havi.ui.HVisible} component. This method delegates the
     * responsibility of drawing the component background, borders and any
     * <code>HVisible</code> related data or content to the
     * <code>fillBackground</code>,
     * <code>renderVisible</code> and <code>renderBorders</code> methods
     * respectively, subject to the clipping rectangle of the Graphics object
     * passed to it. This method shall call the methods
     * <code>fillBackground</code>,
     * <code>renderVisible</code>, and <code>renderBorders</code> in that order
     * and shall not do any rendering itself.
     * <p>
     * The {@link org.havi.ui.HExtendedLook#showLook showLook} method should
     * not modify the clipRect (clipping rectangle) of the
     * <code>Graphics</code> object that is passed to it in a way
     * which includes any area not part of that original clipRect. If any
     * modifications are made, the original clipRect shall be restored.
     * <p> 
     * Any resources <b>explicitly</b> associated with an {@link
     * org.havi.ui.HExtendedLook} should be loaded by the {@link
     * org.havi.ui.HExtendedLook} during its creation, etc. 
     * Note that the &quot;standard&quot; looks don't load content by default. 
     * <p>
     * This method is called from the {@link
     * org.havi.ui.HVisible#paint} method of {@link
     * org.havi.ui.HVisible} and must never be called from
     * elsewhere. Components wishing to redraw themselves should call
     * their repaint method in the usual way.
     *
     * @param g            the graphics context.
     * @param visible      the visible.
     * @param state the state parameter indicates the state of the
     * visible, allowing the look to render the appropriate content
     * for that state. Note that some components (e.g. HStaticRange,
     * HRange, HRangeValue) do not use state-based content. 
     */
    public void showLook(java.awt.Graphics g, HVisible visible, int state);
}

