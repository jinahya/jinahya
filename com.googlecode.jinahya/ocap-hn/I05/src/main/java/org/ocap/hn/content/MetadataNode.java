/*
 * Created on 29-Sep-2004
 *
 */
package org.ocap.hn.content;

import java.util.Enumeration;
import org.ocap.storage.ExtendedFileAccessPermissions;

/**
 * Base class for all Metadata. Metadata can be a normal java.lang.String,
 * any serializable Object, another MetadataNode for tree like meta data structures
 * or other Objects declared as supported by this specification. One example is 
 * given below:
 <pre>
 RootNode
 |
 --- TITLE - String("Best Movie Ever")
 |
 --- CREW  - MetadataNode
                 |
                 --- MAIN_ACTOR - String("Joe Sixpack")
                 |
                 --- MAIN_ACTOR2 - String("Doris Dosenkohl")
 |
 --- SYNOPSIS - String("Don't know - I fell asleep after 5 seconds")
  </pre>
 * It is possible to get Metadata from other MetadataNodes directly by
 * concatenating the different identifiers using # to separate them.<br>
 * e.g. getMetadata("CREW#MAIN_ACTOR") would return "Joe Sixpack"
 * 
 * <p>
 * The MetadataNode represents the current snapshot of metadata associated
 * with a network entity as cached on the local device. This may or may not
 * reflect an accurate or complete view of the metadata that exists on the 
 * network. It is the responsibility of the application to explicitly update
 * metadata using the home network APIs 
 * (e.g. {@link org.ocap.hn.ContentServerNetModule#requestSearchEntries(String, 
 *             String, int, int, String, String, org.ocap.hn.NetActionHandler)} 
 */
public abstract class MetadataNode extends Object
{
    /**
     * Creates an instance of a MetadataNode object. When added to
     * another MetadataNode, this node can be located using the key
     * specified in the constructor.
     * 
     * @param key the key for locating this MetadataNode
     * 
     * @return the newly created MetadataNode
     */
    public static MetadataNode createMetadataNode(String key)
    {
        return null;
    }
    
    /**
     * Returns the Metadata for the specified key. The returned object
     * can be a normal String (e.g. for title etc), a java.awt.Image, a Vector
     * containing a list of elements, another MetadataNode for tree like
     * metadata structures or other tbd. Objects.  In order to query a Metadata
     * in a hierarchy query strings like<br>IDENT1#IDENT2#IDENT3 are possible
     * where IDENT1 is a MetadataNode in this node, IDENT2 a MetadataNode in 
     * IDENT2 and IDENT3 an object in IDENT2.  Performs a local cache search
     * only.  
     * 
     * This method only returns locally cached metadata. This method SHALL not
     * cause network activity.
     *  
     * @param key The key to search for.
     * 
     * @return The value associated with the key or null if no match found.
     *
     * @throws SecurityException if the calling application does not have
     *      extended file access permission to read from the property
     *      represented by the key parameter.
     */
    public abstract Object getMetadata(String key);

    /**
     * Adds a new metadata entry to this MetadataNode or modifies an existing
     * metadata entry in this MetadataNode.
     * The value Object to be added to this metadata node MUST be of a type for 
     * which a known network mapping exists. All objects implementing the 
     * Serializable interface are considered to have a known network mapping. In 
     * addition, the following classes have a known network mapping:<br>
     * org.dvb.application.AppID <br>
     * org.ocap.storage.ExtendedFileAccessPermissions <br>
     * org.ocap.hn.content.ContentContainer <br>
     * org.ocap.hn.content.MetadataNode <br><br>
     * 
     * If the Object to be added is an instance of MetadataNode, all Objects  
     * contained within that MetadataNode SHALL be recursively added to this 
     * MetadataNode as a result of this operation. <br><br> Invocation of
     * this method SHALL replace any values already present for the given
     * <i>key</i> paramaeter.
     * 
     * If a value Object is already associated with the key string passed into this
     * method, the value passed in by this method SHALL replace the existing value.
     * If a value of null is passed into this method, any existing value assocaited
     * with the key passed into this method SHALL be removed.
     * 
     * Keys MAY contain a namespace as part of their definition. Namespaces SHALL 
     * appear in keys as a colon separated prefix in the key string (e.g. 
     * <i>"namespace:key"</i>). Vendor specific namespaces MUST be added 
     * to this MetadataNode using the addNameSpace() method prior to usage
     * in this method, or be a valid namespace in any containing parent 
     * MetadataNode.
     *
     * Each property added by this method whether new or pre-existing SHALL be
     * given an <code>ExtendedFileAccessPermissions</code> that matches that of
     * the parent <code>ContentEntry</code>.
     * 
     * @see #addNameSpace(String namespace, String URI)
     * 
     * @param key The key e.g. "TITLE".  When the value type is
     *      <code>MetadataNode</code> the implementation SHALL over-write the
     *      <code>key</code> set by the <code>createMetadataNode</code> method
     *      with the value of this parameter.
     * @param value The value associated with the key, e.g.
     *      "When Harry Meets Sally" or a more complex Object like another
     *      MetadataNode.
     *      
     * @throws IllegalArgumentException if value is not a known mappable object
     *      type, or if the key parameter contains an unknown vendor defined
     *      namespace .
     * @throws SecurityException if the property identified by the key
     *      parameter does not exist and the calling application does not have
     *      sufficient file access permissions to write to the
     *      <code>ContentEntry</code> containing this <code>MetadataNode</code>.
     * @throws SecurityException if the property identified by the key
     *      parameter does exist and the calling application does not have
     *      sufficient file access permissions to write to the
     *      the property.
     */
    public abstract void addMetadata(String key, Object value);


