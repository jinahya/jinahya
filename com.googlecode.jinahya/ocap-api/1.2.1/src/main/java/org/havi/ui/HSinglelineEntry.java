package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.KeyEvent;
import org.havi.ui.event.*;


/**
   The {@link org.havi.ui.HSinglelineEntry} is a user interface
   component used to receive a single line of alphanumeric entry from
   the user and can also be used for password input. 
   
   <p>   
   Upon creation the {@link org.havi.ui.HSinglelineEntry} is set to a
   non-editable mode identical in functionality to an {@link
   org.havi.ui.HText}.  

   <p>    
   On keyboard-based systems, if the user navigates to the component
   using the keyboard then the component must first be switched into
   edit mode before it will accept any key presses (other than for
   navigation to another component). The mechanism by which the
   component is switched into and out of edit mode is via
   <code>HTextEvent</code> events with ids {@link
   org.havi.ui.event.HTextEvent#TEXT_START_CHANGE}
   and {@link org.havi.ui.event.HTextEvent#TEXT_END_CHANGE},
   which may be triggered in response to a
   key stroke or other Java AWT event.

   <p> On entering its editable mode the component will send an
   <code>HTextEvent</code> event with an id of {@link
   org.havi.ui.event.HTextEvent#TEXT_START_CHANGE}
   to all registered {@link org.havi.ui.event.HTextListener}
   listeners. The {@link org.havi.ui.HSinglelineEntry}
   will then respond to key events by inserting
   characters into the text string or positioning the insertion point
   (caret) via further {@link org.havi.ui.event.HTextEvent}
   events.

   <p>
   For example, on platforms which do not provide a means of
   positioning the caret independently from navigating to components, the
   navigation keys will be interpreted as caret positioning keys in
   this mode.

   <p>
   While in the editing mode, the component will generate an
   <code>HTextEvent</code> event with an id of {@link
   org.havi.ui.event.HTextEvent#TEXT_CHANGE}
   whenever the text content of the HSinglelineEntry changes
   (e.g. a character is inserted).

   <p> 

   On receiving an <code>HTextEvent</code> event with an id of {@link
   org.havi.ui.event.HTextEvent#TEXT_END_CHANGE}
   the component shall leave its editable mode and send an
   <code>HTextEvent</code> event with an id of {@link
   org.havi.ui.event.HTextEvent#TEXT_END_CHANGE}
   to all registered {@link org.havi.ui.event.HTextListener
   HTextListener} listeners. The user can then navigate out of the {@link
   org.havi.ui.HSinglelineEntry}.

   <p>   
   On mouse-based systems, if the user selects the component by clicking
   a mouse button inside its bounds then the {@link
   org.havi.ui.HSinglelineEntry} will automatically
   switch into edit mode and generate an <code>HTextEvent</code> with
   an id of {@link
   org.havi.ui.event.HTextEvent#TEXT_START_CHANGE}.
   It will stay in edit mode so long as the
   mouse pointer remains within the bounds of the component. Once the
   mouse pointer leaves the bounds then it will switch back into
   non-editable mode and generate an <code>HTextEvent</code> with an
   id of {@link
   org.havi.ui.event.HTextEvent#TEXT_END_CHANGE}.

   <p>
   By default {@link org.havi.ui.HSinglelineEntry} uses the {@link
   org.havi.ui.HSinglelineEntryLook} to render itself.

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr>
  <td>x</td>
  <td>x-coordinate of top left hand corner of this component in pixels, 
  relative to its parent container (subject to layout management).</td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>y</td>
  <td>y-coordinate of top left hand corner of this component in pixels,
  relative to its parent container (subject to layout management).</td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>width</td>
  <td>width of this component in pixels (subject to layout management). 
  </td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>height</td>
  <td>height of this component in pixels (subject to layout management). 
  </td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>

  

<tr>
  <td>text</td> 
  <td>The text within this {@link org.havi.ui.HSinglelineEntry},
  to be used as the displayed and editable content 
  for all states.</td>
  <td>null</td>
  <td>{@link org.havi.ui.HVisible#setTextContent}</td>
  <td>{@link org.havi.ui.HVisible#getTextContent}</td>
</tr>

<tr>
  <td>maxChars</td>
  <td>The maximum number of characters allowed in this {@link
  org.havi.ui.HSinglelineEntry}.</td>
  <td>16 characters</td>
  <td>{@link org.havi.ui.HSinglelineEntry#setMaxChars}</td>
  <td>{@link org.havi.ui.HSinglelineEntry#getMaxChars}</td>
 </tr>
 
<tr>
  <td>font</td>
  <td>The font to be used for this component.</td>
  <td>---</td>
  <td><code>java.awt.Component#setFont</code>.</td>
  <td><code>java.awt.Component#getFont</code>.</td>
 </tr>
 
<tr>
  <td>color</td>
  <td>The color to be used for this component.</td>
  <td>---</td>
  <td><code>java.awt.Component#setForeground</code>.</td>
  <td><code>java.awt.Component#getForeground</code>.</td>
 </tr>
  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr> 
  <td>Associated matte ({@link org.havi.ui.HMatte HMatte}).</td> 
  <td>none (i.e. getMatte() returns <code>null</code>)</td> 
  <td>{@link org.havi.ui.HComponent#setMatte setMatte}</td> 
  <td>{@link org.havi.ui.HComponent#getMatte getMatte}</td> 
  </tr>
   <tr>
       <td> The text layout manager responsible for text
       formatting.</td>
       <td> An {@link org.havi.ui.HDefaultTextLayoutManager}
       object.</td>
       <td> {@link org.havi.ui.HVisible#setTextLayoutManager}
       </td>
       <td> {@link org.havi.ui.HVisible#getTextLayoutManager}
       </td>
   </tr>

   <tr>
       <td>The background painting mode</td>
       <td>{@link org.havi.ui.HVisible#NO_BACKGROUND_FILL}</td>

       <td>{@link org.havi.ui.HVisible#setBackgroundMode}</td>
       <td>{@link org.havi.ui.HVisible#getBackgroundMode}</td>
   </tr>

   <tr>
       <td>The default preferred size</td>
       <td>not set (i.e. NO_DEFAULT_SIZE) unless specified by <code>width</code>
       and <code>height</code> parameters</td>
       <td>{@link org.havi.ui.HVisible#setDefaultSize}</td>
       <td>{@link org.havi.ui.HVisible#getDefaultSize}</td>
   </tr>

   <tr>
       <td>The horizontal content alignment</td>
       <td>{@link org.havi.ui.HVisible#HALIGN_CENTER}</td>
       <td>{@link org.havi.ui.HVisible#setHorizontalAlignment}</td>
       <td>{@link org.havi.ui.HVisible#getHorizontalAlignment}</td>
   </tr>

   <tr>
       <td>The vertical content alignment</td>
       <td>{@link org.havi.ui.HVisible#VALIGN_CENTER}</td>
       <td>{@link org.havi.ui.HVisible#setVerticalAlignment}</td>
       <td>{@link org.havi.ui.HVisible#getVerticalAlignment}</td>
   </tr>

   <tr>
       <td>The content scaling mode</td>
       <td>{@link org.havi.ui.HVisible#RESIZE_NONE}</td>
       <td>{@link org.havi.ui.HVisible#setResizeMode}</td>
       <td>{@link org.havi.ui.HVisible#getResizeMode}</td>
   </tr>

<tr>
    <td>The border mode</td>
    <td><code>true</code></td>
    <td>{@link org.havi.ui.HVisible#setBordersEnabled}</td>
    <td>{@link org.havi.ui.HVisible#getBordersEnabled}</td>
</tr>




<tr>
  <td>Caret position</td>
  <td>At the end of the current text string</td>
  <td>{@link HSinglelineEntry#setCaretCharPosition}</td>
  <td>{@link HSinglelineEntry#getCaretCharPosition}</td>
</tr>
 
<tr>
  <td>Input type</td>
  <td>{@link HSinglelineEntry#INPUT_ANY}</td>
  <td>{@link HSinglelineEntry#setType}</td>
  <td>{@link HSinglelineEntry#getType}</td>
</tr>
 
<tr>
  <td>Customized input range</td>
  <td>null</td>
  <td>{@link HSinglelineEntry#setValidInput}</td>
  <td>{@link HSinglelineEntry#getValidInput}</td>
</tr>

<tr>
  <td>Password protection (the echo character)</td>
  <td>Zero (ASCII NUL), i.e. not password protected.</td>
  <td>{@link HSinglelineEntry#setEchoChar}</td>
  <td>{@link HSinglelineEntry#getEchoChar} and 
  {@link HSinglelineEntry#echoCharIsSet}</td>
</tr>
<tr>
  <td>The default &quot;look&quot; for this class.</td>
  <td>A platform specific {@link org.havi.ui.HSinglelineEntryLook HSinglelineEntryLook}</td>
  <td>{@link org.havi.ui.HSinglelineEntry#setDefaultLook HSinglelineEntry.setDefaultLook}</td>
  <td>{@link org.havi.ui.HSinglelineEntry#getDefaultLook HSinglelineEntry.getDefaultLook}</td>
</tr>

<tr>
  <td>The &quot;look&quot; for this object.</td>
  <td>The {@link org.havi.ui.HSinglelineEntryLook HSinglelineEntryLook} returned from
  HSinglelineEntry.getDefaultLook when this object was created.</td>
  <td>{@link org.havi.ui.HSinglelineEntry#setLook HSinglelineEntry.setLook}</td>
  <td>{@link org.havi.ui.HSinglelineEntry#getLook HSinglelineEntry.getLook}</td>
</tr>
<tr>
  <td>The gain focus sound.</td>
  <td>null </td>
  <td>{@link org.havi.ui.HSinglelineEntry#setGainFocusSound setGainFocusSound}</td>
  <td>{@link org.havi.ui.HSinglelineEntry#getGainFocusSound getGainFocusSound}</td>
</tr>
<tr>
  <td>The lose focus sound. </td>
  <td>null </td>
  <td>{@link org.havi.ui.HSinglelineEntry#setLoseFocusSound setLoseFocusSound}</td>
  <td>{@link org.havi.ui.HSinglelineEntry#getLoseFocusSound getLoseFocusSound}</td>
</tr>
  </table>

*/

