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

package jinahya.beans;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ArrayBean<E> {


    /** property name for index property. */
    public static final String PROPERTY_NAME_INDEX = "index";


    /** property name for elements property. */
    public static final String PROPERTY_NAME_ELEMENTS = "elements";


    /**
     *
     * @param <E>
     */
    public static interface ElementFilter<E> {


        /**
         * Check whether given <code>element</code> is fine or not.
         *
         * @param element element to be checked
         * @return true if <code>element</code> is ok, false otherwise.
         */
        boolean filter(E element);
    }


    /**
     *
     * @param type
     */
    @SuppressWarnings("unchecked")
    public ArrayBean(final Class<E> type) {
        this(type, (E[]) Array.newInstance(type, 0), -1);
    }


    /**
     *
     * @param type
     * @param elements
     */
    public ArrayBean(final Class<E> type, final E[] elements) {
        this(type, elements, elements.length == 0 ? -1 : 0);
    }


    /**
     *
     * @param type
     * @param elements
     * @param index
     */
    public ArrayBean(final Class<E> type, final E[] elements, final int index) {
        super();


        if (type == null) {
            throw new IllegalArgumentException("null type");
        }

        if (elements == null) {
            throw new IllegalArgumentException("null elements");
        }

        if (!type.isAssignableFrom(elements.getClass().getComponentType())) {
            throw new IllegalArgumentException(
                "elements is not assignable to type");
        }

        if ((elements.length == 0 && index != -1)
            || (index < 0 && index >= elements.length)) {

            throw new IllegalArgumentException(
                "illegal index(" + index + ") for elements.length("
                + elements.length + ")");
        }

        this.type = type;
        this.elements = elements;
        this.index = index;

        pcs = new PropertyChangeSupport(this);
    }


    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }


    /**
     *
     * @param newIndex
     */
    public void setIndex(final int newIndex) {

        if (elements.length == 0 && newIndex != -1) {
            throw new IllegalArgumentException(
                "negative index(" + newIndex + ")");
        }

        if (newIndex >= elements.length) {
            throw new ArrayIndexOutOfBoundsException(
                "index(" + newIndex + ") >= elements.length(" + elements.length
                + ")");
        }

        final Object oldIndex = index;
        index = newIndex;
        pcs.firePropertyChange(PROPERTY_NAME_INDEX, oldIndex, index);
    }


    /**
     *
     * @return
     */
    public E[] getElements() {
        return elements;
    }


    /**
     *
     * @param index
     * @return
     */
    public E getElementAt(final int index) {

        if (index < 0) {
            throw new IllegalArgumentException("negative index");
        }

        if (index >= elements.length) {
            throw new ArrayIndexOutOfBoundsException(
                "index(" + index + ") >= elements.length(" + elements.length
                + ")");
        }

        return elements[index];
    }

    /**
     *
     * @param newElements
     */
    public void setElements(final E[] newElements) {

        if (newElements == null) {
            throw new IllegalArgumentException("null newElements");
        }

        setElements(newElements, false);
    }


    /**
     *
     * @param newElements
     * @param indexToLast
     */
    public void setElements(final E[] newElements, final boolean indexToLast) {

        if (newElements == null) {
            throw new IllegalArgumentException("null newElements");
        }

        final Object oldValue = elements;

        elements = newElements;

        if (elements.length == 0) {
            setIndex(-1);
        } else {
            setIndex(indexToLast ? elements.length - 1 : 0);
        }

        pcs.firePropertyChange(PROPERTY_NAME_ELEMENTS, oldValue, elements);
    }


    /**
     * Copy all alements to given <code>model</code>.
     * Specified <code>model</code> will be cleared first.
     *
     * @param bean model to copy
     * @param filter filter to be used or null.
     */
    public void copyTo(final ArrayBean<? super E> bean,
                       final ElementFilter<E> filter) {

        if (filter == null || elements.length == 0) {
            bean.setElements(elements);
            return;
        }

        final Collection<E> newElementList = new ArrayList<E>();

        for (E element : elements) {
            if (filter.filter(element)) {
                newElementList.add(element);
            }
        }

        @SuppressWarnings("unchecked")
        final E[] newElements = (E[]) Array.newInstance(
            type, newElementList.size());
        newElementList.toArray(newElements);

        bean.setElements(newElements);
    }


    /**
     *
     * @param <T>
     * @param bean
     * @param filter
     */
    public <T extends E> void copyFrom(final ArrayBean<T> bean,
                                       final ElementFilter<T> filter) {

        if (filter == null || elements.length == 0) {
            setElements(bean.elements);
            return;
        }

        final Collection<T> newElementList = new ArrayList<T>();

        for (T element : bean.elements) {
            if (filter.filter(element)) {
                newElementList.add(element);
            }
        }

        @SuppressWarnings("unchecked")
        final E[] newElements = (E[]) Array.newInstance(
            type, newElementList.size());
        newElementList.toArray(newElements);

        setElements(newElements);
    }


    /**
     *
     * @param roll
     */
    public boolean decreaseIndex(final boolean roll) {

        if (index == -1) {
            return false;
        }

        if (index > 0) {
            setIndex(index - 1);
        } else {
            if (!roll) {
                return false;
            }
            setIndex(elements.length - 1);
        }

        return true;
    }


    /**
     *
     * @param roll
     */
    public boolean increaseIndex(final boolean roll) {

        if (index == -1) {
            return false;
        }

        if (index < elements.length - 1) {
            setIndex(index + 1);
        } else {
            if (!roll) {
                return false;
            }
            setIndex(0);
        }

        return true;
    }


    /**
     *
     * @param listener
     */
    public void addPropertyChangeListener(
        final PropertyChangeListener listener) {

        pcs.addPropertyChangeListener(listener);
    }


    /**
     *
     * @param listener
     */
    public void removePropertyChangeListener(
        final PropertyChangeListener listener) {

        pcs.removePropertyChangeListener(listener);
    }


    /**
     *
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        setElements((E[]) Array.newInstance(type, 0));
    }


    private final Class<E> type;

    private E[] elements;
    private int index;

    private final PropertyChangeSupport pcs;
}
