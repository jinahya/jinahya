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
 * This class represents reason codes for failure of asynchronous SI
 * retrieval requests.  It is subclassed to provide the individual
 * reason codes.
 *
 * @see SIRequestor#notifyFailure
 * @see SIRequest
 */
public class SIRequestFailureType
{
    /** 
     * The reason generated when the <code>SIRequest</code> is canceled.
     *
     * @see SIRequest#cancel
     */
    public static final SIRequestFailureType CANCELED = null;

    /** 
     * The reason generated when the resources required to fulfill an
     * asynchronous SI retrieval (such as a tuner, section filter, etc.)
     * are unavailable. The application may attempt to release some
     * resources and attempt the request again.
     */
    public static final SIRequestFailureType INSUFFICIENT_RESOURCES = null;

    /** 
     * The reason generated when the system cannot find the
     * requested data. This occurs when the
     * requested data is not present in the transport stream, when the
     * data is present on some transport/network but the SI database
     * does not know about it, when the type of requested data is
     * not supported by the broadcast environment, or when it is not possible
     * to retrieve the requested SI element within a reasonable period of time.
     */
    public static final SIRequestFailureType DATA_UNAVAILABLE = null;

    /** 
     * The reason for the failure is unknown.
     */
    public static final SIRequestFailureType UNKNOWN = null;

    /** 
     * Creates an <code>SIRequestFailureType</code> object.
     *
     * @param name The string name of this type (e.g., "CANCELED").
     */
    protected SIRequestFailureType(String name) { }

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
