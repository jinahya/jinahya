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


  


package java.lang;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/** 
 * <code>Package</code> objects contain version information
 * about the implementation and specification of a Java package.
 * This versioning information is retrieved and made available
 * by the {@link ClassLoader <code>ClassLoader</code>} instance that
 * loaded the class(es).  Typically, it is stored in the manifest that is
 * distributed with the classes.
 *
 * <p>The set of classes that make up the package may implement a
 * particular specification and if so the specification title, version number,
 * and vendor strings identify that specification.
 * An application can ask if the package is
 * compatible with a particular version, see the {@link #isCompatibleWith
 * <code>isCompatibleWith</code>} method for details.
 *
 * <p>Specification version numbers use a syntax that consists of positive
 * decimal integers separated by periods ".", for example "2.0" or
 * "1.2.3.4.5.6.7".  This allows an extensible number to be used to represent
 * major, minor, micro, etc. versions.  The version specification is described
 * by the following formal grammar:
 * <blockquote>
 * <dl>
 * <dt><i>SpecificationVersion:
 * <dd>Digits RefinedVersion<sub>opt</sub></i>
 *
 * <p><dt><i>RefinedVersion:</i>
 * <dd><code>.</code> <i>Digits</i>
 * <dd><code>.</code> <i>Digits RefinedVersion</i>
 *
 * <p><dt><i>Digits:
 * <dd>Digit
 * <dd>Digits</i>
 *
 * <p><dt><i>Digit:</i>
 * <dd>any character for which {@link Character#isDigit} returns <code>true</code>,
 * e.g. 0, 1, 2, ...
 * </dl>
 * </blockquote>
 *
 * <p>The implementation title, version, and vendor strings identify an
 * implementation and are made available conveniently to enable accurate
 * reporting of the packages involved when a problem occurs. The contents
 * all three implementation strings are vendor specific. The
 * implementation version strings have no specified syntax and should
 * only be compared for equality with desired version identifers.
 *
 * <p>Within each <code>ClassLoader</code> instance all classes from the same
 * java package have the same Package object.  The static methods allow a package
 * to be found by name or the set of all packages known to the current class
 * loader to be found.
 *
 * @see ClassLoader#definePackage
 */
public class Package
{

    private Package() { }

    /** 
     * Return the name of this package.
     *
     * @return The name of this package using the Java language dot notation
     * 		for the package. i.e  java.lang
     */
    public java.lang.String getName() {
        return null;
    }

    /** 
     * Return the title of the specification that this package implements.
     * @return the specification title, null is returned if it is not known.
     */
    public java.lang.String getSpecificationTitle() {
        return null;
    }

    /** 
     * Returns the version number of the specification
     * that this package implements.
     * This version string must be a sequence of positive decimal
     * integers separated by "."'s and may have leading zeros.
     * When version strings are compared the most significant
     * numbers are compared.
     * @return the specification version, null is returned if it is not known.
     */
    public java.lang.String getSpecificationVersion() {
        return null;
    }

    /** 
     * Return the name of the organization, vendor,
     * or company that owns and maintains the specification
     * of the classes that implement this package.
     * @return the specification vendor, null is returned if it is not known.
     */
    public java.lang.String getSpecificationVendor() {
        return null;
    }

    /** 
     * Return the title of this package.
     * @return the title of the implementation, null is returned if it is not known.
     */
    public java.lang.String getImplementationTitle() {
        return null;
    }

    /** 
     * Return the version of this implementation. It consists of any string
     * assigned by the vendor of this implementation and does
     * not have any particular syntax specified or expected by the Java
     * runtime. It may be compared for equality with other
     * package version strings used for this implementation
     * by this vendor for this package.
     * @return the version of the implementation, null is returned if it is not known.
     */
    public java.lang.String getImplementationVersion() {
        return null;
    }

    /** 
     * Returns the name of the organization,
     * vendor or company that provided this implementation.
     * @return the vendor that implemented this package..
     */
    public java.lang.String getImplementationVendor() {
        return null;
    }

    /** 
     * Returns true if this package is sealed.
     *
     * @return true if the package is sealed, false otherwise
     */
    public boolean isSealed() {
        return false;
    }

    /** 
     * Returns true if this package is sealed with respect to the specified
     * code source url.
     *
     * @param url the code source url
     * @return true if this package is sealed with respect to url
     */
    public boolean isSealed(URL url) {
        return false;
    }

    /** 
     * Compare this package's specification version with a
     * desired version. It returns true if
     * this packages specification version number is greater than or equal
     * to the desired version number. <p>
     *
     * Version numbers are compared by sequentially comparing corresponding
     * components of the desired and specification strings.
     * Each component is converted as a decimal integer and the values
     * compared.
     * If the specification value is greater than the desired
     * value true is returned. If the value is less false is returned.
     * If the values are equal the period is skipped and the next pair of
     * components is compared.
     *
     * @param desired the version string of the desired version.
     * @return true if this package's version number is greater
     * 		than or equal to the desired version number
     *
     * @exception NumberFormatException if the desired or current version
     *		is not of the correct dotted form.
     */
    public boolean isCompatibleWith(java.lang.String desired)
        throws java.lang.NumberFormatException
    {
        return false;
    }

    /** 
     * Find a package by name in the callers <code>ClassLoader</code> instance.
     * The callers <code>ClassLoader</code> instance is used to find the package
     * instance corresponding to the named class. If the callers
     * <code>ClassLoader</code> instance is null then the set of packages loaded
     * by the system <code>ClassLoader</code> instance is searched to find the
     * named package. <p>
     *
     * Packages have attributes for versions and specifications only if the class
     * loader created the package instance with the appropriate attributes. Typically,
     * those attributes are defined in the manifests that accompany the classes.
     *
     * @param name a package name, for example, java.lang.
     * @return the package of the requested name. It may be null if no package
     * 		information is available from the archive or codebase.
     */
    public static java.lang.Package getPackage(java.lang.String name) {
        return null;
    }

    /** 
     * Get all the packages currently known for the caller's <code>ClassLoader</code>
     * instance.  Those packages correspond to classes loaded via or accessible by
     * name to that <code>ClassLoader</code> instance.  If the caller's
     * <code>ClassLoader</code> instance is the bootstrap <code>ClassLoader</code>
     * instance, which may be represented by <code>null</code> in some implementations,
     * only packages corresponding to classes loaded by the bootstrap
     * <code>ClassLoader</code> instance will be returned.
     *
     * @return a new array of packages known to the callers <code>ClassLoader</code>
     * instance.  An zero length array is returned if none are known.
     */
    public static java.lang.Package[] getPackages() {
        return null;
    }

    /** 
     * Return the hash code computed from the package name.
     * @return the hash code computed from the package name.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the string representation of this Package.
     * Its value is the string "package " and the package name.
     * If the package title is defined it is appended.
     * If the package version is defined it is appended.
     * @return the string representation of the package.
     */
    public java.lang.String toString() {
        return null;
    }
}
