package org.ocap.hn.upnp.server;

/**
 * This interface represents a listener to UPnP local (server) 
 * device availability on a home network.
 *
 * @see org.ocap.hn.upnp.client.UPnPClientDeviceListener
 */
public interface UPnPManagedDeviceListener extends java.util.EventListener
{

    /**
     * Notifies the listener that a <code>UPnPManagedDevice</code> 
     * is about to be added to the home network by the local host. 
     * This listener method is called in response to the 
     * <code>UPnPManagedDevice.sendAlive()</code> method, and prior 
     * to the device being advertised on the home network. This 
     * allows an application to prepare for the 
     * advertisement of the new device, at a point where it can 
     * modify the device prior to the advertisement taking place.
     *
     * @param device The <code>UPnPManagedDevice</code> that is 
     *               about to be added.
     */
    public void notifyDeviceAdded(UPnPManagedDevice device);

    /**
     * Notifies the listener that a UPnPManagedDevice on the local 
     * host has been removed from the home network. This allows an  
     * application that is monitoring the managed devices to clean 
     * up after the device has been removed. 
     *
     * @param device The <code>UPnPManagedDevice</code> that was 
     *               removed.
     */
    public void notifyDeviceRemoved(UPnPManagedDevice device);

}
