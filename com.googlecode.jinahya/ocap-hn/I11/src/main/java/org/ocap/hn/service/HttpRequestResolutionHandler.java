package org.ocap.hn.service;

import java.net.InetAddress;
import java.net.URL;
import org.ocap.hn.NetworkInterface;

/**
 * This interface provides a handler that can be registered with an
 * implementation to provide a mapping service between an incoming HTTP GET or HEAD
 * request from a client and a content binary served by
 * the OC-DMS. 
 * <p>
 * If a <code>HttpRequestResolutionHandler</code> is registered then the 
 * implementation SHALL invoke the handler and use mapping provided by the
 * handler. If the handler is not registered or fails to provide a mapping,
 * the implementation attempts to map the request URI itself.
 */
public interface HttpRequestResolutionHandler {
    
    /**
     * Resolves the incoming HTTP request to a URL
     * that identifies a content binary 
     *
     * @param inetAddress IP address the transaction was sent from.
     * @param url The <code>URL</code> requested by the transaction.
     * @param request The HTTP message request;
     *                i.e., the request line and subsequent message headers.
     * @param networkInterface The <code>NetworkInterface</code> the request
     *      came on.  The <code>getInetAddress</code> method of this parameter
     *      SHALL return the <code>InetAddress</code> on which the 
     *      <code>request</code> was received.
     *
     * @return URL of the content binary if a match is found,
     * null otherwise.
     * 
     */
    public URL resolveHttpRequest(InetAddress inetAddress,
                                     URL url,
                                     String[] request,
                                     NetworkInterface networkInterface);
    
}
