package org.ocap.media;

import javax.media.Controller;
import org.davic.mpeg.ElementaryStream;

/**
 * <code>AlternativeMediaPresentationEvent</code> is a JMF event generated 
 * to indicate that an "alternative" content is presented during the media 
 * presentation of a service. 
 * <p>
 * Alternative content is defined as content that is not actually part of 
 * the service.
 *
 */
public abstract class AlternativeMediaPresentationEvent extends
                                                    MediaPresentationEvent
{
    /**
     * Constructor.
     * 
     * @see MediaPresentationEvent
     */ 
    protected AlternativeMediaPresentationEvent(Controller from){
        super(from);
    }

    /**
     * Gets the reason alternative content is being presented.
     * 
     * @return Returns the reason that lead to the non presentation of 
     *      the given service component. Possible reasons are defined 
     *      in <code>{@link AlternativeMediaPresentationReason}</code>)
     *      interface.
     */
    public int getReason(){
        return 0;
    }

}

