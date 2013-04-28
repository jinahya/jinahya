package org.ocap.application;

import java.security.PermissionCollection;
import org.dvb.application.AppID;

/**
 * <P>
 * This class contains information to allow the monitor application
 * to choose the permissions to grant to an application.
 *
 * @see SecurityPolicyHandler
*/
public abstract class PermissionInformation
{
    /**
     * OCAP applications SHALL NOT use this constructor - it is provided
     * for internal use by the OCAP implementation.  The result of calling
     * this method from an application is undefined, and valid
     * implementations MAY throw any Error or RuntimeException.
     */
    protected PermissionInformation() {
    }


    /**
     * This method returns an AppID of an application to be granted a 
     * requested set of Permissions that is returned by the 
     * {@link PermissionInformation#getRequestedPermissions} method.
     *
     * @return The AppID instance of an application to be granted a 
     *          requested set of Permissions which is returned by the 
     *          {@link PermissionInformation#getRequestedPermissions}
     *          method.
     *
     * @throws  SecurityException if the caller does not have 
     *          MonitorAppPermission("security"). 
     */
    public abstract AppID getAppID();
     
    /**
     * Returns true if and only if the application identified by the AppID
     * returned by the getAppID() is a Host Device Manufacturer applications.
     *
     * @return true if and only if the application identified by the AppID
     *         returned by the getAppID() is a Host Device Manufacturer
     *         application.
     */
    public abstract boolean isManufacturerApp();

    /**
     * Returns the set of valid certificates that were used to sign the 
     * application identified by the AppID returned by the getAppID() method.  
     * <p>
     * Note that for Host Device Manufacturer applications, this may
     * be an empty array.
     * </p><p>
     * For unsigned applications, this shall be an empty array.
     * </p>
     *
     * @return The return value is a two dimensional array of certificates 
     *         where each member of the outer dimension represents 
     *         a certificate chain that authenticates the application. 
     *         The order of certificate chains in the outer array is 
     *         unspecified. Each member of the inner dimension contains 
     *         a certificate in the chain with the root certificate in 
     *         the first member and the end-entity certificate in the 
     *         final member of the array. Each certificate in the inner 
     *         array authenticates the certificate contained in the next 
     *         array member.
     */
    public abstract java.security.cert.Certificate[][] getCertificates ();

    /**
     * Verifies that an end-entity certificate used to validate and application 
     * or file is a member of the list of privileged certificates in the privileged
     * certificate descriptor.  
     *
     * @param cert  The X.509 certificate that is to be checked against the list 
     *              of privileged certificates in the privileged certificate 
     *              descriptor.
     * @return The return value is set to true if the SHA-1 hash of the supplied
     *         certificate matches one of the hash values listed in the 
     *         privileged certificate descriptor.
     */
    public abstract boolean isPrivilegedCertificate(java.security.cert.Certificate cert);

    /**
     * This method returns the set of Permissions that are requested by 
     * all unsigned applications.  The contents of this set of permissions
     * is defined elsewhere in this specification.
     *
     * @return  A read-only instance of a sub class of PermissionCollection 
     *          containing the set of Permissions for an unsigned application.
     */
    public static PermissionCollection getUnsignedAppPermissions()
   {
        return null;
   }
    
    /**
     * This method returns the requested set of Permissions for the 
     * application specified by the AppID that is returned by the 
     * {@link PermissionInformation#getAppID} method. 
     * <p>
     * For Host Device Manufacturer applications, this is the set of
     * permissions requested for the application by the Host Device
     * Manufacturer.  Note that this may include manufacturer-specific
     * permissions (e.g. a manufacturer-specific permission to access
     * a DVD player API).
     * </p><p>
     * For other applications, the requested set of Permissions consists 
     * of Permissions that are requested in a permission request 
     * file and Permissions requested for unsigned applications. 
     * </p><p>
     * Note that the requested set of Permissions always includes the
     * permissions requested for unsigned applications, as returned
     * by getUnsignedAppPermissions().
     * </p>
     *
     * @return  An instance of a sub class of the PermissionCollection 
     *          containing the requested set of Permissions for an 
     *          application to be launched. The application is 
     *          specified by the AppID returned by the 
     *          {@link PermissionInformation#getAppID} 
     *          method. 
     */
    public abstract PermissionCollection getRequestedPermissions();

}
