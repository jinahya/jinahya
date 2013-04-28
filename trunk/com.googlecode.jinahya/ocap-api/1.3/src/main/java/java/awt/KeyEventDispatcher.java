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

/** 
 * A KeyEventDispatcher cooperates with the current KeyboardFocusManager in the
 * targeting and dispatching of all KeyEvents. KeyEventDispatchers registered
 * with the current KeyboardFocusManager will receive KeyEvents before they are
 * dispatched to their targets, allowing each KeyEventDispatcher to retarget
 * the event, consume it, dispatch the event itself, or make other changes.
 * <p>
 * Note that KeyboardFocusManager itself implements KeyEventDispatcher. By
 * default, the current KeyboardFocusManager will be the sink for all KeyEvents
 * not dispatched by the registered KeyEventDispatchers. The current
 * KeyboardFocusManager cannot be completely deregistered as a
 * KeyEventDispatcher. However, if a KeyEventDispatcher reports that it
 * dispatched the KeyEvent, regardless of whether it actually did so, the
 * KeyboardFocusManager will take no further action with regard to the
 * KeyEvent. (While it is possible for client code to register the current
 * KeyboardFocusManager as a KeyEventDispatcher one or more times, this is
 * usually unnecessary and not recommended.)
 *
 * @author David Mendenhall
 * @version 1.4, 01/23/03
 *
 * @see KeyboardFocusManager#addKeyEventDispatcher
 * @see KeyboardFocusManager#removeKeyEventDispatcher
 * @since 1.4
 */
public interface KeyEventDispatcher
{

    /** 
     * This method is called by the current KeyboardFocusManager requesting
     * that this KeyEventDispatcher dispatch the specified event on its behalf.
     * This KeyEventDispatcher is free to retarget the event, consume it,
     * dispatch it itself, or make other changes. This capability is typically
     * used to deliver KeyEvents to Components other than the focus owner. This
     * can be useful when navigating children of non-focusable Windows in an
     * accessible environment, for example. Note that if a KeyEventDispatcher
     * dispatches the KeyEvent itself, it must use <code>redispatchEvent</code>
     * to prevent the current KeyboardFocusManager from recursively requesting
     * that this KeyEventDispatcher dispatch the event again.
     * <p>
     * If an implementation of this method returns <code>false</code>, then
     * the KeyEvent is passed to the next KeyEventDispatcher in the chain,
     * ending with the current KeyboardFocusManager. If an implementation
     * returns <code>true</code>, the KeyEvent is assumed to have been
     * dispatched (although this need not be the case), and the current
     * KeyboardFocusManager will take no further action with regard to the
     * KeyEvent. In such a case,
     * <code>KeyboardFocusManager.dispatchEvent</code> should return
     * <code>true</code> as well. If an implementation consumes the KeyEvent,
     * but returns <code>false</code>, the consumed event will still be passed
     * to the next KeyEventDispatcher in the chain. It is important for
     * developers to check whether the KeyEvent has been consumed before
     * dispatching it to a target. By default, the current KeyboardFocusManager
     * will not dispatch a consumed KeyEvent.
     *
     * @param e the KeyEvent to dispatch
     * @return <code>true</code> if the KeyboardFocusManager should take no
     *         further action with regard to the KeyEvent; <code>false</code>
     *         otherwise
     * @see KeyboardFocusManager#redispatchEvent
     */
    public boolean dispatchKeyEvent(KeyEvent e);
}
