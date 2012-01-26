package org.ocap.hn.content;

/**
 * Interface implemented by subclasses of ContentResource to identify that a content contains audio.
 */
public interface AudioResource extends ContentResource {

	/**
	 * Returns the sample frequency in Hz of this audio content or -1 if not known.
	 * @return the sample frequency of the content of -1 if not known.
	 */
	public int getSampleFrequency();
	
	/**
	 * Returns the number of audio channels, for example, 1 for mono, 2 for stereo, 6 for DTS 5.1 and 7 for DTS 6.1
	 * @return the sample frequency of the content of -1 if not known.
	 */
	public int getNumberOfChannels();
	
	/**
	 * Returns the number of bits per sample or -1 if not known.
	 * @return the number of bits per sample or -1 if not known.
	 */
	public int getBitsPerSample();

	/**
         * Returns an array of languages associated with this audio content or a zero
	 * length array if not known.
	 * @return the languages associated with this audio
	 */
	public String[] getLanguages();

}
