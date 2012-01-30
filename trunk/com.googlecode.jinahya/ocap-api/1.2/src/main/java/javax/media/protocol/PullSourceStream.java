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

import java.io.*;

/** 
 * Abstracts a read interface that data is pulled from.
 *
 * @see PullDataSource
 * @version 1.8, 97/08/23.
 */
public interface PullSourceStream extends SourceStream
{

    /** 
     * Find out if data is available now.
     * Returns <CODE>true</CODE> if a call to <CODE>read</CODE> would block
     * for data.
     *
     * @return Returns <CODE>true</CODE> if read would block; otherwise
     * returns <CODE>false</CODE>.
     */
    public boolean willReadBlock();

    /** 
     * Block and read data from the stream.
     * <p>
     * Reads up to <CODE>length</CODE> bytes from the input stream into
     * an array of bytes.
     * If the first argument is <code>null</code>, up to
     * <CODE>length</CODE> bytes are read and discarded.
     * Returns -1 when the end
     * of the media is reached.
     *
     * This method  only returns 0 if it was called with
     * a <CODE>length</CODE> of 0.
     *
     * @param buffer The buffer to read bytes into.
     * @param offset The offset into the buffer at which to begin writing data.
     * @param length The number of bytes to read.
     * @return The number of bytes read, -1 indicating
     * the end of stream, or 0 indicating <CODE>read</CODE>
     * was called with <CODE>length</CODE> 0.
     * @throws IOException Thrown if an error occurs while reading. 
     */
    public int read(byte[] buffer, int offset, int length) throws IOException;
}
