package org.ocap.event;

/**
 * Represents a user event. A user event is defined by a family, a type and
 * either a code or a character. Unless stated otherwise, all constants used
 * in this class are defined in <code>org.ocap.ui.event.OcRcEvent</code>,  
 * <code>java.awt.event.KeyEvent</code> and their parent classes.
 */
public class UserEvent extends org.dvb.event.UserEvent {
    /**
     * Constructor for a new UserEvent object representing a key being 
     * pressed.
     *
     * @param source the <code>EventManager</code> which is the source of 
     *               the event. 
     * @param family the event family. 
     * @param type   the event type. Either one of KEY_PRESSED or 
     *               KEY_RELEASED. 
     * @param code   the event code. One of the constants whose name begins 
     *               in "VK_" defined in java.ui.event.KeyEvent, 
     *               org.havi.ui.event or org.ocap.ui.event.OcRcEvent. 
     * @param modifiers the modifiers active when the key was pressed. These
     *               have the same semantics as modifiers in 
     *               <code>java.awt.event.KeyEvent</code>. 
     * @param when   a long integer that specifies the time the event 
     *               occurred. 
     *
     */
    public UserEvent (Object source, int family, int type, int code, 
                                    int modifiers, long when) {
        super(source, family, type, code, modifiers, when);
    }

    /**
     * Constructor for a new UserEvent object representing a key being typed.
     * This is the combination of a key being pressed and then being released.
     * The type of UserEvents created with this constructor shall be KEY_TYPED.
     * Key combinations which do not result in characters, such as action keys
     * like F1, shall not generate KEY_TYPED events.
     *
     * @param source the <code>EventManager</code> which is the source of the 
     *               event
     * @param family the event family.
     * @param keyChar the character typed
     * @since MHP 1.0.1
     * @param when a long integer that specifies the time the event occurred
     */
    public UserEvent (Object source, int family, char keyChar, long when) {
        super(source, family, keyChar, when);
    }

    
    /**
     * Modifies the event code. For KEY_TYPED events, the code is VK_UNDEFINED.
     *
     * @throws SecurityException if the caller does not have monitorapplication 
     * permission ("filterUserEvents").
     *
     * @since OCAP 1.0
     *
     */
    public void setCode (int code) {
    }

    /**
     * Modifies the character associated with the key in this event. If no 
     * valid Unicode character exists for this key event, keyChar must be 
     * CHAR_UNDEFINED.
     *
     * @throws SecurityException if the caller does not have 
     *                  monitorapplication permission ("filterUserEvents").
     *
     * @since OCAP 1.0
     */
    public void setKeyChar(char keychar) {
    }

}

