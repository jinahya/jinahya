package org.davic.mpeg.sections;
 /**
  * This class is the base class for a set of classes describing section filters with different 
  * characteristics of life cycle and buffering.
  *
  * When a SectionFilterGroup is detached, either by the client or through resource withdrawal,
  * started SectionFilters shall remain started. Hence if the SectionFilterGroup is re-attached,
  * those filters shall re-activate.
  *
  * @version Updated to DAVIC 1.3.1
  */
public abstract class SectionFilter
{
	// here to stop javadoc generating a constructor
	SectionFilter()
	{
	}
 /**
  * Defines a SectionFilter object as filtering only for sections matching a specific PID. If the 
  * parent SectionFilterGroup is attached to a TransportStream then filtering will start 
  * immediately.
  * @param appData An object supplied by the application. This object will be delivered to the 
  * subscribed section filter listener as part of all SectionFilterEvents that will be generated 
  * because of this method call. The application can use this object for internal communication 
  * purposes. If the application does not need any application data, the parameter can be null.
  * @param pid the value of the PID to filter for in incoming sections
  * @exception FilterResourceException if all the number of started SectionFilters for the parent SectionFilterGroup 
  * is already equal to the number of section filters associated with the SectionFilterGroup when it 
  * was created. Note that this is applied whether the parent section filter group is connected 
  * to a TS or not.
  * @exception org.davic.mpeg.NotAuthorizedException if the information requested is scrambled and 
  * permission to descramble it is refused.
  * @exception IllegalFilterDefinitionException if called for a TableSectionFilter.
  * @exception ConnectionLostException if the parent SectionFilterGroup is in the ConnectionLost state and hence
  * is unable to satisfy the method call due to absence of resources or absence of sections to filter. 
  */
  public void startFiltering( Object appData, int pid )
 	throws FilterResourceException, org.davic.mpeg.NotAuthorizedException,  IllegalFilterDefinitionException,
	ConnectionLostException
	{
	}
 /**
  * Defines a SectionFilter object as filtering only for sections matching a specific PID and 
  * table_id. If the parent SectionFilterGroup is attached to a TransportStream then filtering will 
  * start immediately. 
  * @param appData An object supplied by the application. This object will be delivered to the 
  * subscribed section filter listener as part of all SectionFilterEvents that will be generated 
  * because of this method call. The application can use this object for internal communication 
  * purposes. If the application does not need any application data, the parameter can be null.
  * @param pid the value of the PID to filter for in incoming sections
  * @param table_id the value of the table_id to filter for in incoming sections
  * @exception FilterResourceException if all the number of started SectionFilters for the parent SectionFilterGroup 
  * is already equal to the number of section filters associated with the SectionFilterGroup when it 
  * was created. Note that this is applied whether the parent section filter group is connected 
  * to a TS or not.
  * @exception org.davic.mpeg.NotAuthorizedException if the information requested is scrambled and 
  * permission to descramble it is refused.
  * @exception ConnectionLostException if the parent SectionFilterGroup is in the ConnectionLost state and hence
  * is unable to satisfy the method call due to absence of resources or absence of sections to filter. 
  * @exception IllegalFilterDefinitionException where either integer is negative or larger than allowed by the MPEG specification
  */
  public void startFiltering( Object appData, int pid, int table_id )
 	throws FilterResourceException, org.davic.mpeg.NotAuthorizedException, ConnectionLostException,
		IllegalFilterDefinitionException
	{
	}
 /**
  * Defines a SectionFilter object as filtering only for sections matching a specific PID and table_id, and 
  * where contents of the section match the specified filter pattern. The first byte of each array corresponds 
  * to the third byte of the section. If the parent SectionFilterGroup is attached to a TransportStream then 
  * filtering will start immediately.
  *
  * @param appData An object supplied by the application. This object will be delivered to the 
  * subscribed section filter listener as part of all SectionFilterEvents that will be generated 
  * because of this method call. The application can use this object for internal communication 
  * purposes. If the application does not need any application data, the parameter can be null.
  * @param pid the value of the PID to filter for in incoming sections.
  * @param table_id the value of the table_id field to filter for in incoming sections
  * @param posFilterDef defines values to match for bits in the section, as defined in clause E.8.1.
  * @param posFilterMask defines which bits in the section are to be compared against the values specified 
  * in the posFilterDef parameter, as defined in clause E.8.1.
  * @exception FilterResourceException if all the number of started SectionFilters for the parent SectionFilterGroup 
  * is already equal to the number of section filters associated with the SectionFilterGroup when it 
  * was created. Note that this is applied whether the parent section filter group is connected 
  * to a TS or not.
  * @exception IllegalFilterDefinitionException the filter definition specified is illegal either because the 
  * posFilterDef and posFilterMask arrays are of different sizes or because their length is beyond the 
  * filtering capacity of the system.
  * @exception org.davic.mpeg.NotAuthorizedException if the information requested is scrambled and 
  * permission to descramble it is refused.
  * @exception ConnectionLostException if the parent SectionFilterGroup is in the ConnectionLost state and hence
  * is unable to satisfy the method call due to absence of resources or absence of sections to filter. 
  */
  public void startFiltering( Object appData, int pid, int table_id, byte posFilterDef[], 
	  byte posFilterMask[]  ) 
 	throws FilterResourceException, IllegalFilterDefinitionException, 
		org.davic.mpeg.NotAuthorizedException, ConnectionLostException
	{
	}
 /**
  * Defines a SectionFilter object as filtering only for sections matching a specific PID and 
  * table_id, and where contents of the section match the specified filter pattern. If the parent 
  * SectionFilterGroup is attached to a TransportStream then filtering will start immediately.
  *
  * @param appData An object supplied by the application. This object will be delivered to the 
  * subscribed section filter listener as part of all SectionFilterEvents that will be generated 
  * because of this method call. The application can use this object for internal communication 
  * purposes. If the application does not need any application data, the parameter can be null.
  * @param pid the value of the PID to filter for in incoming sections.
  * @param table_id the value of the table_id field to filter for in incoming sections
  * @param offset defines the offset within the section which the first byte of the posFilterDef and 
  * posFilterMask arrays is intended to match. The offset must be less than 31 as described in 
  * DAVIC part 10, section 115.3. The offset must be equal to or greater than 3.
  * @param posFilterDef defines values to match for bits in the section, as defined in clause E.8.1.
  * @param posFilterMask defines which bits in the section are to be compared against the values specified 
  * in the posFilterDef parameter, as defined in clause E.8.1.
  * @exception FilterResourceException if all the number of started SectionFilters for the parent SectionFilterGroup 
  * is already equal to the number of section filters associated with the SectionFilterGroup when it 
  * was created. Note that this is applied whether the parent section filter group is connected 
  * to a TS or not.
  * @exception IllegalFilterDefinitionException the filter definition specified is illegal either because the posFilterDef 
  * and posFilterMask arrays are not the same size or because their length is beyond the filtering 
  * capacity of the system or because the specified offset is too large.
  * @exception org.davic.mpeg.NotAuthorizedException if the information requested is scrambled and 
  * permission to descramble it is refused.
  * @exception ConnectionLostException if the parent SectionFilterGroup is in the ConnectionLost state and hence
  * is unable to satisfy the method call due to absence of resources or absence of sections to filter. 
  */
  public void startFiltering( Object appData, int pid, int table_id, int offset, byte  posFilterDef[], byte 
  posFilterMask[] ) 
 	throws FilterResourceException, IllegalFilterDefinitionException, 
		org.davic.mpeg.NotAuthorizedException, ConnectionLostException
	{
	}
 /**
  * Defines a SectionFilter object as filtering only for sections matching a specific PID and table_id, and 
  * where contents of the section match the specified filter pattern. The first byte of each array corresponds 
  * to the third byte of the section. If the parent SectionFilterGroup is attached to a TransportStream then 
  * filtering will start immediately.
  *
  * @param appData An object supplied by the application. This object will be delivered to the 
  * subscribed section filter listener as part of all SectionFilterEvents that will be generated 
  * because of this method call. The application can use this object for internal communication 
  * purposes. If the application does not need any application data, the parameter can be null.
  * @param pid the value of the PID to filter for in incoming sections.
  * @param table_id the value of the table_id field to filter for in incoming sections
  * @param posFilterDef defines values to match for bits in the section, as defined in clause E.8.1.
  * @param posFilterMask defines which bits in the section are to be compared against the values specified 
  * in the posFilterDef parameter, as defined in clause E.8.1.
  * @param negFilterDef defines values to match for bits in the section, as defined in clause E.8.1.
  * @param negFilterMask defines which bits in the section are to be compared against the values specified 
  * in the negFilterDef parameter, as defined in clause E.8.1.
  * @exception FilterResourceException if all the number of started SectionFilters for the parent SectionFilterGroup 
  * is already equal to the number of section filters associated with the SectionFilterGroup when it 
  * was created. Note that this is applied whether the parent section filter group is connected 
  * to a TS or not.
  * @exception IllegalFilterDefinitionException the filter definition specified is illegal either because the 
  * arrays posFilterDef, posFilterMask, negFilterDef, negFilterMask are not all the same size or 
  * because their length is beyond the filtering capacity of the system. 
  * @exception org.davic.mpeg.NotAuthorizedException if the information requested is scrambled and 
  * permission to descramble it is refused.
  * @exception ConnectionLostException if the parent SectionFilterGroup is in the ConnectionLost state and hence
  * is unable to satisfy the method call due to absence of resources or absence of sections to filter. 
  */
  public void startFiltering( Object appData, int pid, int table_id, byte posFilterDef[], 
	  byte posFilterMask[], byte negFilterDef[], byte negFilterMask[])
 	throws FilterResourceException, IllegalFilterDefinitionException, 
		org.davic.mpeg.NotAuthorizedException, ConnectionLostException
	{
	}
 /**
  * Defines a SectionFilter object as filtering only for sections matching a specific PID and 
  * table_id, and where contents of the section match the specified filter pattern. If the parent 
  * SectionFilterGroup is attached to a TransportStream then filtering will start immediately.
  *
  * @param appData An object supplied by the application. This object will be delivered to the 
  * subscribed section filter listener as part of all SectionFilterEvents that will be generated 
  * because of this method call. The application can use this object for internal communication 
  * purposes. If the application does not need any application data, the parameter can be null.
  * @param pid the value of the PID to filter for in incoming sections.
  * @param table_id the value of the table_id field to filter for in incoming sections
  * @param offset defines the offset within the section which the first byte of the posFilterDef, 
  * posFilterMask, negFilterDef and negFilterMask arrays is intended to match. The offset must be 
  * less than 31 as described in DAVIC part 10, section 115.3. The offset must be equal to or
  * greater than 3.
  * @param posFilterDef defines values to match for bits in the section, as defined in clause E.8.1.
  * @param posFilterMask defines which bits in the section are to be compared against the values specified 
  * in the posFilterDef parameter, as defined in clause E.8.1.
  * @param negFilterDef defines values to match for bits in the section, as defined in clause E.8.1.
  * @param negFilterMask defines which bits in the section are to be compared against the values specified 
  * in the negFilterDef parameter, as defined in clause E.8.1.
  * @exception FilterResourceException if all the number of started SectionFilters for the parent SectionFilterGroup 
  * is already equal to the number of section filters associated with the SectionFilterGroup when it 
  * was created. Note that this is applied whether the parent section filter group is connected 
  * to a TS or not.
  * @exception IllegalFilterDefinitionException the filter definition specified is illegal either because the 
  * posFilterDef, posFilterMask, negFilterDef, negFilterMask arrays are not all the same size or 
  * because their length is beyond the filtering capacity of the system or because the specified offset 
  * is too large.
  * @exception org.davic.mpeg.NotAuthorizedException if the information requested is scrambled and 
  * permission to descramble it is refused.
  * @exception ConnectionLostException if the parent SectionFilterGroup is in the ConnectionLost state and hence
  * is unable to satisfy the method call due to absence of resources or absence of sections to filter. 
  */
  public void startFiltering( Object appData, int pid, int table_id, int offset, byte posFilterDef[], byte posFilterMask[], 
	byte negFilterDef[], byte negFilterMask[])
 	throws FilterResourceException, IllegalFilterDefinitionException, org.davic.mpeg.NotAuthorizedException,
	ConnectionLostException
	{
	}
 /**
  * If the parent SectionFilterGroup is attached to a TransportStream then filtering for sections 
  * matching this SectionFilter object will stop. If the parent is not attached then should it 
  * become attached, filtering for sections matching this SectionFilter object will not start.
  */
  public void stopFiltering()
	{
	}

