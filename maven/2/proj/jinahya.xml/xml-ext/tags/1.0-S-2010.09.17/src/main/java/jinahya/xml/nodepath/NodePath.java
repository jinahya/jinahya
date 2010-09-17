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
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author <a href="mailto:support@minigate.net">Jin Kwon</a>
 * @param <T>
 */
public class NodePath {


    /*
     *
     * @param path
    public NodePath(final NodePath<? extends T> path) {
        this(path.node, path.path);
    }
     */


    public NodePath(final Node node) {
        this(node, XPathFactory.newInstance().newXPath());
    }


    /**
     *
     * @param node
     * @param path
     */
    public NodePath(final Node node, final XPath path) {
        super();

        if (node == null) {
            throw new IllegalArgumentException(
                "param:0:" + Node.class + ": is null");
        }

        if (path == null) {
            throw new IllegalArgumentException(
                "param:1:" + XPath.class + ": is null");
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
    public String STRING(final String expression)
        throws XPathExpressionException {

        return (String) evaluate(expression, XPathConstants.STRING);
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public boolean BOOLEAN(final String expression)
        throws XPathExpressionException {

        return (Boolean) evaluate(expression, XPathConstants.BOOLEAN);
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public Double NUMBER(final String expression)
        throws XPathExpressionException {

        return (Double) evaluate(expression, XPathConstants.NUMBER);
    }


    /**
     *
     * @param expression
     * @return
     */
    public double DOUBLE(final String expression)
        throws XPathExpressionException {

        return NUMBER(expression).doubleValue();
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public float FLOAT(final String expression)
        throws XPathExpressionException {

        return NUMBER(expression).floatValue();
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public int INTEGER(final String expression)
        throws XPathExpressionException {

        return NUMBER(expression).intValue();
    }


    /**
     * 
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public long LONG(final String expression)
        throws XPathExpressionException {

        return NUMBER(expression).longValue();
    }


    /**
     *
     * @param expression xpath expression
     * @return a Node matches given <code>expression</code>
     * @throws XPathExpressionException if xpath expression error occurs.
     */
    public Node NODE(final String expression) throws XPathExpressionException {
        return (Node) evaluate(expression, XPathConstants.NODE);
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public Element ELEMENT(final String expression)
        throws XPathExpressionException {

        return (Element) NODE(expression);
    }


    /**
     *
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public NodeList NODESET(final String expression)
        throws XPathExpressionException {

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
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (returnType == null) {
            throw new IllegalArgumentException(
                "param:1:" + QName.class + ": is null");
        }

        if (cacheExpressions) {
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
     * @param expression
     * @return
     * @throws XPathExpressionException
     */
    public NodePath child(final String expression)
        throws XPathExpressionException {

        return new NodePath(NODE(expression), path);
    }


    /**
     *
     * @param node
     * @return new child node path
     * @throws IllegalArguementException if given <code>node</code> is null
     *         or not a descendent of current node.
     */
    public NodePath child(final Node node) {

        if (node == null) {
            throw new IllegalArgumentException(
                "param:0:" + Node.class + ": is null");
        }

        for (Node parent = node.getParentNode(); parent != null;
             parent = parent.getParentNode()) {

            if (parent.equals(this.node)) {
                return new NodePath(node, path);
            }
        }

        /*
         * Billie Jean is not my lover
         * She's just a girl who claims that I am the one
         * But the kid is not my son
         * She says I am the one, but the kid is not my son
         * (Don't think twice)
         */

        throw new IllegalArgumentException(
            "param:0:" + Node.class + ":" + node
            + " is not a descendent of current node");
    }


    /**
     *
     * @param <E>
     * @param clazz
     * @return
     */
    public NodePath parent() {

        final Node parent = node.getParentNode();

        if (parent == null) {
            return null;
        }

        return new NodePath(parent, path);
    }


    @Override
    public String toString() {
        return ("NodePath: {" + node.getNamespaceURI() + "}"
                + node.getNodeName() + ": " + node.getNodeValue());
    }


    /**
     * Gets the <code>cacheExpressions</code> value.
     *
     * @return the current <code>cacheExpressions</code> vlaue
     */
    public final boolean getCacheExpressions() {
        return cacheExpressions;
    }


    /**
     * Sets the <code>compile</code> value.
     *
     * @param compile new compile value
     */
    public final void setCacheExpressions(final boolean cacheExpressions) {
        this.cacheExpressions = cacheExpressions;
    }


    /**
     *
     * @return
     */
    public final Node getNode() {
        return node;
    }


    /**
     *
     */
    public void clearCachedExpressions() {
        synchronized (compiledExpressions) {
            compiledExpressions.clear();
        }
    }


    private Node node;
    private XPath path;

    private boolean cacheExpressions = false;

    private final Map<String, XPathExpression> compiledExpressions =
        Collections.synchronizedMap(new HashMap<String, XPathExpression>());
}
