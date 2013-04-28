package org.davic.mpeg.sections;
 /**
  * This class is used to report when a TransportStream which was available becomes no longer available or 
  * if the section filter resources are removed from a connected SectionFilterGroup. In this second case, 
  * the notifyRelease() method of the ResourceClient will also be called in addition to this event being generated. 
  */
public class ForcedDisconnectedEvent
	extends org.davic.resources.ResourceStatusEvent
{
 /**
  * This constructs a ForcedDisconnectedEvent for the specified SectionFilterGroup object.
  * @param f the SectionFilterGroup 
  */
  public ForcedDisconnectedEvent( SectionFilterGroup f)
	{
	  super(f);
	}
 /**
  * This returns the SectionFilterGroup object which filtered the data.
  */
  	public Object getSource()
	{
	  return null;
	}
}

