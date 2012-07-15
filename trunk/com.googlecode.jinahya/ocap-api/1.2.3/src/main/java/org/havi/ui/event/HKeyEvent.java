package org.havi.ui.event;


import java.awt.Component;


/**
 * An {@link org.havi.ui.event.HKeyEvent HKeyEvent} event is used to
 * interact with a component implementing the {@link
 * org.havi.ui.HKeyboardInputPreferred HKeyboardInputPreferred} interface
 * as follows:
 *
 * <p><ul>
 *
 * <li> An {@link org.havi.ui.event.HKeyEvent HKeyEvent} event may be sent
 * from the HAVi system to inform the component about key-input. The source
 * of the input may be either a real or a virtual keyboard.
 * 
 * <li> An {@link org.havi.ui.event.HKeyEvent HKeyEvent} event is sent from
 * the component to all registered {@link
 * org.havi.ui.event.HKeyListener HKeyListeners} whenever the
 * component received input.
 * 
 * </ul><p>
 * 
 * All interoperable HAVi components which expect to receive {@link
 * org.havi.ui.event.HKeyEvent HKeyEvent} events must either implement the

 * {@link org.havi.ui.HKeyboardInputPreferred
 * HKeyboardInputPreferred} interface or subclass components providing
 * the <code>processHKeyEvent({@link
 * org.havi.ui.event.HKeyEvent HKeyEvent})</code> method.
 * 
 * <hr>
 * The parameters to the constructors are as follows, in cases where
 * parameters are not used, then the constructor should use the default
 * values.
 * <p>
 * <h3>Default parameter values exposed in the constructors</h3>
 * <table border>
 * <tr><th>Parameter</th><th>Description</th><th>Default value</th>
 * <th>Set method</th><th>Get method</th></tr>
 * <tr><td colspan=5>None.</td></tr>
 * </table>
 * <h3>Default parameter values not exposed in the constructors</h3>
 * <table border>
 * <tr><th>Description</th><th>Default value</th><th>Set method</th>
 * <th>Get method</th></tr>
 * <tr><td colspan=4>None.</td></tr>
 * </table>
 *
 * @see org.havi.ui.HKeyboardInputPreferred#processHKeyEvent(org.havi.ui.event.HKeyEvent)
 */
public class HKeyEvent extends HRcEvent
{

  /**
   * Constructs an {@link org.havi.ui.event.HKeyEvent HKeyEvent}
   * object with the specified source component, type, modifiers and
   * key.
   *
   * @param source the object where the event originated.
   * @param id This is the value that will be returned by the event
   * object's <code>getID()</code> method.
   * @param when the time stamp for this event.
   * @param modifiers indication of any modification keys that are
   * active for this event.
   * @param keyCode the code of the key associated with this event.
   * @param keyChar the character representation of the key
   * associated with this event.
   */
  public HKeyEvent(Component source, int id, long when, int modifiers,
                   int keyCode, char keyChar) {
    super(source, id, when, modifiers, keyCode, keyChar);
  }

  
  /**
   * Constructs an {@link org.havi.ui.event.HKeyEvent HKeyEvent}
   * object with the specified source component, type, modifiers and
   * key.
   *
   * @param source the object where the event originated.
   * @param id This is the value that will be returned by the event
   * object's <code>getID()</code> method.
   * @param when the time stamp for this event.
   * @param modifiers indication of any modification keys that are
   * active for this event.
   * @param keyCode the code of the key associated with this event.
   * @deprecated See explanation in java.awt.event.KeyEvent.
   */
//  public HKeyEvent(Component source, int id, long when, int modifiers,
//                   int keyCode) {
//    super(source, id, when, modifiers, keyCode);
//  }

}


