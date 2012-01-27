

package org.dvb.ui;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.media.Clock;
import javax.media.Time;
import javax.media.IncompatibleTimeBaseException;

/**
 * A <code>BufferedAnimation</code> is an AWT component that maintains a queue
 * of one or more image buffers.  This permits efficient flicker-free
 * animation by allowing a caller to draw to an off-screen buffer,
 * which the system then copies to the framebuffer in coordination with
 * the video output subsystem.  This class also allows an application
 * to request a series of buffers, so that it can get a small number
 * of frames ahead in an animation.  This allows an application to
 * be robust in the presence of short delays, e.g. from garbage collection.
 * A relatively small number of buffers is recommended, perhaps three or 
 * four.  A BufferedAnimation with one buffer provides little or no protection
 * from pauses, but does provide double-buffered animation.
 * <p>
 * This class can be used for frame-synchronous animation.  When animation
 * is in progress, it maintains a count of the frame number in the underlying
 * video output device.  This frame number increases monotonically by one
 * for each video frame output.  It is not influenced by trick play of any
 * video that might be playing on the same screen.  However, the framerate
 * of the <code>BufferedAnimation</code> may be 
 * determined by the framerate of such
 * video; see <code>setFramerate(float)</code> and 
 * <code>getFramerate()</code> for details.
 * <p>
 * The implementation shall prevent tearing artifacts whenever possible.
 * The maximum size and animation rate that can be achieved without
 * tearing artifacts may be specified by the system model of a 
 * specification that includes this class.  If it is necessary to avoid
 * a tearing artifact, an implementation shall delay the copying of an 
 * internal buffer to the frame buffer by up to one frame.
 * <p>
 * The size of this component is set using the normal AWT mechanisms.
 * When the method <code>setBuffers(Dimension, int, boolean, boolean)</code>
 * is called,
 * initialization is performed.  At this time, the size of the
 * graphics buffers is set.  
 * <p>
 * When one of this component's buffers is copied to the frame buffer,
 * it is done without regard to any AWT components which may overlap with
 * this component.  This is like the behavior when an application draws
 * directly to the screen using a graphics object obtained with
 * <code>java.awt.Component.getGraphics().</code>
 * <p>
 * When the system copies a buffer to the frame buffer for a given
 * frame <i>f</i>, it shall select the valid buffer associated with
 * the highest-numbered frame <i>f<sub>b</sub></i> such that 
 * <i>f<sub>b</sub></i> &lt;= <i>f</i>.  A buffer is valid if
 * <code>startFrame(int)</code> has been called for that buffer, 
 * <code>finishFrame(int)</code>
 * has been called, and the given buffer has not been re-used for
 * a subsequent frame as a result of another call to 
 * <code>startFrame(int)</code>.
 * <p>
 * The animation task proceeds at a high relative CPU priority, and can
 * be considered to execute at a priority greater than Java's
 * <code>Thread.NORM_PRIORITY</code>.  However, when no new 
 * image buffer is ready, the system task must always block until one
 * is.  Drawing into the buffer is done within a Java thread, which is
 * subject to the normal scheduling guarantees.  In this way, a CPU-bound
 * caller
 * can avoid starving more important activities, such as responding to
 * remote control input.  CPU-bound applications may wish to invoke
 * <code>Thread.yield()</code> after each frame, however, particularly
 * if the application's
 * animation thread is at the same priority level as other application
 * threads.
 * <p>
 * A component that is not visible and is in the started state will
 * run, but it will not display any buffers to the screen.  It will
 * block in the call to <code>startFrame()</code>
 * until the component becomes visible,
 * or until it is too late to draw the requested frame, whichever comes
 * first.  Once it is too late, it will, of course, return -1, thus 
 * ensuring that the caller doesn't waste time drawing to an internal
 * graphics buffer that wouldnt' be displayed.
 * <p>
 * For the behavior when this component is destroyed, see 
 * <code>removeNotify()</code>.
 * <p>
 * Sample usage:
 * <pre>
 *
 *     BufferedAnimation anim = new BufferedAnimation();
 *      ... put anim in a component hierarchy
 *     Dimension d = new Dimension(...);
 *     int numBuffers = 4;
 *     for (;;) {
 *         try {
 *             anim.setBuffers(d, 4, false, true);
 *             break;
 *         } catch (OutOfMemoryError err) {
 *             ... try smaller buffers, or fewer of them
 *         }
 *      }
 *      ... set framerate, if needed
 *      ... Make anim visible
 *     Graphics2D[] bufs = anim.getBuffersGraphics();
 *     anim.startAnimation();
 *     //  Animate 1000 frames...
 *     try {
 *         for (int f = 0; f < 1000; f++) {
 *             int n;
 *             try {
 *                  n = anim.startFrame(f);	// blocks until a buffer is free
 *             } catch (InterruptedException ex) {
 *                  // someone else called stopAnimation. or removeNotify() was
 *                  // called.  In any case, we're being asked to stop the 
 *                  // animation immediately.
 *                  break;
 *             }
 *             if (n > -1) {
 *                  try {
 *                      myDrawFrame(f, bufs[n]);
 *                  } finally {
 *                      anim.finishFrame(f);
 *                  }
 *              }
 *          }
 *      } finally {
 *          anim.stopAnimation(false);
 *      }
 *
 * </pre>
 * <p>
 * This class does not specify a return value for
 * <code>Component.isDoubleBuffered()</code>.  That method 
 * reports on a different
 * kind of buffering, related to the <code>repaint()</code>
 * call.  <code>BufferedAnimation</code>
 * objects might or might not be double-buffered, in the repaint-related
 * sense meant by <code>Component.isDoubleBuffered()</code>.
 * <p>
 * NOTE:  A future version of this API could potentially allow simultaneous 
 * drawing to two frames, by relaxing the synchronization condition on 
 * startFrame(int).  However, it is unclear if this would yield benefits
 * e.g. on a multi-core system, given memory bandwith limitations and the
 * already existing ability to have parallel threads drawing into one
 * frame or doing other computations.
 * <p>
 * <font color="red">OPEN ISSUES:</font>
 * <ul>
 *   <li>Is IncompatibleTimebaseException needed?  Are there timebases
 *	 that can't easily be related to the video output pipe?
 *   <li>What's the right failure mode if a Clock isn't running?
 *   <li>In MHP, what is the mapping from the underlying MPEG timebase to
 *       the media time from a JMF Clock?  (Note from Bill:  DSMCCStream
 *       has NPT, which is also a long and is also nanoseconds.  Are these
 *       related?  Also, I note that the trick play API uses Clock.setRate,
 *       so certainly there is a linkage from the JMF media time to what
 *       is being presented...)
 * </ul>
 * @since MHP 1.1.3
 **/

