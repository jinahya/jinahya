
package org.dvb.dsmcc;

import java.util.*;
import org.davic.net.*;

/** 
  * This class describes an object change event that is used to monitor
  * the arrival of a new version of a <code>DSMCCObject</code>.
  * For files carried in a DSMCC object carousel, when a change in a module is
  * detected, this event shall 
  * be sent to all registered listeners for all objects carried in that module.
  */

public class ObjectChangeEvent extends java.util.EventObject {


 /** 
	* Creates an ObjectChangeEvent indicating that a new version of the
	* monitored DSMCC Object has been detected. It is up to the application
	* to reload the new version of the object.
	* @param source the DSMCCObject whose version has changed
      	* @param aVersionNumber the new version number.
	*/
  public ObjectChangeEvent(DSMCCObject source, int aVersionNumber)
	{super(source);
	}
  
  /** 
	* This method is used to get the new version number of the 
	* monitored <code>DSMCCObject</code>. For files carried in
	* a DSMCC object carousel, this method shall return the version number of the module
	* carrying the file.
        *
      	* @return the new version number.
	*/
  public int getNewVersionNumber()
	{return 0 ;
	}

  /**
   * Returns the DSMCCObject that has changed
   *
   * @return the DSMCCObject that has changed
   */
  public Object getSource() {return null;}  
 }

