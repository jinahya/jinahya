/*
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.awt;

import java.awt.Component;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/** 
 * The <code>MediaTracker</code> class is a utility class to track 
 * the status of a number of media objects. Media objects could 
 * include audio clips as well as images, though currently only 
 * images are supported. 
 * <p>
 * To use a media tracker, create an instance of  
 * <code>MediaTracker</code> and call its <code>addImage</code>  
 * method for each image to be tracked. In addition, each image can 
 * be assigned a unique identifier. This identifier controls the 
 * priority order in which the images are fetched. It can also be used 
 * to identify unique subsets of the images that can be waited on 
 * independently. Images with a lower ID are loaded in preference to 
 * those with a higher ID number. 
 *
 * <em><p>Note: The following code example includes classes that do
 * not appear in this specification. Their inclusion is purely to
 * serve as a demonstration.</em>
 *
 * <p>
 * Here is an example: 
 * <p>
 * <hr><blockquote><pre>
 * import java.applet.Applet;
 * import java.awt.Color;
 * import java.awt.Image;
 * import java.awt.Graphics;
 * import java.awt.MediaTracker;
 *
 * public class ImageBlaster extends Applet implements Runnable {
 *	MediaTracker tracker;
 *	Image bg;
 *	Image anim[] = new Image[5];
 *	int index;
 *	Thread animator;
 *
 *	// Get the images for the background (id == 0) 
 *	// and the animation frames (id == 1) 
 *      // and add them to the MediaTracker
 *	public void init() {
 *	    tracker = new MediaTracker(this);
 *	    bg = getImage(getDocumentBase(), 
 *                  "images/background.gif");
 *	    tracker.addImage(bg, 0);
 *	    for (int i = 0; i < 5; i++) {
 *		anim[i] = getImage(getDocumentBase(), 
 *                      "images/anim"+i+".gif");
 *		tracker.addImage(anim[i], 1);
 *	    }
 *	}
 *
 *	// Start the animation thread.
 *	public void start() {
 *	    animator = new Thread(this);
 *	    animator.start();
 *	}
 *
 *	// Stop the animation thread.
 *	public void stop() {
 *	    animator = null;
 *	}
 *
 *	// Run the animation thread.
 *	// First wait for the background image to fully load 
 *      // and paint.  Then wait for all of the animation 
 *	// frames to finish loading. Finally, loop and 
 *	// increment the animation frame index.
 *	public void run() {
 *	    try {
 *		tracker.waitForID(0);
 *		tracker.waitForID(1);
 *	    } catch (InterruptedException e) {
 *		return;
 *	    }
 *	    Thread me = Thread.currentThread();
 *	    while (animator == me) {
 *		try {
 *		    Thread.sleep(100);
 *		} catch (InterruptedException e) {
 *		    break;
 *		}
 *		synchronized (this) {
 *		    index++;
 *		    if (index >= anim.length) {
 *			index = 0;
 *		    }
 *		}
 *		repaint();
 *	    }
 *	}
 *
 *	// The background image fills the frame so we 
 *	// don't need to clear the applet on repaints. 
 *      // Just call the paint method.
 *	public void update(Graphics g) {
 *	    paint(g);
 *	}
 *
 *	// Paint a large red rectangle if there are any errors 
 *	// loading the images.  Otherwise always paint the 
 *	// background so that it appears incrementally as it 
 *      // is loading.  Finally, only paint the current animation 
 *	// frame if all of the frames (id == 1) are done loading,
 *	// so that we don't get partial animations.
 *	public void paint(Graphics g) {
 *	    if ((tracker.statusAll(false) & MediaTracker.ERRORED) != 0) {
 *		g.setColor(Color.red);
 *		g.fillRect(0, 0, size().width, size().height);
 *		return;
 *	    }
 *	    g.drawImage(bg, 0, 0, this);
 *	    if (tracker.statusID(1, false) == MediaTracker.COMPLETE) {
 *		g.drawImage(anim[index], 10, 10, this);
 *	    }
 *	}
 * }
 * </pre></blockquote><hr>
 * <p>
<!-- PBP/PP [6203790] -->
 * <em>Note that serialization and deserialization of MediaTracker objects
 * is not fully supported in this Profile; some MediaTracker state cannot
 * be serialized.</em>
 *
 * @version 	1.39, 01/23/03
 * @author 	Jim Graham
 * @since       JDK1.0
 */
