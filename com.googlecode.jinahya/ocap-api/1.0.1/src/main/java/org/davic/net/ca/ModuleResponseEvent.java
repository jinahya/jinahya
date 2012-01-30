package org.davic.net.ca;

/** Base class for events that carry a message from the module
  */
public abstract class ModuleResponseEvent extends MessageEvent
{

/**
 * This constructor is provided for the use of implementations and specifications which 
 * extend the present document. Applications shall not define sub-classes of this class.
 * Implementations are not required to behave correctly if any such application defined 
 * sub-classes are used.
 */
 protected ModuleResponseEvent() {}
  
  /** Returns the CAModule that is the source of the event     */
  public Object getSource() {
    return null;
  }
  /** Returns the data bytes of the response. 
    * @return the data bytes of the response
    */
  public byte[] getData() {
    return null;
  }


}

