package org.davic.net.ca;
import org.davic.resources.*;
import org.davic.mpeg.Service;
	
/** The CA module manager is an object that manages available CA modules. <p>
  * There is only one instance of the CAModuleManager in a 
  * receiver and it can be retrieved using the getInstance method.
  */
public class CAModuleManager implements ResourceServer {

	/** dummy private constructor so that JavaDoc doesn't generate
	one */

	private CAModuleManager() {
	}

  /** Returns the instance of the CAModuleManager class. 
    * @return the CA module manager object instance
    */
  public static CAModuleManager getInstance() {
    return null;
  }
	
  /** Returns the number of connected CA modules. 
    * @return number of connected CA modules
    */
  public int numberOfModules() {
    return 0;
  }
  
  /** Returns all available CA modules. <p>
    * If there are no available modules, returns an array whose length is 0.
    * @return an array containing all available CA modules
    */
  public CAModule[] getModules() {
    return null;
  }
  
  /** Returns all available modules whose CASystemID matches  
    * with the CASystemID of a CA system used to scramble 
    * this service. <p>
    * If there are no applicable modules, returns an array whose length is 0.
    * If the service passed as a parameter is not scrambled, returns an empty 
    * array whose length is 0.
    * @param s a service that is scrambled
    * @return an array of CA modules
    */
  public CAModule[] getModules(Service s) {
    return null;
  }
  
  /** Registers a new CA event listener to CAModuleManager.
    * @param l the listener to be registered
    */ 
  public void addCAListener(CAListener l) {
  }

  /** Removes a registered listener from CAModuleManager.
    * @param l the listener to be removed
    */
  public void removeCAListener(CAListener l) {
  }
  
  /** Registers a listener for the MMI related 
    * events.
    * There can be only one MMI listener registered at a time.
    * If an application has registered (and not removed) a listener to handle 
    * the MMI dialogues and if an MMI dialogue is required, this causes the platform 
    * to ask the MMI listener to handle the MMI dialogues.If there is no 
    * application registered to handle the MMI dialogues, these will be handled by 
    * the platform.
    * @param listener the listener to be registered
    * @exception MMIListenerAlreadyRegisteredException raised if there is 
    *            already a listener registered
    * @exception SecurityException raised if the application does not have an instance of
    * <code>CAPermission</code> with the "MMI" name. 
    */
  public void addMMIListener(MMIListener listener) throws
    CAException {
    }
  
  /** Removes a registered listener for the MMI related 
    * events.
    * @param listener the listener to be removed
    */
  public void removeMMIListener(MMIListener listener) {
  }
  
  /** Registers a listener for the resource status messages.
    * @param l the listener to be registered
    */
  public void addResourceStatusEventListener(ResourceStatusListener l) {
  }
  
  /** Removes a registered listener for the resource status messages. 
    * @param l the listener to be removed
    */
  public void removeResourceStatusEventListener(ResourceStatusListener l) {
  }
  
}

