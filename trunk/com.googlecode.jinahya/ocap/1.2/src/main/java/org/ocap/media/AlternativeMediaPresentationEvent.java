package org.ocap.media;

import javax.media.Controller;
import org.davic.mpeg.ElementaryStream;

/**
 * <code>AlternativeMediaPresentationEvent</code> is a JMF event generated 
 * to indicate that an "alternative" content is presented during the media 
 * presentation of a service. 
 * <p>Alternative content is defined as content that is not actually part of 
 * the service.
 * <p>
 * <code>AlternativeMediaPresentationEvent</code> notification is generated :
 * <li>When alternative media content presentation begins;
 * <li>During the presentation of a service, if any of the service components 
 * presented are replaced by alternative content;
 * <li>During the presentation of a service, if an alternative media content was 
 * presented and an evaluation leads to a new alternative media content
 * presentation.
 */
public abstract class AlternativeMediaPresentationEvent extends MediaPresentationEvent
		implements NotPresentedMediaInterface {

	/**
	 * Constructor of MediaPresentationEvent
	 * @see MediaPresentationEvent
	 */	
	protected AlternativeMediaPresentationEvent(Controller from, int previous, int current, int target){
		super(from,previous,current,target);
	}

	/**
	 * @return Returns the subset of explicitly (by Application request) or
	 * implicitly (by the Player itself) service components that were selected and which 
	 * presentation was not possible.
	 */
	public ElementaryStream[] getNotPresentedStreams(){
	    return null;
	}
		
	/**
	 * @return Returns a bit mask of reasons that lead to the non presentation of 
	 * the given service component. The reasons are defined 
	 * in <code>{@link AlternativeMediaPresentationReason}</code>)interface.
	 * @param es a not presented service component.
	 */
	public int getReason(ElementaryStream es){
	    return 0;
	}

}

