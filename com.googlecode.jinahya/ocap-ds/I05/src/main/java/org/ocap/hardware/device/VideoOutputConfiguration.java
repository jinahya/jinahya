/*
 * Created on Mar 20, 2007
 */
package org.ocap.hardware.device;

/**
 * Describes a <code>VideoOutputConfiguration</code> supported by a 
 * <code>VideoOutputPort</code>.
 * 
 * @see VideoOutputSettings
 * 
 * @author Aaron Kamienski
 */
public interface VideoOutputConfiguration
{
    /**
     * Get the <code>String</code> representation of this <code>VideoOutputConfiguration</code>,
     * suitable for display to the user.
     * 
     * @return <code>String</code> representation of this object
     */
    public String getName();
}
