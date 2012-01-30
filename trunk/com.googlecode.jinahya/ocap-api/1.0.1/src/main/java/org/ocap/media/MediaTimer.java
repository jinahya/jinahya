package org.ocap.media;

import javax.media.Player;
import javax.media.Time;

/**
* This is a timer class that counts time based on a media time 
* of a specified Player. A media time is specified 
* by the JMF specification. An application can specify a range 
* between a first time and a last time. When a current media time 
* exceeds this range, this timer fires and calls a 
* MediaTimerListener.notify() method. I.e., when a current media 
* time passes the last time, this timer fires and calls the 
* notify() method with MEDIA_WENTOFF_LAST event. On the other hand, 
* when a current media time passes the first time in a reverse 
* playback or a skip playback, this timer fires and calls the 
* notify() method with MEDIA_WENTOFF_FIRST event. 
*/
public class MediaTimer extends Object {
    /**
     * Constructor to make a MediaTimer object that counts time based 
     * on the media time line of the specified Player. 
     *
     * @param p  a JMF Player. 
     */
    public MediaTimer(Player p, 
                      MediaTimerListener listener){
    	
    }

    /**
     * Set a first time of a time range. This MediaTimer object 
     * shall go off when a current time passes the specified first 
     * time in a reverse playback or a skip playback. 
     * A first time value specified in the past will be cleared, 
     * when a new first time is set by this method. 
     *
     * @param time  a time to go off. 
     */
    public void setFirstTime(Time time){
    	
    }

    /**
     * Set a last time of a time range. This MediaTimer object 
     * shall go off when a current time passes the specified last 
     * time in a normal playback or a skip playback. 
     * A last time value specified in the past will be cleared, 
     * when a new last time is set by this method. 
     *
     * @param time  a time to go off. 
     */
    public void setLastTime(Time time){
    	
    }

    /**
     * Get a first time that was set to this MediaTimer object. 
     *
     * @return  a time to go off. 
     */
    public Time getFirstTime(){
       	return null;    	
    }

    /**
     * Get a last time that was set to this MediaTimer object. 
     *
     * @return  a time to go off. 
     */
    public Time getLastTime(){
    	return null;
    }

    /**
     * Start this MediaTimer object. 
     * A MediaTimerListener is called with TIMER_START event value 
     * by the OCAP implementation. 
     */
    public void start(){
    	
    }

    /**
     * Stop this MediaTimer object. 
     * A MediaTimerListener is called with TIMER_STOP event value 
     * by the OCAP implementation. 
     */
    public void stop(){
    	
    }

    /**
     * Get a Player that was tied to this MediaTimer 
     * object by a constructor. 
     *
     * @return  a Player that was tied to this MediaTimer 
     *            object. 
     */
    public Player getPlayer(){
    	return null;
    }
}
