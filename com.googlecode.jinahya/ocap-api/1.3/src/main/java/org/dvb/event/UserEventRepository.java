package org.dvb.event ;

/**
 * The application will use this class to define the events that it wants to 
 * receive. Events that are able to be put in the repository are defined in
 * the UserEvent class.<p>Where a repository includes a <code>KEY_PRESSED</code> type event 
 * without the <code>KEY_RELEASED</code> type event for the same key code or vice versa then 
 * exclusive reservations shall be made for both event types but only the one 
 * requested shall be received by the listener. Where a repository includes
 * a <code>KEY_TYPED</code> event without the corresponding <code>KEY_PRESSED</code> and 
 * <code>KEY_RELEASED</code> 
 * events (excluding <code>KEY_PRESSED</code> or <code>KEY_RELEASED</code> events for modifiers),
 * when an exclusive reservation is requested, it shall also be made for
 * those corresponding <code>KEY_PRESSED</code> and <code>KEY_RELEASED</code> events but only the
 * requested event shall be received by the listener.<p>
 * Repositories do not keep a count of the number of times a particular user 
 * event is added or removed. Repeatedly adding an event to a repository has no 
 * effect. Removing an event removes it regardless of the number of times it has 
 * been added. For example, org.dvb.event.UserEventRepository.addUserEvent(UserEvent 
 * event) does nothing in case that the event is already in the repository. 
 * Events are considered to be already in the repository if an event with the same 
 * triplet of family, type and code is already in the repository.<p>
 * If an application loses exclusive access to a repository, it shall lose access to all 
 * events defined in that repository.
 * Repositories are resolved when they are passed into the methods of EventManager.
 * Adding or removing events from the repository after those method calls does not 
 * affect the subscription to those events.
 * <p>Unless stated otherwise, all constants used in the specification of this class are defined 
 * in <code>java.awt.event.KeyEvent</code> and its parent classes and not in this class.
 * @see UserEvent
 */
public class UserEventRepository extends RepositoryDescriptor {

    /**
     * The method to construct a new UserEventRepository.
     *
     * @param name the name of the repository.
     */
    public UserEventRepository (String name) {
    }
    
    /**
     * Adds the given user event to the repository.
     * The values of the modifiers (if any) in the <code>UserEvent</code> shall be ignored by 
     * the MHP terminal.
     * The value of the source used to construct the specified <code>UserEvent</code> shall 
     * be ignored by the MHP terminal when the <code>UserEventRepository</code> is used to 
     * specify events which an application wants to receive.
     * @param event the user event to be added in the repository.
     */ 
    public void addUserEvent (UserEvent event) {}
    
    /**
     * Returns the list of the user events that are in the repository.
     *
     * @return an array which contains the user events that are in the 
     * repository.
     */
    public UserEvent[] getUserEvent () { return null;}

    /**
     * Remove a user event from the repository. Removing a user event
     * which is not in the repository shall have no effect.
     *
     * @param event the event to be removed from the repository.
     */
    public void removeUserEvent (UserEvent event) {}
    
    /**
     * Adds the specified keycode to the repository. Keycodes added in this way shall 
     * be listed in the list of user events returned by the <code>getUserEvent</code> method.
     * If a key is already in the repository, this method has no effect.
     * After calling this method, the keycode shall be present for both
     * the <code>KEY_PRESSED</code> and <code>KEY_RELEASED</code> modes.
     * @param keycode the key code.
     */
    public void addKey (int keycode) {}
   
    /**
     * The method to remove a key from the repository. Removing a key which 
     * is not in the repository has no effect.
     * After calling this method, the keycode shall not be present for both
     * the <code>KEY_PRESSED</code> and <code>KEY_RELEASED</code> modes.
     *
     * @param keycode the key code.
     */
    public void removeKey (int keycode) {}

	/** 
	 * Adds the key codes for the numeric keys (VK_0, VK_1, VK_2, VK_3,
	 * VK_4, VK_5, VK_6, VK_7, VK_8, VK_9). Any key codes already in the 
	 * repository will not be added again. 
         * After calling this method, the keycodes shall be present for both
         * the <code>KEY_PRESSED</code> and <code>KEY_RELEASED</code> modes.
	 */
	public void addAllNumericKeys() {}

	/**
	 * Adds the key codes for the colour keys (VK_COLORED_KEY_0,
	 * VK_COLORED_KEY_1, VK_COLORED_KEY_2, VK_COLORED_KEY_3). Any key
	 * codes already in the repository will not be added again.
         * After calling this method, the keycodes shall be present for both
         * the <code>KEY_PRESSED</code> and <code>KEY_RELEASED</code> modes.
	 */
	public void addAllColourKeys(){}

	/**
	 * Adds the key codes for the arrow keys (VK_LEFT, VK_RIGHT, VK_UP, 
	 * VK_DOWN). Any key codes already in the repository will not be
	 * added again. 
         * After calling this method, the keycodes shall be present for both
         * the <code>KEY_PRESSED</code> and <code>KEY_RELEASED</code> modes.
	 */
	public void addAllArrowKeys(){}

	/** 
	 * Remove the key codes for the numeric keys (VK_0, VK_1, VK_2, VK_3,
	 * VK_4, VK_5, VK_6, VK_7, VK_8, VK_9). Key codes from this set which
	 * are not present in the repository will be ignored.
         * After calling this method, the keycodes shall not be present for both
         * the <code>KEY_PRESSED</code> and <code>KEY_RELEASED</code> modes.
	 */
	public void removeAllNumericKeys(){}

	/**
	 * Removes the key codes for the colour keys (VK_COLORED_KEY_0,
	 * VK_COLORED_KEY_1, VK_COLORED_KEY_2, VK_COLORED_KEY_3). Key codes from 
	 * this set which are not present in the repository will be ignored.
         * After calling this method, the keycodes shall not be present for both
         * the <code>KEY_PRESSED</code> and <code>KEY_RELEASED</code> modes.
	 */
	public void removeAllColourKeys(){}

	/**
	 * Removes the key codes for the arrow keys (VK_LEFT, VK_RIGHT, VK_UP, 
	 * VK_DOWN). Key codes from this set which are not present in the 
	 * repository will be ignored.
         * After calling this method, the keycodes shall not be present for both
         * the <code>KEY_PRESSED</code> and <code>KEY_RELEASED</code> modes.
	 */
	public void removeAllArrowKeys(){}

	/**
	 * Returns the name of the current repository as passed to the constructor.
	 * @return a String with the name of the repository.
	 */
	public java.lang.String getName() {return null;}

}










