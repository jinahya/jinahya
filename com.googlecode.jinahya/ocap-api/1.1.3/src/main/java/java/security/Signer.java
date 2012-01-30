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

import java.io.*;

/** 
 * This class is used to represent an Identity that can also digitally
 * sign data.
 *
 * <p>The management of a signer's private keys is an important and
 * sensitive issue that should be handled by subclasses as appropriate
 * to their intended use.
 *
 * @see Identity
 *
 * @version 1.37 00/02/02
 * @author Benjamin Renaud
 *
 * @deprecated This class is no longer used. Its functionality has been
 * replaced by <code>java.security.KeyStore</code>, the
 * <code>java.security.cert</code> package, and
 * <code>java.security.Principal</code>.
 */
public abstract class Signer extends Identity
{
    /** 
     * The signer's private key.
     *
     * @serial
     */
    private PrivateKey privateKey;

    /** 
     * Creates a signer. This constructor should only be used for
     * serialization.
     */
    protected Signer() { }

    /** 
     * Creates a signer with the specified identity name.
     *
     * @param name the identity name.
     */
    public Signer(String name) { }

    /** 
     * Creates a signer with the specified identity name and scope.
     *
     * @param name the identity name.
     *
     * @param scope the scope of the identity.
     *
     * @exception KeyManagementException if there is already an identity
     * with the same name in the scope.
     */
    public Signer(String name, IdentityScope scope)
        throws KeyManagementException
    { }

    /** 
     * Returns this signer's private key.
     *
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with <code>"getSignerPrivateKey"</code> 
     * as its argument to see if it's ok to return the private key. 
     * 
     * @return this signer's private key, or null if the private key has
     * not yet been set.
     * 
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkSecurityAccess</code> method doesn't allow 
     * returning the private key.
     * 
     * @see SecurityManager#checkSecurityAccess
     */
    public PrivateKey getPrivateKey() {
        return null;
    }

    /** 
     * Sets the key pair (public key and private key) for this signer.
     *
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with <code>"setSignerKeyPair"</code> 
     * as its argument to see if it's ok to set the key pair. 
     * 
     * @param pair an initialized key pair.
     *
     * @exception InvalidParameterException if the key pair is not
     * properly initialized.
     * @exception KeyException if the key pair cannot be set for any
     * other reason.
     * @exception  SecurityException  if a security manager exists and its  
     * <code>checkSecurityAccess</code> method doesn't allow 
     * setting the key pair.
     * 
     * @see SecurityManager#checkSecurityAccess
     */
    public final void setKeyPair(KeyPair pair)
        throws InvalidParameterException, KeyException
    { }

    /** 
     * Returns a string of information about the signer.
     *
     * @return a string of information about the signer.
     */
    public String toString() {
        return null;
    }
}
