/*
 * Created on 05-Jan-2005 by 
 * Dr. Immo Benjes <immo.benjes@philips.com>, Philips Digital Systems Labs, Redhill, UK
 *
 */
package org.ocap.hn;

import org.ocap.hn.content.ContentContainer;
import org.ocap.hn.content.navigation.ContentList;
import org.ocap.hn.content.ContentEntry;

/**
 * <p>
 * Class representing a NetModule which serves content.
 * </p>
 * <p>
 * NetModules which implement this interface SHALL have a NetModule.PROP_NETMODULE_TYPE
 * property value of NetModule.CONTENT_SERVER.
 * </p>
 */
public interface ContentServerNetModule extends NetModule
{

    /**
     * returns the root ContentContainer for this ContentServerNetModule.
     * 
     * This is an asynchronous method. The caller gets informed via
     * {@link NetActionHandler#notify(NetActionEvent)} of the process.
     * On success, an {@link NetActionEvent} is created where the
     * {@link NetActionEvent#getResponse()} method will return a
     * ContentContainer object representing the root container for
     * this ContentServerNetModule.
     * 
     * @param handler NetActionHandler which gets informed once this asynchronous
     *                request completes
     *      
     * @return NetActionRequest See {@link NetActionRequest}
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentlisting")
     */
    public NetActionRequest requestRootContainer(NetActionHandler handler);

    /**
     * Returns the list of property keys which applications can search against
     * on this ContentServer using the 
     * {@link #requestSearchEntries} method.
     * 
     * This is an asynchronous method. The caller gets informed via
     * {@link NetActionHandler#notify(NetActionEvent)} of the process.
     * On success an {@link NetActionEvent} is created where the
     * {@link NetActionEvent#getResponse()} method will return an
     * array of String objects containing the valid property keys. 
     * A return of an array with zero length indicates that this server supports 
     * no searching functionality. A return containing "*" indicates that any key 
     * associated with any content entry on this server may be used. 
     * 
     * @param handler NetActionHandler which gets informed once this asynchronous
     *                request completes
     * 
     * @return NetActionRequest See {@link NetActionRequest}
     */
    public NetActionRequest requestSearchCapabilities(NetActionHandler handler);
    
    /**
     * Requests a browse of this ContentServer which results in the 
     * creation of a <code>ContentList</code>.  
     * <p>
     * <code>ContentEntry</code> objects hosted on the remote server 
     * will be browsed starting at the ContentEntry specified.
     * 
     * The propertFilter parameter of this method SHALL contain a comma separated
     * list of properties indicating which metadata fields should be 
     * returned in the ContentEntry objects contained in the resulting
     * ContentList. A filter value of "*" indicates all available metadata
     * be returned.
     * 
     * The sortCriteria parameter of this method is a string containing
     * the properties and sort modifiers to be used to sort the resulting
     * ContentList. The format of the string containing the sort criteria
     * shall follow the format defined in UPnP Content Directory Service
     * 3.0 specification section 2.3.16: A_ARG_TYPE_SortCriteria. 
     * 
     * </p>
     * <p>
     * This is an asynchronous method. The caller gets informed via
     * {@link NetActionHandler#notify(NetActionEvent)} of the process.
     * On success an {@link NetActionEvent} is created where the
     * {@link NetActionEvent#getResponse()} method will return a
     * {@link ContentList} containing the search results.  If no matches
     * are found, this value SHALL be a ContentList with zero entries.
     * 
     * A return from {@link NetActionEvent#getActionStatus()} of
     * {@link NetActionEvent#ACTION_COMPLETED} SHALL indicate that
     * a valid {@link ContentList} will be returned from 
     * {@link NetActionEvent#getResponse()}.
     * 
     * <p/>
     * 
     * @param startingEntryID the ID of the ContentEntry on the server to start the browse
     *      from. A value of "0" SHALL indicate the root container on this server.
     *      
     * @param propertyFilter the set of property values to return from this browse operation
     * 
     * @param browseChildren if set to true, this operation will browse all of the
     *      direct children of the startingEntryID parameter. If false, this operation
     *      will return a content list containing the entry identified by startingEntryID
     *      only.
     *       
     * @param startingIndex starting zero-based offset to enumerate children 
     *      under the container specified by <code>parent</code>.
     * 
     * @param requestedCount requested number of entries under the <code>ContentContainer</code>
     *      specified by <code>parent</code>.  Setting this parameter
     *      to <code>0</code> indicates request all entries. 
     *      
     * @param sortCriteria properties and sort modifiers to be used to sort the resulting
     *      ContentList
     *      
     * @param handler NetActionHandler which gets informed once the
     *      results <code>ContentList</code> is created or an error occurs.  
     *      calling <code>getResponse()</code> on handler will return a 
     *      <code>ContentList</code> containing the requested entries, or 
     *      if the call was unsuccessful will return an error message 
     *      supplied by the server.
     * 
     * @return NetActionRequest See {@link NetActionRequest}.
     * 
     * @throws IllegalArgumentException if the startingEntryID is not
     *      available on this ContentServerNetModule, or if the handler parameter 
     *      is null.
     *      
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentlisting")
     *      
     */    
    public abstract NetActionRequest requestBrowseEntries(
            String startingEntryID, 
            String propertyFilter,
            boolean browseChildren,
            int startingIndex,
            int requestedCount,
            String sortCriteria,
            NetActionHandler handler);
    
