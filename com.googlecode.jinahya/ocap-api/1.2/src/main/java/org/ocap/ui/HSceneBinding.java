/*
 * HSceneBinding.java
 *
 * Created on November 23, 2004, 10:47 AM
 * per ECO OCAP1.0-O-04.0694-2
 * jdb
 */
package org.ocap.ui;

import org.ocap.application.OcapAppAttributes;
import org.havi.ui.HScreenRectangle;

/**
  * Defines a binding between the area on a display that an HScene
  * is (or will) occupy and the associated application.
  */
public interface HSceneBinding
{
    /**
     * Gets the rectangle in normalised co-ordinates that the HScene is using
     * or has requested to use.
     *
     * @return Rectangle of the HScene corresponding to the application attributes.
     */
    public HScreenRectangle getRectangle();

    /**
     * Gets the attributes of the application associated with the HScene.
     */
    public OcapAppAttributes getAppAttributes();
}


