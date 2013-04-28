package org.davic.mpeg.sections;
 /**
  * Signals that a SectionFilterGroup has lost its connection or resources and hence is unable to satisfy
  * a call to a startFiltering method. It is only generated for SectionFilterGroups which are in the 
  * ConnectionLost state.
  * @version Updated to DAVIC 1.3.1
  */
public class ConnectionLostException
	extends org.davic.mpeg.sections.SectionFilterException
{
 /**
  * Constructs a resource Exception.
  */
  public ConnectionLostException()
	{
	}
  /**
   * Constructs a ConnectionLostException with the specified detail message
   * @param s the detail message
   */
  public ConnectionLostException(String s)
  {
  }
}

