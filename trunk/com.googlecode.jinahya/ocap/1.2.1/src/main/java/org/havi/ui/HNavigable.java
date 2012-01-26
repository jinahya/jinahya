package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Dimension;
import java.awt.Graphics;



/**
 * This interface is implemented by all HAVi UI components that can be navigated to by the user (i.e. components which can gain focus).  <h3>Event Behavior</h3> <p>  Subclasses of    {@link org.havi.ui.HComponent    HComponent}    which implement    {@link org.havi.ui.HNavigable    HNavigable}    must respond to   {@link org.havi.ui.event.HFocusEvent    HFocusEvent}   events.  <p>  Applications should assume that classes which implement    {@link org.havi.ui.HNavigable    HNavigable}    can generate events of the type    {@link org.havi.ui.event.HFocusEvent    HFocusEvent}    in response to other types of input event. <p>  An application may add one or more    {@link org.havi.ui.event.HFocusListener    HFocusListener}    listeners to the component. The    {@link org.havi.ui.event.HFocusListener#focusGained    focusGained}    and    {@link org.havi.ui.event.HFocusListener#focusLost    focusLost}    methods of the    {@link org.havi.ui.event.HFocusListener    HFocusListener}    are invoked whenever the    {@link org.havi.ui.HNavigable    HNavigable}    gains or loses focus.  <p> An    {@link org.havi.ui.HNavigable    HNavigable}    has an arbitrary focus traversal table associated with it (see    {@link org.havi.ui.HNavigable#setMove    setMove}    and    {@link org.havi.ui.HNavigable#getMove    getMove}   ).  This mechanism allows the four-way focus behavior of a set of components to be set (see   {@link org.havi.ui.HNavigable#setFocusTraversal    setFocusTraversal}   ,   {@link org.havi.ui.HNavigable#setMove    setMove}    and    {@link org.havi.ui.HNavigable#getMove    getMove}   ). <p>  HAVi focus events are discussed in detail in the    {@link org.havi.ui.HNavigationInputPreferred    HNavigationInputPreferred}   interface description. <h3>Interaction States</h3> <p>   The following interaction states are valid for this    {@link org.havi.ui.HNavigable    HNavigable}    component: <p><ul> <li>    {@link org.havi.ui.HState#NORMAL_STATE    NORMAL_STATE}   <li>    {@link org.havi.ui.HState#FOCUSED_STATE    FOCUSED_STATE}   <li>    {@link org.havi.ui.HState#DISABLED_STATE    DISABLED_STATE}   <li>    {@link org.havi.ui.HState#DISABLED_FOCUSED_STATE    DISABLED_FOCUSED_STATE}   </ul><p> The state machine diagram below shows the valid state  transitions for an    {@link org.havi.ui.HNavigable      HNavigable}    component. <p><table border> <tr><td><img src="../../../images/HNavigable_state.gif"></td></tr> </table><p> <h3>Platform Classes</h3> The following HAVi platform classes implement or inherit the    {@link org.havi.ui.HNavigable    HNavigable}    interface. These classes shall all generate    {@link org.havi.ui.event.HFocusEvent    HFocusEvent}    events in addition to any other events specified in the respective class descriptions. <ul> <li>    {@link org.havi.ui.HAnimation    HAnimation}   <li>    {@link org.havi.ui.HIcon    HIcon}   <li>    {@link org.havi.ui.HText    HText}   <li>    {@link org.havi.ui.HRange    HRange}   <li>    {@link org.havi.ui.HGraphicButton    HGraphicButton}   <li>    {@link org.havi.ui.HTextButton    HTextButton}   <li>    {@link org.havi.ui.HToggleButton    HToggleButton}   <li>    {@link org.havi.ui.HListGroup    HListGroup}   <li>    {@link org.havi.ui.HSinglelineEntry    HSinglelineEntry}   <li>    {@link org.havi.ui.HMultilineEntry    HMultilineEntry}   <li>    {@link org.havi.ui.HRangeValue    HRangeValue}   </ul>
 * @see org.havi.ui.HNavigationInputPreferred    
 * @see org.havi.ui.event.HFocusEvent
 * @see  org.havi.ui.event.HFocusListener
 */

