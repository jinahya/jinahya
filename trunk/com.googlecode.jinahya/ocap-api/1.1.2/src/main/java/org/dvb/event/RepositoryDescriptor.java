package org.dvb.event ;

 /**
  * An instance of this class will be sent to clients of the
  * DVB event API to notify them (through the interface
  * org.davic.resources.ResourceClient) when they are about to
  * lose, or have lost, access to an event source. This object
  * can be used by the application to get the name of the
  * repository from which it will no longer be able to receive
  * events.
  * All instances of RepositoryDescriptor are also instances of UserEventRepository.
  * This class is preserved for backwards compatibility with existing applications.
  */
public class RepositoryDescriptor 
    implements org.davic.resources.ResourceProxy {
   
	/**
	 * package-private constructor for parent classes
	 */
    RepositoryDescriptor () {}
    
    /**
     * Returns the name of the repository to which the lost, or about to be lost, user event belongs.
     *
     * @return String the name of the repository.
     */
    public String getName () {return null;}
    
    /**
     * Return the object which asked to be notified about withdrawal of the event
     * source. This is the object passed as the <code>ResoourceClient</code> to 
     * whichever of the various 'add' methods on EventManager was used by the
     * application to express interest in this repository.
     *
     * @return the object which asked to be notified about withdrawal of the event source.
     * If the <code>UserEventRepository</code> has not yet been added to an <code>EventManager</code>
     * then null shall be returned. Once the <code>UserEventRepository</code> has been added, the 
     * last used <code>ResourceClient</code> shall be returned even if the <code>UserEventRepository</code>
     * has been since removed. 
     */
    public org.davic.resources.ResourceClient getClient() { return null;}
}




