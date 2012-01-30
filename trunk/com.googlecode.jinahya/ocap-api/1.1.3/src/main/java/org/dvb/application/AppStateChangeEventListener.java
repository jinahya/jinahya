package org.dvb.application;

import java.util.*;

/**
 * The <code>AppStateChangeEventListener</code> class allows a launcher
 * application to keep track of applications it launches or other applications
 * running as part of the same service.
 * <p>
 *
 * @since   MHP1.0
 */
public interface AppStateChangeEventListener extends EventListener{
    /**
     * The
     * application the listener was tracking has made a state
     * transition from <code>fromState</code> to <code>toState</code>
     * and this method will be given the state event.
     * 
     * @param evt the AppStateChangeEvent.
     * @since   MHP1.0
     */
    public void stateChange(AppStateChangeEvent evt);
}

