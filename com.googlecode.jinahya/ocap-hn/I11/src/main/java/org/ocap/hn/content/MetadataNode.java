package org.ocap.hn.content;

import java.util.Enumeration;
import org.ocap.storage.ExtendedFileAccessPermissions;

/**
 * A collection of metadata entries, each of which is a key/value pair
 * where the key identifies a property and the value is the property value,
 * and a collection of supporting namespace declarations. Each property
 * may have an <code>ExtendedFileAccessPermissions</code> defining its
 * accessibility. Property values (also called metadata) can be
 * <code>MetadataNode</code>s; thus treelike metadata structures are
 * supported.
 *
 * <p>
 * One example is given below:
 <pre>
 RootNode
 |
 --- TITLE    - String("Best Movie Ever")
 |
 --- CREW     - MetadataNode
 |                  |
 |                  --- MAIN_ACTOR  - String("Joe Sixpack")
 |                  |
 |                  --- MAIN_ACTOR2 - String("Doris Dosenkohl")
 |
 --- SYNOPSIS - String("Don't know - I fell asleep after 5 seconds")
 </pre>
 * It is possible to get metadata from nested <code>MetadataNode</code>s
 * directly by concatenating the different keys using # to separate them.
 * In the above example, getMetadata("CREW#MAIN_ACTOR") would return
 * "Joe Sixpack".
 *
 * <p>
 * The <code>MetadataNode</code> represents the current snapshot of metadata
 * associated with a network entity as cached on the local device. This may or
 * may not reflect an accurate or complete view of the metadata that exists on
 * the network. It is the responsibility of the application to explicitly update
 * metadata using the home network APIs
 * (e.g. {@link org.ocap.hn.ContentServerNetModule#requestSearchEntries(String,
 * String, int, int, String, String, org.ocap.hn.NetActionHandler)}).
 */
public abstract class MetadataNode
{
    /**
     * Constructor; only to be invoked by subclass constructors.
     */
    protected MetadataNode()
    {
    }

    /**
     * Creates a <code>MetadataNode</code>.
     *
     * @return The newly created <code>MetadataNode</code>.
     */
    public static MetadataNode createMetadataNode()
    {
        return null;
    }

    /**
     * @deprecated Replaced by {@link #createMetadataNode()}.
     *
     * Creates a <code>MetadataNode</code>.
     *
     * @param key Unused. (In particular, it SHALL NOT be used
     *            to set the key string retrievable by the
     *            <code>getKey</code> method.)
     *
     * @return The newly created <code>MetadataNode</code>.
     */
    public static MetadataNode createMetadataNode(String key)
    {
        return null;
    }

    /**
     * Gets a reference to the value associated with the specified key,
     * if any.
     *
     * <p>
     * In order to query metadata in a hierarchy, query strings like
     * IDENT1#IDENT2#IDENT3 are possible, where IDENT1 maps to a
     * <code>MetadataNode</code> in this <code>MetadataNode</code>,
     * IDENT2 a <code>MetadataNode</code> in the IDENT1
     * <code>MetadataNode</code>, and IDENT3 an <code>Object</code>
     * in the IDENT2 <code>MetadataNode</code>.
     *
     * <p>
     * This method only returns locally cached metadata. This method SHALL NOT
     * cause network activity.
     *
     * @param key The key.
     *
     * @return A reference to the value associated with the key, or null if
     *         there is none.
     *
     * @throws SecurityException if the calling application does not have
     *      extended file access permission to read from the property
     *      identified by the key argument.
     */
    public abstract Object getMetadata(String key);

