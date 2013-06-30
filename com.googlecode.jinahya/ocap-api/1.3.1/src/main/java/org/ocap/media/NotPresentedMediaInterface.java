package org.ocap.media;

import org.davic.mpeg.ElementaryStream;

/**
 * NotPresentedMediaInterface shall be implemented by classes which can report 
 * failure to access media components. The interface provides an ability for an 
 * application to find out the list of not presented service components and some 
 * information about the reason for the failure. 
 */
public interface NotPresentedMediaInterface {

    /**
	 * @return Returns the subset of explicitely (by Application request) or
	 * implicitely (by the Player itself) service components that were selected and which 
	 * presentation was not possible.
	 */
	public ElementaryStream[] getNotPresentedStreams();
	
	/**
	 * @return Returns a bit mask of reasons that lead to the non presentation of 
	 * the given service component. The reasons are defined 
	 * in <code>{@link AlternativeMediaPresentationReason}</code>)interface.
	 * @param es a not presented service component.
	 */
	public int getReason(ElementaryStream es);
}