public interface HNavigable
    extends HNavigationInputPreferred
{  
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
    public void setMove(int keyCode, HNavigable target);

    /**
     * Provides the {@link org.havi.ui.HNavigable HNavigable} object
     * that is navigated to when a particular key is pressed.
     * 
     * @param keyCode The key code of the pressed key.
     * @return Returns the {@link org.havi.ui.HNavigable HNavigable}
     * object or <code>null</code> if no {@link org.havi.ui.HNavigable
     * HNavigable} is associated with the keyCode.
     */
    public HNavigable getMove(int keyCode);

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
    public void setFocusTraversal(HNavigable up, HNavigable down, HNavigable left, HNavigable right);

    /**
     * Indicates if this component has focus. 
     * 
     * @return <code>true</code> if the component has focus, otherwise
     * returns <code>false</code>.  
     */
    public boolean isSelected();

    /**
	 * Associate a sound with gaining focus, i.e. when the  {@link org.havi.ui.HNavigable  HNavigable}  receives a {@link org.havi.ui.event.HFocusEvent  HFocusEvent}  event of type <code>FOCUS_GAINED</code>. This sound will start to be played when an object implementing this interface gains focus. It is not guaranteed to be played to completion. If the object implementing this interface loses focus before the audio completes playing, the audio will be truncated.  Applications wishing to ensure the audio is always played to completion must implement special logic to slow down the focus transitions. <p> By default, an  {@link org.havi.ui.HNavigable  HNavigable}  object does not have any gain focus sound associated with it. <p> Note that the ordering of playing sounds is dependent on the order of the focus lost, gained events.
	 * @param sound  the sound to be played, when the component gains  focus. If sound content is already set, the original content is  replaced. To remove the sound specify a null  {@link org.havi.ui.HSound  HSound}  .
	 * @uml.property  name="gainFocusSound"
	 */
    public void setGainFocusSound(HSound sound);

    /**
	 * Associate a sound with losing focus, i.e. when the  {@link org.havi.ui.HNavigable  HNavigable}  receives a {@link org.havi.ui.event.HFocusEvent  HFocusEvent}  event of type FOCUS_LOST. This sound will start to be played when an object  implementing this interface loses focus. It is not guaranteed to be  played to completion. It is implementation dependent whether and when  this sound will be truncated by any gain focus sound played by the next object to gain focus. <p> By default, an  {@link org.havi.ui.HNavigable  HNavigable}  object does not have any lose focus sound associated with it. <p> Note that the ordering of playing sounds is dependent on the order of the focus lost, gained events.
	 * @param sound  the sound to be played, when the component loses  focus. If sound content is already set, the original content is  replaced. To remove the sound specify a null  {@link org.havi.ui.HSound  HSound}  .
	 * @uml.property  name="loseFocusSound"
	 */
    public void setLoseFocusSound(HSound sound);

    /**
     * Get the sound associated with the gain focus event.
     * 
     * @return The sound played when the component gains focus. If no
     * sound is associated with gaining focus, then null shall be
     * returned.  
     */
    public HSound getGainFocusSound();

    /**
     * Get the sound associated with the lost focus event.
     * 
     * @return The sound played when the component loses focus. If no
     * sound is associated with losing focus, then null shall be
     * returned.  
     */
    public HSound getLoseFocusSound();

/**
 * Adds the specified {@link org.havi.ui.event.HFocusListener HFocusListener} to 
 * receive {@link org.havi.ui.event.HFocusEvent HFocusEvent} events sent from 
 * this {@link org.havi.ui.HNavigable HNavigable}: If the listener has 
 * already been added further calls will add further references to the listener,  
 * which will then receive  multiple copies of a single event.
 * 
 * @param l the HFocusListener to add
 */

public void addHFocusListener(org.havi.ui.event.HFocusListener l);


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

public void removeHFocusListener(org.havi.ui.event.HFocusListener l);




}