public class BufferedAnimation extends java.awt.Component {

    /**
     * Constant representing a common video framerate, approximately
     * 23.98 frames per second, and equal to
     * <code>24000f/1001f</code>.
     *
     * @see #getFramerate()
     * @see #setFramerate(float)
     **/
    static public float FRAME_23_98 = 24000f/1001f;

    /**
     * Constant representing a common video framerate, equal to
     * <code>24f</code>.
     *
     * @see #getFramerate()
     * @see #setFramerate(float)
     **/
    static public float FRAME_24 = 24f;

    /**
     * Constant representing a common video framerate, equal to
     * <code>25f</code>.
     *
     * @see #getFramerate()
     * @see #setFramerate(float)
     **/
    static public float FRAME_25 = 25f;

    /**
     * Constant representing a common video framerate, approximately
     * 29.97 frames per second, and equal to<code>30000f/1001f</code>.
     *
     * @see #getFramerate()
     * @see #setFramerate(float)
     **/
    static public float FRAME_29_97 = 30000f/1001f;

    /**
     * Constant representing a common video framerate, equal to
     * <code>50f</code>.
     *
     * @see #getFramerate()
     * @see #setFramerate(float)
     **/
    static public float FRAME_50 = 50f;

    /**
     * Constant representing a common video framerate, approximately
     * 59.94 frames per second, and equal to<code>60000f/1001f</code>.
     *
     * @see #getFramerate()
     * @see #setFramerate(float)
     **/
    static public float FRAME_59_94 = 59.94f;

