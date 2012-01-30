package org.davic.mpeg.sections;
 /**
  * Signals that no data is available from a Section object.
  * @version Updated to DAVIC 1.3.1
  */
public class NoDataAvailableException
	extends org.davic.mpeg.sections.SectionFilterException
{
 /**
  * Constructs a NoDataAvailableException.
  */
  public NoDataAvailableException()
	{
	}
  /**
   * Constructs a NoDataAvailableException with the specified detail message
   * @param s the detail message
   */
  public NoDataAvailableException(String s)
  {
  }
}

