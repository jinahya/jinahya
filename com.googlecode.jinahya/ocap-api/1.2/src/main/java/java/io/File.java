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

import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Hashtable;
import java.util.Random;
import java.security.AccessController;
import java.security.AccessControlException;

/** 
 * An abstract representation of file and directory pathnames.
 *
 * <p> User interfaces and operating systems use system-dependent <em>pathname
 * strings</em> to name files and directories.  This class presents an
 * abstract, system-independent view of hierarchical pathnames.  An
 * <em>abstract pathname</em> has two components:
 *
 * <ol>
 * <li> An optional system-dependent <em>prefix</em> string,
 *      such as a disk-drive specifier, <code>"/"</code>&nbsp;for the UNIX root
 *      directory, or <code>"\\"</code>&nbsp;for a Microsoft Windows UNC pathname, and
 * <li> A sequence of zero or more string <em>names</em>.
 * </ol>
 *
 * Each name in an abstract pathname except for the last denotes a directory;
 * the last name may denote either a directory or a file.  The <em>empty</em>
 * abstract pathname has no prefix and an empty name sequence.
 *
 * <p> The conversion of a pathname string to or from an abstract pathname is
 * inherently system-dependent.  When an abstract pathname is converted into a
 * pathname string, each name is separated from the next by a single copy of
 * the default <em>separator character</em>.  The default name-separator
 * character is defined by the system property <code>file.separator</code>, and
 * is made available in the public static fields <code>{@link
 * #separator}</code> and <code>{@link #separatorChar}</code> of this class.
 * When a pathname string is converted into an abstract pathname, the names
 * within it may be separated by the default name-separator character or by any
 * other name-separator character that is supported by the underlying system.
 *
 * <p> A pathname, whether abstract or in string form, may be either
 * <em>absolute</em> or <em>relative</em>.  An absolute pathname is complete in
 * that no other information is required in order to locate the file that it
 * denotes.  A relative pathname, in contrast, must be interpreted in terms of
 * information taken from some other pathname.  By default the classes in the
 * <code>java.io</code> package always resolve relative pathnames against the
 * current user directory.  This directory is named by the system property
 * <code>user.dir</code>, and is typically the directory in which the Java
 * virtual machine was invoked.
 *
 * <p> The prefix concept is used to handle root directories on UNIX platforms,
 * and drive specifiers, root directories and UNC pathnames on Microsoft Windows platforms,
 * as follows:
 *
 * <ul>
 *
 * <li> For UNIX platforms, the prefix of an absolute pathname is always
 * <code>"/"</code>.  Relative pathnames have no prefix.  The abstract pathname
 * denoting the root directory has the prefix <code>"/"</code> and an empty
 * name sequence.
 *
 * <li> For Microsoft Windows platforms, the prefix of a pathname that contains a drive
 * specifier consists of the drive letter followed by <code>":"</code> and
 * possibly followed by <code>"\"</code> if the pathname is absolute.  The
 * prefix of a UNC pathname is <code>"\\"</code>; the hostname and the share
 * name are the first two names in the name sequence.  A relative pathname that
 * does not specify a drive has no prefix.
 *
 * </ul>
 *
 * <p> Instances of the <code>File</code> class are immutable; that is, once
 * created, the abstract pathname represented by a <code>File</code> object
 * will never change.
 *
 * @version 1.95 10/17/00
 * @author  unascribed
 * @since   JDK1.0
 */
public class File implements Serializable, Comparable
{

    /**
     * The FileSystem object representing the platform's local file system.
     */
    static private FileSystem fs = FileSystem.getFileSystem();

    /** 
     * The system-dependent default name-separator character.  This field is
     * initialized to contain the first character of the value of the system
     * property <code>file.separator</code>.  On UNIX systems the value of this
     * field is <code>'/'</code>; on Microsoft Windows systems it is <code>'\'</code>.
     *
     * @see     java.lang.System#getProperty(java.lang.String)
     */
    public static final char separatorChar = fs.getSeparator();

    /** 
     * The system-dependent default name-separator character, represented as a
     * string for convenience.  This string contains a single character, namely
     * <code>{@link #separatorChar}</code>.
     */
    public static final String separator = "" + separatorChar;

    /** 
     * The system-dependent path-separator character.  This field is
     * initialized to contain the first character of the value of the system
     * property <code>path.separator</code>.  This character is used to
     * separate filenames in a sequence of files given as a <em>path list</em>.
     * On UNIX systems, this character is <code>':'</code>; on Microsoft Windows systems it
     * is <code>';'</code>.
     *
     * @see     java.lang.System#getProperty(java.lang.String)
     */
    public static final char pathSeparatorChar = fs.getPathSeparator();

    /** 
     * The system-dependent path-separator character, represented as a string
     * for convenience.  This string contains a single character, namely
     * <code>{@link #pathSeparatorChar}</code>.
     */
    public static final String pathSeparator = "" + pathSeparatorChar;

