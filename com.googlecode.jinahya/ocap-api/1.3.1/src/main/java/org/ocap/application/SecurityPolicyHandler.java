package org.ocap.application;

import java.security.PermissionCollection;

/**
 * <P>
 * This interface provides a callback handler to modify the Permissions 
 * granted to an application to be launched. An application that has a 
 * MonitorAppPermission("security") can have a concrete class that 
 * implements this interface and set an instance of it to the 
 * AppManagerProxy. 
 *</P><P>
 * The {@link SecurityPolicyHandler#getAppPermissions} method shall be 
 * called before the OCAP implementation launches any type of application 
 * (e.g. before class loading of any OCAP-J application). 
 * The application shall then be loaded and started with the set of Permissions 
 * that are returned as the return value of this method. 
 * </P>
 *
 * @see AppManagerProxy#setSecurityPolicyHandler
 */
public interface SecurityPolicyHandler
{
    /**
     * <P>
     * This callback method is used to modify the set of Permissions 
     * that is granted to an application to be launched. 
     * </P><P>
     * The OCAP implementation shall call this method before class 
     * loading of any application, if an instance of a class that 
     * implements the {@link SecurityPolicyHandler} interface is set to the 
     * {@link AppManagerProxy}. The permissionInfo parameter of 
     * this method contains the AppID of the application to be launched 
     * and a requested set of Permissions that consists of Permissions 
     * requested in a permission request file and Permissions requested 
     * for the unsigned application. 
     * This method can modify the requested set of 
     * Permissions and returns them as the return value. The OCAP 
     * implementation shall grant them to the application. 
     *</P><P>
     * The modified set of Permissions shall be a subset of the 
     * requested set of Permissions specified by the permissionInfo 
     * parameter, and shall be a superset of the set of the Permissions
     * granted to unsigned applications (as returned by
     * PermissionInformation.getUnsignedAppPermissions()). 
     *</P>
     *
     * @param   permissionInfo  The PermissionInformation that 
     *          specifies the application to be launched and its 
     *          requested set of Permissions that are requested in 
     *          a permission request file and requested for the 
     *          unsigned application. 
     *
     * @return  An instance of a subclass of the 
     *          java.security.PermissionCollection that contains a 
     *          modified set of Permissions to be granted to the 
     *          application specified by the permissionInfo parameter. 
     *          The modified set of Permissions (i.e., return value) 
     *          shall be granted to the application. If the 
     *          modified set of Permissions is not a subset of the 
     *          requested Permissions, or is not a superset of the
     *          set of the Permissions granted to unsigned applications
     *          (as returned by
     *          PermissionInformation.getUnsignedAppPermissions()),
     *          the OCAP implementation shall ignore the returned 
     *          PermissionCollection and shall grant the requested set 
     *          of Permissions to the application. 
     */
    public PermissionCollection getAppPermissions(PermissionInformation permissionInfo);
}
