package org.ocap.hn.security;

import java.net.InetAddress;
import java.net.URL;

/**
 * This interface represents a callback mechanism to a registered network
 * authorization handler.
 */
public interface NetAuthorizationHandler
{

    /**
     * Notifies the registered authorization handler that an
     * activity to access cable services has been started.  The handler
     * will permit or deny the ability for the activity to
     * continue.
     *
     * @param inetAddress IP address the transaction was sent from.
     * @param macAddress An empty string; this parameter is not used.
     * @param url The <code>URL</code> requested by the transaction.
     * @param activityID The unique identifier of the activity.
     *
     * @return true if the activity is accepted; false otherwise.
     */
    public boolean notifyActivityStart(InetAddress inetAddress,
                                       String macAddress,
                                       URL url,
                                       int activityID);

    /**
     * Notifies the registered authorization handler that an
     * activity has ended.
     *
     * @param activityID Unique identifier assigned to the activity and
     *      passed to the <code>notifyActivityStart</code> method.
     */
    public void notifyActivityEnd(int activityID);

    /**
     * Notifies the authorization handler that an action it
     * registered interest in has been received.
     *
     * @param actionName Name of the action received.  Will match a name
     *      in the {@code actionNames} parameter previously passed to
     * {@link NetSecurityManager#setAuthorizationHandler(NetAuthorizationHandler, String[], boolean)}.
     * @param inetAddress IP address the transaction was sent from.
     * @param macAddress An empty string; this parameter is not used.
     * @param activityID The unique identifier of the activity if known.  If
     *      no activityID is associated with the transaction the
     *      implementation SHALL pass a value of -1;
     *
     * @return True if the activity is accepted, otherwise returns false.
     */
    public boolean notifyAction(String actionName,
                                InetAddress inetAddress,
                                String macAddress,
                                int activityID);
}
