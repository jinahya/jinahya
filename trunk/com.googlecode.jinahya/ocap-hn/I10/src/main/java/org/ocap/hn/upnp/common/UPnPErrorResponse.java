package org.ocap.hn.upnp.common;

/**
 * The class represents a response to an unsuccessfully 
 * completed UPnP action, where the server responded with HTTP
 * code 500 (Internal Server Error). It carries the UPnP
 * errorCode and errorDescription. Instances of this class are constructed by the
 * <code>UPnPActionHandler</code> on a UPnP server, and are 
 * passed to a client in the 
 * <code>UPnPActionResponseHandler</code>. 
 */
public class UPnPErrorResponse extends UPnPResponse
{
    /**
     * Construct the instance. 
     *  
     * @param errorCode The UPnP errorCode to be used for this error
     *                  response.
     * @param errorDescription The UPnP errorDescription to be used 
     *                         for this error response.
     * @param action The action invocation that this error response 
     *               relates to.
     */
    public UPnPErrorResponse(int errorCode, String errorDescription, UPnPActionInvocation action)
    {
    }

    /**
     * Get the error code. 
     *  
     * @return The error code for this error response. 
     */
    public int getErrorCode()
    {
       return 0;
    }

    /**
     * Get the error description. 
     *  
     * @return The error description for this error response. 
     */
    public String getErrorDescription()
    {
       return null;
    }

}