    /** 
     * This abstract pathname's normalized pathname string.  A normalized
     * pathname string uses the default name-separator character and does not
     * contain any duplicate or redundant separators.
     *
     * @serial
     */
    private String path;

    /** 
     * Creates a new <code>File</code> instance by converting the given
     * pathname string into an abstract pathname.  If the given string is
     * the empty string, then the result is the empty abstract pathname.
     *
     * @param   pathname  A pathname string
     * @throws  NullPointerException
     *          If the <code>pathname</code> argument is <code>null</code>
     */
    public File(String pathname) { }

    /** 
     * Creates a new <code>File</code> instance from a parent pathname string
     * and a child pathname string.
     *
     * <p> If <code>parent</code> is <code>null</code> then the new
     * <code>File</code> instance is created as if by invoking the
     * single-argument <code>File</code> constructor on the given
     * <code>child</code> pathname string.
     *
     * <p> Otherwise the <code>parent</code> pathname string is taken to denote
     * a directory, and the <code>child</code> pathname string is taken to
     * denote either a directory or a file.  If the <code>child</code> pathname
     * string is absolute then it is converted into a relative pathname in a
     * system-dependent way.  If <code>parent</code> is the empty string then
     * the new <code>File</code> instance is created by converting
     * <code>child</code> into an abstract pathname and resolving the result
     * against a system-dependent default directory.  Otherwise each pathname
     * string is converted into an abstract pathname and the child abstract
     * pathname is resolved against the parent.
     *
     * @param   parent  The parent pathname string
     * @param   child   The child pathname string
     * @throws  NullPointerException
     *          If <code>child</code> is <code>null</code>
     */
    public File(String parent, String child) { }

    /** 
     * Creates a new <code>File</code> instance from a parent abstract
     * pathname and a child pathname string.
     *
     * <p> If <code>parent</code> is <code>null</code> then the new
     * <code>File</code> instance is created as if by invoking the
     * single-argument <code>File</code> constructor on the given
     * <code>child</code> pathname string.
     *
     * <p> Otherwise the <code>parent</code> abstract pathname is taken to
     * denote a directory, and the <code>child</code> pathname string is taken
     * to denote either a directory or a file.  If the <code>child</code>
     * pathname string is absolute then it is converted into a relative
     * pathname in a system-dependent way.  If <code>parent</code> is the empty
     * abstract pathname then the new <code>File</code> instance is created by
     * converting <code>child</code> into an abstract pathname and resolving
     * the result against a system-dependent default directory.  Otherwise each
     * pathname string is converted into an abstract pathname and the child
     * abstract pathname is resolved against the parent.
     *
     * @param   parent  The parent abstract pathname
     * @param   child   The child pathname string
     * @throws  NullPointerException
     *          If <code>child</code> is <code>null</code>
     */
    public File(File parent, String child) { }

    /**
     * Creates a new <tt>File</tt> instance by converting the given
     * <tt>file:</tt> URI into an abstract pathname.
     *
     * <p> The exact form of a <tt>file:</tt> URI is system-dependent, hence
     * the transformation performed by this constructor is also
     * system-dependent.
     *
     * <p> For a given abstract pathname <i>f</i> it is guaranteed that
     *
     * <blockquote><tt>
     * new File(</tt><i>&nbsp;f</i><tt>.{@link #toURI() toURI}()).equals(</tt><i>&nbsp;f</i><tt>.{@link #getAbsoluteFile() getAbsoluteFile}())
     * </tt></blockquote>
     *
     * so long as the original abstract pathname, the URI, and the new abstract
     * pathname are all created in (possibly different invocations of) the same
     * Java virtual machine.  This relationship typically does not hold,
     * however, when a <tt>file:</tt> URI that is created in a virtual machine
     * on one operating system is converted into an abstract pathname in a
     * virtual machine on a different operating system.
     *
     * @param  uri
     *         An absolute, hierarchical URI with a scheme equal to
     *         <tt>"file"</tt>, a non-empty path component, and undefined
     *         authority, query, and fragment components
     *
     * @throws  NullPointerException
     *          If <tt>uri</tt> is <tt>null</tt>
     *
     * @throws  IllegalArgumentException
     *          If the preconditions on the parameter do not hold
     *
     * @see #toURI()
     * @see java.net.URI
     * @since 1.4
     */

    public File(URI uri) { }


    /** 
     * Returns the name of the file or directory denoted by this abstract
     * pathname.  This is just the last name in the pathname's name
     * sequence.  If the pathname's name sequence is empty, then the empty
     * string is returned.
     *
     * @return  The name of the file or directory denoted by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String getName() {
        return null;
    }

    /** 
     * Returns the pathname string of this abstract pathname's parent, or
     * <code>null</code> if this pathname does not name a parent directory.
     *
     * <p> The <em>parent</em> of an abstract pathname consists of the
     * pathname's prefix, if any, and each name in the pathname's name
     * sequence except for the last.  If the name sequence is empty then
     * the pathname does not name a parent directory.
     *
     * @return  The pathname string of the parent directory named by this
     *          abstract pathname, or <code>null</code> if this pathname
     *          does not name a parent
     */
    public String getParent() {
        return null;
    }