public class MediaTracker implements java.io.Serializable
{
    /** 
     * Flag indicating that media is currently being loaded.
     * @see         java.awt.MediaTracker#statusAll
     * @see         java.awt.MediaTracker#statusID
     */
    public static final int LOADING = 1;

    /** 
     * Flag indicating that the downloading of media was aborted.
     * @see         java.awt.MediaTracker#statusAll
     * @see         java.awt.MediaTracker#statusID
     */
    public static final int ABORTED = 2;

    /** 
     * Flag indicating that the downloading of media encountered 
     * an error.
     * @see         java.awt.MediaTracker#statusAll
     * @see         java.awt.MediaTracker#statusID
     */
    public static final int ERRORED = 4;

    /** 
     * Flag indicating that the downloading of media was completed 
     * successfully.
     * @see         java.awt.MediaTracker#statusAll
     * @see         java.awt.MediaTracker#statusID
     */
    public static final int COMPLETE = 8;

    /** 
     * A given <code>Component</code> that will be
     * tracked by a media tracker where the image will
     * eventually be drawn.
     *
     * @serial
     * @see #MediaTracker(Component)
     */
     java.awt.Component target;

    // /** 
     // * The head of the list of <code>Images</code> that is being
     // * tracked by the <code>MediaTracker</code>.
     // *
     // * @serial
     // * @see #addImage(Image, int)
     // * @see #removeImage(Image)
     // */
     // MediaEntry head;

    /*
     * JDK 1.1 serialVersionUID 
     */
    private static final long serialVersionUID = -483174189758638095L;

    /** 
     * Creates a media tracker to track images for a given component.
     * @param     comp the component on which the images 
     *                     will eventually be drawn
     */
    public MediaTracker(java.awt.Component comp) { }

    /** 
     * Adds an image to the list of images being tracked by this media 
     * tracker. The image will eventually be rendered at its default 
     * (unscaled) size. 
     * @param     image   the image to be tracked
     * @param     id      an identifier used to track this image
     */
    public void addImage(java.awt.Image image, int id) { }

    /** 
     * Adds a scaled image to the list of images being tracked  
     * by this media tracker. The image will eventually be 
     * rendered at the indicated width and height.
     *
     * @param     image   the image to be tracked
     * @param     id   an identifier that can be used to track this image
     * @param     w    the width at which the image is rendered
     * @param     h    the height at which the image is rendered
     */
    public synchronized void addImage(java.awt.Image image, int id, int w, int
        h)
    { }

    /** 
     * Checks to see if all images being tracked by this media tracker 
     * have finished loading. 
     * <p>
     * This method does not start loading the images if they are not 
     * already loading. 
     * <p>
     * If there is an error while loading or scaling an image, then that 
     * image is considered to have finished loading. Use the 
     * <code>isErrorAny</code> or <code>isErrorID</code> methods to 
     * check for errors. 
     * @return      <code>true</code> if all images have finished loading, 
     *                       have been aborted, or have encountered 
     *                       an error; <code>false</code> otherwise
     * @see         java.awt.MediaTracker#checkAll(boolean)
     * @see         java.awt.MediaTracker#checkID
     * @see         java.awt.MediaTracker#isErrorAny
     * @see         java.awt.MediaTracker#isErrorID
     */
    public boolean checkAll() { return false; }

