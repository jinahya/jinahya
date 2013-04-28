
package org.ocap.hn;

/**
 *	
 * This interface represents a handler passed to asynchronous methods
 * @author Patrick Ladd, Vidiom
 *	
 */
public interface NetActionHandler
{

    /**
     *	Notifies the application of an action event.  This method is called
     *	by the implementation when a response to an action or a failure for
     *	the action is detected.
     */
    public void notify(NetActionEvent event);
}