    /** 
     * Returns the abstract pathname of this abstract pathname's parent,
     * or <code>null</code> if this pathname does not name a parent
     * directory.
     *
     * <p> The <em>parent</em> of an abstract pathname consists of the
     * pathname's prefix, if any, and each name in the pathname's name
     * sequence except for the last.  If the name sequence is empty then
     * the pathname does not name a parent directory.
     *
     * @return  The abstract pathname of the parent directory named by this
     *          abstract pathname, or <code>null</code> if this pathname
     *          does not name a parent
     *
     * @since 1.2
     */
    public File getParentFile() {
        return null;
    }

    /** 
     * Converts this abstract pathname into a pathname string.  The resulting
     * string uses the {@link #separator default name-separator character} to
     * separate the names in the name sequence.
     *
     * @return  The string form of this abstract pathname
     */
    public String getPath() {
        return null;
    }

    /** 
     * Tests whether this abstract pathname is absolute.  The definition of
     * absolute pathname is system dependent.  On UNIX systems, a pathname is
     * absolute if its prefix is <code>"/"</code>.  On Microsoft Windows systems, a
     * pathname is absolute if its prefix is a drive specifier followed by
     * <code>"\\"</code>, or if its prefix is <code>"\\"</code>.
     *
     * @return  <code>true</code> if this abstract pathname is absolute,
     *          <code>false</code> otherwise
     */
    public boolean isAbsolute() {
        return false;
    }

    /** 
     * Returns the absolute pathname string of this abstract pathname.
     *
     * <p> If this abstract pathname is already absolute, then the pathname
     * string is simply returned as if by the <code>{@link #getPath}</code>
     * method.  If this abstract pathname is the empty abstract pathname then
     * the pathname string of the current user directory, which is named by the
     * system property <code>user.dir</code>, is returned.  Otherwise this
     * pathname is resolved in a system-dependent way.  On UNIX systems, a
     * relative pathname is made absolute by resolving it against the current
     * user directory.  On Microsoft Windows systems, a relative pathname is made absolute
     * by resolving it against the current directory of the drive named by the
     * pathname, if any; if not, it is resolved against the current user
     * directory.
     *
     * @return  The absolute pathname string denoting the same file or
     *          directory as this abstract pathname
     *
     * @throws  SecurityException
     *          If a required system property value cannot be accessed.
     *
     * @see     java.io.File#isAbsolute()
     */
    public String getAbsolutePath() {
        return null;
    }

    /** 
     * Returns the absolute form of this abstract pathname.  Equivalent to
     * <code>new&nbsp;File(this.{@link #getAbsolutePath}())</code>.
     *
     * @return  The absolute abstract pathname denoting the same file or
     *          directory as this abstract pathname
     *
     * @throws  SecurityException
     *          If a required system property value cannot be accessed.
     *
     * @since 1.2
     */
    public File getAbsoluteFile() {
        return null;
    }

    /** 
     * Returns the canonical pathname string of this abstract pathname.
     *
     * <p> A canonical pathname is both absolute and unique.  The precise
     * definition of canonical form is system-dependent.  This method first
     * converts this pathname to absolute form if necessary, as if by invoking the
     * {@link #getAbsolutePath} method, and then maps it to its unique form in a
     * system-dependent way.  This typically involves removing redundant names
     * such as <tt>"."</tt> and <tt>".."</tt> from the pathname, resolving
     * symbolic links (on UNIX platforms), and converting drive letters to a
     * standard case (on Microsoft Windows platforms).
     *
     * <p> Every pathname that denotes an existing file or directory has a
     * unique canonical form.  Every pathname that denotes a nonexistent file
     * or directory also has a unique canonical form.  The canonical form of
     * the pathname of a nonexistent file or directory may be different from
     * the canonical form of the same pathname after the file or directory is
     * created.  Similarly, the canonical form of the pathname of an existing
     * file or directory may be different from the canonical form of the same
     * pathname after the file or directory is deleted.
     *
     * @return  The canonical pathname string denoting the same file or
     *          directory as this abstract pathname
     *
     * @throws  IOException
     *          If an I/O error occurs, which is possible because the
     *          construction of the canonical pathname may require
     *          filesystem queries
     *
     * @throws  SecurityException
     *          If a required system property value cannot be accessed.
     *
     * @since   JDK1.1
     */
    public String getCanonicalPath() throws IOException {
        return null;
    }

    /** 
     * Returns the canonical form of this abstract pathname.  Equivalent to
     * <code>new&nbsp;File(this.{@link #getCanonicalPath}())</code>.
     *
     * @return  The canonical pathname string denoting the same file or
     *          directory as this abstract pathname
     *
     * @throws  IOException
     *          If an I/O error occurs, which is possible because the
     *          construction of the canonical pathname may require
     *          filesystem queries
     *
     * @throws  SecurityException
     *          If a required system property value cannot be accessed.
     *
     * @since 1.2
     */
    public File getCanonicalFile() throws IOException {
        return null;
    }

