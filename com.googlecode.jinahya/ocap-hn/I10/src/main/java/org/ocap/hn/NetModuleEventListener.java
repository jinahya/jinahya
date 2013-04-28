package org.ocap.hn;

/**
 * NetModuleEventListener
 *
 * @author			Luyang Li (lly@research.panasonic.com)
 * @version			1.0
 *
 */

/**
 * NetModuleEvent callback interface. When a NetModule is registered or removed from
 * NetManager, or if the internal status of a NetModule changes, then system will 
 * notify all registered listeners.
 */
public interface NetModuleEventListener extends java.util.EventListener {
    /**
     * Callback function for NetModule event. Callee will be notified when NetModule
     * event happens
     * 
     * @param event
     *            NetModule event
     */
    public void notify(NetModuleEvent event);
}