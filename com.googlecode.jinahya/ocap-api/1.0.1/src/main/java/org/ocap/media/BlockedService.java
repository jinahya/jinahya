package org.ocap.media;

import javax.tv.service.Service;
import java.util.Date;

/**
 * This class represents a service that is blocked from a start time for a 
 * specific duration.  Whenever a <code>Service</code> or 
 * <code>ServiceComponent</code> from a service that is blocked are selected
 * for new or changed presentation in a service context or a discrete player,
 * the implementation SHALL block the corresponding presentation. When any
 * service components in a blocked service are selected in a service context
 * or discrete player they are blocked.
 * <p>
 * A blocked service is associated with an application specific level.  This
 * blocking level is not associated with a specific content rating system, but
 * MAY be used by a <code>MediaAccessHandler</code> to set up different levels
 * of blocking.
 */
public class BlockedService
{
    /**
     * Constructor.
     * 
     * @param startTime Wall clock time to start blocking the service.
     * @param duration Amount of time in milli-seconds to block the service.
     * @param service Service to block.
     * @param askMediaAccessHandler When true the implementation SHALL call a
     *  registered <code>MediaAccessHandler.checkMediaAccessAuthorization</code>
     *  method.
     * @param level The blocking level. 
     * 
     * @throws IllegalArgumentException if the duration is less than or equal to
     *      zero.
     */
    public BlockedService(Date startTime,
                          long duration,
                          Service service,
                          boolean askMediaAccessHandler,
                          int level)
    {
    }

    /**
     * Gets the wall clock start time to begin service blocking.
     * 
     * @return Blocked service start time.
     */
    public Date getStartTime()
    {
        return null;
    }

    /**
     * Gets the duration to block the service.
     * 
     * @return The duration in milli-seconds to block the service.
     */
    public long getDuration()
    {
        return 0;
    }

    /**
     * Gets the blocked <code>Service</code>.
     * 
     * @return The service to be blocked.
     */
    public Service getService()
    {
        return null;
    }

    /**
     * Gets the action when a service is blocked.
     * 
     * @return True when a registered
     * <code>{@link MediaAccessHandler#checkMediaAccessAuthorization}</code> is
     * to be called and returns false when the service is to be blocked without
     * calling the <code>MediaAccessHandler</code>
     */
    public boolean isAskMediaAccessHandler()
    {
        return false;
    }

    /**
     * Gets the level of blocking.  
     * 
     * @return The blocking level.
     */
    public int getLevel()
    {
        return 0;
    }

}
