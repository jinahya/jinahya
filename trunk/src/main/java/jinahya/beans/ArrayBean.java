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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <E>
 */
public class ArrayBean<E> {


    /** property name for index property. */
    public static final String PROPERTY_NAME_INDEX = "index";


    /** property name for elements property. */
    public static final String PROPERTY_NAME_ELEMENTS = "elements";


    /**
     * Policy for setting index to the first.
     */
    public static final int INDEX_FIRST = 0x01;


    /**
     * Policy for setting index to current.
     */
    public static final int INDEX_CURRENT = INDEX_FIRST << 1;


    /**
     * Policy for setting index to the last.
     */
    public static final int INDEX_LAST = INDEX_CURRENT << 1;


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
     * Creates an empty instance.
     *
     * @param type element type
     */
    @SuppressWarnings("unchecked")
    public ArrayBean(final Class<E> type) {
        this((E[]) Array.newInstance(type, 0));
    }


    /**
     * Creates an instance.
     *
     * @param elements elements
     */
    public ArrayBean(final E[] elements) {
        this(elements, elements.length == 0 ? -1 : 0);
    }


    /**
     * Creates an instance.
     *
     * @param elements elements
     * @param index index
     */
    public ArrayBean(final E[] elements, final int index) {
        super();

        if (elements == null) {
            throw new IllegalArgumentException("null elements");
        }

        if (elements.length == 0) {
            if (index != -1) {
                throw new IllegalArgumentException(
                    "illegal index(" + index + ") for elements.length("
                    + elements.length + ")");
            }
        } else {
            if ((index < 0 || index >= elements.length)) {
                throw new IllegalArgumentException(
                    "illegal index(" + index + ") for elements.length("
                    + elements.length + ")");
            }
        }

        this.elements = new LinkedList<E>();
        this.elements.addAll(Arrays.asList(elements));

        this.index = index;

        pcs = new PropertyChangeSupport(this);
    }


    /**
     * Returns current index value.
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }


    /**
     * Sets index.
     *
     * @param index new index
     */
    public void setIndex(final int index) {

        if (elements.isEmpty()) {
            if (index != -1) {
                throw new IllegalArgumentException(
                    "illegal index(" + index + ") for empty elements");
            }
        } else {
            if (index < 0 || index >= elements.size()) {
                throw new IllegalArgumentException(
                    "illegal index(" + index + ") for elements.length("
                    + elements.size() + ")");
            }
        }

        final Object oldIndex = this.index;
        this.index = index;
        pcs.firePropertyChange(PROPERTY_NAME_INDEX, oldIndex, this.index);
    }


    /**
     * Returns elements.
     *
     * @return elements
     */
    @SuppressWarnings("unchecked")
    public E[] getElements() {
        return (E[]) elements.toArray();
    }


    /**
     * Returns element at given <code>index</code>.
     *
     * @param index element index
     * @return element at <code>index</code>
     */
    public E getElementAt(final int index) {
        return elements.get(index);
    }


    /**
     * Returns elements' length.
     *
     * @return elements' length
     */
    public int getLength() {
        return elements.size();
    }


    /**
     * Removes element at given <code>index</code>.
     *
     * @param index index to be removed
     * @return removed element
     */
    public E removeElementAt(final int index) {

        final E[] oldElements = getElements();

        final E removed = elements.remove(index); // IOOBE

        if (this.index >= elements.size()) {
            setIndex(elements.size() - 1);
        }

        pcs.firePropertyChange(
            PROPERTY_NAME_ELEMENTS, oldElements, getElements());

        return removed;
    }


    /**
     * Add given <code>element</code> at specified <code>index</code>.
     *
     * @param index index
     * @param element element
     */
    public void addElementAt(final int index, final E element) {

        if (element == null) {
            throw new IllegalArgumentException("null element");
        }

        final E[] oldValue = getElements();

        elements.add(index, element);

        pcs.firePropertyChange(PROPERTY_NAME_ELEMENTS, oldValue, getElements());
    }


    /**
     * Sets given <code>newElements</code> with false for honorCurrentIndex.
     *
     * @param newElements new elements
     */
    public <T extends E> void setElements(final T[] newElements) {

        if (newElements == null) {
            throw new IllegalArgumentException("null newElements");
        }

        setElements(newElements, INDEX_FIRST);
    }


    /**
     * Sets given <code>newElements</code>.
     *
     * @param newElements new elements
     * @param indexPolicy index policy
     */
    public <T extends E> void setElements(final T[] newElements,
                                          final int indexPolicy) {

        if (newElements == null) {
            throw new IllegalArgumentException("null newElements");
        }

        setElements(Arrays.asList(newElements), indexPolicy);
    }


