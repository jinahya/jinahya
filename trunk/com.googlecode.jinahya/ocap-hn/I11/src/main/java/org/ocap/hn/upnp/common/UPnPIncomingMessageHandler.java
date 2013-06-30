package org.ocap.hn.upnp.common;

import java.net.InetSocketAddress;


/**
 * This interface represents an incoming message handler that can
 * monitor and modify any incoming messages to the UPnP stack. 
 * All messages targeting the UPnP stack go through the handler 
 * (if the handler is registered). This includes advertisements,
 * action responses, device, service and icon retrieval 
 * responses on a client, UPnP action invocations, subscription 
 * requests, device searches, and device, service and icon 
 * retrieval requests on a server. 
 */

public interface UPnPIncomingMessageHandler
{
    /**
     * Handles an incoming message. The primary responsibility is
     * to parse the incoming byte array and produce an XML document 
     * representing the incoming content.
     * An application-provided {@code UPnPIncomingMessageHandler} may invoke
     * the default, stack-provided message handler via the specified
     * {@code defaultHandler}.
     * The handler may also cause the incoming message to be discarded by
     * returning {@code null}; subsequent
     * processing SHALL continue as if the message had never been received.
     * <p>
     * Note that if the {@code UPnPMessage} returned by this method contains an
     * HTTP {@code CONTENT-LENGTH} header, its value should describe the
     * length of the raw XML data <i>before</i> it is parsed
     * into the XML document reported by {@link UPnPMessage#getXML()}.  See
     * {@link UPnPMessage#getHeaders()}.

     *
     * @param address InetSocketAddress representing the network interface
     * and port on which the message was received.
     *  
     * @param incomingMessage  The incoming UPnP message data,
     *                         including any HTTP headers.
     *  
     * @param defaultHandler The default stack-provided incoming 
     *                       message handler.
     *                       If this {@code UPnPIncomingMessageHandler} is
     *                       the default incoming message handler, this
     *                       parameter SHALL be ignored.
     *  
     * @return The UPnP message to be passed up the stack.
     *                     The handler can cause the incoming message to be
     *                     discarded by returning null.
     */
    public UPnPMessage handleIncomingMessage(InetSocketAddress address,
            byte[] incomingMessage,
            UPnPIncomingMessageHandler defaultHandler);
}

