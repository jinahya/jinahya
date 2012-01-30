 /**
  * This exception must be thrown by MPEG related APIs when tuning to the 
  * required transport stream was not possible.
  *
  */

package org.davic.mpeg;

public class TuningException
	extends org.davic.mpeg.ResourceException
{
  /**
   * Constructs a TuningException with no detail message
   */
  public TuningException()
  {
  }
  /**
   * Constructs a TuningException with the specified detail message
   * @param s the detail message
   */
  public TuningException(String s)
  {
  }
	/**
	  * @return a reference to the TransportStream object that represents
	  * the Transport Stream which could not be accessed.
	  */
	public TransportStream getTransportStream()
	{
	  return null;
	}
}














