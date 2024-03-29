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
 * Package-private abstract class for the local filesystem abstraction.
 */

abstract class FileSystem {

    /**
     * Return the FileSystem object representing this platform's local
     * filesystem.
     */
    public static native FileSystem getFileSystem();


    /* -- Normalization and construction -- */

    /**
     * Return the local filesystem's name-separator character.
     */
    public abstract char getSeparator();

    /**
     * Return the local filesystem's path-separator character.
     */
    public abstract char getPathSeparator();

    /**
     * Convert the given pathname string to normal form.  If the string is
     * already in normal form then it is simply returned.
     */
    public abstract String normalize(String path);

    /**
     * Compute the length of this pathname string's prefix.  The pathname
     * string must be in normal form.
     */
    public abstract int prefixLength(String path);

    /**
     * Resolve the child pathname string against the parent.
     * Both strings must be in normal form, and the result
     * will be in normal form.
     */
    public abstract String resolve(String parent, String child);

    /**
     * Return the parent pathname string to be used when the parent-directory
     * argument in one of the two-argument File constructors is the empty
     * pathname.
     */
    public abstract String getDefaultParent();

    /**
     * Post-process the given URI path string if necessary.  This is used on
     * win32, e.g., to transform "/c:/foo" into "c:/foo".  The path string
     * still has slash separators; code in the File class will translate them
     * after this method returns.
     */
    public abstract String fromURIPath(String path);


    /* -- Path operations -- */

    /**
     * Tell whether or not the given abstract pathname is absolute.
     */
    public abstract boolean isAbsolute(File f);

    /**
     * Resolve the given abstract pathname into absolute form.  Invoked by the
     * getAbsolutePath and getCanonicalPath methods in the File class.
     */
    public abstract String resolve(File f);

    public abstract String canonicalize(String path) throws IOException;


    /* -- Attribute accessors -- */

    /* Constants for simple boolean attributes */
    public static final int BA_EXISTS    = 0x01;
    public static final int BA_REGULAR   = 0x02;
    public static final int BA_DIRECTORY = 0x04;
    public static final int BA_HIDDEN    = 0x08;

    /**
     * Return the simple boolean attributes for the file or directory denoted
     * by the given abstract pathname, or zero if it does not exist or some
     * other I/O error occurs.
     */
    public abstract int getBooleanAttributes(File f);

    /**
     * Check whether the file or directory denoted by the given abstract
     * pathname may be accessed by this process.  If the second argument is
     * <code>false</code>, then a check for read access is made; if the second
     * argument is <code>true</code>, then a check for write (not read-write)
     * access is made.  Return false if access is denied or an I/O error
     * occurs.
     */
    public abstract boolean checkAccess(File f, boolean write);

    /**
     * Return the time at which the file or directory denoted by the given
     * abstract pathname was last modified, or zero if it does not exist or
     * some other I/O error occurs.
     */
    public abstract long getLastModifiedTime(File f);

    /**
     * Return the length in bytes of the file denoted by the given abstract
     * pathname, or zero if it does not exist, is a directory, or some other
     * I/O error occurs.
     */
    public abstract long getLength(File f);


    /* -- File operations -- */

    /**
     * Create a new empty file with the given pathname.  Return
     * <code>true</code> if the file was created and <code>false</code> if a
     * file or directory with the given pathname already exists.  Throw an
     * IOException if an I/O error occurs.
     */
    public abstract boolean createFileExclusively(String pathname)
	throws IOException;

    /**
     * Delete the file or directory denoted by the given abstract pathname,
     * returning <code>true</code> if and only if the operation succeeds.
     */
    public abstract boolean delete(File f);

    /**
     * Arrange for the file or directory denoted by the given abstract
     * pathname to be deleted when the VM exits, returning <code>true</code> if
     * and only if the operation succeeds.
     */
    public abstract boolean deleteOnExit(File f);

    /**
     * List the elements of the directory denoted by the given abstract
     * pathname.  Return an array of strings naming the elements of the
     * directory if successful; otherwise, return <code>null</code>.
     */
    public abstract String[] list(File f);

    /**
     * Create a new directory denoted by the given abstract pathname,
     * returning <code>true</code> if and only if the operation succeeds.
     */
    public abstract boolean createDirectory(File f);

    /**
     * Rename the file or directory denoted by the first abstract pathname to
     * the second abstract pathname, returning <code>true</code> if and only if
     * the operation succeeds.
     */
    public abstract boolean rename(File f1, File f2);

    /**
     * Set the last-modified time of the file or directory denoted by the
     * given abstract pathname, returning <code>true</code> if and only if the
     * operation succeeds.
     */
    public abstract boolean setLastModifiedTime(File f, long time);

    /**
     * Mark the file or directory denoted by the given abstract pathname as
     * read-only, returning <code>true</code> if and only if the operation
     * succeeds.
     */
    public abstract boolean setReadOnly(File f);


    /* -- Filesystem interface -- */

    /**
     * List the available filesystem roots.
     */
    public abstract File[] listRoots();


    /* -- Basic infrastructure -- */

    /**
     * Compare two abstract pathnames lexicographically.
     */
    public abstract int compare(File f1, File f2);

    /**
     * Compute the hash code of an abstract pathname.
     */
    public abstract int hashCode(File f);

}
