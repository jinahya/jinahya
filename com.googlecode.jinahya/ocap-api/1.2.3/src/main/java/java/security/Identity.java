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

import java.util.*;

import java.io.Serializable;

/** 
 * <p>This class represents identities: real-world objects such as people,
 * companies or organizations whose identities can be authenticated using 
 * their public keys. Identities may also be more abstract (or concrete) 
 * constructs, such as daemon threads or smart cards.
 *
 * <p>All Identity objects have a name and a public key. Names are
 * immutable. Identities may also be scoped. That is, if an Identity is
 * specified to have a particular scope, then the name and public
 * key of the Identity are unique within that scope.
 *
 * <p>An Identity also has a set of certificates (all certifying its own
 * public key). The Principal names specified in these certificates need 
 * not be the same, only the key.
 *
 * <p>An Identity can be subclassed, to include postal and email addresses,
 * telephone numbers, images of faces and logos, and so on.
 *
 * @see IdentityScope
 * @see Signer
 * @see Principal
 *
 * @version 1.56
 * @author Benjamin Renaud
 * @deprecated This class is no longer used. Its functionality has been
 * replaced by <code>java.security.KeyStore</code>, the
 * <code>java.security.cert</code> package, and
 * <code>java.security.Principal</code>.
 */
public abstract class Identity implements Principal, Serializable
{
    /** 
     * The name for this identity.
     *
     * @serial
     */
    private String name;

    /** 
     * The public key for this identity.
     *
     * @serial
     */
    private PublicKey publicKey;

    /** use serialVersionUID from JDK 1.1.x for interoperability */
    private static final long serialVersionUID = 3609922007826600659L;

    /** 
     * Generic, descriptive information about the identity.
     *
     * @serial
     */
     String info;

    /** 
     * The scope of the identity.
     *
     * @serial
     */
     IdentityScope scope;

    /** 
     * The certificates for this identity.
     *
     * @serial
     */
     Vector certificates;

    /** 
     * Constructor for serialization only.
     */
    protected Identity() { }

    /** 
     * Constructs an identity with the specified name and scope.
     *
     * @param name the identity name.  
     * @param scope the scope of the identity.
     *
     * @exception KeyManagementException if there is already an identity 
     * with the same name in the scope.
     */
    public Identity(String name, IdentityScope scope)
        throws KeyManagementException
    { }

    /** 
     * Constructs an identity with the specified name and no scope.
     *
     * @param name the identity name.
     */
    public Identity(String name) { }

    /** 
     * Returns this identity's name.
     *
     * @return the name of this identity.
     */
    public final String getName() {
        return null;
    }

    /** 
     * Returns this identity's scope.
     *
     * @return the scope of this identity.
     */
    public final IdentityScope getScope() {
        return null;
    }

    /** 
     * Returns this identity's public key.
     * 
     * @return the public key for this identity.
     * 
     * @see #setPublicKey
     */
    public PublicKey getPublicKey() {
        return null;
    }

    /** 
     * Sets this identity's public key. The old key and all of this
     * identity's certificates are removed by this operation. 
     *
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with <code>"setIdentityPublicKey"</code> 
     * as its argument to see if it's ok to set the public key. 
     * 
     * @param key the public key for this identity.
     *
     * @exception KeyManagementException if another identity in the 
     * identity's scope has the same public key, or if another exception occurs.  
     * 
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkSecurityAccess</code> method doesn't allow 
     * setting the public key.
     * 
     * @see #getPublicKey
     * @see SecurityManager#checkSecurityAccess
     */
    public void setPublicKey(PublicKey key) throws KeyManagementException { }

    /** 
     * Specifies a general information string for this identity.
     *
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with <code>"setIdentityInfo"</code> 
     * as its argument to see if it's ok to specify the information string. 
     * 
     * @param info the information string.
     * 
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkSecurityAccess</code> method doesn't allow 
     * setting the information string.
     * 
     * @see #getInfo
     * @see SecurityManager#checkSecurityAccess
     */
    public void setInfo(String info) { }

