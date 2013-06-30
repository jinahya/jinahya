package org.ocap.hn.content.navigation;

import org.ocap.hn.content.ContentEntry;

/**
 * This class represent a filtering criteria to be applied while creating a ContentList.
 * @author Labeeb Ismail, Liberate 
 * @author Dr. Immo Benjes <immo.benjes@philips.com>, Philips Digital Systems Labs, Redhill, UK 
 */

public abstract class ContentDatabaseFilter {

	protected ContentDatabaseFilter() {}

	/** 
	 * This method is called for every ContentEntry in the database/list this 
	 * filter is applied to. Implementations should return true if the 
	 * specified ContentItem should be in the filtered list. If the ContentItem 
	 * should not be listed in the new list false should be returned.
	 *  
	 * @param entry the ContentEntry to filter
	 * @return true if the ContentEntry should be in the filtered ContentList, false otherwise.
	 */
	public abstract boolean accept(ContentEntry entry);
}

