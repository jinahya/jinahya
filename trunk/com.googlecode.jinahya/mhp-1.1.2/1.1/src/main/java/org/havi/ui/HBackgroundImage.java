package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import org.havi.ui.event.HBackgroundImageListener;

/**
   This class represents a background image. Images of this class can
   be used as full screen backgrounds outside the java.awt framework.  

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr><td colspan=5>None.</td></tr>
  </table>
  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr><td colspan=4>None.</td></tr>
  </table>

*/

public class HBackgroundImage
{
    /**
     * Create an HBackgroundImage
     * object. Loading of the data for the object shall not happen at
     * this time.
     *
     * @param filename the name of the file to use as the source of
     * data in a platform-specific URL format.
     */
    public HBackgroundImage(String filename)
    {
    }

    /**
     * Create an HBackgroundImage object from an array of bytes encoded in the
     * same encoding format as when reading this type of image data
     * from a file. 
     * <p>
     * If this constructor succeeds then the object will automatically
     * be in the loaded state and calling the {@link
     * org.havi.ui.HBackgroundImage#load} method shall
     * immediately generate an {@link
     * org.havi.ui.event.HBackgroundImageEvent}
     * reporting success.
     * <p>
     * If the byte array does not contain a valid image then this
     * constructor may throw a
     * <code>java.lang.IllegalArgumentException</code>. 
     * <p>
     * Calling the {@link org.havi.ui.HBackgroundImage#flush}
     * method on an object built with this constructor shall have no
     * effect.
     *
     * @param pixels the data for the HBackgroundImage object encoded
     * in the specified format for image files of this type.  
     */
    public HBackgroundImage(byte pixels[])
    {
    }
    
    /**
     * Create an HBackgroundImage
     * object. Loading of the data for the object shall not happen at
     * this time.
     * 
     * @param contents a URL referring to the data to load.
     */
    public HBackgroundImage(java.net.URL contents)
    {
    }

    /**
     * Load the data for this object. This method is asynchronous. The
     * completion of data loading is reported through the listener
     * provided.
     * <p>
     * Multiple calls to <code>load</code> shall each add an extra
     * listener, all of which are informed when the loading is
     * completed. If load is called with the same listener more than
     * once, the listener shall then receive multiple copies of a single
     * event. 
     *
     * @param l the listener to call when loading of data is
     * completed.  
     *
     * @see org.havi.ui.event.HBackgroundImageEvent
     */
    public void load(HBackgroundImageListener l)
    {
    }

    /**
     * Determines the height of the image. This is returned in pixels
     * as defined by the format of the image concerned. If this
     * information is not known when this method is called then -1 is
     * returned.
     * <p>
     * The image must have been successfully loaded to completion
     * before this information is guaranteed to be available. It is
     * implementation specific whether this information is available
     * before the image is successfully loaded to completion.  An
     * image whose loading failed for any reason shall be considered
     * as having this information unavailable.
     *
     * @return the height of the image
     */
    public int getHeight()
    {
        return 0;
    }

    /**
     * Determines the width of the image. This is returned in pixels
     * as defined by the format of the image concerned. If this
     * information is not known when this method is called then -1 is
     * returned.
     * <p>
     * The image must have been successfully loaded to completion
     * before this information is guaranteed to be available. It is
     * implementation specific whether this information is available
     * before the image is successfully loaded to completion.  An
     * image whose loading failed for any reason shall be considered
     * as having this information unavailable.
     *
     * @return the width of the image 
     */
    public int getWidth()
    {
        return 0;
    }

    /**
     * Flush all the resources used by this image. This includes any
     * pixel data being cached as well as all underlying system
     * resources used to store data or pixels for the image. After
     * calling this method the image is in a state similar to when it
     * was first created without any load method having been
     * called. When this method is called, the image shall not be in
     * use by an application. Resources related to any {@link
     * org.havi.ui.HBackgroundDevice} are not
     * released.  
     */
    public void flush()
    {
    }
}