public class HSinglelineEntry
    extends HVisible
    implements HTextValue
{
    /**
     * Creates an {@link org.havi.ui.HSinglelineEntry}
     * object. See the class description for details
     * of constructor parameters and default values.
     */
    public HSinglelineEntry()
    {
    }

    /**
     * Creates an {@link org.havi.ui.HSinglelineEntry}
     * object. See the class description for details
     * of constructor parameters and default values.
     */
    public HSinglelineEntry(String text, int x, int y, int width, int height,
                            int maxChars, Font font, Color color)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HSinglelineEntry}
     * object. See the class description for details
     * of constructor parameters and default values.
     */
    public HSinglelineEntry(int x, int y, int width, int height,
                            int maxChars)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HSinglelineEntry}
     * object. See the class description for details
     * of constructor parameters and default values.
     */
    public HSinglelineEntry(String text, int maxChars, Font font,
                            Color color)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HSinglelineEntry}
     * object. See the class description for details
     * of constructor parameters and default values.
     */
    public HSinglelineEntry(int maxChars)
    {
    }

    /**
     * Sets the text content used in this {@link
     * org.havi.ui.HSinglelineEntry}.
     * <p>
     * Note that {@link org.havi.ui.HSinglelineEntry}
     * components do not support separate pieces of textual content per
     * state (as defined in {@link org.havi.ui.HState}) ---
     * rather a single piece of content is defined for all its
     * interaction states.
     * <p>
     * Additionally, the {@link
     * org.havi.ui.HSinglelineEntry#setTextContent}
     * method truncates the string according to the current maxChars
     * setting.
     *
     * @param string The content. If the content is null, then any
     * currently assigned content shall be removed for the specified
     * state.
     * @param state The state of the component for which this content
     * should be displayed. This parameter shall be ignored and
     * considered to have the value {@link HState#ALL_STATES}.
     *
     * @see HSinglelineEntry#getTextContent
     */
    public void setTextContent(String string, int state)
    {
    }

    /**
     * Gets the text content used in this {@link
     * org.havi.ui.HSinglelineEntry HSinglelineEntry}.
     * <p>
     * Note that {@link org.havi.ui.HSinglelineEntry}
     * components do not support separate pieces of textual content per
     * state (as defined in {@link org.havi.ui.HState}) ---
     * rather a single piece of content is defined for all its
     * interaction states.
     *
     * @param state The state of the component for which this content
     * should be displayed. This parameter shall be ignored.
     * @return the text content used in this {@link
     * org.havi.ui.HSinglelineEntry}
     * @see HSinglelineEntry#setTextContent 
     */
    public String getTextContent(int state)
    {
        return (null);
    }

    /**
     * Gets the position of the text insertion caret for this the
     * current line in this text component.  The valid values of the
     * caret position are from 0 to the length of the string retrieved
     * using {@link org.havi.ui.HSinglelineEntry#getTextContent
     * getTextContent}, where 0 implies insertion as the first
     * character (i.e. at the start of the string) and {@link
     * org.havi.ui.HSinglelineEntry#getTextContent
     * (getTextContent()).length()} implies that further characters
     * are to be appended onto the end of the string. Hence, the valid
     * caret positions for the string &quot;abc&quot; of length 3, are
     * 0, 1, 2 and 3 --- with caret locations as shown below:
     * <pre>
     * 0 &quot;a&quot; 1 &quot;b&quot; 2 &quot;c&quot; 3
     * </pre>
     *
     * @return the position of the text insertion caret.  
     */
    public int getCaretCharPosition()
    {
        return (0);
    }

    /**
     * Sets the position of the text insertion caret for this text
     * component. If position is not valid for the current content the
     * caret is moved to the nearest position.
     *
     * @param position the new position of the text insertion caret.
     * @return the new caret position.  
     */
    public int setCaretCharPosition(int position)
    {
	return (0);
    }

    /**
     * Set to indicate to the system which input keys are required by this
     * component. The input type constants can be added to define the
     * union of the character sets corresponding to the respective
     * constants.
     *
     * @param type sum of one or several of {@link
     * org.havi.ui.HKeyboardInputPreferred#INPUT_ANY},
     * {@link org.havi.ui.HKeyboardInputPreferred#INPUT_NUMERIC}, {@link
     * org.havi.ui.HKeyboardInputPreferred#INPUT_ALPHA} or
     * {@link org.havi.ui.HKeyboardInputPreferred#INPUT_CUSTOMIZED}.
     */
    public void setType(int type)
    {
    }
    
    /** 
     * Defines the set of the characters which are valid for
     * customized keyboard input, i.e. when the input type is set to
     * {@link org.havi.ui.HKeyboardInputPreferred#INPUT_CUSTOMIZED}.
     * 
     * @param inputChars an array of characters which comprises the
     * valid input characters.  
     */
    public void setValidInput(char[] inputChars)
    {
    }

    /**
     * Determine if this component has an echo character set, i.e. if
     * the echo character is non-zero.
     * 
     * @return true if an echo character is set, false otherwise.  
     */
    public boolean echoCharIsSet()
    {
        return (false);
    }

    /**
     * Returns the character to be used for echoing.
     * 
     * @return the character to be used for echoing or 0 (ASCII NUL)
     * if no echo character is set.
     */
    public char getEchoChar()
    {
        return (0);
    }

    /**
     * Sets the number of character to echo for this component.
     * 
     * @param c the character used to echo any input, e.g. if c == '*'
     * a password-style input will be displayed.  If c is zero (ASCII
     * NUL), then all characters will be echoed on-screen, this is the
     * default behavior.  
     */
    public void setEchoChar(char c)
    {
    }

    /**
     * Sets the default {@link org.havi.ui.HLook HLook} for further
     * {@link org.havi.ui.HSinglelineEntry}
     * Components.
     *
     * @param look The {@link org.havi.ui.HLook} that will be
     * used by default when creating a new {@link
     * org.havi.ui.HSinglelineEntry} component.
     * Note that this parameter may be null, in which case newly
     * created components shall not draw themselves until a non-null
     * look is set using the {@link
     * org.havi.ui.HSinglelineEntry#setLook} method.
     */
    public static void setDefaultLook(HSinglelineEntryLook look)
    {
    }

    /**
     * Returns the currently set default {@link org.havi.ui.HLook}
     * for {@link org.havi.ui.HSinglelineEntry}
     * components.
     *
     * @return The {@link org.havi.ui.HLook} that is used by
     * default when creating a new {@link org.havi.ui.HSinglelineEntry}
     * component.
     */
    public static HSinglelineEntryLook getDefaultLook()
    {
        return (null);
    }

    /**
     * Sets the {@link org.havi.ui.HLook} for this component.
     * 
     * @param hlook The {@link org.havi.ui.HLook} that is to be
     * used for this component.
     * Note that this parameter may be null, in which case the
     * component will not draw itself until a look is set.
     * @exception HInvalidLookException If the Look is not an {@link
     * org.havi.ui.HSinglelineEntryLook}.  
     */
    public void setLook(HLook hlook)
	throws HInvalidLookException
    {
    }

    /**
     * Insert a character at the current caret position, subject to
     * the maximum number of input characters.
     * 
     * @param c the character to insert
     * @return true if the character was inserted, false otherwise.  
     */
    public boolean insertChar(char c)
    {
        return (true);
    }

    /**
     * Delete a character behind the current caret position.
     *
     * @return true if a character was deleted, false otherwise.  
     */
    public boolean deletePreviousChar()
    {
        return (true);
    }

    /**
     * Delete a character forward of the current caret position.
     *
     * @return true if a character was deleted, false otherwise.
     */
    public boolean deleteNextChar()
    {
        return (true);
    }

    /**
     * Move the caret to the next character. The caret position is
     * constrained such that the insert point lies within the range as
     * defined by the {@link
     * org.havi.ui.HSinglelineEntry#getCaretCharPosition}
     * method.  
     */
    public void caretNextCharacter()
    {
    }

    /**
     * Move the caret to the previous character. The caret position is
     * constrained such that the insert point lies within the range as
     * defined by the {@link
     * org.havi.ui.HSinglelineEntry#getCaretCharPosition}
     * method.  
     */
    public void caretPreviousCharacter()
    {
    }

    /**
     * Set maximum number of characters.
     *
     * @param maxChars - the maximum number of characters.
     */
    public void setMaxChars(int maxChars)
    {
    }

    /**
     * Get maximum number of characters. The behavior of the component when the 
     * last character on a line is typed is implementation dependent.
     * 
     * @return the maximum number of characters.
     */
    public int getMaxChars()
    {
        return (0);
    }

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
    public void setMove(int keyCode, HNavigable target)
    {
        return;
    }

    /**
     * Provides the {@link org.havi.ui.HNavigable HNavigable} object
     * that is navigated to when a particular key is pressed.
     * 
     * @param keyCode The key code of the pressed key.
     * @return Returns the {@link org.havi.ui.HNavigable HNavigable}
     * object or <code>null</code> if no {@link org.havi.ui.HNavigable
     * HNavigable} is associated with the keyCode.
     */
    public HNavigable getMove(int keyCode)
    {
        return(null);
    }

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
    public void setFocusTraversal(HNavigable up, HNavigable down, HNavigable left, HNavigable right)
    {
        return;
    }

    /**
     * Indicates if this component has focus. 
     * 
     * @return <code>true</code> if the component has focus, otherwise
     * returns <code>false</code>.  
     */
    public boolean isSelected()
    {
        return(false);
    }

    /**
     * Associate a sound with gaining focus, i.e. when the {@link
     * org.havi.ui.HNavigable HNavigable} receives a
     * {@link org.havi.ui.event.HFocusEvent HFocusEvent} event of type
     * <code>FOCUS_GAINED</code>. This sound will start to be played
     * when an object implementing this interface gains focus. It is
     * not guaranteed to be played to completion. If the object
     * implementing this interface loses focus before the audio
     * completes playing, the audio will be truncated.  Applications
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
    public void setGainFocusSound(HSound sound)
    {
        return;
    }

    /**
     * Associate a sound with losing focus, i.e. when the {@link
     * org.havi.ui.HNavigable HNavigable} receives a
     * {@link org.havi.ui.event.HFocusEvent HFocusEvent} event of type
     * FOCUS_LOST. This sound will start to be played when an object 
     * implementing this interface loses focus. It is not guaranteed to be 
     * played to completion. It is implementation dependent whether and when 
     * this sound will be truncated by any gain focus sound played by the next
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
    public void setLoseFocusSound(HSound sound)
    {
        return;
    }

    /**
     * Get the sound associated with the gain focus event.
     * 
     * @return The sound played when the component gains focus. If no
     * sound is associated with gaining focus, then null shall be
     * returned.  
     */
    public HSound getGainFocusSound()
    {
        return(null);
    }

    /**
     * Get the sound associated with the lost focus event.
     * 
     * @return The sound played when the component loses focus. If no
     * sound is associated with losing focus, then null shall be
     * returned.  
     */
    public HSound getLoseFocusSound()
    {
        return(null);
    }

