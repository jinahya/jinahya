package org.ocap.diagnostics;

/**
 * This interface represents a MIB object that exposes its data type.  See RFC
 * 2578 for data type definition.
 */
public interface MIBDefinition
{
    /**
     * Unrecognized type encountered.  Not defined by RFC 2578.
     */
    final static int SNMP_TYPE_INVALID = 0;

    /**
     * Base type, built-in ASN.1 integer type.
     */
    final static int SNMP_TYPE_INTEGER = 0x02;

    /**
     * The BITS construct.
     */
    final static int SNMP_TYPE_BITS = 0x03;

    /**
     * Base type, built-in ASN.1 string type.
     */
    final static int SNMP_TYPE_OCTETSTRING = 0x04;

    /**
     * Base type, built-in ASN.1 OBJECT IDENTIFIER type.
     */
    final static int SNMP_TYPE_OBJECTID = 0x06;

    /**
     * Base type, application defined IP address.
     */
    final static int SNMP_TYPE_IPADDRESS = 0x40;

    /**
     * Base type, application defined 32 bit counter.
     */
    final static int SNMP_TYPE_COUNTER32 = 0x41;

    /**
     * Base type, application defined 32 bit gauge.
     */
    final static int SNMP_TYPE_GAUGE32 = 0x42;

    /**
     * Base type, built-in ASN.1 Unsigned32 - non-negative 32-bit values. 
     */
    final static int SNMP_TYPE_UNSIGNED32 = 0x42;

    /**
     * Base type, application defined time ticks.
     */
    final static int SNMP_TYPE_TIMETICKS = 0x43;

    /**
     * Base type, application defined opaque variable.
     */
    final static int SNMP_TYPE_OPAQUE = 0x44;

    /**
     * Base type, application defined 64 bit counter.
     */
    final static int SNMP_TYPE_COUNTER64 = 0x46;
    
    
    /**
     * Gets the SNMP data type of the MIB.
     * 
     * @return An SNMP data type defined by constants in this
     *      interface.
     */
    public int getDataType();
    
    /**
     * Gets the MIB object associated with this MIB definition.
     * 
     * @return The MIB Object for this MIB definition.
     */
    public MIBObject getMIBObject();

}
