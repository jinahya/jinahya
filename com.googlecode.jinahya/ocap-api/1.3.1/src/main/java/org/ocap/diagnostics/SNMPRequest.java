package org.ocap.diagnostics;

/**
 * This interface represents an application request for SNMP check, get,
 * or set of a specific MIB.
 */
public interface SNMPRequest
{
    /**
     * Used to validate a MIB value before doing a set.
     */
    final static int SNMP_CHECK_FOR_SET_REQUEST = 0;

    /**
     * Set (modify) the value in the MIB for an OID.
     */
    final static int SNMP_SET_REQUEST = 1;

    /**
     * Get data for the exact OID passed in.
     */
    final static int SNMP_GET_REQUEST = 2;
       
    /**
     * Get data for the next OID beyond the one passed in.
     */
    final static int SNMP_GET_NEXT_REQUEST=4;
 

    /**
     * Gets the type for this request.
     * 
     * @return One of the request types defined in this interface.
     */
    public int getRequestType();
    
    /**
     * Gets the <code>MIBObject</code> for the request.
     * 
     * @return <code>MIBObject</code> for the request.
     */
    public MIBObject getMIBObject();
}

