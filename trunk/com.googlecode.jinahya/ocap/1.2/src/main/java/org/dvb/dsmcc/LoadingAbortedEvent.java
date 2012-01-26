package org.dvb.dsmcc;

/**
 * This event will be sent to the AsynchronousEventListener when an 
 * asynchronous loading operation is aborted. 
 * @since MHP 1.0.1
 */
public class LoadingAbortedEvent extends AsynchronousLoadingEvent 
{
	/**
	 * Creates a LoadingAbortedEvent object.
	 *
	 * @param aDSMCCObject the <code>DSMCCObject</code> that generated the event.
	 */
	public LoadingAbortedEvent(DSMCCObject aDSMCCObject) { super(aDSMCCObject);}
	
	/**
	 * Returns the DSMCCObject that generated the event. 
	 *
	 * @return the DSMCCObject whose loading was aborted
         */
        public Object getSource() {return null; } 
}

