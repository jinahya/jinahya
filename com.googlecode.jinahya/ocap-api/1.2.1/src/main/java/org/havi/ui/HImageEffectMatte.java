package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Image;
import java.awt.Point;


/**
   The {@link org.havi.ui.HImageEffectMatte HImageEffectMatte} class
   represents a matte that varies over both space and time, it is
   specified as a sequence of image masks.
   
   <p>
   The data for any HImageEffectMatte may be changed &quot;on the fly&quot;
   using the {@link org.havi.ui.HImageEffectMatte#setMatteData setMatteData} method. 
   
   However, some implementations may be asynchronously referencing
   their content (i.e. through a separate implementation-specific
   animation thread).  Therefore the following restrictions apply to
   the {@link org.havi.ui.HImageEffectMatte#setMatteData setMatteData} method:
   
   <p><ul>                 
   <li>The method must be synchronized with any implementation-specific
   animation thread such that content cannot be changed while a
   different thread is using it. 
   <li>If the animation was running the method should stop the
   animation in a synchronized manner before changing content.  
   <li>The method should reset the animation to a starting position
   defined by the current play mode. The repeat count of the animation
   should be reset to 0.  
   <li>If the animation was running the method should start the
   animation.  
   </ul>
   <p>

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
 <tr>
    <td>data</td>
    <td>The transparency data for this image effect matte.</td>
    <td>null (the matte should be treated as being spatially and
    temporally unvarying and opaque)</td>    
    <td>{@link org.havi.ui.HImageEffectMatte#setMatteData setMatteData}</td>
    <td>{@link org.havi.ui.HImageEffectMatte#getMatteData getMatteData}</td>
 </tr>
  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
 <tr>
     <td>The initial piece of content to be presented, i.e. its
     position in the content array.</td>
     <td>0</td>
     <td>{@link org.havi.ui.HImageEffectMatte#setPosition setPosition}</td>
     <td>{@link org.havi.ui.HImageEffectMatte#getPosition getPosition}</td>
 </tr>

 <tr>
     <td>By default the animation should be stopped. Hence, to start the
     animation its start method must be explicitly invoked. This
     mechanism allows for animations that are programmatically
     controlled, e.g. via the setPosition method. </td>     
     <td>&quot;stopped&quot;</td>
     <td>{@link org.havi.ui.HImageEffectMatte#start start} / 
         {@link org.havi.ui.HImageEffectMatte#stop stop}</td>
     <td>{@link org.havi.ui.HImageEffectMatte#isAnimated isAnimated}</td>
 </tr>
 <tr>
    <td>The pixel offset for each image within the {@link
    org.havi.ui.HImageEffectMatte HImageEffectMatte}, relative to the
    top, left corner of its associated component.</td>
    <td>A java.awt.Point (0, 0)</td>
    <td>{@link org.havi.ui.HImageEffectMatte#setOffset setOffset}</td>
    <td>{@link org.havi.ui.HImageEffectMatte#getOffset getOffset}</td>
 </tr>
  </table>

*/

