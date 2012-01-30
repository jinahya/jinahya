package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Image;
import java.awt.Dimension;



/**
   The {@link org.havi.ui.HListGroup HListGroup} is a user interface
   component representing a list of selectable items ({@link
   org.havi.ui.HListElement HListElements}) which contain
   graphical and / or textual content.

   <p> 
   This component can be navigated to, i.e. it can have the input
   focus. It also responds to {@link org.havi.ui.event.HItemEvent
   HItemEvent} events as follows:

   <ul><p>
   
   <li>When the component has focus, sending an {@link
   org.havi.ui.event.HItemEvent#ITEM_START_CHANGE ITEM_START_CHANGE}
   event to the component causes it to enter selection mode.

   <li>When the component has focus, sending an {@link
   org.havi.ui.event.HItemEvent#ITEM_END_CHANGE ITEM_END_CHANGE}
   event to the component causes it to leave selection mode.

   <li>When the component has focus and is in selection mode, the
   current item can be set by sending {@link
   org.havi.ui.event.HItemEvent#ITEM_SET_CURRENT ITEM_SET_CURRENT},
   {@link org.havi.ui.event.HItemEvent#ITEM_SET_PREVIOUS
   ITEM_SET_PREVIOUS} and {@link
   org.havi.ui.event.HItemEvent#ITEM_SET_NEXT ITEM_SET_NEXT} events to
   the component.

   <li>When the component has focus and is in selection mode, sending
   an {@link org.havi.ui.event.HItemEvent#ITEM_TOGGLE_SELECTED
   ITEM_TOGGLE_SELECTED} event causes the current item to be toggled
   between a selected and unselected state.

   <li>Irrespective of focus and selection mode, sending an {@link
   org.havi.ui.event.HItemEvent#ITEM_SELECTION_CLEARED
   ITEM_SELECTION_CLEARED} event to the component causes the current
   selection set to be cleared.

   <li> HListGroup will respond to 
   {@link org.havi.ui.event.HItemEvent HItemEvent} with id SCROLL_PAGE_MORE 
   or SCROLL_PAGE_LESS by adjusting the scrollposition with the value returned
   by HListGroupLook.getNumVisible(HVisible visible), or an implementation-
   specific value if no look has been set on this widget.


   </ul><p>

   {@link org.havi.ui.HListGroup HListGroup} has the following properties
   which make it slightly different from the other platform
   components.

   <p><ul>

   <li> uses the {@link org.havi.ui.HTextLayoutManager
   HTextLayoutManager} to render text from the elements. {@link
   org.havi.ui.HListGroup HListGroup} is not required to respect the
   default horizontal and vertical content alignments specified by
   {@link org.havi.ui.HVisible HVisible}. For {@link
   org.havi.ui.HListGroup HListGroup} these defaults are implementation
   specific. Application programmers who require a specific
   alignment policy must therefore make explicit calls to {@link
   org.havi.ui.HVisible#setHorizontalAlignment setHorizontalAlignment}
   and {@link org.havi.ui.HVisible#setVerticalAlignment
   setVerticalAlignment} to enforce the alignments required.

   <li> the resize mode as defined by {@link org.havi.ui.HVisible
   HVisible} determines how the icons are scaled.

   <li> focus traversal applies to the entire list component. The
   elements in the list are not components in their own right and
   never receive focus. The concept of the current element is handled
   through {@link org.havi.ui.event.HItemEvent HItemEvent} events.

   <li> for the purpose of layout management of the {@link
   org.havi.ui.HListGroup HListGroup} component, the following
   constraints are applied:
   <p><ol>
   <li> the minimum size is the size to present one element or an
   implementation specific minimum (32 x 32 for example) if label
   and icon size are not set (see {@link
   HListGroupLook#getMinimumSize}).

   <li> the preferred size is that set by {@link
   org.havi.ui.HVisible#setDefaultSize setDefaultSize} rounded
   down to the nearest element (minimum of one) or the size
   required to present 5 elements if a default size is not set.

   <li> the maximum size is that required to present all elements.
   </ol><p>
   </ul><p>

   <p>	
   Interoperable HAVi applications shall not add 
   {@link org.havi.ui.HListElement HListElements} more than once. If an
   application requires items with identical contents (label and/or
   icon), then additional items shall be created. The behavior of the
   {@link org.havi.ui.HListGroup} if duplicates are added is
   implementation specific. The methods <code>setIcon()</code> and
   <code>setLabel()</code> of <code>HListElement</code> shall not be
   used for elements, which are part of <code>HListGroup</code>. If an
   application requires to alter the content, it shall either replace
   the entire element, or remove it temporarily and re-add it after the content
   was changed.
   <p>   
   
   <p>
   By default this component uses the {@link org.havi.ui.HListGroupLook
   HListGroupLook} class to render itself.

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
  <td>x-coordinate of top left hand corner of this component in pixels, 
  relative to its parent container (subject to layout management).</td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>y</td>
  <td>y-coordinate of top left hand corner of this component in pixels,
  relative to its parent container (subject to layout management).</td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>width</td>
  <td>width of this component in pixels (subject to layout management). 
  </td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>height</td>
  <td>height of this component in pixels (subject to layout management). 
  </td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>

  
  <tr>
  <td>items</td>
  <td>The initial list of elements for this HListGroup
   or null for an empty list.</td>
  <td>null</td>
  <td>{@link org.havi.ui.HListGroup#setListContent}</td>
  <td>{@link org.havi.ui.HListGroup#getListContent}</td>
  </tr>

  

  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr> 
  <td>Associated matte ({@link org.havi.ui.HMatte HMatte}).</td> 
  <td>none (i.e. getMatte() returns <code>null</code>)</td> 
  <td>{@link org.havi.ui.HComponent#setMatte setMatte}</td> 
  <td>{@link org.havi.ui.HComponent#getMatte getMatte}</td> 
  </tr>
   <tr>
       <td> The text layout manager responsible for text
       formatting.</td>
       <td> An {@link org.havi.ui.HDefaultTextLayoutManager}
       object.</td>
       <td> {@link org.havi.ui.HVisible#setTextLayoutManager}
       </td>
       <td> {@link org.havi.ui.HVisible#getTextLayoutManager}
       </td>
   </tr>

   <tr>
       <td>The background painting mode</td>
       <td>{@link org.havi.ui.HVisible#NO_BACKGROUND_FILL}</td>

       <td>{@link org.havi.ui.HVisible#setBackgroundMode}</td>
       <td>{@link org.havi.ui.HVisible#getBackgroundMode}</td>
   </tr>

   <tr>
       <td>The default preferred size</td>
       <td>not set (i.e. NO_DEFAULT_SIZE) unless specified by <code>width</code>
       and <code>height</code> parameters</td>
       <td>{@link org.havi.ui.HVisible#setDefaultSize}</td>
       <td>{@link org.havi.ui.HVisible#getDefaultSize}</td>
   </tr>

   <tr>
       <td>The horizontal content alignment</td>
       <td>{@link org.havi.ui.HVisible#HALIGN_CENTER}</td>
       <td>{@link org.havi.ui.HVisible#setHorizontalAlignment}</td>
       <td>{@link org.havi.ui.HVisible#getHorizontalAlignment}</td>
   </tr>

   <tr>
       <td>The vertical content alignment</td>
       <td>{@link org.havi.ui.HVisible#VALIGN_CENTER}</td>
       <td>{@link org.havi.ui.HVisible#setVerticalAlignment}</td>
       <td>{@link org.havi.ui.HVisible#getVerticalAlignment}</td>
   </tr>

   <tr>
       <td>The content scaling mode</td>
       <td>{@link org.havi.ui.HVisible#RESIZE_NONE}</td>
       <td>{@link org.havi.ui.HVisible#setResizeMode}</td>
       <td>{@link org.havi.ui.HVisible#getResizeMode}</td>
   </tr>

<tr>
    <td>The border mode</td>
    <td><code>true</code></td>
    <td>{@link org.havi.ui.HVisible#setBordersEnabled}</td>
    <td>{@link org.havi.ui.HVisible#getBordersEnabled}</td>
</tr>




<tr>
<td>Orientation</td>
<td>ORIENT_TOP_TO_BOTTOM</td>
<td>{@link org.havi.ui.HListGroup#setOrientation setOrientation}</td>
<td>{@link org.havi.ui.HListGroup#getOrientation getOrientation}</td>
</tr>
<tr>
<td>Multi selection</td>
<td>false</td>
<td>{@link org.havi.ui.HListGroup#setMultiSelection setMultiSelection}</td>
<td>{@link org.havi.ui.HListGroup#getMultiSelection getMultiSelection}</td>
</tr>
<tr>
<td>Selection mode</td>
<td>false</td>
<td>{@link org.havi.ui.HListGroup#setSelectionMode setSelectionMode}</td>
<td>{@link org.havi.ui.HListGroup#getSelectionMode getSelectionMode}</td>
</tr>
<tr>
<td>Current item</td>
<td>0 if list content was set (i.e. at least one {@link 
    org.havi.ui.HListElement HListElement}),
    {@link org.havi.ui.HListGroup#ITEM_NOT_FOUND ITEM_NOT_FOUND} else</td>
<td>{@link org.havi.ui.HListGroup#setCurrentItem setCurrentItem}</td>
<td>{@link org.havi.ui.HListGroup#getCurrentIndex getCurrentIndex}</td>
</tr>
<tr>
<td>Scrollposition</td>
<td>0 if list content was set (i.e. at least one {@link 
    org.havi.ui.HListElement HListElement}),
    {@link org.havi.ui.HListGroup#ITEM_NOT_FOUND ITEM_NOT_FOUND} else</td>
<td>{@link org.havi.ui.HListGroup#setScrollPosition setScrollPosition}</td>
<td>{@link org.havi.ui.HListGroup#getScrollPosition getScrollPosition}</td>
</tr>
<tr>
<td>Selection</td>
<td>null</td>
<td>{@link org.havi.ui.HListGroup#getSelection getSelection}</td>
<td>---</td>
</tr>
<tr>
<td>Label size</td>
<td>not set (i.e. null)</td>
<td>{@link org.havi.ui.HListGroup#setLabelSize setLabelSize}</td>
<td>{@link org.havi.ui.HListGroup#getLabelSize getLabelSize}</td>
</tr>
<tr>
<td>Icon size</td>
<td>not set (i.e. null)</td>
<td>{@link org.havi.ui.HListGroup#setIconSize setIconSize}</td>
<td>{@link org.havi.ui.HListGroup#getIconSize getIconSize}</td>
</tr>

<tr>
  <td>The default &quot;look&quot; for this class.</td>
  <td>A platform specific {@link org.havi.ui.HListGroupLook HListGroupLook}</td>
  <td>{@link org.havi.ui.HListGroup#setDefaultLook HListGroup.setDefaultLook}</td>
  <td>{@link org.havi.ui.HListGroup#getDefaultLook HListGroup.getDefaultLook}</td>
</tr>

<tr>
  <td>The &quot;look&quot; for this object.</td>
  <td>The {@link org.havi.ui.HListGroupLook HListGroupLook} returned from
  HListGroup.getDefaultLook when this object was created.</td>
  <td>{@link org.havi.ui.HListGroup#setLook HListGroup.setLook}</td>
  <td>{@link org.havi.ui.HListGroup#getLook HListGroup.getLook}</td>
</tr>
<tr>
  <td>The gain focus sound.</td>
  <td>null </td>
  <td>{@link org.havi.ui.HListGroup#setGainFocusSound setGainFocusSound}</td>
  <td>{@link org.havi.ui.HListGroup#getGainFocusSound getGainFocusSound}</td>
</tr>
<tr>
  <td>The lose focus sound. </td>
  <td>null </td>
  <td>{@link org.havi.ui.HListGroup#setLoseFocusSound setLoseFocusSound}</td>
  <td>{@link org.havi.ui.HListGroup#getLoseFocusSound getLoseFocusSound}</td>
</tr>
<tr>
  <td>The selection sound.</td>
  <td>null </td>
  <td>{@link org.havi.ui.HListGroup#setSelectionSound setSelectionSound}</td>
  <td>{@link org.havi.ui.HListGroup#getSelectionSound getSelectionSound}</td>
</tr>
  </table>

  @see HListElement
  @see HListGroupLook
  @see HNavigable
  @see HItemValue

*/