/**
 * Adds the specified {@link org.havi.ui.event.HFocusListener HFocusListener} to 
 * receive {@link org.havi.ui.event.HFocusEvent HFocusEvent} events sent from 
 * this {@link org.havi.ui.HNavigable HNavigable}: If the listener has 
 * already been added further calls will add further references to the listener,  
 * which will then receive  multiple copies of a single event.
 * 
 * @param l the HFocusListener to add
 */

public void addHFocusListener(org.havi.ui.event.HFocusListener l)
    {
        return;
    }


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

public void removeHFocusListener(org.havi.ui.event.HFocusListener l)
    {
        return;
    }




    /**
     * Retrieve the set of key codes which this component maps to
     * navigation targets. 
     *
     * @return an array of key codes, or <code>null</code> if no
     * navigation targets are set on this component.  
     */
    public int[] getNavigationKeys()
    {
        return(null);
    }

     /**
     * Process an {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent} sent to this {@link org.havi.ui.HSinglelineEntry
     * HSinglelineEntry}.
     * 
     * @param evt the {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent} to process. 
     */
     
	public void processHFocusEvent(org.havi.ui.event.HFocusEvent evt)
    {
        return;
    }


/**
 * Adds the specified {@link org.havi.ui.event.HKeyListener HKeyListener} to  
 * receive {@link org.havi.ui.event.HKeyEvent HKeyEvent} events sent from this 
 * {@link org.havi.ui.HTextValue HTextValue}: If the listener has already been 
 * added further calls will add further references to the listener, which will 
 * then receive multiple copies of a single event.
 * 
 * @param l the HKeyListener to add
 */

