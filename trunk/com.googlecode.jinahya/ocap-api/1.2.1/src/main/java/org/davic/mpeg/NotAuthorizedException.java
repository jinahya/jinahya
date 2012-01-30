package org.davic.mpeg;
/**
  * This class is thrown by MPEG related APIs when access is requested to 
  * information which is scrambled and to which access is not permitted by 
  * the security system.
  *
  */

public class NotAuthorizedException extends java.lang.Exception implements NotAuthorizedInterface
{
  /**
   * Constructs a NotAuthorizedException with no detail message
   */
  public NotAuthorizedException()
  {
  }
  /**
   * Constructs a NotAuthorizedException with the specified detail message
   * @param s the detail message
   */
  public NotAuthorizedException(String s)
  {
  }
    /**
     * @return SERVICE or ELEMENTARY_STREAM to indicate
     * that either a service (MPEG program) or one or more
     * elementary streams could not be descrambled. 
     */
    public int getType()
    {
       return 0;
    }

    /**
     * If getType() returns SERVICE, then this method returns the
     * Service that could not be descrambled. Otherwise it returns
     * null. 
     *
     * @return either the Service that could not be descrambled or null
     */
    public Service getService()
    {
        return null;
    }

    /**
     * If getType() returns ELEMENTARY_STREAM, then this method returns
     * the set of ElementaryStreams that could not be descrambled.
     * Otherwise it returns null.
     *
     * @return either the set of ElementaryStreams that could not be 
     * descrambled or null
     */
    public ElementaryStream[] getElementaryStreams()
    {
        return null;
    }

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
      throws java.lang.IndexOutOfBoundsException
    {
      return null;
    }
}











