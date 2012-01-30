package org.davic.net.ca;

/**
 * In systems based upon the DVB Common Interface this event is generated in response to the 
 * Host Control replace /clear_replace requests. NOTE:This event is for information only. The 
 * platform is responsible for implementing the requests from the CA system. See also R206.
 */

public class PIDChangeEvent extends CAEvent {


  /** Constructor for the event
    * @param oldPid the PID to be replaced.
    * @param newPid the new replacement PID.
    * @param caModule the CAModule object which is the source of the event.
    */
  public PIDChangeEvent(short oldPid, short newPid, Object caModule) {
  }


  /** Returns the PID of the previous elementary stream.
    * @return the previous PID
    */
  public short getReplacedPID() {
    return (short) 0;
  }
  
  /** Returns the PID of the new elementary stream that substitutes the
    * previous one.
    * @return the new PID to replace the previous one.
    */
  public short getReplacementPID() {
    return (short) 0;
  }

  /** Returns the CAModule that is the source of the event.
    */
  public Object getSource() {
    return null;
  }
}



