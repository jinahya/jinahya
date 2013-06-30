package org.ocap.media;

import javax.media.TransitionEvent;
import javax.media.Controller;
import org.davic.mpeg.ElementaryStream;
import org.ocap.net.OcapLocator;

/**
 * <code>MediaPresentationEvent</code> is a JMF event used as the parent
 * class of events indicating dynamic changes to the presentation of 
 * media components.
 * <p>This event provides the trigger that leads to the generation of 
 * this event. 
 */
public abstract class MediaPresentationEvent extends TransitionEvent {

	/**
	 * Constructor of MediaPresentationEvent
	 * @see javax.media.TransitionEvent
	 */	
	protected MediaPresentationEvent(Controller from, int previous, int current, int target){
		super(from,previous,current,target);
	}
	
	/**
	 * @return Returns true if the presented source is digital, false otherwise.
	 */
	public boolean isSourceDigital() {
		return true;
	}

	/**
	 * @return Returns the URL of the source that is presented.
	 */
	public OcapLocator getSourceURL() {
		return null;
	}

	/**
	 * @return Returns the service components that are currently presented.
	 * If no service components is presented or the source is analog, null is returned.
	 */
	public ElementaryStream[] getPresentedStreams() {
		return null;
	}
	
	/**
	 * @return Returns the trigger that leads to the generation of a 
	 * 			MediaPresentationEvent.
	 * @see MediaPresentationEvaluationTrigger
	 */
	public MediaPresentationEvaluationTrigger getTrigger() {
		return null;
	}
}