public void addHKeyListener(org.havi.ui.event.HKeyListener l)
    {
        return;
    }


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

public void removeHKeyListener(org.havi.ui.event.HKeyListener l)
    {
        return;
    }

/**
 * Adds the specified {@link org.havi.ui.event.HTextListener HTextListener} to  
 * receive {@link org.havi.ui.event.HTextEvent HTextEvent} events sent from this  
 * {@link org.havi.ui.HTextValue HTextValue}: If the listener has already been  
 * added further calls will add further references to the listener, which will 
 * then receive multiple copies of a single event.
 *
 * @param l the HTextListener to add
 */

public void addHTextListener(org.havi.ui.event.HTextListener l)
    {
        return;
    }

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

public void removeHTextListener(org.havi.ui.event.HTextListener l)
    {
        return;
    }


    /**
     * Get the editing mode for this {@link org.havi.ui.HSinglelineEntry}.
     * If the returned value is <code>true</code> the
     * component is in edit mode, and its textual content may be
     * changed through user interaction such as keyboard events.
     * <p>
     * The component is switched into and out of edit mode on
     * receiving {@link
     * org.havi.ui.event.HTextEvent#TEXT_START_CHANGE}
     * and {@link
     * org.havi.ui.event.HTextEvent#TEXT_END_CHANGE}
     * events.
     * 
     * @return <code>true</code> if this component is in edit mode,
     * <code>false</code> otherwise.  
     */
    public boolean getEditMode()
    {
        return(true);
    }

    /**
     * Set the editing mode for this {@link org.havi.ui.HSinglelineEntry}.
     * <p>
     * This method is provided for the convenience of component
     * implementors. Interoperable applications shall not call this
     * method. It cannot be made protected because interfaces cannot have
     * protected methods.
     * 
     * @param edit true to switch this component into edit mode, false
     * otherwise.
     * @see HKeyboardInputPreferred#getEditMode 
     */
    public void setEditMode(boolean edit)
    {
        return;
    }

    /**
     * Retrieve the desired input type for this component.  This value
     * should be set to indicate to the system which input keys are
     * required by this component. The input type constants can be added to 
     * define the union of the character sets corresponding to the respective 
     * constants.
     * 	
     * 
     * @return The sum of one or several of {@link
     * org.havi.ui.HKeyboardInputPreferred#INPUT_ANY},
     * {@link
     * org.havi.ui.HKeyboardInputPreferred#INPUT_NUMERIC},
     * {@link
     * org.havi.ui.HKeyboardInputPreferred#INPUT_ALPHA},
     * or {@link org.havi.ui.HKeyboardInputPreferred#INPUT_CUSTOMIZED}.
     */
    public int getType()
    {
        return(0);
    }

  /**
   * Retrieve the customized input character range. If 
   * <code>getType()</code> returns a value with the INPUT_CUSTOMIZED
   * bit set then this method shall return an array containing the
   * range of customized input keys. If the range of customized input
   * keys has not been set then this method shall return a zero length
   * char array. This method shall return null if 
   * <code>getType()</code> returns a value without the
   * INPUT_CUSTOMIZED bit set.
   * @return an array containing the characters which this component
   * expects the platform to provide, or <code>null</code>.
   */

    public char[] getValidInput()
    {
        return(null);
    }

    /**
     * Process an {@link org.havi.ui.event.HTextEvent
     * HTextEvent} sent to this {@link org.havi.ui.HSinglelineEntry}.
     * 
     * @param evt the {@link org.havi.ui.event.HTextEvent}
     * to process. 
     */
    public void processHTextEvent(org.havi.ui.event.HTextEvent evt)
    {
        return;
    }

    /**
     * Process an {@link org.havi.ui.event.HKeyEvent}
     * sent to this {@link org.havi.ui.HSinglelineEntry}.
     * 
     * @param evt the {@link org.havi.ui.event.HKeyEvent}
     * to process. 
     */
    public void processHKeyEvent(org.havi.ui.event.HKeyEvent evt)
    {
        return;
    }




}


