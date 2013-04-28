// AbstractServiceType.java

package org.ocap.service;

/**
 * This class represents the abstract service type value.
 */
public class AbstractServiceType
    extends javax.tv.service.ServiceType
{
    /**
     * ServiceType for an abstract service.
     */
    public static final javax.tv.service.ServiceType
        OCAP_ABSTRACT_SERVICE = javax.tv.service.ServiceType.UNKNOWN;

    /**
     * Provides an instance of AbstractServiceType.
     *
     * @param name The string name of this type (i.e. "OCAP_ABSTRACT_SERVICE").
     */
    protected AbstractServiceType (String name){
        super(name);
    }
}