public class HImageEffectMatte
    implements HMatte, HAnimateEffect
{
    /**
     * Creates an {@link org.havi.ui.HImageEffectMatte
     * HImageEffectMatte} object. See the class description for
     * details of constructor parameters and default values.  
     */
    public HImageEffectMatte()
    {
    }

    /**
     * Creates an {@link org.havi.ui.HImageEffectMatte
     * HImageEffectMatte} object. See the class description for
     * details of constructor parameters and default values.
     */
    public HImageEffectMatte(Image[] data)
    {
    }

    /**
     * Sets the data for this matte (an array of images). Any
     * previously set data is replaced. If this method is called when
     * the animation is running the data is changed immediately and
     * the current animation position is reset according to the active
     * play mode. The changes affect the animation immediately.  
     * <p>
     * Note that if the size of an image is smaller than the size of
     * the component to which the matte is applied, the empty space
     * behaves as if it were an opaque flat matte of value 1.0. By
     * default images are aligned at the top left corner of the
     * component. This can be changed with the {@link
     * org.havi.ui.HImageEffectMatte#setOffset setOffset} method
     * 
     * @param data the data for this matte. Specify a null object to
     * remove the associated data for this matte. If the length of the
     * data array is zero, an IllegalArgumentException is thrown.  If
     * an element of the data array is null, or an image referred to
     * is still being loaded, then that image will be skipped when the
     * animation is playing.  */
    public void setMatteData(Image[] data)
    {
    }

    /**
     * Returns the data used for this matte.
     *
     * @return the data used for this matte (an array of images) or
     * null if no matte data has been set.
     */
    public Image[] getMatteData()
    {
        return (null);
    }

    /**
     * Set the offset of a specified frame of the matte relative to
     * its component in pixels.
     *
     * @param p the offset of the specified frame of the matte
     * relative to the top left corner of its component in pixels. If
     * p is null a NullPointerException is thrown.
     * @param index the zero-index to the data for which the offset
     * should be applied. If index is not valid for this matte an
     * IndexOutOfBounds exception is thrown.  
     */
    public void setOffset(Point p, int index)
    {
    }

    /**
     *
     * Get the offset of a specified frame of the matte relative to
     * its component in pixels.
     *
     * @param index the zero-index to the data for which the offset
     * should be recovered. If index is not valid for this matte an
     * IndexOutOfBounds exception is thrown.
     * @return the offset of the specified frame of the matte relative
     * to its component in pixels (as a Point) 
     */
    public Point getOffset(int index)
    {
        return (null);
    }

    /**
     * This method starts this {@link org.havi.ui.HImageEffectMatte
     * HImageEffectMatte} playing.  If <code>start</code> is called when
     * the animation is already running it resets the animation
     * according to the current play mode, as returned by {@link
     * org.havi.ui.HImageEffectMatte#getPlayMode getPlayMode}.
     */
    public void start()
    {
        return;
    }

    /**
     * This method indicates that the running {@link
     * org.havi.ui.HImageEffectMatte HImageEffectMatte} should be
     * stopped. After calling this method, there is no guarantee that
     * one or more frames will not be displayed before the animation
     * actually stops playing. If the animation is already stopped
     * further calls to <code>stop</code> have no effect.  
     */
    public void stop()
    {
        return;
    }

    /**
     * This method indicates the animation (running) state of the
     * {@link org.havi.ui.HImageEffectMatte HImageEffectMatte}.
     * 
     * @return <code>true</code> if this {@link org.havi.ui.HImageEffectMatte
     * HImageEffectMatte} is running, i.e. the <code>start</code> method
     * has been invoked - <code>false</code> otherwise.  
     */
    public boolean isAnimated()
    {
        return(false);
    }

    /**
     * Set this {@link org.havi.ui.HImageEffectMatte HImageEffectMatte} to
     * display the content at the specified position. If the animation
     * is already running a call to <code>setPosition</code> will
     * change the current value and affect the animation immediately.
     * 
     * @param position an index into the content array which specifies
     * the next piece of content to be displayed. If
     * <code>position</code> is less than 0, then the array element at
     * index 0 is displayed, if <code>position</code> is greater than
     * or equal to the length of the content array, then the array
     * element at index [<code>length</code>-1] will be used.  
     */
    public void setPosition(int position)
    {
        return;
    }

    /**
     * Get the current index into the content array which this {@link
     * org.havi.ui.HImageEffectMatte HImageEffectMatte} is using to display
     * content.
     * 
     * @return the index of the content currently being displayed, in
     * the range <code>0 <= index < length</code>  
     */
    public int getPosition()
    {
        return(0);
    }

    /**
     * Sets the number of times that this {@link
     * org.havi.ui.HImageEffectMatte HImageEffectMatte} should be played.
     * If the animation is already running a call to
     * <code>setRepeatCount</code> will change the current value and
     * reset the current number of repeats to 0, affecting the
     * animation immediately.
     * 
     * @param count the number of times that an {@link
     * org.havi.ui.HImageEffectMatte HImageEffectMatte} should be
     * played. Valid values of the repeat count are one or more, and
     * {@link org.havi.ui.HAnimateEffect#REPEAT_INFINITE
     * REPEAT_INFINITE}. 
     */
    public void setRepeatCount(int count)
    {
        return;
    }

    /**
     * Gets the number of times that this {@link org.havi.ui.HImageEffectMatte
     * HImageEffectMatte} is to be played. Note that this method does <em>not</em>
     * return the number of repeats that are remaining to be played.
     * <p>
     * Except for <code>HAnimateEffect</code> implementations that specify a
     * different default, <code>getRepeatCount()</code> returns
     * <code>REPEAT_INFINITE</code> if no call to
     * <code>setRepeatCount()</code> has previously been made.
     * 
     * @return the total number of times that an {@link
     * org.havi.ui.HImageEffectMatte HImageEffectMatte} is to be played. The
     * returned value shall be greater than zero, or {@link
     * org.havi.ui.HAnimateEffect#REPEAT_INFINITE REPEAT_INFINITE}.  
     */
    public int getRepeatCount()
    {
        return(0);
    }

    /**
     * Sets the delay between the presentation of successive pieces of
     * content (frames).
     * <p>
     * After calling {@link org.havi.ui.HImageEffectMatte#setDelay
     * setDelay} on a currently playing {@link
     * org.havi.ui.HImageEffectMatte HImageEffectMatte}, there is no
     * guarantee that one or more frames will not be displayed using
     * the previous delay until the new delay value takes effect.
     * 
     * @param count the content presentation delay in units of 0.1
     * seconds duration.  If count is less than one &quot;unit&quot;,
     * then it shall be treated as if it were a delay of one
     * &quot;unit&quot;, i.e. 0.1 seconds.  
     */
    public void setDelay(int count)
    {
        return;
    }

    /**
     * Gets the presentation delay for this {@link
     * org.havi.ui.HImageEffectMatte HImageEffectMatte}.
     * 
     * @return the presentation delay in units of 0.1 seconds.
     */
    public int getDelay()
    {
        return(0);
    }

    /**
     * Sets the playing mode for this {@link org.havi.ui.HImageEffectMatte
     * HImageEffectMatte}. If the animation is already running a call to
     * <code>setPlayMode</code> will change the current value and
     * affect the animation immediately. The position of the animation
     * is unchanged.
     * 
     * @param mode the play mode for this {@link
     * org.havi.ui.HImageEffectMatte HImageEffectMatte}, which must be either {@link
     * org.havi.ui.HAnimateEffect#PLAY_ALTERNATING} or {@link
     * org.havi.ui.HAnimateEffect#PLAY_REPEATING}.
     */
    public void setPlayMode(int mode)
    {
        return;
    }

    /**
     * Gets the playing mode for this {@link org.havi.ui.HImageEffectMatte
     * HImageEffectMatte}.
     * 
     * @return the play mode for this {@link
     * org.havi.ui.HImageEffectMatte HImageEffectMatte}.  
     */
    public int getPlayMode()
    {
        return(0);
    }

}

