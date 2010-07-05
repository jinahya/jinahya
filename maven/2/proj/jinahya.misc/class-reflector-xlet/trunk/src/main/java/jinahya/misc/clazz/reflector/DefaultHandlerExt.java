package jinahya.misc.clazz.reflector;


import java.io.IOException;
import java.io.Writer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
class DefaultHandlerExt extends DefaultHandler {


    /**
     *
     * @param out
     */
    public DefaultHandlerExt(final Writer out) {
        super();

        this.out = out;
    }


    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attributes)
        throws SAXException {

        startElement(uri, localName, qName, attributes, false);
    }


    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attributes,
                             final boolean close)
        throws SAXException {


        try {
            out.write("<" + localName);

            for (int i = 0; i < attributes.getLength(); i++) {
                out.write(" " + attributes.getLocalName(i) + "=\""
                          + attributes.getValue(i) + "\"");
            }

            if (close) {
                out.write("/");
            }

            out.write(">");
            out.flush();

        } catch (IOException ioe) {
            throw new SAXException(ioe.getMessage());
        }
    }


    @Override
    public void endElement(final String uri, final String localName,
                           final String qName)
        throws SAXException {

        try {
            out.write("</" + localName + ">");
            out.flush();
        } catch (IOException ioe) {
            throw new SAXException(ioe.getMessage());
        }
    }


    private Writer out;
}
