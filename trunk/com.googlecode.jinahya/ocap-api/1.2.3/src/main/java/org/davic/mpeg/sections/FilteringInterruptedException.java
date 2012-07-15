package org.davic.mpeg.sections;
 /**
  * Signals that a filtering operation was interrupted before any data had been filtered.
  *
  * @version updated to DAVIC 1.3.1
  */
public class FilteringInterruptedException
	extends org.davic.mpeg.sections.SectionFilterException
{
 /**
  * Constructs an FilteringInterruptedException.
  */
  public FilteringInterruptedException()
	{
	}
  /**
   * Constructs a FilteringInterruptedException with the specified detail message
   * @param s the detail message
   */
  public FilteringInterruptedException(String s)
  {
  }

}

