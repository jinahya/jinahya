package org.dvb.application;

/**
 * A <code>DVBJProxy</code> Object is a proxy to a DVBJ application.
 */
public interface DVBJProxy extends AppProxy{

	/**
	 * The application is in the loaded state. 
	 */
	 public static final int LOADED = 5;


    /**
     * Provides a hint to preload at least the initial class of the application
     * into local storage, resources permitting. This does not require loading of
     * classes into the virtual machine or creation of a new logical virtual
     * machine which are implications of the <code>init</code> method.
     * <p>This method is asynchronous and its completion will be notified by 
     * an <code>AppStateChangeEvent</code>. In case of failure, the <code>hasFailed</code>
     * method of the <code>AppStateChangeEvent</code> will return true.
     * Calls to this method shall only succeed if the application is in the
     * NOT_LOADED state.
     * In all cases, an AppStateChangeEvent will be sent, whether 
     * the call was successful or not.
     * @since   MHP1.0
     * @throws SecurityException if the application is not entitled to load
     *  	this application. Being able to load an application requires
     *		to be entitled to start it.

     */
    public void load ();
    
    /**
     * Requests the application manager calls the <code>initXlet</code> method on 
     * the application.
     * <p>This method is asynchronous and its completion will be notified by 
     * an AppStateChangeEvent. In case of failure, the hasFailed method of 
     * the <code>AppStateChangeEvent</code> will return true. 
     * Calls to this method shall only succeed if the application is in the
     * NOT_LOADED or LOADED states. If the application is in the NOT_LOADED
     * state, the application will move through the LOADED state into the PAUSED
     * state before calls to this method complete.
     * <p> In all cases, an AppStateChangeEvent will be sent, whether the call 
     * was successful or not.
     * @throws SecurityException if the application is not entitled to load
     *  	this application. Being able to init an application requires
     *		to be entitled to start it.
     *
     * @since   MHP1.0
     */
    public void init () ;
}


