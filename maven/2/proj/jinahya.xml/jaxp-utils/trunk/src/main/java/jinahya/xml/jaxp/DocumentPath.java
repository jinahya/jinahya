package jinahya.xml.jaxp;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author <a href="mailto:support@minigate.net">Jin Kwon</a>
 */
public final class DocumentPath {


    /**
     *
     */
    private static DocumentBuilder documentBuilder = null;


    /**
     *
     * @return
     * @throws ParserConfigurationException
     */
    private static synchronized DocumentBuilder getDocumentBuilder()
        throws ParserConfigurationException {

        if (documentBuilder == null) {

            final DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();

            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        }

        return documentBuilder;
    }


    /**
     *
     * @param source
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static DocumentPath newInstance(final File source)
        throws ParserConfigurationException, SAXException, IOException {

        return new DocumentPath(getDocumentBuilder().parse(source));
    }


    /**
     *
     * @param source
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static DocumentPath newInstance(final InputStream source)
        throws ParserConfigurationException, SAXException, IOException {

        return new DocumentPath(getDocumentBuilder().parse(source));
    }


    /**
     *
     * @param source
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static DocumentPath newInstance(final InputSource source)
        throws ParserConfigurationException, SAXException, IOException {

        return new DocumentPath(getDocumentBuilder().parse(source));
    }


    /**
     * XPathFactory singleton instance.
     */
    private static XPathFactory xPathFactory;


    /**
     * Returns a new XPath instance.
     *
     * @return a new XPath instance.
     */
    private static synchronized XPath newXPath() {
        if (xPathFactory == null) {
            xPathFactory = XPathFactory.newInstance();
            //xPathFactory = new org.apache.xpath.jaxp.XPathFactoryImpl();
        }
        return xPathFactory.newXPath();
    }


    /**
     * Creates a new instance.
     *
     * @param document widget document.
     */
    private DocumentPath(final Document document) {
        super();

        this.document = document;

        xPath = newXPath();
    }


    /**
     *
     * @param expression
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
    public String evaluate(final String expression, final boolean compile)
        throws XPathExpressionException {
        return evaluate(expression, document, compile);
    }


    /**
     *
     * @param expression
     * @param item
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
    public String evaluate(final String expression, final Object item,
                           final boolean compile)
        throws XPathExpressionException {

        if (compile) {
            synchronized (expressions) {
                XPathExpression compiled = expressions.get(expression);
                if (compiled != null) {
                    compiled = xPath.compile(expression);
                    expressions.put(expression, compiled);
                }
                return compiled.evaluate(item);
            }
        }

        return xPath.evaluate(expression, item);
    }


    /**
     *
     * @param expression
     * @param returnType
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
    public Object evaluate(final String expression, final QName returnType,
                           final boolean compile)
        throws XPathExpressionException {

        return evaluate(expression, document, returnType, compile);
    }


    /**
     *
     * @param expression
     * @param item
     * @param returnType
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
    public Object evaluate(final String expression, final Object item,
                           final QName returnType, final boolean compile)
        throws XPathExpressionException {

        if (compile) {
            synchronized (expressions) {
                XPathExpression compiled = expressions.get(expression);
                if (compiled != null) {
                    compiled = xPath.compile(expression);
                    expressions.put(expression, compiled);
                }
                return compiled.evaluate(item, returnType);
            }
        }

        return xPath.evaluate(expression, item, returnType);
    }


    private Document document;
    private XPath xPath;

    private final Map<String, XPathExpression> expressions =
        Collections.synchronizedMap(new HashMap<String, XPathExpression>());
}
