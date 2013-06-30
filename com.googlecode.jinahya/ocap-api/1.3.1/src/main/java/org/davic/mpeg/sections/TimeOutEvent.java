package org.davic.mpeg.sections;

/**
 * This event is generated if section filter operations time out within the period specified by the 
 * setTimeOut() method. For a SimpleSectionFilter it will be generated if no sections arrive within the 
 * specified period. For a TableSectionFilter, it will be generated if the complete table does not arrive
 * within the specified time. For a RingSectionFilter, it will be generated if the specified time has 
 * elapsed since the arrival of the last section being successfully filtered.
 *
 * @version created for DAVIC 1.3.1
 */

public class TimeOutEvent extends EndOfFilteringEvent
{
 /** 
  * This constructs an TimeOutEvent event for the specified SectionFilter object.
  * @param  f the SectionFilter object which timed out
  * @param appData application data that was passed to the startFiltering method
  */
 
 public TimeOutEvent ( SectionFilter f, Object appData)
	{
	  super(f,appData);
	}

 /**
  *  This returns the SectionFilter object which timed out
  */

 public Object getSource()
	{
	  return null;
	}
}



