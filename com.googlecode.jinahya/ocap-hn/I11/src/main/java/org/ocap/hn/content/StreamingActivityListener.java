package org.ocap.hn.content;

import java.util.EventListener;

/**
 * This interface represents a listener for notification of streaming
 * being started, changed or ended
 */
public interface StreamingActivityListener extends EventListener
{

    /**
     * Reason for activity ended.
     */

    /*
     * The service vanished from the network.
     */
   public final static int ACTIVITY_END_SERVICE_VANISHED 	= 1;

    /*
     * Resources needed to present the service have been removed.
     */ 
    public final static int ACTIVITY_END_RESOURCE_REMOVED 	= 3;

    /*
     * Access to the service or some component of it has been withdrawn by the system.
     */
    public final static int ACTIVITY_END_ACCESS_WITHDRAWN 	= 4;

   /*
    * The application or user requested that the presentation be stopped.
    */
    public final static int ACTIVITY_END_USER_STOP		 	= 5;

    /*
     * The presentation was stopped due to a network timeout
     */
    public final static int ACTIVITY_END_NETWORK_TIMEOUT	= 11;

    /*
     * The presentation was terminated due to an unknown reason or for multiple reasons
     */
    public final static int ACTIVITY_END_OTHER 			= 255;

    /**
     * contentTypes.
     */
    public final static int CONTENT_TYPE_ALL_RESOURCES = 0; 
    public final static int CONTENT_TYPE_LIVE_RESOURCES = 1; 
    public final static int CONTENT_TYPE_RECORDED_RESOURCES = 2; 

    /**
     * Notifies the <code>StreamingActivityListener</code> when content begins
     * streaming the content to the home network in response to a request
     * for <code>ContentItem</code> streaming.
     *
     * @param contentItem The <code>ContentItem</code> requested.
     * @param activityID A unique value assigned by the implementation for this
     *      streaming activity.
     * @param URI the URI of the content that's been requested.
     * @param tuner The <code>NetworkInterface</code> representing the tuner
     *      the content is being streamed from if used. The value will be null
     *		if no tuner is used.
     */
    public void notifyStreamingStarted(ContentItem contentItem,
                                       int activityID,
                                       String URI,
                                       org.davic.net.tuning.NetworkInterface tuner);

    /**
     * Notifies the <code>StreamingActivityListener</code> when streaming parameter
     * on this activity such as tuning parameter changes
     *
     * @param contentItem The <code>ContentItem</code> associated with this activity
     * @param activityID A unique value assigned by the implementation for this
     *      streaming activity.
     * @param URI the URI of the content that's been changed.
     * @param tuner The <code>NetworkInterface</code> representing the tuner
     *      the content is being streamed from if used.  The value will be null
     *		if no tuner is used.
     */
    public void notifyStreamingChange(ContentItem contentItem,
                                       int activityID,
                                       String URI,
                                       org.davic.net.tuning.NetworkInterface tuner);

    /**
     * Notifies the <code>StreamingActivityListener</code> when content
     * streaming to the home network in response to a request
     * for <code>ContentItem</code> streaming has ended.
     *
     * @param contentItem The <code>ContentItem</code> requested.
     * @param activityID A unique value assigned by the implementation for this
     *      streaming activity.
     * @param reasonOfEnd Unique value defined in this class for the end of this 
     *      streaming activity
     */
    public void notifyStreamingEnded(ContentItem contentItem,
                                     int activityID,
                                     int reasonOfEnd);
}
