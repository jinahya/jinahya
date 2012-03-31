package org.davic.mpeg.sections;
 /**
  * This class describes a single section as filtered from an MPEG transport stream.
  *
  * A cloned Section object is a new and separate object. It is unaffected by changes in the state of the original 
  * Section object or restarting of the SectionFilter the source Section object originated from. The clone method 
  * must be implemented without declaring exceptions.
  *
  * @version updated to DAVIC 1.3.1
  */
public class Section implements java.lang.Cloneable
{
	// here to stop javadoc generating a constructor
	Section()
	{
	}
 /**
  * This method returns all data from the filtered section in the Section object, including the 
  * section header.  Each call to this method results in a new a copy of the section data.
  * @exception NoDataAvailableException if no valid data is available.
  */
  public byte[] getData()
	throws NoDataAvailableException
	{
	  return null;
	}
 /**
  * This method returns the specified part of the filtered data. 
  * Each call to this method results in a new a copy of the section data.
  * @param index defines within the filtered section the index of the first byte of the data to be retrieved. 
  * The first byte of the section (the table_id field) has index 1.
  * @param length defines the number of consecutive bytes from the filtered section to be retrieved.
  * @exception NoDataAvailableException if no valid data is available.
  * @exception java.lang.IndexOutOfBoundsException if any part of the filtered data requested would be 
  * outside the range of data in the section.
  */
  public byte[] getData(int index, int length)
	throws NoDataAvailableException, java.lang.IndexOutOfBoundsException
	{
	  return null;
	}
 /**
  * This method returns one byte from the filtered data. 
  * @param index defines within the filtered section the index of the byte to be retrieved. The first byte of 
  * the section (the table_id field) has index 1.
  * @exception NoDataAvailableException if no valid data is available.
  * @exception java.lang.IndexOutOfBoundsException if the byte requested would be outside the range of 
  * data in the section.
  */
  public byte getByteAt(int index)
	throws NoDataAvailableException, 
java.lang.IndexOutOfBoundsException
	{
	  return 0;
	}
 /**
  * This method returns the value of the corresponding field from an MPEG-2 section header.
  * @exception NoDataAvailableException thrown if no valid data is available
  */
  public int table_id()
	throws NoDataAvailableException
	{
	  return 0;
	}
 /**
  * This method returns the value of the corresponding field from an MPEG-2 section header.
  * @exception NoDataAvailableException thrown if no valid data is available
  */
  public boolean section_syntax_indicator()
	throws NoDataAvailableException
	{
	  return false;
	}
 /**
  * This method returns the value of the corresponding field from an MPEG-2 section header.
  * @exception NoDataAvailableException thrown if no valid data is available
  */
  public boolean private_indicator()
	throws NoDataAvailableException
	{
	  return false;
	}
 /**
  * This method returns the value of the corresponding field from an MPEG-2 section header.
  * @exception NoDataAvailableException thrown if no valid data is available
  */
  public int section_length()
	throws NoDataAvailableException
	{
	  return 0;
	}
 /**
  * This method returns the value of the corresponding field from an MPEG-2 section header.
  * @exception NoDataAvailableException thrown if no valid data is available
  */
  public int table_id_extension()
	throws NoDataAvailableException
	{
	  return 0;
	}
 /**
  * This method returns the value of the corresponding field from an MPEG-2 section header.
  * @exception NoDataAvailableException thrown if no valid data is available
  */
  public short version_number()
	throws NoDataAvailableException
	{
	  return 0;
	}
 /**
  * This method returns the value of the corresponding field from an MPEG-2 section header.
  * @exception NoDataAvailableException thrown if no valid data is available
  */
  public boolean current_next_indicator()
	throws NoDataAvailableException
	{
	  return false;
	}
 /**
  * This method returns the value of the corresponding field from an MPEG-2 section header.
  * @exception NoDataAvailableException thrown if no valid data is available
  */
  public int section_number()
	throws NoDataAvailableException
	{
	  return 0;
	}
 /**
  * This method returns the value of the corresponding field from an MPEG-2 section header.
  * @exception NoDataAvailableException thrown if no valid data is available
  */
  public int last_section_number()
	throws NoDataAvailableException
	{
	  return 0;
	}
 /**
  * This method reads whether a Section object contains valid data.
  * @return true when the Section object contains valid data otherwise false
  */
  public boolean getFullStatus()
	{
	  return false;
	}
 /**
  * This method sets a Section object such that any data contained within it is no longer valid. 
  * This is intended to be used with RingSectionFilters to indicate that the particular object can 
  * be re-used.
  */
  public void setEmpty()
	{
	}
  /**
   * Create a copy of this Section object. A cloned Section object is a new and separate object. 
   * It is unaffected by changes in the 
   * state of the original Section object or restarting of the SectionFilter the source 
   * Section object originated from.
   * 
   */
  public Object clone() 
  {
	return null;
  }
}

