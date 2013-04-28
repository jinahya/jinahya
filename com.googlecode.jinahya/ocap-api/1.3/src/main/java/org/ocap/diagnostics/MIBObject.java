package org.ocap.diagnostics;

/**
 * This interface represents a MIB Object.
 */
public class MIBObject 
{

    /**
     * Constructs a MIB object.
     * 
     * @param oid Object Identifier of the MIB object.
     * @param data Array of bytes representing the MIB object data,
     * in ASN.1 format with BER encoding.
     */
    public MIBObject(String oid, byte[] data)
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
     * Returns the current MIB object data, in ASN.1 format with BER encoding.
     * 
     * @return A byte array containing the ASN.1 formatted MIB object data.
     */
    public byte[] getData()
    {
        return null;
    }

   /**
    * Returns the unencoded value of the current MIB object data.  The 
    * returned array is the value field of the BER type-length-value encoding
    * provided by {@link #getData()}.
    *
    * @return A byte array representing the unencoded value of the MIB object data,
    * or {@code null} if the data cannot be unencoded.
    *
    * @see #getData
    */
   public byte[] getValue() 
   { 
       return null; 
   }
}