    /** 
     * Checks to see if all images being tracked by this media tracker 
     * have finished loading. 
     * <p>
     * If the value of the <code>load</code> flag is <code>true</code>, 
     * then this method starts loading any images that are not yet 
     * being loaded. 
     * <p>
     * If there is an error while loading or scaling an image, that 
     * image is considered to have finished loading. Use the 
     * <code>isErrorAny</code> and <code>isErrorID</code> methods to 
     * check for errors. 
     * @param       load   if <code>true</code>, start loading any 
     *                       images that are not yet being loaded
     * @return      <code>true</code> if all images have finished loading, 
     *                       have been aborted, or have encountered 
     *                       an error; <code>false</code> otherwise
     * @see         java.awt.MediaTracker#checkID
     * @see         java.awt.MediaTracker#checkAll()
     * @see         java.awt.MediaTracker#isErrorAny()
     * @see         java.awt.MediaTracker#isErrorID(int)
     */
    public boolean checkAll(boolean load) { return false; }

    /** 
     * Checks the error status of all of the images.
     * @return   <code>true</code> if any of the images tracked
     *                  by this media tracker had an error during 
     *                  loading; <code>false</code> otherwise
     * @see      java.awt.MediaTracker#isErrorID
     * @see      java.awt.MediaTracker#getErrorsAny
     */
    public synchronized boolean isErrorAny() { return false; }

    /** 
     * Returns a list of all media that have encountered an error.
     * @return       an array of media objects tracked by this 
     *                        media tracker that have encountered 
     *                        an error, or <code>null</code> if 
     *                        there are none with errors
     * @see          java.awt.MediaTracker#isErrorAny
     * @see          java.awt.MediaTracker#getErrorsID
     */
    public synchronized Object[] getErrorsAny() { return null; }

    /** 
     * Starts loading all images tracked by this media tracker. This 
     * method waits until all the images being tracked have finished 
     * loading. 
     * <p>
     * If there is an error while loading or scaling an image, then that 
     * image is considered to have finished loading. Use the 
     * <code>isErrorAny</code> or <code>isErrorID</code> methods to 
     * check for errors. 
     * @see         java.awt.MediaTracker#waitForID(int)
     * @see         java.awt.MediaTracker#waitForAll(long)
     * @see         java.awt.MediaTracker#isErrorAny
     * @see         java.awt.MediaTracker#isErrorID
     * @exception   InterruptedException  if another thread has 
     *                                     interrupted this thread
     */
    public void waitForAll() throws InterruptedException { }

    /** 
     * Starts loading all images tracked by this media tracker. This 
     * method waits until all the images being tracked have finished 
     * loading, or until the length of time specified in milliseconds  
     * by the <code>ms</code> argument has passed. 
     * <p>
     * If there is an error while loading or scaling an image, then  
     * that image is considered to have finished loading. Use the 
     * <code>isErrorAny</code> or <code>isErrorID</code> methods to 
     * check for errors. 
     * @param       ms       the number of milliseconds to wait 
     *                       for the loading to complete
     * @return      <code>true</code> if all images were successfully 
     *                       loaded; <code>false</code> otherwise
     * @see         java.awt.MediaTracker#waitForID(int)
     * @see         java.awt.MediaTracker#waitForAll(long)
     * @see         java.awt.MediaTracker#isErrorAny
     * @see         java.awt.MediaTracker#isErrorID
     * @exception   InterruptedException  if another thread has 
     *                                     interrupted this thread.
     */
    public synchronized boolean waitForAll(long ms) throws InterruptedException
    { return false; }

    /** 
     * Calculates and returns the bitwise inclusive <b>OR</b> of the 
     * status of all media that are tracked by this media tracker. 
     * <p>
     * Possible flags defined by the 
     * <code>MediaTracker</code> class are <code>LOADING</code>, 
     * <code>ABORTED</code>, <code>ERRORED</code>, and 
     * <code>COMPLETE</code>. An image that hasn't started 
     * loading has zero as its status. 
     * <p>
     * If the value of <code>load</code> is <code>true</code>, then
     * this method starts loading any images that are not yet being loaded. 
     *
     * @param        load   if <code>true</code>, start loading 
     *                            any images that are not yet being loaded
     * @return       the bitwise inclusive <b>OR</b> of the status of 
     *                            all of the media being tracked
     * @see          java.awt.MediaTracker#statusID(int, boolean)
     * @see          java.awt.MediaTracker#LOADING
     * @see          java.awt.MediaTracker#ABORTED
     * @see          java.awt.MediaTracker#ERRORED
     * @see          java.awt.MediaTracker#COMPLETE
     */
    public int statusAll(boolean load) { return 0; }

