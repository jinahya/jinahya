package org.ocap.media;

import javax.media.Controller;


/**
 * <code>NormalMediaPresentationEvent</code> is a JMF event generated when the
 * normal media components of a service are presented. 
 * <p>Media presentation is considered as normal when explicitly selected 
 * service components (by a dedicated API), or implicitly selected service components 
 * (by the player itself) can be presented to the user. Media presentation is 
 * considered as "alternative" in any other case, especially when it is caused 
 * by one of the reasons described in <code>{@link AlternativeMediaPresentationReason}</code>.  
 * <p>
 * <code>NormalMediaPresentationEvent</code> notification is generated :
 * <p><li>When normal media content presentation begins;
 * <p><li>During the presentation of a service, if alternative media content was 
 * presented and all of that media alternative content is replaced by a
 * content which is a normal part of the service concerned;
 * <p><li>During the presentation of a service, if normal media content was being
 *  presented and an evaluation leads to a new normal media content presentation.
 */
public abstract class NormalMediaPresentationEvent extends MediaPresentationEvent {
	
	/**
	 * Constructor of MediaPresentationEvent
	 * @see MediaPresentationEvent
	 */	
	protected NormalMediaPresentationEvent(Controller from, int previous, int current, int target){
		super(from,previous,current,target);
	}
 
}
