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


  


package java.util.jar;

import java.util.zip.*;
import java.io.*;

/** 
 * The <code>JarInputStream</code> class is used to read the contents of
 * a JAR file from any input stream. It extends the class
 * <code>java.util.zip.ZipInputStream</code> with support for reading
 * an optional <code>Manifest</code> entry. The <code>Manifest</code>
 * can be used to store meta-information about the JAR file and its entries.
 *
 * @author  David Connelly
 * @version 1.25, 02/02/00
 * @see	    Manifest
 * @see	    java.util.zip.ZipInputStream
 * @since   1.2
 */
public class JarInputStream extends ZipInputStream
{

    /** 
     * Creates a new <code>JarInputStream</code> and reads the optional
     * manifest. If a manifest is present, also attempts to verify
     * the signatures if the JarInputStream is signed.
     * @param in the actual input stream
     * @exception IOException if an I/O error has occurred
     */
    public JarInputStream(InputStream in) throws IOException { 
        super(in);    
    }

    /** 
     * Creates a new <code>JarInputStream</code> and reads the optional
     * manifest. If a manifest is present and verify is true, also attempts 
     * to verify the signatures if the JarInputStream is signed.
     *
     * @param in the actual input stream
     * @param verify whether or not to verify the JarInputStream if
     * it is signed.
     * @exception IOException if an I/O error has occurred
     */
    public JarInputStream(InputStream in, boolean verify) throws IOException { 
        super(in);    
    }

    /** 
     * Returns the <code>Manifest</code> for this JAR file, or
     * <code>null</code> if none.
     *
     * @return the <code>Manifest</code> for this JAR file, or
     *         <code>null</code> if none.
     */
    public Manifest getManifest() {
        return null;
    }

    /** 
     * Reads the next ZIP file entry and positions stream at the beginning
     * of the entry data.
     * @exception ZipException if a ZIP file error has occurred
     * @exception IOException if an I/O error has occurred
     */
    public ZipEntry getNextEntry() throws IOException {
        return null;
    }

    /** 
     * Reads the next JAR file entry and positions the stream at the
     * beginning of the entry data.
     *
     * @return the next JAR file entry
     * @exception ZipException if a ZIP file error has occurred
     * @exception IOException if an I/O error has occurred
     */
    public JarEntry getNextJarEntry() throws IOException {
        return null;
    }

    /** 
     * Reads from the current JAR file entry into an array of bytes.
     * Blocks until some input is available.
     * @param b the buffer into which the data is read
     * @param off the start offset of the data
     * @param len the maximum number of bytes to read
     * @return the actual number of bytes read, or -1 if the end of the
     *         entry is reached
     * @exception ZipException if a ZIP file error has occurred
     * @exception IOException if an I/O error has occurred
     */
    public int read(byte[] b, int off, int len) throws IOException {
        return 0;
    }

    /** 
     * Creates a new <code>JarEntry</code> (<code>ZipEntry</code>) for the
     * specified JAR file entry name.
     *
     * @param name the name of the JAR/ZIP file entry
     * @return the <code>JarEntry</code> object just created
     */
    protected ZipEntry createZipEntry(String name) {
        return null;
    }
}
