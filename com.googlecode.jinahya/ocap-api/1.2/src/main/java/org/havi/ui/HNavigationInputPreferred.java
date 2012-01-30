package org.havi.ui;

import org.havi.ui.event.HFocusEvent;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/** 
    A component which implements {@link
    org.havi.ui.HNavigationInputPreferred}
    indicates that this component expects to receive {@link
    org.havi.ui.event.HFocusEvent} events. The focus event
    system in HAVi is designed to be compatible with standard AWT
    focus mechanisms while supporting key event-based focus traversal
    for HAVi UI components.<p>
   
   All interoperable implementations of the {@link
    org.havi.ui.HNavigationInputPreferred}
   interface must extend {@link org.havi.ui.HComponent HComponent}.

    <p> 
    Components which implement {@link
    org.havi.ui.HNavigationInputPreferred}
    to handle {@link org.havi.ui.event.HFocusEvent} events
    can optionally manage focus traversal based on keyboard input
    events, in addition to the normal semantics of the {@link
    org.havi.ui.event.HFocusEvent#FOCUS_GAINED} and
    {@link org.havi.ui.event.HFocusEvent#FOCUS_LOST} event
    types. The focus traversal mechanism specified by the HAVI UI
    {@link org.havi.ui.HNavigable} interface is one such
    system.

    <p>
    In the case where such an implementation requires specific keys to
    manage focus traversal the {@link
    org.havi.ui.HNavigationInputPreferred#getNavigationKeys}
    method is provided to allow the HAVi platform
    to query the set of keys for which a navigation target has been
    set. When such a component has the input focus, platforms without
    a physical means of generating the desired keystrokes shall
    provide another means for navigation e.g. by offering an on-screen
    &quot;virtual&quot; keyboard. Applications can query the system about
    the support of specific keyCodes through the {@link
    org.havi.ui.event.HKeyCapabilities#isSupported} method.<p>
    
    The keyCodes for navigation keystrokes generated on the {@link
    org.havi.ui.HNavigationInputPreferred} will be passed to the {@link
    org.havi.ui.HNavigationInputPreferred} as an {@link
    org.havi.ui.event.HFocusEvent} transferId through the {@link
    org.havi.ui.HNavigationInputPreferred#processHFocusEvent} method. No {@link
    org.havi.ui.event.HKeyEvent} will be generated on the {@link
    org.havi.ui.HNavigationInputPreferred} as a result of these keystrokes.

    <p> 
    Note that the java.awt.Component method isFocusTraversable should
    always return true for a <code>java.awt.Component</code>
    implementing this interface.  */

public interface HNavigationInputPreferred 
{
    /**
     * Retrieve the set of key codes which this component maps to
     * navigation targets. 
     *
     * @return an array of key codes, or <code>null</code> if no
     * navigation targets are set on this component.  
     */
    public int[] getNavigationKeys();

     /**
     * Process an {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent} sent to this {@link org.havi.ui.HNavigationInputPreferred
     * HNavigationInputPreferred}.
     * 
     * @param evt the {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent} to process. 
     */
     
	public void processHFocusEvent(org.havi.ui.event.HFocusEvent evt);

}