    /**
     * Adds a new metadata entry, modifies an existing metadata entry, or
     * removes an existing metadata entry.
     *
     * <p>
     * If the key argument does not contain an "@" character, the value
     * argument must be either null or a reference to an instance of one
     * of the following classes:
     * <ul>
     * <li><code>org.dvb.application.AppID</code>
     * <li><code>org.ocap.storage.ExtendedFileAccessPermissions</code>
     * <li>a class extending <code>org.ocap.hn.content.MetadataNode</code>
     * and not implementing <code>java.io.Serializable</code>
     * <li>a nonarray class implementing <code>java.io.Serializable</code>
     * <li><code>T[]</code>, where <code>T</code> is one of the above
     * </ul>
     *
     * <p>
     * If the key argument contains an "@" character but does not begin
     * with an "@" character, the value argument must be either null or
     * a reference to an instance of one of the following classes:
     * <ul>
     * <li><code>java.lang.String</code>
     * <li><code>java.lang.String[]</code>
     * </ul>
     *
     * <p>
     * If the key argument begins with an "@" character, the value argument
     * must be either null or a reference to an instance of the following
     * class:
     * <ul>
     * <li><code>java.lang.String</code>
     * </ul>
     *
     * <p>
     * If the value argument is null or a reference to a zero-length array,
     * any existing value associated with the key argument SHALL be removed.
     * Otherwise, if a value is already associated with the key argument,
     * the value argument's referent SHALL replace that value; if not, the
     * value argument's referent SHALL be associated with the key argument.
     *
     * <p>
     * Keys MAY contain a namespace prefix as part of their definition.
     * Namespace prefixes SHALL appear in keys as a colon separated prefix in
     * the key (e.g. <i>upnp:class</i>). Vendor-specific namespace prefixes
     * MUST be declared in this <code>MetadataNode</code> using the
     * <code>addNameSpace</code> method prior to usage in this method, or be a
     * valid namespace prefix in any containing parent
     * <code>MetadataNode</code>.
     *
     * <p>
     * Each property added by this method, whether new or pre-existing, SHALL be
     * given an <code>ExtendedFileAccessPermissions</code> that matches that of
     * the closest containing <code>ContentEntry</code>, if there is one.
     *
     * @see #addNameSpace(String namespace, String URI)
     *
     * @param key The key, e.g. "TITLE". When the value argument is a reference
     *      to an instance of a class extending <code>MetadataNode</code>, the
     *      implementation SHALL set that <code>MetadataNode</code>'s key string
     *      retrievable by the <code>getKey</code> method to this key.
     * @param value Null or a reference to the value to associate with the key.
     *
     * @throws IllegalArgumentException if the key argument contains an
     *      undeclared namespace prefix or a "#" character, or if the value
     *      argument is neither null nor a reference to an instance of an
     *      acceptable class, or if the value argument is a reference to an
     *      instance of a class that implements
     *      <code>java.io.Serializable</code> but that instance cannot be
     *      successfully serialized.
     * @throws SecurityException if the property identified by the key
     *      argument does not exist and the calling application does not have
     *      sufficient file access permissions to write to the
     *      <code>ContentEntry</code> containing this <code>MetadataNode</code>,
     *      or if the property identified by the key argument does exist and the
     *      calling application does not have sufficient file access permissions
     *      to write to the property.
     */
    public abstract void addMetadata(String key, Object value);

    /**
     * Adds a new metadata entry, modifies an existing metadata entry, or
     * removes an existing metadata entry, with specification of
     * <code>ExtendedFileAccessPermissions</code>.
     *
     * <p>
     * If the key argument does not contain an "@" character, the value
     * argument must be either null or a reference to an instance of one
     * of the following classes:
     * <ul>
     * <li><code>org.dvb.application.AppID</code>
     * <li><code>org.ocap.storage.ExtendedFileAccessPermissions</code>
     * <li>a class extending <code>org.ocap.hn.content.MetadataNode</code>
     * and not implementing <code>java.io.Serializable</code>
     * <li>a nonarray class implementing <code>java.io.Serializable</code>
     * <li><code>T[]</code>, where <code>T</code> is one of the above
     * </ul>
     *
     * <p>
     * If the key argument contains an "@" character but does not begin
     * with an "@" character, the value argument must be either null or
     * a reference to an instance of one of the following classes:
     * <ul>
     * <li><code>java.lang.String</code>
     * <li><code>java.lang.String[]</code>
     * </ul>
     *
     * <p>
     * If the key argument begins with an "@" character, the value argument
     * must be either null or a reference to an instance of the following
     * class:
     * <ul>
     * <li><code>java.lang.String</code>
     * </ul>
     *
     * <p>
     * If the value argument is null or a reference to a zero-length array,
     * any existing value associated with the key argument SHALL be removed.
     * Otherwise, if a value is already associated with the key argument,
     * the value argument's referent SHALL replace that value; if not, the
     * value argument's referent SHALL be associated with the key argument.
     *
     * <p>
     * Keys MAY contain a namespace prefix as part of their definition.
     * Namespace prefixes SHALL appear in keys as a colon separated prefix in
     * the key (e.g. <i>upnp:class</i>). Vendor-specific namespace prefixes
     * MUST be declared in this <code>MetadataNode</code> using the
     * <code>addNameSpace</code> method prior to usage in this method, or be a
     * valid namespace prefix in any containing parent
     * <code>MetadataNode</code>.
     *
     * <p>
     * Each property added by this method, whether new or pre-existing, SHALL be
     * given an <code>ExtendedFileAccessPermissions</code> that matches the
     * efap argument.
     *
     * @see #addNameSpace(String namespace, String URI)
     *
     * @param key The key, e.g. "TITLE". When the value argument is a reference
     *      to an instance of a class extending <code>MetadataNode</code>, the
     *      implementation SHALL set that <code>MetadataNode</code>'s key string
     *      retrievable by the <code>getKey</code> method to this key.
     * @param value Null or a reference to the value to associate with the key.
     * @param efap The <code>ExtendedFileAccessPermissions</code> for the
     *      property added or modified by this method.
     *
     * @throws IllegalArgumentException if the key argument contains an
     *      undeclared namespace prefix or a "#" character, or if the value
     *      argument is neither null nor a reference to an instance of an
     *      acceptable class, or if the value argument is a reference to an
     *      instance of a class that implements
     *      <code>java.io.Serializable</code> but that instance cannot be
     *      successfully serialized, or if the efap argument is null.
     * @throws SecurityException if the property identified by the key
     *      argument does not exist and the calling application does not have
     *      sufficient file access permissions to write to the
     *      <code>ContentEntry</code> containing this <code>MetadataNode</code>,
     *      or if the property identified by the key argument does exist and the
     *      calling application does not have sufficient file access permissions
     *      to write to the property.
     */
    public abstract void addMetadata(String key, Object value,
                                        ExtendedFileAccessPermissions efap);

