package org.dvb.application;

import java.util.*;

/**
 * The <code>AppsDatabaseListener</code> class allows an
 * application to monitor the application database so that it can keep an up 
 * to date interface without polling the state. The application shall receive 
 * these events in a timely fashion after the AIT changes, however it is 
 * system dependant how often the AIT table is checked.
 *
 * @since   MHP1.0
 */
public interface AppsDatabaseEventListener extends EventListener{
    /**
     * The AppsDataBase has radically changed. 
     *
     * @param evt the AppsDatabaseEvent.
     * @since   MHP1.0
     */
    public void newDatabase(AppsDatabaseEvent evt) ;
    
    /**
     * The AppsDataBase has had an application entry added. 
     * 
     * @param evt the AppsDatabaseEvent.
     * @since   MHP1.0
     */
    public void entryAdded(AppsDatabaseEvent evt) ;
    
    /**
     * The AppsDataBase has had an application entry removed. 
     * 
     * @param evt the AppsDatabaseEvent.
     * @since   MHP1.0
     */
    public void entryRemoved(AppsDatabaseEvent evt) ;

    /**
     * The AppsDataBase has had an application entry changed. 
     * 
     * @param evt the AppsDatabaseEvent.
     * @since   MHP1.0
     */
    public void entryChanged(AppsDatabaseEvent evt) ;
}


