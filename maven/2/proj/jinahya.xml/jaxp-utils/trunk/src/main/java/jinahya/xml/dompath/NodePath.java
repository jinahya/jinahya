/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package jinahya.xml.dompath;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author <a href="mailto:support@minigate.net">Jin Kwon</a>
 */
public class NodePath<T extends Node> {


    /**
     *
     * @param item
     * @param path
     */
    public NodePath(final T item, final XPath path) {
        super();

        this.item = item;
        this.path = path;
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

        if (compile) {
            synchronized (expressions) {
                XPathExpression compiled = expressions.get(expression);
                if (compiled == null) {
                    compiled = path.compile(expression);
                    expressions.put(expression, compiled);
                }
                return compiled.evaluate(item);
            }
        }

        return path.evaluate(expression, item);
    }


    /**
     *
     * @param expression
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
    public Boolean evaluateBOOLEAN(final String expression,
                                   final boolean compile)
        throws XPathExpressionException {

        return (Boolean) evaluate(expression, XPathConstants.BOOLEAN, compile);
    }


    /**
     *
     * @param expression
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
    public Double evaluateNUMBER(final String expression, final boolean compile)
        throws XPathExpressionException {

        return (Double) evaluate(expression, XPathConstants.NUMBER, compile);
    }


    /**
     *
     * @param expression
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
    public int evaluateInt(final String expression, final boolean compile)
        throws XPathExpressionException {

        return evaluateNUMBER(expression, compile).intValue();
    }


    /**
     *
     * @param expression
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
    public Node evaluateNODE(final String expression, final boolean compile)
        throws XPathExpressionException {

        return (Node) evaluate(expression, XPathConstants.NODE, compile);
    }


    public NodeList evaluateNODESET(final String expression,
                                    final boolean compile)
        throws XPathExpressionException {

        return (NodeList) evaluate(expression, XPathConstants.NODESET, compile);
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

        if (compile) {
            synchronized (expressions) {
                XPathExpression compiled = expressions.get(expression);
                if (compiled == null) {
                    compiled = path.compile(expression);
                    expressions.put(expression, compiled);
                }
                return compiled.evaluate(item, returnType);
            }
        }

        return path.evaluate(expression, item, returnType);
    }


    protected final Document getDocument() {
        if (item instanceof Document) {
            return (Document) item;
        }
        if (parent == null) {
            return null;
        }
        return parent.getDocument();
    }


    protected final T getItem() {
        return item;
    }


    protected final XPath getPath() {
        return path;
    }


    private NodePath parent;

    private T item;
    private XPath path;

    private final Map<String, XPathExpression> expressions =
        Collections.synchronizedMap(new HashMap<String, XPathExpression>());
}
