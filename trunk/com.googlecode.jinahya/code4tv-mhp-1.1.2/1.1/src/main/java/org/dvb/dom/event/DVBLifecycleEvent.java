package org.dvb.dom.event;

/**
 * another option for the java.util.EventObject:
 * implements org.w3c.dom.events.Event
 * and so on.
 * 
 * @author KIKE
 *
 */
public class DVBLifecycleEvent extends java.util.EventObject {
	
public DVBLifecycleEvent(){
	super(null);
}

public DVBLifecycleEvent(String typearg, boolean canbubblearg,boolean candisablearg, long detailarg){
	super(null);
}

public long getDetail(){
	return 0;
}
}
