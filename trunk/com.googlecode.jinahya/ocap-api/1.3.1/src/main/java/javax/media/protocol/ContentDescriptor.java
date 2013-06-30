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



  


package javax.media.protocol;

/** 
 * A <CODE>ContentDescriptor</CODE> identifies media data containers.
 *
 * @see SourceStream
 * @version 1.12, 05/10/24.
 */
public class ContentDescriptor
{
    public static final String CONTENT_UNKNOWN = "UnknownContent";

    protected String typeName;

    /** 
     * Create a content descriptor with the specified name.
     * <p>
     * To create a <CODE>ContentDescriptor</CODE> from a MIME type, use
     * the <code>mimeTypeToPackageName</code> static member.
     *
     * @param cdName The name of the content-type.
     */
    public ContentDescriptor(String cdName) { }

    /** 
     * Obtain a string that represents the content-name
     * for this descriptor. It is identical to the string passed to
     * the constructor.
     *
     * @return The content-type name.
     */
    public String getContentType() {
        return null;
    }

    /** 
     * Map a MIME content-type to an equivalent string
     * of class-name components.
     * <p>
     * The MIME type is mapped to a string by:
     * <ol>
     * <li>Replacing all slashes with a period.
     * <li>Converting all alphabetic characters to lower case.
     * <li>Converting all non-alpha-numeric characters other than periods
     * to underscores (_).
     * </ol>
     * <p>
     * For example, "text/html" would
     * be converted to "text.html"
     *
     * @param mimeType The MIME type to map to a string.
     */
    public static final String mimeTypeToPackageName(String mimeType) {
        return null;
    }
}
