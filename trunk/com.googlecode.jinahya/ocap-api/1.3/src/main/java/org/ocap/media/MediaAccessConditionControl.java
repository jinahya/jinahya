package org.ocap.media;

import javax.media.Control;

/**
 * <p>
 * This interface allows an application to notify that conditions of media
 * presentation in a running JMF player have been modified, and so the check
 * for media presentation must be done.
 * Instance of the MediaAccessConditionControl interface shall be obtained 
 * via a {@link javax.media.Player#getControl} and a 
 * {@link javax.media.Player#getControls} method by all applications. But 
 * MonitorAppPermission(“mediaAccess”) is necessary to call methods in this interface.
 * </p>
 * 
 */
public interface MediaAccessConditionControl extends Control{
	
	/**
	 * Notifies the player that the conditions to authorize the service 
	 * presentation have been modified, and so a new check must be done for 
	 * the specified player.
	 * <p>Registered <code>{link MediaAccessHandler}</code> will be called.
	 * @param trigger any of the optional trigger defined in 
	 * 			<code>{@link MediaPresentationEvaluationTrigger}</code> or an application 
	 * 			defined MediaPresentationEvaluationTrigger object.
	 * @throws java.lang.SecurityException if the caller 
	 * 			does not have MonitorAppPermission("mediaAccess")
	 * @see MediaPresentationEvaluationTrigger
	 * @see MediaAccessHandler
	 */
	public void conditionHasChanged(MediaPresentationEvaluationTrigger trigger);

}