    /** 
     * Converts this abstract pathname into a <code>file:</code> URL.  The
     * exact form of the URL is system-dependent.  If it can be determined that
     * the file denoted by this abstract pathname is a directory, then the
     * resulting URL will end with a slash.
     *
     * <p> <b>Usage note:</b> This method does not automatically escape
     * characters that are illegal in URLs.  It is recommended that new code
     * convert an abstract pathname into a URL by first converting it into a
     * URI, via the {@link #toURI() toURI} method, and then converting the URI
     * into a URL via the {@link java.net.URI#toURL() URI.toURL} method.
     *
     * @return  A URL object representing the equivalent file URL
     *
     * @throws  MalformedURLException
     *          If the path cannot be parsed as a URL
     *
     * @see     #toURI()
     * @see     java.net.URI
     * @see     java.net.URI#toURL()
     * @see     java.net.URL
     * @since   1.2
     */
    public URL toURL() throws MalformedURLException {
        return null;
    }

    /**
     * Constructs a <tt>file:</tt> URI that represents this abstract pathname.
     *
     * <p> The exact form of the URI is system-dependent.  If it can be
     * determined that the file denoted by this abstract pathname is a
     * directory, then the resulting URI will end with a slash.
     *
     * <p> For a given abstract pathname <i>f</i>, it is guaranteed that
     *
     * <blockquote><tt>
     * new {@link #File(java.net.URI) File}(</tt><i>&nbsp;f</i><tt>.toURI()).equals(</tt><i>&nbsp;f</i><tt>.{@link #getAbsoluteFile() getAbsoluteFile}())
     * </tt></blockquote>
     *
     * so long as the original abstract pathname, the URI, and the new abstract
     * pathname are all created in (possibly different invocations of) the same
     * Java virtual machine.  Due to the system-dependent nature of abstract
     * pathnames, however, this relationship typically does not hold when a
     * <tt>file:</tt> URI that is created in a virtual machine on one operating
     * system is converted into an abstract pathname in a virtual machine on a
     * different operating system.
     *
     * @return  An absolute, hierarchical URI with a scheme equal to
     *          <tt>"file"</tt>, a path representing this abstract pathname,
     *          and undefined authority, query, and fragment components
     *
     * @see #File(java.net.URI)
     * @see java.net.URI
     * @see java.net.URI#toURL()
     * @since 1.4
     */
    public URI toURI() {
        return null;
    }

    /** 
     * Tests whether the application can read the file denoted by this
     * abstract pathname.
     *
     * @return  <code>true</code> if and only if the file specified by this
     *          abstract pathname exists <em>and</em> can be read by the
     *          application; <code>false</code> otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the file
     */
    public boolean canRead() {
        return false;
    }

    /** 
     * Tests whether the application can modify to the file denoted by this
     * abstract pathname.
     *
     * @return  <code>true</code> if and only if the file system actually
     *          contains a file denoted by this abstract pathname <em>and</em>
     *          the application is allowed to write to the file;
     *          <code>false</code> otherwise.
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *          method denies write access to the file
     */
    public boolean canWrite() {
        return false;
    }

    /** 
     * Tests whether the file or directory denoted by this abstract pathname
     * exists.
     *
     * @return  <code>true</code> if and only if the file or directory denoted
     *          by this abstract pathname exists; <code>false</code> otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the file or directory
     */
    public boolean exists() {
        return false;
    }

    /** 
     * Tests whether the file denoted by this abstract pathname is a
     * directory.
     *
     * @return <code>true</code> if and only if the file denoted by this
     *          abstract pathname exists <em>and</em> is a directory;
     *          <code>false</code> otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the file
     */
    public boolean isDirectory() {
        return false;
    }

    /** 
     * Tests whether the file denoted by this abstract pathname is a normal
     * file.  A file is <em>normal</em> if it is not a directory and, in
     * addition, satisfies other system-dependent criteria.  Any non-directory
     * file created by a Java application is guaranteed to be a normal file.
     *
     * @return  <code>true</code> if and only if the file denoted by this
     *          abstract pathname exists <em>and</em> is a normal file;
     *          <code>false</code> otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the file
     */
    public boolean isFile() {
        return false;
    }

    /** 
     * Tests whether the file named by this abstract pathname is a hidden
     * file.  The exact definition of <em>hidden</em> is system-dependent.  On
     * UNIX systems, a file is considered to be hidden if its name begins with
     * a period character (<code>'.'</code>).  On Microsoft Windows systems, a file is
     * considered to be hidden if it has been marked as such in the filesystem.
     *
     * @return  <code>true</code> if and only if the file denoted by this
     *          abstract pathname is hidden according to the conventions of the
     *          underlying platform
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the file
     *
     * @since 1.2
     */
    public boolean isHidden() {
        return false;
    }

