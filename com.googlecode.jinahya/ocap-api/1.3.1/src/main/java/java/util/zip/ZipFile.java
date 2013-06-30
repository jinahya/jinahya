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


  


package java.util.zip;

import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.File;
import java.util.Vector;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/** 
 * This class is used to read entries from a zip file.
 *
 * @version   1.58, 05/09/05 
 * @author	David Connelly
 */
public class ZipFile implements ZipConstants
{
    /** 
     * Mode flag to open a zip file for reading.
     */
    public static final int OPEN_READ = 1;

    /** 
     * Mode flag to open a zip file and mark it for deletion.  The file will be
     * deleted some time between the moment that it is opened and the moment
     * that it is closed, but its contents will remain accessible via the
     * <tt>ZipFile</tt> object until either the close method is invoked or the 
     * virtual machine exits.
     */
    public static final int OPEN_DELETE = 4;

    /** 
     * Opens a zip file for reading.
     * 
     * <p>First, if there is a security
     * manager, its <code>checkRead</code> method
     * is called with the <code>name</code> argument
     * as its argument to ensure the read is allowed.
     * 
     * @param name the name of the zip file
     * @exception ZipException if a ZIP format error has occurred
     * @exception IOException if an I/O error has occurred
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkRead</code> method doesn't allow read access to the file.
     * @see SecurityManager#checkRead(java.lang.String)
     */
    public ZipFile(String name) throws IOException { }

    /** 
     * Opens a new <code>ZipFile</code> to read from the specified
     * <code>File</code> object in the specified mode.  The mode argument
     * must be either <tt>OPEN_READ</tt> or <tt>OPEN_READ | OPEN_DELETE</tt>.
     * 
     * <p>First, if there is a security manager, its <code>checkRead</code>
     * method is called with the <code>name</code> argument as its argument to
     * ensure the read is allowed.
     *
     * @param file the ZIP file to be opened for reading
     * @param mode the mode in which the file is to be opened
     * @exception ZipException if a ZIP format error has occurred
     * @exception IOException if an I/O error has occurred
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkRead</code> method doesn't allow read access to the file,
     *             or <code>checkDelete</code> method doesn't allow deleting the file
     *             when <tt>OPEN_DELETE</tt> flag is set.
     * @exception IllegalArgumentException
     *            If the <tt>mode</tt> argument is invalid
     * @see SecurityManager#checkRead(java.lang.String)
     */
    public ZipFile(File file, int mode) throws IOException { }

    /** 
     * Opens a ZIP file for reading given the specified File object.
     * @param file the ZIP file to be opened for reading
     * @exception ZipException if a ZIP error has occurred
     * @exception IOException if an I/O error has occurred
     */
    public ZipFile(File file) throws ZipException, IOException { }

    /** 
     * Returns the zip file entry for the specified name, or null
     * if not found.
     *
     * @param name the name of the entry
     * @return the zip file entry, or null if not found
     * @exception IllegalStateException if the zip file has been closed
     */
    public ZipEntry getEntry(String name) {
        return null;
    }

    /** 
     * Returns an input stream for reading the contents of the specified
     * zip file entry.
     *
     * Returns an input stream for reading the contents of the specified
     * zip file entry.
     *
     * <p> Closing this ZIP file will, in turn, close all input 
     * streams that have been returned by invocations of this method.
     *
     * @param entry the zip file entry
     * @return the input stream for reading the contents of the specified
     * zip file entry.
     * @exception ZipException if a ZIP format error has occurred
     * @exception IOException if an I/O error has occurred
     * @exception IllegalStateException if the zip file has been closed
     */
    public InputStream getInputStream(ZipEntry entry) throws IOException {
        return null;
    }

    /** 
     * Returns the path name of the ZIP file.
     * @return the path name of the ZIP file
     */
    public String getName() {
        return null;
    }

    /** 
     * Returns an enumeration of the ZIP file entries.
     * @return an enumeration of the ZIP file entries
     * @exception IllegalStateException if the zip file has been closed
     */
    public Enumeration entries() {
        return null;
    }

    /** 
     * Returns the number of entries in the ZIP file.
     * @return the number of entries in the ZIP file
     * @exception IllegalStateException if the zip file has been closed
     */
    public int size() {
        return 0;
    }

    /** 
     * Closes the ZIP file.
     * <p> Closing this ZIP file will close all of the input streams
     * previously returned by invocations of the {@link #getInputStream
     * getInputStream} method.
     *
     * @throws IOException if an I/O error has occured
     */
    public void close() throws IOException { }

    /** 
     * Ensures that the <code>close</code> method of this ZIP file is
     * called when there are no more references to it.
     *
     * <p>
     * Since the time when GC would invoke this method is undetermined, 
     * it is strongly recommanded that applications invoke the <code>close</code> 
     * method as soon they have finished accessing this <code>ZipFile</code>.
     * This will prevent holding up system resources for an undetermined 
     * length of time.
     * 
     * @exception  IOException  if an I/O error occurs.
     * @see        java.util.zip.ZipFile#close()
     */
    protected void finalize() throws IOException { }
}
