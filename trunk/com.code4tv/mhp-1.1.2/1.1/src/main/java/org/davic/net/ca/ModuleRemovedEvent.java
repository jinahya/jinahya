package org.davic.net.ca;

/** This event informs that a CA module has been removed.
  */
public class ModuleRemovedEvent extends CAEvent {

  /** Constructor for the event
    * @param slotNumber the slot number
    * @param caModuleManager the CAModuleManager object 
    * that is the source of the event
    */
  public ModuleRemovedEvent(int slotNumber, Object caModuleManager) {
  }

  /** Returns the slot number of the slot where the module
    * was removed from 
    * @return the number of the slot from which the module was removed
    */
  public int getSlotNumber() {
    return 0;
  }

  /** Returns the CAModuleManager that is the source of the event 
    */
  public Object getSource() {
    return null;
  }
}