    /** 
     * Returns the time that the file denoted by this abstract pathname was
     * last modified.
     *
     * @return  A <code>long</code> value representing the time the file was
     *          last modified, measured in milliseconds since the epoch
     *          (00:00:00 GMT, January 1, 1970), or <code>0L</code> if the
     *          file does not exist or if an I/O error occurs
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the file
     */
    public long lastModified() {
        return -1;
    }

    /** 
     * Returns the length of the file denoted by this abstract pathname.
     * The return value is unspecified if this pathname denotes a directory.
     *
     * @return  The length, in bytes, of the file denoted by this abstract
     *          pathname, or <code>0L</code> if the file does not exist
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the file
     */
    public long length() {
        return -1;
    }

    /** 
     * Atomically creates a new, empty file named by this abstract pathname if
     * and only if a file with this name does not yet exist.  The check for the
     * existence of the file and the creation of the file if it does not exist
     * are a single operation that is atomic with respect to all other
     * filesystem activities that might affect the file.
     * <P>
     * Note: this method should <i>not</i> be used for file-locking, as
     * the resulting protocol cannot be made to work reliably. 
     *
     * @return  <code>true</code> if the named file does not exist and was
     *          successfully created; <code>false</code> if the named file
     *          already exists
     *
     * @throws  IOException
     *          If an I/O error occurred
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *          method denies write access to the file
     *
     * @since 1.2
     */
    public boolean createNewFile() throws IOException {
        return false;
    }

    /** 
     * Deletes the file or directory denoted by this abstract pathname.  If
     * this pathname denotes a directory, then the directory must be empty in
     * order to be deleted.
     *
     * @return  <code>true</code> if and only if the file or directory is
     *          successfully deleted; <code>false</code> otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkDelete}</code> method denies
     *          delete access to the file
     */
    public boolean delete() {
        return false;
    }

    /** 
     * Requests that the file or directory denoted by this abstract 
     * pathname be deleted when the virtual machine terminates.  
     * Deletion will be attempted only for normal termination of the 
     * virtual machine, as defined by the Java Language Specification. 
     *
     * <p> Once deletion has been requested, it is not possible to cancel the
     * request.  This method should therefore be used with care.
     *
     * <P>
     * Note: this method should <i>not</i> be used for file-locking, as 
     * the resulting protocol cannot be made to work reliably. 
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkDelete}</code> method denies
     *          delete access to the file
     *
     * @see #delete
     *
     * @since 1.2
     */
    public void deleteOnExit() { }

    /** 
     * Returns an array of strings naming the files and directories in the
     * directory denoted by this abstract pathname.
     *
     * <p> If this abstract pathname does not denote a directory, then this
     * method returns <code>null</code>.  Otherwise an array of strings is
     * returned, one for each file or directory in the directory.  Names
     * denoting the directory itself and the directory's parent directory are
     * not included in the result.  Each string is a file name rather than a
     * complete path.
     *
     * <p> There is no guarantee that the name strings in the resulting array
     * will appear in any specific order; they are not, in particular,
     * guaranteed to appear in alphabetical order.
     *
     * @return  An array of strings naming the files and directories in the
     *          directory denoted by this abstract pathname.  The array will be
     *          empty if the directory is empty.  Returns <code>null</code> if
     *          this abstract pathname does not denote a directory, or if an
     *          I/O error occurs.
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the directory
     */
    public String[] list() {
        return null;
    }

    /** 
     * Returns an array of strings naming the files and directories in the
     * directory denoted by this abstract pathname that satisfy the specified
     * filter.  The behavior of this method is the same as that of the
     * <code>{@link #list()}</code> method, except that the strings in the
     * returned array must satisfy the filter.  If the given
     * <code>filter</code> is <code>null</code> then all names are accepted.
     * Otherwise, a name satisfies the filter if and only if the value
     * <code>true</code> results when the <code>{@link
     * FilenameFilter#accept}</code> method of the filter is invoked on this
     * abstract pathname and the name of a file or directory in the directory
     * that it denotes.
     *
     * @param  filter  A filename filter
     *
     * @return  An array of strings naming the files and directories in the
     *          directory denoted by this abstract pathname that were accepted
     *          by the given <code>filter</code>.  The array will be empty if
     *          the directory is empty or if no names were accepted by the
     *          filter.  Returns <code>null</code> if this abstract pathname
     *          does not denote a directory, or if an I/O error occurs.
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the directory
     */
    public String[] list(FilenameFilter filter) {
        return null;
    }

