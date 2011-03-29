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
import java.util.Arrays;
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
     * 
     * @param type item type
     */
    public ArrayBean(final Class<I> type) {
        super();

        if (type == null) {
            throw new IllegalArgumentException("null type");
        }

        this.type = type;

        list = new LinkedList<I>();
        index = -1;

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
     * @param newIndex new index
     */
    public void setIndex(final int newIndex) {

        if (newIndex < -1) {
            throw new IllegalArgumentException(
                "newIndex(" + newIndex + ") < -1");
        } else if (newIndex == -1) {
            if (!list.isEmpty()) {
                throw new IllegalArgumentException(
                    "newIndex(" + newIndex + ") for an empty items");
            }
        } else { // newIndex >= 0
            if (newIndex >= list.size()) {
                throw new IllegalArgumentException(
                    "newIndex(" + newIndex + ") >= items.length("
                    + list.size() + ")");
            }
        }

        final Object oldIndex = index;
        index = newIndex;
        firePropertyChange(PROPERTY_NAME_INDEX, oldIndex, index);
    }


    /**
     * Returns elements.
     *
     * @return elements
     */
    @SuppressWarnings("unchecked")
    public I[] getItems() {
        return list.toArray((I[]) Array.newInstance(type, list.size()));
    }


    /**
     * 
     * @param newItems 
     */
    public void setItems(final I[] newItems) {

        if (newItems == null) {
            throw new IllegalArgumentException("null newItemse");
        }

        final I[] oldItems = getItems();

        list.clear();
        list.addAll(Arrays.asList(newItems));

        if (index == -1) {
            if (!list.isEmpty()) {
                setIndex(0);
            }
        } else {
            if (index >= list.size()) {
                setIndex(list.size() - 1);
            }
        }

        firePropertyChange(PROPERTY_NAME_ITEMS, oldItems, getItems());
    }


    /**
     * 
     */
    public I getItemAtIndex() {
        return getItemAt(index);
    }


    /**
     * 
     */
    public I getItemAt(final int targetIndex) {
        return list.get(targetIndex);
    }


    public void swapItemAtIndexWith(final int targetIndex) {
        swapItemAtWith(index, targetIndex);
    }


    public void swapItemAtWithIndex(final int sourceIndex) {
        swapItemAtWith(sourceIndex, index);
    }


    public void swapItemAtWith(final int sourceIndex, final int targetIndex) {

        if (sourceIndex < 0) {
            throw new IllegalArgumentException(
                "sourceIndex(" + sourceIndex + ") < 0");
        }

        if (sourceIndex >= list.size()) {
            throw new IllegalArgumentException(
                "sourceIndex(" + sourceIndex + ") >= list.length(" + list.size()
                + ")");
        }

        if (targetIndex < 0) {
            throw new IllegalArgumentException(
                "targetIndex(" + targetIndex + ") < 0");
        }

        if (targetIndex >= list.size()) {
            throw new IllegalArgumentException(
                "targetIndex(" + targetIndex + ") >= list.length(" + list.size()
                + ")");
        }

        if (sourceIndex == targetIndex) {
            return;
        }

        final I[] oldItems = getItems();

        Collections.swap(list, sourceIndex, targetIndex);

        if (index == sourceIndex) {
            setIndex(targetIndex);
        } else if (index == targetIndex) {
            setIndex(sourceIndex);
        }

        firePropertyChange(PROPERTY_NAME_ITEMS, oldItems, getItems());
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
     * 
     * @param <E> item type
     * @param targetIndex insertion index
     * @param newItems new item
     */
    public <E extends I> void insertItemsAt(final int targetIndex,
                                            final E... newItems) {

        //insertItemsAt(targetIndex, Arrays.asList(newItems));

        if (targetIndex < 0) {
            throw new IllegalArgumentException(
                "targetIndex(" + targetIndex + ") < 0");
        }

        if (targetIndex > list.size()) {
            throw new IllegalArgumentException(
                "targetIndex(" + targetIndex + ") > items.length("
                + list.size() + ")");
        }

        if (newItems == null) {
            throw new IllegalArgumentException("null newItems");
        }

        final Class<?> componentType = newItems.getClass().getComponentType();
        if (!type.isAssignableFrom(componentType)) {
            throw new IllegalArgumentException(
                "newItems' componentType(" + componentType
                + ") is not assignable to " + type);
        }

        final I[] oldItems = getItems();

        for (int i = 0; i < newItems.length; i++) {
            list.add(targetIndex + i, newItems[i]);
        }

        if (index == -1) {
            setIndex(0);
        } else {
            if (index >= targetIndex) {
                setIndex(index + newItems.length);
            }
        }

        firePropertyChange(PROPERTY_NAME_ITEMS, oldItems, getItems());
    }


    /**
     * 
     * @param <E> item type
     * @param newItems new items
     */
    public <E extends I> void insertItemsAtFirst(final E... newItems) {

        insertItemsAt(0, newItems);
    }


    /**
     * 
     * @param <E> item type
     * @param newItems new item
     */
    public <E extends I> void insertItemsAtLast(final E... newItems) {

        insertItemsAt(list.size(), newItems);
    }


    /**
     * Deletes item at index.
     *
     * @return deleted item
     */
    public I deleteItemAtIndex() {
        return deleteItemAt(index);
    }


    /**
     * Deletes item at given <code>targetIndex</code>.
     *
     * @param targetIndex index to be removed
     * @return deleted item
     */
    public I deleteItemAt(final int targetIndex) {

        if (targetIndex < 0) {
            throw new IllegalArgumentException(
                "targetIndex(" + targetIndex + ") < 0");
        }

        if (targetIndex >= list.size()) {
            throw new IllegalArgumentException(
                "targetIndex(" + targetIndex + ") >= items.length"
                + list.size());
        }

        final I[] oldItems = getItems();

        final I removed = list.remove(targetIndex);

        if (index < targetIndex) {
            // do nothin'
        } else if (index == targetIndex) {
            if (index == list.size()) {
                setIndex(list.size() - 1);
            }
        } else { // index > targetIndex
            setIndex(index - 1);
        }

        pcs.firePropertyChange(PROPERTY_NAME_ITEMS, oldItems, getItems());

        return removed;
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
