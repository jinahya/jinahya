package org.ocap.hn.content;

/**
 * This interface represents a content format. Instances of this
 * interface represent the specific formats in which content is
 * either available or can be transformed into.
 */
public interface ContentFormat
{
    
    /**
     * This method returns an identifier representing the media format of 
     * the content. See [OC-BUNDLE] and <code>ContentProfile</code> for a list 
     * of valid identifiers.
     * See {@link ContentProfile}
     * 
     * @return The media format of the content.
     */
    public String getContentProfile();    
    
    /**
     * Returns the protection type of the content.  
     * This method returns the <code>ProtectionType</code> registered as an
     * approved output with CableLabs. 
     * See {@link ProtectionType}
     * 
     * @return The protection type. Returns empty string if the 
     *      content is not protected.
     */
    public String getProtectionType();
        
}