    /**
     * Create a new <code>BufferedAnimation</code> component.  The
     * BufferedAnimation functionality may be optional.  Applications
     * written to device specifications that do not make this functionality
     * mandatory should be prepared to catch UnsupportedOperationException
     * when invoking this constructor.
     *
     * @throws java.lang.UnsupportedOperationException  
     *		If this feature is not supported on the device.
     **/
    public BufferedAnimation() {
    }

    /**
     * Set the size and number of the internal image buffers.  If the system 
     * is capable of scaling a <code>BufferedAnimation</code>'s buffers to the
     * component's size in real-time, then the internal buffers will
     * be of the requested size, and scaling will occur.  If the system
     * is not, then the results will depend on the value of
     * <code>forceSize</code>.
     * <p>
     * On a system that cannot perform a requested scaling, if 
     * <code>forceSize</code> is true,
     * then the buffers' sizes will be set to the requested size
     * regardless.  The displayed result will be clipped or will
     * have areas that are unpainted, as needed.  In all cases, the
     * upper-left hand corner of the buffers will be painted at
     * to the upper-left hand corner of the component.
     * <p>
     * On a system that cannot perform scaling, if 
     * <code>forceSize</code> is false,
     * the buffers' size will be set to the current size of the component.  
     * That is, the requested buffer dimension will be ignored.
     * <p>
     * The system model of specifications that include this class
     * may specify a set of supported scalings.
     * <p>
     * Note that the framebuffer itself might be scaled for display
     * on the output device.  For example, a specification including
     * this class might include half-resolution mode, e.g. for
     * half-resolution computer graphics over 1080i video.
     * <p>
     * <b><font size="+1">Graphics Acceleration</font></b>
     * <p>
     * Some systems have special faster video memory that gives accelerated
     * graphics performance.  The system model of a specification adopting
     * this API may define a minimum amount of such memory.  Other hardware
     * architectures, such as "unified memory architecture" platforms,
     * don't have special accelerated memory.  On these platforms,
     * all video memory is considered "accelerated", that is, the
     * <code>forceAccelerated</code> parameter has no effect, and does
     * not cause automatic failure.
     * <p>
     * On platforms with special accelerated video memory, the caller may
     * indicate
     * that all of the graphics buffers must be allocated from this
     * accelerated memory.  It does this by setting the 
     * <code>forceAcceleration</code>
     * parameter true.  This may make an OutOfMemoryError more likely.
     * If forceAcceleration is not true, then the
     * implementation will make a "best-effort" attempt to put the buffers
     * in accelerated memory, but will fall back to normal heap memory,
     * if required.
     * <p>
     * This method may be called more than once.  If it exits with an
     * exception, the state of this object will not be changed.  If it
     * returns normally, the new values will override anything set previously.
     *
     * @param  bufSize	The requested buffer size
     * @param  numBuffers  The number of image buffers to allocate
     * @param forceSize Force sizing the buffers to the requested size,
     *			even if this means clipping or having unpainted
     *			areas.
     * @param forceAcceleration
     *			Force allocation of all buffers in accelerated
     *			memory.
     *
     * @throws IllegalArgumentException if d.width or d.height is less 
     *					than one, or numBuffers is less
     *					than one.
     * @throws IllegalStateException	if getBuffersGraphics has been called
     *					for this component.
     * @throws IllegalStateException	If this component isn't displayable.
     * @throws OutOfMemoryException	If there isn't enough memory to allocate
     *					the needed buffers.
     *
     * @see java.awt.Component#isDisplayable()
     **/
    public void setBuffers(Dimension bufSize, int numBuffers, boolean forceSize,
			   boolean forceAcceleration) {
    }

