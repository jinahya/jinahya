/*
 * HSceneManager.java
 *
 * Created on November 23, 2004, 10:55 AM
 * per ECO OCAP1.0-O-04.0694-2
 * jdb
 */
package org.ocap.ui;

import org.ocap.application.OcapAppAttributes;

/**
 * This class represents a manager that lets an application register a handler
 * to requested HScene changes within a logical HScreen composited with all HScenes.
 * In addition, HScene z-ordering can be queried using this manager.
 */
public abstract class HSceneManager
{
    /**
     * Protected default constructor.
     **/
    protected HSceneManager()
    {
    }

    /**
     * Gets the singleton instance of the HScene manager.  The singleton MAY be
     * implemented using application or implementation scope.
     *
     * @return The HScene manager.
     **/
    public static HSceneManager getInstance()
    {
        return null;
    }

    /**
     * Lets an application add itself as the HScene change request handler.  If
     * a handler is already registered when this method is called, it is replaced
     * with the parameter handler.
     *
     * @param handler HSceneChangeRequestHandler for requests to HScene z-ordering changes.
     *      If this parameter is null the current handler is removed.
     *
     * @throws SecurityException if the caller does not have
     *      MonitorAppPermission("handler.resource").
     */
    public void setHSceneChangeRequestHandler(HSceneChangeRequestHandler handler)
    {
    }

    /**
     * Gets the current HScene z-ordering.  The array of attributes returned is ordered
     * increasing in z-order where the first entry (0) corresponds to an HScene on top
     * and the last entry is on bottom.
     *
     * @return Array of application attributes corresponding to HScene instances in z-order.
     *
     * @throws SecurityException if the caller does not have
     *      MonitorAppPermission("handler.resource").
     */
    public static OcapAppAttributes [] getHSceneOrder()
    {
        return null;
    }

    /**
     * Gets the current HScene z-order location for a specific HScene.  Applications
     * can call this to determine where their HScene is located.
     *
     * @return HScene z-order location for the calling application.  The value is ordered
     *      increasing in z-order where 0 is on top and all other values are in increasing
     *      order below the top.  A value of -1 indicates the HScene has not been ordered.
     */
    public int getAppHSceneLocation()
    {
        return -1;
    }
}


