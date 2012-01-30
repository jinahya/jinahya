/*
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.awt;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.Serializable;

/** 
 * An <code>AWTKeyStroke</code> represents a key action on the
 * keyboard, or equivalent input device. <code>AWTKeyStroke</code>s
 * can correspond to only a press or release of a
 * particular key, just as <code>KEY_PRESSED</code> and
 * <code>KEY_RELEASED</code> <code>KeyEvent</code>s do;
 * alternately, they can correspond to typing a specific Java character, just
 * as <code>KEY_TYPED</code> <code>KeyEvent</code>s do.
 * In all cases, <code>AWTKeyStroke</code>s can specify modifiers
 * (alt, shift, control, meta, or a combination thereof) which must be present
 * during the action for an exact match.
 * <p>
 * <code>AWTKeyStrokes</code> are immutable, and are intended
 * to be unique. Client code should never create an
 * <code>AWTKeyStroke</code> on its own, but should instead use
 * a variant of <code>getAWTKeyStroke</code>. Client use of these factory
 * methods allows the <code>AWTKeyStroke</code> implementation
 * to cache and share instances efficiently.
 *
 * @see #getAWTKeyStroke
 *
 * @version 1.14, 01/23/03
 * @author Arnaud Weber
 * @author David Mendenhall
 * @since 1.4
 */
public class AWTKeyStroke implements Serializable
{
  // PBP/PP 1.1
    static final long serialVersionUID = -6430539691155161871L;

    private char keyChar;

    private int keyCode;

    private int modifiers;

    private boolean onKeyRelease;

    /** 
     * Constructs an <code>AWTKeyStroke</code> with default values.
     * The default values used are:
     * <table border="1" summary="AWTKeyStroke default values">
     * <tr><th>Property</th><th>Default Value</th></tr>
     * <tr>
     *    <td>Key Char</td>
     *    <td><code>KeyEvent.CHAR_UNDEFINED</code></td>
     * </tr>
     * <tr>
     *    <td>Key Code</td>
     *    <td><code>KeyEvent.VK_UNDEFINED</code></td>
     * </tr>
     * <tr>
     *    <td>Modifiers</td>
     *    <td>none</td>
     * </tr>
     * <tr>
     *    <td>On key release?</td>
     *    <td><code>false</code></td>
     * </tr>
     * </table>
     * 
     * <code>AWTKeyStroke</code>s should not be constructed
     * by client code. Use a variant of <code>getAWTKeyStroke</code>
     * instead.
     *
     * @see #getAWTKeyStroke
     */

    // PBP/PP 6218482
    // Make package private
    // protected AWTKeyStroke() { }
     AWTKeyStroke() { }

    /** 
     * Constructs an <code>AWTKeyStroke</code> with the specified
     * values. <code>AWTKeyStroke</code>s should not be constructed
     * by client code. Use a variant of <code>getAWTKeyStroke</code>
     * instead.
     *
     * @param keyChar the character value for a keyboard key
     * @param keyCode the key code for this <code>AWTKeyStroke</code>
     * @param modifiers a bitwise-ored combination of any modifiers
     * @param onKeyRelease <code>true</code> if this
     *        <code>AWTKeyStroke</code> corresponds
     *        to a key release; <code>false</code> otherwise
     * @see #getAWTKeyStroke
     */

    // PBP/PP 6218482
    // Make package private
//      protected AWTKeyStroke(char keyChar, int keyCode, int modifiers, boolean
//          onKeyRelease)
     AWTKeyStroke(char keyChar, int keyCode, int modifiers, boolean
         onKeyRelease)
    { }

    /** 
     * Registers a new class which the factory methods in
     * <code>AWTKeyStroke</code> will use when generating new
     * instances of <code>AWTKeyStroke</code>s. After invoking this
     * method, the factory methods will return instances of the specified
     * Class. The specified Class must be either <code>AWTKeyStroke</code>
     * or derived from <code>AWTKeyStroke</code>, and it must have a
     * no-arg constructor. The constructor can be of any accessibility,
     * including <code>private</code>. This operation
     * flushes the current <code>AWTKeyStroke</code> cache.
     *
     * @param subclass the new Class of which the factory methods should create
     *        instances
     * @throws IllegalArgumentException if subclass is <code>null</code>,
     *         or if subclass does not have a no-arg constructor
     * @throws ClassCastException if subclass is not
     *         <code>AWTKeyStroke</code>, or a class derived from
     *         <code>AWTKeyStroke</code>
     */
    // PBP/PP 6218482
    // protected static void registerSubclass(Class subclass) { }

