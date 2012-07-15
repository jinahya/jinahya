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

import java.io.*;
import java.util.*;
import java.util.zip.*;

import java.security.cert.Certificate;

/** 
 * The <code>JarFile</code> class is used to read the contents of a JAR file
 * from any file that can be opened with <code>java.io.RandomAccessFile</code>.
 * It extends the class <code>java.util.zip.ZipFile</code> with support
 * for reading an optional <code>Manifest</code> entry. The
 * <code>Manifest</code> can be used to specify meta-information about the
 * JAR file and its entries.
 *
 * @author  David Connelly
 * @version 1.38, 02/02/00
 * @see	    Manifest
 * @see     java.util.zip.ZipFile
 * @see     java.util.jar.JarEntry
 * @since   1.2
 */
public class JarFile extends ZipFile
{
    /** 
     * The JAR manifest file name.
     */
    public static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";

    /** 
     * Creates a new <code>JarFile</code> to read from the specified
     * file <code>name</code>. The <code>JarFile</code> will be verified if
     * it is signed.
     * @param name the name of the JAR file to be opened for reading
     * @exception IOException if an I/O error has occurred
     * @exception SecurityException if access to the file is denied
     *            by the SecurityManager
     */
    public JarFile(String name) throws IOException { 
	this(new File(name), true, ZipFile.OPEN_READ);    
    }

    /** 
     * Creates a new <code>JarFile</code> to read from the specified
     * file <code>name</code>.
     * @param name the name of the JAR file to be opened for reading
     * @param verify whether or not to verify the JarFile if
     * it is signed.
     * @exception IOException if an I/O error has occurred
     * @exception SecurityException if access to the file is denied
     *            by the SecurityManager 
     */
    public JarFile(String name, boolean verify) throws IOException { 
        this(new File(name), verify, ZipFile.OPEN_READ);    
    }

    /** 
     * Creates a new <code>JarFile</code> to read from the specified
     * <code>File</code> object. The <code>JarFile</code> will be verified if
     * it is signed.
     * @param file the JAR file to be opened for reading
     * @exception IOException if an I/O error has occurred
     * @exception SecurityException if access to the file is denied
     *            by the SecurityManager
     */
    public JarFile(File file) throws IOException { 
	this(file, true, ZipFile.OPEN_READ);    
    }

    /** 
     * Creates a new <code>JarFile</code> to read from the specified
     * <code>File</code> object.
     * @param file the JAR file to be opened for reading
     * @param verify whether or not to verify the JarFile if
     * it is signed.
     * @exception IOException if an I/O error has occurred
     * @exception SecurityException if access to the file is denied
     *            by the SecurityManager.
     */
    public JarFile(File file, boolean verify) throws IOException { 
	this(file, verify, ZipFile.OPEN_READ);    
    }

    /** 
     * Creates a new <code>JarFile</code> to read from the specified
     * <code>File</code> object in the specified mode.  The mode argument
     * must be either <tt>OPEN_READ</tt> or <tt>OPEN_READ | OPEN_DELETE</tt>.
     *
     * @param file the JAR file to be opened for reading
     * @param verify whether or not to verify the JarFile if
     * it is signed.
     * @param mode the mode in which the file is to be opened
     * @exception IOException if an I/O error has occurred
     * @exception IllegalArgumentException
     *            If the <tt>mode</tt> argument is invalid
     * @exception SecurityException if access to the file is denied
     *            by the SecurityManager
     */
    public JarFile(File file, boolean verify, int mode) throws IOException { 
        super(file, mode);
    }

    /** 
     * Returns the JAR file manifest, or <code>null</code> if none.
     *
     * @return the JAR file manifest, or <code>null</code> if none
     */
    public Manifest getManifest() throws IOException {
        return null;
    }

    /** 
     * Returns the <code>JarEntry</code> for the given entry name or
     * <code>null</code> if not found.
     *
     * @param name the JAR file entry name
     * @return the <code>JarEntry</code> for the given entry name or
     *         <code>null</code> if not found.
     * @see java.util.jar.JarEntry
     */
    public JarEntry getJarEntry(String name) {
        return null;
    }

    /** 
     * Returns the <code>ZipEntry</code> for the given entry name or
     * <code>null</code> if not found.
     *
     * @param name the JAR file entry name
     * @return the <code>ZipEntry</code> for the given entry name or
     *         <code>null</code> if not found
     * @see java.util.zip.ZipEntry
     */
    public ZipEntry getEntry(String name) {
        return null;
    }

    /** 
     * Returns an enumeration of the ZIP file entries.
     */
    public Enumeration entries() {
        return null;
    }

    /** 
     * Returns an input stream for reading the contents of the specified
     * ZIP file entry.
     * @param ze the zip file entry
     * @return an input stream for reading the contents of the specified
     *         ZIP file entry
     * @exception ZipException if a ZIP format error has occurred
     * @exception IOException if an I/O error has occurred
     * @exception SecurityException if any of the JarFile entries are incorrectly signed.
     */
    public synchronized InputStream getInputStream(ZipEntry ze)
        throws IOException
    {
        return null;
    }
}
