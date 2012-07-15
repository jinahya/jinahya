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
import java.util.*;

import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/** 
 * This class represents an in-memory collection of keys and certificates.
 * It manages two types of entries:
 *
 * <ul>
 * <li><b>Key Entry</b>
 * <p>This type of keystore entry holds very sensitive cryptographic key
 * information, which is stored in a protected format to prevent unauthorized
 * access.
 *
 * <p>Typically, a key stored in this type of entry is a secret key, or a
 * private key accompanied by the certificate chain for the corresponding
 * public key.
 *
 * <p>Private keys and certificate chains are used by a given entity for
 * self-authentication. Applications for this authentication include software
 * distribution organizations which sign JAR files as part of releasing
 * and/or licensing software.<p>
 *
 * <li><b>Trusted Certificate Entry</b>
 * <p>This type of entry contains a single public key certificate belonging to
 * another party. It is called a <i>trusted certificate</i> because the
 * keystore owner trusts that the public key in the certificate indeed belongs
 * to the identity identified by the <i>subject</i> (owner) of the
 * certificate. 
 *
 * <p>This type of entry can be used to authenticate other parties.
 * </ul>
 *
 * <p>Each entry in a keystore is identified by an "alias" string. In the
 * case of private keys and their associated certificate chains, these strings
 * distinguish among the different ways in which the entity may authenticate
 * itself. For example, the entity may authenticate itself using different
 * certificate authorities, or using different public key algorithms.
 *
 * <p>Whether keystores are persistent, and the mechanisms used by the
 * keystore if it is persistent, are not specified here. This allows
 * use of a variety of techniques for protecting sensitive (e.g., private or
 * secret) keys. Smart cards or other integrated cryptographic engines
 * (SafeKeyper) are one option, and simpler mechanisms such as files may also
 * be used (in a variety of formats).
 *
 * <p>There are two ways to request a KeyStore object: by
 * specifying either just a keystore type, or both a keystore type
 * and a package provider.
 *
 * <ul>
 * <li>If just a keystore type is specified:
 * <pre>
 *      KeyStore ks = KeyStore.getInstance("JKS");
 * </pre>
 * the system will determine if there is an implementation of the keystore type
 * requested available in the environment, and if there is more than one, if
 * there is a preferred one.<p>
 * 
 * <li>If both a keystore type and a package provider are specified:
 * <pre>
 *      KeyStore ks = KeyStore.getInstance("JKS", "SUN");
 * </pre>
 * the system will determine if there is an implementation of the
 * keystore type in the package requested, and throw an exception if there
 * is not.
 *
 * </ul>
 *
 * <p>Before a keystore can be accessed, it must be
 * {@link #load(java.io.InputStream, char[]) loaded}. In order to create 
 * an empty keystore, you pass <code>null</code>
 * as the <code>InputStream</code> argument to the <code>load</code> method.
 *
 * @author Jan Luehe
 *
 * @version 1.29, 02/02/00
 *
 * @see java.security.PrivateKey
 * @see java.security.cert.Certificate
 *
 * @since 1.2
 */
public class KeyStore
{

    /** 
     * Creates a KeyStore object of the given type, and encapsulates the given
     * provider implementation (SPI object) in it.
     *
     * @param keyStoreSpi the provider implementation.
     * @param provider the provider.
     * @param type the keystore type.
     */
    protected KeyStore(KeyStoreSpi keyStoreSpi, Provider provider, String type)
    { }

    /** 
     * Generates a keystore object of the given type.
     * 
     * <p>If the default provider package provides a keystore implementation
     * of the given type, an instance of <code>KeyStore</code> containing that
     * implementation is returned. If the requested keystore type is not
     * available in the default package, other packages are searched.
     *
     * @param type the type of keystore. 
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard keystore types.
     *
     * @return a keystore object of the specified type.
     *
     * @exception KeyStoreException if the requested keystore type is
     * not available in the default provider package or any of the other
     * provider packages that were searched.  
     */
    public static KeyStore getInstance(String type) throws KeyStoreException {
        return null;
    }

    /** 
     * Generates a keystore object for the specified keystore
     * type from the specified provider.
     *
     * @param type the type of keystore.
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard keystore types.
     *
     * @param provider the name of the provider.
     *
     * @return a keystore object of the specified type, as
     * supplied by the specified provider.
     *
     * @exception KeyStoreException if the requested keystore type is not
     * available from the provider.
     * 
     * @exception NoSuchProviderException if the provider has not been
     * configured.
     *
     * @exception IllegalArgumentException if the provider name is null
     * or empty.
     *
     * @see Provider
     */
    public static KeyStore getInstance(String type, String provider)
        throws KeyStoreException, NoSuchProviderException
    {
        return null;
    }