    /** 
     * Returns an array of abstract pathnames denoting the files in the
     * directory denoted by this abstract pathname.
     *
     * <p> If this abstract pathname does not denote a directory, then this
     * method returns <code>null</code>.  Otherwise an array of
     * <code>File</code> objects is returned, one for each file or directory in
     * the directory.  Pathnames denoting the directory itself and the
     * directory's parent directory are not included in the result.  Each
     * resulting abstract pathname is constructed from this abstract pathname
     * using the <code>{@link #File(java.io.File, java.lang.String)
     * File(File,&nbsp;String)}</code> constructor.  Therefore if this pathname
     * is absolute then each resulting pathname is absolute; if this pathname
     * is relative then each resulting pathname will be relative to the same
     * directory.
     *
     * <p> There is no guarantee that the name strings in the resulting array
     * will appear in any specific order; they are not, in particular,
     * guaranteed to appear in alphabetical order.
     *
     * @return  An array of abstract pathnames denoting the files and
     *          directories in the directory denoted by this abstract
     *          pathname.  The array will be empty if the directory is
     *          empty.  Returns <code>null</code> if this abstract pathname
     *          does not denote a directory, or if an I/O error occurs.
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the directory
     *
     * @since 1.2
     */
    public File[] listFiles() {
        return null;
    }

    /** 
     * Returns an array of abstract pathnames denoting the files and
     * directories in the directory denoted by this abstract pathname that
     * satisfy the specified filter.  The behavior of this method is the
     * same as that of the <code>{@link #listFiles()}</code> method, except
     * that the pathnames in the returned array must satisfy the filter.
     * If the given <code>filter</code> is <code>null</code> then all
     * pathnames are accepted.  Otherwise, a pathname satisfies the filter
     * if and only if the value <code>true</code> results when the
     * <code>{@link FilenameFilter#accept}</code> method of the filter is
     * invoked on this abstract pathname and the name of a file or
     * directory in the directory that it denotes.
     *
     * @param  filter  A filename filter
     *
     * @return  An array of abstract pathnames denoting the files and
     *          directories in the directory denoted by this abstract
     *          pathname.  The array will be empty if the directory is
     *          empty.  Returns <code>null</code> if this abstract pathname
     *          does not denote a directory, or if an I/O error occurs.
     *          
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the directory
     *
     * @since 1.2
     */
    public File[] listFiles(FilenameFilter filter) {
        return null;
    }

    /** 
     * Returns an array of abstract pathnames denoting the files and
     * directories in the directory denoted by this abstract pathname that
     * satisfy the specified filter.  The behavior of this method is the
     * same as that of the <code>{@link #listFiles()}</code> method, except
     * that the pathnames in the returned array must satisfy the filter.
     * If the given <code>filter</code> is <code>null</code> then all
     * pathnames are accepted.  Otherwise, a pathname satisfies the filter
     * if and only if the value <code>true</code> results when the
     * <code>{@link FileFilter#accept(java.io.File)}</code> method of
     * the filter is invoked on the pathname.
     *
     * @param  filter  A file filter
     *
     * @return  An array of abstract pathnames denoting the files and
     *          directories in the directory denoted by this abstract
     *          pathname.  The array will be empty if the directory is
     *          empty.  Returns <code>null</code> if this abstract pathname
     *          does not denote a directory, or if an I/O error occurs.
     *          
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the directory
     *
     * @since 1.2
     */
    public File[] listFiles(FileFilter filter) {
        return null;
    }

    /** 
     * Creates the directory named by this abstract pathname.
     *
     * @return  <code>true</code> if and only if the directory was
     *          created; <code>false</code> otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *          method does not permit the named directory to be created
     */
    public boolean mkdir() {
        return false;
    }

    /** 
     * Creates the directory named by this abstract pathname, including any
     * necessary but nonexistent parent directories.  Note that if this
     * operation fails it may have succeeded in creating some of the necessary
     * parent directories.
     *
     * @return  <code>true</code> if and only if the directory was created,
     *          along with all necessary parent directories; <code>false</code>
     *          otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *          method does not permit the named directory and all necessary
     *          parent directories and to be created
     */
    public boolean mkdirs() {
        return false;
    }

    /** 
     * Renames the file denoted by this abstract pathname.
     * 
     * <p> Whether or not this method can move a file from one filesystem 
     * to another is platform-dependent.  The return value should always 
     * be checked to make sure that the rename operation was successful.
     *
     * @param  dest  The new abstract pathname for the named file
     * 
     * @return  <code>true</code> if and only if the renaming succeeded;
     *          <code>false</code> otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *          method denies write access to either the old or new pathnames
     * 
     * @throws  NullPointerException  
     *          If parameter <code>dest</code> is <code>null</code>
     */
    public boolean renameTo(File dest) {
        return false;
    }

    /** 
     * Sets the last-modified time of the file or directory named by this
     * abstract pathname.
     *
     * <p> All platforms support file-modification times to the nearest second,
     * but some provide more precision.  The argument will be truncated to fit
     * the supported precision.  If the operation succeeds and no intervening
     * operations on the file take place, then the next invocation of the
     * <code>{@link #lastModified}</code> method will return the (possibly
     * truncated) <code>time</code> argument that was passed to this method.
     *
     * @param  time  The new last-modified time, measured in milliseconds since
     *               the epoch (00:00:00 GMT, January 1, 1970)
     *
     * @return <code>true</code> if and only if the operation succeeded;
     *          <code>false</code> otherwise
     *
     * @throws  IllegalArgumentException  If the argument is negative
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *          method denies write access to the named file
     *
     * @since 1.2
     */
    public boolean setLastModified(long time) {
        return false;
    }

