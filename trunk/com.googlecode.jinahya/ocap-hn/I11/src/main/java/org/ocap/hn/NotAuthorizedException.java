package org.ocap.hn;

/**
 * NotAuthorizedException
 *
 * @author			Luyang Li (lly@research.panasonic.com)
 * @version			1.0
 *
 */

/**
 * Exception indicating that the application has no permission to perform 
 * certain action. 
 */
public class NotAuthorizedException extends Exception {
    /**
     * Constructs a NotAuthorizedException object.
     */
    public NotAuthorizedException() {
        super();
    }

    /**
     * Constructs a NotAuthorizedException object with a reason.
     * @param reason	reason for this exception
     */
    public NotAuthorizedException(String reason) {
        super(reason);
    }
}