package org.davic.resources;

/**
 * This class is the parent class for events reporting changes in the status 
 * of resources.
 *
 */

public class ResourceStatusEvent extends java.util.EventObject
{
	/**
	 * This constructs a resource status event relating to the specified resource. 
	 * The precise class of the object will depend on the individual API using 
	 * the resource notification API.
	 *
	 * @param source the resource whose status changed
	 */

	public ResourceStatusEvent(Object source)
	{
		super(source);
	}
  /**
   * @return the object whose status changed
   */
  public Object getSource()
  {
	return null;
  }
}



