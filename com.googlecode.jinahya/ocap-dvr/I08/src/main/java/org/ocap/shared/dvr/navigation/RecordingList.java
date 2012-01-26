package org.ocap.shared.dvr.navigation;

import org.ocap.shared.dvr.RecordingRequest;
import javax.tv.locator.Locator;
import javax.tv.locator.InvalidLocatorException;

/**
 * RecordingList represents a list of recordings.
 */
public interface RecordingList
{

    /**
     * Creates a new <code>RecordingList</code> object that is a subset of
     * this list, based on the conditions specified by a
     * <code>RecordingListFilter</code> object. This method may be used
     * to generate increasingly specialized lists of
     * <code>RecordingRequest</code> objects based on multiple filtering
     * criteria. If the filter is <code>null</code>, the resulting
     * <code>RecordingList</code> will be a duplicate of this list.<p>
     * Note that the <code>accept</code> method of the given
     * <code>RecordingListFilter</code> will be invoked for each
     * <code>RecordingRequest</code> to be filtered using the same
     * application thread that invokes this method.
     *
     * @param filter A filter constraining the requested
     * recording list, or <code>null</code>.
     * @return A <code>RecordingList</code> object created based on the
     * specified filtering rules.
     */
    public RecordingList filterRecordingList(RecordingListFilter filter);

    /**
     * Generates an iterator on the <code>RecordingRequest</code> elements
     * in this list.
     *
     * @return A <code>RecordingListIterator</code> on the
     * <code>RecordingRequest</code>s in this list.
     */
    public RecordingListIterator createRecordingListIterator();

    /**
     * Tests if the indicated <code>RecordingRequest</code> object is
     * contained in the list.
     *
     * @param entry The <code>RecordingRequest</code> object for which
     * to search.
     * @return <code>true</code> if the specified
     * <code>RecordingRequest</code> is a member of the list;
     * <code>false</code> otherwise.
     */
    public boolean contains(RecordingRequest entry);

    /**
     * Reports the position of the first occurrence of the indicated
     * <code>RecordingRequest</code> object in the list.
     *
     * @param entry The <code>RecordingRequest</code> object for which
     * to search.
     * @return The index of the first occurrence of the <code>entry</code>,
     * or <code>-1</code> if <code>entry</code> is not contained in the list.
     */
    public int indexOf(RecordingRequest entry);

    /**
     * Reports the number of <code>RecordingRequest</code> objects in the list.
     *
     * @return The number of <code>RecordingRequest</code> objects in the list.
     */
    public int size();

    /**
     * Reports the <code>RecordingRequest</code> at the specified index
     * position.
     *
     * @param index A position in the <code>RecordingList</code>.
     * @return The <code>RecordingRequest</code> at the specified index.
     * @throws java.lang.IndexOutOfBoundsException If
     * <code>index</code> < 0 or <code>index</code> > <code>size()-1</code>.
     */
    public RecordingRequest getRecordingRequest(int index);

    /**
    * Creates a new <code>RecordingList</code> that contains all the
    * elements of this list sorted according to the criteria specified
    * by a <code>RecordingListComparator</code>. 
    * 
    * @param sortCriteria the sort criteria to be applied to sort the
    * entries in the recording list.
    *
    * @return A sorted copy of the recording list.
    */
    public RecordingList sortRecordingList(
            RecordingListComparator sortCriteria);

}


