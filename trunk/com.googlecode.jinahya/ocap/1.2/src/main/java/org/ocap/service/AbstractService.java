// AbstractService.java

package org.ocap.service;

/**
 * The AbstractService represents a non-broadcast Service which
 * applications can be added to and be selected as a group
 * within a given javax.tv.service.selection.ServiceContext.
 * <p>
 * Note the following subinterface-specific behavior for methods
 * defined by the javax.tv.service.Service superinterface:
 * <ul>
 * <li> The hasMultipleInstances() method shall always return false.
 * <li> The getServiceType() method shall always return
 *      AbstractServiceType.OCAP_ABSTRACT_SERVICE.
 * </ul>
 */
public interface AbstractService
    extends javax.tv.service.Service
{
   
    /**
     * Returns a java.util.Enumeration of references to
     * org.dvb.application.AppID instances.
     * These application IDs correspond to applications that are
     * associated with this abstract service.
     *
     * @return the java.util.Enumeration of application IDs included in
     * this AbstractService
     */
    java.util.Enumeration getAppIDs ();
    
    /**
     * Returns a java.util.Enumeration of references to
     * org.ocap.application.OcapAppAttributes instances.
     * These application attributes correspond to applications that are
     * associated with this abstract service.
     *
     * @return the java.util.Enumeration of application attributes for
     * applications included in this AbstractService.
     */
    java.util.Enumeration getAppAttributes ();       


}
