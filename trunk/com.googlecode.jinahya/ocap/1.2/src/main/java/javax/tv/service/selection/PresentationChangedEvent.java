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
 * <code>PresentationChangedEvent</code> indicates that the content
 * being presented in the <code>ServiceContext</code> has changed.
 * <code>PresentationChangedEvent</code> is the parent class of events
 * indicating dynamic changes to the presentation of a service due to
 * interaction with the CA system.  It is generated when neither
 * <code>AlternativeContentEvent</code> nor
 * <code>NormalContentEvent</code> are applicable.<p>
 * 
 * Applications may determine the nature of the new content by
 * querying the current <code>ServiceContentHandler</code> instances
 * of the <code>ServiceContext</code>.
 *
 * @see AlternativeContentEvent
 * @see NormalContentEvent
 * @see ServiceContentHandler#getServiceContentLocators 
 */
public class PresentationChangedEvent extends ServiceContextEvent
{

    /** 
     * Constructs the event.
     *
     * @param source The <code>ServiceContext</code> that generated the
     * event.
     */
    public PresentationChangedEvent(ServiceContext source) { 
        super(source);
    }
}
