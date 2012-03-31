package org.davic.media;

/**
 * Subtitling language control 
 */

public interface SubtitlingLanguageControl extends LanguageControl 
{
	/**
	 * @return true if the subtitling is currently being presented, otherwise returns false 
	 */
	public boolean isSubtitlingOn();

	/**
	 * Changes the subtitling on or off depending on the value of the parameter 
	 * @param new_value true => subtitles on, false => subtitles off. 
	 * @return the previous state. 
	 */
	public boolean setSubtitling(boolean new_value);
}


