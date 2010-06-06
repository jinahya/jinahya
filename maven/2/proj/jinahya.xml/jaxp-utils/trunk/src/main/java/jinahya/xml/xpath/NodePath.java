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

package jinahya.xml.xpath;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author <a href="mailto:support@minigate.net">Jin Kwon</a>
 * @param <T>
 */
public class NodePath<T extends Node> {



    /**
     *
     * @param path
     */
    protected NodePath(final NodePath<? extends T> path) {
        this(path.node, path.path);
    }


    /**
     *
     * @param node
     * @param path
     */
    protected NodePath(final T node, final XPath path) {
        super();

        if (node == null) {
            throw new IllegalArgumentException("'node' is null");
        }

        if (path == null) {
            throw new IllegalArgumentException("'path' is null");
        }

        this.node = node;
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
                return compiled.evaluate(node);
            }
        }

        return path.evaluate(expression, node);
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
    public boolean evaluateBoolean(final String expression,
                                   final boolean compile)
        throws XPathExpressionException {

        return evaluateBOOLEAN(expression, compile).booleanValue();
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
    public double evaluateDouble(final String expression,
                                 final boolean compile)
        throws XPathExpressionException {

        return evaluateNUMBER(expression, compile).doubleValue();
    }


    /**
     *
     * @param expression
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
    public float evaluateFloat(final String expression, final boolean compile)
        throws XPathExpressionException {

        return evaluateNUMBER(expression, compile).floatValue();

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
    public long evaluateLong(final String expression, final boolean compile)
        throws XPathExpressionException {

        return evaluateNUMBER(expression, compile).longValue();
    }


    /**
     *
     * @param expression xpath expression
     * @param compile the boolean flag for pre-compilation
     * @return a Node matches given <code>expression</code>
     * @throws XPathExpressionException if xpath expression error occurs.
     */
    public Node evaluateNODE(final String expression, final boolean compile)
        throws XPathExpressionException {

        return (Node) evaluate(expression, XPathConstants.NODE, compile);
    }


    /**
     *
     * @param expression
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
    public Element evaluateElement(final String expression,
                                   final boolean compile)
        throws XPathExpressionException {

        return (Element) evaluateNODE(expression, compile);
    }


    /**
     *
     * @param expression
     * @param compile
     * @return
     * @throws XPathExpressionException
     */
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
                return compiled.evaluate(node, returnType);
            }
        }

        return path.evaluate(expression, node, returnType);
    }


    /**
     *
     * @param <E> Type extends {@link org.w3c.dom.Node}.
     * @param clazz node class
     * @param expression xpath expression
     * @param compile boolean flag for pre-compilation for future use.
     * @return the child path
     * @throws XPathExpressionException if xpath expression error occurs
     */
    public <E extends Node> NodePath<E> getChildPath(final Class<E> clazz,
                                                     final String expression,
                                                     final boolean compile)
        throws XPathExpressionException {

        //return getChildPath(clazz.cast(evaluateNODE(expression, compile)));
        return getChildPath((E) evaluateNODE(expression, compile));
    }


    /**
     *
     * @param <E>
     * @param child
     * @return
     */
    public <E extends Node> NodePath<E> getChildPath(final E child) {

        if (child == null) {
            throw new IllegalArgumentException("child");
        }


        for (Node iter = child; iter != null; iter = iter.getParentNode()) {
            if (iter.getParentNode().equals(node)) {
                return new NodePath(child, path);
            }
        }

        throw new IllegalArgumentException(
            child + " is not a child of " + node);
    }


    /*
    protected final T getNode() {
        return node;
    }
     */


    /*
    protected final XPath getPath() {
        return path;
    }
     */


    @Override
    public String toString() {
        return ("NodePath<" + node.getClass() + ">: {" + node.getNamespaceURI()
                + "}" + node.getNodeName() + ": " + node.getNodeValue());
    }


    private T node;
    private XPath path;

    private final Map<String, XPathExpression> expressions =
        Collections.synchronizedMap(new HashMap<String, XPathExpression>());
}