    /**
     * Adds a namespace declaration to this <code>MetadataNode</code>. Once a
     * namespace is declared, its prefix SHALL be valid as a metadata key
     * qualifier for this <code>MetadataNode</code> and any of its descendants.
     *
     * @param namespace The namespace prefix (e.g. "av").
     * @param URI       The namespace name (e.g. "urn:schemas-upnp-org:av:av").
     */
    public abstract void addNameSpace(String namespace, String URI);

    /**
     * Gets an <code>Enumeration</code> of all top-level metadata in this
     * <code>MetadataNode</code>.
     *
     * <p>
     * This method only returns an <code>Enumeration</code> of locally cached
     * metadata. This method SHALL NOT cause network activity.
     *
     * @return An <code>Enumeration</code> of all top-level metadata in this
     *         <code>MetadataNode</code>.
     */
    public abstract Enumeration getMetadata();

    /**
     * Gets the parent <code>MetadataNode</code> of this
     * <code>MetadataNode</code>.
     *
     * @return The parent <code>MetadataNode</code> of this
     *         <code>MetadataNode</code>, or null if there is none.
     */
    public abstract MetadataNode getParentNode();

    /**
     * Gets the key string, which can be utilized to retrieve this
     * <code>MetadataNode</code> from its parent. If this
     * <code>MetadataNode</code> has no parent, this method SHALL return null.
     *
     * @return The key string associated with this <code>MetadataNode</code>.
     */
    public abstract String getKey();

    /**
     * Gets the keys for all top-level metadata in this
     * <code>MetadataNode</code>.
     *
     * <p>
     * This method only returns the keys for locally cached
     * metadata. This method SHALL NOT cause network activity.
     *
     * @return An array of <code>String</code>s that are the keys for all
     *         top-level metadata in this <code>MetadataNode</code>.
     */
    public abstract String[] getKeys();

    /**
     * Gets the <code>ExtendedFileAccessPermissions</code> for the property
     * identified by the specified key.
     *
     * <p>
     * In order to query metadata in a hierarchy, query strings like
     * IDENT1#IDENT2#IDENT3 are possible, where IDENT1 maps to a
     * <code>MetadataNode</code> in this <code>MetadataNode</code>,
     * IDENT2 a <code>MetadataNode</code> in the IDENT1
     * <code>MetadataNode</code>, and IDENT3 an <code>Object</code>
     * in the IDENT2 <code>MetadataNode</code>.
     *
     * @param key The key identifying the property to get the
     *      <code>ExtendedFileAccessPermissions</code> for.
     *
     * @return The <code>ExtendedFileAccessPermissions</code> of the property
     *         identified by the key, or null if there is no such property or if
     *         the property has no <code>ExtendedFileAccessPermissions</code>.
     */
    public abstract ExtendedFileAccessPermissions
        getExtendedFileAccessPermissions(String key);
}