    /** 
     * Checks to see if all images tracked by this media tracker that 
     * are tagged with the specified identifier have finished loading. 
     * <p>
     * This method does not start loading the images if they are not 
     * already loading. 
     * <p>
     * If there is an error while loading or scaling an image, then that 
     * image is considered to have finished loading. Use the 
     * <code>isErrorAny</code> or <code>isErrorID</code> methods to 
     * check for errors. 
     * @param       id   the identifier of the images to check
     * @return      <code>true</code> if all images have finished loading, 
     *                       have been aborted, or have encountered 
     *                       an error; <code>false</code> otherwise
     * @see         java.awt.MediaTracker#checkID(int, boolean)
     * @see         java.awt.MediaTracker#checkAll()
     * @see         java.awt.MediaTracker#isErrorAny()
     * @see         java.awt.MediaTracker#isErrorID(int)
     */
    public boolean checkID(int id) { return false; }

    /** 
     * Checks to see if all images tracked by this media tracker that 
     * are tagged with the specified identifier have finished loading. 
     * <p>
     * If the value of the <code>load</code> flag is <code>true</code>, 
     * then this method starts loading any images that are not yet 
     * being loaded. 
     * <p>
     * If there is an error while loading or scaling an image, then that 
     * image is considered to have finished loading. Use the 
     * <code>isErrorAny</code> or <code>isErrorID</code> methods to 
     * check for errors. 
     * @param       id       the identifier of the images to check
     * @param       load     if <code>true</code>, start loading any 
     *                       images that are not yet being loaded
     * @return      <code>true</code> if all images have finished loading, 
     *                       have been aborted, or have encountered 
     *                       an error; <code>false</code> otherwise
     * @see         java.awt.MediaTracker#checkID(int, boolean)
     * @see         java.awt.MediaTracker#checkAll()
     * @see         java.awt.MediaTracker#isErrorAny()
     * @see         java.awt.MediaTracker#isErrorID(int)
     */
    public boolean checkID(int id, boolean load) { return false; }

    /** 
     * Checks the error status of all of the images tracked by this 
     * media tracker with the specified identifier. 
     * @param        id   the identifier of the images to check
     * @return       <code>true</code> if any of the images with the 
     *                          specified identifier had an error during 
     *                          loading; <code>false</code> otherwise
     * @see          java.awt.MediaTracker#isErrorAny
     * @see          java.awt.MediaTracker#getErrorsID
     */
    public synchronized boolean isErrorID(int id) { return false; }

    /** 
     * Returns a list of media with the specified ID that 
     * have encountered an error.
     * @param       id   the identifier of the images to check
     * @return      an array of media objects tracked by this media 
     *                       tracker with the specified identifier 
     *                       that have encountered an error, or 
     *                       <code>null</code> if there are none with errors
     * @see         java.awt.MediaTracker#isErrorID
     * @see         java.awt.MediaTracker#isErrorAny
     * @see         java.awt.MediaTracker#getErrorsAny
     */
    public synchronized Object[] getErrorsID(int id) { return null; }

    /** 
     * Starts loading all images tracked by this media tracker with the 
     * specified identifier. This method waits until all the images with 
     * the specified identifier have finished loading. 
     * <p>
     * If there is an error while loading or scaling an image, then that 
     * image is considered to have finished loading. Use the 
     * <code>isErrorAny</code> and <code>isErrorID</code> methods to 
     * check for errors. 
     * @param         id   the identifier of the images to check
     * @see           java.awt.MediaTracker#waitForAll
     * @see           java.awt.MediaTracker#isErrorAny()
     * @see           java.awt.MediaTracker#isErrorID(int)
     * @exception     InterruptedException  if another thread has 
     *                          interrupted this thread.
     */
    public void waitForID(int id) throws InterruptedException { }

