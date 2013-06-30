package org.ocap.hn.ruihsrc;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class represents a Manager that can be used to configure the
 * Remote User Interfaces published by the Host on a home network.
 */
public abstract class RemoteUIServerManager
{

    /**
     * Protected constructor not callable by applications.
     */
    protected RemoteUIServerManager()
    {
    }

    /**
     * Sets a remote user interface list.  If the parameter is null any
     * RUI’s that were previously set are removed.
     * 
     * @param uiList An <code>InputStream</code> representing the XML document for
     *			the UI List. 
     * 
     * @throws IllegalArgumentException if the parameter is not null and the XML
     * document is detected to be invalid. 
     *
     * @throws IOException if an I/O error occurs on the uiList
     *
     * @throws SecurityException if the calling application does not have
     * MonitorAppPermission("handler.homenetwork")
     * 
     */
    public abstract void setUIList(InputStream uiList) throws IOException;

    /**
     * Get the RemoteUIServerManager singleton.
     * @return The RemoteUIServerManager singleton.
     */
    public static RemoteUIServerManager getInstance()
    {
        return null;
    }

}
