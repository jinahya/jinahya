package org.dvb.dsmcc;
import java.util.*;

 /** 
  * Listener for applications which perform asynchronous loading, in order
  * to be informed if the loading is done or if an error has occurred. 
  */
   public interface AsynchronousLoadingEventListener extends java.util.EventListener {
   	/**
   	 * Method called when an event is sent to the application.
   	 *
   	 * @param e an AsynchronousLoadingEvent event.
       */
   	public void receiveEvent(AsynchronousLoadingEvent e);
   }