    /** 
     * Returns a shared instance of an <code>AWTKeyStroke</code> 
     * that represents a <code>KEY_TYPED</code> event for the 
     * specified character.
     *
     * @param keyChar the character value for a keyboard key
     * @return an <code>AWTKeyStroke</code> object for that key
     */
    public static AWTKeyStroke getAWTKeyStroke(char keyChar) { return null; }

    /** 
     * Returns a shared instance of an <code>AWTKeyStroke</code>,
     * given a Character object and a set of modifiers. Note
     * that the first parameter is of type Character rather than
     * char. This is to avoid inadvertent clashes with
     * calls to <code>getAWTKeyStroke(int keyCode, int modifiers)</code>.
     *
     * The modifiers consist of any combination of:<ul>
     * <li>java.awt.event.InputEvent.SHIFT_DOWN_MASK 
     * <li>java.awt.event.InputEvent.CTRL_DOWN_MASK
     * <li>java.awt.event.InputEvent.META_DOWN_MASK
     * <li>java.awt.event.InputEvent.ALT_DOWN_MASK
     * <li>java.awt.event.InputEvent.ALT_GRAPH_DOWN_MASK
     * <li>java.awt.event.InputEvent.BUTTON1_DOWN_MASK 
     * <li>java.awt.event.InputEvent.BUTTON2_DOWN_MASK 
     * <li>java.awt.event.InputEvent.BUTTON3_DOWN_MASK
     * </ul>
     * The old modifiers <ul>
     * <li>java.awt.event.InputEvent.SHIFT_MASK 
     * <li>java.awt.event.InputEvent.CTRL_MASK 
     * <li>java.awt.event.InputEvent.META_MASK 
     * <li>java.awt.event.InputEvent.ALT_MASK
     * <li>java.awt.event.InputEvent.ALT_GRAPH_MASK
     * </ul> 
     * also can be used, but they are mapped to _DOWN_ modifiers.
     *
     * Since these numbers are all different powers of two, any combination of
     * them is an integer in which each bit represents a different modifier
     * key. Use 0 to specify no modifiers.
     *
     * @param keyChar the Character object for a keyboard character
     * @param modifiers a bitwise-ored combination of any modifiers
     * @return an <code>AWTKeyStroke</code> object for that key
     * @throws IllegalArgumentException if <code>keyChar</code> is
     *       <code>null</code>
     *
     * @see java.awt.event.InputEvent
     */
    public static AWTKeyStroke getAWTKeyStroke(Character keyChar, int modifiers)
    { return null; }

