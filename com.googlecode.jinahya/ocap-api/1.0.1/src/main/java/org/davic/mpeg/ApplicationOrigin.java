/**
  * This class is used by applications to find references to the service and 
  * transport stream from which they were originally downloaded in a 
  * broadcast environment.
  */
package org.davic.mpeg;

public class ApplicationOrigin
{
  private ApplicationOrigin()
  {
  }
	/**
	  * @return the service that contained the root object of the application, 
	  * or null if the application was not contained in a service (e.g. in
	  * the case of a receiver-resident application). 
	  */
	public static Service getService()
	{
		return null;
	}
}

