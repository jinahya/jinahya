/*
 * @(#)ControllerAdapter.java	1.10 98/03/28
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv.media;

import javax.media.*;

/**
 * The event adapter which recieves <code>ControllerEvents</code> and
 * dispatches them to an appropriate stub-method.  Classes that extend
 * this adapter can easily replace only the message handlers they are
 * interested in.
 * 
 * For example, the following code extends a ControllerAdapter with a JDK
 * 1.1 anonymous inner-class to make a self-contained player that
 * resets itself back to the beginning and deallocates itself when the
 * player reaches the end of the media:
 * 
 * <code>
 * player.addControllerListener(new ControllerAdapter() {
 *     public void endOfMedia(EndOfMediaEvent e) {
 *         Controller controller = e.getSource();
 *         controller.stop();
 *         controller.setMediaTime(0);
 *         controller.deallocate();
 *     }
 * });
 * </code>
 * 
 * @see ControllerListener
 * @see Player#addControllerListener(ControllerListener)
 *
 * @version 1.10, 98/03/28
 *
 */

public class ControllerAdapter
    implements ControllerListener, java.util.EventListener {

    public void cachingControl(CachingControlEvent e) {}
	
    public void controllerError(ControllerErrorEvent e) {}

    public void dataLostError(DataStarvedEvent e) {}

    public void internalError(InternalErrorEvent e) {}

    public void resourceUnavailable(ResourceUnavailableEvent e) {}

    public void durationUpdate(DurationUpdateEvent e) {}

    public void mediaTimeSet(MediaTimeSetEvent e) {}

    public void rateChange(RateChangeEvent e) {}

    public void stopTimeChange(StopTimeChangeEvent e) {}

    public void transition(TransitionEvent e) {}

    public void prefetchComplete(PrefetchCompleteEvent e) {}

    public void realizeComplete(RealizeCompleteEvent e) {}

    public void start(StartEvent e) {}

    public void stop(StopEvent e) {}

    public void deallocate(DeallocateEvent e) {}

    public void endOfMedia(EndOfMediaEvent e) {}

    public void restarting(RestartingEvent e) {}

    public void stopAtTime(StopAtTimeEvent e) {}

    public void stopByRequest(StopByRequestEvent e) {}


    /**  
     * Main dispatching function.  Subclasses should not need to
     * override this method, but instead subclass only the individual
     * event methods listed above that they need
     */
    public void controllerUpdate(ControllerEvent e) {

	if (e instanceof CachingControlEvent) {
	    cachingControl((CachingControlEvent)e);

	} else if (e instanceof ControllerErrorEvent) {
	    controllerError((ControllerErrorEvent)e);
	    
	    if (e instanceof DataStarvedEvent) {
		dataLostError((DataStarvedEvent)e);
		
	    } else if (e instanceof InternalErrorEvent) {
		internalError((InternalErrorEvent)e);

	    } else if (e instanceof ResourceUnavailableEvent) {
		resourceUnavailable((ResourceUnavailableEvent)e);
	    }

	} else if (e instanceof DurationUpdateEvent) {
	    durationUpdate((DurationUpdateEvent)e);

	} else if (e instanceof MediaTimeSetEvent) {
	    mediaTimeSet((MediaTimeSetEvent)e);

	} else if (e instanceof RateChangeEvent) {
	    rateChange((RateChangeEvent)e);

	} else if (e instanceof StopTimeChangeEvent) {
	    stopTimeChange((StopTimeChangeEvent)e);

	} else if (e instanceof TransitionEvent) {
	    transition((TransitionEvent)e);

	    if (e instanceof PrefetchCompleteEvent) {
		prefetchComplete((PrefetchCompleteEvent)e);

	    } else if (e instanceof RealizeCompleteEvent) {
		realizeComplete((RealizeCompleteEvent)e);
		
	    } else if (e instanceof StartEvent) {
		start((StartEvent)e);

	    } else if (e instanceof StopEvent) {
		stop((StopEvent)e);
		
		if (e instanceof DeallocateEvent) {
		    deallocate((DeallocateEvent)e);
		
		} else if (e instanceof EndOfMediaEvent) {
		    endOfMedia((EndOfMediaEvent)e);
		    
		} else if (e instanceof RestartingEvent) {
		    restarting((RestartingEvent)e);
		    
		} else if (e instanceof StopAtTimeEvent) {
		    stopAtTime((StopAtTimeEvent)e);
		    
		} else if (e instanceof StopByRequestEvent) {
		    stopByRequest((StopByRequestEvent)e);
		}
	    }
	}
    }
}
