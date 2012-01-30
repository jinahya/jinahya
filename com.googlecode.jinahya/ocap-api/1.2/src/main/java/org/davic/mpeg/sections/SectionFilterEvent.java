package org.davic.mpeg.sections;
 /**
  * This class is the base class for Events in the section filter API.
  * @version Updated to DAVIC 1.3.1
  */
public class SectionFilterEvent extends java.util.EventObject
{
 /**
  * This constructs a SectionFilterEvent for the specified SectionFilter object.
  * @param f the SectionFilter object where the event originated.
  * @param appData application data that was passed to the startFiltering method
  */
  public SectionFilterEvent( SectionFilter f, Object appData)
	{
		super( (Object) f) ;
	}
 /**
  * This returns the SectionFilter object which was the source of the event.
  */
  	public Object getSource()
	{
	  return null;
	}

	/**
	 * Returns the application data that was passed to the
	 * startFiltering method
	 * @return the application data
	 */
	public Object getAppData()
	{
		return null;
	}

}

