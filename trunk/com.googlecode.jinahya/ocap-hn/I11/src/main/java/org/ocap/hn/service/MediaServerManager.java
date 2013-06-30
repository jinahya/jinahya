package org.ocap.hn.service;

/**
 * This class provides access to the configuration of the content server.
 * 
 * It permits privileged applications to register a handler that maps
 * incoming HTTP requests for content streaming to a content binary.
 */
public abstract class MediaServerManager 
{
    
    /**
     * Protected constructor; not for application use.
     */
    protected MediaServerManager()
    {    
    }
    
    /**
     * Get the MediaServerManager instance
     */
    public static MediaServerManager getInstance() 
    {
        return null;
    }
    
    /**
     * Registers a HTTP Request resolution handler. If a handler is already
     * registered, this method SHALL replace it. If the {@code hrrh} parameter
     * is null, any previously registered handler is removed.
     * 
     * @param hrrh The HttpRequestResolutionHandler to register.
     * 
     * @throws SecurityException if the caller does not have
     *         MonitorAppPermission("handler.homenetwork").
     */
   public abstract void setHttpRequestResolutionHandler(HttpRequestResolutionHandler hrrh);
    
    /**
     * Gets the port number used in the URL of audio and video content items 
     * that are streamed over HTTP.
     * 
     * @return  The port number on which the content server is listening for
     *          HTTP streaming requests.
     */
    public abstract int getHttpMediaPortNumber(); 
         
    
}
