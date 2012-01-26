package org.ocap.shared.dvr.navigation;

import org.ocap.shared.dvr.RecordingRequest;

/**
 * Filter to filter based on values returned by the getState method in
 * {@link RecordingRequest}.
 */
public class RecordingStateFilter extends RecordingListFilter
{

    /**
     * Constructs the filter based on a particular state type
     * (PENDING, FAILED, etc.).
     *
     * @param state Value for matching the state of a
     *      {@link RecordingRequest} instance.
     */
    public RecordingStateFilter(int state)
    {
    }

    /**
     * Reports the value of state used to create this filter.
     *
     * @return The value of state used to create this filter.
     */
    public int getFilterValue()
    {
        return 0;
    }

    /**
     * Tests if the given {@link RecordingRequest} passes the filter.
     *
     * @param entry An individual RecordingRequest to be evaluated against
     *      the filtering algorithm.
     *
     * @return <code>true</code> if {@link RecordingRequest} contained
     *      within the RecordingRequest parameter is in the state indicated
     *      by the filter value; <code>false</code> otherwise.
     */
    public boolean accept(RecordingRequest entry)
    {
        return false;
    }

}


