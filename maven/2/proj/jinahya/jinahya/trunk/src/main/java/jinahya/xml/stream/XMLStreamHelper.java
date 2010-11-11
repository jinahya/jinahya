package jinahya.xml.stream;


import java.util.Map;
import java.util.Map.Entry;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


/**
 *
 * @author <a href="mailto:support@minigate.net">Minigate Co., Ltd.</a>
 */
public class XMLStreamHelper {


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

        if (text != null) {
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

        if (text != null) {
            writer.writeCharacters(text);
            writer.writeEndElement();
        }
    }
}
