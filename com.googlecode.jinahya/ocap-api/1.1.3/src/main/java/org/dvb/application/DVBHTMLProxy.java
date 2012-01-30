package org.dvb.application ;

/**
 * A <code>DVBHTMLProxy</code> Object is a proxy to a DVBHTML application.
 */
public interface DVBHTMLProxy extends AppProxy {
	/**
	 * The application is in the loading state.
	 * @since MHP 1.0.2
	 */
	public static final int LOADING = 6;
	/**
	 * The application is in the killed state.
	 * @since MHP 1.0.2
	 */
	public static final int KILLED = 7;


    /**
     * Loads the initial entry page of the application and waits for a signal. 
     * This method mimics the PREFETCH control code and is intended to be 
     * called instead of
     * and not as well as start. Calling prefetch on a started application will
     * have no effect.
     *
     * @throws SecurityException if the calling application does not have 
     * permission to start applications
     * @since   MHP1.0
     */
    public void prefetch () ;
    
    /**
     * Sends the application a start trigger at the specified time. 
     *
     * @param starttime the specified time to send a start trigger to the application. If the time
     * has already passed the application manager shall send the trigger 
     * immediately. Dates pre-epoch shall always cause the application manager 
     * to send the trigger immediately.
     * @throws SecurityException if the calling application does not have 
     * permission to start applications
     *
     * @since   MHP1.0
     */
    public void startTrigger (java.util.Date starttime) ;

    /**
     * Sends the application a trigger with the given payload at the specified time. 
     *
     * @param time the specified time to send a start trigger to the application. 
If the time has
     * already passed the application manager should send the trigger 
     * immediately. Dates pre-epoch shall always cause the application manager
     * to send a 'now' trigger.
     *
     * @param triggerPayload the specified payload to deliver with the trigger. 
     * The payload is specified as object, but this 
     * will be refined once DVB-HTML Triggers are properly defined.
     * @throws SecurityException if the calling application does not have 
     * permission to start applications
     *
     * @since   MHP1.0
     */
    public void trigger (java.util.Date time, Object triggerPayload) ;
}


