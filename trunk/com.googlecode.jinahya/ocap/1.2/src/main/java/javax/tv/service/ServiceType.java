/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.tv.service;

/** 
 * This class represents service type values such as "digital television",
 * "digital radio", "NVOD reference service", "NVOD time-shifted service",
 * "analog television", "analog radio", "data broadcast" and "application".
 * These basic service types can be extended by subclassing.<p>
 *
 * (These values are mappable to the ATSC service type in the VCT table and
 * the DVB service type in the Service Descriptor.)
 */
public class ServiceType
{
    /** 
     * Digital TV service type.
     */
    public static final ServiceType DIGITAL_TV = null;

    /** 
     * Digital radio service type.
     */
    public static final ServiceType DIGITAL_RADIO = null;

    /** 
     * NVOD reference service type.
     */
    public static final ServiceType NVOD_REFERENCE = null;

    /** 
     * NVOD time-shifted service type.
     */
    public static final ServiceType NVOD_TIME_SHIFTED = null;

    /** 
     * Analog TV service type.
     */
    public static final ServiceType ANALOG_TV = null;

    /** 
     * Analog radio service type.
     */
    public static final ServiceType ANALOG_RADIO = null;

    /** 
     * Data broadcast service type identifying a data service.
     */
    public static final ServiceType DATA_BROADCAST = null;

    /** 
     * Data application service type identifying an interactive application.
     */
    public static final ServiceType DATA_APPLICATION = null;

    /** 
     * Unknown service type.
     */
    public static final ServiceType UNKNOWN = null;

    /** 
     * Creates a service type object.
     *
     * @param name The string name of this type (e.g., "DIGITAL_TV").
     */
    protected ServiceType(String name) { }

    /** 
     * Provides the string name of the type.  For the type objects
     * defined in this class, the string name will be identical to the
     * class variable name.
     *
     * @return The string name of the type.
     */
    public String toString() {
        return null;
    }
}
