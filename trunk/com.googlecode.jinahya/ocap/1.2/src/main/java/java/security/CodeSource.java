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


  


package java.security;

import java.security.cert.*;

import java.net.URL;
import java.net.SocketPermission;
import java.util.Hashtable;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/** 
 * <p>This class extends the concept of a codebase to
 * encapsulate not only the location (URL) but also the certificate(s)
 * that were used to verify signed code originating from that
 * location.
 *
 * @version 	1.28, 02/02/00
 * @author Li Gong
 * @author Roland Schemers
 */
public class CodeSource implements java.io.Serializable
{
    /** 
     * The code location.
     *
     * @serial
     */
    private URL location;

    /** 
     * Constructs a CodeSource and associates it with the specified 
     * location and set of certificates.
     * 
     * @param url the location (URL).
     * 
     * @param certs the certificate(s).
     */
    public CodeSource(URL url, java.security.cert.Certificate[] certs) { }

    /** 
     * Returns the hash code value for this object.
     *
     * @return a hash code value for this object.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Tests for equality between the specified object and this
     * object. Two CodeSource objects are considered equal if their 
     * locations are of identical value and if the two sets of 
     * certificates are of identical values. It is not required that
     * the certificates be in the same order.
     * 
     * @param obj the object to test for equality with this object.
     * 
     * @return true if the objects are considered equal, false otherwise.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns the location associated with this CodeSource.
     * 
     * @return the location (URL).
     */
    public final URL getLocation() {
        return null;
    }

    /** 
     * Returns the certificates associated with this CodeSource.
     * 
     * @return the certificates
     */
    public final java.security.cert.Certificate[] getCertificates() {
        return null;
    }

    /** 
     * Returns true if this CodeSource object "implies" the specified CodeSource.
     * <P>
     * More specifically, this method makes the following checks, in order. 
     * If any fail, it returns false. If they all succeed, it returns true.<p>
     * <ol>
     * <li> <i>codesource</i> must not be null.
     * <li> If this object's certificates are not null, then all
     * of this object's certificates must be present in <i>codesource</i>'s 
     * certificates.
     * <li> If this object's location (getLocation()) is not null, then the 
     * following checks are made against this object's location and 
     * <i>codesource</i>'s:<p>
     *   <ol>
     *     <li>  <i>codesource</i>'s location must not be null.
     *
     *     <li>  If this object's location 
     *           equals <i>codesource</i>'s location, then return true.
     *
     *     <li>  This object's protocol (getLocation().getProtocol()) must be
     *           equal to <i>codesource</i>'s protocol.
     *
     *     <li>  If this object's host (getLocation().getHost()) is not null,  
     *           then the SocketPermission
     *           constructed with this object's host must imply the
     *           SocketPermission constructed with <i>codesource</i>'s host.
     *
     *     <li>  If this object's port (getLocation().getPort()) is not 
     *           equal to -1 (that is, if a port is specified), it must equal 
     *           <i>codesource</i>'s port.
     *
     *     <li>  If this object's file (getLocation().getFile()) doesn't equal
     *           <i>codesource</i>'s file, then the following checks are made:
     *           If this object's file ends with "/-",
     *           then <i>codesource</i>'s file must start with this object's
     *           file (exclusive the trailing "-").
     *           If this object's file ends with a "/*",
     *           then <i>codesource</i>'s file must start with this object's
     *           file and must not have any further "/" separators.
     *           If this object's file doesn't end with a "/", 
     *           then <i>codesource</i>'s file must match this object's 
     *           file with a '/' appended.
     *
     *     <li>  If this object's reference (getLocation().getRef()) is 
     *           not null, it must equal <i>codesource</i>'s reference.
     *
     *   </ol>
     * </ol>
     * <p>
     * For example, the codesource objects with the following locations
     * and null certificates all imply
     * the codesource with the location "http://java.sun.com/classes/foo.jar"
     * and null certificates:
     * <pre>
     *     http:
     *     http://*.sun.com/classes/*
     *     http://java.sun.com/classes/-
     *     http://java.sun.com/classes/foo.jar
     * </pre>
     * 
     * Note that if this CodeSource has a null location and a null
     * certificate chain, then it implies every other CodeSource.
     *
     * @param codesource CodeSource to compare against.
     *
     * @return true if the specified codesource is implied by this codesource,
     * false if not.  
     */
    public boolean implies(CodeSource codesource) {
        return false;
    }

    /** 
     * Returns a string describing this CodeSource, telling its
     * URL and certificates.
     * 
     * @return information about this CodeSource.
     */
    public String toString() {
        return null;
    }

    /** 
     * Restores this object from a stream (i.e., deserializes it).
     */
    private synchronized void readObject(java.io.ObjectInputStream ois)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * Writes this object out to a stream (i.e., serializes it).
     *
     * @serialData An initial <code>URL</code> is followed by an
     * <code>int</code> indicating the number of certificates to follow 
     * (a value of "zero" denotes that there are no certificates associated
     * with this object).
     * Each certificate is written out starting with a <code>String</code>
     * denoting the certificate type, followed by an
     * <code>int</code> specifying the length of the certificate encoding,
     * followed by the certificate encoding itself which is written out as an
     * array of bytes.
     */
    private synchronized void writeObject(java.io.ObjectOutputStream oos)
        throws IOException
    { }
}
