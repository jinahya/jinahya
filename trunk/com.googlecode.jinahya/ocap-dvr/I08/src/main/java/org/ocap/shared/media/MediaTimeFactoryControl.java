package org.ocap.shared.media;
import javax.media.Time;
/**
 * Provides the ability to obtain media times with various special
 * characteristics when applied to the content being played by this JMF
 * player. The behaviour of these media times is implementation dependent
 * if used with any other JMF player. <p>
 * Methods returning instances of Time shall not check whether that Time
 * references a point outside the content being played by this JMF player.
 * Checking for this condition shall happen when the instance of Time is used - in
 * the same way as for instances of Time created using the constructor of that
 * class.
 */

public interface MediaTimeFactoryControl extends javax.media.Control {
	/**
	 * Obtain a media time relative to the current location
	 *
	 * @param offset the offset relative to the current location measured in 
	 * nanoseconds
	 * @return a media time
	 */
	public Time getRelativeTime(long offset);
	/**
	 * Enables applications to precisely control the position where playback
	 * starts following a call to Player.setMediaTime. This method takes an 
	 * original media time as input and returns a new media time which
	 * encapsulates the original media time and an indication of how that 
	 * original media time is to be interpreted when used in a call to
	 * Player.setMediaTime.<p>
	 * 
	 * The present document does not define the return values from calling getSeconds
	 * and getNanoseconds on the new media time instance. They may be copies of the
	 * return values from the original time. They may be new values accurately
	 * reflecting where playback would start if the new media time was passed to
	 * Player.setMediaTime.
	 * @param original the original media time
	 * @param beforeOrAfter if true, the media time where playback starts will
	 * be on or before the original one (i.e. content at the original media
	 * time is guaranteed to be presented in playback). If false, the media
	 * time where playback starts will be after the original one (i.e. neither
	 * content at the original media time nor any content before that original
	 * time will be presented in playback).
	 * 
	 * @return a new media time
	 */ 
	public Time setTimeApproximations(Time original, boolean beforeOrAfter);
}

