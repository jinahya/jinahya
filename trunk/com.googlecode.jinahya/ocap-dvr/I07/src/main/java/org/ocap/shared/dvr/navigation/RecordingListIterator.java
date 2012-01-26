package org.ocap.shared.dvr.navigation;

import org.ocap.shared.dvr.RecordingRequest;

/**
 * This iterator could be used to traverse entries in a RecordingList.
 * An iterator for lists that allows the programmer to traverse the list in either
 * direction and to obtain the iterator's current position in the list. A
 * RecordingListIterator has no current element; its cursor position always lies
 * between the element that would be returned by a call to previousEntry(),
 * previousEntries(n), nextEntry(),and nextEntries(n), where each traverse
 * backward by 1, n, and forward by 1, n respectively. In a list of length n, there are N+1
 * valid index values, from 0 to n, inclusive. 
 *<pre>
 *        Element(0)   Element(1)   Element(2)   ... Element(n)   
 *        ^            ^            ^            ^               ^
 * Index: 0            1            2            3               N+1
 *</pre>
 * Total failure to traverse the list shall not change the position. 
 * When a request to traverses by n entries would result in going beyond the list, it
 * shall traverse as much possible and change the position accordingly. The orders of
 * the entries in the array returned by both nextEntries(n) or previousEntries(n) 
 * shall be the same the order maintained within the implementation.
 */
public interface RecordingListIterator
{

    /**
     * Resets the iterator to the beginning of the list, such that
     * <code>hasPrevious()</code> returns <code>false</code> and
     * <code>nextEntry()</code> returns the first
     * <code>RecordingRequest</code> in the list (if the list is not empty).
     */
    public void toBeginning();

    /**
     * Sets the iterator to the end of the list, such that
     * <code>hasNext()</code> returns <code>false</code> and
     * <code>previousEntry()</code> returns the last
     * <code>RecordingRequest</code> in the list (if the list is not empty).
     */
    public void toEnd();

    /**
     * Gets the next <code>RecordingRequest</code> object in the list.
     * This method may be called repeatedly to iterate through the list.
     *
     * @return The <code>RecordingRequest</code> object at the next
     *      position in the list.
     *
     * @throws java.util.NoSuchElementException If the
     *      iteration has no next <code>RecordingRequest</code>.
     */
    public RecordingRequest nextEntry();

    /**
     * Gets the previous <code>RecordingRequest</code> object in the
     * list. This method may be called repeatedly to iterate through the
     * list in reverse order.
     *
     * @return The <code>RecordingRequest</code> object at the previous
     *      position in the list.
     *
     * @throws java.util.NoSuchElementException If the iteration has no
     *      previous <code>RecordingRequest</code>.
     */
    public RecordingRequest previousEntry();

    /**
     * Tests if there is a <code>RecordingRequest</code> in the next
     * position in the list.
     *
     * @return <code>true</code> if there is a <code>RecordingRequest</code>
     * in the next position in the list; <code>false</code> otherwise.
     */
    public boolean hasNext();

    /**
     * Tests if there is a <code>RecordingRequest</code> in the previous
     * position in the list.
     *
     * @return <code>true</code> if there is a <code>RecordingRequest</code>
     * in the previous position in the list; <code>false</code> otherwise.
     */
    public boolean hasPrevious();

    /**
     * Gets the next 'n' <code>RecordingRequest</code> objects in the list.
     * This method also advances the current position within the list. If
     * the requested number of entries are not  available, the remaining
     * elements are returned. If the current position is at the end 
	 * of the iterator, this method returns an array with length zero.
     *
     * @param n the number of next entries requested.
     *
     * @return an array containing the next 'n' <code>RecordingRequest</code>
     *      object from the current position in the list. If 'n' is zero
     *      or negative, a zero-length array shall be returned.
     */
    public RecordingRequest[] nextEntries(int n);

    /**
     * Gets the previous 'n' <code>RecordingRequest</code> objects in the
     * list. This method also changes the current position within the list.
     * If the requested number of entries are not available, the remaining
     * elements are returned. If the current position is at the beginning 
	 * of the iterator, this method returns an array with length zero.
     *
     * @param n the number of previous entries requested.
     *
     * @return an array containing the previous 'n'
     *      <code>RecordingRequest</code> object from the current position
     *      in the list. If 'n' is zero
     *      or negative, a zero-length array shall be returned.
     */
    public RecordingRequest[] previousEntries(int n);

    /**
     * Gets the <code>RecordingRequest</code> object at the specified
     * position. This method does not advance the current position within
     * the list.
     *
     * @param index the position of the RecordingRequest to be retrieved.
     *
     * @return the RecordingRequest at the specified position.
     *
     * @throws IndexOutOfBoundsException if the index is greater than
     *      the size of the list.
     */
    public RecordingRequest getEntry(int index);

    /**
     * Gets the position of a specified recording request in the list.
     *
     * @param entry The recording request for which the position is sought.
     *
     * @return The position of the specified recording; -1 if the entry is 
	 * not found.
     */
    public int getPosition(RecordingRequest entry);

    /**
     * Gets the current position of the RecordingListIterator. This would
     * be the position from where the next RecordingRequest will be
     * retrieved when an application calls the nextEntry.
     *
     * @return the current position of the RecordingListIterator.
     */
    public int getPosition();

    /**
     * Sets the current position of the RecordingListIterator. This would
     * be the position from where the next RecordingRequest will be
     * retrieved when an application calls the nextEntry.
     *
     * @param index the current position of the RecordingListIterator
     *      would be set to this value.
     *
     * @throws IndexOutOfBoundsException if the index is greater than
     *      the size of the list.
     */
    public void setPosition(int index) throws IndexOutOfBoundsException;

    /**
     * Gets the recording list corresponding to this RecordingListIterator. 
     *
     * @return the RecordingList corresponding to this iterator. 
     */
    public RecordingList getRecordingList();
}


