package org.dvb.application;

/**
 * The <code>AppsDatabaseEvent</code> class indicates either an entry
 * in the application database has changed, or so many changes have occurred.
 * that the database should be considered totally new.
 * An event with event_id NEW_DATABASE shall always be sent after switching to a new service.
 * After such an event, the contents of the database (both the set of applications and 
 * their attributes) shall reflect the new database contents. All former contents of 
 * the database shall be discarded except for running externally authorised applications.
 * It is platform dependant if and when a new database event is thrown 
 * while tuned to the same service except that a NEW_DATABASE event shall not 
 * be sent when only one application has changed within a service.
 * <p>The APP_ADDED, APP_CHANGED and APP_DELETED events shall not be 
 * generated in response to the same database change as caused a 
 * NEW_DATABASE event to be generated.
 * <p>
 * @since   MHP1.0
 */
public class AppsDatabaseEvent extends java.util.EventObject{
    /**
     * The new database event id.
     */
    static public final int NEW_DATABASE = 0;
    /**
     * The changed event id. The APP_CHANGED event is generated whenever
     * any of the information about an application changes. It is NOT generated
     * when the entry is added to or removed from the AppsDatabase. In such cases, 
     * the APP_ADDED or APP_DELETED events will be generated instead.
     */
    static public final int APP_CHANGED = 1;
    /**
     * The addition event id. The APP_ADDED event is generated whenever
     * an entry is added to the AppsDatabase. It is NOT generated
     * when the entry already in the AppsDatabase changes. 
     */
    static public final int APP_ADDED = 2;
    /**
     * The deletion event id. The APP_DELETED event is generated whenever
     * an entry is removed from the AppsDatabase. 
     */
    static public final int APP_DELETED = 3;

    /**
     * Create a new AppsDatabaseEvent object for the entry in the database 
     * that changed, or for a new database. 
     * <p>    
     * @param id the cause of the event
     * @param appid the AppId of the entry that changed
     * @param source the AppaDatabase object.
     * @since   MHP1.0
     */

     public AppsDatabaseEvent(int id, AppID appid, Object source){super(source);};

    /**
     * gets the application ID object for the entry in the database that 
     * changed.
     * <p>    
     * When the event type is NEW_DATABASE, AppID will be null.
     * @return     application ID representing the application
     * @since   MHP1.0
     */
    public AppID getAppID(){return null;}

    /**
     * gets the type of the event.
     * <p>    
     * @return  an integer that matches one of the static fields describing events.
     * @since   MHP1.0
     */
    public int getEventId(){return 0;}


}
	

