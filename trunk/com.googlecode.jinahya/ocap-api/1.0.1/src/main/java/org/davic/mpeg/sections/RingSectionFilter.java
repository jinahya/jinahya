package org.davic.mpeg.sections;
 /**
  * This class defines a section filter intended to be used to capture a continuous stream of 
  * MPEG-2 sections without needing to stop and re-arm a filter. A RingSectionFilter object has 
  * a pre-defined number of Section objects as part of it. Incoming sections are loaded 
  * sequentially into these Section objects. Filtering proceeds while empty Section objects 
  * remain. Applications wishing filtering to proceed indefinitely must use the setEmpty 
  * method of the Section object to mark Section objects as empty before the filling process 
  * reaches them. If the filtering process reaches a non-empty Section object, it will terminate at 
  * that point.  On each occasion when startFiltering is called, the sections will be captured starting 
  * from the beginning of the array.<p>
  * All sections in a ring section filter are initialised to empty when the ring section
  * filter is first created. Clearing them to empty any time after this is the responsibility 
  * of the application. Starting a ring section filter shall not clear any of the sections to empty.
  *
  * @version updated to DAVIC 1.3.1
  */
public class RingSectionFilter
	extends org.davic.mpeg.sections.SectionFilter
{
	// here to stop javadoc generating a constructor
	RingSectionFilter()
	{
	}
 /**
  * This method returns the Section objects of the RingSectionFilter in an array. The array will 
  * be fully populated at all times, it is the responsibility of the application to check which of 
  * these contain valid data. 
  *
  * Repeated calls to this method will always return the same result.
  */
  public Section[] getSections()
	{
	  return null;
	}
}

