package org.ocap.media;

import org.ocap.net.OcapLocator;
import java.util.Date;

/**
 * A <code>{@link MediaAccessAuthorization}</code> object represents the
 * presentation authorization given by a registered
 * <code>MediaAccessHandler</code> for a specific A/V content. When the
 * <code>{@link MediaAccessHandler}</code> is called by the OCAP
 * implementation, it returns a <code>{@link MediaAccessAuthorization}</code>
 * to indicate presentation authorization, and a reason for denied access
 * (use constant defined in <code>AlternativeMediaPresentationReason</code>).
 * <p>
 * 
 * @see MediaAccessHandler
 * @see AlternativeMediaPresentationReason
 */
public interface MediaAccessAuthorization
{
    /**
     * Indicates if the pending presentation changes are denied.
     * This method MAY allow authorization for blocked programs, but does
     * not affect conditional access entitlements.
     *
     * @return True if the presentation is denied.
     */
    public boolean isDenied();

    /**
     * Returns the reason when presentation is denied.  Denial reasons are
     * defined in <code>{@link AlternativeMediaPresentationReason}</code>.
     * 
     * @return The reason for presentation denial.
     */
    public int getDenialReason();

    /**
     * Gets the wall clock start time to begin service blocking.  The time
     * returned over-rides any corresponding <code>BlockingService</code>
     * start-time.
     * 
     * @return Blocked service start time.
     */
    public Date getStartTime();
    /**
     * Gets the duration to block the service.  The duration returned
     * over-rides any corresponding <code>BlockingService</code>
     * duration.
     * 
     * @return The duration in milli-seconds to block the service.
     */
    public long getDuration();
}

