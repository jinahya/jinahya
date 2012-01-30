package org.ocap.media;

import org.davic.mpeg.ElementaryStream;
import java.util.Enumeration;

/**
 * A <code>{@link MediaAccessAuthorization}</code>
 * object represents the presentation authorization 
 * given by a registered MediaAccessHandler for a specific A/V content.
 * When the <code>{@link MediaAccessHandler}</code> is 
 * triggered by the OCAP implementation, it returns a 
 * <code>{@link MediaAccessAuthorization}</code> to indicate 
 * which service components should not be presented, if any, with a list of reasons 
 * for denied access (use constant defined in <code>AlternativeMediaPresentationReason</code>)
 * per service component.    
 *  
 * @see MediaAccessHandler
 * @see AlternativeMediaPresentationReason
 */
public interface MediaAccessAuthorization {

	/**
	 * Returns true if the presentation of all service components is authorized. False otherwise.
	 * @return true if the presentation of all service components is authorized. False otherwise.
	 */
	public boolean isFullAuthorization();

	/**
	 * Returns the list of service components whose presentation has not been authorized
	 * by the <code>{@link MediaAccessHandler}</code>.
	 * @return an Enumeration of <code>{@link org.davic.mpeg.ElementaryStream}</code> whose 
	 * 			presentation has not been authorized by the <code>{@link MediaAccessHandler}</code>.
	 */
	public Enumeration getDeniedElementaryStreams();

	/**
	 * Return a bit mask of denial reasons for the given service component.
	 * Denial reasons are defined in <code>{@link AlternativeMediaPresentationReason}</code>
	 * @param es the service component the <code>{@link MediaAccessHandler}</code> refused presentation.
	 * @return a bit mask of reasons for the denial. This bit mask is made of 
	 * 			<code>{@link AlternativeMediaPresentationReason}</code>.
	 */
	public int getDenialReasons(ElementaryStream es);
	
}

