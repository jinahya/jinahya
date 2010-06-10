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

package jinahya.xml.nodepath;


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
    public NodePath(final NodePath<? extends T> path) {
        this(path.node, path.path);
    }


    /**
     *
     * @param node
     * @param path
     */
    public NodePath(final T node, final XPath path) {
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
     * @return
     * @throws XPathExpressionException
     */
    public String evaluate(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        if (compileExpressions) {
            synchronized (compiledExpressions) {
                XPathExpression compiled = compiledExpressions.get(expression);
                if (compiled == null) {
                    compiled = path.compile(expression);
                    compiledExpressions.put(expression, compiled);
                }
                return compiled.evaluate(node);
            }
        }

        return path.evaluate(expression, node);
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public Boolean evaluateBOOLEAN(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return (Boolean) evaluate(expression, XPathConstants.BOOLEAN);
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public boolean evaluateBoolean(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return evaluateBOOLEAN(expression).booleanValue();
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public Double evaluateNUMBER(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return (Double) evaluate(expression, XPathConstants.NUMBER);
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public double evaluateDouble(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return evaluateNUMBER(expression).doubleValue();
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public float evaluateFloat(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return evaluateNUMBER(expression).floatValue();
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public int evaluateInt(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return evaluateNUMBER(expression).intValue();
    }


    /**
     * 
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public long evaluateLong(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return evaluateNUMBER(expression).longValue();
    }


    /**
     *
     * @param expression xpath expression
     * @return a Node matches given <code>expression</code>
     * @throws XPathExpressionException if xpath expression error occurs.
     */
    public Node evaluateNODE(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return (Node) evaluate(expression, XPathConstants.NODE);
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public Element evaluateElement(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return (Element) evaluateNODE(expression);
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public NodeList evaluateNODESET(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return (NodeList) evaluate(expression, XPathConstants.NODESET);
    }


    /**
     *
     * @param expression
     * @param returnType
     * @return
     * @throws XPathExpressionException
     */
    public Object evaluate(final String expression, final QName returnType)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        if (returnType == null) {
            throw new IllegalArgumentException("'returnType' is null");
        }

        if (compileExpressions) {
            synchronized (compiledExpressions) {
                XPathExpression compiled = compiledExpressions.get(expression);
                if (compiled == null) {
                    compiled = path.compile(expression);
                    compiledExpressions.put(expression, compiled);
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
     * @return the child path
     * @throws XPathExpressionException if xpath expression error occurs
     */
    public <E extends Node> NodePath<E> getChildPath(final Class<E> clazz,
                                                     final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return getChildPath((E) evaluateNODE(expression));
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public NodePath<Node> getChildPath(final String expression)
        throws XPathExpressionException {

        if (expression == null) {
            throw new IllegalArgumentException("'expression' is null");
        }

        return getChildPath(Node.class, expression);
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


    /**
     *
     * @param <E>
     * @param clazz
     * @return
     */
    public <E extends Node> NodePath<E> getParentPath(final Class<E> clazz) {

        if (clazz == null) {
            throw new IllegalArgumentException("'clazz' is null");
        }

        final E parent = (E) node.getParentNode();

        if (parent == null) {
            return null;
        }

        return new NodePath(parent, path);
    }


    /**
     *
     * @return
     */
    public NodePath<Node> getParentPath() {
        return getParentPath(Node.class);
    }


    @Override
    public String toString() {
        return ("NodePath<" + node.getClass() + ">: {" + node.getNamespaceURI()
                + "}" + node.getNodeName() + ": " + node.getNodeValue());
    }


    /**
     * Gets the <code>compile</code> value.
     *
     * @return the current compile vlaue
     */
    public final boolean getCompileExpressions() {
        return compileExpressions;
    }


    /**
     * Sets the <code>compile</code> value.
     *
     * @param compile new compile value
     */
    public final void setCompileExpressions(final boolean compileExpressions) {
        this.compileExpressions = compileExpressions;
    }


    /**
     *
     * @return
     */
    public final T getNode() {
        return node;
    }


    /**
     *
     * @param node
     */
    public final void setNode(final T node) {
        if (node == null) {
            throw new IllegalArgumentException("node is null");
        }
        this.node = node;
    }


    /**
     *
     * @return
     */
    public final XPath getPath() {
        return path;
    }


    /**
     *
     * @param path
     */
    public final void setPath(final XPath path) {
        if (path == null) {
            throw new IllegalArgumentException("path is null");
        }
        this.path = path;
    }


    private T node;
    private XPath path;

    private boolean compileExpressions = false;

    private final Map<String, XPathExpression> compiledExpressions =
        Collections.synchronizedMap(new HashMap<String, XPathExpression>());
}
