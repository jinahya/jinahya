package org.davic.mpeg.sections;
 /**
  * This class is used to report the end of a filtering operation started by RingSectionFilter or 
  * TableSectionFilter. It is not generated when play stops for SimpleSectionFilter.
  */
public class EndOfFilteringEvent
	extends org.davic.mpeg.sections.SectionFilterEvent
{
 /**
  * This constructs an EndOfFiltering event for the specified SectionFilter object.
  * @param f the SectionFilter object which filtered the data.
  * @param appData application data that was passed to the startFiltering method
  */
  public EndOfFilteringEvent( SectionFilter f, Object appData)
	{
	  super(f,appData);
	}
 /**
  * This returns the SectionFilter object which filtered the data.
  */
  	public Object getSource()
	{
	  return null;
	}
}

