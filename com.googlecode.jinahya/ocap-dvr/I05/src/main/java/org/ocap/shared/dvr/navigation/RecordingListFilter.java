package org.ocap.shared.dvr.navigation;

import org.ocap.shared.dvr.RecordingRequest;

/**
 * Base class for all RecordingListFilters. Subclasses of RecordingListFilter
 * may be used to create filters to specify restrictions.
 */
public abstract class RecordingListFilter
{

    /**
     * Constructs the filter.
     */
    protected RecordingListFilter()
    {
    }

    /**
     * Tests if a particular entry passes this filter. Subtypes of
     * <code>RecordingListFilter</code> override this method to provide the
     * logic for a filtering operation on individual
     * <code>RecordingRequest</code> objects.
     *
     * @param entry A <code>RecordingRequest</code> to be evaluated
     * against the filtering algorithm.
     * @return <code>true</code> if <code>entry</code> satisfies the
     * filtering algorithm; <code>false</code> otherwise.
     */
    public abstract boolean accept(RecordingRequest entry);

    /**
     * Provides a means to cascade filters. The accept method of this
     * filter is called only for entries matching the specified filter.
     * Multiple calls to this method will replace the previously set filter.
     *
     * @param filter the filter that will be applied before selecting the
     * entries for which the accept() method is called. If the current
     * filter is in the cascade chain of the filter passed in as the
     * argument, this method does nothing. Passing null shall remove the 
     * cascading relationship between the two filters.
     */
    public void setCascadingFilter(RecordingListFilter filter)
    {
    }
    /**
     * Return the current cascaded filter.
     * @return the current cascaded filter or null if no cascading
     * filter is set.
     */
    public RecordingListFilter getCascadingFilter(){return null;}
}


