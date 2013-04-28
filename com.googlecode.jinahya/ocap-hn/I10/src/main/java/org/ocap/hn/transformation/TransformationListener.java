package org.ocap.hn.transformation;

import java.util.EventListener;
import org.ocap.hn.content.ContentItem;

/**
 * Listener interface for classes interested in getting notifications from the
 * <code>TransformationManager</code>. Only one of the notify callbacks will be
 * received for each (<code>ContentItem</code>, <code>Transformation</code>)
 * tuple.
 * 
 */
public interface TransformationListener extends EventListener {
    
    /**
     * ReasonCode: Transformation was not successful due to unknown reason(s).
     */
    public static final int REASON_UNKNOWN = 0;
    
    /**
     * ReasonCode: Some resource was not available to create the transformation.
     */
    public static final int REASON_RESOURCE_UNAVAILABLE = 1;
    
    /**
     * ReasonCode: The specific content item for which the transformation was requested 
     * has been deleted.
     */
    public static final int REASON_CONTENTITEM_DELETED = 2;
       
    /**
     * ReasonCode: The content item native format isn't compatible with the requested 
     * transformation's input content profile.
     */
    public static final int REASON_NONMATCHING_INPUT_PROFILE = 3;
       
    /**
     * Callback indicating the <code>ContentResource</code> for the 
     * transformation has been created.
     * 
     * @param contentItem affected contentItem
     * @param transformation requested transformation on contentItem
     */
    void notifyTransformationReady(ContentItem contentItem, 
                                Transformation transformation);
    
    /**
     * Callback indicating the content binary representation for the 
     * transformation could not be created. 
     * 
     * @param contentItem affected contentItem
     * @param transformation requested transformation on contentItem
     * @param reasonCode reason for the failure
     */
    void notifyTransformationFailed(ContentItem contentItem, 
                                Transformation transformation, int reasonCode);
    
}
