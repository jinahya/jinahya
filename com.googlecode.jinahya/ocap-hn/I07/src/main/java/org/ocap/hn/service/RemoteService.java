/*
 * Created on Dec 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.ocap.hn.service;

import javax.tv.service.Service;

import org.ocap.hn.content.ContentItem;

/**
 * A RemoteService is a service which is hosted or provided by another device on the home network.
 * @author benjes
 */
public interface RemoteService extends Service {
	
	/**
	 * Returns the ContentItem associated with this remote service
	 * @return The {@link ContentItem} associated with this service.
	 */
	public ContentItem getContentItem();
	
}
