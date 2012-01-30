package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
   This interface is implemented by all HAVi UI components which have
   have editable text content (e.g. a text entry control).

   <h3>Event Behavior</h3>

   Subclasses of {@link org.havi.ui.HComponent HComponent} which
   implement {@link org.havi.ui.HTextValue HTextValue} must respond to
   {@link org.havi.ui.event.HFocusEvent HFocusEvent}, {@link 
   org.havi.ui.event.HKeyEvent HKeyEvent}
    and {@link
   org.havi.ui.event.HTextEvent HTextEvent} events.

   <p> 
   Applications should assume that classes which implement {@link
   org.havi.ui.HTextValue HTextValue} can generate events of the
   types {@link org.havi.ui.event.HFocusEvent HFocusEvent} and {@link
   org.havi.ui.event.HTextEvent HTextEvent} in response to other
   types of input event.

   <p> 
   An application may add one or more {@link
   org.havi.ui.event.HTextListener HTextListener} listeners to the
   component. The {@link org.havi.ui.event.HTextListener#textChanged
   textChanged} method of the {@link org.havi.ui.event.HTextListener
   HTextListener} is invoked whenever the text in the {@link
   org.havi.ui.HTextValue HTextValue} is changed, and the {@link
   org.havi.ui.event.HTextListener#caretMoved caretMoved} method of the
   {@link org.havi.ui.event.HTextListener HTextListener} is invoked whenever
   the text caret position is altered.

   <p> 
   HAVi text events are discussed in detail in the {@link
   org.havi.ui.HKeyboardInputPreferred HKeyboardInputPreferred}
   interface description.
   
   <h3>Interaction States</h3>

   The following interaction states are valid for this {@link
   org.havi.ui.HTextValue HTextValue} component:

   <p><ul>
   <li> {@link org.havi.ui.HState#NORMAL_STATE NORMAL_STATE}
   <li> {@link org.havi.ui.HState#FOCUSED_STATE FOCUSED_STATE}
   <li> {@link org.havi.ui.HState#DISABLED_STATE DISABLED_STATE}
   <li> {@link org.havi.ui.HState#DISABLED_FOCUSED_STATE DISABLED_FOCUSED_STATE}
   </ul><p>
   	
   The state machine diagram below shows the valid state 
   transitions for an {@link org.havi.ui.HTextValue 
   HTextValue} component.

   <p><table border>
   <tr><td><img src="../../../images/HxxxValue_state.gif"></td></tr>
   </table><p>

   <h3>Platform Classes</h3>

   The following HAVi platform classes implement or inherit the {@link
   org.havi.ui.HTextValue HTextValue} interface. These classes shall all
   generate both {@link org.havi.ui.event.HFocusEvent HFocusEvent} and
   {@link org.havi.ui.event.HTextEvent HTextEvent} events in
   addition to any other events specified in the respective class
   descriptions.

   <p><ul>
   <li> {@link org.havi.ui.HSinglelineEntry}
   <li> {@link org.havi.ui.HMultilineEntry}
   </ul><p>
   
   @see org.havi.ui.HNavigable
   @see org.havi.ui.HKeyboardInputPreferred
   @see org.havi.ui.event.HTextEvent
   @see org.havi.ui.event.HTextListener

*/

public interface HTextValue
    extends HNavigable, HKeyboardInputPreferred
{
/**
 * Adds the specified {@link org.havi.ui.event.HKeyListener HKeyListener} to  
 * receive {@link org.havi.ui.event.HKeyEvent HKeyEvent} events sent from this 
 * {@link org.havi.ui.HTextValue HTextValue}: If the listener has already been 
 * added further calls will add further references to the listener, which will 
 * then receive multiple copies of a single event.
 * 
 * @param l the HKeyListener to add
 */

public void addHKeyListener(org.havi.ui.event.HKeyListener l);


/**
 * Removes the specified {@link org.havi.ui.event.HKeyListener HKeyListener} so  
 * that it no longer receives {@link org.havi.ui.event.HKeyEvent HKeyEvent} 
 * events from this {@link org.havi.ui.HTextValue HTextValue}. If the specified 
 * listener is not registered, the method has no effect. If multiple references 
 * to a single listener have been registered it should be noted that this method 
 * will only remove one reference per call.
 * 
 * @param l the HKeyListener to remove
 */

public void removeHKeyListener(org.havi.ui.event.HKeyListener l);

/**
 * Adds the specified {@link org.havi.ui.event.HTextListener HTextListener} to  
 * receive {@link org.havi.ui.event.HTextEvent HTextEvent} events sent from this  
 * {@link org.havi.ui.HTextValue HTextValue}: If the listener has already been  
 * added further calls will add further references to the listener, which will 
 * then receive multiple copies of a single event.
 *
 * @param l the HTextListener to add
 */

public void addHTextListener(org.havi.ui.event.HTextListener l);

/**
 * Removes the specified {@link org.havi.ui.event.HTextListener HTextListener}    
 * so that it no longer receives {@link org.havi.ui.event.HTextEvent HTextEvent}   
 * events from this {@link org.havi.ui.HTextValue HTextValue}. If the specified 
 * listener is not registered, the method has no effect. If multiple references 
 * to a single listener have been registered it should be noted that this method 
 * will only remove one reference per call.
 *
 * @param l the HTextListener to remove
 */

public void removeHTextListener(org.havi.ui.event.HTextListener l);


}




