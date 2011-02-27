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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <I> element type
 */
public class ArrayBean<I> {


    /** property name for index property. */
    public static final String PROPERTY_NAME_INDEX = "index";


    /** property name for elements property. */
    public static final String PROPERTY_NAME_ITEMS = "items";


    /**
     * Policy for setting index to the first.
     */
    public static final int POLICY_INDEX_FIRST = 0x01;


    /**
     * Policy for setting index to current.
     */
    public static final int POLICY_INDEX_CURRENT = POLICY_INDEX_FIRST << 1;


    /**
     * Policy for setting index to the last.
     */
    public static final int POLICY_INDEX_LAST = POLICY_INDEX_CURRENT << 1;


    /**
     *
     * @param <I>
     */
    public static interface Filter<I> {


        /**
         * Check whether given <code>item</code> is fine or not.
         *
         * @param item item to be checked
         * @return true if <code>item</code> is ok, false otherwise.
         */
        boolean accept(I item);
    }


    /**
     * 
     * @param <I> 
     */
    public static interface Manipulator<I> {


        /**
         * 
         * @param items items to manipulate
         * @return new index
         */
        int manipulate(List<I> list);
    }


    /**
     * Creates an instance.
     *
     * @param type item type
     */
    public ArrayBean(final Class<I> type) {
        super();

        if (type == null) {
            throw new IllegalArgumentException("null type");
        }

        this.type = type;

        list = Collections.synchronizedList(new LinkedList<I>());
        index = -1;

        pcs = new PropertyChangeSupport(this);
    }


    /**
     * 
     * @param filter 
     */
    public void filter(final Filter<I> filter) {

        if (filter == null) {
            throw new IllegalArgumentException("null filter");
        }

        synchronized (list) {

            final int oldIndex = index;
            final I[] oldItems = getItems();

            for (int i = list.size() - 1; i >= 0; i--) {
                if (filter.accept(list.get(i))) {
                    continue;
                }
                if (index < i) {
                    // ok.
                } else if (index == i) {
                    if (list.size() - 1 == index) {
                        index--;
                    }
                } else { // index > i
                    index --;
                }
                list.remove(i);
            }

            firePropertyChange(PROPERTY_NAME_INDEX, oldIndex, index);
            firePropertyChange(PROPERTY_NAME_ITEMS, oldItems, getItems());
        }
    }


    public void manipulate(final Manipulator<I> manipulator) {

        if (manipulator == null) {
            throw new IllegalArgumentException("null manipulator");
        }

        final List<I> mlist = new LinkedList<I>(list);
        final int mindex = manipulator.manipulate(mlist);
        if ((mlist.isEmpty() && mindex != -1) || (mlist.size() <= mindex)) {
            throw new RuntimeException(
                "wrong manipulation: index(" + mindex + ") for length("
                + mlist.size() + ")");
        }

        synchronized (list) {
        }
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

        if ((list.isEmpty() && index != -1) || (list.size() <= index)) {
            throw new IllegalArgumentException(
                "wrong index(" + index + ") for length(" + getLength() + ")");
        }

        final Object oldIndex = this.index;
        this.index = index;
        firePropertyChange(PROPERTY_NAME_INDEX, oldIndex, this.index);
    }


    /**
     * Returns elements.
     *
     * @return elements
     */
    public I[] getItems() {
        return list.toArray((I[]) Array.newInstance(type, list.size()));
    }


    /**
     * Returns element at given <code>index</code>.
     *
     * @param index element index
     * @return item at <code>index</code>
     */
    public I getItemAt(final int index) {
        return list.get(index);
    }


    /**
     * Returns items' length.
     *
     * @return items' length
     */
    public int getLength() {
        return list.size();
    }


    /**
     * Removes element at given <code>index</code>.
     *
     * @param index index to be removed
     * @return removed element
     */
    public I removeItemAt(final int index) {

        final I[] oldItems = getItems();

        final I removed = list.remove(index); // IOOBE

        if (this.index >= list.size()) {
            setIndex(list.size() - 1);
        }

        pcs.firePropertyChange(PROPERTY_NAME_ITEMS, oldItems, getItems());

        return removed;
    }


    public boolean remove(final I item) {

        final boolean result = list.remove(item);

        if (result && (index >= list.size())) {
            setIndex(list.size() - 1);
        }

        return result;
    }


    /**
     * Add given <code>element</code> at specified <code>index</code>.
     *
     * @param index index
     * @param item item to add
     */
    public <E extends I> void add(final int index, final E item) {

        if (item == null) {
            throw new IllegalArgumentException("null element");
        }

        final I[] oldValue = getItems();

        list.add(index, item);

        pcs.firePropertyChange(PROPERTY_NAME_ITEMS, oldValue, getItems());
    }


