package org.ocap.diagnostics;

/**
 * The MIBManager class provides Management Information Base (MIB)
 * access to the host's MIB.  Applications may use the MIBManager 
 * to make MIB queries.  In addition, applications can set a MIB Object
 * that can be retrieved from the Host device using SNMP.
 */
public abstract class MIBManager
{
    /**
     * Constant used to specify read-only access for the MIB being registered.
     * @see #registerOID
     */
    public final static int MIB_ACCESS_READONLY = 0;

    /**
     * Constant used to specify read/write access for the MIB being registered.
     * @see #registerOID
     */
    public final static int MIB_ACCESS_READWRITE = 1;

    /**
     * Constant used to specify write-only access for the MIB being registered.
     * @see #registerOID
     */
    public final static int MIB_ACCESS_WRITEONLY = 2;

    /**
     * Protected constructor, no application access.
     */
    protected MIBManager()
    {
    }

    /**
     * Gets the MIBManager.
     * 
     * @return The MIBManager.
     * 
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("diagnostics"). 
     */
    public static MIBManager getInstance()
    {
        return null;
    }

    /**
     * Registers a MIB object by adding the OID and listener to the MIB tables.  
     * OID must be unique and not pre-existing.
     * OID must include a trailing ".0" for leaf items or but not include the instance ID
     * for table column items.  (The same listener may be installed to will be invoked
     * for all row instances for table column items. any number of OIDs, if desired.)
     * Table OIDs may be the OID of the start of the table or a column 
     * within the table, depending upon whether the implementation desires
     * handling all table values with a single listener, or have different
     * listeners for each column.  
     * 
     * @param oid The Object Identifier of the MIB being registered.  The
     *      format of the string is based on the format defined by RFC 2578 for
     *      OBJECT IDENTIFIER definition.  Terms in the string are period
     *      delimited, e.g. "1.3.6.1.4.1".
     * @param leaf When true indicates if the object is a leaf, otherwise the
     *      object is a non-leaf table column.
     * @param access Indicates allowed access to the MIB being registered.  See
     *      access constants in the class for valid values.
     * @param dataType The data type of the MIB being registered.  See constants
     *      in {@link MIBDefinition} for valid values.
     * @param listener Listener to the MIB being registered.  Must not be null.
     * 
     * @throws IllegalArgumentException if oid is an invalid oid string, 
     *      if oid is already installed, or any other parameter has an 
     *      invalid value.
     */
    public abstract void registerOID(String oid,
                                     int access,
                                     boolean leaf,
                                     int dataType,
                                     MIBListener listener);
                   
    /**
     * Unregisters a previously registered OID if the OID was
     * registered by the same application. 
     * 
     * @param oid An object identifier that was passed to the registerOID
     *      method.
     * 
     * @throws IllegalArgumentException if parameter does not match an OID
     *      registered with the registerOID method.
     */ 
    public abstract void unregisterOID(String oid);


    /**
     * Makes a query for all MIB objects matching the oid parameter, as
     * well as any descendants in the MIB tree.  If the object to be searched
     * for is a leaf the trailing ".0" must be included for an exact match.
     * A query for a leaf object SHALL return just that object if found.  A
     * query for a non-leaf OID SHALL return all MIB objects below that OID.
     * Existing leaf and table items SHALL be included in the results;
     * branch-nodes without data SHALL NOT.  For example; If a query is for
     * OID 1.2.3.4 then all table items and leafs below that OID are returned.
     * If OIDs 1.2.3.4.1 and 1.2.3.4.2 are the only items below the query object
     * they would be returned.  The query SHALL NOT return items outside the
     * OID.  For example; if 1.2.3.4 is the query OID then 1.2.3.5 is not
     * returned. </p><p>
     *
     * </p><p>
     * When both the Host device and CableCARD support the CARD MIB Access
     * resource and the oid parameter is
     * equal to the OID or within the subtree of the OID returned by the 
     * get_rootOID_req APDU, then the implementation SHALL use the
     * snmp_request APDU in order to satisfy the query.
     * </p>
     *
     * 
     * If the CableCARD supports MIB APDUs, then this method SHALL be able to
     * query the CableCARD MIB objects. </p>
     *
     * 
     * @param oid The object identifier to search for.  The format of the
     *      string is based on the format defined by RFC 2578 for
     *      OBJECT IDENTIFIER definition.  Terms in the string
     *      are period delimited, e.g. "1.3.6.1.4.1".
     * 
     * @return An array of MIB definitions.  The array is lexographically ordered
     *      by increasing value of OID with the lowest value in the first element
     *      of the array.
     */ 
    public abstract MIBDefinition [] queryMibs(String oid);

    /**
     * Sets a Host MIB object.  The OID in the MIBObject parameter must reference
     * a writable MIB object supported by the implementation. </p><p>
     * 
     * Note: CableCARD MIBs cannot be set using this method. </p>
     * 
     * @param mibToSet The MIB to set.  The OID in the MIBObject SHALL be used to
     *      replace values with the data array in the parameter.
     * 
     * @throws IllegalArgumentException if the OID in the MIBObject parameter does
     *      not reference a supported Host MIB or if the data attribute in the MIBObject
     *      parameter is not in the correct ASN.1 form for the OID.
     * @throws SecurityException if the MIB is not writable or if the calling
     *      application does not have MonitorAppPermission("diagnostics").
     */
    public abstract void setMIBObject(MIBObject mibToSet);
}