    /**
     * Adds a new metadata entry to this MetadataNode or modifies an existing
     * metadata entry in this MetadataNode.
     * The value Object to be added to this metadata node MUST be of a type for
     * which a known network mapping exists. All objects implementing the
     * Serializable interface are considered to have a known network mapping. In
     * addition, the following classes have a known network mapping:<br>
     * org.dvb.application.AppID <br>
     * org.ocap.storage.ExtendedFileAccessPermissions <br>
     * org.ocap.hn.content.ContentContainer <br>
     * org.ocap.hn.content.MetadataNode <br><br>
     *
     * If the Object to be added is an instance of MetadataNode, all Objects
     * contained within that MetadataNode SHALL be recursively added to this
     * MetadataNode as a result of this operation. <br><br> Invocation of
     * this method SHALL replace any values already present for the given
     * <i>key</i> paramaeter.
     *
     * If a value Object is already associated with the key string passed into this
     * method, the value passed in by this method SHALL replace the existing value.
     * If a value of null is passed into this method, any existing value assocaited
     * with the key passed into this method SHALL be removed.
     *
     * Keys MAY contain a namespace as part of their definition. Namespaces SHALL
     * appear in keys as a colon separated prefix in the key string (e.g.
     * <i>"namespace:key"</i>). Vendor specific namespaces MUST be added
     * to this MetadataNode using the addNameSpace() method prior to usage
     * in this method, or be a valid namespace in any containing parent
     * MetadataNode.
     *
     * Each property added by this method whether new or pre-existing SHALL be
     * given an <code>ExtendedFileAccessPermissions</code> that matches the
     * efap parameter.
     *
     * @see #addNameSpace(String namespace, String URI)
     *
     * @param key The key e.g. "TITLE".  When the value type is
     *      <code>MetadataNode</code> the implementation SHALL over-write the
     *      <code>key</code> set by the <code>createMetadataNode</code> method
     *      with the value of this parameter.
     * @param value The value associated with the key, e.g.
     *      "When Harry Meets Sally" or a more complex Object like another
     *      MetadataNode.
     * @param efap ExtendedFileAccessPermissions for the property or
     *      properties added or modified by this method.
     *
     * @throws IllegalArgumentException if value is not a known mappable object
     *      type, or if the key parameter contains an unknown vendor defined
     *      namespace, or if the efap parameter is null.
     * @throws SecurityException if the property identified by the key
     *      parameter does not exist and the calling application does not have
     *      sufficient file access permissions to write to the
     *      <code>ContentEntry</code> containing this <code>MetadataNode</code>.
     * @throws SecurityException if the property identified by the key
     *      parameter does exist and the calling application does not have
     *      sufficient file access permissions to write to the
     *      the property.
     */
    public abstract void addMetadata(String key, Object value,
                                        ExtendedFileAccessPermissions efap);

    /**
     * Adds a namespace definition to this MetadataNode. Once added, this 
     * namespace SHALL be valid as a metadata key qualifier for this node 
     * and any children and sub-children of this MetadataNode.
     * 
     * @see #addMetadata(String key,Object value)
     * 
     * @param namespace String identifier of the namespace to be added
     * @param URI vendor specific String based universal resource indicator 
     *                  pointing to the definition of this namespace.
     */
    public abstract void addNameSpace(String namespace, String URI);
    
    /**
     * Gets an Enumeration of all metadata objects in this node. The 
     * enumeration SHALL contain the top-level MetadataNode objects which
     * MAY contain other MetadataNode objects.
     * 
     * This method only returns locally cached metadata. This method SHALL not
     * cause network activity.
     * 
     * @return Enumeration of top-level MetadataNode objects.
     */
    public abstract Enumeration getMetadata();

    /**
     * Gets the parent node of this MetadataNode.
     * 
     * @return Parent of this node.
     */
    public abstract MetadataNode getParentNode();
    
    /**
     * Gets the key string which can be utilized to retrieve this MetadataNode
     * from this node's parent. If this MetadataNode is a root MetadataNode,
     * the method SHALL return null.
     * 
     * @return the key string associated with this MetadataNode
     *

     */
    public abstract String getKey();
    
    /**
     * Gets the set of keys for all metadata contained within this
     * MetadataNode.
     * 
     * @return an array of Strings representing metadata contained within
     *         this MetadataNode
     */
    public abstract String[] getKeys();

    /**
     * Gets the extended file access permissions for a property.
     *
     * @param key The key value representing the property to get the
     *      permissions for.
     *
     * @return The extended file access permissions of the property or null
     *      if the key does not match a known property.
     */
    public abstract ExtendedFileAccessPermissions getExtendedFileAccessPermissions(
                                            String key);
}
