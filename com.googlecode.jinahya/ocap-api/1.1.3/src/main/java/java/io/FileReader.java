/*
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 219: Foundation
Profile 1.1. In the event of a discrepency between this work and the
JSR 219 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
*/


  


package java.io;

/** 
 * Convenience class for reading character files.  The constructors of this
 * class assume that the default character encoding and the default byte-buffer
 * size are appropriate.  To specify these values yourself, construct an
 * InputStreamReader on a FileInputStream.
 *
 * <p><code>FileReader</code> is meant for reading streams of characters.
 * For reading streams of raw bytes, consider using a
 * <code>FileInputStream</code>.
 *
 * @see InputStreamReader
 * @see FileInputStream
 *
 * @version 	1.10, 00/02/02
 * @author	Mark Reinhold
 * @since	JDK1.1
 */
public class FileReader extends InputStreamReader
{

    /** 
     * Creates a new <tt>FileReader</tt>, given the name of the
     * file to read from.
     *
     * @param fileName the name of the file to read from
     * @exception  FileNotFoundException  if the named file does not exist,
     *                   is a directory rather than a regular file,
     *                   or for some other reason cannot be opened for
     *                   reading.
     */
    public FileReader(String fileName) throws FileNotFoundException { 
    	super(new FileInputStream(fileName));
    }

    /** 
     * Creates a new <tt>FileReader</tt>, given the <tt>File</tt> 
     * to read from.
     *
     * @param file the <tt>File</tt> to read from
     * @exception  FileNotFoundException  if the file does not exist,
     *                   is a directory rather than a regular file,
     *                   or for some other reason cannot be opened for
     *                   reading.
     */
    public FileReader(File file) throws FileNotFoundException { 
    	super(new FileInputStream(file));
    }

    /** 
     * Creates a new <tt>FileReader</tt>, given the 
     * <tt>FileDescriptor</tt> to read from.
     *
     * @param fd the FileDescriptor to read from
     */
    public FileReader(FileDescriptor fd) { 
    	super(new FileInputStream(fd));    
    }
}
