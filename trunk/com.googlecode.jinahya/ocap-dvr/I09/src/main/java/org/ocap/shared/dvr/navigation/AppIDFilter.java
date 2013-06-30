package org.ocap.shared.dvr.navigation;

import org.ocap.shared.dvr.RecordingRequest;
import org.dvb.application.AppID;

/**
 * Filter to filter based on AppID.
 */
public class AppIDFilter extends RecordingListFilter
{

    private AppID appID;

    /**
     * Constructs the filter based on a particular <code>AppID</code>.
     *
     * @param appID AppID value for matching <code>RecordingRequest</code>s.
     */
    public AppIDFilter(AppID appID)
    {
    }

    /**
     * Reports the value of <code>AppID</code> used to create this filter.
     *
     * @return The value of <code>AppID</code> used to create this filter.
     */
    public AppID getFilterValue()
    {
        return appID;
    }

    /**
     * Tests if the given <code>RecordingRequest</code> passes the filter.
     *
     * @param entry An individual <code>RecordingRequest</code> to be
     * evaluated against the filtering algorithm.
     * @return <code>true</code> if <code>RecordingRequest</code> is of the type
     * indicated by the filter value; <code>false</code> otherwise.
     */
    public boolean accept(RecordingRequest entry)
    {
        return false;
    }

}

