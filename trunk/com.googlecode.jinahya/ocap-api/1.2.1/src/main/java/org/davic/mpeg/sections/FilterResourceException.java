package org.davic.mpeg.sections;
 /**
  * Signals that inadequate resources are available to support the requested operation
  * when a SectionFilterGroup is in the connected or disconnected states.
  * @version Updated to DAVIC 1.3.1
  */
public class FilterResourceException
	extends org.davic.mpeg.sections.SectionFilterException
{
 /**
  * Constructs a resource Exception.
  */
  public FilterResourceException()
	{
	}
  /**
   * Constructs a FilterResourceException with the specified detail message
   * @param s the detail message
   */
  public FilterResourceException(String s)
  {
  }
}

