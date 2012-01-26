/*
 * HSceneChangeHandler.java
 *
 * Created on November 23, 2004, 10:51 AM
 * per ECO OCAP1.0-O-04.0694-2
 * jdb
 */
package org.ocap.ui;

import org.havi.ui.HScreenRectangle;

/**
  * Interface to be implemented by a privileged application in order to
  * handle requests to display an HScene not currently displayed, or
  * change the positions of HScenes on the screen, or move an HScene
  * in the 'z' order.
  */
public interface HSceneChangeRequestHandler
{
    /**
     * Tests whether an HScene display request can be allowed or not.  The implementation
     * SHALL call this method whenever the HScene is to be displayed including when
     * the HScene show or setvisible(true) methods are called.
     *
     * @param newScene the new HScene to be displayed
     *
     * @param oldScenes the existing displayed HScenes
     *
     * @return true if the new HScene is allowed to be displayed
     *      false if it is not allowed to be displayed
     */
    public boolean testShow( HSceneBinding newScene,
                                   HSceneBinding oldScenes[]);

    /**
     * Tests whether an HScene move request can be allowed or not.  Called when an HScene
     * is to be moved around the HScreen or resized.
     *
     * @param move the new location/size of the HScene.
     * @param currentScenes the existing HScenes including the current location
     *      of the HScene to move.
     *
     * @return True if the move can be made, otherwise returns false.
     */
    public boolean testMove( HSceneBinding move, HSceneBinding currentScenes[]);

    /**
     * Tests if an HScene z-order change request can be made or not.  Called when an HScene
     * is to be moved in z-order.
     *
     * @param currentScenes the existing displayed HScenes in z-order with entry 0 being
     *      the front.
     * @param currentOrder the existing position in the currentScene array of the HScene to move.
     * @param newOrder the new position that it is requested to move the HScene to.
     *
     * @return True if the move can be made, otherwise returns false.
     */
    public boolean testOrder( HSceneBinding currentScenes[], int currentOrder, int newOrder);
}