    /**
     * Sets given <code>newElements</code>.
     *
     * @param newElements new elements
     * @param indexPolicy honor-current-index flag
     */
    private void setElements(final Collection<? extends E> newElements,
                             final int indexPolicy) {

        if (newElements == null) {
            throw new IllegalArgumentException("null newElements");
        }

        final E[] oldElements = getElements();

        elements.clear();
        elements.addAll(newElements);

        if (elements.isEmpty()) {
            setIndex(-1);
        } else {
            switch (indexPolicy) {
                case INDEX_FIRST:
                    setIndex(0);
                    break;
                case INDEX_CURRENT:
                    if (index == -1) {
                        setIndex(0);
                    } else {
                        if (index >= elements.size()) {
                            setIndex(elements.size() - 1);
                        }
                    }
                    break;
                case INDEX_LAST:
                    setIndex(elements.size() - 1);
                    break;
                default:
                    throw new IllegalArgumentException(
                        "unknown index policy: " + indexPolicy);
                    //break;
            }
        }

        pcs.firePropertyChange(
            PROPERTY_NAME_ELEMENTS, oldElements, getElements());
    }

    
    /**
     * Copy alements to given <code>bean</code>.
     *
     * @param bean target bean.
     * @param filter element filter. can be null.
     * @param indexPolicy index policy
     */
    public void copyTo(final ArrayBean<? super E> bean,
                       final ElementFilter<E> filter, final int indexPolicy) {

        if (filter == null || elements.isEmpty()) {
            bean.setElements(elements, indexPolicy);
            return;
        }

        final Collection<E> newElements = new ArrayList<E>();
        for (E element : elements) {
            if (filter.filter(element)) {
                newElements.add(element);
            }
        }
        bean.setElements(newElements, indexPolicy);
    }


    /**
     * Copy elements from given <code>bean</code>.
     *
     * @param <T> source bean's element type
     * @param bean source bean
     * @param filter element filter. can be null.
     * @param indexPolicy honor-current-index flag
     */
    public <T extends E> void copyFrom(final ArrayBean<T> bean,
                                       final ElementFilter<T> filter,
                                       final int indexPolicy) {

        if (filter == null || bean.elements.isEmpty()) {
            setElements(bean.elements, indexPolicy);
            return;
        }

        final Collection<T> newElements = new ArrayList<T>();
        for (T element : bean.elements) {
            if (filter.filter(element)) {
                newElements.add(element);
            }
        }
        setElements(newElements, indexPolicy);
    }


    /**
     * Decreases index value.
     *
     * @param roll roll-to-the-last flag
     * @return true if decreased, false otherwise
     */
    public boolean decreaseIndex(final boolean roll) {

        if (elements.size() <= 1) {
            return false;
        }

        if (index == 0) { // first
            if (roll) {
                setIndex(elements.size() - 1);
                return true;
            }
        } else { // index > 0
            setIndex(index - 1);
            return true;
        }

        return false;
    }


    /**
     * Increases index value.
     *
     * @param roll roll-to-the-first flag
     * @return true if increased, false otherwise.
     */
    public boolean increaseIndex(final boolean roll) {

        if (elements.size() <= 1) {
            return false;
        }

        if (index == elements.size() - 1) { // last
            if (roll) {
                setIndex(0);
                return true;
            }
        } else { // index < elements.size() - 1
            setIndex(index + 1);
            return true;
        }

        return false;
    }


    /**
     * Adds given <code>listener</code> to this bean.
     *
     * @param listener listener to be added.
     */
    public void addPropertyChangeListener(
        final PropertyChangeListener listener) {

        pcs.addPropertyChangeListener(listener);
    }


    /**
     * Removes given <code>listener</code> from this bean.
     *
     * @param listener listener to be removed.
     */
    public void removePropertyChangeListener(
        final PropertyChangeListener listener) {

        pcs.removePropertyChangeListener(listener);
    }


    /**
     * Clear this bean.
     */
    public void clear() {
        final E[] oldElements = getElements();
        elements.clear();
        setIndex(-1);
        pcs.firePropertyChange(PROPERTY_NAME_ELEMENTS, oldElements,
                               getElements());
    }


    /**
     * Fire a property change event.
     *
     * @param propertyName property name
     * @param oldValue old value
     * @param newValue new value
     */
    protected void firePropertyChange(final String propertyName,
                                      final Object oldValue,
                                      final Object newValue) {

        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }


    /**
     *
     * @param propertyName
     * @param index
     * @param oldValue
     * @param newValue
     */
    protected void fireIndexedPropertyChange(final String propertyName,
                                             final int index,
                                             final Object oldValue,
                                             final Object newValue) {

        try {
            final Method method = PropertyChangeSupport.class.getMethod(
                "fireIndexedPropertyChange", new Class<?>[]{
                    String.class, Integer.TYPE, Object.class, Object.class});
            try {
                method.invoke(pcs, new Object[]{
                        propertyName, index, oldValue, newValue});
            } catch (IllegalAccessException iae) {
                iae.printStackTrace(System.err);
            } catch (InvocationTargetException ite) {
                ite.printStackTrace(System.err);
            }
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace(System.err);
        }
    }


    private final List<E> elements;
    private int index;

    private final PropertyChangeSupport pcs;
}
