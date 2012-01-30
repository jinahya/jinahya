package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Graphics;



/**
 * This interface is implemented for all user interface components that can be actioned such that they &quot;toggle&quot; on and off and maintain the chosen state. <h3>Event Behavior</h3> Subclasses of    {@link org.havi.ui.HComponent    HComponent}    which implement    {@link org.havi.ui.HSwitchable    HSwitchable}    must respond to   {@link org.havi.ui.event.HFocusEvent    HFocusEvent}    and    {@link org.havi.ui.event.HActionEvent    HActionEvent}    events. <p>  Applications should assume that classes which implement    {@link org.havi.ui.HSwitchable    HSwitchable}    can generate events of the types    {@link org.havi.ui.event.HFocusEvent    HFocusEvent}    and    {@link org.havi.ui.event.HActionEvent    HActionEvent}    in response to other types of input event. <p> An application may add one or more    {@link org.havi.ui.event.HActionListener    HActionListener}    listeners to the component. The    {@link org.havi.ui.event.HActionListener#actionPerformed    actionPerformed}   method of the    {@link org.havi.ui.event.HActionListener    HActionListener}    is invoked whenever the    {@link org.havi.ui.HSwitchable    HSwitchable}    is actioned. <p>  HAVi action events are discussed in detail in the    {@link org.havi.ui.HActionInputPreferred    HActionInputPreferred}   interface description. <h3>Interaction States</h3> The following interaction states are valid for this    {@link org.havi.ui.HSwitchable    HSwitchable}    component: <p><ul> <li>    {@link org.havi.ui.HState#NORMAL_STATE    NORMAL_STATE}   <li>    {@link org.havi.ui.HState#FOCUSED_STATE    FOCUSED_STATE}   <li>    {@link org.havi.ui.HState#ACTIONED_STATE    ACTIONED_STATE}   	 <li>    {@link org.havi.ui.HState#ACTIONED_FOCUSED_STATE    ACTIONED_FOCUSED_STATE}   	 <li>    {@link org.havi.ui.HState#DISABLED_STATE    DISABLED_STATE}   <li>    {@link org.havi.ui.HState#DISABLED_FOCUSED_STATE    DISABLED_FOCUSED_STATE}   <li>    {@link org.havi.ui.HState#DISABLED_ACTIONED_STATE    DISABLED_ACTIONED_STATE}   	 <li>    {@link org.havi.ui.HState#DISABLED_ACTIONED_FOCUSED_STATE    DISABLED_ACTIONED_FOCUSED_STATE}   	 </ul><p> The state machine diagram below shows the valid state  transitions for an    {@link org.havi.ui.HSwitchable      HSwitchable}    component. <p><table border> <tr><td><img src="../../../images/HSwitchable_state.gif"></td></tr> </table><p> Unlike    {@link org.havi.ui.HActionable    HActionable}    components there are no automatic transitions to other states. Actioned states (i.e. those with the    {@link org.havi.ui.HState#ACTIONED_STATE_BIT    ACTIONED_STATE_BIT}    bit set may persist after any registered    {@link org.havi.ui.event.HActionListener    HActionListener}    listeners have been called, until a further    {@link org.havi.ui.event.HActionEvent    HActionEvent}   is received. <h3>Platform Classes</h3> The following HAVi platform classes implement or inherit the    {@link org.havi.ui.HSwitchable    HSwitchable}    interface. These classes shall all generate both    {@link org.havi.ui.event.HFocusEvent    HFocusEvent}    and   {@link org.havi.ui.event.HActionEvent    HActionEvent}    events in addition to any other events specified in the respective class descriptions. <p><ul> <li>    {@link org.havi.ui.HToggleButton    HToggleButton}   </ul> 
 * @see org.havi.ui.HNavigable
 * @see org.havi.ui.HActionable
 * @see org.havi.ui.HActionInputPreferred    
 * @see org.havi.ui.event.HActionEvent
 * @see  org.havi.ui.event.HActionListener
 */

public interface HSwitchable 
    extends HActionable
{
    /**
	 * Returns the current switchable state of this  {@link org.havi.ui.HSwitchable  HSwitchable} .
	 * @return  the current switchable state of this  {@link org.havi.ui.HSwitchable  HSwitchable}  .
	 * @uml.property  name="switchableState"
	 */
    public boolean getSwitchableState();
    
    /**
	 * Sets the current state of the button. Note that ActionListeners are only called when an ACTION_PERFORMED event is received, or if they are called directly, e.g. via <code>processActionEvent</code>, they are not called by  {@link org.havi.ui.HToggleButton#setSwitchableState  setSwitchableState} .
	 * @uml.property  name="switchableState"
	 */
    public void setSwitchableState(boolean state);
    
    /**
	 * Associate a sound to be played when the interaction state of the  {@link org.havi.ui.HSwitchable  HSwitchable}  makes the following transitions: <p><ul> <li>  {@link org.havi.ui.HState#ACTIONED_STATE  ACTIONED_STATE}  to  {@link org.havi.ui.HState#NORMAL_STATE  NORMAL_STATE} <li>  {@link org.havi.ui.HState#ACTIONED_FOCUSED_STATE  ACTIONED_FOCUSED_STATE}  to  {@link org.havi.ui.HState#FOCUSED_STATE  FOCUSED_STATE} </ul><p>
	 * @param sound  a sound to be played when the  {@link org.havi.ui.HSwitchable  HSwitchable}  transitions from an  actioned state.  If sound content is already set, the original  content is replaced. To remove the sound specify a null  {@link org.havi.ui.HSound  HSound}  .
	 * @uml.property  name="unsetActionSound"
	 */
    public void setUnsetActionSound(HSound sound);
    
    /**       
     * Get the sound to be played when the interaction state of
     * the {@link org.havi.ui.HSwitchable HSwitchable} makes the
     * following transitions:
     * <p><ul>
     * <li> {@link org.havi.ui.HState#ACTIONED_STATE ACTIONED_STATE} to {@link org.havi.ui.HState#NORMAL_STATE NORMAL_STATE}
     * <li> {@link org.havi.ui.HState#ACTIONED_FOCUSED_STATE ACTIONED_FOCUSED_STATE} to {@link org.havi.ui.HState#FOCUSED_STATE FOCUSED_STATE}
     * </ul><p>
     * 
     * @return the sound to be played when the {@link
     * org.havi.ui.HSwitchable HSwitchable} transitions from an
     * actioned state.
     */
    public HSound getUnsetActionSound();
}