    /** 
     * Generates a keystore object for the specified keystore
     * type from the specified provider. Note: the <code>provider</code> 
     * doesn't have to be registered. 
     *
     * @param type the type of keystore.
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard keystore types.
     *
     * @param provider the provider.
     *
     * @return a keystore object of the specified type, as
     * supplied by the specified provider.
     *
     * @exception KeyStoreException if the requested keystore type is not
     * available from the provider.
     *
     * @exception IllegalArgumentException if the <code>provider</code> is
     * null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public static KeyStore getInstance(String type, Provider provider)
        throws KeyStoreException
    {
        return null;
    }

    /** 
     * Returns the provider of this keystore.
     * 
     * @return the provider of this keystore.
     */
    public final Provider getProvider() {
        return null;
    }

    /** 
     * Returns the type of this keystore.
     *
     * @return the type of this keystore.
     */
    public final String getType() {
        return null;
    }

    /** 
     * Returns the key associated with the given alias, using the given
     * password to recover it.
     *
     * @param alias the alias name
     * @param password the password for recovering the key
     *
     * @return the requested key, or null if the given alias does not exist
     * or does not identify a <i>key entry</i>.
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     * @exception NoSuchAlgorithmException if the algorithm for recovering the
     * key cannot be found
     * @exception UnrecoverableKeyException if the key cannot be recovered
     * (e.g., the given password is wrong).
     */
    public final Key getKey(String alias, char[] password)
        throws KeyStoreException, NoSuchAlgorithmException,
        UnrecoverableKeyException
    {
        return null;
    }

    /** 
     * Returns the certificate chain associated with the given alias.
     *
     * @param alias the alias name
     *
     * @return the certificate chain (ordered with the user's certificate first
     * and the root certificate authority last), or null if the given alias
     * does not exist or does not contain a certificate chain (i.e., the given 
     * alias identifies either a <i>trusted certificate entry</i> or a
     * <i>key entry</i> without a certificate chain).
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     */
    public final Certificate[] getCertificateChain(String alias)
        throws KeyStoreException
    {
        return null;
    }

    /** 
     * Returns the certificate associated with the given alias.
     *
     * <p>If the given alias name identifies a
     * <i>trusted certificate entry</i>, the certificate associated with that
     * entry is returned. If the given alias name identifies a
     * <i>key entry</i>, the first element of the certificate chain of that
     * entry is returned, or null if that entry does not have a certificate
     * chain.
     *
     * @param alias the alias name
     *
     * @return the certificate, or null if the given alias does not exist or
     * does not contain a certificate.
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     */
    public final Certificate getCertificate(String alias)
        throws KeyStoreException
    {
        return null;
    }

    /** 
     * Returns the creation date of the entry identified by the given alias.
     *
     * @param alias the alias name
     *
     * @return the creation date of this entry, or null if the given alias does
     * not exist
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     */
    public final Date getCreationDate(String alias) throws KeyStoreException {
        return null;
    }

    /** 
     * Assigns the given key to the given alias, protecting it with the given
     * password.
     *
     * <p>If the given key is of type <code>java.security.PrivateKey</code>,
     * it must be accompanied by a certificate chain certifying the
     * corresponding public key.
     *
     * <p>If the given alias already exists, the keystore information
     * associated with it is overridden by the given key (and possibly
     * certificate chain).
     *
     * @param alias the alias name
     * @param key the key to be associated with the alias
     * @param password the password to protect the key
     * @param chain the certificate chain for the corresponding public
     * key (only required if the given key is of type
     * <code>java.security.PrivateKey</code>).
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded), the given key cannot be protected, or this operation fails
     * for some other reason
     */
    public final void setKeyEntry(String alias, Key key, char[] password,
        Certificate[] chain) throws KeyStoreException
    { }

    /** 
     * Assigns the given key (that has already been protected) to the given
     * alias.
     * 
     * <p>If the protected key is of type
     * <code>java.security.PrivateKey</code>, it must be accompanied by a
     * certificate chain certifying the corresponding public key.
     *
     * <p>If the given alias already exists, the keystore information
     * associated with it is overridden by the given key (and possibly
     * certificate chain).
     *
     * @param alias the alias name
     * @param key the key (in protected format) to be associated with the alias
     * @param chain the certificate chain for the corresponding public
     * key (only useful if the protected key is of type
     * <code>java.security.PrivateKey</code>).
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded), or if this operation fails for some other reason.
     */
    public final void setKeyEntry(String alias, byte[] key, Certificate[] chain)
        throws KeyStoreException
    { }

    /** 
     * Assigns the given certificate to the given alias.
     *
     * <p>If the given alias already exists in this keystore and identifies a
     * <i>trusted certificate entry</i>, the certificate associated with it is
     * overridden by the given certificate.
     *
     * @param alias the alias name
     * @param cert the certificate
     *
     * @exception KeyStoreException if the keystore has not been initialized,
     * or the given alias already exists and does not identify a
     * <i>trusted certificate entry</i>, or this operation fails for some
     * other reason.
     */
    public final void setCertificateEntry(String alias, Certificate cert)
        throws KeyStoreException
    { }

    /** 
     * Deletes the entry identified by the given alias from this keystore.
     *
     * @param alias the alias name
     *
     * @exception KeyStoreException if the keystore has not been initialized,
     * or if the entry cannot be removed.
     */
    public final void deleteEntry(String alias) throws KeyStoreException { }

