package jinahya.xml.xpath;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;


/**
 *
 * @author <a href="mailto:support@minigate.net">Jin Kwon</a>
 */
public final class DocumentPath {


    /**
     * Creates a new instance.
     *
     * @param document widget document.
     */
    public DocumentPath(final Document document, final XPath xPath) {
        super();

        this.document = document;
        this.xPath = xPath;
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
