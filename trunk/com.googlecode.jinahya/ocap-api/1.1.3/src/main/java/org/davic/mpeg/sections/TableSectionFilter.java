package org.davic.mpeg.sections;
 /**
  * This class defines a section filter operation optimized to capture entire tables with minimum 
  * intervention required from the application. When filtering is started, first one section 
  * matching the specified pattern will be filtered. Once that section has been found, the 
  * last_section_number field will be used to determine the number of Section objects required 
  * to hold the entire table. This number of objects will be created and filtering re-started to 
  * capture all the sections of the table. The SectionAvailableEvent will be generated each time 
  * a Section is captured. The EndOfFilteringEvent will be generated when the complete table 
  * has been captured.
  *
  * The version_number of all sections of the table will be the same. If a section is captured with a 
  * version_number that differs from the version_number of the section first captured, a 
  * VersionChangeDetectedEvent will be generated. The newly captured section will be ignored 
  * and filtering will continue on the table with the version number of the first captured section. 
  * Only one VersionChangeDetectedEvent will be sent per filtering action. 
  *
  * Care should be taking in setting the filter parameters, a too restrictive filter will never stop 
  * automatically and a too wide filter can produce inconsistent results (e.g. filtering short sections 
  * using a TableSectionFilter)
  *
  * When the API detects a filtering situation where the filter parameters have been incompletely defined, 
  * resulting in a blocking filter or a non MPEG-2 compliant result, an InCompleteFilteringEvent is sent 
  * and filtering is stopped.
  *
  * @version updated to DAVIC 1.3.1
  */
public class TableSectionFilter
	extends org.davic.mpeg.sections.SectionFilter
{
	// here to stop javadoc generating a constructor
	TableSectionFilter()
	{
	}
 /**
  * This method returns an array of Section objects corresponding to the sections of the table. 
  * The sections in the array will be ordered according to their section_number. Any sections 
  * which have not yet been filtered from the source will have the corresponding entry in the 
  * array set to null. If no sections have been filtered then this method will block until at least 
  * one section is available or filtering stops. 
  *
  * Repeated calls to this method will return the same array, provided that no new calls to 
  * startFiltering have been made in the interim. Each time a new filtering operation is started, 
  * a new array of Section objects will be created. All references except any in the application 
  * to the previous array and Section objects will be removed. All data accessing methods on the 
  * previous Section objects will throw a NoDataAvailableException.
  *
  * @exception FilteringInterruptedException if filtering stops before one section is available 
  */
  public Section[] getSections()
	throws FilteringInterruptedException
	{
	  return null;
	}
}

