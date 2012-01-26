package org.dvb.dsmcc ;

/**
 * The object requested is available in an alternate ServiceDomain.
 * When an application attempts to asynchronously load an object that
 * has itself a LiteOptionProfileBody IOR or that has a parent directory
 * that has a LiteOptionProfileBody IOR, this event shall be sent to the
 * application.
 * There is no implicit mounting by the implementation of the carousel that 
 * actually contains the object. This event is not sent if the Service Domain 
 * that actually contains the DSMCCObject is already mounted.
 */
public class ServiceXFRErrorEvent extends AsynchronousLoadingEvent {
   	/**
	 * Creates a ServiceXFRErrorEvent object.
         *
         * @param o the DSMCCObject that generated the event.
         * @param ref the address of an alternate ServiceDomain 
         * where the object can be found.
         */

	public ServiceXFRErrorEvent (DSMCCObject o, ServiceXFRReference ref) {
		super(o);}

	/**
	 * Returns the DSMCCObject that generated the event.
         *
	 * @return the DSMCCObject that generated the event.
	 */
	public java.lang.Object getSource() {return null;}

	
   /**
    * This method is used to get a reference to the service domain that 
    * contains the requested object.
    * @return The address of an alternate ServiceDomain where the object can
    * be found.
    */            
    public ServiceXFRReference getServiceXFR () {return null;}
}

