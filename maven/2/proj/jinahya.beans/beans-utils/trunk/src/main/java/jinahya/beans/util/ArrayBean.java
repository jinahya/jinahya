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

package jinahya.beans.util;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ArrayBean<E> {


    /** property name for index property. */
    public static final String PROPERTY_NAME_INDEX = "index";


    /** property name for elements property. */
    public static final String PROPERTY_NAME_ELEMENTS = "elements";


    /** default arraylist's capacity. */
    private static final int DEFAULT_INITIAL_CAPACITY = 10;


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
     * @param elementType element type
     */
    public ArrayBean(final Class<E> elementType) {
        this(elementType, new ArrayList<E>(DEFAULT_INITIAL_CAPACITY));
    }


    /**
     *
     * @param elementType element type
     * @param elementList element list
     */
    public ArrayBean(final Class<E> elementType,
                        final List<E> elementList) {
        super();

        this.elementType = elementType;
        this.elements = elementList;
        index = elements.isEmpty() ? -1 : 0;

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

        if (index != -1 && index >= elements.size()) {
            throw new ArrayIndexOutOfBoundsException(
                newIndex + " >= " + elements.size());
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
        @SuppressWarnings("unchecked")
        final E[] array = (E[]) Array.newInstance(elementType, elements.size());
        return elements.toArray(array);
    }


    /**
     *
     * @param newElements
     */
    public void setElements(final E[] newElements) {

        if (newElements == null) {
            throw new IllegalArgumentException("null elements");
        }

        setElements(Arrays.asList(newElements));
    }


    /**
     *
     * @param newElements
     */
    public void setElements(final Collection<? extends E> newElements) {

        if (newElements == null) {
            throw new IllegalArgumentException("null elements");
        }

        final E[] oldValue = getElements();
        setIndex(-1);
        elements.clear();

        elements.addAll(newElements);

        setIndex(elements.isEmpty() ? -1 : 0);

        pcs.firePropertyChange(PROPERTY_NAME_ELEMENTS, oldValue, getElements());
    }


    /**
     * Copy all alements to given <code>model</code>.
     * Specified <code>model</code> will be cleared first.
     *
     * @param array model to copy
     * @param filter filter to be used or null.
     */
    public void copyTo(final ArrayBean<? super E> array,
                       final ElementFilter<E> filter) {

        final Collection<E> newElements = new ArrayList<E>();

        for (E element : elements) {
            if (filter == null || filter.filter(element)) {
                newElements.add(element);
            }
        }

        array.setElements(newElements);
    }


    /**
     *
     * @param <T>
     * @param array
     * @param filter
     */
    public <T extends E> void copyFrom(final ArrayBean<T> array,
                                       final ElementFilter<T> filter) {


        final Collection<T> newElements = new ArrayList<T>();

        for (T element : array.elements) {
            if (filter == null || filter.filter(element)) {
                newElements.add(element);
            }
        }

        setElements(newElements);
    }


    /**
     *
     * @param roll
     */
    public void decreaseIndex(final boolean roll) {

        if (index == -1) {
            return;
        }

        if (index > 0) {
            setIndex(index - 1);
        } else if (roll) {
            setIndex(elements.size() - 1);
        }
    }


    /**
     *
     * @param roll
     */
    public void increaseIndex(final boolean roll) {

        if (index == -1) {
            return;
        }

        if (index < elements.size() - 1) {
            setIndex(index + 1);
        } else if (roll) {
            setIndex(0);
        }
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



    private final Class<E> elementType;
    private final List<E> elements;
    private int index = -1;

    private final PropertyChangeSupport pcs;
}