public class HListGroup extends HVisible
    implements HItemValue	
{
    /**
     * A constant which shall be returned from {@link
     * org.havi.ui.HListGroup#getIndex getIndex} if the
     * requested element is not found in the content.
     */
    public static final int ITEM_NOT_FOUND = -1;

    /**
     * A constant for use with {@link org.havi.ui.HListGroup#addItem
     * addItem} and {@link org.havi.ui.HListGroup#addItems addItems}
     * which specifies that the new items shall be appended to the
     * end of the list.
     */
    public static final int ADD_INDEX_END = -1;

    /**
     * A constant for use with {@link org.havi.ui.HListGroup#setLabelSize
     * setLabelSize} and {@link org.havi.ui.HListGroup#getLabelSize
     * getLabelSize}. When no call to
     * {@link org.havi.ui.HListGroup#setLabelSize setLabelSize} has been
     * made then {@link org.havi.ui.HListGroup#getLabelSize getLabelSize}
     * will return this value for its default width. The default label
     * width for all orientations is implementation specific.
     */
    public static final int DEFAULT_LABEL_WIDTH = -1;
    
    /**
     * A constant for use with {@link org.havi.ui.HListGroup#setLabelSize
     * setLabelSize} and {@link org.havi.ui.HListGroup#getLabelSize
     * getLabelSize}. When no call to
     * {@link org.havi.ui.HListGroup#setLabelSize setLabelSize} has been
     * made then {@link org.havi.ui.HListGroup#getLabelSize getLabelSize}
     * will return this value for its default height. The default label
     * height for all orientations is the current font height.
     */
    public static final int DEFAULT_LABEL_HEIGHT = -2;
    
    /**
     * A constant for use with {@link org.havi.ui.HListGroup#setIconSize
     * setIconSize} and {@link org.havi.ui.HListGroup#getIconSize
     * getIconSize}. When no call to
     * {@link org.havi.ui.HListGroup#setIconSize setIconSize} has been
     * made then {@link org.havi.ui.HListGroup#getIconSize getIconSize}
     * will return this value for its default width. The default icon
     * width for all orientations is implementation specific.
     */
    public static final int DEFAULT_ICON_WIDTH = -3;
    
    /**
     * A constant for use with {@link org.havi.ui.HListGroup#setIconSize
     * setIconSize} and {@link org.havi.ui.HListGroup#getIconSize
     * getIconSize}. When no call to
     * {@link org.havi.ui.HListGroup#setIconSize setIconSize} has been
     * made then {@link org.havi.ui.HListGroup#getIconSize getIconSize}
     * will return this value for its default height. The default icon
     * height for all orientations is implementation specific.
     */
    public static final int DEFAULT_ICON_HEIGHT = -4;
    
    /**
     * Creates an {@link org.havi.ui.HListGroup HListGroup}
     * object. See the class description for details of constructor
     * parameters and default values.
     */
    public HListGroup()
    {
    }

    /**
     * Creates an {@link org.havi.ui.HListGroup HListGroup}
     * object. See the class description for details of constructor
     * parameters and default values.
     */
    public HListGroup(HListElement[] items)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HListGroup HListGroup}
     * object. See the class description for details of constructor
     * parameters and default values.
     */
    public HListGroup(HListElement[] items, int x, int y,
		      int width, int height)
    {
    }

    /**
     * Sets the {@link org.havi.ui.HLook HLook} for this component.
     *
     * @param hlook The {@link org.havi.ui.HLook HLook} that is to be
     * used for this component.
     * Note that this parameter may be null, in which case the
     * component will not draw itself until a look is set.
     * @exception HInvalidLookException If the Look is not an {@link
     * org.havi.ui.HListGroupLook HListGroupLook}.
     */
    public void setLook(HLook hlook) throws HInvalidLookException
    {
    }

    /**
     * Sets the default {@link org.havi.ui.HLook HLook} for further
     * {@link org.havi.ui.HListGroup HListGroup} Components.
     *
     * @param look The {@link org.havi.ui.HLook HLook} that will be
     * used by default when creating a new {@link
     * org.havi.ui.HListGroup HListGroup} component.
     * Note that this parameter may be null, in which case newly
     * created components shall not draw themselves until a non-null
     * look is set using the {@link
     * org.havi.ui.HListGroup#setLook setLook} method.
     */
    public static void setDefaultLook(HListGroupLook look)
    {
    }

    /**
     * Returns the currently set default {@link org.havi.ui.HLook
     * HLook} for {@link org.havi.ui.HListGroup HListGroup}
     * components.
     *
     * @return The {@link org.havi.ui.HLook HLook} that is used by
     * default when creating a new {@link org.havi.ui.HListGroup
     * HListGroup} component.
     */
    public static HListGroupLook getDefaultLook()
    {
        return (null);
    }

    /**
     * Retrieve the list content for this {@link
     * org.havi.ui.HListGroup HListGroup}.
     *
     * @return the list content or <code>null</code> if no content has
     * been set.
     */
    public HListElement[] getListContent()
    {
	return (null);
    }

    /**
     * Set the list content for this HListGroup. If any items are
     * selected, then the selection shall be discarded and an
     * <code>HItemEvent</code>  with an ID of
     * <code>ITEM_SELECTION_CLEARED</code> shall be generated and sent
     * to all registered listeners.
     * <p>
     * If <code>elements</code> is <code>null</code> then the current
     * active item index shall be set to <code>ITEM_NOT_FOUND</code>. If
     * elements is not <code>null</code> then the current active item 
     * index shall be set to zero. An HItemEvent with an ID of
     * <code>ITEM_SET_CURRENT</code> shall be sent to all registered listeners.
     *
     * @param elements the list content. If this parameter is
     * <code>null</code> any existing content is removed.
     */
    public void setListContent(HListElement[] elements)
    {
    }

    /**
     * Add an item to this {@link org.havi.ui.HListGroup
     * HListGroup}. The item is inserted at the specified zero-based
     * index in the content list. All following elements are shifted. If
     * no content exists a new content list is created to contain the
     * new item and the value of the <code>index</code> parameter is
     * ignored.
     * <p>
     * If the act of adding a new item causes the current active item
     * index to change, an {@link org.havi.ui.event.HItemEvent
     * HItemEvent} shall be sent.
     * <p>
     * Note that items are stored in the content list by reference,
     * they are not copied.
     *
     * @param item the item to add.
     * @param index the index of the currently existing item which the
     * new item should be placed at, or <code>ADD_INDEX_END</code>
     * to append the new item to the end of the list.  If this value
     * is not a valid item index for this list a
     * <code>java.lang.IndexOutOfBoundsException</code> shall be
     * thrown.
     */
     public void addItem(HListElement item, int index)
     {
     }

    /**
     * Add an array of items to this {@link org.havi.ui.HListGroup
     * HListGroup}. The items are inserted in the same order as they
     * are in the array at the specified zero-based index. All following
     * items are shifted. If no content exists a new content list is
     * created to contain the new items and the value of the
     * <code>index</code> parameter is ignored.
     * <p>
     * If the act of adding a new item causes the current active item
     * index to change, an {@link org.havi.ui.event.HItemEvent
     * HItemEvent} shall be sent.
     * <p>
     * Note that items are stored in the content list by reference,
     * they are not copied.
     *
     * @param items the items to add.
     * @param index the index of the currently existing item which the
     * new items should be placed at, or <code>ADD_INDEX_END</code>
     * to append the new items to the end of the list.  If this value
     * is not a valid item index for this list a
     * <code>java.lang.IndexOutOfBoundsException</code> shall be
     * thrown.
     */
     public void addItems(HListElement[] items, int index)
     {
     }

    /**
     * Retrieve an item from the content list by index.
     *
     * @param index the index of the item to retrieve. If this
     * parameter is negative a
     * <code>java.lang.IllegalArgumentException</code> shall be thrown.
     * @return the {@link org.havi.ui.HListElement HListElement} at
     * the given index, or <code>null</code> if no such element
     * exists.
     */
    public HListElement getItem(int index)
    {
	return (null);
    }

    /**
     * Retrieve the index position of an item in the content list.
     *
     * @param item the item to retrieve the index for.
     * @return the index of the given {@link org.havi.ui.HListElement
     * HListElement}, or {@link org.havi.ui.HListGroup#ITEM_NOT_FOUND
     * ITEM_NOT_FOUND} if no such element exists.
     */
    public int getIndex (HListElement item)
    {
	return (0);
    }

    /**
     * Retrieve the number of items in the content list.
     *
     * @return the number of items in the content list, or 0 if no
     * content has been set.
     */
    public int getNumItems()
    {
	return (0);
    }

    /**
     * Remove the HListElement at the specified index. All following items
     * are shifted. 
     * <p>
     * If the item is the only HListElement in this HListGroup then the
     * current active item index shall be set to
     * <code>ITEM_NOT_FOUND</code>. If the removal of the item causes
     * a change of the current active item index, then an HItemEvent
     * with an ID of <code>ITEM_SET_CURRENT</code> shall be generated
     * and sent to all registered listeners.
     * <p>
     * If the item is selected then it shall be removed from the selection
     * and an HItemEvent with an ID of <code>ITEM_CLEARED</code> shall
     * be generated and sent to all registered listeners.
     *
     * @param index the index of the item to remove.  
     * @return the {@link org.havi.ui.HListElement HListElement} that has
     * been removed or <code>null</code> if the index is not valid. No
     * exception is thrown if <code>index</code> is not valid.
     * @see HListGroup#getSelection 
     */
    public HListElement removeItem(int index)
    {
	return null;
    }
    
    /**
     * Remove all the content. If any items are selected, then the
     * selection shall be discarded and an HItemEvent with an ID of
     * <code>ITEM_SELECTION_CLEARED</code> shall be generated and sent
     * to all registered listeners.
     * <p>
     * The current active item index shall be set to
     * <code>ITEM_NOT_FOUND</code> and an HItemEvent with an ID of
     * <code>ITEM_SET_CURRENT</code> shall be generated and sent to all
     * registered listeners.
     *
     * @see HListGroup#getSelection
     */
    public void removeAllItems()
    {
    }

    /**
     * Retrieve the current active item index, if one is currently
     * chosen. The current index is the index of the 
     * {@link org.havi.ui.HListElement HListElement} which would be
     * selected or deselected should the user toggle the
     * {@link org.havi.ui.HListGroup HListGroup}. If there is no
     * current element or there is no content set this method shall
     * return {@link org.havi.ui.HListGroup#ITEM_NOT_FOUND
     * ITEM_NOT_FOUND}.
     * 
     * @return the current item index, or
     * {@link org.havi.ui.HListGroup#ITEM_NOT_FOUND ITEM_NOT_FOUND} if
     * no such item exists.  
     */
    public int getCurrentIndex()
    {
      return (0);
    }

    /**
     * Retrieve the current active item, if one has been chosen. The current
     * item is the {@link org.havi.ui.HListElement HListElement} which
     * would be selected or deselected should the user toggle the
     * {@link org.havi.ui.HListGroup HListGroup}. If there is no
     * current item or there is no content set this method shall
     * return <code>null</code>.
     *
     * @return the current item, or <code>null</code> if no such item
     * exists.
     */
    public HListElement getCurrentItem()
    {
	return (null);
    }

    /**
     * Set the current active item. The current item is the {@link
     * org.havi.ui.HListElement} which would be selected
     * or deselected should the user toggle the HListGroup.
     * <p>
     * If index is valid for this HListGroup then the current active item
     * index shall be set to index. If this causes a change in the current
     * active item index then an HItemEvent with an ID of
     * <code>ITEM_SET_CURRENT</code> shall be generated and sent to
     * all registered listeners.
     * 
     * @param index the index of the new current item.
     * @return <code>true</code> if the current item was changed,
     * <code>false</code> if <code>index</code> was not a valid index
     * for this HListGroup or the
     * current item was not changed because it was already
     * the current item. No exception is thrown if <code>index</code> is not
     * valid.
     */
    public boolean setCurrentItem(int index)
    {
	return (false);
    }

    /**
     * Get the list of selection indices from this
     * {@link org.havi.ui.HListGroup HListGroup}.  The selection is
     * defined as that set of
     * {@link org.havi.ui.HListElement HListElement} indices which
     * the user has caused to be selected by toggling the {@link
     * org.havi.ui.HListGroup HListGroup}.
     * 
     * @return the index selection, or <code>null</code> if no items
     * are selected. Only items which are currently part of the content
     * for this {@link org.havi.ui.HListGroup HListGroup} may be
     * selected.  
     */
    public int[] getSelectionIndices()
    {
      return (null);
    }

    /**
     * Get the selection from this {@link org.havi.ui.HListGroup
     * HListGroup}.  The selection is defined as that set of {@link
     * org.havi.ui.HListElement HListElements} which the user has
     * caused to be selected by toggling the {@link
     * org.havi.ui.HListGroup HListGroup}.
     *
     * @return the selection, or <code>null</code> if no items are
     * selected. Only items which are currently part of the content
     * for this {@link org.havi.ui.HListGroup HListGroup} may be
     * selected.
     */
    public HListElement[] getSelection()
    {
	return (null);
    }

    /**
     * Destroy the selection. This method deselects any selected
     * {@link org.havi.ui.HListElement HListElement}, but does not
     * remove them from the {@link org.havi.ui.HListGroup
     * HListGroup}. After calling this method calls to {@link
     * org.havi.ui.HListGroup#getSelection getSelection} shall return
     * <code>null</code> until a new selection is made.
     * <p>
     * If the selection was not already empty, an {@link
     * org.havi.ui.event.HItemEvent HItemEvent}
     * shall be sent to any registered listeners.
     *
     * @see HListGroup#getSelection
     */
    public void clearSelection()
    {
    }

    /**
     * Return the number of items which would be in the selection, if
     * the {@link org.havi.ui.HListGroup#getSelection
     * HListGroup#getSelection} method were called at this time.
     *
     * @return the number of selected items.
     */
    public int getNumSelected()
    {
	return (0);
    }

    /**
     * Return the multiple selection mode currently active for this
     * HListGroup. Multiple selection mode means that there may be
     * more than one {@link org.havi.ui.HListElement HListElement}
     * selected at a time.
     *
     * @return <code>true</code> if multiple selections are permitted,
     * <code>false</code> otherwise.
     */
    public boolean getMultiSelection()
    {
	return (false);
    }

    /**
     * Set the multiple selection mode for this
     * HListGroup. Multiple selection mode means that there may be
     * more than one {@link org.havi.ui.HListElement HListElement}
     * selected at a time.
     * <p>
     * Note that if the HListGroup is
     * switched out of multiple selection mode and more than one item
     * is selected, the selection shall change so that the first of
     * the items is selected and the others are deselected. An
     * {@link org.havi.ui.event.HItemEvent} with an ID of
     * <code>ITEM_CLEARED</code> shall be sent to all registered listeners
     * for each deselected item.
     *
     * @param multi <code>true</code> if multiple selections are to be
     * permitted, <code>false</code> otherwise.
     */
    public void setMultiSelection(boolean multi)
    {
    }
	
    /**
     * Set the selection state of a particular {@link
     * org.havi.ui.HListElement}.
     * <p>
     * If a call to this method causes an item to become deselected an
     * HItemEvent with an ID of <code>ITEM_CLEARED</code> shall be sent to
     * all registered listeners. This can happen because either
     * <code>sel</code> is false or this HListGroup is not in multi
     * selection mode.
     * <p>
     * If a call to this method causes a non selected item to become a
     * selected item then an HItemEvent with an ID of
     * <code>ITEM_SELECTED</code> shall be sent to all registered listeners.
     *
     * @param index the index of the {@link org.havi.ui.HListElement
     * HListElement} to alter. A
     * <code>java.lang.IllegalArgumentException</code> shall be thrown
     * if this index is not valid for the {@link
     * org.havi.ui.HListGroup HListGroup}.
     * @param sel <code>true</code> to select the given {@link
     * org.havi.ui.HListElement HListElement}, <code>false</code>
     * otherwise.
     */
    public void setItemSelected(int index, boolean sel)
    {
    }

    /**
     * Retrieve the selection state of a particular {@link
     * org.havi.ui.HListElement HListElement}.
     * <p>
     * Note that if multiple selections are not permitted only one
     * HListElement may be selected at a time.
     *
     * @param index the index of the {@link org.havi.ui.HListElement
     * HListElement} to query. A
     * <code>java.lang.IllegalArgumentException</code> shall be thrown
     * if this index is not valid for the {@link
     * org.havi.ui.HListGroup HListGroup}.
     * @return <code>true</code> if the given {@link
     * org.havi.ui.HListElement HListElement} is selected,
     * <code>false</code> otherwise.
     */
    public boolean isItemSelected(int index)
    {
	return (false);
    }


    /**
     * Retrieve the scroll position of the {@link
     * org.havi.ui.HListGroup HListGroup}. The scroll position
     * determines the first {@link org.havi.ui.HListElement
     * HListElement} to be drawn when the {@link
     * org.havi.ui.HListGroupLook HListGroupLook} lays out the list.
     *
     * @return the current scroll position, or ITEM_NOT_FOUND if no
     * content is set.
     */
    public int getScrollPosition()
    {
	return (0);
    }

    /**
     * Set the scroll position of the {@link
     * org.havi.ui.HListGroup HListGroup}. The scroll position
     * determines the first {@link org.havi.ui.HListElement
     * HListElement} to be drawn when the {@link
     * org.havi.ui.HListGroupLook HListGroupLook} lays out the list.
     * An IllegalArgumentException shall be thrown if scroll is not
     * a valid scroll position.
     * <p>
     * It is an implementation option for {@link
     * org.havi.ui.HListGroupLook HListGroupLook} to draw elements
     * before this first one, in order to fill the available space.
     * <p>
     * Valid scrollpositions conform to 0<= scrollposition< size. If no
     * content is set there are no valid scrollpositions.
     *
     * @param scroll the scroll position
     */
    public void setScrollPosition(int scroll)
    {
    }

    /**
     * Retrieve the icon size for this {@link org.havi.ui.HListGroup
     * HListGroup}. This size is the desired size of the area into
     * which the {@link org.havi.ui.HListGroupLook HListGroupLook}
     * should render any image content of the {@link
     * org.havi.ui.HListElement HListElements}.
     * If label and icon size do not match the size per element, the
     * associated <code>HListGroupLook</code> is allowed to use other
     * sizes during the rendering process.  This size shall be used by
     * <code>HListGroupLook</code> to calculate the size per element.
     *
     * @return the icon size. If no size has been set then this
     * method shall return <code>new
     * Dimension(DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT)</code>.
     */
    public Dimension getIconSize()
    {
	return (null);
    }

    /**
     * Set the icon size for this {@link org.havi.ui.HListGroup
     * HListGroup}. This size is the desired size of the area into
     * which the {@link org.havi.ui.HListGroupLook HListGroupLook}
     * should render any image content of the {@link
     * org.havi.ui.HListElement HListElements}.
     * If label and icon size do not match the size per element, the
     * associated <code>HListGroupLook</code> is allowed to use other
     * sizes during the rendering process.  This size shall be used by
     * <code>HListGroupLook</code> to calculate the size per element.
     *
     * @param size the icon size. If this parameter is <code>new
     * Dimension(DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT)</code> or
     * <code>null</code> the {@link org.havi.ui.HListGroup HListGroup}
     * shall revert to using an implementation-specific icon size.
     */
    public void setIconSize(Dimension size)
    {
    }

    /**
     * Retrieve the label size for this {@link org.havi.ui.HListGroup
     * HListGroup}. This size is the desired size of the area into
     * which the {@link org.havi.ui.HListGroupLook HListGroupLook}
     * should render any textual content of the {@link
     * org.havi.ui.HListElement HListElements}.
     * If label and icon size do not match the size per element, the
     * associated <code>HListGroupLook</code> is allowed to use other
     * sizes during the rendering process.  This size shall be used by
     * <code>HListGroupLook</code> to calculate the size per element.
     *
     * @return the label size. If no size has been set then this
     * method shall return <code>new
     * Dimension(DEFAULT_LABEL_WIDTH, DEFAULT_LABEL_HEIGHT)</code>.
     */
    public Dimension getLabelSize()
    {
	return (null);
    }

    /**
     * Set the label size for this {@link org.havi.ui.HListGroup
     * HListGroup}. This size is the desired size of the area into
     * which the {@link org.havi.ui.HListGroupLook HListGroupLook}
     * should render any textual content of the {@link
     * org.havi.ui.HListElement HListElements}.
     * If label and icon size do not match the size per element, the
     * associated <code>HListGroupLook</code> is allowed to use other
     * sizes during the rendering process.  This size shall be used by
     * <code>HListGroupLook</code> to calculate the size per element.
     *
     * @param size the label size. If this parameter is <code>new
     * Dimension(DEFAULT_LABEL_WIDTH, DEFAULT_LABEL_HEIGHT)</code> or
     * <code>null</code> the {@link org.havi.ui.HListGroup HListGroup}
     * shall revert to using an implementation-specific label size.
     */
    public void setLabelSize(Dimension size)
    {
    }

    /**
     * Defines the navigation path from the current {@link
     * org.havi.ui.HNavigable HNavigable} to another {@link
     * org.havi.ui.HNavigable HNavigable} when a particular key is
     * pressed. <p> Note that {@link
     * org.havi.ui.HNavigable#setFocusTraversal setFocusTraversal} is
     * equivalent to multiple calls to {@link
     * org.havi.ui.HNavigable#setMove setMove}, where the key codes
     * <code>VK_UP</code>, <code>VK_DOWN</code>, <code>VK_LEFT</code>,
     * <code>VK_RIGHT</code> are used.
     * 
     * @param keyCode The key code of the pressed key.  Any numerical
     * keycode is allowed, but the platform may not be able to
     * generate all keycodes. Application authors should only use keys
     * for which <code>HRcCapabilities.isSupported()</code> or
     * <code>HKeyCapabilities.isSupported()</code> returns true.
     * @param target The target {@link org.havi.ui.HNavigable
     * HNavigable} object that should be navigated to. If a target is
     * to be removed from a particular navigation path, then
     * <code>null</code> shall be specified.  
     */
    public void setMove(int keyCode, HNavigable target)
    {
        return;
    }

    /**
     * Provides the {@link org.havi.ui.HNavigable HNavigable} object
     * that is navigated to when a particular key is pressed.
     * 
     * @param keyCode The key code of the pressed key.
     * @return Returns the {@link org.havi.ui.HNavigable HNavigable}
     * object or <code>null</code> if no {@link org.havi.ui.HNavigable
     * HNavigable} is associated with the keyCode.
     */
    public HNavigable getMove(int keyCode)
    {
        return(null);
    }

    /**
     * Set the focus control for an {@link org.havi.ui.HNavigable
     * HNavigable} component. Note {@link
     * org.havi.ui.HNavigable#setFocusTraversal setFocusTraversal} is a
     * convenience function for application programmers where a standard
     * up, down, left and right focus traversal between components is
     * required.  
     * <p> 
     * Note {@link org.havi.ui.HNavigable#setFocusTraversal
     * setFocusTraversal} is equivalent to multiple calls to {@link
     * org.havi.ui.HNavigable#setMove setMove}, where the key codes
     * VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT are used.  <p> Note that this
     * API does not prevent the creation of &quot;isolated&quot;
     * {@link org.havi.ui.HNavigable HNavigable} components --- authors
     * should endeavor to avoid confusing the user.
     * 
     * @param up The {@link org.havi.ui.HNavigable HNavigable} component
     * to move to, when the user generates a VK_UP KeyEvent. If there is no {@link
     * org.havi.ui.HNavigable HNavigable} component to move
     * &quot;up&quot; to, then null shall be specified.
     * @param down The {@link org.havi.ui.HNavigable HNavigable}
     * component to move to, when the user generates a VK_DOWN KeyEvent. If there
     * is no {@link org.havi.ui.HNavigable HNavigable} component to move
     * &quot;down&quot; to, then null shall be specified.
     * @param left The {@link org.havi.ui.HNavigable HNavigable}
     * component to move to, when the user generates a VK_LEFT KeyEvent. If there
     * is no {@link org.havi.ui.HNavigable HNavigable} component to move
     * &quot;left&quot; to, then null shall be specified.
     * @param right The {@link org.havi.ui.HNavigable HNavigable}
     * component to move to, when the user generates a VK_RIGHT KeyEvent. If there
     * is no {@link org.havi.ui.HNavigable HNavigable} component to move
     * &quot;right&quot; to, then null shall be specified.  
     */
    public void setFocusTraversal(HNavigable up, HNavigable down, HNavigable left, HNavigable right)
    {
        return;
    }

    /**
     * Indicates if this component has focus. 
     * 
     * @return <code>true</code> if the component has focus, otherwise
     * returns <code>false</code>.  
     */
    public boolean isSelected()
    {
        return(false);
    }

    /**
     * Associate a sound with gaining focus, i.e. when the {@link
     * org.havi.ui.HNavigable HNavigable} receives a
     * {@link org.havi.ui.event.HFocusEvent HFocusEvent} event of type
     * <code>FOCUS_GAINED</code>. This sound will start to be played
     * when an object implementing this interface gains focus. It is
     * not guaranteed to be played to completion. If the object
     * implementing this interface loses focus before the audio
     * completes playing, the audio will be truncated.  Applications
     * wishing to ensure the audio is always played to completion must
     * implement special logic to slow down the focus transitions. <p>
     * By default, an {@link org.havi.ui.HNavigable HNavigable} object
     * does not have any gain focus sound associated with it. <p> Note
     * that the ordering of playing sounds is dependent on the order
     * of the focus lost, gained events.
     * 
     * @param sound the sound to be played, when the component gains
     * focus. If sound content is already set, the original content is
     * replaced. To remove the sound specify a null {@link
     * org.havi.ui.HSound HSound}.  
     */
    public void setGainFocusSound(HSound sound)
    {
        return;
    }

    /**
     * Associate a sound with losing focus, i.e. when the {@link
     * org.havi.ui.HNavigable HNavigable} receives a
     * {@link org.havi.ui.event.HFocusEvent HFocusEvent} event of type
     * FOCUS_LOST. This sound will start to be played when an object 
     * implementing this interface loses focus. It is not guaranteed to be 
     * played to completion. It is implementation dependent whether and when 
     * this sound will be truncated by any gain focus sound played by the next
     * object to gain focus. <p> By default, an {@link
     * org.havi.ui.HNavigable HNavigable} object does not have any
     * lose focus sound associated with it. <p> Note that the ordering
     * of playing sounds is dependent on the order of the focus lost,
     * gained events.
     * 
     * @param sound the sound to be played, when the component loses
     * focus. If sound content is already set, the original content is
     * replaced. To remove the sound specify a null {@link
     * org.havi.ui.HSound HSound}.
     */
    public void setLoseFocusSound(HSound sound)
    {
        return;
    }

    /**
     * Get the sound associated with the gain focus event.
     * 
     * @return The sound played when the component gains focus. If no
     * sound is associated with gaining focus, then null shall be
     * returned.  
     */
    public HSound getGainFocusSound()
    {
        return(null);
    }

    /**
     * Get the sound associated with the lost focus event.
     * 
     * @return The sound played when the component loses focus. If no
     * sound is associated with losing focus, then null shall be
     * returned.  
     */
    public HSound getLoseFocusSound()
    {
        return(null);
    }

