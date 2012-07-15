package org.dvb.dsmcc ;

 /**
  * This event indicates that the asynchronous loading was 
  * successful.
  */

public class SuccessEvent extends AsynchronousLoadingEvent {
	/**
	 * Creates a <code>SuccessEvent</code> object.
         *
         * @param o the <code>DSMCCObject</code> which was successfully loaded.
         */
	public SuccessEvent (DSMCCObject o) {super(o);}
	
   /** 
    * Returns the <code>DSMCCObject</code> which was successfully loaded.
    * 
    * @return the loaded <code>DSMCCObject</code>
    */
    public Object getSource () {return null;}
}