 /**
  * Sets the time-out for this section filter. When the time-out happens, a TimeOutEvent will be 
  * generated and sent to the SectionFilter object and filtering stops. For a SimpleSectionFilter 
  * this will be generated if no sections arrive within the specified period. For a TableSectionFilter, 
  * this will be generated if the complete table does not arrive within the specified time. For a 
  * RingSectionFilter, this will be generated if the specified time has elapsed since the arrival 
  * of the last section being successfully filtered.
  * Setting a time-out of 0 milliseconds has the effect of removing a possible time-out. A set 
  * time-out only applies to subsequent filter activations, not to a possible filter activation 
  * that is currently in progress when the call to this method is made. The default time-out value is 0.
  *
  * @param milliseconds the time out period
  * @exception IllegalArgumentException if the 'milliseconds' parameter is negative
  */

  public void setTimeOut(long milliseconds) throws IllegalArgumentException
	{
	}
 
 /**
  * Specifies an object to be notified of events relating to this SectionFilter object.
  * @param listener the object to be notified of events
  */
  public void addSectionFilterListener( SectionFilterListener listener)
	{
	}
 /**
  * Indicates that an object is no longer to be notified of events relating to this SectionFilter 
  * object. If the object was not specified as to be notified then this method has no effect.
  * @param listener the object no longer to be notified of events
  */
  public void removeSectionFilterListener( SectionFilterListener listener)
	{
	}
}

