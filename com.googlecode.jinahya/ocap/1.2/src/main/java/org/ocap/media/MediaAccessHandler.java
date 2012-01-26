package org.ocap.media;

import javax.media.Player;
import org.davic.mpeg.ElementaryStream;
import org.ocap.net.OcapLocator;

/**
 * A class implementing this interface can prevent the presentation of A/V 
 * service components.
 * <p>Only one instance of the class that implements this interface can be 
 * registered to <code>{@link MediaAccessHandlerRegistrar}</code> via the 
 * <code>{@link MediaAccessHandlerRegistrar#registerMediaAccessHandler(MediaAccessHandler)}</code> 
 * method.
 * JMF calls checkMediaAccessAuthorization() before AV service components presentation.  
 * <p>An application which has a MonitorAppPermission("mediaAccess") may 
 * implement this interface, and may set an instance of it in <code>{@link MediaAccessHandlerRegistrar}</code>.</p>
 * <p>Note : this handler is only responsible for the presentation of A/V service components 
 * and not for launching or not applications.</p>
 */
public interface MediaAccessHandler {
	
	/**
	 * The <code>checkMediaAccessAuthorization()</code> method is invoked each time a 
	 * <code>{@link MediaPresentationEvaluationTrigger}</code> is generated either by the OCAP 
	 * implementation, or, by a monitor application that has MonitorAppPermission(“mediaAccess”) 
	 * through the <code>{@link MediaAccessConditionControl}</code> JMF control. 
	 * The OCAP implementation SHALL block the new presentation corresponding to the new environment 
	 * that led to the generation of the trigger until the MediaAccessHandler grants permission. 
	 * It is implementation dependent whether presentation of previously selected service components is 
	 * stopped or not.
	 * The OCAP implementation gives all the service components that are part of the service selection
	 * even if they are already presented before the trigger is issued. 
	 * @param p the concerned player.
	 * @param sourceURL the URL of the content to be presented.
	 * @param isSourceDigital a boolean indicating if the source is digital or analog. 
	 * @param esList is the list of service components that are going to be presented. esList can be
	 * 			null, for instance if isSourceDigital is false.
	 * @param evaluationTrigger is one of the constant defined in 
	 * 			<code>{@link MediaPresentationEvaluationTrigger}</code> or an application defined
	 * 			<code>{@link MediaPresentationEvaluationTrigger}</code>.
	 * @return a {@link MediaAccessAuthorization} defined by MediaAccessHandler for the given 
	 * 			service components.
	 * 			The MediaAccessAuthorization contains the reason(s), if any, of denied access 
	 * 			(use constant defined in <code>{@link AlternativeMediaPresentationReason}</code>) 
	 * 			per service component.  
	 * @see MediaAccessAuthorization
	 * @see AlternativeMediaPresentationReason
	 * @see MediaPresentationEvaluationTrigger
	 */
	public MediaAccessAuthorization checkMediaAccessAuthorization(
										Player p,
										OcapLocator sourceURL,
										boolean isSourceDigital,
										ElementaryStream[] esList, 
										MediaPresentationEvaluationTrigger evaluationTrigger);

}
