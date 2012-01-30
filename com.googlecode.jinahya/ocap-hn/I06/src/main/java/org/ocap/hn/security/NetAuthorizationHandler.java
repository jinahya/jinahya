package org.ocap.hn.security;

import java.net.InetAddress;
import java.net.URL;

/**
 * This interface represents a callback mechanism to a registered network
 * authorization handler application.
 */
public interface NetAuthorizationHandler
{

    /**
     * Notifies the registered authorization handler application that an
     * activity to access cable services has been started.  The handler
     * application will accept or deny the ability for the activity to
     * continue.
     *
     * @param inetAddress IP address the transaction was sent from.
     * @param macAddress MAC address the transaction was sent from if present
     *      at any layer of the received communications protocol.  Can be empty
     *      <code>String</code> if not present.  The format is EUI-48 with 6
     *      colon separated 2 digit bytes in hexadecimal notation with no
     *      leading "0x", e.g. "00:11:22:AA:BB:CC".
     * @param url The <code>URL</code> requested by the transaction.
     * @param activityId The unique identifier of the activity, e.g. recorded
     *      content playback.
     *
     * @return True if the activity is accepted, otherwise returns false.
     */
    public boolean notifyActivityStart(InetAddress inetAddress,
                                       String macAddress,
                                       URL url,
                                       int activityId);

    /**
     * Notifies the registered authorization handler application that an
     * activity has ended.
     *
     * @param activityId Unique identifier assigned to the activity and
     *      passed to the <code>notifyActivityStart</code> method.
     */
    public void notifyActivityEnd(int activityId);

    /**
     * Notifies the authorization handler application that an action it
     * registered interest in has been received.
     *
     * @param actionName Name of the action received.  Will match the name
     *      passed to the
     *      <code>NetSecurityManager.setAuthorizationApplication</code> method.
     * @param inetAddress IP address the transaction was sent from.
     * @param macAddress MAC address the transaction was sent from if present
     *      at any layer of the received communications protocol.  Can be empty
     *      <code>String</code> if not present.  The format is EUI-48 with 6
     *      colon separated 2 digit bytes in hexadecimal notation with no
     *      leading "0x", e.g. "00:11:22:AA:BB:CC".
     * @param activityId The unique identifier of the activity if known.  If
     *      no activityId is association with the transaction the
     *      implementation SHALL pass a value of -1;
     *
     * @return True if the activity is accepted, otherwise returns false.
     */
    public boolean notifyAction(String actionName,
                                InetAddress inetAddress,
                                String macAddress,
                                int activityId);
}