    /** 
     * Starts loading all images tracked by this media tracker with the 
     * specified identifier. This method waits until all the images with 
     * the specified identifier have finished loading, or until the 
     * length of time specified in milliseconds by the <code>ms</code> 
     * argument has passed. 
     * <p>
     * If there is an error while loading or scaling an image, then that 
     * image is considered to have finished loading. Use the 
     * <code>statusID</code>, <code>isErrorID</code>, and
     * <code>isErrorAny</code> methods to check for errors. 
     * @param         id   the identifier of the images to check
     * @param         ms   the length of time, in milliseconds, to wait 
     *                           for the loading to complete
     * @see           java.awt.MediaTracker#waitForAll
     * @see           java.awt.MediaTracker#waitForID(int)
     * @see           java.awt.MediaTracker#statusID
     * @see           java.awt.MediaTracker#isErrorAny()
     * @see           java.awt.MediaTracker#isErrorID(int)
     * @exception     InterruptedException  if another thread has 
     *                          interrupted this thread.
     */
    public synchronized boolean waitForID(int id, long ms)
        throws InterruptedException
    { return false; }

    /** 
     * Calculates and returns the bitwise inclusive <b>OR</b> of the 
     * status of all media with the specified identifier that are 
     * tracked by this media tracker. 
     * <p>
     * Possible flags defined by the 
     * <code>MediaTracker</code> class are <code>LOADING</code>, 
     * <code>ABORTED</code>, <code>ERRORED</code>, and 
     * <code>COMPLETE</code>. An image that hasn't started 
     * loading has zero as its status. 
     * <p>
     * If the value of <code>load</code> is <code>true</code>, then
     * this method starts loading any images that are not yet being loaded. 
     * @param        id   the identifier of the images to check
     * @param        load   if <code>true</code>, start loading 
     *                            any images that are not yet being loaded
     * @return       the bitwise inclusive <b>OR</b> of the status of 
     *                            all of the media with the specified
     *                            identifier that are being tracked
     * @see          java.awt.MediaTracker#statusAll(boolean)
     * @see          java.awt.MediaTracker#LOADING
     * @see          java.awt.MediaTracker#ABORTED
     * @see          java.awt.MediaTracker#ERRORED
     * @see          java.awt.MediaTracker#COMPLETE
     */
    public int statusID(int id, boolean load) { return 0; }

    /** 
     * Removes the specified image from this media tracker.
     * All instances of the specified image are removed, 
     * regardless of scale or ID.
     * @param   image     the image to be removed
     * @see     java.awt.MediaTracker#removeImage(java.awt.Image, int)
     * @see     java.awt.MediaTracker#removeImage(java.awt.Image, int, int, int)
     * @since   JDK1.1
     */
    public synchronized void removeImage(java.awt.Image image) { }

    /** 
     * Removes the specified image from the specified tracking 
     * ID of this media tracker.
     * All instances of <code>Image</code> being tracked 
     * under the specified ID are removed regardless of scale.
     * @param      image the image to be removed
     * @param      id the tracking ID frrom which to remove the image
     * @see        java.awt.MediaTracker#removeImage(java.awt.Image)
     * @see        java.awt.MediaTracker#removeImage(java.awt.Image, int, int, int)
     * @since      JDK1.1
     */
    public synchronized void removeImage(java.awt.Image image, int id) { }

    /** 
     * Removes the specified image with the specified 
     * width, height, and ID from this media tracker.
     * Only the specified instance (with any duplicates) is removed.
     * @param   image the image to be removed
     * @param   id the tracking ID from which to remove the image
     * @param   width the width to remove (-1 for unscaled)
     * @param   height the height to remove (-1 for unscaled)
     * @see     java.awt.MediaTracker#removeImage(java.awt.Image)
     * @see     java.awt.MediaTracker#removeImage(java.awt.Image, int)
     * @since   JDK1.1
     */
    public synchronized void removeImage(java.awt.Image image, int id, int
        width, int height)
    { }
}
