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



  


package javax.tv.service.selection;

/** 
 * <code>SelectionFailedEvent</code> is generated when a service
 * selection operation fails.  <code>SelectionFailedEvent</code> is
 * not generated when a service selection fails with an exception. <p>
 *
 * Presentation failures enforced via a conditional access system may
 * be reported by this event (with the reason code CA_REFUSAL) or by
 * <code>AlternativeContentEvent.</code> Which of these is used
 * depends on the precise nature of the conditional access
 * system. Applications must allow for both modes of failure.
 *
 * @see AlternativeContentEvent
 */
public class SelectionFailedEvent extends ServiceContextEvent
{
    /** 
     * Reason code : Selection has been interrupted by another selection
     * request.
     */
    public static final int INTERRUPTED = 1;

    /** 
     * Reason code : Selection failed due to the CA system refusing to
     * permit it.
     */
    public static final int CA_REFUSAL = 2;

    /** 
     * Reason code : Selection failed because the requested content
     * could not be found in the network.
     */
    public static final int CONTENT_NOT_FOUND = 3;

    /** 
     * Reason code : Selection failed due to absence of a 
     * <code>ServiceContentHandler</code> required to present the requested
     * service.
     *
     * @see ServiceContentHandler
     */
    public static final int MISSING_HANDLER = 4;

    /** 
     * Reason code : Selection failed due to problems with tuning.
     */
    public static final int TUNING_FAILURE = 5;

    /** 
     * Reason code : Selection failed due to a lack of resources required to
     * present this service.
     */
    public static final int INSUFFICIENT_RESOURCES = 6;

    /** 
     * Reason code: Selection failed due to an unknown reason or for multiple
     * reasons.
     */
    public static final int OTHER = 255;

    /** 
     * Constructs the event with a reason code.
     *
     * @param source The <code>ServiceContext</code> that generated the event.
     * @param reason The reason why the selection failed.
     */
    public SelectionFailedEvent(ServiceContext source, int reason) { 
        super(source);    
    }

    /** 
     * Reports the reason why the selection failed.
     *
     * @return The reason why the selection failed.
     */
    public int getReason() {
        return 0;
    }
}
