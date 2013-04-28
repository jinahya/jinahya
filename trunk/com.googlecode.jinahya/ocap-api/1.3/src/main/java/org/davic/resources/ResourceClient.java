package org.davic.resources;

/**
 * This interface should be implemented by objects that use a scarce resource.
 */

public interface ResourceClient
{
  /**
   * A call to this operation informs the ResourceClient that another 
   * application has requested the resource accessed via the proxy parameter. 
   * If the ResourceClient decides to give up the resource as a result of this,
   * it should terminate its usage of proxy and return True, otherwise False. 
   * requestData may be used to pass more data to the ResourceClient so that 
   * it can decide whether or not to give up the resource, using semantics 
   * specified outside this framework;  for conformance to this framework, 
   * requestData can be ignored by the ResourceClient.
   *
   * @param proxy the ResourceProxy representing the scarce resource to the application
   * @param requestData application specific data
   * @return If the ResourceClient decides to give up the resource following
   * this call, it should terminate its usage of proxy and return True, 
   * otherwise False. 
   */

  public abstract boolean requestRelease(ResourceProxy proxy, Object requestData);

  /**
   * A call to this operation informs the ResourceClient that proxy is about 
   * to lose access to a resource. The ResourceClient shall complete any 
   * clean-up that is needed before the resource is lost before it returns
   * from this operation. This operation is not guaranteed to be allowed to 
   * complete before notifyRelease() is called. 
   *
   * @param proxy the ResourceProxy representing the scarce resource to the application
   */

  public abstract void release(ResourceProxy proxy);

  /**
   * A call to this operation notifies the ResourceClient that proxy has lost 
   * access to a resource. This can happen for two reasons:  either the 
   * resource is unavailable for some reason beyond the control of the 
   * environment (e.g. hardware failure) or because the client has been too 
   * long in dealing with a ResourceClient.release() call.
   *
   * @param proxy the ResourceProxy representing the scarce resource to the application
   *
   */

  public abstract void notifyRelease(ResourceProxy proxy);
}


