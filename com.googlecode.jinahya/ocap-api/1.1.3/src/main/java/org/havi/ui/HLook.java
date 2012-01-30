package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Dimension;
import java.awt.Insets;
import java.util.Hashtable;

/**
   The {@link org.havi.ui.HLook HLook} interface defines the
   &quot;look&quot; of a component and may be regarded as a mechanism
   to allow a &quot;pluggable&quot; paint method to be attached to the
   component. Instead of having to subclass the entire
   component to change its look, it is possible to simply implement an
   {@link org.havi.ui.HLook HLook} that will render the component
   &quot;look&quot; and then associate this {@link org.havi.ui.HLook
   HLook} implementation with the component.  

   <h3>Borders</h3>

   An implementation of {@link org.havi.ui.HLook HLook} will also
   include code to draw implementation-specific borders. The
   application or component can query the reserved space for an {@link
   org.havi.ui.HLook HLook} with the {@link
   org.havi.ui.HLook#getInsets getInsets} method.

   <p>
   Since the border area is included in the overall size of the
   component, the border effectively constrains the area available for
   rendering content to the rectangle which has an upper-left corner
   location of <code>(insets.left, insets.top)</code>, and has a size
   of <code>width - (insets.left + insets.right)</code> by
   <code>height - (insets.top + insets.bottom)</code>.

   <h3>Invocation Mechanism</h3>

   The {@link org.havi.ui.HLook#showLook showLook} method of the
   {@link org.havi.ui.HLook HLook} interface will be called by the
   havi.ui framework in response to the paint method of the {@link
   org.havi.ui.HVisible HVisible} being called by the AWT lightweight
   component framework. Applications should simply invoke the
   component repaint method as in normal AWT, rather than calling the
   {@link org.havi.ui.HLook#showLook showLook} method directly.

   <h3>Content Rendering</h3>

   Some looks make use of content stored on an associated
   {@link org.havi.ui.HVisible HVisible}. These looks are:

   <p><ul>
   <li>{@link org.havi.ui.HAnimateLook HAnimateLook} - for animated
   graphical content
   <li>{@link org.havi.ui.HGraphicLook HGraphicLook} - for static
   graphical content
   <li>{@link org.havi.ui.HTextLook HTextLook} - for static textual
   content
   <li>{@link org.havi.ui.HSinglelineEntryLook HSinglelineEntryLook} -
   for single line text entry content
   <li>{@link org.havi.ui.HMultilineEntryLook HMultilineEntryLook} -
   for multiline text entry content 
   <li>{@link org.havi.ui.HListGroupLook HListGroupLook} -
   for list element content
   </ul>

   <p>  
   Some of these looks may support the scaling and alignment of their
   content as an implementation option. The scaling and alignment
   modes supported are specified in the class description of {@link
   org.havi.ui.HVisible HVisible}. The table below details which
   features the content-based platform looks should support:

   <p><table border>
   <tr><th>HLook</th><th>Scaling</th><th>Alignment</th></tr>
   <tr><td>HAnimateLook</td><td>Optional</td><td>Mandatory</td></tr>
   <tr><td>HGraphicLook</td><td>Optional</td><td>Mandatory</td></tr>
   <tr><td>HListGroupLook</td><td>Optional</td><td>Mandatory</td></tr>
   <tr><td>HTextLook</td><td>Not used</td><td>Mandatory</td></tr>
   <tr><td>HSinglelineEntryLook</td><td>Not used</td><td>Not used</td></tr>
   <tr><td>HMultilineEntryLook</td><td>Not used</td><td>Not used</td></tr>
   </table>

   <p>
   Where scaling support is optional all implementations must
   as a minimum support the {@link org.havi.ui.HVisible#RESIZE_NONE
   RESIZE_NONE} scaling mode. Platforms are
   <em>not</em> required to support scaling of textual content by default.
      
   <p>
   Looks should use the {@link
   org.havi.ui.HVisible#getHorizontalAlignment getHorizontalAlignment}
   and {@link org.havi.ui.HVisible#getVerticalAlignment
   getVerticalAlignment} methods to retrieve the current alignment
   modes, and the {@link org.havi.ui.HVisible#getResizeMode
   getResizeMode} method to determine the active scaling mode, where
   supported. However, note that HAVi platform looks which render text
   content using the {@link org.havi.ui.HDefaultTextLayoutManager
   HDefaultTextLayoutManager} class shall delegate the alignment of
   text content to the layout manager.

   <h3>Rendering Hints</h3>
   
   HLook provides a method, {@link org.havi.ui.HLook#widgetChanged
   widgetChanged} which can be called by an {@link
   org.havi.ui.HVisible HVisible} with one or more hints to inform the
   look that something has changed. This method provides information
   to the look about what has changed, which allows smarter repainting
   than having the HVisible simply call its <code>repaint</code>
   method. The hint constants are defined on {@link
   org.havi.ui.HVisible HVisible}. See the class definition for {@link
   org.havi.ui.HVisible HVisible} for more information.
   
   <h3>Private Data</h3>

   Implementations of {@link org.havi.ui.HLook HLook} may store
   private data on each instance of an {@link org.havi.ui.HVisible
   HVisible} to optimize the drawing of that component. However, this
   is an implementation option. Furthermore such data may be
   invalidated by another part of the system, for example if 
   {@link org.havi.ui.HVisible#setLook setLook}
   is called on {@link org.havi.ui.HVisible HVisible}. 
   Therefore if this mechanism is used by implementations
   of {@link org.havi.ui.HLook HLook} those implementations
   <em>must</em> be capable of regenerating such data on the fly,
   according to the current state of the {@link org.havi.ui.HVisible
   HVisible}.

   <h3>Platform Looks</h3>

   The HAVi UI provides a number of classes implementing the {@link
   org.havi.ui.HLook HLook} interface. Applications wishing to provide
   their own {@link org.havi.ui.HLook HLooks} may directly implement
   this interface or may subclass those provided by the platform.

   <h3>Default Behavior </h3>

   Unless already specified in a particular {@link org.havi.ui.HLook
   HLook}, implementations of {@link org.havi.ui.HLook HLook} should
   use:

   <ul>
   <li> the foreground color of each associated {@link org.havi.ui.HVisible 
   HVisible} (using the java.awt.Component method getForeground) to
   determine the Color to render both the content and border (as necessary) 
   <li> the background color of each associated {@link
   org.havi.ui.HVisible HVisible} (using the java.awt.Component method
   getBackground) to determine the Color used to render a rectangular
   area to fill the component and erase any previous content, as
   specified by the current background drawing mode for that component
   - see {@link org.havi.ui.HVisible#setBackgroundMode
   setBackgroundMode} on {@link org.havi.ui.HVisible HVisible}.

   <li> the current alignment mode of each associated {@link
   org.havi.ui.HVisible HVisible} (using {@link
   org.havi.ui.HVisible#getHorizontalAlignment getHorizontalAlignment}
   and {@link org.havi.ui.HVisible#getVerticalAlignment
   getVerticalAlignment}) to determine how to align content.

   <li> the current scaling mode of each associated {@link
   org.havi.ui.HVisible HVisible} (using to {@link
   org.havi.ui.HVisible#getResizeMode getResizeMode}) to determine how
   to scale content.
   </ul>
 
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
   
*/

