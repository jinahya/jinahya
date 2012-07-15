package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


import org.havi.ui.HTextValue;

/**
   An {@link org.havi.ui.event.HTextEvent HTextEvent} event is used to
   interact with a component implementing the {@link
   org.havi.ui.HKeyboardInputPreferred HKeyboardInputPreferred}
   interface as follows:

   <p><ul>

   <li> An {@link org.havi.ui.event.HTextEvent HTextEvent} event may be sent
   from the HAVi system to the component to cause a change to the
   caret position or editable mode of the component as a result of
   user interaction. For example, a platform which lacks suitable
   caret positioning or mode switching keys may choose to generate
   this using a virtual keyboard user interface.

   <li> An {@link org.havi.ui.event.HTextEvent HTextEvent} event is sent
   from the component to all registered {@link
   org.havi.ui.event.HTextListener HTextListeners} when a change to
   the text content, caret position or editable mode of the component
   occurs.
   
   </ul><p>

   All interoperable HAVi components which expect to receive {@link
   org.havi.ui.event.HTextEvent HTextEvent} events should implement the
   {@link org.havi.ui.HKeyboardInputPreferred
   HKeyboardInputPreferred} interface.

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

 */
public class HTextEvent 
    extends java.awt.AWTEvent
{

    /**
     * The first integer id in the range of event ids supported by
     * the {@link org.havi.ui.event.HTextEvent
     * HTextEvent} class.  
     */
    public static final int TEXT_FIRST = HItemEvent.ITEM_LAST + 1;
    
    /**
     * The last integer id in the range of event ids supported by
     * the {@link org.havi.ui.event.HTextEvent
     * HTextEvent} class.  
     */
    public static final int TEXT_LAST = TEXT_FIRST + 9;
    
    /**
     * A text event with this id indicates that the textual content of
     * an {@link org.havi.ui.HTextValue HTextValue} component may be
     * about to change. This event is sent to or from the component 
     * when the user causes the
     * component to enter its editable mode. Note that it is a
     * platform specific implementation option for such components to
     * enter editable mode automatically e.g. when they receive input
     * focus. In such a case the order in which the {@link
     * org.havi.ui.event.HFocusEvent HFocusEvent} and {@link
     * org.havi.ui.event.HTextEvent HTextEvent} are sent is platform
     * specific.
     * 
     * @see HTextValue#getEditMode 
     */
    public static final int TEXT_START_CHANGE = TEXT_FIRST;

    /**
     * A text event with this id is sent from the component whenever the
     * textual content of an {@link org.havi.ui.HTextValue
     * HTextValue} component is changed.
     */
    public static final int TEXT_CHANGE = TEXT_FIRST + 1;

    /**
     * A text event with this id is sent from the component whenever the
     * caret position of an {@link org.havi.ui.HTextValue
     * HTextValue} component is changed. This event will be sent only if the
     * caret position changed in a manner not notified by the
     {@link org.havi.ui.event.HTextEvent#CARET_NEXT_CHAR
     * CARET_NEXT_CHAR}, {@link org.havi.ui.event.HTextEvent#CARET_NEXT_LINE
     * CARET_NEXT_LINE}, {@link org.havi.ui.event.HTextEvent#CARET_PREV_CHAR
     * CARET_PREV_CHAR}, {@link org.havi.ui.event.HTextEvent#CARET_PREV_LINE
     * CARET_PREV_LINE}, {@link org.havi.ui.event.HTextEvent#CARET_NEXT_PAGE
     * CARET_NEXT_PAGE}, or {@link org.havi.ui.event.HTextEvent#CARET_PREV_PAGE
     * CARET_PREV_PAGE} events.
     */
    public static final int TEXT_CARET_CHANGE = TEXT_FIRST + 2;

    /**
     * A text event with this id indicates that the textual content of
     * an {@link org.havi.ui.HTextValue HTextValue} component has been
     * finally set. This event is sent to or from the component when the user causes the
     * component to leave its editable mode. Note that it is a
     * platform specific implementation option for such components to
     * leave editable mode automatically e.g. when they lose input
     * focus. In such a case the order in which the {@link
     * org.havi.ui.event.HFocusEvent HFocusEvent} and {@link
     * org.havi.ui.event.HTextEvent HTextEvent} are sent is platform
     * specific.
     * 
     * @see HTextValue#getEditMode 
     */
    public static final int TEXT_END_CHANGE = TEXT_FIRST + 3;
    
    
   /**
     * When a text event with this id is sent to a 
     * {@link org.havi.ui.HTextValue HTextValue} component, then its
     * caret position should move one character forward. If such
     * an event is sent from a component to {@link HTextListener
     * HTextListeners}, then it was moved.
     */
     public static final int CARET_NEXT_CHAR = TEXT_FIRST + 4;

    /**
     * When a text event with this id is sent to a 
     * {@link org.havi.ui.HTextValue HTextValue} component, then its
     * caret position should move down one line. If such
     * an event is sent from a component to {@link HTextListener
     * HTextListeners}, then it was moved. It is widget specific, if
     * the caret remains at the same column or at an approximate
     * horizontal pixel position for non-fixed-width fonts.
     */
     public static final int CARET_NEXT_LINE = TEXT_FIRST + 5;

    /**
     * When a text event with this id is sent to a 
     * {@link org.havi.ui.HTextValue HTextValue} component, then its
     * caret position should move one character backward. If such
     * an event is sent from a component to {@link HTextListener
     * HTextListeners}, then it was moved.
     */
     public static final int CARET_PREV_CHAR = TEXT_FIRST + 6;

    /**
     * When a text event with this id is sent to a 
     * {@link org.havi.ui.HTextValue HTextValue} component, then its
     * caret position should move up one line. If such
     * an event is sent from a component to {@link HTextListener
     * HTextListeners}, then it was moved. It is widget specific, if
     * the caret remains at the same column or at an approximate
     * horizontal pixel position for non-fixed-width fonts.
     */
     public static final int CARET_PREV_LINE = TEXT_FIRST + 7;    
    
    
/**
 * When a text event with this id is sent to a
 * {@link org.havi.ui.HTextValue HTextValue} component, then its
 * caret position should move down to the last possible line in
 * the visible window. If the caret position is already on the
 * last visible line then the caret should move down so that the
 * last visible line scrolls up to the top of the visible window. If
 * such an event is sent from a component to {@link HTextListener
 * HTextListeners}, then it was moved. It is widget specific, if
 * the caret remains at the same column or at an approximate
 * horizontal pixel position for non-fixed-width fonts.
 */
public static final int CARET_NEXT_PAGE = TEXT_FIRST + 8;

/**
 * When a text event with this id is sent to a
 * {@link org.havi.ui.HTextValue HTextValue} component, then its
 * caret position should move up to the first possible line in
 * the visible window. If the caret position is already on the
 * first visible line then the caret should move down so that the
 * first visible line scrolls down to the bottom of the visible
 * window. If such an event is sent from a component to
 * {@link HTextListener HTextListeners}, then it was moved. It is
 * widget specific, if the caret remains at the same column or at
 * an approximate horizontal pixel position for non-fixed-width
 * fonts.
 */
public static final int CARET_PREV_PAGE = TEXT_FIRST + 9;




    

    /**
     * Constructs an {@link org.havi.ui.event.HTextEvent
     * HTextEvent}.
     * 
     * @param source The {@link org.havi.ui.HKeyboardInputPreferred
     * HKeyboardInputPreferred} component whose value has been modified.
     * @param id The event id of the {@link
     * org.havi.ui.event.HTextEvent HTextEvent} generated
     * by the 
     * {@link org.havi.ui.HKeyboardInputPreferred HKeyboardInputPreferred}
     * component. This is the value that will be returned by the
     * event object's <code>getID</code> method.  
     */
    public HTextEvent(org.havi.ui.HKeyboardInputPreferred source, int id)
    {
	super(source, id);
    }

}


