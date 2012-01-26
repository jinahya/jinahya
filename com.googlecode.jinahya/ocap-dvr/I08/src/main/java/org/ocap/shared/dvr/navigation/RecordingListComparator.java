package org.ocap.shared.dvr.navigation;

import org.ocap.shared.dvr.RecordingRequest;

/**
 * This interface represents a sorting criteria to be applied while sorting a
 * RecordingList.
 */

public interface RecordingListComparator
{

    /**
     * Compares two entries to check whether the first entry should be
     * placed ahead of the second entry in the iterator list.
     *
     * @param first the first entry to compare
     *
     * @param second the second entry to compare
     *
     * @return  positive integer if the first argument should be placed
     *      ahead of the second argument; negative integer if the second
     *      argument should be placed ahead of the first entry; zero if
     *      the current order should be retained.
     */

    public int compare(RecordingRequest first, RecordingRequest second);
}


