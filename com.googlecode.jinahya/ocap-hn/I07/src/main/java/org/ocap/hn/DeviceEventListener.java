package org.ocap.hn;

/**
 * DeviceEvent callback interface. When a Device is registered or removed from
 * NetManager, or if the internal status of a Device changes, then system will
 * notify all registered listeners.
 */
public interface DeviceEventListener extends java.util.EventListener {
    /**
     * Callback function for Device events.
     * 
     * @param event
     *            Device event
     */
    public void notify(DeviceEvent event);
}