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

/** 
 * This class represents a "provider" for the
 * Java Security API, where a provider implements some or all parts of
 * Java Security. Services that a provider may implement include:
 *
 * <ul>
 *
 * <li>Algorithms (such as DSA, RSA, MD5 or SHA-1).
 *
 * <li>Key generation, conversion, and management facilities (such as for
 * algorithm-specific keys).
 *
 *</ul>
 *
 * <p>Each provider has a name and a version number, and is configured
 * in each runtime it is installed in.
 *
 * <p>See <a href =
 * "../../../guide/security/CryptoSpec.html#Provider">The Provider Class</a>
 * in the "Java Cryptography Architecture API Specification &amp; Reference"
 * for information about how a particular type of provider, the
 * cryptographic service provider, works and is installed. However,
 * please note that a provider can be used to implement any security
 * service in Java that uses a pluggable architecture with a choice
 * of implementations that fit underneath. 
 *
 * @version 1.48, 02/02/00
 * @author Benjamin Renaud
 */
public abstract class Provider extends Properties
{
    /** 
     * The provider name.
     *
     * @serial
     */
    private String name;

    /** 
     * A description of the provider and its services.
     *
     * @serial
     */
    private String info;

    /** 
     * The provider version number.
     *
     * @serial
     */
    private double version;

    /** 
     * Constructs a provider with the specified name, version number,
     * and information.
     *
     * @param name the provider name.
     *
     * @param version the provider version number.
     *
     * @param info a description of the provider and its services.
     */
    protected Provider(String name, double version, String info) { }

    /** 
     * Returns the name of this provider.
     *
     * @return the name of this provider.
     */
    public String getName() {
        return null;
    }

    /** 
     * Returns the version number for this provider.
     *
     * @return the version number for this provider.
     */
    public double getVersion() {
        return 0.0d;
    }

    /** 
     * Returns a human-readable description of the provider and its
     * services.  This may return an HTML page, with relevant links.
     *
     * @return a description of the provider and its services.
     */
    public String getInfo() {
        return null;
    }

    /** 
     * Returns a string with the name and the version number
     * of this provider.
     *
     * @return the string with the name and the version number
     * for this provider.
     */
    public String toString() {
        return null;
    }

    /** 
     * Clears this provider so that it no longer contains the properties
     * used to look up facilities implemented by the provider.
     * 
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with the string <code>"clearProviderProperties."+name</code>
     * (where <code>name</code> is the provider name) to see if it's ok to clear this provider.
     * If the default implementation of <code>checkSecurityAccess</code> 
     * is used (that is, that method is not overriden), then this results in
     * a call to the security manager's <code>checkPermission</code> method with a
     * <code>SecurityPermission("clearProviderProperties."+name)</code>
     * permission.
     *
     * @throws  SecurityException
     *          if a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkSecurityAccess}</code> method denies
     *          access to clear this provider
     *
     * @since 1.2
     */
    public synchronized void clear() { }

    /** 
     * Reads a property list (key and element pairs) from the input stream.
     *
     * @param inStream   the input stream.
     * @exception  IOException  if an error occurred when reading from the
     *               input stream.
     * @see java.util.Properties#load
     */
    public synchronized void load(InputStream inStream) throws IOException { }

    /** 
     * Copies all of the mappings from the specified Map to this provider.
     * These mappings will replace any properties that this provider had 
     * for any of the keys currently in the specified Map. 
     *
     * @since 1.2
     */
    public synchronized void putAll(Map t) { }

    /** 
     * Returns an unmodifiable Set view of the property entries contained 
     * in this Provider.
     *
     * @see   java.util.Map.Entry
     * @since 1.2
     */
    public synchronized Set entrySet() {
        return null;
    }

    /** 
     * Returns an unmodifiable Set view of the property keys contained in 
     * this provider.
     *
     * @since 1.2
     */
    public Set keySet() {
        return null;
    }

    /** 
     * Returns an unmodifiable Collection view of the property values 
     * contained in this provider.
     *
     * @since 1.2
     */
    public Collection values() {
        return null;
    }

    /** 
     * Sets the <code>key</code> property to have the specified
     * <code>value</code>.
     * 
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with the string <code>"putProviderProperty."+name</code>,
     * where <code>name</code> is the provider name,
     * to see if it's ok to set this provider's property values.
     * If the default implementation of <code>checkSecurityAccess</code> 
     * is used (that is, that method is not overriden), then this results in
     * a call to the security manager's <code>checkPermission</code> method with a
     * <code>SecurityPermission("putProviderProperty."+name)</code>
     * permission.
     *
     * @param key the property key.
     *
     * @param value the property value.
     *
     * @return the previous value of the specified property
     * (<code>key</code>), or null if it did not have one.
     *
     * @throws  SecurityException
     *          if a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkSecurityAccess}</code> method denies
     *          access to set property values.
     *
     * @since 1.2
     */
    public synchronized Object put(Object key, Object value) {
        return null;
    }

    /** 
     * Removes the <code>key</code> property (and its corresponding
     * <code>value</code>).
     * 
     * <p>First, if there is a security manager, its <code>checkSecurityAccess</code> 
     * method is called with the string <code>""removeProviderProperty."+name</code>,
     * where <code>name</code> is the provider name,
     * to see if it's ok to remove this provider's properties. 
     * If the default implementation of <code>checkSecurityAccess</code> 
     * is used (that is, that method is not overriden), then this results in
     * a call to the security manager's <code>checkPermission</code> method with a
     * <code>SecurityPermission("removeProviderProperty."+name)</code>
     * permission.
     *
     * @param key the key for the property to be removed.
     *
     * @return the value to which the key had been mapped,
     * or null if the key did not have a mapping.
     *
     * @throws  SecurityException
     *          if a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkSecurityAccess}</code> method denies
     *          access to remove this provider's properties.
     *
     * @since 1.2
     */
    public synchronized Object remove(Object key) {
        return null;
    }

    // Declare serialVersionUID to be compatible with JDK1.1
    static final long serialVersionUID = -4298000515446427739L;

}
