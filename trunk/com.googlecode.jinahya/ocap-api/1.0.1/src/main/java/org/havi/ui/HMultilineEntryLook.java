package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Dimension;
import java.awt.Graphics;


/**
   The {@link org.havi.ui.HMultilineEntryLook HMultilineEntryLook}
   class is used by the {@link org.havi.ui.HMultilineEntry
   HMultilineEntry} component to display the entering of text.  This
   look will be provided by the platform and the exact way in which it
   is rendered will be platform dependent. 

   <p>
   The {@link org.havi.ui.HMultilineEntryLook HMultilineEntryLook} class draws the content set on an {@link
   org.havi.ui.HMultilineEntry HMultilineEntry}. It uses the {@link
   org.havi.ui.HSinglelineEntry#getTextContent getTextContent(int state)}
   method to determine the content to render. The interaction state of
   the {@link org.havi.ui.HMultilineEntry HMultilineEntry} is
   ignored.

   <p>   
   This is the default look that is used by {@link
   org.havi.ui.HMultilineEntry HMultilineEntry} and its subclasses.
  
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
  <tr><td colspan=4>None.</td></tr>

  </table>

  @see org.havi.ui.HMultilineEntry

*/

public class HMultilineEntryLook 
    extends HSinglelineEntryLook
{
    /**
     * Creates a {@link org.havi.ui.HMultilineEntryLook HMultilineEntryLook} object. See the class description for
     * details of constructor parameters and default values.  
     */ 
    public HMultilineEntryLook()
    {
    }

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
    public void fillBackground(java.awt.Graphics g, HVisible visible, int state)
    {
        return;
    }

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
    public void renderBorders(java.awt.Graphics g, HVisible visible, int state)
    {
        return;
    }

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
    public void renderVisible(java.awt.Graphics g, HVisible visible, int state)
    {
        return;
    }
    
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
    public void showLook(java.awt.Graphics g, HVisible visible, int state)
    {
        return;
    }
     /**
      * Called by the {@link org.havi.ui.HVisible HVisible} whenever
      * its content, state, or any other data changes. See the class
      * description of {@link org.havi.ui.HVisible HVisible} for more
      * information about the <code>changes</code> parameter.
      * <p>
      * The implementation of this method should work out which
      * graphical areas of the {@link org.havi.ui.HVisible HVisible}
      * have changed and make any relevant calls to trigger the
      * repainting of those areas.
      * <p>
      * A minimum implementation of this method could simply call
      * <pre>
      * visible.repaint()
      * </pre>
      * 
      * @param visible the {@link org.havi.ui.HVisible HVisible} which
      * has changed
      * @param changes an array containing hint data and associated hint
      * objects. If this argument is <code>null</code> a full repaint
      * will be triggered.  
      */
   public void widgetChanged (HVisible visible, HChangeData[] changes)
    {
        return;
    }
    
    

    /**
     * Gets the minimum size of the {@link org.havi.ui.HVisible
     * HVisible} component when drawn with this {@link
     * org.havi.ui.HLook HLook}. 
     * <p>
     * This size may be determined in several ways depending on the
     * information available to the look. These steps are performed in
     * order and the first available result is returned. For the
     * purposes of this algorithm {@link org.havi.ui.HLook HLook}
     * classes that do not use content (e.g. {@link
     * org.havi.ui.HRangeLook HRangeLook}) are treated as if no
     * content was present.
     * <p>
     * The extra space required for border decoration can be determined from 
     * the {@link org.havi.ui.HLook#getInsets getInsets} method.
     * <p><ol>
     * 
     * <li> If this look is an {@link org.havi.ui.HTextLook HTextLook} and 
     * if {@link org.havi.ui.HVisible#getTextLayoutManager
     * HVisible.getTextLayoutManager()} returns an {@link
     * org.havi.ui.HDefaultTextLayoutManager HDefaultTextLayoutManager}, then
     * this method should delegate the call to its {@link
     * org.havi.ui.HDefaultTextLayoutManager#getMinimumSize getMinimumSize()}
     * method plus any additional dimensions that the HLook requires
     * for border decoration etc. If the HDefaultTextLayoutManager
     * returns a zero size, then proceed with the following steps.
     * <li> If the HLook supports the scaling of its content (e.g.
     * an HGraphicLook) and scaling is requested and content is set,
     * then the return value is a size containing the width of the
     * narrowest content and the height of the shortest content plus
     * any additional dimensions that the HLook requires for border
     * decoration etc.
     * <li> If the {@link org.havi.ui.HLook HLook} does not support
     * scaling of content or no scaling is requested, <em>and</em>
     * content is set then the return value is a size sufficiently
     * large to hold each
     * piece of content plus any additional dimensions that the HLook
     * requires for border decoration etc.
     * <li> If no content is available but a default preferred size
     * has been set using {@link org.havi.ui.HVisible#setDefaultSize
     * setDefaultSize} has been called to set then the return value is
     * this value (as obtained with {@link
     * org.havi.ui.HVisible#getDefaultSize getDefaultSize}) plus any
     * additional dimensions that the HLook requires for border
     * decoration etc.
     * <li> If there is no content or default size set then the
     * return value is an implementation-specific minimum size plus
     * any additional dimensions that the HLook requires for border
     * decoration etc.
     * </ol>
     * 
     * @param hvisible {@link org.havi.ui.HVisible HVisible} to which
     * this {@link org.havi.ui.HLook HLook} is attached.
     * @return A dimension object indicating this {@link
     * org.havi.ui.HLook HLook's} minimum size.
     * @see org.havi.ui.HVisible#getMinimumSize 
     */
    public Dimension getMinimumSize(HVisible hvisible)
    {
        return(null);
    }

    /**
     * Gets the preferred size of the {@link org.havi.ui.HVisible
     * HVisible} component when drawn with this {@link
     * org.havi.ui.HLook HLook}. 
     * <p>
     * This size may be determined in several ways depending on the
     * information available to the look. These steps are performed in
     * order and the first available result is returned. For the
     * purposes of this algorithm {@link org.havi.ui.HLook HLook}
     * classes that do not use content (e.g. {@link
     * org.havi.ui.HRangeLook HRangeLook}) are treated as if no
     * content was present.
     * <p>
     * The extra space required for border decoration can be determined from 
     * the {@link org.havi.ui.HLook#getInsets getInsets} method.
     * <p>
     * <ol>
     * <li> If a default preferred size has been set for this {@link
     * org.havi.ui.HVisible HVisible} (using {@link
     * org.havi.ui.HVisible#setDefaultSize setDefaultSize}) then the
     * return value is this size (obtained with {@link
     * org.havi.ui.HVisible#getDefaultSize getDefaultSize}) plus any
     * additional dimensions that the HLook requires for border
     * decoration etc.
     * <li> If this look is an {@link org.havi.ui.HTextLook
     * HTextLook} and if a default preferred size has not been set and {@link
     * org.havi.ui.HVisible#getTextLayoutManager
     * HVisible.getTextLayoutManager()} returns an {@link
     * org.havi.ui.HDefaultTextLayoutManager HDefaultTextLayoutManager}, then
     * this method should delegate the call to its {@link
     * org.havi.ui.HDefaultTextLayoutManager#getPreferredSize  
     * getPreferredSize()} method plus any additional dimensions that the HLook 
     * requires for border decoration etc. If the HDefaultTextLayoutManager returns 
     * a zero size, then proceed with the following steps.
     * <li> If this {@link org.havi.ui.HLook HLook} does not support
     * scaling of content or no scaling is requested, and content is
     * present then the return value is a size that is sufficiently large
     * to hold each piece of content plus any additional dimensions
     * that the HLook requires for border decoration etc.
     * <li> If this {@link org.havi.ui.HLook HLook} supports the
     * scaling of its content (e.g. an {@link org.havi.ui.HGraphicLook
     * HGraphicLook}) and content is set then the return value is the
     * current size of the {@link org.havi.ui.HVisible HVisible} as
     * returned by {@link org.havi.ui.HVisible#getSize getSize}).
     * <li>If there is no content and no default size set then the
     * return value is the current size of the {@link
     * org.havi.ui.HVisible HVisible} as returned by {@link
     * org.havi.ui.HVisible#getSize getSize}).
     * </ol>
     * <p>
     * If a default preferred size has been set for this
     * <code>HVisible</code> (using <code>setDefaultSize()</code>) and
     * the default preferred size has a <code>NO_DEFAULT_WIDTH</code>
     * then the return value is a <code>Dimension</code> with this
     * height (obtained with <code>getDefaultSize()</code>) and the
     * preferred width for the content plus any additional dimensions
     * that the <code>HLook</code> requires for border decoration etc.
     * <p>
     * If a default preferred size has been set for this
     * <code>HVisible</code> (using <code>setDefaultSize()</code>) and
     * the default preferred size has a <code>NO_DEFAULT_HEIGHT</code>
     * then the return value is a <code>Dimension</code> with this width
     * (obtained with <code>getDefaultSize()</code>) and the preferred
     * height for the content plus any additional dimensions that the
     * <code>HLook</code> requires for border decoration etc.
     * 
     * @param hvisible {@link org.havi.ui.HVisible HVisible} to which
     * this {@link org.havi.ui.HLook HLook} is attached.
     * @return A dimension object indicating the preferred size of the
     * {@link org.havi.ui.HVisible HVisible} when drawn with this
     * {@link org.havi.ui.HLook HLook}.
     * @see org.havi.ui.HVisible#getPreferredSize 
     * @see org.havi.ui.HVisible#setDefaultSize 
     */
    public Dimension getPreferredSize(HVisible hvisible)
    {
        return(null);
    }

    /**
     * Gets the maximum size of the {@link org.havi.ui.HVisible
     * HVisible} component when drawn with this {@link
     * org.havi.ui.HLook HLook}. 
     * <p>
     * This size may be determined in several ways depending on the
     * information available to the look. These steps are performed in
     * order and the first available result is returned. For the
     * purposes of this algorithm {@link org.havi.ui.HLook HLook}
     * classes that do not use content (e.g. {@link
     * org.havi.ui.HRangeLook HRangeLook}) are treated as if no
     * content was present.
     * <p>
     * The extra space required for border decoration can be determined from 
     * the {@link org.havi.ui.HLook#getInsets getInsets} method.
     * <p><ol>
     * <li> If this look is an {@link org.havi.ui.HTextLook HTextLook} and if 
     * {@link org.havi.ui.HVisible#getTextLayoutManager 
     * HVisible.getTextLayoutManager()} returns an {@link
     * org.havi.ui.HDefaultTextLayoutManager HDefaultTextLayoutManager}, then
     * this method should delegate the call to its {@link
     * org.havi.ui.HDefaultTextLayoutManager#getMaximumSize getMaximumSize()}
     * method plus any additional dimensions that the HLook requires for border 
     * decoration etc. If the HDefaultTextLayoutManager returns a zero size, then 
     * proceed with the following steps.
     * <li> If the {@link org.havi.ui.HLook HLook} supports the
     * scaling of its content (e.g. an {@link org.havi.ui.HGraphicLook
     * HGraphicLook}) then the return value is the current size of the {@link
     * org.havi.ui.HVisible HVisible} (as returned by {@link
     * org.havi.ui.HVisible#getSize HVisible#getSize}).
     * <li> If the {@link org.havi.ui.HLook HLook} does not support
     * scaling of content or no scaling is requested, and content is
     * set then the return value is a size sufficiently large to hold
     * each piece of
     * content plus any additional dimensions that the HLook requires
     * for border decoration etc.
     * <li> If there is no content set then a maximum size of
     * <code>[Short.MAX_VALUE, Short.MAX_VALUE]</code> is returned as
     * a Dimension.     
     * </ol>
     *
     * @param hvisible {@link org.havi.ui.HVisible HVisible} to which
     * this {@link org.havi.ui.HLook HLook} is attached.
     * @return A dimension object indicating this {@link
     * org.havi.ui.HLook HLook's} maximum size.
     * @see org.havi.ui.HVisible#getMaximumSize 
     */
    public Dimension getMaximumSize(HVisible hvisible)
    {
        return(null);
    }

    /** 
     * Returns true if the entire painted area of the {@link
     * org.havi.ui.HVisible HVisible} when using this look is fully opaque,
     * i.e. the {@link org.havi.ui.HLook#showLook showLook} method
     * guarantees that all pixels are painted in an opaque Color.
     * <p>
     * The default value is implementation specific and depends on the
     * background painting mode of the given {@link
     * org.havi.ui.HVisible HVisible}. The consequences of an invalid
     * overridden value are implementation specific.  
     * 
     * @param visible the visible to test
     * @return true if all the pixels with the
     * java.awt.Component#getBounds method of an {@link
     * org.havi.ui.HVisible HVisible} using this look are fully
     * opaque, i.e.  the {@link org.havi.ui.HLook#showLook showLook}
     * method guarantees that all pixels are painted in an opaque
     * Color, otherwise false.
     */
    public boolean isOpaque(HVisible visible)
    {
        return(false);
    }

    /**
     * Determines the insets of this {@link org.havi.ui.HLook HLook},
     * which indicate the size of the border. This area is
     * reserved for the {@link org.havi.ui.HLook HLook} to use for
     * drawing borders around the associated {@link
     * org.havi.ui.HVisible HVisible}.
     * 
     * @param hvisible {@link org.havi.ui.HVisible HVisible} to which
     * this {@link org.havi.ui.HLook HLook} is attached.
      * @return the insets of this {@link org.havi.ui.HLook HLook}.
     */
    public java.awt.Insets getInsets(HVisible hvisible)
    {
        return(null);
    }


    /** 
     * Returns the character position of the caret within the content
     * string if it were to be moved down one line.  
     * 
     * @param visible a multiline text entry component. <p>Note that if
     * this component is not actually using this {@link
     * org.havi.ui.HLook HLook} to display itself the return value of
     * this method will probably be wrong. Application authors should
     * take care to only call this method with components which are
     * using this instance of the {@link
     * org.havi.ui.HMultilineEntryLook HMultilineEntryLook}.
     * @return the caret position (as defined in {@link
     * org.havi.ui.HSinglelineEntry#getCaretCharPosition
     * HSinglelineEntry#getCaretCharPosition}) to move the caret to if
     * it were moved down one line, or the nearest position if this is not possible.  
     */
    public int getCaretPositionNextLine(HVisible visible)
    {
	return (0);
    }

    /** 
     * Returns the character position of the caret within the content
     * string if it were to be moved up one line.
     * 
     * @param visible a multiline text entry component. <p>Note that if
     * this component is not actually using this {@link
     * org.havi.ui.HLook HLook} to display itself the return value of
     * this method will probably be wrong. Application authors should
     * take care to only call this method with components which are
     * using this instance of the {@link
     * org.havi.ui.HMultilineEntryLook HMultilineEntryLook}.
     * @return the caret position (as defined in {@link
     * org.havi.ui.HSinglelineEntry#getCaretCharPosition
     * HSinglelineEntry#getCaretCharPosition}) to move the caret to if
     * it were moved up one line, or the nearest position if this is not possible.  
     */
    public int getCaretPositionPreviousLine(HVisible visible)
    {
	return (0);
    }
    
    
    /**
     * Returns the character position of the caret within the content
     * string if it were to be moved vertically to the given 'line'.
     * A line is identified by its first character as obtained from
     * the getSoftLineBreakPositions() method. If an invalid line is
     * specified an <code>IllegalArgumentException</code> is thrown. If the
     * caret cannot be moved to the same column position on this line, the
     * nearest position should be returned. 
     *
     * @param visible a multiline text entry component. <p>Note that if
     * this component is not actually using this {@link
     * org.havi.ui.HLook HLook} to display itself the return value of
     * this method will probably be wrong. Application authors should
     * take care to only call this method with components which are
     * using this instance of the {@link
     * org.havi.ui.HMultilineEntryLook HMultilineEntryLook}.
     * @param line the line number for which the caret position is requested
     * @return the caret position (as defined in {@link
     * org.havi.ui.HSinglelineEntry#getCaretCharPosition
     * HSinglelineEntry#getCaretCharPosition}) to move the caret to if
     * it were moved to this line.  
     *
     * @see HSinglelineEntry#getCaretCharPosition
     * @see HMultilineEntryLook#getSoftLineBreakPositions
     */
    public int getCaretCharPositionForLine(HVisible visible, int line)
    { return 0;
    }

   /**
    * Returns the starting positions of lines currently shown within the HVisible. 
    * Lines are identified by the positions of those characters that start on a new 'line' (including those
    * following \n), in order, from the first visible line to the last. This
    * method can be used to calculate the number of visible lines
    * currently shown in the look as well as the line shown at the top
    * of the visible window.
    * @param visible a multiline text entry component. 
    * @return the starting positions of the lines currently shown
    *  within the <code>HVisible</code>.
    *  If there is no text content within the <code>HVisible</code>, a
    *  zero length array shall be returned.
    */
   public int[] getVisibleSoftLineBreakPositions(HVisible visible)
   { return null;
   }    


   /**
    * Returns the positions within the content string of all those characters
    * that start on a new 'line' (including those following \n), in order 
    * from the first line to the last, including the line starting at 0. 
    * @param visible a multiline text entry component. <p>Note that if
    * this component is not actually using this {@link
    * org.havi.ui.HLook HLook} to display itself the return value of
    * this method will probably be wrong. Application authors should
    * take care to only call this method with components which are
    * using this instance of the {@link
    * org.havi.ui.HMultilineEntryLook HMultilineEntryLook}.
    * @return the positions of 'soft line breaks' introduced by the HLook.
    *  If there is no text content within the <code>HVisible</code>, a
    *  zero length array shall be returned.
    */
   public int[] getSoftLineBreakPositions(HVisible visible){ return null;
   }
    
}









