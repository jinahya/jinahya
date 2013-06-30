package org.ocap.service;

import javax.tv.service.selection.AlternativeContentEvent;
import javax.tv.service.selection.PresentationTerminatedEvent;
import javax.tv.service.selection.SelectionFailedEvent;
import javax.tv.service.selection.ServiceContentHandler;
import javax.tv.service.selection.ServiceContext;

/**
 * <code>AlternativeContentErrorEvent</code> is generated to indicate that
 * "alternative" content is being presented due to an error that 
 * prevents the presentation of normal content as part of selection
 * of a service and during presentation of that selected service.  
 * <p>
 * This event will be generated instead of <code>SelectionFailedEvent</code>
 * where normal content could not be presented due to the following situations:
 * 
 * <ul>
 * <li> The parental control settings prevent it.
 * <li> The CA system refusing to permit it.
 * <li> The requested content could not be found in the network.
 * <li> The absence of a <code>ServiceContentHandler</code> 
 *      required to present the requested service.
 * <li> Problems with tuning.
 * </ul>
 * 
 * Such presentation failures are not considered selection failures.
 * 
 * <p>
 * This event will be generated instead of <code>PresentationTerminatedEvent</code>
 * where normal content presentation could not continue due to the following
 * situations:
 * 
 * <ul>
 * <li> The parental control settings prevent it.  
 * <li> The CA system refusing to permit it.
 * <li> Inability to locate the requested content on the network.
 * <li> The absence of a <code>ServiceContentHandler</code> 
 *      required to present the requested service.
 * <li> Change of tuning information.
 * </ul>
 * 
 * Such presentation failures do not terminate presentation and allow for 
 * restoration of normal content presentation after correction of the error
 * condition.
 *
 * <p>
 * Note: The set of reason codes defined in this class may be extended by subclasses.
 * Care should be taken to ensure that the values of newly-defined reason codes
 * are unique.
 * 
 * 
 * @see SelectionFailedEvent
 * @see PresentationTerminatedEvent
 * @see AlternativeContentEvent
 *
 * @author Aaron Kamienski
 */
public class AlternativeContentErrorEvent 
    extends AlternativeContentEvent
{
    /**
     * Reason code: Normal content could not be presented due to a 
     * parental control rating problem.
     */
    public static final int RATING_PROBLEM = 100;
    
    /**
     * Reason code: Normal content could not be presented due to the
     * CA system refusing to permit it. 
     */
    public static final int CA_REFUSAL = 101;

    /**
     * Reason code : Normal content could not be presented because the requested 
     * content could not be found in the network.
     */
    public static final int CONTENT_NOT_FOUND = 102;
    
    /**
     * Reason code : Normal content could not be presented due to absence of a 
     * <code>ServiceContentHandler</code> required to present the requested
     * service's content.
     *
     * @see ServiceContentHandler
     */
    public static final int MISSING_HANDLER = 103;
    
    /**
     * Reason code : Normal content could not be presented due to problems with tuning.
     * This includes lack of tuning information as well as errors encountered during
     * tuning.
     */
    public static final int TUNING_FAILURE = 104;

    /**
     * Constructs an event with a reason code.
     * 
     * @param source The <code>ServiceContext</code> that generated the event.
     * @param reason The reason why alternative content is being presented.
     */
    public AlternativeContentErrorEvent(ServiceContext source, int reason)
    {
        super(source);
    }
    
    /**
     * Reports the reason why alternative content is being presented.
     * 
     * @return The reason why alternative content is being presented.
     *         This SHALL be one of
     *         {@link #RATING_PROBLEM}, 
     *         {@link #CA_REFUSAL},
     *         {@link #CONTENT_NOT_FOUND},
     *         {@link #MISSING_HANDLER},
     *         or
     *         {@link #TUNING_FAILURE}.
     */
    public int getReason()
    {
        return 0;
    }
}
