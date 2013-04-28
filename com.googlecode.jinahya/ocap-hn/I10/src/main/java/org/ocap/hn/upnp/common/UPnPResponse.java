package org.ocap.hn.upnp.common;


/**
 * This class represents a response to a UPnP action. 
 */
public abstract class UPnPResponse
{
    /**
     * Protected constructor for the instance.
     */
    protected UPnPResponse()
    {

    }

    /**
     * Gets the HTTP response code from the response.
     * 
     * @return  The HTTP response code associated with the 
     *          response, such as 200 (OK) or 500 (Internal
     *          Server Error).
     */
    public int getHTTPResponseCode()
    {
        return 0;
    }

    /**
     * Gets the {@code UPnPActionInvocation} that the response is for.
     * 
     * @return  The UPnP action invocation that prompted this response.
     */
    public UPnPActionInvocation getActionInvocation()
    {
        return null;
    }

}
