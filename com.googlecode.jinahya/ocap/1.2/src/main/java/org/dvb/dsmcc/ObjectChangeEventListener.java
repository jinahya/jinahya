
package org.dvb.dsmcc;

import java.util.*;



/** 
  * The objects that implements the ObjectChangeEventListener interface can receive
  * ObjectChangeEvent event.
  */

public interface ObjectChangeEventListener extends java.util.EventListener {
  
  /**
	* Send a ObjectChangeEvent to the ObjectChangeEventListener.
	* @param e the ObjectChangeEvent event.
	*/
  public void receiveObjectChangeEvent(ObjectChangeEvent e);
}

