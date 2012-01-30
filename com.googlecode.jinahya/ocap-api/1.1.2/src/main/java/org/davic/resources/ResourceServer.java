package org.davic.resources;

/**
 * The resource server interface is implemented by objects which manage low 
 * level scarce resources and inform applications of changes in their status 
 * which may have happened due to factors beyond the control of the application.
 *
 * Any application wishing to use a resource controlled by an object 
 * implementing the ResourceServer interface must request access to that 
 * resource via an object implementing the ResourceProxy interface, and should 
 * release the resource via the same object when exclusive access to the 
 * resource is no longer needed.
 *
 */

public interface ResourceServer
{
  /**
   * This method informs a resource server that a particular object should be 
   * informed of changes in the state of the resources managed by that server.
   *
   * @param listener the object to be informed of state changes
   */

   public abstract void addResourceStatusEventListener (ResourceStatusListener listener);

  /**
   * This method informs a resource server that a particular object is no 
   * longer interested in being informed about changes in state of resources 
   * managed by that server. If the object had not registered it's interest 
   * initially then this method has no effect.
   *
   * @param listener the object which is no longer interested
   * 
   */

  public void removeResourceStatusEventListener(ResourceStatusListener listener);
}






