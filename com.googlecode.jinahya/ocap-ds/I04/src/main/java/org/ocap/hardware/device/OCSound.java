package org.ocap.hardware.device;

import org.havi.ui.HSound;

/**
 * Extends the HAVi <code>HSound</code> class, adding additional configuration options.
 * Instances of this class provide control over audio gain level, muting, and 
 * output ports.
 * 
 * @see AudioOutputPort
 */
public class OCSound extends HSound
{
    /**
     * Creates an <code>OCSound</code> object. 
     * The following defaults apply upon construction:
     * <table border>
     * <tr> <th>Attribute</th> <th>Method</th> <th>Default</th> </tr>
     * <tr> <td>Level</td> 
     *      <td>{@link #getLevel()}</td>
     *      <td><code>1.0</code></td> </tr>
     * <tr> <td>Mute</td> 
     *      <td>{@link #isMuted()}</td>
     *      <td><code>false</code></td> </tr>
     * <tr> <td>Outputs</td> 
     *      <td>{@link #getAudioOutputs}</td>
     *      <td>the default audio output for the application constructing the 
     *          <code>OCSound</code> instance</td>
     * </table>
     */
    public OCSound()
    {
        // to be implemented
    }

    /**
     * Set the gain using a floating point scale with values between 0.0 and 1.0.
     * 0.0 is silence; 1.0 is the loudest level for associated audio output ports.
     *
     * @param level The new gain value specified in the level scale.
     * @return The level that was actually set.
     * 
     * @see #getLevel
     * @see AudioOutputPort#setLevel
     * @see AudioOutputPort#getLevel
     */
    public float setLevel(float level)
    {
        return 0F;
    }

    /**
     * Get the current gain set for this <code>OCSound</code> 
     * as a value between 0.0 and 1.0. 
     *
     * @return The gain in the level scale (0.0-1.0).
     * @see #setLevel
     */
    public float getLevel()
    {
        return 0F;
    }

    /**
     * Get the mute state of the audio signal associated with this audio clip.
     *
     * @return The current mute state: 
     *         <code>true</code> if muted and <code>false</code> otherwise. 
     * @see #setMuted
     */
    public boolean isMuted()
    {
        return true;
    }

    /**
     * Mute or unmute the signal associated with this <code>OCSound</code>.
     * Redundant invocations of this method are ignored.
     * The mute state does not effect the gain (as represented by {@link #getLevel()}.
     *
     * @param mute The new mute state: 
     *             <code>true</code> mutes the signal and <code>false</code> unmutes the signal.
     * 
     * @see #isMuted()
     */
    public void setMuted(boolean mute)
    {
        // to be implemented
    }

    /**
     * Get the audio output ports on which this audio clip would be played.
     * By default, audio-clips will be played on the default audio output port
     * for the application that created this <code>OCSound</code>.
     * Unless <code>AudioOutputPort</code>s have been removed by calling
     * <code>removeAudioOutput</code>, this method SHALL return the
     * at least the default <code>AudioOutputPort</code> for the application.
     * Unless <code>AudioOutputPort</code>s have been added by calling
     * <code>addAudioOutput</code>, this method SHALL return at most
     * the default <code>AudioOutputPort</code> for the application.
     *
     * @return The set of target <code>AudioOutputPort</code>s as an array.
     *         If all ports have been removed, then an empty array SHALL be returned.
     *         
     * @see #addAudioOutput 
     * @see #removeAudioOutput
     */
    public AudioOutputPort[] getAudioOutputs()
    {
        return new AudioOutputPort[0];
    }

    /**
     * Add an <code>AudioOutputPort</code> to the set of audio output ports where
     * this clip will be played.
     * <p>
     * Redundant additions SHALL have no effect.
     *
     * @param au The <code>AudioOutputPort</code> to add.
     */
    public void addAudioOutput(AudioOutputPort au)
    {
        // to be implemented
    }

    /**
     * Remove an <code>AudioOutputPort</code> from the set of audio ouput ports where
     * this clip will be played.
     * <p>
     * Attempting to remove an <code>AudioOutputPort</code> that is not currently
     * in the set of audio output ports for this <code>OCSound</code> SHALL have
     * no effect.
     *
     * @param au The <code>AudioOutputPort</code> to remove.
     */
    public void removeAudioOutput(AudioOutputPort au)
    {
        // to be implemented
    }
}
