package org.dvb.application.storage;

/**
 * The service type used for stored application services.
 *
 * @see javax.tv.service.navigation.ServiceTypeFilter
 * @since MHP 1.1.2
 */
public class StoredApplicationServiceType extends
    javax.tv.service.ServiceType {

    /**
     * The service type for a stored application.
     *
     * @since MHP 1.1.2
     */
    public static final javax.tv.service.ServiceType STORED_APPLICATION_SERVICE = 
            new StoredApplicationServiceType("STORED_APPLICATION_SERVICE");

    /**
     * Creates a new StoredServiceType object.
     *
     * @param name The string name of this type (e.g.
     * "STORED_APPLICATION_SERVICE").
     *
     * @since MHP 1.1.2
     */
    protected StoredApplicationServiceType(String name) {
        super(name);
    }
}

