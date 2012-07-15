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

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

import java.net.URL;
import java.security.InvalidParameterException;

/** 
 * <p>This class centralizes all security properties and common security
 * methods. One of its primary uses is to manage providers.
 *
 * @author Benjamin Renaud
 * @version 1.101 10/17/00
 */
public final class Security
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private Security() { }

    /** 
     * Adds a new provider, at a specified position. The position is
     * the preference order in which providers are searched for
     * requested algorithms. Note that it is not guaranteed that this
     * preference will be respected. The position is 1-based, that is,
     * 1 is most preferred, followed by 2, and so on.
     * 
     * <p>If the given provider is installed at the requested position,
     * the provider that used to be at that position, and all providers
     * with a position greater than <code>position</code>, are shifted up
     * one position (towards the end of the list of installed providers).
     * 
     * <p>A provider cannot be added if it is already installed.
     * 
     * <p>First, if there is a security manager, its
     * <code>checkSecurityAccess</code> 
     * method is called with the string
     * <code>"insertProvider."+provider.getName()</code> 
     * to see if it's ok to add a new provider. 
     * If the default implementation of <code>checkSecurityAccess</code> 
     * is used (i.e., that method is not overriden), then this will result in
     * a call to the security manager's <code>checkPermission</code> method
     * with a
     * <code>SecurityPermission("insertProvider."+provider.getName())</code>
     * permission.
     *
     * @param provider the provider to be added.
     *
     * @param position the preference position that the caller would
     * like for this provider.
     * 
     * @return the actual preference position in which the provider was 
     * added, or -1 if the provider was not added because it is
     * already installed.
     *
     * @throws  SecurityException
     *          if a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkSecurityAccess}</code> method
     *          denies access to add a new provider
     *
     * @see #getProvider
     * @see #removeProvider 
     * @see java.security.SecurityPermission
     */
    public static synchronized int insertProviderAt(Provider provider, int
        position)
    {
        return 0;
    }

    /** 
     * Adds a provider to the next position available.
     *
     * <p>First, if there is a security manager, its
     * <code>checkSecurityAccess</code> 
     * method is called with the string
     * <code>"insertProvider."+provider.getName()</code> 
     * to see if it's ok to add a new provider. 
     * If the default implementation of <code>checkSecurityAccess</code> 
     * is used (i.e., that method is not overriden), then this will result in
     * a call to the security manager's <code>checkPermission</code> method
     * with a
     * <code>SecurityPermission("insertProvider."+provider.getName())</code>
     * permission.
     * 
     * @param provider the provider to be added.
     *
     * @return the preference position in which the provider was 
     * added, or -1 if the provider was not added because it is
     * already installed.
     *
     * @throws  SecurityException
     *          if a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkSecurityAccess}</code> method
     *          denies access to add a new provider
     * 
     * @see #getProvider
     * @see #removeProvider
     * @see java.security.SecurityPermission
     */
    public static int addProvider(Provider provider) {
        return 0;
    }

    /** 
     * Removes the provider with the specified name.
     *
     * <p>When the specified provider is removed, all providers located
     * at a position greater than where the specified provider was are shifted
     * down one position (towards the head of the list of installed
     * providers).
     *
     * <p>This method returns silently if the provider is not installed.
     * 
     * <p>First, if there is a security manager, its
     * <code>checkSecurityAccess</code> 
     * method is called with the string <code>"removeProvider."+name</code> 
     * to see if it's ok to remove the provider. 
     * If the default implementation of <code>checkSecurityAccess</code> 
     * is used (i.e., that method is not overriden), then this will result in
     * a call to the security manager's <code>checkPermission</code> method
     * with a <code>SecurityPermission("removeProvider."+name)</code>
     * permission.
     *
     * @param name the name of the provider to remove.
     *
     * @throws  SecurityException
     *          if a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkSecurityAccess}</code> method
     *          denies
     *          access to remove the provider
     *
     * @see #getProvider
     * @see #addProvider
     */
    public static synchronized void removeProvider(String name) { }

    /** 
     * Returns an array containing all the installed providers. The order of
     * the providers in the array is their preference order.
     * 
     * @return an array of all the installed providers.
     */
    public static synchronized Provider[] getProviders() {
        return null;
    }

    /** 
     * Returns the provider installed with the specified name, if
     * any. Returns null if no provider with the specified name is
     * installed.
     * 
     * @param name the name of the provider to get.
     * 
     * @return the provider of the specified name.
     *
     * @see #removeProvider
     * @see #addProvider
     */
    public static synchronized Provider getProvider(String name) {
        return null;
    }

    /** 
     * Returns an array containing all installed providers that satisfy the
     * specified selection criterion, or null if no such providers have been
     * installed. The returned providers are ordered
     * according to their <a href=
     * "#insertProviderAt(java.security.Provider, int)">preference order</a>. 
     * 
     * <p> A cryptographic service is always associated with a particular
     * algorithm or type. For example, a digital signature service is
     * always associated with a particular algorithm (e.g., DSA),
     * and a CertificateFactory service is always associated with
     * a particular certificate type (e.g., X.509).
     * NOTE: <B>java.security.cert.CertificateFactory</B> is found in J2ME CDC 
     * profiles such as J2ME Foundation Profile.
     *
     * <p>The selection criterion must be specified in one of the following two formats:
     * <ul>
     * <li> <i>&lt;crypto_service>.&lt;algorithm_or_type></i> <p> The
     * cryptographic service name must not contain any dots.
     * <p> A 
     * provider satisfies the specified selection criterion iff the provider implements the 
     * specified algorithm or type for the specified cryptographic service.
     * <p> For example, "CertificateFactory.X.509" 
     * would be satisfied by any provider that supplied
     * a CertificateFactory implementation for X.509 certificates.
     * NOTE: <B>java.security.cert.CertificateFactory</B> is found in J2ME CDC 
     * profiles such as J2ME Foundation Profile.
     * <li> <i>&lt;crypto_service>.&lt;algorithm_or_type> &lt;attribute_name>:&lt attribute_value></i>
     * <p> The cryptographic service name must not contain any dots. There
     * must be one or more space charaters between the the <i>&lt;algorithm_or_type></i>
     * and the <i>&lt;attribute_name></i>.
     * <p> A provider satisfies this selection criterion iff the
     * provider implements the specified algorithm or type for the specified 
     * cryptographic service and its implementation meets the
     * constraint expressed by the specified attribute name/value pair.
     * <p> For example, "Signature.SHA1withDSA KeySize:1024" would be
     * satisfied by any provider that implemented
     * the SHA1withDSA signature algorithm with a keysize of 1024 (or larger).
     * NOTE: <B>java.security.Signature</B> is found in J2ME CDC profiles such as 
     * J2ME Foundation Profile.
     *  
     * </ul>
     *
     * <p> See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptogaphy Architecture API Specification &amp; Reference </a>
     * for information about standard cryptographic service names, standard
     * algorithm names and standard attribute names.
     *
     * @param filter the criterion for selecting
     * providers. The filter is case-insensitive.
     *
     * @return all the installed providers that satisfy the selection
     * criterion, or null if no such providers have been installed.
     *
     * @throws InvalidParameterException
     *         if the filter is not in the required format
     *
     * @see #getProviders(java.util.Map)
     */
    public static Provider[] getProviders(String filter) {
        return null;
    }

    /** 
     * Returns an array containing all installed providers that satisfy the specified
     * selection criteria, or null if no such providers have been installed. 
     * The returned providers are ordered
     * according to their <a href=
     * "#insertProviderAt(java.security.Provider, int)">preference order</a>. 
     * 
     * <p>The selection criteria are represented by a map.
     * Each map entry represents a selection criterion. 
     * A provider is selected iff it satisfies all selection
     * criteria. The key for any entry in such a map must be in one of the
     * following two formats:
     * <ul>
     * <li> <i>&lt;crypto_service>.&lt;algorithm_or_type></i>
     * <p> The cryptographic service name must not contain any dots.
     * <p> The value associated with the key must be an empty string.
     * <p> A provider
     * satisfies this selection criterion iff the provider implements the 
     * specified algorithm or type for the specified cryptographic service.
     * <li>  <i>&lt;crypto_service>.&lt;algorithm_or_type> &lt;attribute_name></i>
     * <p> The cryptographic service name must not contain any dots. There
     * must be one or more space charaters between the <i>&lt;algorithm_or_type></i>
     * and the <i>&lt;attribute_name></i>.
     * <p> The value associated with the key must be a non-empty string.
     * A provider satisfies this selection criterion iff the
     * provider implements the specified algorithm or type for the specified 
     * cryptographic service and its implementation meets the
     * constraint expressed by the specified attribute name/value pair. 
     * </ul>
     *
     * <p> See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptogaphy Architecture API Specification &amp; Reference </a>
     * for information about standard cryptographic service names, standard
     * algorithm names and standard attribute names.
     *
     * @param filter the criteria for selecting
     * providers. The filter is case-insensitive.
     *
     * @return all the installed providers that satisfy the selection
     * criteria, or null if no such providers have been installed. 
     *
     * @throws InvalidParameterException
     *         if the filter is not in the required format
     *
     * @see #getProviders(java.lang.String)
     */
    public static Provider[] getProviders(Map filter) {
        return null;
    }

    /** 
     * Gets a security property value.
     *
     * <p>First, if there is a security manager, its
     * <code>checkPermission</code>  method is called with a 
     * <code>java.security.SecurityPermission("getProperty."+key)</code>
     * permission to see if it's ok to retrieve the specified
     * security property value.. 
     *
     * @param key the key of the property being retrieved.
     *
     * @return the value of the security property corresponding to key.
     *
     * @throws  SecurityException
     *          if a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkPermission}</code> method
     *          denies
     *          access to retrieve the specified security property value
     * 
     * @see #setProperty
     * @see java.security.SecurityPermission
     */
    public static String getProperty(String key) {
        return null;
    }

    /** 
     * Sets a security property value.
     *
     * <p>First, if there is a security manager, its
     * <code>checkPermission</code> method is called with a 
     * <code>java.security.SecurityPermission("setProperty."+key)</code>
     * permission to see if it's ok to set the specified
     * security property value.
     *
     * @param key the name of the property to be set.
     *
     * @param datum the value of the property to be set.
     *
     * @throws  SecurityException
     *          if a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkPermission}</code> method
     *          denies access to set the specified security property value
     * 
     * @see #getProperty
     * @see java.security.SecurityPermission
     */
    public static void setProperty(String key, String datum) { }

    /** 
     * Returns a Set of Strings containing the names of all available
     * algorithms or types for the specified Java cryptographic service
     * (e.g., Signature, MessageDigest, Cipher, Mac, KeyStore). Returns
     * an empty Set if there is no provider that supports the  
     * specified service. For a complete list of Java cryptographic
     * services, please see the 
     * <a href="../../../guide/security/CryptoSpec.html">Java 
     * Cryptography Architecture API Specification &amp; Reference</a>.
     * Note: the returned set is immutable.
     *
     * @param serviceName the name of the Java cryptographic 
     * service (e.g., Signature, MessageDigest, Cipher, Mac, KeyStore).
     * Note: this parameter is case-insensitive.
     *
     * NOTE: <B>java.security.Signature, java.security.KeyStore</B> are found 
     * in J2ME CDC profiles such as J2ME Foundation Profile. <B> Cipher,
     * Mac </B> are found in J2ME CDC optional packages such as J2ME Security
     * Optional Package.
     *
     * @return a Set of Strings containing the names of all available 
     * algorithms or types for the specified Java cryptographic service
     * or an empty set if no provider supports the specified service.
     *
     * @since 1.4
     */
    public static Set getAlgorithms(String serviceName) {
        return null;
    }
}