    /** 
     * Marks the file or directory named by this abstract pathname so that
     * only read operations are allowed.  After invoking this method the file
     * or directory is guaranteed not to change until it is either deleted or
     * marked to allow write access.  Whether or not a read-only file or
     * directory may be deleted depends upon the underlying system.
     *
     * @return <code>true</code> if and only if the operation succeeded;
     *          <code>false</code> otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *          method denies write access to the named file
     *
     * @since 1.2
     */
    public boolean setReadOnly() {
        return false;
    }

    /** 
     * List the available filesystem roots.
     *
     * <p> A particular Java platform may support zero or more
     * hierarchically-organized file systems.  Each file system has a
     * <code>root</code> directory from which all other files in that file
     * system can be reached.  Windows platforms, for example, have a root
     * directory for each active drive; UNIX platforms have a single root
     * directory, namely <code>"/"</code>.  The set of available filesystem
     * roots is affected by various system-level operations such the insertion
     * or ejection of removable media and the disconnecting or unmounting of
     * physical or virtual disk drives.
     *
     * <p> This method returns an array of <code>File</code> objects that
     * denote the root directories of the available filesystem roots.  It is
     * guaranteed that the canonical pathname of any file physically present on
     * the local machine will begin with one of the roots returned by this
     * method.
     *
     * <p> The canonical pathname of a file that resides on some other machine
     * and is accessed via a remote-filesystem protocol such as SMB or NFS may
     * or may not begin with one of the roots returned by this method.  If the
     * pathname of a remote file is syntactically indistinguishable from the
     * pathname of a local file then it will begin with one of the roots
     * returned by this method.  Thus, for example, <code>File</code> objects
     * denoting the root directories of the mapped network drives of a Windows
     * platform will be returned by this method, while <code>File</code>
     * objects containing UNC pathnames will not be returned by this method.
     *
     * <p> Unlike most methods in this class, this method does not throw
     * security exceptions.  If a security manager exists and its <code>{@link
     * java.lang.SecurityManager#checkRead(java.lang.String)}</code> method
     * denies read access to a particular root directory, then that directory
     * will not appear in the result.
     *
     * @return  An array of <code>File</code> objects denoting the available
     *          filesystem roots, or <code>null</code> if the set of roots
     *          could not be determined.  The array will be empty if there are
     *          no filesystem roots.
     *
     * @since 1.2
     */
    public static File[] listRoots() {
        return null;
    }

    /** 
     * <p> Creates a new empty file in the specified directory, using the
     * given prefix and suffix strings to generate its name.  If this method
     * returns successfully then it is guaranteed that:
     *
     * <ol>
     * <li> The file denoted by the returned abstract pathname did not exist
     *      before this method was invoked, and
     * <li> Neither this method nor any of its variants will return the same
     *      abstract pathname again in the current invocation of the virtual
     *      machine.
     * </ol>
     *
     * This method provides only part of a temporary-file facility.  To arrange
     * for a file created by this method to be deleted automatically, use the
     * <code>{@link #deleteOnExit}</code> method.
     *
     * <p> The <code>prefix</code> argument must be at least three characters
     * long.  It is recommended that the prefix be a short, meaningful string
     * such as <code>"hjb"</code> or <code>"mail"</code>.  The
     * <code>suffix</code> argument may be <code>null</code>, in which case the
     * suffix <code>".tmp"</code> will be used.
     *
     * <p> To create the new file, the prefix and the suffix may first be
     * adjusted to fit the limitations of the underlying platform.  If the
     * prefix is too long then it will be truncated, but its first three
     * characters will always be preserved.  If the suffix is too long then it
     * too will be truncated, but if it begins with a period character
     * (<code>'.'</code>) then the period and the first three characters
     * following it will always be preserved.  Once these adjustments have been
     * made the name of the new file will be generated by concatenating the
     * prefix, five or more internally-generated characters, and the suffix.
     *
     * <p> If the <code>directory</code> argument is <code>null</code> then the
     * system-dependent default temporary-file directory will be used.  The
     * default temporary-file directory is specified by the system property
     * <code>java.io.tmpdir</code>.  On UNIX systems the default value of this
     * property is typically <code>"/tmp"</code> or <code>"/var/tmp"</code>; on
     * Microsoft Windows systems it is typically <code>"c:\\temp"</code>.  A different
     * value may be given to this system property when the Java virtual machine
     * is invoked, but programmatic changes to this property are not guaranteed
     * to have any effect upon the the temporary directory used by this method.
     *
     * @param  prefix     The prefix string to be used in generating the file's
     *                    name; must be at least three characters long
     *
     * @param  suffix     The suffix string to be used in generating the file's
     *                    name; may be <code>null</code>, in which case the
     *                    suffix <code>".tmp"</code> will be used
     *
     * @param  directory  The directory in which the file is to be created, or
     *                    <code>null</code> if the default temporary-file
     *                    directory is to be used
     *
     * @return  An abstract pathname denoting a newly-created empty file
     *
     * @throws  IllegalArgumentException
     *          If the <code>prefix</code> argument contains fewer than three
     *          characters
     *
     * @throws  IOException  If a file could not be created
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *          method does not allow a file to be created
     *
     * @since 1.2
     */
    public static File createTempFile(String prefix, String suffix, File
        directory) throws IOException
    {
        return null;
    }