    /**
     * Requests a search of this ContentServer which results in the 
     * creation of a <code>ContentList</code>.  
     * <p>
     * <code>ContentEntry</code> objects hosted on the remote server 
     * will be searched for using the specified search criteria.  The 
     * format of the string containing the search criteria SHALL follow 
     * the format defined by the UPnP Content Directory Service  
     * 3.0 specification section 2.3.13.1: Search Criteria String Syntax.
     * 
     * The propertFilter parameter of this method SHALL contain a comma separated
     * list of properties indicating which metadata fields should be 
     * returned in the ContentEntry objects contained in the resulting
     * ContentList. A filter value of "*" indicates all available metadata
     * be returned.
     * 
     * The sortCriteria parameter of this method is a string containing
     * the properties and sort modifiers to be used to sort the resulting
     * ContentList. The format of the string containing the sort criteria
     * shall follow the format defined in UPnP Content Directory Service
     * 3.0 specification section 2.3.16: A_ARG_TYPE_SortCriteria. 
     * 
     * </p>
     * <p>
     * This is an asynchronous method. The caller gets informed via
     * {@link NetActionHandler#notify(NetActionEvent)} of the process.
     * On success an {@link NetActionEvent} is created where the
     * {@link NetActionEvent#getResponse()} method will return a
     * {@link ContentList} containing the search results.  If no matches
     * are found, this value SHALL be a ContentList with zero entries.
     * 
     * A return from {@link NetActionEvent#getActionStatus()} of
     * {@link NetActionEvent#ACTION_COMPLETED} SHALL indicate that
     * a valid {@link ContentList} will be returned from 
     * {@link NetActionEvent#getResponse()}.
     * 
     * <p/>
     * 
     * @param parentID the ID of the ContentContainer on the server to start the search
     *      from. A value of "0" SHALL indicate the root container on this server.
     *      
     * @param propertyFilter the set of property values to return from this browse operation
     *      
     * @param startingIndex starting zero-based offset to enumerate children 
     *      under the container specified by <code>parent</code>.
     * 
     * @param requestedCount requested number of entries under the <code>ContentContainer</code>
     *      specified by <code>parent</code>.  Setting this parameter
     *      to <code>0</code> indicates request all entries. 
     *            
     * @param searchCriteria contains the criteria string to search for.
     *        If this parameter is null, the implementation SHALL consider
     *        all entries in the parent container as matching the search
     *        criteria.
     * 
     * @param sortCriteria properties and sort modifiers to be used to sort the resulting
     *      ContentList
     *      
     * @param handler NetActionHandler which gets informed once the
     *      results <code>ContentList</code> is created or an error occurs.  
     *      calling <code>getResponse()</code> on handler will return a 
     *      <code>ContentList</code> containing the requested entries, or 
     *      if the call was unsuccessful will return an error message 
     *      supplied by the server.
     * 
     * @return NetActionRequest See {@link NetActionRequest}.
     * 
     * @throws IllegalArgumentException if the startingEntryID is not
     *      available on this ContentServerNetModule, or if the handler parameter 
     *      is null.
     *      
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentlisting")
     *      
     */    
    public abstract NetActionRequest requestSearchEntries(
            String parentID, 
            String propertyFilter,            
            int startingIndex,
            int requestedCount,
            String searchCriteria,
            String sortCriteria,            
            NetActionHandler handler);
    
    /**
     * Adds a ContentServerListener to this ContentContainer. This
     * ContentServerListener will be notified of additions, removals,
     * or changes to any objects contained within this server
     * 
     * @param listener the Listener that will receive ContentServerEvents.
     */
    public void addContentServerListener(ContentServerListener listener);

    /**
     * Removes the specified ContentServerListener.
     * 
     * @param listener the Listener to remove
     */
    public void removeContentServerListener(ContentServerListener listener);


    
}
