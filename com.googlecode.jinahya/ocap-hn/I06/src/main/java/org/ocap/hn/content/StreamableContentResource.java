package org.ocap.hn.content;

/**
 * Abstract class representing content that can be streamed, e.g., MPEG file.
 */
public interface StreamableContentResource extends ContentResource
{

    /**
     * Gets the duration of this content.
     * 
     * @return the duration of the content.
     */
    public javax.media.Time getDuration();

    /**
     * Gets the Bitrate this content is encoded with or -1 if not known.
     * 
     * @return the bit rate of the content in bytes per second or -1 if
     *      not known.
     */
    public long getBitrate();
}
