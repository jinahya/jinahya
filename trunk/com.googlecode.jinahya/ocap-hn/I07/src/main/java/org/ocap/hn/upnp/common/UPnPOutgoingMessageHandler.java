package org.ocap.hn.upnp.common;

import java.net.InetSocketAddress;


/**
 * This interface represents an outgoing message handler that can 
 * monitor and modify any outgoing messages from the UPnP stack. 
 * All messages originating from the UPnP stack go through the 
 * handler (if the handler is registered). This includes 
 * advertisements, action responses and device, service and icon
 * retrieval responses on a server, UPnP action invocations, 
 * subscription requests, device searches and device, service 
 * and icon retrieval requests on a client. 
 */
public interface UPnPOutgoingMessageHandler
{
    /**
     * Handles an outgoing message.  The primary responsibility is
     * to process the provided {@code UPnPMessage} object and produce a
     * composite byte array for the outbound message that complies with
     * the UPnP Device Architecture specification.
     * An application-provided {@code UPnPOutgoingMessageHandler} may invoke
     * the default, stack-provided message handler via the specified
     * {@code defaultHandler}.
     * The handler may also cause the outgoing message to be discarded by
     * returning {@code null}; the message SHALL NOT be sent,
     * and subsequent processing SHALL continue as if any expected response
     * to the message had never been received.
     * <p>
     * Note that if the {@code UPnPMessage} provided to this method contains
     * an HTTP {@code CONTENT-LENGTH} header, its value is undefined.
     * The handler must supply the correct value after "stringifying"
     * the XML document provided by {@link UPnPMessage#getXML()} into
     * XML data to be carried in returned byte array.
     * See {@link UPnPMessage#getHeaders()}.
     *
     * @param address The InetSocketAddress to which the message is to be sent.
     *  
     * @param message The UPnP message that is to be sent.
     *  
     * @param defaultHandler The default stack-provided outgoing
     *                       message handler.
     *                       If this {@code UPnPOutgoingMessageHandler} is
     *                       the default outgoing message handler, this
     *                       parameter SHALL be ignored.
     *  
     * @return Composite output byte array containing HTTP start line,
     *         headers, and body of the message. No further
     *         processing or parsing is performed by the stack
     *         on the output byte array prior to transmission. The
     *         handler can cause the outgoing message to be
     *         discarded by returning null.
     *
     */
     public byte[] handleOutgoingMessage(InetSocketAddress address, 
                                        UPnPMessage message,
                                        UPnPOutgoingMessageHandler defaultHandler);
}

