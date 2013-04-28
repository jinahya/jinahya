package org.ocap.environment;
import java.util.EventListener;

/**
 * The listener interface for receiving environment events.
 */
public interface EnvironmentListener extends EventListener{
	/**
	 * Invoked when an application is to be notified of an
	 * event relating to an environment
	 * @param e the event 
	 */
	public void notify( EnvironmentEvent e);
}
