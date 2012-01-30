package org.davic.mpeg.sections;

/**
 * This class is used to report the end of a filtering operation started by TableSectionFilter. This event is 
 * generated when the API detects a filtering situation where the filter parameters have been incompletely defined, 
 * resulting in a blocking filter or a non MPEG-2 compliant result.
 *
 * @version created for DAVIC 1.3.1
 */

public class IncompleteFilteringEvent extends EndOfFilteringEvent
{
 /** 
  * This constructs an IncompleteFilteringEvent event for the specified SectionFilter object.
  * @param  f the SectionFilter object which filtered the data.
  * @param appData application data that was passed to the startFiltering method
  */
 
 public IncompleteFilteringEvent ( SectionFilter f,Object appData )
	{
	  super(f,appData);
	}

 /**
  *  This returns the SectionFilter object which filtered the data.
  */

 public Object getSource()
	{
	  return null;
	}
}