    /** 
     * Returns general information previously specified for this identity.
     *
     * @return general information about this identity.
     *
     * @see #setInfo
     */
    public String getInfo() {
        return null;
    }

    /** 
     * Adds a certificate for this identity. If the identity has a public
     * key, the public key in the certificate must be the same, and if
     * the identity does not have a public key, the identity's
     * public key is set to be that specified in the certificate.
     *
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with <code>"addIdentityCertificate"</code> 
     * as its argument to see if it's ok to add a certificate. 
     * 
     * @param certificate the certificate to be added.
     *
     * @exception KeyManagementException if the certificate is not valid,
     * if the public key in the certificate being added conflicts with
     * this identity's public key, or if another exception occurs.
     * 
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkSecurityAccess</code> method doesn't allow 
     * adding a certificate.
     * 
     * @see SecurityManager#checkSecurityAccess
     */
    public void addCertificate(Certificate certificate)
        throws KeyManagementException
    { }

    /** 
     * Removes a certificate from this identity.
     *
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with <code>"removeIdentityCertificate"</code> 
     * as its argument to see if it's ok to remove a certificate. 
     * 
     * @param certificate the certificate to be removed.
     *
     * @exception KeyManagementException if the certificate is
     * missing, or if another exception occurs.
     * 
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkSecurityAccess</code> method doesn't allow 
     * removing a certificate.
     * 
     * @see SecurityManager#checkSecurityAccess
     */
    public void removeCertificate(Certificate certificate)
        throws KeyManagementException
    { }

    /** 
     * Returns a copy of all the certificates for this identity.  
     * 
     * @return a copy of all the certificates for this identity.  
     */
    public Certificate[] certificates() {
        return null;
    }

    /** 
     * Tests for equality between the specified object and this identity.
     * This first tests to see if the entities actually refer to the same
     * object, in which case it returns true. Next, it checks to see if
     * the entities have the same name and the same scope. If they do, 
     * the method returns true. Otherwise, it calls 
     * {@link #identityEquals(Identity) identityEquals}, which subclasses should 
     * override.
     *
     * @param identity the object to test for equality with this identity.  
     *
     * @return true if the objects are considered equal, false otherwise.
     *
     * @see #identityEquals 
     */
    public final boolean equals(Object identity) {
        return false;
    }

    /** 
     * Tests for equality between the specified identity and this identity.
     * This method should be overriden by subclasses to test for equality. 
     * The default behavior is to return true if the names and public keys 
     * are equal.
     *
     * @param identity the identity to test for equality with this identity.
     * 
     * @return true if the identities are considered equal, false
     * otherwise. 
     *
     * @see #equals 
     */
    protected boolean identityEquals(Identity identity) {
        return false;
    }

    /** 
     * Returns a short string describing this identity, telling its
     * name and its scope (if any).
     *
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with <code>"printIdentity"</code> 
     * as its argument to see if it's ok to return the string. 
     *
     * @return information about this identity, such as its name and the  
     * name of its scope (if any).
     * 
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkSecurityAccess</code> method doesn't allow 
     * returning a string describing this identity.
     * 
     * @see SecurityManager#checkSecurityAccess
     */
    public String toString() {
        return null;
    }

    /** 
     * Returns a string representation of this identity, with
     * optionally more details than that provided by the
     * <code>toString</code> method without any arguments.
     *
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with <code>"printIdentity"</code> 
     * as its argument to see if it's ok to return the string. 
     *
     * @param detailed whether or not to provide detailed information.  
     *
     * @return information about this identity. If <code>detailed</code>
     * is true, then this method returns more information than that 
     * provided by the <code>toString</code> method without any arguments.
     *
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkSecurityAccess</code> method doesn't allow 
     * returning a string describing this identity.
     * 
     * @see #toString
     * @see SecurityManager#checkSecurityAccess
     */
    public String toString(boolean detailed) {
        return null;
    }

    /** 
     * Returns a hashcode for this identity.
     *
     * @return a hashcode for this identity.
     */
    public int hashCode() {
        return 0;
    }
}
