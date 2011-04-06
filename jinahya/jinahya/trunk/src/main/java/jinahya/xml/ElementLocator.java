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


package jinahya.xml;


import java.util.ArrayList;
import java.util.LinkedList;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <E>
 */
public abstract class ElementLocator<E> {


    public ElementLocator(final E root) {
        super();

        if (root == null) {
            throw new IllegalArgumentException("null root");
        }

        path = new LinkedList<E>();
        path.add(root);
    }


    /**
     * Returns the number of child elements those are reside in no name space.
     *
     * @param name element name
     * @return child count;
     * @see #getCountNS(java.lang.String, java.lang.String)
     */
    public abstract int getCount(final String name);


    /**
     * Returns the number of child elements those are reside in given
     * <code>namespace</code>.
     *
     * @param namespace namespace
     * @param name name
     * @return child count
     */
    public abstract int getCountNS(final String namespace, final String name);


    /**
     * Locate to the root.
     *
     * @return self
     */
    public final ElementLocator<E> locateRoot() {

        while (path.size() > 1) {
            path.removeLast();
        }

        return this;
    }


    /**
     * 
     * @return 
     * @see #locateRoot()
     */
    public final ElementLocator<E> R() {
        return locateRoot();
    }


    /**
     * Locate to the parent of the current element.
     *
     * @return self
     */
    public final ElementLocator<E> locateParent() {

        if (path.size() > 1) {
            path.removeLast();
        }

        return this;
    }


    /**
     * Shortened for {@link #locateParent()}.
     *
     * @return self
     * @see #locateParent()
     */
    public final ElementLocator<E> P() {
        return locateParent();
    }


    /**
     * Locates a child.
     *
     * @param name element name.
     * @param index element index
     * @return self
     * @see #locateChildNS(java.lang.String, java.lang.String, int) 
     */
    public final ElementLocator<E> locateChild(final String name,
                                               final int index) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }


        E child = findChild(name, index);

        if (child == null) {
            child = addChild(name);
        }

        path.add(child);

        return this;
    }


    /**
     * Locates a child.
     *
     * @param name
     * @param index
     * @return 
     * @see #locateChild(java.lang.String, int) 
     */
    public ElementLocator<E> C(final String name, final int index) {
        return locateChild(name, index);
    }


    /**
     * Finds a child.
     *
     * @param name child elment name
     * @param index child element index
     * @return child found or null if not found
     */
    protected abstract E findChild(final String name, final int index);


    /**
     * Adds new child to the current element.
     *
     * @param name name
     * @return added child
     */
    protected abstract E addChild(final String name);


    /**
     * Locate a child.
     *
     * @param namespace namespace
     * @param name name
     * @param index index
     * @return self
     * @see #locateChild(java.lang.String, int) 
     */
    public final ElementLocator<E> locateChildNS(final String namespace,
                                                 final String name,
                                                 final int index) {

        if (namespace == null) {
            throw new IllegalArgumentException("null namespace");
        }

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        E child = findChildNS(namespace, name, index);

        if (child == null) {
            child = addChildNS(namespace, name);
        }

        path.add(child);

        return this;
    }


    /**
     * Locate a child.
     *
     * @param namespace namespace
     * @param name name
     * @param index index
     * @return self
     * @see #locateChildNS(java.lang.String, java.lang.String, int) 
     */
    public final ElementLocator<E> C(final String namespace, final String name,
                                     final int index) {

        return locateChildNS(namespace, name, index);
    }


    /**
     * Finds a child.
     *
     * @param namespace namespace
     * @param name name
     * @param index index
     * @return child or null if not found
     */
    protected abstract E findChildNS(final String namespace, final String name,
                                     final int index);


    /**
     * Adds new child element to the current element.
     *
     * @param namespace namespace
     * @param name name
     * @return added child
     */
    protected abstract E addChildNS(final String namespace, final String name);


    /**
     * Returns text value of current element.
     *
     * @return text value
     */
    public abstract String getText();


    /**
     * @return
     * @see #getText() 
     */
    public final String T() {
        return getText();
    }


    /**
     * Returns text value of current element and locate parent if specified.
     *
     * @param locateParent locate parent flag
     * @return text value.
     */
    public final String getText(final boolean locateParent) {

        final String text = getText();

        if (locateParent) {
            locateParent();
        }

        return text;
    }


    /**
     * Returns text value of current element and locate parent if specified.
     *
     * @param locateParent locate parent flag
     * @return text value
     * @see #getText(boolean) 
     */
    public final String T(final boolean locateParent) {
        return getText(locateParent);
    }


    /**
     * Adds text value to current element.
     *
     * @param text text value
     * @return self
     */
    public abstract ElementLocator<E> setText(final String text);


    /**
     * Adds text value to current element.
     *
     * @param text text value
     * @return self
     * @see #setText(java.lang.String) 
     */
    public final ElementLocator<E> T(final String text) {
        return setText(text);
    }


    /**
     * Adds text value to current element and locate parent if specified.
     *
     * @param text text value
     * @param locateParent locate parent flag
     * @return self
     */
    public final ElementLocator<E> setText(final String text,
                                           final boolean locateParent) {

        setText(text);

        if (locateParent) {
            locateParent();
        }

        return this;
    }


    /**
     * Adds text value to current element and locate parent if specified.
     *
     * @param text text value
     * @param locateParent locate parent flag
     * @return self
     * @see #setText(java.lang.String, boolean) 
     */
    public final ElementLocator<E> T(final String text,
                                     final boolean locateParent) {
        return setText(text, locateParent);
    }


    /**
     * Returns attribute value.
     *
     * @param name attribute name
     * @return attribute value
     * @see #getAttribute(java.lang.String, java.lang.String) 
     */
    public abstract String getAttribute(final String name);


    /**
     * 
     * @param space
     * @param name
     * @return 
     */
    public abstract String getAttributeNS(final String space,
                                          final String name);


    /**
     * Sets attribute value.
     *
     * @param name attribute name
     * @param value attribute value
     * @return self
     */
    public abstract ElementLocator<E> setAttribute(final String name,
                                                   final String value);


    /**
     * 
     * @param name
     * @param value
     * @return 
     * @see #setAttribute(java.lang.String, java.lang.String) 
     */
    public final ElementLocator<E> A(final String name, final String value) {
        return setAttribute(name, value);
    }


    /**
     * 
     * @param space
     * @param name
     * @param value
     * @return self
     */
    public abstract ElementLocator<E> setAttributeNS(final String space,
                                                     final String name,
                                                     final String value);


    /**
     * 
     * @param space
     * @param name
     * @param value
     * @return 
     * @see #setAttributeNS(java.lang.String, java.lang.String, java.lang.String) 
     */
    public final ElementLocator<E> A(final String space, final String name,
                                     final String value) {

        return setAttributeNS(space, name, value);
    }


    /**
     * Returns
     */
    /**
     * Returns currently located element.
     *
     * @return current element
     */
    protected final E getCurrent() {
        return path.getLast();
    }


    /** element path. */
    private final LinkedList<E> path;
}