    /**
     * Get the graphics objects for drawing into this component's internal
     * image buffers.  The size and number of buffers is determined by
     * the <code>setBuffers</code> method.
     * <p>
     * After the first call, subsequent invocations of this method shall
     * return the identical value (i.e. multiple calls will return values
     * that are <code>==</code> to each other).
     * <p>
     * Other than the <code>setBuffers</code> mechanism, 
     * the size of the internal
     * buffers will never change.  If the component is resized, the
     * system might scale the resulting animation, but this behavior
     * is not guaranteed by the specification of this class.  In all 
     * cases, the upper-left hand corner
     * of the image buffer will be displayed in the upper-left hand
     * corner of the component.
     * <p>
     * The initial contents of the graphics buffers is undefined.  Callers
     * may wish to initialize the buffers to a known state, such as fully
     * transparent, before starting an animation.  Once an animation is
     * started, drawing into a buffer outside of a
     * <code>startFrame/finishFrame</code> pair may produce unpredictable
     * results on the screen.
     *
     * @return An array of graphics objects that can be used to draw into
     *	       the internal image buffers.
     *
     * @throws IllegalStateException	if the <code>setBuffers()</code> hasn't
     *					been successfully called.
     *
     * @see #setBuffers(java.awt.Dimension, int, boolean, boolean)
     **/
    public Graphics2D[] getBuffersGraphics() {
	return null;
    }

    /**
     * Get the size of the internal image buffers.  If setBuffers has
     * not yet been successfully called, then null is returned.
     *
     * @see #getBuffersGraphics()
     * @see #setBuffers(java.awt.Dimension, int, boolean, boolean)
     **/
    public Dimension getBuffersSize() {
	return null;
    }

    /**
     * If this component has an active animation, then this method 
     * paints either the last valid image buffer or the next valid
     * image buffer to the given graphics object.  If there is no
     * available valid image buffer and a <code>startFrame/finishFrame</code>
     * sequence is in progress, this method will block until 
     * <code>finishFrame</code>
     * is called, thus generating a valid image buffer.  If no animation
     * is in progress or no valid frames have yet been generated, then 
     * this method does nothing.
     * <p>
     * Note that in normal operation, this method should be called by
     * the platform very infrequently, if at all.  It might be called, for
     * example, due to an "expose event," or due to a call to
     * <code>Component.print(Graphics)</code>.  Application authors should not
     * request a call to <code>paint</code> via the repaint mechanism to animate
     * this component, because this class uses a different model for 
     * animation.
     **/
    public void paint(Graphics g) {
    }

    /**
     * Start drawing the given frame.  The return value gives the index
     * into the array obtained from <code>getBuffersGraphics()</code>
     * for drawing of this frame, or -1 if the animation has fallen 
     * behind, and a later frame should now be drawn.  After calling 
     * this method, if a value other than -1 is returned, the
     * caller may draw to the indicated graphics buffer.  When it is
     * finished, it shall call <code>finishFrame()</code>.
     * <p>
     * If a buffer is not available for the given frame, this method
     * will block until one is ready.
     * <p>
     * The caller can always skip frames.  For example, a caller wishing
     * to animate at half of the component's framerate could request
     * frames 0, 2, 4, 6, 8, 10, etc.  In this example, if there are four
     * buffers and animation does not fall behind, the caller would be 
     * instructed to draw into buffer 0, 1, 2, 3, 0, 1, etc.  A caller that
     * wishes to start animating at a frame greater than 0 may do so by
     * simply starting with a number greater than zero; when the lower-numbered
     * frames are being presented, the component will simply do no drawing.
     * <p>
     * The content of the framebuffers is not modified by the system.
     * Thus, a caller that is drawing into buffer number <i>n</i>
     * could function correctly if it only drew to pixels that have
     * changed since it last drew into buffer number <i>n</i>.
     *
     * @param	frameNumber	The frame number to draw.  The first frame
     *				is frame 0.
     *
     * @return  An index into the array of graphics objects for drawing
     *		the given frame, or -1 if animation has fallen behind, and
     *		a later frame should now be drawn.
     *
     * @throws	IllegalArgumentException if frameNumber is less than or
     *					 equal to a number previously
     *					 supplied to this animation, or is
     *					 less than zero.
     *
     * @throws	IllegalStateException	if <code>startFrame()</code>
     *					has already been 
     *					successfully
     *					called without a corresponding
     *					<code>finishFrame()</code>.
     *
     * @throws 	InterruptedException	If this animation is in the stopped
     *					state, either when this method is called
     *					or due to a state transition while
     *					it is blocked waiting for a
     *					graphics buffer.
     *
     * @see #getBuffersGraphics()
     * @see #isStarted()
     **/
    public int startFrame(int frameNumber) throws InterruptedException {
	return -1;
    }