    /** 
     * Returns a shared instance of an <code>AWTKeyStroke</code>,
     * given a numeric key code and a set of modifiers, specifying
     * whether the key is activated when it is pressed or released.
     * <p>
     * The "virtual key" constants defined in
     * <code>java.awt.event.KeyEvent</code> can be 
     * used to specify the key code. For example:<ul>
     * <li><code>java.awt.event.KeyEvent.VK_ENTER</code> 
     * <li><code>java.awt.event.KeyEvent.VK_TAB</code>
     * <li><code>java.awt.event.KeyEvent.VK_SPACE</code>
     * </ul>
     * The modifiers consist of any combination of:<ul>
     * <li>java.awt.event.InputEvent.SHIFT_DOWN_MASK 
     * <li>java.awt.event.InputEvent.CTRL_DOWN_MASK
     * <li>java.awt.event.InputEvent.META_DOWN_MASK
     * <li>java.awt.event.InputEvent.ALT_DOWN_MASK
     * <li>java.awt.event.InputEvent.ALT_GRAPH_DOWN_MASK
     * <li>java.awt.event.InputEvent.BUTTON1_DOWN_MASK 
     * <li>java.awt.event.InputEvent.BUTTON2_DOWN_MASK 
     * <li>java.awt.event.InputEvent.BUTTON3_DOWN_MASK
     * </ul>
     * The old modifiers <ul>
     * <li>java.awt.event.InputEvent.SHIFT_MASK 
     * <li>java.awt.event.InputEvent.CTRL_MASK 
     * <li>java.awt.event.InputEvent.META_MASK 
     * <li>java.awt.event.InputEvent.ALT_MASK
     * <li>java.awt.event.InputEvent.ALT_GRAPH_MASK
     * </ul> 
     * also can be used, but they are mapped to _DOWN_ modifiers.
     *
     * Since these numbers are all different powers of two, any combination of
     * them is an integer in which each bit represents a different modifier
     * key. Use 0 to specify no modifiers.
     *
     * @param keyCode an int specifying the numeric code for a keyboard key
     * @param modifiers a bitwise-ored combination of any modifiers
     * @param onKeyRelease <code>true</code> if the <code>AWTKeyStroke</code>
     *        should represent a key release; <code>false</code> otherwise
     * @return an AWTKeyStroke object for that key
     *
     * @see java.awt.event.KeyEvent
     * @see java.awt.event.InputEvent
     */
    public static AWTKeyStroke getAWTKeyStroke(int keyCode, int modifiers,
        boolean onKeyRelease)
    { return null; }

    /** 
     * Returns a shared instance of an <code>AWTKeyStroke</code>,
     * given a numeric key code and a set of modifiers. The returned
     * <code>AWTKeyStroke</code> will correspond to a key press.
     * <p>
     * The "virtual key" constants defined in
     * <code>java.awt.event.KeyEvent</code> can be 
     * used to specify the key code. For example:<ul>
     * <li><code>java.awt.event.KeyEvent.VK_ENTER</code> 
     * <li><code>java.awt.event.KeyEvent.VK_TAB</code>
     * <li><code>java.awt.event.KeyEvent.VK_SPACE</code>
     * </ul>
     * The modifiers consist of any combination of:<ul>
     * <li>java.awt.event.InputEvent.SHIFT_DOWN_MASK 
     * <li>java.awt.event.InputEvent.CTRL_DOWN_MASK
     * <li>java.awt.event.InputEvent.META_DOWN_MASK
     * <li>java.awt.event.InputEvent.ALT_DOWN_MASK
     * <li>java.awt.event.InputEvent.ALT_GRAPH_DOWN_MASK
     * <li>java.awt.event.InputEvent.BUTTON1_DOWN_MASK 
     * <li>java.awt.event.InputEvent.BUTTON2_DOWN_MASK 
     * <li>java.awt.event.InputEvent.BUTTON3_DOWN_MASK
     * </ul>
     * The old modifiers <ul>
     * <li>java.awt.event.InputEvent.SHIFT_MASK 
     * <li>java.awt.event.InputEvent.CTRL_MASK 
     * <li>java.awt.event.InputEvent.META_MASK 
     * <li>java.awt.event.InputEvent.ALT_MASK
     * <li>java.awt.event.InputEvent.ALT_GRAPH_MASK
     * </ul> 
     * also can be used, but they are mapped to _DOWN_ modifiers.
     *
     * Since these numbers are all different powers of two, any combination of
     * them is an integer in which each bit represents a different modifier
     * key. Use 0 to specify no modifiers.
     *
     * @param keyCode an int specifying the numeric code for a keyboard key
     * @param modifiers a bitwise-ored combination of any modifiers
     * @return an <code>AWTKeyStroke</code> object for that key
     *
     * @see java.awt.event.KeyEvent
     * @see java.awt.event.InputEvent
     */
    public static AWTKeyStroke getAWTKeyStroke(int keyCode, int modifiers) { return null; }

