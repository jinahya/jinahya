package org.davic.net.ca;

/** This event informs that a new CA module has been added.
  * In the case of a CA module based on the DVB common interface, this event shall only be 
  * generated when module initialisation has been completed. Hence an application has no means 
  * to detect when a module is inserted but has not yet been initialised.
  */
public class NewModuleEvent extends CAEvent {

  /** Constructor for the event
    * @param caModule the CAModule object representing the new module
    * @param caModuleManager the CAModuleManager object representing the 
    *                        module manager that is the source of the event
    */
  public NewModuleEvent(CAModule caModule, Object caModuleManager) {
  }

   
  /** Returns the CAModule object representing the new module 
    * @return the CAModule object representing the new module
    */
  public CAModule getModule() {
    return null;
  }
	
  /** Returns the CAModuleManager that is the source of the event 
    */
  public Object getSource() {
    return null;
  }

}

