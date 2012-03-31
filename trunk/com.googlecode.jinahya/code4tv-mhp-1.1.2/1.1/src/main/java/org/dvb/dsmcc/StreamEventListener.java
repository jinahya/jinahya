
package org.dvb.dsmcc;

import java.util.*;



/** 
  * Objects that implement the StreamEventListener interface can receive
  * StreamEvent event.
  */

public interface StreamEventListener extends java.util.EventListener {
  
  /**
	* Send a StreamEvent to the StreamEventListener.
	* @param e the StreamEvent event.
	*/
  public void receiveStreamEvent(StreamEvent e);
}