    /**
     * Sets given <code>newElements</code> with false for honorCurrentIndex.
     *
     * @param items new elements
     */
    public <T extends I> void items(final T[] items) {

        if (items == null) {
            throw new IllegalArgumentException("null items");
        }

        setItems(items, POLICY_INDEX_FIRST);
    }


    /**
     * Sets given <code>newElements</code>.
     *
     * @param items new elements
     * @param policy index policy
     */
    public <T extends I> void setItems(final T[] items, final int policy) {

        if (items == null) {
            throw new IllegalArgumentException("null items");
        }

        setItems(Arrays.asList(items), policy);
    }


    /**
     * Sets given <code>newElements</code>.
     *
     * @param items new elements
     * @param policy honor-current-index flag
     */
    private void setItems(final Collection<? extends I> items,
                          final int policy) {

        if (items == null) {
            throw new IllegalArgumentException("null newElements");
        }

        synchronized (list) {

            final I[] oldItems = getItems();

            list.clear();
            list.addAll(items);

            if (list.isEmpty()) {
                setIndex(-1);
            } else {
                switch (policy) {
                    case POLICY_INDEX_FIRST:
                        setIndex(0);
                        break;
                    case POLICY_INDEX_CURRENT:
                        if (index == -1) {
                            setIndex(0);
                        } else {
                            if (index >= items.size()) {
                                setIndex(items.size() - 1);
                            }
                        }
                        break;
                    case POLICY_INDEX_LAST:
                        setIndex(items.size() - 1);
                        break;
                    default:
                        break;
                }
            }

            firePropertyChange(PROPERTY_NAME_ITEMS, oldItems, getItems());
        }
    }


    /**
     * Copy alements to given <code>bean</code>.
     *
     * @param bean target bean.
     * @param filter element filter. can be null.
     * @param indexPolicy index policy
     */
    public void copyTo(final ArrayBean<? super I> bean,
                       final Filter<I> filter, final int indexPolicy) {

        if (filter == null || list.isEmpty()) {
            bean.setItems(list, indexPolicy);
            return;
        }

        final Collection<I> newElements = new ArrayList<I>();
        for (I element : list) {
            if (filter.accept(element)) {
                newElements.add(element);
            }
        }
        bean.setItems(newElements, indexPolicy);
    }


    /**
     * Copy elements from given <code>bean</code>.
     *
     * @param <T> source bean's element type
     * @param bean source bean
     * @param filter element filter. can be null.
     * @param indexPolicy honor-current-index flag
     */
    public <T extends I> void copyFrom(final ArrayBean<T> bean,
                                       final Filter<T> filter,
                                       final int indexPolicy) {

        if (filter == null || bean.list.isEmpty()) {
            setItems(bean.list, indexPolicy);
            return;
        }

        final Collection<T> newElements = new ArrayList<T>();
        for (T element : bean.list) {
            if (filter.accept(element)) {
                newElements.add(element);
            }
        }
        setItems(newElements, indexPolicy);
    }


    /**
     * Decreases index value.
     *
     * @param roll roll-to-the-last flag
     * @return true if decreased, false otherwise
     */
    public boolean decreaseIndex(final boolean roll) {

        if (list.size() <= 1) {
            return false;
        }

        if (index == 0) { // first
            if (roll) {
                setIndex(list.size() - 1);
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

        if (list.size() <= 1) {
            return false;
        }

        if (index == list.size() - 1) { // last
            if (roll) {
                setIndex(0);
                return true;
            }
        } else {
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

        if (listener == null) {
            throw new IllegalArgumentException("null listener");
        }

        pcs.addPropertyChangeListener(listener);
    }


    /**
     * Removes given <code>listener</code> from this bean.
     *
     * @param listener listener to be removed.
     */
    public void removePropertyChangeListener(
        final PropertyChangeListener listener) {

        if (listener == null) {
            throw new IllegalArgumentException("null listener");
        }

        pcs.removePropertyChangeListener(listener);
    }


    /**
     * Clear this bean.
     */
    public void clear() {

        final I[] oldElements = getItems();

        list.clear();

        setIndex(-1);

        firePropertyChange(PROPERTY_NAME_ITEMS, oldElements, getItems());
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


    /**
     * Returns element type.
     * @return element type
     */
    protected Class<I> getType() {
        return type;
    }


    private final Class<I> type;


    private final List<I> list;


    private int index = -1;


    private final PropertyChangeSupport pcs;
}
