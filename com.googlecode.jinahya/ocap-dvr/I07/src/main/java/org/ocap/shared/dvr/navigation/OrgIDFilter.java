package org.ocap.shared.dvr.navigation;

import org.ocap.shared.dvr.RecordingRequest;

/**
 * Filter to filter based on OrgID.
 */
public class OrgIDFilter extends RecordingListFilter {

  /**
   * Constructs the filter based on a particular organization ID.
   *
   * @param orgID the organization ID value for matching
   *        {@link RecordingRequest} instances.
   */
  public OrgIDFilter(int orgID)
  {
  }

  /**
   * Reports the value of the organization ID used to create this filter.
   *
   * @return The organization ID used to filter.
   */
  public int getFilterValue()
  {
    return 0;
  }

  /**
   * Tests if the given {@link RecordingRequest} passes the filter.
   *
   * @param entry An individual <code>RecordingRequest</code> to be evaluated
   *        against the filtering algorithm.
   *
   * @return <code>true</code> if <code>RecordingRequest</code> is of the type
   * indicated by the filter value; <code>false</code> otherwise.
   */
  public boolean accept(RecordingRequest entry)
  {
    return false;
  }

}