    /**
     * Notify the system that the frame currently being drawn is finished.
     * Drawing the current frame is initiated with 
     * <code>startFrame(int)</code>.
     * Once <code>finishFrame(int)</code> is called, 
     * the system can copy that frame to
     * the framebuffer, and the caller can move on to preparing the next
     * frame.
     *
     * @param frameNumber	The frame number that is finished.  This must
     *				match the value passed into startFrame(int).
     *
     * @throws IllegalStateException	if a startFrame call has not returned
     *					successfully for the given frame number
     *					(with a return value other
     *					than -1), if 
     *					<code>finishFrame(int)</code> has
     *					already been called for this frame
     *					number since the startFrame call, or if 
     *					<code>stopAnimation(boolean)</code>
     *					has been called since the corresponding
     *					startFrame call.
     * @see #startFrame(int)
     **/
    public void finishFrame(int frameNumber) {
    }

    /**
     * Attempt to set the framerate of the screen associated with this
     * component.  Other factors, such as video being output to the
     * same device or device limitations, might determine the framerate, 
     * thus causing this method to have no effect.  The frameright might 
     * subsequently be changed, e.g. by video being presented on the screen 
     * or by other APIs.  Unless other behavior is mandated by the system 
     * model of a specification incorporating this class, it is an allowable
     * implementation option for this method to never change the framerate.
     * <p>
     * The system model of specifications including this class might
     * determine under what conditions the framerate can be set, and
     * what framerates are guaranteed to be supported.  This class
     * defines a number of common framerates as constants whose name
     * begin with "<code>FRAME_</code>".
     * <p>
     * Note that an application that wishes to animate at a lower framerate
     * than that of the hardware may do so, by simply skipping frames.  This
     * is discussed in the <code>startFrame(int)</code> method.
     *
     * @throws IllegalStateException	If this component is not displayable.
     *
     * @see java.awt.Component#isDisplayable()
     * @see #startFrame(int)
     **/
    public void setFramerate(float rate) {
    }

    /**
     * Get the actual framerate of the screen associated with this
     * component.  This class
     * defines a number of common framerates as constants whose name
     * begin with "<code>FRAME_</code>".
     **/
    public float getFramerate() {
	return 0.0f;
    }

    /**
     * Start this animation immediately, and reset the frame number to zero.
     * Frame zero will be the first frame that can be displayed; typically it
     * will be one or two frames after the frame visible on the screen at the
     * time this method is called.
     * <p>
     * Applications should only call this method on a stopped animation.
     * However, if this method is called when an animation is already started,
     * it is re-started; the effect is equivalent to calling 
     * <code>stopAnimation(true)</code> followed by 
     * <code>startAnimation()</code>.
     * 
     * @see #isStarted()
     **/
    public void startAnimation() {
    }

    /**
     * Start this animation keyed to the clock at the given media time.
     * If the clock's media time is already greater than the given time,
     * this is equivalent to <code>startAnimation()</code>.  Otherwise,
     * once the clock's media time is greater than or equal to the given
     * the given value, the animation will be started.  Callers should
     * not assume that frame zero of the animation will coincide with the
     * desired time in all cases; for example, the clock's media time might
     * advance in a discontinuous manner.  Callers should always consult
     * <code>getMediaTime(...)</code>.
     * <p>
     * This method can be used to initiate frame-accurate animation that is
     * synchronized to video that is being presented on the same screen.
     * The animation enters the started state, and drawing to graphics
     * buffers can begin.  The system will start copying these buffers
     * to the framebuffer automatically, when the clock reaches the given
     * time.
     * <p>
     * Subsequent calls to this method override any previoius calls.
     * If this method is called when an animation is already started,
     * it is re-started; the effect is equivlanet to calling 
     * <code>stopAnimation(true)</code> followed by 
     * <code>startAnimationAt(...)</code>.
     *
     *
     * @param c		A JMF Clock that is associated with some media.
     * @param t		A media time of that clock when the
     *			animation should start.
     *
     * @throws IncompatibleTimeBaseException
     *			If this Clock is incompatible with this animation.
     *			This will never be thrown if the Clock is associated
     *			with video being displayed on the same screen as
     *			this animation.
     *
     * @see #isStarted()
     * @see #getMediaTime(javax.media.Clock, int)
     **/
    public void startAnimationAt(Clock c, Time t) 
	    throws IncompatibleTimeBaseException
    {
    }