public interface HLook extends java.lang.Cloneable
{
    /**
     * The {@link org.havi.ui.HLook#showLook showLook} method is
     * responsible for repainting the entire {@link
     * org.havi.ui.HVisible HVisible} component, (including any
     * content set on the component, and the component background), subject to
     * the clipping rectangle of the Graphics object passed to it.
     * <p>
     * The {@link org.havi.ui.HLook#showLook showLook} method should
     * not modify the clipRect (clipping rectangle) of the
     * <code>Graphics</code> object that is passed to it in a way
     * which includes any area not part of that original clipRect. If any
     * modifications are made, the original clipRect shall be restored.
     * <p> 
     * For looks which draw content (e.g.  {@link
     * org.havi.ui.HTextLook HTextLook}, {@link
     * org.havi.ui.HGraphicLook HGraphicLook} and {@link
     * org.havi.ui.HAnimateLook HAnimateLook}), if no content is
     * associated with the component, the {@link
     * org.havi.ui.HLook#showLook showLook} method paints the component
     * with its current background <code>Color</code> according to the {@link
     * org.havi.ui.HVisible#setBackgroundMode setBackgroundMode}
     * method of {@link org.havi.ui.HVisible HVisible} and draws any
     * (implementation-specific) borders. Note that by default the
     * background mode is set so as to <b>not</b> paint a
     * background. Furthermore on platforms which support transparent
     * colors the background <code>Color</code> may be partially or completely
     * transparent.
     * <p> 
     * Any resources <b>explicitly</b> associated with an {@link
     * org.havi.ui.HLook HLook} should be loaded by the {@link
     * org.havi.ui.HLook HLook} during its creation, etc. 
     * Note that the &quot;standard&quot; looks don't load content by default. 
     * <p>
     * This method is called from the {@link
     * org.havi.ui.HVisible#paint paint} method of {@link
     * org.havi.ui.HVisible HVisible} and must never be called from
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
   public void widgetChanged (HVisible visible, HChangeData[] changes);
    
    

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
    public Dimension getMinimumSize(HVisible hvisible);

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
    public Dimension getPreferredSize(HVisible hvisible);

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
    public Dimension getMaximumSize(HVisible hvisible);

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
    public boolean isOpaque(HVisible visible);

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
    public java.awt.Insets getInsets(HVisible hvisible);

}










