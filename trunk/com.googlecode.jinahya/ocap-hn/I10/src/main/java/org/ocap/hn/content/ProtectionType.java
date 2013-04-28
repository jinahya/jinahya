package org.ocap.hn.content;

/**
 * Interface defining constants that represent supported
 * output protection types to be used in conjunction
 * with the <code>ContentFormat</code> interface.
 * 
 */
public interface ProtectionType 
{
    
    /**
     * DTCP-IP protection type as defined in [OC-BUNDLE].
     */
    public static final String DTCP_IP = "DTCP-IP";
    
    /**
     * DECE DRM type. Used with any DRM that is part of DECE as defined 
     * in [OC-BUNDLE].
     */
    public static final String DECE_DRM = "DECE-DRM";
    
}
