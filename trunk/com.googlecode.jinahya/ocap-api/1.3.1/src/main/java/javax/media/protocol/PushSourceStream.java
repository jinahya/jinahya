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
 * Abstracts a read interface that pushes data.
 *
 *
 * @see PushDataSource
 * @version 1.7, 97/08/25.
 */
public interface PushSourceStream extends SourceStream
{

    /** 
     * Read from the stream without blocking.
     * Returns -1 when the end of the media
     * is reached.
     *
     * @param buffer The buffer to read bytes into.
     * @param offset The offset into the buffer at which to begin writing data.
     * @param length The number of bytes to read.
     * @return The number of bytes read or -1
     * when the end of stream is reached.
     */
    public int read(byte[] buffer, int offset, int length);

    /** 
     * Determine the size of the buffer needed for the data transfer.
     * This method is provided so that a transfer handler
     * can determine how much data, at a minimum, will be
     * available to transfer from the source.
     * Overflow and data loss is likely to occur if this much
     * data isn't read at transfer time.
     *
     * @return The size of the data transfer.
     */
    public int getMinimumTransferSize();

    /** 
     * Register an object to service data transfers to this stream.
     * <p>
     * If a handler is already registered when
     * <CODE>setTransferHandler</CODE> is called,
     * the handler is replaced;
     * there can only be one handler at a time.
     * 
     * @param transferHandler The handler to transfer data to.
     */
    public void setTransferHandler(SourceTransferHandler transferHandler);
}
