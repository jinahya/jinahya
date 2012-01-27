package org.dvb.application.inner;

import java.io.IOException;

import org.havi.ui.HNavigable;
import org.havi.ui.HSound;

import org.havi.ui.event.HFocusEvent;

/**
 * Represents embedding of an inner application within the user interface
 * of a DVB-J application. The inner application shall be started when
 * the object of this class is constructed. The behaviour of instances of
 * this class when an inner application exits is either implementation 
 * or inner application format dependent. <p>
 * When an instance of this class gains input focus,shall gain the input
 * focus. The meaning of this is specific to the format of the inner
 * application concerned. Inner applications are allowed to consume the
 * events VK_UP, VK_DOWN, VK_LEFT and VK_RIGHT. While this is happening,
 * the HNavigable focus management shall be disabled and the actions defined
 * by <code>setFocusTraversal</code> shall not happen. The mechanisms by which
 * inner applications start and stop consuming these events are specific to the
 * content format of the inner application.
 */

public abstract class InnerApplicationContainer extends org.havi.ui.HComponent implements HNavigable {
	/**
	 * Construct an instance of this class with a particular inner
	 * application as its content. This shall not be used by inter-operable
	 * applications.
	 *
	 * @param a the inner application
	 * @exception IOException if an error occurred while reading the
 	 * code or data for the inner application
	 */
	protected InnerApplicationContainer(InnerApplication a)
		throws IOException {}

	/**
	 * Add a listener for events when the inner application exits.
	 *
	 * @param l the listener to be notified when the inner application exits
	 */
	public void addInnerApplicationListener( InnerApplicationListener l){}

	/**
	 * Remove a listener for events when the inner application exits.
	 *
	 * @param l the listener to be removed
	 */
	public void removeInnerApplicationListener( InnerApplicationListener l){}

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
	* @param keyCode The key code of the pressed key. Any numerical
	* keycode is allowed, but the platform may not be able to
	* generate all keycodes. Application authors should only use keys
	* for which <code>HRcCapabilities.isSupported()</code> or
	* <code>HKeyCapabilities.isSupported()</code> returns true.
	* @param target The target {@link org.havi.ui.HNavigable
	* HNavigable} object that should be navigated to. If a target is
	* to be removed from a particular navigation path, then
	* <code>null</code> should be specified. 
	*/
    public void setMove(int keyCode, HNavigable target){}

    /**
	* Provides the {@link org.havi.ui.HNavigable HNavigable} object
	* that is navigated to when a particular key is pressed.
	* 
	* @param keyCode The key code of the pressed key.
	* @return Returns the {@link org.havi.ui.HNavigable HNavigable}
	* object, or if no {@link org.havi.ui.HNavigable HNavigable} is
	* associated with the keyCode then returns <code>null</code>.	
	*/
    public HNavigable getMove(int keyCode){ return null; }

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
	* VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT are used. <p> Note that this
	* API does not prevent the creation of &quot;isolated&quot;
	* {@link org.havi.ui.HNavigable HNavigable} components --- authors
	* should endeavor to avoid confusing the user.
	* 
	* @param up The {@link org.havi.ui.HNavigable HNavigable} component
	* to move to, when the user generates a VK_UP KeyEvent. If there is no {@link
	* org.havi.ui.HNavigable HNavigable} component to move
	* &quot;up&quot; to, then null should be specified.
	* @param down The {@link org.havi.ui.HNavigable HNavigable}
	* component to move to, when the user generates a VK_DOWN KeyEvent. If there
	* is no {@link org.havi.ui.HNavigable HNavigable} component to move
	* &quot;down&quot; to, then null should be specified.
	* @param left The {@link org.havi.ui.HNavigable HNavigable}
	* component to move to, when the user generates a VK_LEFT KeyEvent. If there
	* is no {@link org.havi.ui.HNavigable HNavigable} component to move
	* &quot;left&quot; to, then null should be specified.
	* @param right The {@link org.havi.ui.HNavigable HNavigable}
	* component to move to, when the user generates a VK_RIGHT KeyEvent. If there
	* is no {@link org.havi.ui.HNavigable HNavigable} component to move
	* &quot;right&quot; to, then null should be specified. 
	*/
    public void setFocusTraversal(HNavigable up, HNavigable down, HNavigable left, HNavigable right){}

    /**
	* Indicates if this component has focus. 
	* 
	* @return <code>true</code> if the component has focus, otherwise
	* returns <code>false</code>. 
	*/
    public boolean isSelected() { return false;}

    /**
	* Associate a sound with gaining focus, i.e. when the {@link
	* org.havi.ui.HNavigable HNavigable} receives a
	* <code>java.awt.event.FocusEvent</code> event of type
	* <code>FOCUS_GAINED</code>. This sound will start to be played
	* when an object implementing this interface gains focus. It is
	* not guaranteed to be played to completion. If the object
	* implementing this interface loses focus before the audio
	* completes playing, the audio will be truncated. Applications
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
    public void setGainFocusSound(HSound sound){}

    /**
	* Associate a sound with losing focus, i.e. when the {@link
	* org.havi.ui.HNavigable HNavigable} receives a
	* java.awt.event.FocusEvent event of type FOCUS_LOST. This sound
	* will start to be played when an object implementing this
	* interface loses focus. It is not guaranteed to be played to
	* completion. It is implementation dependent whether and when this
	* sound will be truncated by any gain focus sound played by the next
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
    public void setLoseFocusSound(HSound sound){}

    /**
	* Get the sound associated with the gain focus event.
	* 
	* @return The sound played when the component gains focus. If no
	* sound is associated with gaining focus, then null shall be
	* returned. 
	*/
    public HSound getGainFocusSound(){ return null; }

    /**
	* Get the sound associated with the lost focus event.
	* 
	* @return The sound played when the component loses focus. If no
	* sound is associated with losing focus, then null shall be
	* returned. 
	*/
    public HSound getLoseFocusSound() { return null; }
    /**
     * Retrieve the set of key codes which this component maps to
     * navigation targets. 
     *
     * @return an array of key codes, or <code>null</code> if no
     * navigation targets are set on this component. 
     */
    public int[] getNavigationKeys(){return null;}

     /**
     * Process an {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent} sent to this {@link org.havi.ui.HNavigationInputPreferred
     * HNavigationInputPreferred}.
     * 
     * @param evt the {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent} to process. 
     */
         	public void processHFocusEvent(HFocusEvent evt){};

/**
 * Adds the specified <code>HFocusListener</code> to 
 * receive <code>HFocusEvent</code> events sent from
 * this <code>HNavigable</code>. If the listener has 
 * already been added further calls will add further references to the listener,  
 * which will then receive multiple copies of a single event.
 * 
 * @param l the HFocusListener to add
 */

public void addHFocusListener(org.havi.ui.event.HFocusListener l){}


/**
 * Removes the specified <code>HFocusListener</code>
 * so that it no longer receives
 * <code>HFocusEvent</code> events from this <code>HNavigable</code>. If    
 * the specified listener is not registered, the method has no effect. If 
 * multiple references to a single listener have been registered it should be 
 * noted that this method will only remove one reference per call.
 *
 * @param l the HFocusListener to remove
 */

public void removeHFocusListener(org.havi.ui.event.HFocusListener l){}

}	

