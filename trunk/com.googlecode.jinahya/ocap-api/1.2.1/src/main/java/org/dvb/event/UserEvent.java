package org.dvb.event ;

/**
 * Represents a user event. A user event is defined by a family, a type and 
 * either a code or a character. Unless stated otherwise, all constants used
 * in the specification of this class are defined in <code>java.awt.event.KeyEvent</code> and its
 * parent classes.
 */
public class UserEvent extends java.util.EventObject {
    
    /**
     * the family for events that are coming from the remote control or from 
     * the keyboard.
     */
    public static final int UEF_KEY_EVENT=1 ;

    /**
     * Constructor for a new UserEvent object representing a key being pressed.
     *
     * @param source the <code>EventManager</code> which is the source of the event
     * @param family the event family.
     * @param type the event type. Either one of KEY_PRESSED or KEY_RELEASED.
     * @param code the event code. One of the constants whose name begins in 
     * "VK_" defined in java.awt.event.KeyEvent or org.havi.ui.event.HRcEvent.
     * @param modifiers the modifiers active when the key was pressed. These
     * have the same semantics as modifiers in <code>java.awt.event.KeyEvent</code>.
     * @param when a long integer that specifies the time the event occurred
     *
     */
    public UserEvent (Object source, int family, int type, int code, int modifiers, long when) {
	super(source);
	}  
    /**
     * Constructor for a new UserEvent object representing a key being typed.
     * This is the combination of a key being pressed and then being released.
     * The type of UserEvents created with this constructor shall be KEY_TYPED.
     * Key combinations which do not result in characters, such as keys like the
     * red key on a remote control, shall not generate KEY_TYPED events.
     * <code>KEY_TYPED</code> events shall have no modifiers and hence shall not report 
     * any modifiers as being down.
     * @param source the <code>EventManager</code> which is the source of the event
     * @param family the event family.
     * @param keyChar the character typed
     * @param when a long integer that specifies the time the event occurred
     * @since MHP 1.0.1
     */
    public UserEvent (Object source, int family, char keyChar, long when) {
	super(source);
	}  

    /**
     * Returns the event family. Could be UEF_KEY_EVENT.
     *
     * @return an int representing the event family.
     */
    public int getFamily () { return 0;}

    /**
     * Returns the event type. Could be KEY_PRESSED, KEY_RELEASED or KEY_TYPED.
     *
     * @return an int representing the event type.
     */
    public int getType () {return 0;}

    /**
     * Returns the event code. For KEY_TYPED events, the code is VK_UNDEFINED.
     *
     * @return an int representing the event code.
     */
    public int getCode () {return 0;}    

    /**
     * Returns the character associated with the key in this event. If no valid Unicode character 
     * exists for this key event, keyChar is CHAR_UNDEFINED. 
     *
     * @return a character
     * @since MHP 1.0.1
     */
    public char getKeyChar() { return (char)0;}

	/**
	 * Returns the modifiers flag for this event.
         * This method shall return 0 for UserEvents constructed using a constructor which 
	 * does not include an input parameter specifying the modifiers.
	 * @return the modifiers flag for this event
	 * @since MHP 1.0.1
	 */
	public int getModifiers() { return 0;}

	/**
	 * Returns whether or not the Shift modifier is down on this event. 
         * This method shall return false for UserEvents constructed using a constructor 
         * which does not include an input parameter specifying the modifiers.
	 *
	 * @return whether the Shift modifier is down on this event
	 * @since MHP 1.0.1
	 */
	public boolean isShiftDown() { return false;}

	/**
	 * Returns whether or not the Control modifier is down on this event. 
         * This method shall return false for UserEvents constructed using a constructor 
         * which does not include an input parameter specifying the modifiers.
	 * @return whether the Control modifier is down on this event
	 * @since MHP 1.0.1
	 */
	 public boolean isControlDown() { return false;}

	/**
	 * Returns whether or not the Meta modifier is down on this event. 
         * This method shall return false for UserEvents constructed using a constructor 
         * which does not include an input parameter specifying the modifiers.
	 * @return whether the Meta modifier is down on this event
	 * @since MHP 1.0.1
	 */
	public boolean isMetaDown() { return false;}

	/**
	 * Returns whether or not the Alt modifier is down on this event. 
         * This method shall return false for UserEvents constructed using a constructor 
         * which does not include an input parameter specifying the modifiers.
	 * @return whether the Alt modifier is down on this event
	 * @since MHP 1.0.1
	 */
	public boolean isAltDown() { return false;}

	/**
	 * Returns the timestamp of when this event occurred.
	 * @return a long
	 * @since MHP 1.0.2
	 */
	public long getWhen() { return 0; }
}

