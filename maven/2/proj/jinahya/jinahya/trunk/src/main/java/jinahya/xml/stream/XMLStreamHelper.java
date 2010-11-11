package jinahya.xml.stream;


import java.util.Map;
import java.util.Map.Entry;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;


/**
 *
 * @author <a href="mailto:support@minigate.net">Minigate Co., Ltd.</a>
 */
public class XMLStreamHelper {


    /**
     * Reads text value of current element. Given <code>reader</code>'s current
     * event type must be
     * {@link javax.xml.stream.XMLStreamConstants#START_ELEMENT}.
     *
     * @param reader reader
     * @return text content
     * @throws XMLStreamException if XML error occurs
     */
    public static String readTextOnlyElement(final XMLStreamReader reader)
        throws XMLStreamException {

        StringBuffer buffer = null;

        reader.require(XMLStreamConstants.START_ELEMENT, null, null);

        final String namespaceURI = reader.getNamespaceURI();
        final String localName = reader.getLocalName();

        while (reader.next() != XMLStreamConstants.END_ELEMENT) {
            switch (reader.getEventType()) {
                case XMLStreamConstants.CDATA:
                case XMLStreamConstants.CHARACTERS:
                    if (buffer == null) {
                        buffer = new StringBuffer();
                    }
                    buffer.append(reader.getText());
                    break;
                default:
                    break;
            }
        }

        reader.require(XMLStreamConstants.END_ELEMENT, namespaceURI, localName);

        if (buffer == null) {
            return null;
        }

        return buffer.toString();
    }


    /**
     * 
     * @param writer
     * @param localName
     * @param text
     * @throws XMLStreamException
     */
    public static void writeTextOnlyElement(final XMLStreamWriter writer,
                                            final String localName,
                                            final String text)
        throws XMLStreamException {

        writeTextOnlyElement(writer, localName, null, text);
    }
        

    /**
     * 
     * @param writer
     * @param localName
     * @param attributes
     * @param text
     * @throws XMLStreamException
     */
    public static void writeTextOnlyElement(
        final XMLStreamWriter writer, final String localName,
        final Map<String, String> attributes, final String text)
        throws XMLStreamException {

        if (writer == null) {
            throw new IllegalArgumentException("null writer");
        }

        if (localName == null) {
            throw new IllegalArgumentException("null localName");
        }

        if (text == null) {
            writer.writeEmptyElement(localName);
        } else {
            writer.writeStartElement(localName);
        }

        if (attributes != null) {
            for (Entry<String, String> entry : attributes.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    continue;
                }
                writer.writeAttribute(entry.getKey(), entry.getValue());
            }
        }

        if (text == null) {
            writer.writeCharacters("");
        } else {
            writer.writeCharacters(text);
            writer.writeEndElement();
        }
    }


    /**
     *
     * @param writer
     * @param namespaceURI
     * @param localName
     * @param text
     * @throws XMLStreamException
     */
    public static void writeTextOnlyElementNS(final XMLStreamWriter writer,
                                              final String namespaceURI,
                                              final String localName,
                                              final String text)
        throws XMLStreamException {

        writeTextOnlyElementNS(writer, namespaceURI, localName, null, text);
    }
        

    /**
     * 
     * @param writer
     * @param localName
     * @param attributes
     * @param text
     * @throws XMLStreamException
     */
    public static void writeTextOnlyElementNS(
        final XMLStreamWriter writer, final String namespaceURI,
        final String localName, final Map<String, String> attributes,
        final String text)
        throws XMLStreamException {

        if (writer == null) {
            throw new IllegalArgumentException("null writer");
        }

        if (namespaceURI == null) {
            throw new IllegalArgumentException("null namespaceURI");
        }

        if (localName == null) {
            throw new IllegalArgumentException("null localName");
        }

        if (text == null) {
            writer.writeEmptyElement(namespaceURI, localName);
        } else {
            writer.writeStartElement(namespaceURI, localName);
        }

        if (attributes != null) {
            for (Entry<String, String> entry : attributes.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    continue;
                }
                writer.writeAttribute(entry.getKey(), entry.getValue());
            }
        }

        if (text == null) {
            writer.writeCharacters("");
        } else {
            writer.writeCharacters(text);
            writer.writeEndElement();
        }
    }
}