    /** 
     * Lists all the alias names of this keystore.
     *
     * @return enumeration of the alias names
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     */
    public final Enumeration aliases() throws KeyStoreException {
        return null;
    }

    /** 
     * Checks if the given alias exists in this keystore.
     *
     * @param alias the alias name
     *
     * @return true if the alias exists, false otherwise
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     */
    public final boolean containsAlias(String alias) throws KeyStoreException {
        return false;
    }

    /** 
     * Retrieves the number of entries in this keystore.
     *
     * @return the number of entries in this keystore
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     */
    public final int size() throws KeyStoreException {
        return 0;
    }

    /** 
     * Returns true if the entry identified by the given alias is a
     * <i>key entry</i>, and false otherwise.
     *
     * @param alias the alias for the keystore entry to be checked
     *
     * @return true if the entry identified by the given alias is a
     * <i>key entry</i>, false otherwise.
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     */
    public final boolean isKeyEntry(String alias) throws KeyStoreException {
        return false;
    }

    /** 
     * Returns true if the entry identified by the given alias is a
     * <i>trusted certificate entry</i>, and false otherwise.
     *
     * @param alias the alias for the keystore entry to be checked
     *
     * @return true if the entry identified by the given alias is a
     * <i>trusted certificate entry</i>, false otherwise.
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     */
    public final boolean isCertificateEntry(String alias)
        throws KeyStoreException
    {
        return false;
    }

    /** 
     * Returns the (alias) name of the first keystore entry whose certificate
     * matches the given certificate.
     *
     * <p>This method attempts to match the given certificate with each
     * keystore entry. If the entry being considered
     * is a <i>trusted certificate entry</i>, the given certificate is
     * compared to that entry's certificate. If the entry being considered is
     * a <i>key entry</i>, the given certificate is compared to the first
     * element of that entry's certificate chain (if a chain exists).
     *
     * @param cert the certificate to match with.
     *
     * @return the (alias) name of the first entry with matching certificate,
     * or null if no such entry exists in this keystore.
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     */
    public final String getCertificateAlias(Certificate cert)
        throws KeyStoreException
    {
        return null;
    }

    /** 
     * Stores this keystore to the given output stream, and protects its
     * integrity with the given password.
     *
     * @param stream the output stream to which this keystore is written.
     * @param password the password to generate the keystore integrity check
     *
     * @exception KeyStoreException if the keystore has not been initialized
     * (loaded).
     * @exception IOException if there was an I/O problem with data
     * @exception NoSuchAlgorithmException if the appropriate data integrity
     * algorithm could not be found
     * @exception CertificateException if any of the certificates included in
     * the keystore data could not be stored
     */
    public final void store(OutputStream stream, char[] password)
        throws KeyStoreException, IOException, NoSuchAlgorithmException,
        java.security.cert.CertificateException
    { }

    /** 
     * Loads this KeyStore from the given input stream.
     *
     * <p>If a password is given, it is used to check the integrity of the
     * keystore data. Otherwise, the integrity of the keystore is not checked.
     *
     * <p>In order to create an empty keystore, or if the keystore cannot
     * be initialized from a stream (e.g., because it is stored on a hardware
     * token device), you pass <code>null</code>
     * as the <code>stream</code> argument.
     *
     * <p> Note that if this KeyStore has already been loaded, it is
     * reinitialized and loaded again from the given input stream.
     *
     * @param stream the input stream from which the keystore is loaded, or
     * null if an empty keystore is to be created.
     * @param password the (optional) password used to check the integrity of
     * the keystore.
     *
     * @exception IOException if there is an I/O or format problem with the
     * keystore data
     * @exception NoSuchAlgorithmException if the algorithm used to check
     * the integrity of the keystore cannot be found
     * @exception CertificateException if any of the certificates in the
     * keystore could not be loaded
     */
    public final void load(InputStream stream, char[] password)
        throws IOException, NoSuchAlgorithmException,
        java.security.cert.CertificateException
    { }

    /** 
     * Returns the default keystore type as specified in the Java security
     * properties file, or the string &quot;jks&quot; (acronym for &quot;Java keystore&quot;)
     * if no such property exists.
     * The Java security properties file is located in the file named
     * &lt;JAVA_HOME&gt;/lib/security/java.security, where &lt;JAVA_HOME&gt;
     * refers to the directory where the SDK was installed.
     *
     * <p>The default keystore type can be used by applications that do not
     * want to use a hard-coded keystore type when calling one of the
     * <code>getInstance</code> methods, and want to provide a default keystore
     * type in case a user does not specify its own.
     *
     * <p>The default keystore type can be changed by setting the value of the
     * "keystore.type" security property (in the Java security properties
     * file) to the desired keystore type.
     *
     * @return the default keystore type as specified in the 
     * Java security properties file, or the string &quot;jks&quot;
     * if no such property exists.
     */
    public static final String getDefaultType() {
        return null;
    }
}
