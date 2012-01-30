package org.ocap.media;

import java.util.EventListener;
import javax.media.Player;

/**
* This is a listener to inform events on a MediaTimer object. 
*
*/
public interface MediaTimerListener extends EventListener
{

    /**
     * Indicates a MediaTimer starts. 
     */
    public final int TIMER_START = 1; 

    /**
     * Indicates a MediaTimer stops. 
     */
    public final int TIMER_STOP = 2; 

    /**
     * Indicates a MediaTimer went off since a current media time 
     * passes the specified first time in a reverse playback or 
     * a skip playback. 
     */
    public final int TIMER_WENTOFF_FIRST = 3; 

    /**
     * Indicates a MediaTimer went off since a current media time 
     * passes the specified last time in a normal playback or 
     * a skip playback. 
     */
    public final int TIMER_WENTOFF_LAST = 4; 

    /**
     * This is a call back method to inform event on a MediaTimer. 
     *
     * @param event  an event value that happened on a MediaTimer. 
     *         One of constants that have TIMER_ prefix. 
     *
     * @param p  a Player that was tied to this MediaTimer  
     *         object. 
     */
    public void notify(int event, Player p); 
}