    /** 
     * Returns an <code>AWTKeyStroke</code> which represents the
     * stroke which generated a given <code>KeyEvent</code>.
     * <p>
     * This method obtains the key char from a <code>KeyTyped</code>
     * event, and the key code from a <code>KeyPressed</code> or
     * <code>KeyReleased</code> event. The <code>KeyEvent</code> modifiers are
     * obtained for all three types of <code>KeyEvent</code>.
     *
     * @param anEvent the <code>KeyEvent</code> from which to
     *      obtain the <code>AWTKeyStroke</code>
     * @return the <code>AWTKeyStroke</code> that precipitated the event
     */
    public static AWTKeyStroke getAWTKeyStrokeForEvent(KeyEvent anEvent) { return null; }

    /** 
     * Parses a string and returns an <code>AWTKeyStroke</code>. 
     * The string must have the following syntax:
     * <pre>
     *    &lt;modifiers&gt;* (&lt;typedID&gt; | &lt;pressedReleasedID&gt;)
     *
     *    modifiers := shift | control | ctrl | meta | alt | button1 | button2 | button3
     *    typedID := typed &lt;typedKey&gt;
     *    typedKey := string of length 1 giving Unicode character.
     *    pressedReleasedID := (pressed | released) key
     *    key := KeyEvent key code name, i.e. the name following "VK_".
     * </pre>
     * If typed, pressed or released is not specified, pressed is assumed. Here
     * are some examples:
     * <pre>
     *     "INSERT" => getAWTKeyStroke(KeyEvent.VK_INSERT, 0);
     *     "control DELETE" => getAWTKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK);
     *     "alt shift X" => getAWTKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK);
     *     "alt shift released X" => getAWTKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK, true);
     *     "typed a" => getAWTKeyStroke('a');
     * </pre>
     *
     * @param s a String formatted as described above
     * @return an <code>AWTKeyStroke</code> object for that String
     * @throws IllegalArgumentException if <code>s</code> is <code>null</code>,
     *        or is formatted incorrectly
     */
    public static AWTKeyStroke getAWTKeyStroke(String s) {return null;  }

    /** 
     * Returns the character for this <code>AWTKeyStroke</code>.
     *
     * @return a char value
     * @see #getAWTKeyStroke(char)
     */
    public final char getKeyChar() {return 0;  }

    /** 
     * Returns the numeric key code for this <code>AWTKeyStroke</code>.
     *
     * @return an int containing the key code value
     * @see #getAWTKeyStroke(int,int)
     */
    public final int getKeyCode() { return 0; }

    /** 
     * Returns the modifier keys for this <code>AWTKeyStroke</code>.
     *
     * @return an int containing the modifiers
     * @see #getAWTKeyStroke(int,int)
     */
    public final int getModifiers() { return 0; }

    /** 
     * Returns whether this <code>AWTKeyStroke</code> represents a key release.
     *
     * @return <code>true</code> if this <code>AWTKeyStroke</code>
     *          represents a key release; <code>false</code> otherwise
     * @see #getAWTKeyStroke(int,int,boolean)
     */
    public final boolean isOnKeyRelease() { return false; }

    /** 
     * Returns the type of <code>KeyEvent</code> which corresponds to
     * this <code>AWTKeyStroke</code>.
     *
     * @return <code>KeyEvent.KEY_PRESSED</code>,
     *         <code>KeyEvent.KEY_TYPED</code>,
     *         or <code>KeyEvent.KEY_RELEASED</code>
     * @see java.awt.event.KeyEvent
     */
    public final int getKeyEventType() { return 0; }

    /** 
     * Returns a numeric value for this object that is likely to be unique,
     * making it a good choice as the index value in a hash table.
     *
     * @return an int that represents this object
     */
    public int hashCode() { return 0; }

    /** 
     * Returns true if this object is identical to the specified object.
     *
     * @param anObject the Object to compare this object to
     * @return true if the objects are identical
     */
    public final boolean equals(Object anObject) { return false; }

    /** 
     * Returns a string that displays and identifies this object's properties.
     *
     * @return a String representation of this object
     */
    public String toString() { return null; }

    /** 
     * Returns a cached instance of <code>AWTKeyStroke</code>  which is equal to this instance.
     *
     * @return a cached instance which is equal to this instance
     */
        // PBP/PP 6218482
    // protected Object readResolve() throws java.io.ObjectStreamException {return null;  }
    private Object readResolve() throws java.io.ObjectStreamException {return null;  }

}
