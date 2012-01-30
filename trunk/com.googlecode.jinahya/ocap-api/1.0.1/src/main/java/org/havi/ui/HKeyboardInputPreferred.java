package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
 * A component which implements  {@link org.havi.ui.HKeyboardInputPreferred} indicates that this component expects to receive both  {@link org.havi.ui.event.HKeyEvent}  and  {@link org.havi.ui.event.HTextEvent}  input events.<p> All interoperable implementations of the  {@link org.havi.ui.HKeyboardInputPreferred} interface must extend  {@link org.havi.ui.HComponent} . <p>  The set of characters which the component expects to receive via {@link org.havi.ui.event.HKeyEvent}  events is defined by the return code from the  {@link org.havi.ui.HKeyboardInputPreferred#getType}  method. <p>  When this component has focus, platforms without a physical means of generating key events with the desired range of characters will provide another means for keyboard entry e.g. by offering an on-screen &quot;virtual&quot; keyboard. Applications can query the system about the support of specific keyCodes through the  {@link org.havi.ui.event.HKeyCapabilities#isSupported}  method. <p>  Note that the java.awt.Component method isFocusTraversable should always return true for a <code>java.awt.Component</code> implementing this interface.
 */

public interface HKeyboardInputPreferred 
{
    /**
     * This constant indicates that the component requires
     * numeric input, as determined by the
     * <code>java.lang.Character isDigit</code> method.  
     */
    public static final int INPUT_NUMERIC = 1;

    /**
     * This constant indicates that the component requires
     * alphabetic input, as determined by the
     * <code>java.lang.Character isLetter</code> method.  
     */
    public static final int INPUT_ALPHA = 2;

    /**
     * Indicates that the component requires any possible character as
     * input, as determined by the <code>java.lang.Character
     * isDefined</code> method.  
     */
    public static final int INPUT_ANY = 4;

    /**
     * Indicates that the component requires as input the characters
     * present in the array returned from the {@link
     * org.havi.ui.HKeyboardInputPreferred#getValidInput} method.
     */
    public static final int INPUT_CUSTOMIZED = 8;


    /**
	 * Get the editing mode for this  {@link org.havi.ui.HKeyboardInputPreferred} . If the returned value is <code>true</code> the component is in edit mode, and its textual content may be changed through user interaction such as keyboard events. <p> The component is switched into and out of edit mode on receiving  {@link org.havi.ui.event.HTextEvent#TEXT_START_CHANGE} and  {@link org.havi.ui.event.HTextEvent#TEXT_END_CHANGE} events.
	 * @return  <code>true</code> if this component is in edit mode,  <code>false</code> otherwise.
	 * @uml.property  name="editMode"
	 */
    public boolean getEditMode();

    /**
	 * Set the editing mode for this  {@link org.havi.ui.HKeyboardInputPreferred} . <p> This method is provided for the convenience of component implementors. Interoperable applications shall not call this method. It cannot be made protected because interfaces cannot have protected methods.
	 * @param edit  true to switch this component into edit mode, false  otherwise.
	 * @see  HKeyboardInputPreferred#getEditMode
	 * @uml.property  name="editMode"
	 */
    public void setEditMode(boolean edit);

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
    public int getType();

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

    public char[] getValidInput();

    /**
     * Process an {@link org.havi.ui.event.HTextEvent
     * HTextEvent} sent to this {@link org.havi.ui.HKeyboardInputPreferred}.
     * 
     * @param evt the {@link org.havi.ui.event.HTextEvent}
     * to process. 
     */
    public void processHTextEvent(org.havi.ui.event.HTextEvent evt);

    /**
     * Process an {@link org.havi.ui.event.HKeyEvent}
     * sent to this {@link org.havi.ui.HKeyboardInputPreferred}.
     * 
     * @param evt the {@link org.havi.ui.event.HKeyEvent}
     * to process. 
     */
    public void processHKeyEvent(org.havi.ui.event.HKeyEvent evt);



}










