package org.ocap.diagnostics;

/**
 * This interface represents a response to an implementation request to an
 * application that has registered control over a specific MIB.
 */
public class SNMPResponse
{
    /**
     * The request completed successfully and the MIBObject contains valid
     * contents.
     */
    public final static int SNMP_REQUEST_SUCCESS = 0;

    /**
     * The size of the Response-PDU would be too large to transport.
     */
    public final static int SNMP_REQUEST_TOO_BIG = 1;

    /**
     * The OID could not be found or there is no OID to respond to in a
     * get next request.
     */
    public final static int SNMP_REQUEST_NO_SUCH_NAME = 2;

    /**
     * A Check/Set value (or syntax) error occurred.
     */
    public final static int SNMP_REQUEST_BAD_VALUE = 3;

    /**
     * An attempt was made to set a variable that 
     * has an access value of Read-Only
     */
    public final static int SNMP_REQUEST_READ_ONLY = 4;

    /**
     * Any error not covered by the other error types.
     */
    public final static int SNMP_REQUEST_GENERIC_ERROR = 5;

    /**
     * Access was denied to the object for security reasons.
     */
    public final static int SNMP_REQUEST_NO_ACCESS = 6;

    /**
     * The object type in the request is incorrect for the object.
     */
    public final static int SNMP_REQUEST_WRONG_TYPE = 7;

    /**
     * A variable binding specifies a length incorrect for the object.
     */
    public final static int SNMP_REQUEST_WRONG_LENGTH = 8;

    /**
     * A variable binding specifies an encoding incorrect for the object.
     */
    public final static int SNMP_REQUEST_WRONG_ENCODING = 9;

    /**
     * The value given in a variable binding is not possible for the object
     */
    public final static int SNMP_REQUEST_WRONG_VALUE = 10;

    /**
     * A specified variable does not exist and cannot be created.
     */
    public final static int SNMP_REQUEST_NO_CREATION = 11;

    /**
     * A variable binding specifies a value that could be held by 
     * the variable but cannot be assigned to it at this time.
     * (For example, is not CURRENTLY valid to set because of the
     * value of another MIB object, e.g. one MIB value indicates
     * if a clock display is 12 or 24 hours, and is set to 12, 
     * but then someone tries to set the time to 13:00)
     */
    public final static int SNMP_REQUEST_INCONSISTENT_VALUE = 12;

    /**
     * An attempt to set a variable required a resource 
     * that is not available.
     */
    public final static int SNMP_REQUEST_RESOURCE_UNAVAILABLE = 13;

    /**
     * An attempt to set a particular variable failed.
     */
    public final static int SNMP_REQUEST_COMMIT_FAILED = 14;

    /**
     * An attempt to set a particular variable as part of a group of 
     * variables failed, and the attempt to then undo the setting of 
     * other variables was not successful.
     */
    public final static int SNMP_REQUEST_UNDO_FAILED = 15;

    /**
     * A problem occurred in authorization.
     */
    public final static int SNMP_REQUEST_AUTHORIZATION_ERROR = 16;

    /**
     * The variable does not exist; the agent cannot create 
     * it because the named object instance is inconsistent
     * with the values of other managed objects.
     */
    public final static int SNMP_REQUEST_NOT_WRITABLE = 17;

    /**
     * The variable does not exist; the agent cannot create it 
     * because the named object instance is inconsistent with 
     * the values of other managed objects.
     */
    public final static int SNMP_REQUEST_INCONSISTENT_NAME = 18;


    /**
     * Constructs an SNMPResponse.
     * 
     * @param status Status of the corresponding request.  Possible
     *      values include any of the SNMP_REQUEST_* constants in
     *      this class.
     * @param object MIBObject resulting from the corresponding request.
     */
    public SNMPResponse(int status, MIBObject object)
    {
    }

    /**
     * Get the status of the response.
     * 
     * @return One of the request constants defined in this interface.
     */
    public int getStatus()
    {
        return 0;
    }

    /**
     * Gets the encoding of the MIB object associated with the OID in the
     * request that caused this response.
     * 
     * @return If the getStatus method returns SNMP_REQUEST_SUCCESS this
     *      method SHALL return a populated MIB Object, otherwise the MIB
     *      object returned SHALL contain an empty data array with length
     *      0.
     */
    public MIBObject getMIBObject()
    {
        return null;
    }
}
