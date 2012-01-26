package org.davic.mpeg;

/**
 * NotAuthorizedInterface shall be implemented by classes which can report failure to access broadcast content due to failure to descramble that content. The interface provides an ability for an application to find out some information about the reason for the failure.
 */

public interface NotAuthorizedInterface
{
    /**
     * Major reason - access may be possible under certain conditions.
     */
    public final static int POSSIBLE_UNDER_CONDITIONS = 0;
    /**
     * Major reason - access not possible
     */
    public final static int NOT_POSSIBLE = 1;

  /**
   * Minor reason for POSSIBLE_UNDER_CONDITIONS - user dialog needed for payment
   */
  public final static int COMMERCIAL_DIALOG = 1;
  /**
   * Minor reason for POSSIBLE_UNDER_CONDITIONS - user dialog needed for maturity
   */
    public final static int MATURITY_RATING_DIALOG = 2;
  /**
   * Minor reason for POSSIBLE_UNDER_CONDITIONS - user dialog needed for technical purposes.
   */
    public final static int TECHNICAL_DIALOG = 3;
  /**
   * Minor reason for POSSIBLE_UNDER_CONDITIONS - user dialog needed to explain about free preview.
   */
    public final static int FREE_PREVIEW_DIALOG = 4;
    
    /**
     * Minor reason for NOT_POSSIBLE - user does not have an entitlement
     */
    public final static int NO_ENTITLEMENT = 1;
    /**
     * Minor reason for NOT_POSSIBLE - user does not have suitable maturity
     */
    public final static int MATURITY_RATING = 2;
    /**
     * Minor reason for NOT_POSSIBLE - a technical reason of some kind
     */
    public final static int TECHNICAL = 3;
    /**
     * Minor reason for NOT_POSSIBLE - not allowed for geographical reasons
     */
    public final static int GEOGRAPHICAL_BLACKOUT = 4;

  /**
   * Minor reason for both POSSIBLE_UNDER_CONDITIONS and NOT_POSSIBLE.
   * Another reason.
   */
    public final static int OTHER = 5;

  /**
   * The component to which access was refused was a MPEG Program/DVB Service
   * @see NotAuthorizedInterface#getType
   */
  public final static int SERVICE = 0;
  /**
   * The component to which access was refused was an MPEG elementary stream
   * @see NotAuthorizedInterface#getType
   */
  public final static int ELEMENTARY_STREAM = 1;

    /**
     * @return SERVICE or ELEMENTARY_STREAM to indicate
     * that either a service (MPEG program) or one or more
     * elementary streams could not be descrambled. 
     */
    public int getType();

    /**
     * If getType() returns SERVICE, then this method returns the
     * Service that could not be descrambled. Otherwise it returns
     * null. 
     *
     * @return either the Service that could not be descrambled or null
     */
    public Service getService();

    /**
     * If getType() returns ELEMENTARY_STREAM, then this method returns
     * the set of ElementaryStreams that could not be descrambled.
     * Otherwise it returns null.
     *
     * @return either the set of ElementaryStreams that could not be 
     * descrambled or null
     */
    public ElementaryStream[] getElementaryStreams();

    /**
     * Returns the reason(s) why descrambling was not possible.
     *
     * @param index If the component to which access failed is a Service,
     * index shall be 0. Otherwise index shall refer to one stream in the
     * set returnedby getElementaryStreams().
     *
     * @return an array of length 2 where the first element of the
     * array is the major reason and the second element of the array
     * is the minor reason.
     *
     * @exception IndexOutOfBoundsException If the component to which access
     * failed is a Service, this exception will be thrown if index is non zero.
     * If the component(s) to which access failed was a (set of) elementary
     * streams then this exception will be thrown where index is beyond the
     * size of the array returned by getElementaryStreams.
     *
     * @see NotAuthorizedInterface#getElementaryStreams
     */
    public int[] getReason(int index)
      throws java.lang.IndexOutOfBoundsException;
}