    /** 
     * Creates an empty file in the default temporary-file directory, using
     * the given prefix and suffix to generate its name.  Invoking this method
     * is equivalent to invoking <code>{@link #createTempFile(java.lang.String,
     * java.lang.String, java.io.File)
     * createTempFile(prefix,&nbsp;suffix,&nbsp;null)}</code>.
     *
     * @param  prefix     The prefix string to be used in generating the file's
     *                    name; must be at least three characters long
     *
     * @param  suffix     The suffix string to be used in generating the file's
     *                    name; may be <code>null</code>, in which case the
     *                    suffix <code>".tmp"</code> will be used
     *
     * @return  An abstract pathname denoting a newly-created empty file
     *
     * @throws  IllegalArgumentException
     *          If the <code>prefix</code> argument contains fewer than three
     *          characters
     *
     * @throws  IOException  If a file could not be created
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *          method does not allow a file to be created
     *
     * @since 1.2
     */
    public static File createTempFile(String prefix, String suffix)
        throws IOException
    {
        return null;
    }

    /** 
     * Compares two abstract pathnames lexicographically.  The ordering
     * defined by this method depends upon the underlying system.  On UNIX
     * systems, alphabetic case is significant in comparing pathnames; on Microsoft Windows
     * systems it is not.
     *
     * @param   pathname  The abstract pathname to be compared to this abstract
     *                    pathname
     * 
     * @return  Zero if the argument is equal to this abstract pathname, a
     *		value less than zero if this abstract pathname is
     *		lexicographically less than the argument, or a value greater
     *		than zero if this abstract pathname is lexicographically
     *		greater than the argument
     *
     * @since   1.2
     */
    public int compareTo(File pathname) {
        return 0;
    }

    /** 
     * Compares this abstract pathname to another object.  If the other object
     * is an abstract pathname, then this function behaves like <code>{@link
     * #compareTo(File)}</code>.  Otherwise, it throws a
     * <code>ClassCastException</code>, since abstract pathnames can only be
     * compared to abstract pathnames.
     *
     * @param   o  The <code>Object</code> to be compared to this abstract
     *             pathname
     *
     * @return  If the argument is an abstract pathname, returns zero
     *          if the argument is equal to this abstract pathname, a value
     *          less than zero if this abstract pathname is lexicographically
     *          less than the argument, or a value greater than zero if this
     *          abstract pathname is lexicographically greater than the
     *          argument
     *
     * @throws  <code>ClassCastException</code> if the argument is not an
     *		abstract pathname
     *
     * @see     java.lang.Comparable
     * @since   1.2
     */
    public int compareTo(Object o) {
        return 0;
    }

    /** 
     * Tests this abstract pathname for equality with the given object.
     * Returns <code>true</code> if and only if the argument is not
     * <code>null</code> and is an abstract pathname that denotes the same file
     * or directory as this abstract pathname.  Whether or not two abstract
     * pathnames are equal depends upon the underlying system.  On UNIX
     * systems, alphabetic case is significant in comparing pathnames; on Microsoft Windows
     * systems it is not.
     *
     * @param   obj   The object to be compared with this abstract pathname
     *
     * @return  <code>true</code> if and only if the objects are the same;
     *          <code>false</code> otherwise
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Computes a hash code for this abstract pathname.  Because equality of
     * abstract pathnames is inherently system-dependent, so is the computation
     * of their hash codes.  On UNIX systems, the hash code of an abstract
     * pathname is equal to the exclusive <em>or</em> of its pathname string
     * and the decimal value <code>1234321</code>.  On Microsoft Windows systems, the hash
     * code is equal to the exclusive <em>or</em> of its pathname string,
     * convered to lower case, and the decimal value <code>1234321</code>.
     *
     * @return  A hash code for this abstract pathname
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the pathname string of this abstract pathname.  This is just the
     * string returned by the <code>{@link #getPath}</code> method.
     *
     * @return  The string form of this abstract pathname
     */
    public String toString() {
        return null;
    }

    /** 
     * readObject is called to restore this filename.
     * The original separator character is read.  If it is different
     * than the separator character on this system, then the old seperator
     * is replaced by the local separator.
     */
    private synchronized void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * WriteObject is called to save this filename.
     * The separator character is saved also so it can be replaced
     * in case the path is reconstituted on a different host type.
     */
    private synchronized void writeObject(ObjectOutputStream s)
        throws IOException
    { }

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 301077366599181567L;

}
