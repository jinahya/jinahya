package org.havi.ui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
   The {@link org.havi.ui.HListGroupLook HListGroupLook} class is used by the {@link
   org.havi.ui.HListGroup HListGroup} component to display both the
   {@link org.havi.ui.HListGroup HListGroup} itself (potentially
   including a scrollbar component) and graphical or textual list
   content held on the {@link org.havi.ui.HListGroup HListGroup}. This
   look will be provided by the platform and the exact way in which it
   is rendered will be platform dependent.

   <p> 
   The {@link org.havi.ui.HListGroupLook HListGroupLook} class draws the HListGroup
   and any look-specific borders around the component, and then
   renders the content set on the {@link org.havi.ui.HListGroup
   HListGroup}. It uses the {@link
   org.havi.ui.HListGroup#getListContent getListContent} method to
   determine the content to render. The content of the HListGroup does
   not depend on the interaction state.

   <p> 
   {@link org.havi.ui.HListGroupLook HListGroupLook} should use the 
   following properties of {@link org.havi.ui.HListGroup HListGroup}
   to lay out and render the {@link org.havi.ui.HListElement
   HListElement} content:
   
   <p><table border>   
   <tr><th>Item</th><th>Method</th><th>Purpose</th></tr>
   <tr><td>Orientation</td><td>{@link
   org.havi.ui.HListGroup#getOrientation
   getOrientation}</td><td>direction to lay out elements</td></tr>
   <tr><td>Content</td><td>{@link
   org.havi.ui.HListGroup#getListContent
   getListContent}</td><td>elements to display</td></tr>
   <tr><td>Scroll position</td><td>{@link
   org.havi.ui.HListGroup#getScrollPosition
   getScrollPosition}</td><td>first element to draw</td></tr>
   <tr><td>Selection</td><td>{@link
   org.havi.ui.HListGroup#isItemSelected isItemSelected}</td><td>mark
   an element as selected</td></tr>
   <tr><td>Current item</td><td>{@link
   org.havi.ui.HListGroup#getCurrentItem
   getCurrentItem}</td><td>highlight an element</td></tr>
   </table><p>

   {@link org.havi.ui.HListGroupLook HListGroupLook} 
   should draw a scrollbar as necessary when there are more {@link
   org.havi.ui.HListElement HListElements} than can be displayed. It
   is an implementation option to leave border space between each
   element. The insets used for the element borders can be retrieved
   using {@link org.havi.ui.HListGroupLook#getElementInsets
   getElementInsets}

   <p>
   Implementations of {@link org.havi.ui.HListGroupLook HListGroupLook} 
   should use the appropriate methods on {@link org.havi.ui.HListGroup
   HListGroup} to determine which scaling and alignment modes to use
   when rendering content.  See the class description for {@link
   org.havi.ui.HLook HLook} for more details. 
   <p>
   {@link org.havi.ui.HListGroupLook HListGroupLook} may support scalable graphical
   content. As a minimum, all implementations must support the {@link
   org.havi.ui.HVisible#RESIZE_NONE RESIZE_NONE} scaling mode for
   graphical content, and all alignment modes for text content.
   However, Note that {@link org.havi.ui.HListGroupLook HListGroupLook} behaves
   slightly differently from other HAVi {@link org.havi.ui.HLook
   HLook} classes, as follows.
   <p><ul>
   <li> Where supported, scaling applies to the icon (graphical
   content) of each {@link org.havi.ui.HListElement HListElement},
   based on the area allocated to that {@link org.havi.ui.HListElement
   HListElement} rather than the entire area of the {@link
   org.havi.ui.HListGroup HListGroup}.
   <li> Alignment mode applies to the content of the {@link
   org.havi.ui.HListElement HListElement} within the area allocated to
   that {@link org.havi.ui.HListElement HListElement} rather than the
   entire area of the {@link org.havi.ui.HListGroup HListGroup}.
   </ul><p> 
   Note that the results of applying the {@link
   org.havi.ui.HVisible#VALIGN_JUSTIFY VALIGN_JUSTIFY} and {@link
   org.havi.ui.HVisible#HALIGN_JUSTIFY HALIGN_JUSTIFY} alignment modes
   to graphical content are defined to identical to {@link
   org.havi.ui.HVisible#VALIGN_CENTER VALIGN_CENTER} and {@link
   org.havi.ui.HVisible#HALIGN_CENTER HALIGN_CENTER} modes
   respectively, as justification is meaningless in this context.

   <p>   
   This is the default look that is used by {@link
   org.havi.ui.HListGroup HListGroup}.

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
   <tr>
      <td>Element insets</td>
      <td>null</td>
      <td>{@link org.havi.ui.HListGroupLook#getElementInsets
      getElementInsets}</td>
      <td>---</td>
   </tr>
  </table>

   @see org.havi.ui.HListGroup
   @see org.havi.ui.HListElement
   @see org.havi.ui.HVisible
   @see org.havi.ui.HLook
   @see org.havi.ui.HDefaultTextLayoutManager
*/

public class HListGroupLook
    implements HExtendedLook, HAdjustableLook
{
    /**
     * Creates a {@link org.havi.ui.HListGroupLook HListGroupLook} object. See the
     * class description for details of constructor parameters and
     * default values.  
     */
    public HListGroupLook()
    {
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
     * The labels of the associated <code>HListElement</code>s shall
     * be rendered by using the current text layout manager of the
     * <code>HListGroup</code>. For each visible label is the
     * <code>render()</code> method of <code>HTextLayoutManager</code> 
     * called. The position and size per label are specified as insets
     * relatively to the bounds of <code>HListGroup</code>. Note that
     * the bounds are independent of any borders of the
     * <code>HListGroup</code>, but the insets have to include the
     * borders per element, if any. The look shall use the method
     * <code>getLabelSize()</code> of <code>HListGroup</code> to
     * determine the size for each label. If the returned dimension
     * object has <code>DEFAULT_LABEL_WIDTH</code> for the width and/or
     * <code>DEFAULT_LABEL_HEIGHT</code> for the height as values, then
     * this method shall use implementation specific value(s) as default(s)
     * for the missing dimension(s) instead. If
     * <code>getTextLayoutManager()</code> returns <code>null</code>,
     * then labels shall not be rendered.
     * <p>
     * If supported, scaling of icons shall reflect the resize mode of the
     * visible within the area of the respective list element. The look
     * shall use the method <code>getIconSize()</code> of
     * <code>HListGroup</code> to determine the size for each icon. If
     * the returned dimension object has <code>DEFAULT_ICON_WIDTH</code>
     * for the width and/or <code>DEFAULT_ICON_HEIGHT</code> for the
     * height as values, then this method shall use implementation specific
     * value(s) as default(s) for the missing dimension(s) instead. 
     * <p>
     * Except for the alignment of labels and sizes of labels and icons, it
     * is explicitly not defined, how this look arranges icons and labels
     * within the elements' areas. Additionally, it is an implementation
     * option to render labels and icons in other sizes than specified, if
     * the available size per element is smaller or larger than label and
     * icon size. It is also not defined, how the look presents the current
     * item and selected items, or the current selection mode. The
     * elements shall be layed out as specified by
     * <code>getOrientation()</code> of the associated <code>HListGroup</code>.
     * <p>
     * When the associated <code>HListGroup</code> contains more elements than
     * presentable, the look shall make the user aware of that condition,
     * e.g. by displaying an additional scrollbar reflecting the current
     * scroll position. Again, the visible means by which this information
     * is conveyed is not defined and implementation dependent. It is an
     * implementation option for <code>HListGroupLook</code> to draw
     * elements before the scroll position, in order to fill the
     * available space.
     * <p>
     * The behavior of this method, when a subclass overrides the methods
     * <code>getInsets()</code> or <code>getElementInsets()</code>, is
     * not defined. Application developers shall not assume that the
     * corresponding borders will appear as specified.
     * <p>
     * The {@link org.havi.ui.HExtendedLook#renderVisible} method is
     * responsible for painting any implementation specific borders for
     * each HListElement as well as drawing of an additional scrollbar
     * if required.
     *
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
     * Returns the size to present one element of the specified
     * <code>HVisible</code> plus any additional dimensions that the
     * <code>HListGroupLook</code> requires for border decoration etc.
     * <p>
     * The extra space required for border decoration can be determined
     * from the <code>getInsets()</code> and
     * <code>getElementInsets()</code> methods. The behavior is
     * not defined for the case, when a subclass overrides these methods.
     * Application developers shall not assume any influence on the
     * returned dimensions.
     * <p>
     * The size per element shall be determined by calls to
     * <code>getIconSize()</code> and <code>getLabelSize()</code> of
     * <code>HListGroup</code>. If any of the dimensions requests a
     * default as specified by <code>DEFAULT_ICON_WIDTH</code>,
     * <code>DEFAULT_ICON_HEIGHT</code>, <code>DEFAULT_LABEL_WIDTH</code> and
     * <code>DEFAULT_LABEL_HEIGHT</code>, then an implementation specific
     * default is used for the corresponding value(s).
     *
     * @param visible <code>HVisible</code> to which this
     *                <code>HLook</code> is attached.
     * @return A dimension object indicating this <code>HLook</code>'s
     *         minimum size.
     * @see HListGroup#setIconSize
     * @see HListGroup#setLabelSize
     * @see HVisible#getMinimumSize 
     */
    public Dimension getMinimumSize(HVisible visible)
    {
        return(null);
    }

    /**
     * Gets the preferred size of the <code>HVisible</code> component
     * when drawn with this <code>HListGroupLook</code>.
     * <p>
     * If a default size for width and height was set with
     * <code>HVisible.setDefaultSize()</code>, then the dimensions are
     * rounded down to the nearest element (minimum of one) according
     * to the orientation of the associated <code>HListGroup</code>, and
     * any dimensions for border decorations etc. are added.
     * <p>
     * If no default size was set or only for one dimension (i.e. height is
     * <code>NO_DEFAULT_HEIGHT</code> or width is
     * <code>NO_DEFAULT_WIDTH</code>), then the unset dimension(s) shall
     * be sufficiently large to present five elements according to the
     * <code>HListGroup</code>'s orientation. Any dimensions for border
     * decoration etc. are added.
     * <p>
     * The extra space required for border decoration can be determined
     * from the <code>getInsets()</code> and
     * <code>getElementInsets()</code> methods. The behavior is not
     * defined for the case, when a subclass overrides these methods.
     * Application developers shall not assume any influence on the
     * returned dimensions.
     * <p>
     * The size per element shall be determined by calls to
     * <code>getIconSize()</code> and <code>getLabelSize()</code> of
     * <code>HListGroup</code>. If any of the values requests a
     * default as specified by <code>DEFAULT_ICON_WIDTH</code>,
     * <code>DEFAULT_ICON_HEIGHT</code>,
     * <code>DEFAULT_LABEL_WIDTH</code> and
     * <code>DEFAULT_LABEL_HEIGHT</code>, then an implementation
     * specific default is used for the corresponding value(s).
     *
     * @param visible <code>HVisible</code> to which this
     *                <code>HLook</code> is attached.
     * @return A dimension object indicating the preferred size of the
     *         <code>HVisible</code> when drawn with this
     *         <code>HListGroupLook</code>.
     * @see HListGroup#setIconSize
     * @see HListGroup#setLabelSize
     * @see HVisible#getPreferredSize 
     * @see HVisible#setDefaultSize
     */
    public Dimension getPreferredSize(HVisible visible)
    {
        return(null);
    }

    /**
     * Returns the size to present all elements of the specified
     * <code>HVisible</code> plus any additional dimensions that the
     * <code>HListGroupLook</code> requires for border decoration
     * etc. If no elements are present, a dimension object is returned
     * with width and height set to <code>java.lang.Short.MAX_VALUE</code>.
     * <p>
     * The extra space required for border decoration can be determined
     * from the <code>getInsets()</code> and
     * <code>getElementInsets()</code> methods. The behavior is not
     * defined for the case, when a subclass overrides these methods.
     * Application developers shall not assume any influence on the
     * returned dimensions.
     * <p>
     * The size per element shall be determined by calls to
     * <code>getIconSize()</code> and <code>getLabelSize()</code> of
     * <code>HListGroup</code>. If any of the values
     * requests a default as specified by <code>DEFAULT_ICON_WIDTH</code>,
     * <code>DEFAULT_ICON_HEIGHT</code>,
     * <code>DEFAULT_LABEL_WIDTH</code> and
     * <code>DEFAULT_LABEL_HEIGHT</code>,
     * then an implementation specific default is used for the
     * corresponding value(s).
     *
     * @param visible <code>HVisible</code> to which this
     *        <code>HLook</code> is attached.
     * @return A dimension object indicating this
     * <code>HListGroupLook</code>'s maximum size.
     * @see HListGroup#setIconSize
     * @see HListGroup#setLabelSize
     * @see HVisible#getMaximumSize
     */
    public Dimension getMaximumSize(HVisible visible)
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
     * Returns a value which indicates the pointer click position in the
     * on-screen representation of the orientable component.<p>
     * The behavior of this method in {@link org.havi.ui.HListGroupLook
     * HListGroupLook} differs from the behavior of {@link
     * org.havi.ui.HAdjustableLook#hitTest HAdjustableLook.hitTest()} in
     * that if the position belongs to an {@link org.havi.ui.HListElement
     * HListElement} of the associated {@link org.havi.ui.HListGroup
     * HListGroup}, then this method will return the index of that
     * element. The <code>HListGroup</code> shall interpret this index
     * as current item. If the value is <code>ADJUST_THUMB</code>, then
     * the caller shall use <code>getValue()</code> to retrieve the
     * actual scroll position corresponding to the specified pointer
     * position. The look will not change the appearance of that element
     * (eg. by highlighting it). Such a change may only occur due to a
     * call to {@link #widgetChanged}.<p>
     * Note that it is a valid implementation option to always return
     * {@link org.havi.ui.HAdjustableLook#ADJUST_NONE ADJUST_NONE}.
     * @param component - the HOrientable component for which the hit
     * position should be calculated
     * @param pt - the pointer click point relative to the upper-left corner 
     * of the specified component.
     * @return one of {@link org.havi.ui.HAdjustableLook#ADJUST_NONE
     * ADJUST_NONE}, {@link
     * org.havi.ui.HAdjustableLook#ADJUST_BUTTON_LESS
     * ADJUST_BUTTON_LESS}, {@link
     * org.havi.ui.HAdjustableLook#ADJUST_PAGE_LESS ADJUST_PAGE_LESS},
     * {@link org.havi.ui.HAdjustableLook#ADJUST_THUMB ADJUST_THUMB},
     * {@link org.havi.ui.HAdjustableLook#ADJUST_PAGE_MORE
     * ADJUST_PAGE_MORE} or {@link	
     * org.havi.ui.HAdjustableLook#ADJUST_BUTTON_MORE
     * ADJUST_BUTTON_MORE}, or a non-negative element index.
     */
     public int hitTest(HOrientable component, java.awt.Point pt)
    {
        return(0);
    }

    /**
     * Returns the value of the component which corresponds to the pointer
     * position specified by pt. If the position does not map to a value
     * (eg. the mouse is outside the active area of the component), this
     * method returns <code>null</code>. A non-<code>null</code> value
     * represents the scroll position of the associated
     * <code>HListGroup</code>. The value shall never be less than
     * zero.
     * <p>
     * The look shall not reflect the value returned by this method visibly.
     * If the component uses the returned value, it will inform the look
     * by calling {@link #widgetChanged widgetChanged()}.
     * @param component an {@link org.havi.ui.HOrientable HOrientable}
     * implemented by an {@link org.havi.ui.HVisible HVisible}
     * @param pt the position of the mouse-cursor relative to the
     * upper-left corner of the associated component
     * @return the non-negative scroll position associated with the specified
     * pointer position or <code>null</code> 
     * @see #hitTest
     */
     public java.lang.Integer getValue(HOrientable component, java.awt.Point pt)
    {
        return(null);
    }

    /**
     * Retrieve the element insets for this instance of {@link
     * org.havi.ui.HListGroupLook HListGroupLook}. The element insets control the
     * amount of empty space left between the elements and the border
     * of the HListGroup component.
     * 
     * @return the element insets, or <code>null</code> if insets are
     * not used by this implementation of {@link org.havi.ui.HListGroupLook
     * HListGroupLook}. 
     */
    public java.awt.Insets getElementInsets()
    {
	return (null);
    }
    
    /**
     * Retrieve the number of visible elements for the specified
     * component.
     * <p>
     * This method should determine the number of list elements that
     * would be completely visible should the specified component be
     * drawn using this look. 
     *
     * @param visible the {@link org.havi.ui.HVisible HVisible} to
     * obtain the number of visible elements for.
     * @return the number of visible elements. 
     */
    public int getNumVisible(HVisible visible)
    {
	return (0);
    }
}

