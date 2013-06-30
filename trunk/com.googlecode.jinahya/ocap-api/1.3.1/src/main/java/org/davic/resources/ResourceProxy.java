package org.davic.resources;

/**
 * The resource proxy interface is implemented by objects which represent a  scarce resource to an application but where the actual scarce resource may  be a lower level object to which the application does not have direct access. The indirection provided by ResourceProxy allows the retaining of state  regardless of availability of the actual resource. Objects implementing the ResourceProxy interface are created by the  application program and may have a lifetime longer than the time a resource  is actually held by the application.  A resource may be acquired and released multiple times using the same ResourceProxy object. All interaction between applications and objects abstracting over the  resources themselves is carried out via an object implementing the  ResourceProxy interface.
 */

public interface ResourceProxy
{
  /**
   * @return the object which asked to be notified about withdrawal of the 
   * underlying physical resource from a resource proxy.
   */

  public abstract ResourceClient getClient();
}




