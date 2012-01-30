package org.ocap.hn.security;

import java.net.InetAddress;
import java.net.URL;
import org.ocap.hn.NetManager;
import org.ocap.hn.NetworkInterface;

/**
 * This class provides access to home network security capabilities including
 * password handling.  The passwords that can be handled are specific to each
 * home network interface at the link layer.  Upper layer (e.g. TLS) and
 * Administrator passwords cannot be accessed using this class.  When the
 * network interface type returned by {@link NetworkInterface#getType}
 * is MOCA the implementation SHALL associate the getNetworkPassword and
 * setNetworkPassword methods in this interface to the MoCA link layer password
 * used for the network interface.  When the network interface type returned by
 * {@link NetworkInterface#getType} is WIRELESS_ETHERNET the
 * implementation SHALL associate the getNetworkPassword and setNetworkPassword
 * in this interface to the link layer password, e.g. WEP, used for the
 * network interface.
 */
public abstract class NetSecurityManager
{

    /**
     * Protected constructor.
     */
    protected NetSecurityManager()
    {
    }

    /**
     * Get the network security manager.
     */
    public static NetSecurityManager getInstance()
    {
        return null;
    }

    /**
     * Gets a network interface password.
     *
     * @param networkInterface The interface to get the password for.
     *
     * @return The value of the password requested, or 0 length String if
     *      no password is set for the interface.  If the interface type is
     *      MoCA this method returns a string value equal to the corresponding
     *      mocaIfPassword MIB.  In this case the password MAY have been set
     *      using means other than the <code>setNetworkPassword</code> method.
     *
     * @throws UnsupportedOperationException if a password cannot be retrieved for
     *      the network interface.
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public String getNetworkPassword(org.ocap.hn.NetworkInterface networkInterface)
    {
        return null;
    }

    /**
     * Sets a network interface password.  If the network interface type is
     * MoCA then privacy must also be enabled for the password to have affect.
     * See the <code>enableMocaPrivacy</code> method.  If the interface type
     * is MoCA and the parameter is acceptable this method writes the
     * corresponding mocaIfPassword MIB.
     *
     * @param networkInterface The home network interface the password is
     *      to be set for.
     * @param password The value of the password to set.
     *
     * @throws IllegalArgumentException if the password format
     *      is invalid for the interface type.  A password for a MoCA interface
     *      that is less than 12 characters or greater than 17 characters or
     *      has any non-numerical characters is invalid.
     * @throws UnsupportedOperationException if a password cannot be set for
     *      the network interface.
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public void setNetworkPassword(org.ocap.hn.NetworkInterface networkInterface,
                                   String password)
    {
    }

    /**
     * Sets the authorization handler application.  If a handler application
     * is already registered this method SHALL replace it.  If the parameter
     * is null any set handler is removed.
     *
     * @param nah Network authorization handler interface to the authoriztaion
     *      application.
     *
     * @throws SecurityException if the calling application is not granted
     *      MonitorAppPermission("handler.network").
     */
    public void setAuthorizationHandler(NetAuthorizationHandler nah)
    {
    }

    /**
     * Sets the authorization handler application.  If a handler application
     * is already registered this method SHALL replace it.  If the nah 
     * parameter is null any set handler is removed.
     *
     * @param nah Network authorization handler interface to the authoriztaion
     *      application.
     * @param actionNames A list of action names the hander is interested in
     *      authorizing.  The format of the names is out-of-scope for this
     *      definition.
     * @param notifyTransportRequests A true value indicates the
     *      <code>NetAuthorizationHandler</code> application is always
     *      notified.  A false value indicates that once a message in a
     *      session has been authorized subsequent messages in the same session
     *      are not authorized.  That is, the
     *      <code>NetAuthorizationHandler</code> application is not notified
     *      when the subsequent messages are received.
     *
     * @throws SecurityException if the calling application is not granted
     *      MonitorAppPermission("handler.network").
     * @throws IllegalArgumentException if the actionNames parameter contains
     *      a name that cannot be matched to a known action.
     */
    public void setAuthorizationHandler(NetAuthorizationHandler nah,
                                        String [] actionNames,
                                        boolean notifyTransportRequests)
    {
    }

    /**
     * Revokes a session authorization granted by the authorization handler.
     *
     * @param activityID The activity specific identifier that was passed
     *      to the
     *      <code>NetAuthorizationHandler.notifyAuthorization</code> method.
     *
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("handler.network").
     */
    public void revokeAuthorization(int activityID)
    {
    }

    /**
     * Queries the implementation to determine if it has sent a transaction
     * matching the parameters.
     *
     * @param actionName Name of the request type if known.  If not known an
     *      empty string MAY be used. The format of the name is out-of-scope
     *      of this definition.
     * @param inetAddress IP address the transaction was sent to.
     * @param macAddress MAC address the transaction was sent from if known.
     *      Can be empty <code>String</code> if not known.  The format is
     *      EUI-48 with 6 colon separated 2 digit bytes in hexadecimal notation
     *      with no leading "0x", e.g. "00:11:22:AA:BB:CC".
     * @param url The URL requested by the transaction if known.  If not known
     *      an empty string may be used.
     * @param activityId The activity identifier this device set for the
     *      connection.  A value of -1 indicates the parameter will not be
     *      used for transaction matching purposes.
     *
     * @return True if activityId and other known parameters can be matched to
     *      a transaction sent by the implementation.  If activityId match
     *      cannot be found, or if activityId match is found but any of the
     *      other known parameters do not match the transaction then this
     *      method returns false.
     *
     * @throws IllegalArgumentException if the MAC address is malformed.
     * @throws SecurityException if the calling application is not signed.
     */
    public boolean queryTransaction(String actionName,
                                    InetAddress inetAddress,
                                    String macAddress,
                                    URL url,
                                    int activityId)
    {
        return false;
    }

    /**
     * Enables MoCa privacy.  For MoCA interface types this method enables
     * privacy and writes the corresponding mocaIfPrivacyEnable MIB with a
     * value of 'true'.
     *
     * @param networkInterface Interface to enable privacy on.
     *
     * @throws UnsupportedOperationException if the parameter interface is not
     *      a MoCA interface type.
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public void enableMocaPrivacy(org.ocap.hn.NetworkInterface networkInterface)
    {
    }

    /**
     * Disables MoCA privacy.  For MoCA interface types this method disables
     * privacy and writes the corresponding mocaIfPrivacyEnable MIB with a
     * value of'false'.
     *
     * @param networkInterface Interface to disable privacy on.
     *
     * @throws UnsupportedOperationException if the parameter interface is not
     *      a MoCA interface type.
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public void disableMocaPrivacy(org.ocap.hn.NetworkInterface networkInterface)
    {
    }
}