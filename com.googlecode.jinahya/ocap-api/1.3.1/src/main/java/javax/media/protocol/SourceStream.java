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

import java.io.IOException;

/** 
 * Abstracts a single stream of media data.
 *
 * <h2>Stream Controls</h2>
 *
 * A <code>SourceStream</code> might support an operation that
 * is not part of the <code>SourceStream</code> definition.
 * For example a stream might support seeking to a particular byte
 * in the stream. Some operations are dependent on the stream
 * data, and support cannot be determined until the stream is in
 * use.
 * <p>
 *
 * To obtain all of the objects that provide control over a stream
 * use <code>getControls</code>. To determine if a particular
 * kind of control is available, and obtain the object that
 * implements the control use <code>getControl</code>.
 *
 * 
 * @see DataSource
 * @see PushSourceStream
 * @see PullSourceStream
 * @see Seekable
 *
 * @version 1.12, 97/08/28.
 */
public interface SourceStream extends Controls
{
    public static final long LENGTH_UNKNOWN = -1L;

    /** 
     * Get the current content type for this stream.
     *
     * @return The current <CODE>ContentDescriptor</CODE> for this stream.
     */
    public ContentDescriptor getContentDescriptor();

    /** 
     * Get the size, in bytes, of the content on this stream.
     * LENGTH_UNKNOWN is returned if the length is not known.
     *
     * @return The content length in bytes.
     */
    public long getContentLength();

    /** 
     * Find out if the end of the stream has been reached.
     *
     * @return Returns <CODE>true</CODE> if there is no more data.
     */
    public boolean endOfStream();
}
