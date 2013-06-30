package org.ocap.hn.upnp.common;

/**
 * The class represents a response to an unsuccessfully 
 * completed UPnP action, where the server either did not respond, 
 * or responded with an HTTP error code other than 
 * 500 (Internal Server Error). Instances of this class are constructed 
 * by the stack by the UPnPActionHandler, and are passed to a 
 * client in the 
 * <code>UPnPActionResponseHandler</code>. 
 */
public class UPnPGeneralErrorResponse extends UPnPResponse
{

    /**
     * Indicates that the server did not respond.    
     */ 
    public static final int NETWORK_TIMEOUT = 1;

    /**
     * Indicates that the action was not able to be invoked due to a
     * network error (could not reach server, or other non-timeout 
     * network error).
     */ 
    public static final int NETWORK_ERROR = 2;


   /**
    * Construct the instance. 
    *  
    * @param errorCode The error code that this general error 
    *                  response is to contain.
    * @param action The <code>UPnPActionInvocation</code> that this 
    *               general error response is in response to.
    */
   public UPnPGeneralErrorResponse(int errorCode, UPnPActionInvocation action)
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

}
