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
import java.util.List;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <E> element type parameter
 */
public abstract class ElementLocator<E> {


    /**
     * Creates a new instance.
     *
     * @param root root element
     */
    public ElementLocator(final E root) {
        super();

        if (root == null) {
            throw new IllegalArgumentException("null root");
        }

        path.add(root);
    }


    /**
     * Returns the number of child elements of current element with the
     * specified <code>name</code> in no namespace.
     *
     * @param name element's local name
     * @return child count
     * @see #getCountNS(String, String)
     */
    public abstract int getCount(final String name);


    /**
     * Returns the number of child elements of currently located element with
     * the specified local name and namespace URI.
     *
     * @param space The namespace URI.
     * @param name The local name.
     * @return child count
     * @see #getCount(String) 
     */
    public abstract int getCountNS(final String space, final String name);


    /**
     * Locate the root.
     *
     * @return self
     */
    public ElementLocator<E> locateRoot() {

        while (path.size() > 1) {
            path.remove(path.size() - 1);
        }

        return this;
    }


    /**
     * Synonym of {@link #locateRoot()}.
     *
     * @return self
     * @see #locateRoot()
     */
    public ElementLocator<E> R() {
        return locateRoot();
    }


    /**
     * Locate to the parent of the current element.
     *
     * @return self
     */
    public ElementLocator<E> locateParent() {

        if (path.size() == 1) {
            throw new IllegalStateException("no parent to locate");
        }

        path.remove(path.size() - 1);

        return this;
    }


    /**
     * Synonym of {@link #locateParent()}.
     *
     * @return self
     * @see #locateParent()
     */
    public ElementLocator<E> P() {
        return locateParent();
    }


    /**
     * Locates a child element with the <code>name</code> at <code>index</code>
     * in no namespace.
     *
     * @param name element's local name.
     * @param index element's index
     * @return self
     * @see #locateChildNS(String, String, int) 
     */
    public ElementLocator<E> locateChild(final String name, final int index) {

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
     * Synonym of {@link #locateChild(String, int)}.
     *
     * @param name element's local name
     * @param index target index to locate
     * @return self
     * @see #locateChild(String, int)
     */
    public ElementLocator<E> C(final String name, final int index) {
        return locateChild(name, index);
    }


    /**
     * Finds a child with <code>name</code> in no namespace.
     *
     * @param name child elment's local name
     * @param index child element's index
     * @return child found or null if not found
     * @see #findChildNS(String, String, int) 
     */
    protected abstract E findChild(final String name, final int index);


    /**
     * Adds new child with <code>name</code> to the current element.
     *
     * @param name element's local name
     * @return added child
     * @see #addChildNS(String, String) 
     */
    protected abstract E addChild(final String name);


    /**
     * Locate a child with <code>name</code> at <code>index</code> in
     * <code>space</code>.
     *
     * @param space element's namespace URI
     * @param name element's local name
     * @param index target index to locate
     * @return self
     * @see #locateChild(String, int) 
     */
    public ElementLocator<E> locateChildNS(final String space,
                                           final String name, final int index) {

        if (space == null) {
            throw new IllegalArgumentException("null namespace");
        }

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        E child = findChildNS(space, name, index);

        if (child == null) {
            child = addChildNS(space, name);
        }

        path.add(child);

        return this;
    }


    /**
     * Synonym for {@link #locateChildNS(String, String, int)}.
     *
     * @param space element's namespace URI
     * @param name element's local name
     * @param index index target index to locate.
     * @return self
     * @see #locateChildNS(String, String, int) 
     */
    public ElementLocator<E> C(final String space, final String name,
                               final int index) {

        return locateChildNS(space, name, index);
    }


    /**
     * Finds a child element with <code>name</code> at <code>index</code> in
     * <code>space</code>.
     *
     * @param space element's namespace URI
     * @param name name element's local name
     * @param index taget index to locate
     * @return found child or null if not found
     * @see #findChild(String, int) 
     */
    protected abstract E findChildNS(final String space, final String name,
                                     final int index);


    /**
     * Adds new child element to the current element.
     *
     * @param space element's namespace URI
     * @param name elements' local name
     * @return added child
     * @see #addChild(String) 
     */
    protected abstract E addChildNS(final String space, final String name);


    /**
     * Returns text value of current element.
     *
     * @return text value
     */
    public String getText() {
        return getText(false);
    }


    /**
     * Synonym of {@link #getText()}.
     *
     * @return text value of current element.
     * @see #getText() 
     */
    public String T() {
        return getText();
    }


    /**
     * Returns text value of current element and locate parent if specified.
     *
     * @param locateParent locate parent flag
     * @return text value.
     */
    public String getText(final boolean locateParent) {

        final String text = getText();

        if (locateParent) {
            locateParent();
        }

        return text;
    }


    /**
     * Synonym of {@link #getText(boolean) }.
     *
     * @param locateParent locate parent flag
     * @return text value
     * @see #getText(boolean) 
     */
    public String T(final boolean locateParent) {
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
     * @see #setText(String) 
     */
    public ElementLocator<E> T(final String text) {
        return setText(text);
    }


    /**
     * Adds text value to current element and locate parent if specified.
     *
     * @param text text value
     * @param locateParent locate parent flag
     * @return self
     */
    public ElementLocator<E> setText(final String text,
                                     final boolean locateParent) {

        setText(text);

        if (locateParent) {
            locateParent();
        }

        return this;
    }


    /**
     * Synonym of {@link #setText(String, boolean)}.
     *
     * @param text text value
     * @param locateParent locate parent flag
     * @return self
     * @see #setText(String, boolean) 
     */
    public ElementLocator<E> T(final String text, final boolean locateParent) {
        return setText(text, locateParent);
    }


    /**
     * Returns the value of attribute which has <code>name</code> in no
     * namespace.
     *
     * @param name attribute's local name
     * @return attribute's value
     * @see #getAttribute(String, String) 
     */
    public abstract String getAttribute(final String name);


    /**
     * Returns the value of attribute which has <code>name</code> in
     * <code>space</code>.
     *
     * @param space attribute's namesapce URI
     * @param name attribute's local name
     * @return attributes's value
     * @see #getAttribute(String)
     */
    public abstract String getAttributeNS(final String space,
                                          final String name);


    /**
     * Sets value for attribute which has <code>name</code> in no namespace.
     *
     * @param name attribute's local name
     * @param value attribute's value
     * @return self
     * @see #setAttributeNS(String, String, String)
     */
    public abstract ElementLocator<E> setAttribute(final String name,
                                                   final String value);


    /**
     * Sets the value of attribute which has <code>name</code> in
     * <code>space</code>.
     * 
     * @param space attribute's namespace URI
     * @param name attribute's local name
     * @param value attributes' value
     * @return self
     * @see #setAttribute(String, String) 
     */
    public abstract ElementLocator<E> setAttributeNS(final String space,
                                                     final String name,
                                                     final String value);


    /**
     * Returns currently located element.
     *
     * @return current element
     */
    protected final E getCurrent() {
        return path.get(path.size() - 1);
    }


    /** element path. */
    private final List<E> path = new ArrayList<E>();
}
