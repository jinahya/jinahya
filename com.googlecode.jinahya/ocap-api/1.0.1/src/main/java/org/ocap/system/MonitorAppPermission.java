package org.ocap.system;

/**
 * The MonitorAppPermission class represents permission to execute privileged
 * operations only Monitor Application should be granted.
 * <p>
 * A MonitorAppPermission consists of a permission name, representing
 * a single privileged operation.
 * The name given in the constructor may end in ".*" to represent
 * all permissions beginning with the given string, such as <code>"*"</code>
 * to allow all MonitorAppPermission operations, or
 * <code>"handler.*"</code> to only allow setting any handler.
 * <p>
 * The following table lists all MonitorAppPermission permission names.
 * <table border=1 cellpadding=5>
 * <tr>
 *   <th>Permission Name</th>
 *   <th>What the Permission Allows</th>
 *   <th>Description</th>
 * </tr>
 *
 * <tr>
 *   <td>registrar</td>
 *   <td>Provides access to the Application Database by way of the AppRegistrar</td>
 *   <td>This permission allows the caller to add or remove applications from
 *      the Application Database.</td>
 * </tr>
 *
 * <tr>
 *   <td>service</td>
 *   <td>Allows creation of an AbstactService</td>
 *   <td>Applications with this permission can create and manage their own service
 *      contexts and the services running in those service contexts. </td>
 * </tr>
 *
 * <tr>
 *  <td>servicemanager</td>
 *  <td>Allows management of all services</td>
 *  <td>Applications with this permission can create their own service contexts
 *    and manage both their own and other service contexts and services.</td>
 *
 * <tr>
 *   <td>security</td>
 *   <td>Allows setting the {@link org.ocap.application.SecurityPolicyHandler} 
 *       used by the AppManager</td>
 *   <td>This permission allows the application to register a SecurityPolicyHandler
 *      with the AppManager to determine the PermissionCollection granted to
 *      applications before they are run.</td>
 * </tr>
 *
 * <tr>
 *   <td>reboot</td>
 *   <td>Initiates a system to reboot itself</td>
 *   <td>This permission allows the caller to request for a system reboot.</td>
 * </tr>
 *
 * <tr>
 *   <td>systemevent</td>
 *   <td>Allows setting the error, resource depletion, or reboot handlers</td>
 *   <td>This permission allows the Monitor Application to be alerted upon 
 *       system reboot, resource depletion, and error events.</td>
 * </tr>
 *
 * <tr>
 *   <td>handler.appFilter</td>
 *   <td>Set a new black and white list to the system</td>
 *   <td>This permission allows the application to set a new black and
 *      white list, which the system uses to determine whether to accept or
 *      reject broadcasted applications on the receiver.  Such control should
 *      only be granted to a monitor application.</td>
 * </tr>
 *
 * <tr>
 *   <td>handler.resource</td>
 *   <td>Set a Resource Contention Handler</td>
 *   <td>Set a handler to resolve resource contention between two or more apps
 *   see {@link org.ocap.resource.ResourceContentionManager#setResourceContentionHandler}. </td>
 * </tr>
 *
 * <tr>
 *   <td>handler.closedCaptioning</td>
 *   <td>Set closed-captioning preferences and control captioning. </td>
 *   <td>Allows monitor application to get a 
 *       {@link org.ocap.media.ClosedCaptioningAttribute} 
 *       and call methods in a 
 *       {@link org.ocap.media.ClosedCaptioningControl}. </td>
 * </tr>
 *
 * <tr>
 *   <td>filterUserEvents</td>
 *   <td>Filter user events</td>
 *   <td>This permission allows the user to filter user events.</td>
 * </tr>
 *
 * <tr>
 *   <td>handler.eas</td>
 *   <td>Set preferences of Emergency Alert System (EAS) message 
 *       representation. </td>
 *   <td>Allows monitor application to set preferences of EAS message 
 *       representation and add a new EAShandler by calling
 *       {@link org.ocap.system.EASModuleRegistrar#registerEASHandler}.</td>

 * </tr>
 *
 * <tr>
 *   <td>setVideoPort</td>
 *   <td>Allows enabling and disabling video port</td>
 *   <td>Allows monitor to call org.ocap.hardware.VideoOutputPort.enable() and
 *       org.ocap.hardware.VideoOutputPort.disable().</td>
 * </tr>
 *
 * <tr>
 *   <td>podApplication</td>
 *   <td>Allows assuming a Private Host Application Specific Application Support 
 *       Resource</td>
 *   <td>Allows Monitor Application to call org.ocap.system.SystemModuleRegistrar.</td>
 * </tr>
 *
 * <tr>
 *   <td>signal.configured</td>
 *   <td>Allows monitor to signal implementation to resume boot processing after
 *       handlers have been set</td>
 *   <td>Allows monitor to call org.ocap.OcapSystem.monitorConfiguredSignal().</td>
 * </tr>
 * 
 * <tr>
 *   <td>storage</td>
 *   <td>Provides control of persistent storage devices and content stored therein</td>
 *   <td>Allows monitor to delete volumes it does not own, initialize 
 *       {@link org.ocap.storage.StorageProxy} associated devices, make detachable devices ready 
 *       for detaching or ready for use, and set file access permissions for 
 *       any application accessible file or directory.</td>
 * </tr>
 *
 * <tr>
 *   <td>properties</td>
 *   <td>Allows monitor to access ocap system properties</td>
 *   <td>Allows monitor to call read ocap properties that require monitor application permission.</td>
 * </tr>
 *
 * <tr>
 *   <td>registeredapi.manager</td>
 *   <td>Provides access to network specific APIs</td>
 *   <td>Gives monitor ability to register and remove a registered API.</td>
 * </tr>
 *
 * <tr>
 *   <td>vbifiltering</td>
 *   <td>Allows monitor application to filter VBI data. </td>
 *   <td>Allows monitor application to call a VBIFilterGroup constructor.</td>
 * </tr>
 *
 * <tr>
 *   <td>codeDownload</td>
 *   <td>Allows monitor application to initiate a download, generally following
 * 	 a CVT signaling a deferred download </td>
 *   <td>Allow monitor application to call Host.codeDownload method.</td>
 * </tr>
 *
 *<tr>
 *   <td>mediaAccess</td>
 *   <td>Allows monitor application to register MediaAccessHandler.</td>
 *   <td>Allows monitor application to call a 
 *       MediaAccessHandlerRegistrar.setExternalTriggers(). Allows monitor 
 *       application to call a 
 *       MediaAccessConditionControl.conditionHasChanged().</td>
 *</tr>
 *</table>
 *
 * Other permissions may be added as necessary.
 */
public final class MonitorAppPermission extends java.security.BasicPermission
{

    /**
     * Constructor for the MonitorAppPermission
     *
     * @param name   the name of this permission (see table in class description)
     */
    public MonitorAppPermission (String name)
    {
        super(name);
    }
}
