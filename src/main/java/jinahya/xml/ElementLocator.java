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
     * @param name
     * @param space
     * @return 
     */
    public abstract int count(final String name, final String space);


    /**
     * 
     * @param name
     * @return 
     * @see #getCount(java.lang.String, java.lang.String)
     */
    public final int count(final String name) {
        return count(name, null);
    }


    /**
     * Locate to the root.
     *
     * @return self
     */
    public final ElementLocator<E> root() {

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
        return root();
    }


    /**
     * Locate to the parent of the current element.
     *
     * @return self
     */
    public final ElementLocator<E> parent() {

        if (path.size() > 1) {
            path.removeLast();
        }

        return this;
    }


    /**
     * @see #locateParent()
     */
    public final ElementLocator<E> P() {
        return parent();
    }


    /**
     * 
     * @param index
     * @param name
     * @param space
     * @return 
     */
    public final ElementLocator<E> child(final int index, final String name,
                                         final String space) {

        if (index < 0) {
            throw new IllegalArgumentException("index(" + index + ") < 0");
        }

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        final E located = locate(index, name, space);

        path.add(located);

        return this;
    }


    /**
     * 
     */
    protected abstract E locate(final int index, final String name,
                                final String space);


    /**
     * 
     * @param index
     * @param name
     * @param space
     * @return 
     * @see #child(int, java.lang.String, java.lang.String) 
     */
    public final ElementLocator<E> C(final int index, final String name,
                                     final String space) {

        return child(index, name, space);
    }


    /**
     * 
     * @param index
     * @param name
     * @return 
     * @see #child(int, java.lang.String, java.lang.String) 
     */
    public final ElementLocator<E> child(final int index, final String name) {

        return child(index, name, null);
    }


    /**
     * 
     * @param index
     * @param name
     * @return 
     * @see #child(int, java.lang.String) 
     */
    public final ElementLocator<E> C(final int index, final String name) {
        return child(index, name);
    }


    /*
     * 
     * @param name
     * @param space
     * @return 
    public abstract ElementLocator<E> child(final String name,
    final String space);
     */
    /*
     * 
     * @param name
     * @param space
     * @return 
     * @see #child(java.lang.String, java.lang.String) 
    public final ElementLocator<E> C(final String name, final String space) {
    return child(name, space);
    }
     */
    /**
     * 
     * @return 
     */
    public abstract String text();


    /**
     * @return
     * @see #text() 
     */
    public final String T() {
        return text();
    }


    /**a
     * 
     * @param name
     * @return 
     * @see #attribute(java.lang.String, java.lang.String) 
     */
    public abstract String attribute(final String name);


    /*
     * 
     * @param name
     * @param value
    public abstract void setAttribute(final String name, final String value);
     */


    /**
     * 
     * @param name
     * @param space
     * @return 
     */
    public abstract String attribute(final String name, final String space);


    /*
     * 
     * @param space
     * @param name
     * @param value
    public abstract void setAttributeNS(final String space, final String name,
                                        final String value);
     */


    /**
     * 
     */
    protected final E current() {
        return path.getLast();
    }


    /** element path. */
    private final LinkedList<E> path;
}
