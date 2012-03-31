package org.davic.media;

/**
 * This interface should be implemented to enable the application to freeze the player. 
 * The method getControl and getControls return an object implementing this interface if it is supported.
 */

public interface FreezeControl extends javax.media.Control 
{
	/**
	 * Invocation of this method freezes the decoding of the media stream as soon as possible and 
	 * leaves the last decoded frame visible to the end-user. The decoding of the audio is stopped. 
	 * The actual timebase of the underlying media is however not altered. 
	 * @exception MediaFreezeException if the freeze is unsuccessful
	 */

	public void freeze()throws MediaFreezeException;

	/**
	 * Invocation of this method resumes the decoding of the media stream following a freeze operation.
	 * This should be a very low latency operation. 
         * If the player is started and if decoding of the media stream is not frozen then calls 
         * to this method shall have no effect. If the player is not started then the exception shall be thrown.
	 * @exception MediaFreezeException if the resume is unsuccessful
	 */
	public void resume()throws MediaFreezeException;
}


