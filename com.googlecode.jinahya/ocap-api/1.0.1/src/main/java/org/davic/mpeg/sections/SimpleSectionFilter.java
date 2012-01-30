package org.davic.mpeg.sections;
 /**
  * This class defines a simple section filter intended to be used to capture a single section once 
  * only. When a section matching the specified filter pattern is found, SimpleSectionFilter 
  * objects will stop filtering as if the stopFiltering method had been called.
  *
  * @version updated to DAVIC 1.3.1
  */
public class SimpleSectionFilter
	extends org.davic.mpeg.sections.SectionFilter
{
	// here to stop javadoc generating a constructor
	SimpleSectionFilter()
	{
	}
 /**
  * This method retrieves a Section object describing the last MPEG-2 section which matched 
  * the specified filter characteristics. If the SimpleSectionFilter object is currently filtering, 
  * this method will block until filtering stops. 
  *
  * Repeated calls to this method will return the same Section object, provided that no new calls to 
  * startFiltering have been made in the interim. Each time a new filtering operation is started, 
  * a new Section object will be created. All references except any in the application to the previous 
  * Section object will be removed. All data accessing methods on the previous Section object will 
  * throw a NoDataAvailableException.
  *
  * @exception FilteringInterruptedException if filtering stops before a matching section is found
  */
  public Section getSection() throws FilteringInterruptedException
	{
	  return null;
	}
}

