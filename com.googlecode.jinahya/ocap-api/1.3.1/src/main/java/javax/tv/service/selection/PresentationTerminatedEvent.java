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
 * <code>PresentationTerminatedEvent</code> is generated when the
 * presentation of a service terminates.  This includes both normal
 * termination (e.g., due to an application calling the <code>stop()</code>
 * method) and abnormal termination (e.g., due to some change in the
 * environment). Examples of abnormal termination include:
 *
 * <ul>
 * <li>a tuning operation making the service unavailable </li>
 *
 * <li>removal of fundamental resources required to present the
 * service</li>
 *
 * <li>withdrawal of CA authorization</li>
 * </ul>
 *
 * <code>PresentationTerminatedEvent</code> is also generated
 * following a <code>SelectionFailedEvent</code> either if the service
 * context was not previously in the <em>presenting</em> state or if
 * recovery of what was being presented previously is not possible.
 * <code>PresentationTerminatedEvent</code> is only generated when no
 * components of the requested service can be presented.
 * <p>
 * When a <code>PresentationTerminatedEvent</code> is
 * generated following a failed selection attempt, the
 * reason code of the <code>PresentationTerminatedEvent</code>
 * is derived from the reason code for the
 * <code>SelectionFailedEvent</code> which preceded it, according
 * to the table below.
 * <p>
 * <table align="center" width="80%" border="1" cellspacing="2" cellpadding="2">
 * <tbody>
 * <tr>
 * <th align="center">SelectionFailedEvent reason code</th>
 * <th align="center">PresentationTerminatedEvent reason code</th>
 * </tr>
 * 
 * <tr>
 * <td>CA_REFUSAL</td>
 * <td>ACCESS_WITHDRAWN</td>
 * </tr>
 * 
 * <tr>
 * <td>CONTENT_NOT_FOUND</td>
 * <td>SERVICE_VANISHED</td>
 * </tr>
 * 
 * <tr>
 * <td>INSUFFICIENT_RESOURCES</td>
 * <td>RESOURCES_REMOVED</td>
 * </tr>
 * 
 * <tr>
 * <td>MISSING_HANDLER</td>
 * <td>RESOURCES_REMOVED</td>
 * </tr>
 * 
 * <tr>
 * <td>TUNING_FAILURE</td>
 * <td>TUNED_AWAY</td>
 * </tr>
 * 
 * <tr>
 * <td>OTHER</td>
 * <td>OTHER</td>
 * </tr>
 * 
 * </tbody>
 * </table>
 * <p>
 * (No reason code corresponding to
 * <code>SelectionFailedEvent.INTERRUPTED</code> is necessary, since this
 * code signals that a new service selection operation is underway.)
 * <p>
 * Once a <code>PresentationTerminatedEvent</code> is generated, the
 * <code>ServiceContext</code>
 * will be in the <em>not presenting</em> state until a call to a
 * <code>select()</code> method succeeds.  When this event is
 * generated, all resources used for the presentation have been
 * released, and <code>ServiceContentHandler</code> instances
 * previously associated with the <code>ServiceContext</code> will
 * have ceased presentation of their content.
 *
 * @see SelectionFailedEvent
 */
public class PresentationTerminatedEvent extends ServiceContextEvent
{
    /** 
     * Reason code : The service vanished from the network.
     */
    public static final int SERVICE_VANISHED = 1;

    /** 
     * Reason code : Tuning made the service unavailable.
     */
    public static final int TUNED_AWAY = 2;

    /** 
     * Reason code : Resources needed to present the service have been removed.
     */
    public static final int RESOURCES_REMOVED = 3;

    /** 
     * Reason code : Access to the service or some component of it has been
     * withdrawn by the system. An example of this is the end of a free 
     * preview period for IPPV content. 
     */
    public static final int ACCESS_WITHDRAWN = 4;

    /** 
     * Reason code : The application or user requested that the
     * presentation be stopped. 
     */
    public static final int USER_STOP = 5;

    /** 
     * Reason code: The presentation was terminated due to an unknown reason
     * or for multiple reasons.
     */
    public static final int OTHER = 255;

    /** 
     * Constructs the event with a reason code.
     *
     * @param source The <code>ServiceContext</code> that generated the event.
     * @param reason The reason for which the presentation was terminated.
     *
     */
    public PresentationTerminatedEvent(ServiceContext source, int reason) { 
        super(source);    
    }

    /** 
     * Reports the reason for which the presentation was terminated.
     *
     * @return A reason code for why the presentation was terminated.
     */
    public int getReason() {
        return 0;
    }
}