    /**
     * Stops this animation.  If it is already in the stopped state, this
     * method has no effect.  If it is in the started state, it is set to
     * the stopped state.  Once this component is in the stopped state,
     * it will not draw into any pixels to the screen, or as a result of
     * a call to the <code>paint()</code> method.
     * <p>
     * The caller may request that queued fames of animation
     * be output to the
     * screen.  This will be done if the animation is in the started state,
     * and immediate is set false.
     * <p>
     * If a successful call to <code>startFrame(int)</code> has not yet 
     * been matched with a call to <code>finishFrame(int)</code>, this 
     * object is set to the stopped state, which will cause 
     * <code>finishFrame(int)</code> ti fail.  See that method for details.
     * <p>
     * After this method returns, <code>isStarted()</code> will return false.
     * If this animation is not in the started state, calling this method
     * will have no effect.
     * 
     * @param immediate  If the component should immediately stop copying
     * 			 buffers to the screen,
     *			 instead of letting any queued animation frames
     *			 be output.
     *
     * @see #isStarted()
     * @see #finishFrame(int)
     **/
    public void stopAnimation(boolean immediate) {
    }

    /**
     * Return true if this animation is started.  A 
     * <code>BufferedAnimation</code>
     * can either be in the started or stopped state.  A stopped
     * <code>BufferedAnimation</code> will only draw to the framebuffer
     * if it is in the process of flushing animation buffers due to a
     * call to <code>stopAnimation(false)</code>
     *
     * @return true if this <code>BufferedAnimation</code> is 
     * 		started, false otherwise.
     *
     * @see #stopAnimation(boolean)
     **/
    public boolean isStarted() {
	return false;
    }

    /**
     * Get the predicted media time of the given frameNumber for this 
     * animation.  The predicted media time is calculated from the
     * media time of the frame being presented on the screen, and
     * extrapolating assuming a clock rate of 1.0.
     * <p>
     * The return value can be converted into a javax.media.Time
     * value by calling the <code>javax.media.Time(long)</code> constructor.
     * <p>
     * This method is useful for keeping an animation aligned with a
     * video source, even if "trick play" operations cause the media
     * position to change.  Note that because the computer generated animation
     * might be a small number of frames "ahead" of the video due to the
     * buffering this class provides, there might be a perceptable "lag"
     * during trick play itself, but once the video returns to normal
     * play mode, the animation would once again be frame-synchronized.
     *
     * @param c		The clock to calculate media time relative to
     * @param frameNumber  the desired frame number of this animation
     *
     * @return	The media time, in nanoseconds.
     *
     * @throws IllegalStateException	if this animation is not in the
     *					started state.
     *
     * @throws IncompatibleTimeBaseException
     *			If this Clock is incompatible with this animation.
     *			This will never be thrown if the Clock is associated
     *			with video being displayed on the same screen as
     *			this <code>BufferedAnimation</code> component.
     **/
    public long getMediaTime(Clock c, int frameNumber) {
	return 0;
    }

    /** 
     * Make this component undisplayable by destroying any native
     * resources, and freeing its image buffers.  
     * <code>stopAnimation(true)</code>
     * shall be called by the implementation of this method.
     **/
    public void removeNotify() {
    }

    /** 
     * Makes this <code>Component</code> displayable by connecting 
     * it to a native screen resource. This method is called 
     * internally by the toolkit and should not be called directly 
     * by programs.
     **/
    public void addNotify() {
    }

}


