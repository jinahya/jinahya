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



  


package javax.tv.service.navigation;

import javax.tv.service.*;

/** 
 * This interface represents an abstraction of an elementary
 * stream. It provides information about individual components of a
 * service.  Generally speaking, a service component carries content
 * such as media (e.g. as audio or video) or data.  Content within a
 * <code>ServiceComponent</code> may include <code>Xlet</code>s.
 *
 * @see ServiceDetails#retrieveComponents
 * @see javax.tv.service.guide.ProgramEvent#retrieveComponents
 */
public interface ServiceComponent extends SIElement
{

    /** 
     * Returns a name associated with this component. The Component Descriptor
     * (DVB) or Component Name Descriptor (ATSC) may be used if present. A
     * generic name (e.g., "video", "first audio", etc.) may be used otherwise.
     *
     * @return A string representing the component name or an empty string
     * if no name can be associated with this component.
     */
    public String getName();

    /** 
     * Identifies the language used for the elementary stream. The
     * associated language is indicated using a language code.  This is
     * typically a three-character language code as specified by ISO
     * 639.2/B, but the code may be system-dependent.
     *
     * @return A string representing a language code defining the
     * language associated with this component.  An empty string is
     * returned when there is no language associated with this component.  
     */
    public String getAssociatedLanguage();

    /** 
     * Provides the stream type of this component. (For example, "video",
     * "audio", etc.)
     *
     * @return Stream type of this component.  
     */
    public StreamType getStreamType();

    /** 
     * Provides the <code>Service</code> object to which this
     * <code>ServiceComponent</code> belongs. The result may be
     * <code>null</code> if the Service cannot be determined.
     *
     * @return The <code>Service</code> to which this
     * <code>ServiceComponent</code> belongs.  
     */
    public Service getService();
}
