package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
   The {@link org.havi.ui.HFlatEffectMatte HFlatEffectMatte} class
   represents a matte that is constant over space but varies over
   time. It is specified as a sequence of floating point values 
   in the range 0.0 to 1.0 where:

   <ul>
   <li>0.0 is fully transparent
   <li>values between 0.0 and 1.0 are partially transparent to the
   nearest supported transparency value.
   <li>1.0 is fully opaque
   </ul>

   <p>
   The data for any HFlatEffectMatte may be changed &quot;on the fly&quot;
   using the {@link org.havi.ui.HFlatEffectMatte#setMatteData setMatteData} method. 
   
   However, some implementations may be asynchronously referencing
   their content (i.e. through a separate implementation-specific
   animation thread).  Therefore the following restrictions apply to
   the {@link org.havi.ui.HFlatEffectMatte#setMatteData setMatteData} method:
   
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
    <td>The transparency value for this flat effect matte.</td>
    <td>null (the matte should be treated as being temporally
    unvarying and opaque)</td>    
    <td>{@link org.havi.ui.HFlatEffectMatte#setMatteData setMatteData}</td>
    <td>{@link org.havi.ui.HFlatEffectMatte#getMatteData getMatteData}</td>
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
     <td>{@link org.havi.ui.HFlatEffectMatte#setPosition setPosition}</td>
     <td>{@link org.havi.ui.HFlatEffectMatte#getPosition getPosition}</td>
 </tr>

 <tr>
     <td>By default the animation should be stopped. Hence, to start the
     animation its start method must be explicitly invoked. This
     mechanism allows for animations that are programmatically
     controlled, e.g. via the setPosition method. </td>     
     <td>&quot;stopped&quot;</td>
     <td>{@link org.havi.ui.HFlatEffectMatte#start start} / 
         {@link org.havi.ui.HFlatEffectMatte#stop stop}</td>
     <td>{@link org.havi.ui.HFlatEffectMatte#isAnimated isAnimated}</td>
 </tr>
  </table>

*/

public class HFlatEffectMatte
    implements HMatte, HAnimateEffect
{
    /**
     * Creates an {@link org.havi.ui.HFlatEffectMatte
     * HFlatEffectMatte} object. See the class description for details
     * of constructor parameters and default values.  
     */
    public HFlatEffectMatte()
    {
    }

    /**
     * Creates an {@link org.havi.ui.HFlatEffectMatte
     * HFlatEffectMatte} object. See the class description for details
     * of constructor parameters and default values.
     */
    public HFlatEffectMatte(float[] data)
    {
    }

    /**
     * Sets the data for this matte. Any previously set data is
     * replaced. If this method is called when the animation is
     * running the data is changed immediately and the current
     * animation position is reset according to the active play
     * mode. The changes affect the animation immediately.
     *
     * @param data the data for this matte. Specify a null object to
     * remove the associated data for this matte. If the length of the
     * data array is zero, an IllegalArgumentException is thrown.  
     */
    public void setMatteData(float[] data)
    {
    }

    /**
     * Returns the matte data used for this matte.
     *
     * @return the data used for this matte (an array of numbers), or
     * null if no matte data has been set.
     */
    public float[] getMatteData()
    {
        return (null);
    }

    /**
     * This method starts this {@link org.havi.ui.HFlatEffectMatte
     * HFlatEffectMatte} playing.  If <code>start</code> is called when
     * the animation is already running it resets the animation
     * according to the current play mode, as returned by {@link
     * org.havi.ui.HFlatEffectMatte#getPlayMode getPlayMode}.
     */
    public void start()
    {
        return;
    }

    /**
     * This method indicates that the running {@link
     * org.havi.ui.HFlatEffectMatte HFlatEffectMatte} should be
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
     * {@link org.havi.ui.HFlatEffectMatte HFlatEffectMatte}.
     * 
     * @return <code>true</code> if this {@link org.havi.ui.HFlatEffectMatte
     * HFlatEffectMatte} is running, i.e. the <code>start</code> method
     * has been invoked - <code>false</code> otherwise.  
     */
    public boolean isAnimated()
    {
        return(false);
    }

    /**
     * Set this {@link org.havi.ui.HFlatEffectMatte HFlatEffectMatte} to
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
     * org.havi.ui.HFlatEffectMatte HFlatEffectMatte} is using to display
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
     * org.havi.ui.HFlatEffectMatte HFlatEffectMatte} should be played.
     * If the animation is already running a call to
     * <code>setRepeatCount</code> will change the current value and
     * reset the current number of repeats to 0, affecting the
     * animation immediately.
     * 
     * @param count the number of times that an {@link
     * org.havi.ui.HFlatEffectMatte HFlatEffectMatte} should be
     * played. Valid values of the repeat count are one or more, and
     * {@link org.havi.ui.HAnimateEffect#REPEAT_INFINITE
     * REPEAT_INFINITE}. 
     */
    public void setRepeatCount(int count)
    {
        return;
    }

    /**
     * Gets the number of times that this {@link org.havi.ui.HFlatEffectMatte
     * HFlatEffectMatte} is to be played. Note that this method does <em>not</em>
     * return the number of repeats that are remaining to be played.
     * <p>
     * Except for <code>HAnimateEffect</code> implementations that specify a
     * different default, <code>getRepeatCount()</code> returns
     * <code>REPEAT_INFINITE</code> if no call to
     * <code>setRepeatCount()</code> has previously been made.
     * 
     * @return the total number of times that an {@link
     * org.havi.ui.HFlatEffectMatte HFlatEffectMatte} is to be played. The
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
     * After calling {@link org.havi.ui.HFlatEffectMatte#setDelay
     * setDelay} on a currently playing {@link
     * org.havi.ui.HFlatEffectMatte HFlatEffectMatte}, there is no
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
     * org.havi.ui.HFlatEffectMatte HFlatEffectMatte}.
     * 
     * @return the presentation delay in units of 0.1 seconds.
     */
    public int getDelay()
    {
        return(0);
    }

    /**
     * Sets the playing mode for this {@link org.havi.ui.HFlatEffectMatte
     * HFlatEffectMatte}. If the animation is already running a call to
     * <code>setPlayMode</code> will change the current value and
     * affect the animation immediately. The position of the animation
     * is unchanged.
     * 
     * @param mode the play mode for this {@link
     * org.havi.ui.HFlatEffectMatte HFlatEffectMatte}, which must be either {@link
     * org.havi.ui.HAnimateEffect#PLAY_ALTERNATING} or {@link
     * org.havi.ui.HAnimateEffect#PLAY_REPEATING}.
     */
    public void setPlayMode(int mode)
    {
        return;
    }

    /**
     * Gets the playing mode for this {@link org.havi.ui.HFlatEffectMatte
     * HFlatEffectMatte}.
     * 
     * @return the play mode for this {@link
     * org.havi.ui.HFlatEffectMatte HFlatEffectMatte}.  
     */
    public int getPlayMode()
    {
        return(0);
    }
}

