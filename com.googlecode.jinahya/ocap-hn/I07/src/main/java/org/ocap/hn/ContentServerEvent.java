/*
 * Created on 07-Oct-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.ocap.hn;

import org.ocap.hn.content.ContentContainer;
import org.ocap.hn.content.navigation.ContentList;

/**
 * Event which will be sent to registered ContentServerListeners when 
 * ContentEntrys have been added, changed or removed.
 */
public class ContentServerEvent extends java.util.EventObject{

	/**
	 * Event ID indicating that content got added to a ContentServerNetModule.
	 * This event SHALL NOT guarantee that any content items or 
	 * associated metadata have been communicated to the local device.
	 * Applications should utilize the content browsing and searching
	 * APIs to retrieve any added content items.
	 * 
	 *  @see org.ocap.hn.ContentServerNetModule
	 */
	public static final int CONTENT_ADDED = 0;
	
	/**
	 * Event ID indicating that content got removed from a ContentServerNetModule 
	 */
	public static final int CONTENT_REMOVED = 1;
	
	/**
	 * Event ID indicating that metadata associated with content has
	 * been updated. This event SHALL NOT guarantee that and changes
	 * to content items or associated metadata have been communicated
	 * to the local device. Applications should utilize the content
	 * browsing and searching APIs to retrieve any updated metadata.
	 * 
	 * @see org.ocap.hn.ContentServerNetModule
	 */
	public static final int CONTENT_CHANGED = 2;
	
	/**
	 * Creates a new ContentServerEvent with the given source object, the ContentItem involved and
	 * an event ID indicating whether the content got added or removed.
	 * @param source The source of this event. This must be a ContentServerNetModule.
	 * @param content the IDs of the ContentEntrys involved.
	 * @param evt the Event ID, either CONTENT_ADDED,CONTENT_REMOVED or CONTENT_CHANGED.
	 */
	public ContentServerEvent(Object source, String content[],int evt){
		super(source);
	}
	
	/**
	 * Returns the IDs associated with the ContentEntrys involved in this event.
	 * @return the string IDs of the entries involved.
	 */
	public String[] getContent(){
		return null;
		
	}
	
	/**
	 * Returns the ContentServerNetModule. This is the source object of the  event.
	 * 
	 * @return the ContentServerNetModule containing the ContentItem that was
	 *             added/removed/changed
	 */
	public ContentServerNetModule getContentServerNetModule(){
		return null;
	}

	/**
	 * Gets the event ID for this event. Valid values are
	 * CONTENT_ADDED, CONTENT_CHANGED and CONTENT_REMOVED
	 *  
	 * @return the ID for this event
	 */
	public int getEventID()
	{
	    return 0;
	}
}
