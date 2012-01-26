package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


import org.havi.ui.HItemValue;

/**
   An {@link org.havi.ui.event.HItemEvent} event is used to
   interact with a component implementing the {@link
   org.havi.ui.HSelectionInputPreferred}
   interface as follows:

   <p><ul>

   <li> An {@link org.havi.ui.event.HItemEvent} event may be sent
   from the HAVi system to the component to change the state of the
   component, and the selection set held by the component.

   <li> An {@link org.havi.ui.event.HItemEvent} event is sent
   from the component to all registered {@link
   org.havi.ui.event.HItemListener}s when a change to
   the component state or selection set occurs.
   
   </ul><p>

   The {@link org.havi.ui.event.HItemEvent} event class therefore
   provides support for selecting and clearing individual elements,
   selecting or clearing all elements and managing the currently
   focused element, i.e. the item whose selection state is toggled by
   some user interaction. 

   <p>
   All interoperable HAVi components which expect to receive {@link
   org.havi.ui.event.HItemEvent} events should implement the
   {@link org.havi.ui.HSelectionInputPreferred} interface.   

   @see org.havi.ui.HListGroup

 */

public class HItemEvent 
    extends java.awt.AWTEvent
{
    /**
     * The first integer id in the range of event ids supported by the
     * {@link org.havi.ui.event.HItemEvent} class.
     */
    public static final int ITEM_FIRST = HAdjustmentEvent.ADJUST_LAST + 1;

    /**
     * An item event with this id indicates that the selection of
     * an {@link org.havi.ui.HItemValue} component may be
     * about to change. This event is sent to or from the component 
     * when the user causes the
     * component to enter selection mode. Note that it is a
     * platform specific implementation option for such components to
     * enter selection mode automatically e.g. when they receive input
     * focus. In such a case the order in which the {@link
     * org.havi.ui.event.HFocusEvent} and {@link
     * org.havi.ui.event.HItemEvent} are sent is platform
     * specific.
     * 
     * @see org.havi.ui.HSelectionInputPreferred#getSelectionMode
     */
    public static final int ITEM_START_CHANGE = ITEM_FIRST;

    /**
     * An item event with this id may be sent to an {@link
     * org.havi.ui.HItemValue HItemValue} component to toggle the
     * selection state of the currently focused item. Note that events
     * of this type are never sent to listeners from the component;
     * instead an event of type {@link
     * org.havi.ui.event.HItemEvent#ITEM_SELECTED} or
     * {@link org.havi.ui.event.HItemEvent#ITEM_CLEARED}
     * is sent in response to an event with this id, depending on the
     * new state of the currently focused item.
     */
    public static final int ITEM_TOGGLE_SELECTED = ITEM_FIRST + 1;

    /**
     * An item event with this id is sent from the component whenever
     * an item is added to the selection set of an {@link
     * org.havi.ui.HItemValue} component. Note that events
     * of this type are silently ignored if they are sent to the
     * component.  
     */
    public static final int ITEM_SELECTED = ITEM_FIRST + 2;

    /**
     * An item event with this id is sent from the component whenever
     * an item is removed from the selection set of an {@link
     * org.havi.ui.HItemValue} component.  Note that events
     * of this type are silently ignored if they are sent to the
     * component.  
     */
    public static final int ITEM_CLEARED = ITEM_FIRST + 3;

    /**
     * An item event with this id is sent to or from the component
     * whenever the entire selection set of an {@link
     * org.havi.ui.HItemValue} component is removed.
     */
    public static final int ITEM_SELECTION_CLEARED = ITEM_FIRST + 4;

    /**
     * An item event with this id is sent to or from the component
     * whenever the current item of an {@link org.havi.ui.HItemValue}
     * component changes.
     */
    public static final int ITEM_SET_CURRENT = ITEM_FIRST + 5;

    /**
     * An item event with this id is sent to or from the component
     * whenever the current item of an {@link org.havi.ui.HItemValue}
     * component changes to the previous item in the
     * group. 
     */
    public static final int ITEM_SET_PREVIOUS = ITEM_FIRST + 6;
  
    /**
     * An item event with this id is sent to or from the component
     * whenever the current item of an {@link org.havi.ui.HItemValue}
     * component changes to the next item in the group.  
     */
    public static final int ITEM_SET_NEXT = ITEM_FIRST + 7;


    /**
     * An HItemEvent with this id is sent 
     * to the widget to request an increase of the scrolling position of an 
     * HItemValue by one unit in response to 
     * mouse actions. Use of this constant is implementation-specific.
     */
     public static final int SCROLL_MORE = ITEM_FIRST + 8;

     
    /**
     * An HItemEvent with this id is sent 
     * to the widget to request a decrease of the scrolling position of an 
     * HItemValue by one unit in response to
     * mouse actions. Use of this constant is implementation-specific.
     */
     public static final int SCROLL_LESS = ITEM_FIRST + 9;


    /**
     * An HItemEvent with this id is sent 
     * to the widget to request an increase of the scrolling position of an 
     * HItemValue by one block in response to
     * mouse actions. The value of a block is implementation-dependent. Use of
     * this constant is implementation-specific.
     */
     public static final int SCROLL_PAGE_MORE = ITEM_FIRST + 10;



    /**
     * An HItemEvent with this id is sent 
     * to the widget to request a decrease of the scrolling position of an 
     * HItemValue by one block in response to     
     * mouse actions. The value of a block is implementation-dependent. Use of
     * this constant is implementation-specific.
     */
     public static final int SCROLL_PAGE_LESS = ITEM_FIRST + 11;




    /**
     * An item event with this id indicates that the selection of
     * an HItemValue component has been
     * finally set. This event is sent to or from the component when 
     * the user causes the
     * component to leave selection mode. Note that it is a
     * platform specific implementation option for such components to
     * leave selection mode automatically e.g. when they lose input
     * focus. In such a case the order in which the {@link
     * org.havi.ui.event.HFocusEvent} and {@link
     * org.havi.ui.event.HItemEvent} are sent is platform
     * specific.
     * 
     * @see org.havi.ui.HSelectionInputPreferred#getSelectionMode 
     */
    public static final int ITEM_END_CHANGE = ITEM_FIRST +12;

    /**
     * The last integer id in the range of event ids supported by the
     * {@link org.havi.ui.event.HItemEvent} class.
     */
    public static final int ITEM_LAST = ITEM_FIRST + 12;

    /**
     * Constructs an HItemEvent
     * 
     * @param source The {@link org.havi.ui.HSelectionInputPreferred}
     * component whose value or current item has been modified.
     * @param id The event id of the HItemEvent generated
     * by the {@link org.havi.ui.HSelectionInputPreferred}
     * component. This is the value that will be returned by the
     * event object's <code>getID</code> method.  
     * @param item The item which caused the change, or
     * <code>null</code> if this information is not available. If the
     * event is sent to listeners, this
     * information shall be provided if the event id is one of
     * <code>ITEM_SELECTED</code>, <code>ITEM_CLEARED</code>,
     * <code>ITEM_SET_NEXT</code> or <code>ITEM_SET_PREVIOUS</code>.
     */
    public HItemEvent(org.havi.ui.HSelectionInputPreferred source, int id, Object item)
    {
	super(source, id);
    }

    /**
     * Retrieve the item which caused this {@link
     * org.havi.ui.event.HItemEvent}. This information is
     * not available for the {@link
     * org.havi.ui.event.HItemEvent#ITEM_SELECTION_CLEARED}
     * event id.
     * 
     * @return the item which was selected or cleared, or became the 
     * current item, or 
     * <code>null</code> if this information is not available for this
     * event.  
     */
    public Object getItem() 
    {
	return (null);
    }

}



