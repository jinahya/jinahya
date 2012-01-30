package org.ocap.shared.dvr;

import org.ocap.shared.dvr.navigation.RecordingList;
import org.ocap.shared.dvr.navigation.RecordingListFilter;

/**
 * RecordingManager represents the entity that performs recordings.
 */
public abstract class RecordingManager
{
	/**
	 * Constructor for instances of this class. This constructor is 
	 * provided for the use of implementations and specifications which 
	 * extend this specification. Applications shall not define sub-classes 
	 * of this class. Implementations are not required to behave correctly 
	 * if any such application defined sub-classes are used.
	 */
	protected RecordingManager()
	{
	}

    /**
     * Gets the list of entries maintained by the RecordingManager. This list
     * includes both parent and leaf recording requests.
     * For applications with RecordingPermission("read", "own"), only  
     * RecordingRequests of which the calling application has visibility as
     * defined by any RecordingRequest specific security attributes will be
     * returned.
     * For applications with RecordingPermission("read", "*"), all
     * RecordingRequests will be returned.
     * @return an instance of RecordingList 
     *
     * @throws SecurityException if the calling application does not have 
     * RecordingPermission("read",..) or RecordingPermission("*",..)
     */
    public abstract RecordingList getEntries();

    /**
     * Gets the list of recording requests matching the specified filter.
     * For applications with RecordingPermission("read", "own"), only  
     * RecordingRequests of which the calling application has visibility as
     * defined by any RecordingRequest specific security attributes will be
     * returned.
     * For applications with RecordingPermission("read", "*"), all
     * RecordingRequests matching the specified filter will be returned.
     *
     * @param filter the filter to use on the total set of recording requests
     *
     * @return an instance of RecordingList 
     *
     * @throws SecurityException if the calling application does not have 
     * RecordingPermission("read",..) or RecordingPermission("*",..)
     */
    public abstract RecordingList getEntries(RecordingListFilter filter);

    /**
     * Adds an event listener for changes in status of recording requests.
     * For applications with RecordingPermission("read", "own"), the listener
     * parameter will only be informed of changes that affect RecordingRequests 
     * of which the calling application has visibility as defined by any
     * RecordingRequest specific security attributes.
     * For applications with RecordingPermission("read", "*"), the listener 
     * parameter will be informed of all changes.
     *
     * @param rcl The listener to be registered.
     *
     * @throws SecurityException if the calling application does not have 
     * RecordingPermission("read",..) or RecordingPermission("*",..)
     */
    public abstract void addRecordingChangedListener(
        RecordingChangedListener rcl);

    /**
     * Removes a registed event listener for changes in status of recording
     * requests.  If the listener specified is not registered then this
     * method has no effect.
     *
     * @param rcl the listener to be removed.
     */
    public abstract void removeRecordingChangedListener(
        RecordingChangedListener rcl);

    /**
     * Requests the recording of the stream or streams according to the source
     * parameter.  The concrete sub-class of RecordingSpec may define
     * additional semantics to be applied when instances of that sub-class
     * are used.
     *
     * @param source specification of stream or streams to be recorded
     *      and how they are to be recorded.
     *
     * @return an instance of RecordingRequest that represents the added
     * recording. The request returned is the root request of the recording.
     *
     * @throws IllegalArgumentException if the source is an application
     *      defined class or as defined in the concrete sub-class of
     *      RecordingSpec for instances of that class
     *
     * @throws AccessDeniedException only where defined in the concrete sub-class of
     * RecordingSpec for instances of that class
     * @throws SecurityException if the calling application does not have 
     * RecordingPermission("create",..) or RecordingPermission("*",..)
     *
     */
    public abstract RecordingRequest record(RecordingSpec source)
    throws IllegalArgumentException,AccessDeniedException;

    /**
     * Gets the singleton instance of RecordingManager.
     * @return an instance of RecordingManager
     */
    public static RecordingManager getInstance()
    {
        return null;
    }

	/**
	 * Look up a recording request from the identifier.
	 * Implementations of this method should be optimised considering
	 * the likely very large number of recording requests.
	 * For applications with RecordingPermission("read", "own"), only  
	 * RecordingRequests of which the calling application has visibility as
	 * defined by any RecordingRequest specific security attributes will be
 	 * returned.
	 * @param id an identifier as returned by RecordingRequest.getId
	 * @return the corresponding RecordingRequest
	 * @throws IllegalArgumentException if there is no recording
	 * request corresponding to this identifier or if the recording request
	 * is not visible as defined by RecordingRequest specific security
	 * attributes
	 * @throws SecurityException if the calling application does not have 
	 * RecordingPermission("read",..) or RecordingPermission("*",..)
	 * @see RecordingRequest#getId
	 */
	public abstract RecordingRequest getRecordingRequest(int id)
	   throws IllegalArgumentException;
}


