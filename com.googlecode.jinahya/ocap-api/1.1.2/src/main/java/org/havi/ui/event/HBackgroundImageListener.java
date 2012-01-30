package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.util.EventListener;

/**
   The listener interface for receiving events related to {@link
   org.havi.ui.HBackgroundImage HBackgroundImage} objects.  
*/

public interface HBackgroundImageListener extends EventListener
{
    /**
     * Invoked when the data for an {@link org.havi.ui.HBackgroundImage 
     * HBackgroundImage} has been loaded.
     * 
     * @param e the event describing the loading
     */
    public void imageLoaded(HBackgroundImageEvent e);

    /**
     * Invoked when loading of an HBackgroundImage fails. 
     *
     * @param e the event describing the failure
     */
    public void imageLoadFailed(HBackgroundImageEvent e);

}

