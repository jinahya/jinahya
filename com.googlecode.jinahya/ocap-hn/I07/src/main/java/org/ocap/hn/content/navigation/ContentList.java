package org.ocap.hn.content.navigation;

import java.util.Enumeration;
import org.ocap.hn.content.ContentEntry;
import org.ocap.hn.content.DatabaseException;

/**
 * This interface represents a list of filtered ContentEntry objects. A
 * ContentList may contain a complete or partial subset of entries resulting
 * from an application requested filter, browse or search.
 */
public interface ContentList extends Enumeration
{
    /**
     * Gets the number of ContentEntry objects in this ContentList.
     * 
     * @return Number of entries in this list.  Returns 0 if the list
     *      is empty.
     */
    public int size();

    /**
     * Gets to total number of ContentEntry matches in the filter, browse or search
     * operation that generated this ContentList. This value SHALL be greater than
     * or equal to the value returned from the size() method of this ContentList.
     * 
     * This value SHALL be greater than the value returned from the size() method of
     * this ContentList if the <i>requestedCount</i> parameter of the originating
     * content entry request was less than the total entry matches of the requesting
     * operation.
     *
     * See {@link org.ocap.hn.ContentServerNetModule}.
     *
     * 
     * @return the total number of ContentEntry matches from the originating content 
     *            entry request
     */
    public int totalMatches();
    
    /**
     * Sets the metadata sort order of the items in this list based on 
     * metadata key identifiers using signed property values. 
     * 
     * The sortOrder parameter of this method is a string containing
     * the properties and sort modifiers to be used to sort the resulting
     * ContentList. The format of the string containing the sort criteria
     * shall follow the format defined in UPnP Content Directory Service
     * 3.0 specification section 2.3.16: A_ARG_TYPE_SortCriteria. 
     *
     * @param sortOrder a String representing the sortOrder for this ContentList.
     */
    public void setSortOrder(String sortOrder);

    /**
     * Gets the sort order set by the #setSortOrder method.
     * 
     * @return The array of sort keys, or null if the setPreferredSortOrder
     *      method has not been called for this list.
     */
    public String getSortOrder();

    /**
     * Finds the first {@link ContentEntry} which identifier for the key
     * '<code>key</code>' equals the given object <code>obj</code>.  For
     * instance, if key == "Title" then obj represents the title, e.g.
     * "Best movie ever" and this method will return the first ContentEntry in
     * the list than contains a match for the (key, value) pair.
     * 
     * @param key The identifier key.
     * @param value The object to compare to
     * 
     * @return The first matched ContentEntry, or null if no match found. 
     */
    public ContentEntry find(String key, Object value);

    /**
     * Finds the first ContentEntry which matches the search.
     * The keys and values parameters are parallel arrays.  For example,
     * if keys[0] == "TITLE" and values[0] == "Best movie ever", the 
     * implementation SHALL match the first ContentEntry in the list where
     * the metadata contains that (key, value) pair, as well as matches any
     * other entries in the parameter arrays.
     * 
     * @param keys Array of identifier keys.
     * @param values Array of values.
     * 
     * @return The first matching ContentEntry found, or null if no match.  If
     *      the parameter arrays are not the same length this method returns
     *      null.
     */
    public ContentEntry find(String [] keys, Object [] values);

    /**
     * Finds all ContentEntry objects which match the search.
     * Same as the #find(String[], Object[]) method except all matches are
     * returned instead of just the first match.
     * 
     * @param keys Array of identifier keys.
     * @param values Array of values.
     * 
     * @return A ContentList containing all matches, or null if no matches
     *      were found.
     */
    public ContentList findAll(String[] keys, Object[] values);

    /** 
     * Filters the ContentList. The returned ContentList is a new ContentList
     * only containing ContentItems on which ContentDatabaseFilter.accept
     * returned true.
     * 
     * @param filter the ContentDatabaseFilter
     * 
     * @return newly created ContentList containing only the filtered
     *      ContentItems.
     * 
     * @throws DatabaseException; see DatabaseException for exception reasons.
     */
    public ContentList filterContentList(ContentDatabaseFilter filter)
                                                    throws DatabaseException;
}

