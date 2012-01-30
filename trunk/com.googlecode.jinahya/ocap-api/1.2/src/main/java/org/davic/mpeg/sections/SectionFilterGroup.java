package org.davic.mpeg.sections;
 /**
  * This class represents a group of MPEG-2 section filters to be activated and de-activated as 
  * an atomic operation. The purpose of this class is to minimize the potential for resource 
  * deadlock between independent pieces of application(s).
  */
public class SectionFilterGroup
	implements 	org.davic.resources.ResourceProxy,
			org.davic.resources.ResourceServer
{
 /**
  * Creates a section filter group object with an associated number of section filters needed to 
  * be reserved when the object is to be connected to an active source of MPEG-2 sections. The 
  * object will have a default high resource priority should the number of section filters 
  * available to the package become insufficient.
  * @param numberOfFilters the number of section filters needed for the object.
  * @throws IlegalArgumentException if numberOfFilters is less than one.
  */
  public SectionFilterGroup(int numberOfFilters)
	{
	}
 /**
  * Creates a section filter group object with an associated number of section filters needed to 
  * be reserved when the object is to be connected to an active source of MPEG-2 sections.
  * @param numberOfFilters the number of section filters needed for the object
  * @param resourcePriority the resource priority of the object should the number of section filters 
  * available to the package become insufficient. High priority is indicated by true and low priority 
  * by false. The scope of the resourcePriority shall be a single application only.
  * @throws IlegalArgumentException if numberOfFilters is less than one.
  */
  public SectionFilterGroup(int numberOfFilters, boolean resourcePriority)
	{
	}
 /**
  * Creates a new simple section filter object within the parent section filter group. On 
  * activation (succesfull startFiltering) the SimpleSectionFilter object will use section filters 
  * from the total specified when the parent SectionFilterGroup was created. The section filter 
  * object will have a buffer suitable to hold a default long section.
  */
  public SimpleSectionFilter newSimpleSectionFilter()
	{
	  return null;
	}
 /**
  * Creates a new simple section filter object within the parent section filter group. On 
  * activation (succesfull startFiltering) the SimpleSectionFilter object will use section filters 
  * from the total specified when the parent SectionFilterGroup was created.
  * @param sectionSize specifies the size in bytes of the buffer to be created to hold data captured by the 
  * SectionFilter. If sections are filtered which are larger than this then the extra data will be 
  * dropped and filtering continue without any notification to the application.
  * @throws IllegalArgumentException if sectionSize <1.
  */
  public SimpleSectionFilter newSimpleSectionFilter(int sectionSize)
	{
	  return null;
	}
 /**
  * Creates a new ring section filter within the parent section filter group. On activation 
  * (succesfull startFiltering) the new RingSectionFilter object will use section filters from the 
  * total specified when the parent SectionFilterGroup was created.
  * @param ringSize the number of Section objects to be created for use in the ring.
  * @throws IllegalArgumentException if ringSize <1.  
  */
  public RingSectionFilter newRingSectionFilter(int ringSize)
	{
	  return null;
	}
 /**
  * Creates a new ring section filter within the parent section filter group. On activation 
  * (succesfull startFiltering) the new RingSectionFilter object will use section filters from the 
  * total specified when the parent SectionFilterGroup was created.
  * @param ringSize the number of Section objects to be created for use in the ring.
  * @param sectionSize the size in bytes of the buffer for each Section object. If sections are filtered which 
  * are larger than this then the extra data will be dropped and filtering continue without any 
  * notification to the application.
  * @throws IllegalArgumentException if ringSize <1.  
  * @throws IllegalArgumentException if sectionSize <1.
  */
  public RingSectionFilter newRingSectionFilter(int ringSize, int 
  sectionSize)
	{
	  return null;
	}
 /**
  * Creates a new table section filter object within the parent section filter group. On activation 
  * (succesfull startFiltering) the new TableSectionFilter object will use section filters from the 
  * total specified when the parent SectionFilterGroup was created. Each Section created for the 
  * table section filter object will have a buffer suitable to hold a default long section.
  */
  public TableSectionFilter newTableSectionFilter()
	{
	  return null;
	}
 /**
  * Creates a new table section filter object within the parent section filter group. On activation 
  * (succesfull startFiltering) the new TableSectionFilter object will use section filters from the 
  * total specified when the parent SectionFilterGroup was created.
  * @param sectionSize specifies the size in bytes of the buffer to be created to hold data captured by the 
  * SectionFilter. When the first section has been captured and the total number of sections in the 
  * table known, each Section created will have a buffer of this size. If sections are filtered which 
  * are larger than this then the extra data will be dropped and filtering continue without any 
  * notification to the application.
  * @throws IllegalArgumentException if sectionSize <1.
  */
  public TableSectionFilter newTableSectionFilter(int sectionSize)
	{
	  return null;
	}
 /**
  *  Connects a SectionFilterGroup to an MPEG-2 transport stream. The SectionFilterGroup will 
  * attempt to acquire the number of section filters specified when it was created. Any SectionFilter 
  * objects which are part of the group concerned and whose filtering has been started will 
  * become active and start filtering the source for sections matching the specified patterns. A 
  * call to attach on a attached SectionFilterGroup will be treated as a detach followed by the 
  * new attach.
  * @param stream specifies the source of MPEG-2 sections for filtering
  * @param client specifies an object to be notified if the section filters acquired during this method are 
  * later removed by the environment for any reason.
  * @param requestData application specific data for use by the resource notification API
  * @exception FilterResourceException if reserving the specified section filters fails
  * @exception InvalidSourceException if the source is not a valid source of MPEG-2 sections.
  * @exception org.davic.mpeg.TuningException if the source is not currently tuned to 
  * @exception org.davic.mpeg.NotAuthorizedException if the information requested is scrambled and 
  * permission to descramble it is refused.
  */
   public void attach(org.davic.mpeg.TransportStream stream, org.davic.resources.ResourceClient
	client, Object requestData) 
 	throws FilterResourceException, InvalidSourceException, org.davic.mpeg.TuningException,org.davic.mpeg.NotAuthorizedException 
	{
	}
 /**
  * Returns a SectionFilterGroup to the disconnected state. When called for a SectionFilterGroup 
  * in the connected state, it disconnects a SectionFilterGroup from a source of MPEG-2 sections. 
  * The section filters held by the SectionFilterGroup will be released back to the environment. 
  * Any running filtering operations will be terminated. This method will have no effect for 
  * SectionFilterGroups already in the disconnected state. 
  */
  public void detach()
	{
	}
 /**
  *  Returns the MPEG-2 transport stream to which a SectionFilterGroup is currently connected. 
  * If the SectionFilterGroup is not connected to a transport stream then the method will return 
  * null.
  */
   public org.davic.mpeg.TransportStream getSource()
	{
	  return null;
	}
 /**
  * Returns the ResourceClient object specified in the last call to the attach() method as to be 
  * notified in the case that the section filters acquired by the SectionFilterGroup during that 
  * call to attach() are removed by the environment for any reason. If the SectionFilterGroup is 
  * not connected to a source then the method will return null.
  */
  public org.davic.resources.ResourceClient getClient()
	{
	  return null;
	}
 /**
  * Specifies an object to be notified of changes in the status of resources related to a 
  * SectionFilterGroup object. If this call is made more than once, each specified listener will 
  * be notified of each change in resource status.
  * @param listener the object to be notified
  */
  public void addResourceStatusEventListener 
  (org.davic.resources.ResourceStatusListener listener)
	{
	}
 /**
  * Indicates that an object is no longer to be notified of changes in the status of resources as 
  * setup by addResourceStatusEventListener. If an object was not specified as to be notified 
  * then this method will be have no effect.
  * @param listener the object no longer to be notified
  */
  public void removeResourceStatusEventListener (org.davic.resources.
  						 ResourceStatusListener listener)
	{
	}
}