/**
 * Adds the specified {@link org.havi.ui.event.HFocusListener HFocusListener} to 
 * receive {@link org.havi.ui.event.HFocusEvent HFocusEvent} events sent from 
 * this {@link org.havi.ui.HNavigable HNavigable}: If the listener has 
 * already been added further calls will add further references to the listener,  
 * which will then receive  multiple copies of a single event.
 * 
 * @param l the HFocusListener to add
 */

public void addHFocusListener(org.havi.ui.event.HFocusListener l)
    {
        return;
    }


/**
 * Removes the specified {@link org.havi.ui.event.HFocusListener HFocusListener}      
 * so that it no longer receives {@link org.havi.ui.event.HFocusEvent   
 * HFocusEvent} events from this {@link org.havi.ui.HNavigable HNavigable}. If    
 * the specified listener is not registered, the method has no effect. If 
 * multiple references to a single listener have been registered it should be 
 * noted that this method will only remove one reference per call.
 *
 * @param l the HFocusListener to remove
 */

public void removeHFocusListener(org.havi.ui.event.HFocusListener l)
    {
        return;
    }




    /**
     * Retrieve the set of key codes which this component maps to
     * navigation targets. 
     *
     * @return an array of key codes, or <code>null</code> if no
     * navigation targets are set on this component.  
     */
    public int[] getNavigationKeys()
    {
        return(null);
    }

     /**
     * Process an {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent} sent to this {@link org.havi.ui.HListGroup
     * HListGroup}.
     * 
     * @param evt the {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent} to process. 
     */
     
	public void processHFocusEvent(org.havi.ui.event.HFocusEvent evt)
    {
        return;
    }


    /**
     * Retrieve the orientation of the HListGroup.
     * The orientation controls how an associated
     * <code>HLook</code> lays out the component and affects the
     * visual behavior of <code>HItemEvent</code> events.
     *
     * @return one of {@link
     * org.havi.ui.HOrientable#ORIENT_LEFT_TO_RIGHT
     * ORIENT_LEFT_TO_RIGHT}, {@link
     * org.havi.ui.HOrientable#ORIENT_RIGHT_TO_LEFT
     * ORIENT_RIGHT_TO_LEFT}, {@link
     * org.havi.ui.HOrientable#ORIENT_TOP_TO_BOTTOM
     * ORIENT_TOP_TO_BOTTOM}, or {@link
     * org.havi.ui.HOrientable#ORIENT_BOTTOM_TO_TOP
     * ORIENT_BOTTOM_TO_TOP}.  
     */
    public int getOrientation()
    {
        return(0);
    }

    /**
     * Set the orientation of the HListGroup.
     * The orientation controls the layout of the component.
     *
     * @param orient one of {@link
     * org.havi.ui.HOrientable#ORIENT_LEFT_TO_RIGHT
     * ORIENT_LEFT_TO_RIGHT}, {@link
     * org.havi.ui.HOrientable#ORIENT_RIGHT_TO_LEFT
     * ORIENT_RIGHT_TO_LEFT}, {@link
     * org.havi.ui.HOrientable#ORIENT_TOP_TO_BOTTOM
     * ORIENT_TOP_TO_BOTTOM}, or {@link
     * org.havi.ui.HOrientable#ORIENT_BOTTOM_TO_TOP
     * ORIENT_BOTTOM_TO_TOP}.  
     */
    public void setOrientation(int orient)
    {
        return;
    }

    /**
     * Adds the specified {@link
     * org.havi.ui.event.HItemListener HItemListener} to
     * receive {@link org.havi.ui.event.HItemEvent
     * HItemEvents} sent from this object.  If the listener has
     * already been added further calls will add further references to
     * the listener, which will then receive multiple copies of a
     * single event.
     * 	
     * @param l the {@link org.havi.ui.event.HItemListener
     * HItemListener} to be notified.  
     */
    public void addItemListener(org.havi.ui.event.HItemListener l)
    {
        return;
    }

    /**
     * Removes the specified {@link
     * org.havi.ui.event.HItemListener HItemListener} so
     * that it no longer receives {@link
     * org.havi.ui.event.HItemEvent HItemEvents} from
     * this object.  If the specified listener is not registered, the
     * method has no effect.  If multiple references to
     * a single listener have been registered it should be noted that
     * this method will only remove one reference per call.
     * 
     * @param l the {@link org.havi.ui.event.HItemListener
     * HItemListener} to be removed from notification.
     */
    public void removeItemListener(org.havi.ui.event.HItemListener l)
    {
        return;
    }
    
    /**
     * Associate a sound to be played when the selection is
     * modified. The sound is played irrespective of whether an {@link
     * org.havi.ui.event.HItemEvent HItemEvent} is sent to one or more
     * listeners.
     * 
     * @param sound the sound to be played, when the selection is
     * modified. If sound content is already set, the original content
     * is replaced. To remove the sound specify a null {@link
     * org.havi.ui.HSound HSound}.  
     */
    public void setSelectionSound(HSound sound)
    {
        return;
    }
        
    /**
     * Get the sound to be played when the selection changes.
     * 
     * @return The sound played when the selection changes 
     */
    public HSound getSelectionSound()
    {
        return(null);
    }
 

    /**
     * Get the selection mode for this {@link org.havi.ui.HListGroup
     * HListGroup}. If the returned value is <code>true</code> the
     * component is in selection mode, and the selection may be changed. 
     * <p>
     * The component is switched into and out of selection mode on
     * receiving {@link
     * org.havi.ui.event.HItemEvent#ITEM_START_CHANGE
     * ITEM_START_CHANGE} and {@link
     * org.havi.ui.event.HItemEvent#ITEM_END_CHANGE
     * ITEM_END_CHANGE} events. Note that these events are ignored, if
     * the component is disabled.
     * 
     * @return true if this component is in selection mode, false
     * otherwise.
     * @see HComponent#setEnabled
     */
    public boolean getSelectionMode()
    {
        return(true);
    }

    /**
     * Set the selection mode for this {@link org.havi.ui.HListGroup
     * HListGroup}. 
     * <p>
     * This method is provided for the convenience of component
     * implementors. Interoperable applications shall not call this
     * method. It cannot be made protected because interfaces cannot have
     * protected methods.
     * <p>
     * Calls to this method shall be ignored, if the component is
     * disabled.
     * @param edit true to switch this component into selection mode,
     * false otherwise.
     * @see HComponent#setEnabled
     * @see HSelectionInputPreferred#getSelectionMode 
     */
    public void setSelectionMode(boolean edit)
    {
        return;
    }

    /**
     * Process an {@link org.havi.ui.event.HItemEvent
     * HItemEvent} sent to this {@link org.havi.ui.HListGroup
     * HListGroup}.
     * <p>
     * Widgets implementing this interface shall ignore
     * <code>HItemEvent</code>a, while the component is disabled.
     * 
     * @param evt the {@link org.havi.ui.event.HItemEvent
     * HItemEvent} to process. 
     * @see HComponent#setEnabled
     */
    public void processHItemEvent(org.havi.ui.event.HItemEvent evt)
    {
        return;
    }

}


