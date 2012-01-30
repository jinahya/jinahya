package org.havi.ui.event;


/**
 *
 * The {@link org.havi.ui.event.HKeyListener HKeyListener}
 * interface enables the reception of {@link
 * org.havi.ui.event.HKeyEvent HKeyEvent} events. <p>
 *
 * Note that HAVi compliant systems should never call the methods
 * <code>keyReleased(KeyEvent)</code> and <code>keyTyped(KeyEvent)</code>
 * of the superinterface on an
 * {@link org.havi.ui.event.HKeyListener HKeyListener}.
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
 */

public interface HKeyListener 
  extends java.awt.event.KeyListener
{
}

