// RecordedServiceType.java

package org.ocap.shared.dvr;

import javax.tv.service.ServiceType;

/**
 * This class represents the service type value for a RecordedService.
 */
public class RecordedServiceType
    extends ServiceType
{
    /**
     * ServiceType for a Recorded service.
     */
    public static final javax.tv.service.ServiceType
        RECORDED_SERVICE_TYPE = new RecordedServiceType("RECORDED_SERVICE");

    /**
     * Provides an instance of RecordedServiceType.
     *
     * @param name The string name of this type (i.e. "RECORDED_SERVICE").
     */
    protected RecordedServiceType (String name){
        super(name);
    }
}

