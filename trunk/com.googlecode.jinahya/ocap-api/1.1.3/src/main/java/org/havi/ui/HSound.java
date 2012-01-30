package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   The {@link org.havi.ui.HSound HSound} class is used to represent an
   audio clip.

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
<tr> 
    <td>The starting position of any audio clip to be played.</td> 
    <td>At the beginning of the audio clip.</td> 
    <td>---</td> 
    <td>---</td> 
</tr>
  </table>

*/

public class HSound 
    extends java.lang.Object
{
    /**
     * Creates an {@link org.havi.ui.HSound HSound} object. See the
     * class description for details of constructor parameters and
     * default values.  
     */
    public HSound() 
    {
    }

    /**
     * Loads data synchronously into an {@link org.havi.ui.HSound
     * HSound} object from an audio sample in the specified file. If
     * the object already contains data, this method shall perform the
     * following sequence:
     * <p><ul>
     * <li>stop the sample if it is playing or looping.
     * <li>dispose of the old data and any associated resources, as if
     * the {@link org.havi.ui.HSound#dispose dispose} method had been
     * called.
     * <li>load the new data synchronously.
     * </ul>
     * 
     * @param location the name of a file containing audio data in a
     * recognized file format.
     * @exception java.io.IOException if the sample cannot
     * be loaded due to an IO problem.
     * @exception java.lang.SecurityException if the caller does
     * not have sufficient rights to access the specified audio sample.  
     */
    public void load(String location) 
	throws java.io.IOException, java.lang.SecurityException
    {
    }
    
    /**
     * Loads data synchronously into an {@link org.havi.ui.HSound
     * HSound} object from an audio sample indicated by a URL.  If
     * the object already contains data, this method shall perform the
     * following sequence:
     * <p><ul>
     * <li>stop the sample if it is playing or looping.
     * <li>dispose of the old data and any associated resources, as if
     * the {@link org.havi.ui.HSound#dispose dispose} method had been
     * called.
     * <li>load the new data synchronously.
     * </ul>
     * 
     * @param contents a URL referring to the data to load.
     * @exception java.io.IOException if the audio sample cannot be
     * loaded due to an IO problem.
     * @exception java.lang.SecurityException if the caller does not have
     * sufficient rights to access the specified audio sample.  
     */
    public void load(java.net.URL contents) 
	throws java.io.IOException, java.lang.SecurityException
    {
    }

    /**
     * Constructs an {@link org.havi.ui.HSound HSound} object from an
     * array of bytes encoded in the same encoding format as when
     * reading this type of audio sample data from a file.  If
     * the object already contains data, this method shall perform the
     * following sequence:
     * <p><ul>
     * <li>stop the sample if it is playing or looping.
     * <li>dispose of the old data and any associated resources, as if
     * the {@link org.havi.ui.HSound#dispose dispose} method had been
     * called.
     * <li>load the new data synchronously.
     * </ul><p>
     * If the byte array does not contain a valid audio sample then this
     * method shall throw a
     * <code>java.lang.IllegalArgumentException</code>. 
     *
     * @param data the data for the {@link org.havi.ui.HSound
     * HSound} object encoded in the specified format for audio sample
     * files of this type.  
     */
    public void set(byte data[]) 
    {
    }

    /**
     * Starts the {@link org.havi.ui.HSound HSound} class playing from
     * the beginning of its associated audio data. If the sample data
     * has not been completely loaded, this method has no effect.
     * <p>
     * When the audio data has been played in its entirety then no
     * further audible output should be made until the next play or
     * loop method is invoked.  Note that the audio data is played
     * back asynchronously. There is no mechanism for synchronization
     * with other classes presenting sounds, images, or video.  
     * <p> 
     * This method may fail &quot;silently&quot; if (local) audio
     * facilities are unavailable on the platform.  
     */
    public void play()
    {
    }

    /**
     * Stops the {@link org.havi.ui.HSound HSound} class playing its
     * associated audio data.  
     * <p> 
     * Note that, if a play or loop method is invoked, after a stop,
     * then presentation of the audio data will restart from the
     * beginning of the audio data, rather than from the position
     * where the audio data was stopped.  
     */
    public void stop() 
    {
    }

    /**
     * Starts the {@link org.havi.ui.HSound HSound} class looping from
     * the beginning of its associated audio data. If the sample data
     * has not been completely loaded, this method has no effect.  
     * <p>
     * When the audio data has been played in its entirety, then it
     * should be played again from the beginning of its associated
     * data, so as to cause a &quot;seamless&quot; continuous
     * (infinite) audio playback - until the next stop, or play method
     * is invoked.  Note that the audio data is played back
     * asynchronously, there is no mechanism for synchronization with
     * other classes presenting sounds, images, or video.  
     * <p> 
     * This method may fail &quot;silently&quot; if (local) audio
     * facilities are unavailable on the platform.  
     */
    public void loop() 
    {
    }

    /**
     * If the {@link org.havi.ui.HSound HSound} object is playing /
     * looping then it will be stopped.  The dispose method then
     * discards all sample resources used by the {@link
     * org.havi.ui.HSound HSound} object.  This mechanism resets the
     * {@link org.havi.ui.HSound HSound} object to the state before a
     * load() method was invoked.  
     */
    public void dispose()
    {
    }
}

