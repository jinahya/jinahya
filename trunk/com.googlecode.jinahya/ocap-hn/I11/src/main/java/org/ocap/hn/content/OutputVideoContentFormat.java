package org.ocap.hn.content;

/**
 * This interface provides additional parameters for transforming video content.  
 */
public interface OutputVideoContentFormat extends ContentFormat
{
    /**
     * Returns the content maximum vertical resolution in pixels.
     * 
     * @return The vertical content resolution.  
     */
    public int getVerticalResolution();
    
    /**
     * Returns the content maximum horizontal resolution in pixels.
     * 
     * @return The content horizontal resolution.  
     */
    public int getHorizontalResolution();

    /**
     * Returns the bit rate in bits per second (bps) of the output content.
     *
     * @return The bitrate of the content in bits per second
     */
    public int getBitRate();
    
    /**
     * Returns an indication of progressive or interlaced.
     * 
     * @return True if progressive, false if interlaced.
     */
    public boolean isProgressive();
}
