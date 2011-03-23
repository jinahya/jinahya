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
     * 
     * @param space
     * @param name
     * @return 
     */
    public abstract int getCountNS(final String space, final String name);


    /**
     * 
     * @param name
     * @return 
     * @see #getCount(java.lang.String, java.lang.String)
     */
    public abstract int getCount(final String name);


    /**
     * Locate to the root.
     *
     * @return self
     */
    public final ElementLocator<E> locateRoot() {

        if (path.size() > 1) {
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
     * @see #locateParent()
     */
    public final ElementLocator<E> P() {
        return locateParent();
    }


    /**
     * 
     * @param name
     * @param index
     * @return 
     */
    public final ElementLocator<E> locateChild(final String name,
                                               final int index) {

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        E child = findChild(name, index);

        if (child == null) {
            child = addChild(name);
        }

        path.add(child);

        return this;
    }


    /**
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
     * Finds child. IndexOutOfBoundsException must be thrown if no child found.
     *
     * @param name child name
     * @param index child index
     * @return found child
     */
    protected abstract E findChild(final String name, final int index);


    /**
     * 
     * @param name
     * @return 
     */
    protected abstract E addChild(final String name);


    /**
     * 
     * @param space
     * @param name
     * @param index
     * @return 
     */
    public final ElementLocator<E> locateChildNS(
        final String space, final String name, final int index) {

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        E child = findChildNS(space, name, index);

        if (child == null) {
            child = addChildNS(space, name);
        }

        path.add(child);

        return this;
    }


    /**
     * 
     * @param space
     * @param name
     * @param index
     * @return 
     * @see #locateChildNS(java.lang.String, java.lang.String, int) 
     */
    public final ElementLocator<E> C(final String space, final String name,
                                     final int index) {

        return locateChildNS(space, name, index);
    }


    /**
     * 
     * @param space
     * @param name
     * @param index
     * @return 
     */
    protected abstract E findChildNS(final String space, final String name,
                                     final int index);


    /**
     * 
     * @param space
     * @param name
     * @return 
     */
    protected abstract E addChildNS(final String space, final String name);


    /**
     * 
     * @param index
     * @param name
     * @return 
     * @see #child(int, java.lang.String, java.lang.String) 
     */
    public final ElementLocator<E> child(final int index, final String name) {

        return locateChildNS(null, name, index);
    }


    /**
     * 
     * @return 
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
     * 
     * @param locateParent
     * @return 
     */
    public final String getText(final boolean locateParent) {

        final String text = getText();

        if (locateParent) {
            locateParent();
        }

        return text;
    }


    /**
     * 
     * @param locateParent
     * @return 
     * @see #getText(boolean) 
     */
    public final String T(final boolean locateParent) {
        return getText(locateParent);
    }


    public abstract ElementLocator<E> setText(final String text);


    /**
     * 
     * @param text
     * @return 
     * @see #setText(java.lang.String) 
     */
    public final ElementLocator<E> T(final String text) {
        return setText(text);
    }


    /**
     * 
     * @param text
     * @param locateParent
     * @return 
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
     * 
     * @param text
     * @param locateParent
     * @return 
     * @see #setText(java.lang.String, boolean) 
     */
    public final ElementLocator<E> T(final String text,
                                     final boolean locateParent) {
        return setText(text, locateParent);
    }


    /**
     * 
     * @param name
     * @return 
     * @see #attribute(java.lang.String, java.lang.String) 
     */
    public abstract String getAttribute(final String name);


    /**
     * 
     * @param name
     * @param value
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
     * @return 
     */
    public abstract String getAttributeNS(final String space,
                                          final String name);


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
     * 
     */
    protected final E getCurrent() {
        return path.getLast();
    }


    /** element path. */
    private final LinkedList<E> path;
}
