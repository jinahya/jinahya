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
 * <code>AlternativeContentEvent</code> is generated to indicate that
 * "alternative" content is being presented during the presentation of
 * a service. Alternative content is content not actually part of the
 * requested service, such as content related to conditional access
 * failures (e.g., purchase dialogs or advertising for content for
 * which the user is not yet authorized). The presentation of
 * alternative content is always initiated by the system and never by
 * applications.<p>
 *
 * This event will be generated in two situations:
 *
 * <ul> <li> At the end of a successful service selection operation,
 * this event will be generated if any of the service components being
 * presented are alternative content. Under these conditions, the
 * generation of this event signals a change in state of the service
 * context from the <em>presentation pending</em> state to the
 * <em>presenting</em> state.  A <code>NormalContentEvent</code> will
 * not be generated.  </li>
 *
 * <li>
 * During the presentation of a service, this event will be generated
 * if any of the service components being presented are replaced by
 * alternative content.  One example of this is the expiration of a free
 * preview period. In this case, generation of this event does not
 * impact the service context state model.
 * </li>
 * </ul>
 *
 * Presentation failures enforced via a conditional access system may
 * be reported by this event or by a <code>SelectionFailedEvent</code>
 * with the <code>CA_REFUSAL</code> reason code. Which of these is
 * used depends on the precise nature of the conditional access
 * system. Applications must allow for both modes of failure.
 * 
 * @see NormalContentEvent
 * @see SelectionFailedEvent 
 */
public class AlternativeContentEvent extends PresentationChangedEvent
{

    /** 
     * Constructs the event.
     *
     * @param source The <code>ServiceContext</code> that generated the
     * event.
     */
    public AlternativeContentEvent(ServiceContext source) { 
        super(source);
    }
}
