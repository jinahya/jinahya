package org.ocap.hn.security;

import java.net.InetAddress;
import java.net.URL;
import org.ocap.hn.NetworkInterface;
import org.ocap.hn.content.ContentEntry;

/**
 * This interface represents a callback mechanism to a registered network
 * authorization handler.
 */
public interface NetAuthorizationHandler2
{

    /**
     * Notifies the registered authorization handler that an
     * activity to access cable services has been started.  The handler
     * will permit or deny the ability for the activity to
     * continue.
     *
     * @param inetAddress IP address the transaction was sent from.
     * @param url The <code>URL</code> requested by the transaction.
     * @param activityID The unique identifier of the activity.
     * @param entry The <code>ContentEntry</code> corresponding to the
     *      <code>url</code> parameter.  If no entry can be uniquely matched
     *      then null is passed in.
     * @param request The HTTP message request;
     *                i.e., the request line and subsequent message headers.
     * @param networkInterface The <code>NetworkInterface</code> the session
     *      identified by <code>activityID</code> took place on.  The 
     *      <code>getInetAddress</code> method of this parameter SHALL return 
     *      the <code>InetAddress</code> on which the <code>request</code> was 
     *      received.
     *
     * @return true if the activity is accepted; false otherwise.
     */
    public boolean notifyActivityStart(InetAddress inetAddress,
                                       URL url,
                                       int activityID,
                                       ContentEntry entry,
                                       String[] request,
                                       NetworkInterface networkInterface);

    /**
     * Notifies the registered authorization handler that an
     * activity has ended.
     *
     * @param activityID Unique identifier assigned to the activity and
     *      passed to the <code>notifyActivityStart</code> method.
     * @param resultCode Value indicating success or failure.
     */
    public void notifyActivityEnd(int activityID,
                                  int resultCode);

    /**
     * Notifies the authorization handler that an action it
     * registered interest in has been received.
     *
     * @param actionName Name of the action received.  Will match a name
     *      in the {@code actionNames} parameter previously passed to
     * {@link NetSecurityManager#setAuthorizationHandler(NetAuthorizationHandler2, String[], boolean)}.
     * @param inetAddress IP address the transaction was sent from.
     * @param activityID The unique identifier of the activity if known.  If
     *      no activityID is associated with the transaction the
     *      implementation SHALL pass a value of -1;
     * @param request The HTTP message request;
     *                i.e., the request line and subsequent message headers.
     * @param networkInterface The <code>NetworkInterface</code> the session
     *      identified by <code>activityID</code> took place on. The 
     *      <code>getInetAddress</code> method of this parameter SHALL return the 
     *      <code>InetAddress</code> on which the <code>request</code> was received.
     *
     * @return true if the activity is accepted; false otherwise.
     */
    public boolean notifyAction(String actionName,
                                InetAddress inetAddress,
                                int activityID,
                                String[] request,
                                NetworkInterface networkInterface);
}
