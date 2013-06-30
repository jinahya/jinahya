package org.ocap.hn.upnp.common;

import org.w3c.dom.Document;

/**
 * The class represents a UPnP message comprising an HTTP start line,
 * zero or more headers and an optional XML document.
 */
public class UPnPMessage
{
    /**
     * Public constructor for the message.
     *
     * @param startLine The HTTP start line, excluding trailing CR/LF
     * characters. May be an empty string.
     *
     * @param headers The HTTP or other headers that this instance is
     *                to contain, excluding trailing CF/LF characters and the
     *                blank line that follows HTTP headers.
     *                The contents of the {@code headers} parameter are
     *                copied into the resulting {@code UPnPMessage} object.
     *                May be a zero-length array.
     *
     * @param xml The XML document that this instance is to contain. 
     *            May be null.
     *
     * @throws NullPointerException if {@code startLine},
     * {@code headers}, or any of the array elements within {@code headers} is
     * {@code null}.
     */
    public UPnPMessage(String startLine, String[] headers, Document xml)
    {

    }

    /**
     * Reports the HTTP start line, excluding trailing CR/LF characters.
     *
     * @return The HTTP start line. If the UPnPMessage includes no start line,
     * returns the empty string.
     */
    public String getStartLine()
    {
        return null;
    }

    /**
     * Reports the headers from the message, including all HTTP headers but
     * excluding trailing CR/LF characters.
     * The blank line following the last HTTP header is not
     * included in the array.
     * <p>
     * Note that if the message includes an HTTP {@code CONTENT-LENGTH} header,
     * its value describes the length of the raw XML data carried in the
     * UPnP message body, not the size of the XML document provided by
     * {@link #getXML()}.
     *  
     * @return An array containing a copy of the header lines contained
     *         in the {@code UPnPMessage} obejct.
     *         If the {@code UPnPMessage} has no headers,
     *         returns a zero-length array.
     *
     * @see UPnPIncomingMessageHandler
     * @see UPnPOutgoingMessageHandler
     */
    public String[] getHeaders()
    {
        return null;
    }

    /**
     * Gets the XML from the message. 
     *  
     * @return The XML document of the message. May be null if the 
     *         message contained no XML document.
     */
    public Document getXML()
    {
        return null;
    }
}
