package org.ocap.diagnostics;

/**
 * The interface represents a MIB Object.  It contains the oid, as well as the
 * encoding of the object with value formats corresponding to the ASN.1
 * definition of the object.
 */
public class MIBObject 
{

    /**
     * Constructs a MIB object.
     * 
     * @param oid Object Identifier of the MIB object.
     * @param data Array of bytes representing the MIB encoding.
     */
    public MIBObject(String oid, byte [] data)
    {
    }

    /**
     * Gets the MIB object identifier.
     * 
     * @return Object identifier of this MIB object.  The object ID SHALL
     *      be formatted as per RFC 1778 section 2.15.
     */
    public String getOID()
    {
        return null;
    }

    /**
     * Gets the current MIB object encoding in byte array form.  The
     * array is formatted according to the ASN.1 format of the MIB.
     * 
     * @return A byte array representing the MIB object encoding.
     */
    public byte [] getData()
    {
        return null;
    }
}

