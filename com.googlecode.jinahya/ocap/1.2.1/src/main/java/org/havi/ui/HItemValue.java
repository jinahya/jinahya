package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
 * This interface is implemented by all HAVi UI components which have some form of selectable content (e.g. a list group).  <h3>Event Behavior</h3> Subclasses of    {@link org.havi.ui.HComponent    HComponent}    which implement    {@link org.havi.ui.HItemValue    HItemValue}    must respond to   {@link org.havi.ui.event.HFocusEvent    HFocusEvent}    and    {@link org.havi.ui.event.HItemEvent    HItemEvent}    events. <p>  Applications should assume that classes which implement    {@link org.havi.ui.HItemValue    HItemValue}    can generate events of the types    {@link org.havi.ui.event.HFocusEvent    HFocusEvent}    and    {@link org.havi.ui.event.HItemEvent    HItemEvent}    in response to other types of input event. <p> An application may add one or more    {@link org.havi.ui.event.HItemListener    HItemListener}    listeners to the component. The    {@link org.havi.ui.event.HItemListener#selectionChanged    selectionChanged}    method of the    {@link org.havi.ui.event.HItemListener    HItemListener}    is invoked whenever the selection managed by the    {@link org.havi.ui.HItemValue    HItemValue}    is changed. <p>  HAVi item events are discussed in detail in the    {@link org.havi.ui.HSelectionInputPreferred    HSelectionInputPreferred}   interface description. <h3>Interaction States</h3> The following interaction states are valid for this    {@link org.havi.ui.HItemValue    HItemValue}    component: <p><ul> <li>    {@link org.havi.ui.HState#NORMAL_STATE    NORMAL_STATE}   <li>    {@link org.havi.ui.HState#FOCUSED_STATE    FOCUSED_STATE}   <li>    {@link org.havi.ui.HState#DISABLED_STATE    DISABLED_STATE}   <li>    {@link org.havi.ui.HState#DISABLED_FOCUSED_STATE    DISABLED_FOCUSED_STATE}   </ul><p> The state machine diagram below shows the valid state  transitions for an    {@link org.havi.ui.HItemValue      HItemValue}    component. <p><table border> <tr><td><img src="../../../images/HxxxValue_state.gif"></td></tr> </table><p> <h3>Platform Classes</h3> The following HAVi platform classes implement or inherit the    {@link org.havi.ui.HItemValue    HItemValue}    interface. These classes shall all generate both    {@link org.havi.ui.event.HFocusEvent    HFocusEvent}    and   {@link org.havi.ui.event.HItemEvent    HItemEvent}    events in addition to any other events specified in the respective class descriptions. <p><ul> <li>    {@link org.havi.ui.HListGroup}   </ul><p>
 * @see org.havi.ui.HNavigable
 * @see org.havi.ui.HOrientable
 * @see org.havi.ui.HSelectionInputPreferred
 * @see org.havi.ui.event.HItemEvent
 * @see  org.havi.ui.event.HItemListener
 */

public interface HItemValue 
    extends HNavigable,  HSelectionInputPreferred
{
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
    public void addItemListener(org.havi.ui.event.HItemListener l);

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
    public void removeItemListener(org.havi.ui.event.HItemListener l);
    
    /**
	 * Associate a sound to be played when the selection is modified. The sound is played irrespective of whether an  {@link org.havi.ui.event.HItemEvent  HItemEvent}  is sent to one or more listeners.
	 * @param sound  the sound to be played, when the selection is  modified. If sound content is already set, the original content  is replaced. To remove the sound specify a null  {@link org.havi.ui.HSound  HSound}  .
	 * @uml.property  name="selectionSound"
	 */
    public void setSelectionSound(HSound sound);
        
    /**
     * Get the sound to be played when the selection changes.
     * 
     * @return The sound played when the selection changes 
     */
    public HSound getSelectionSound();
 
}

