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
     *
     */
    public static final int INDEX_FIRST = 0x01;


    /**
     * 
     */
    public static final int INDEX_CURRENT = INDEX_FIRST << 1;


    /**
     * 
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


    /*
     *
     * @param type
    @SuppressWarnings("unchecked")
    public ArrayBean(final Class<E> type) {
        this(type, (E[]) Array.newInstance(type, 0), -1);
    }
     */


    /*
     *
     * @param type
     * @param elements
    public ArrayBean(final Class<E> type, final E[] elements) {
        this(type, elements, elements.length == 0 ? -1 : 0);
    }
     */


    /*
     *
     * @param type
     * @param elements
     * @param index
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

        if (elements.length == 0 && index != -1) {
            throw new IllegalArgumentException(
                "illegal index(" + index + ") for elements.length("
                + elements.length + ")");
        }

        if (elements.length > 0
            && (index < 0 || index >= elements.length)) {
            throw new IllegalArgumentException(
                "illegal index(" + index + ") for elements.length("
                + elements.length + ")");
        }

        this.type = type;
        this.elements = new LinkedList<E>();
        this.index = index;

        pcs = new PropertyChangeSupport(this);
    }
     */


    /**
     * 
     * @param elements
     * @param index
     */
    public ArrayBean(final E[] elements) {
        this(elements, elements.length == 0 ? -1 : 0);
    }


    /**
     * 
     * @param type
     * @param elements
     * @param index
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

        //this.type = type;
        this.elements = new LinkedList<E>();
        this.index = index;

        pcs = new PropertyChangeSupport(this);
    }


    /**
     * Returns current index value.
     *
     * @return index
     */
    public final int getIndex() {
        return index;
    }


    /**
     * Sets index.
     *
     * @param newIndex new index
     */
    public final void setIndex(final int newIndex) {

        if (elements.isEmpty()) {
            if (newIndex != -1) {
                throw new IllegalArgumentException(
                    "illegal index(" + newIndex + ") for empty elements");
            }
        } else {
            if (newIndex < 0 || newIndex >= elements.size()) {
                throw new IllegalArgumentException(
                    "illegal index(" + newIndex + ") for elements.length("
                    + elements.size() + ")");
            }
        }

        final Object oldIndex = index;
        index = newIndex;
        pcs.firePropertyChange(PROPERTY_NAME_INDEX, oldIndex, index);
    }


    /**
     * Returns elements.
     *
     * @return elements
     */
    @SuppressWarnings("unchecked")
    public E[] getElements() {
        return (E[]) elements.toArray();
        //return elements.toArray((E[]) Array.newInstance(type, elements.size()));
    }


    /**
     * Returns element at given <code>index</code>.
     *
     * @param index element index
     * @return element at <code>index</code>
     */
    public final E getElementAt(final int index) {
        return elements.get(index);
    }


    /**
     * Returns currently indexed element.
     *
     * @return indexed element
     * @see #getElementAt(int)
     */
    public final E getElementAtIndex() {
        return getElementAt(index);
    }


    /**
     * Returns elements' size.
     *
     * @return elements' size
     */
    public final int getSize() {
        return elements.size();
    }


    /**
     * Removes element at given <code>index</code>.
     *
     * @param index index to be removed
     * @return removed element
     */
    public final E removeElementAt(final int index) {

        final E[] oldValue = getElements();

        final E removed = elements.remove(index); // IOOBE

        if (this.index >= elements.size()) {
            setIndex(elements.size() - 1);
        }

        firePropertyChangeForElements(oldValue);

        return removed;
    }


    /**
     * Removes currently indexed element.
     *
     * @return remove element.
     * @see #removeElementAt(int)
     */
    public final E removeElementAtIndex() {
        return removeElementAt(index);
    }


    /**
     * Sets given <code>newElements</code> with false for honorCurrentIndex.
     *
     * @param newElements new elements
     */
    public void setElements(final E[] newElements) {

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
    public void setElements(final E[] newElements, final int indexPolicy) {

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
    public void setElements(final Collection<? extends E> newElements,
                            final int indexPolicy) {

        if (newElements == null) {
            throw new IllegalArgumentException("null newElements");
        }

        final E[] oldValue = getElements();

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

        firePropertyChangeForElements(oldValue);
    }


    /**
     * Copy alements to given <code>bean</code>.
     *
     * @param bean target bean.
     * @param filter element filter. can be null.
     * @param indexPolicy index policy
     */
    public final void copyTo(final ArrayBean<? super E> bean,
                             final ElementFilter<E> filter,
                             final int indexPolicy) {

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
    public final <T extends E> void copyFrom(final ArrayBean<T> bean,
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
    public final boolean decreaseIndex(final boolean roll) {

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
    public final boolean increaseIndex(final boolean roll) {

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
    public final void addPropertyChangeListener(
        final PropertyChangeListener listener) {

        pcs.addPropertyChangeListener(listener);
    }


    /**
     * Removes given <code>listener</code> from this bean.
     *
     * @param listener listener to be removed.
     */
    public final void removePropertyChangeListener(
        final PropertyChangeListener listener) {

        pcs.removePropertyChangeListener(listener);
    }


    /**
     * Clear this bean.
     */
    public final void clear() {
        final E[] oldValue = getElements();
        elements.clear();
        setIndex(-1);
        firePropertyChangeForElements(oldValue);
    }


    /**
     * Fires property change for {@link #PROPERTY_NAME_ELEMENTS}.
     *
     * @param oldValue old value
     */
    private void firePropertyChangeForElements(final E[] oldValue) {
        firePropertyChange(PROPERTY_NAME_ELEMENTS, oldValue, getElements());
    }


    /**
     *
     * @param oldIndex
     */
    private void firePropertyChangeForIndex(final Object oldIndex) {
        this.firePropertyChange(PROPERTY_NAME_INDEX, oldIndex, index);
    }


    /**
     * Fire a property change event.
     *
     * @param propertyName property name
     * @param oldValue old value
     * @param newValue new value
     */
    protected final void firePropertyChange(final String propertyName,
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
    protected final void fireIndexedPropertyChange(final String propertyName,
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


    //private final Class<E> type;

    private final List<E> elements;
    private int index;

    private final PropertyChangeSupport pcs;
}
