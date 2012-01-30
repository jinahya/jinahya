package org.dvb.application;

import java.util.*;

/**
 * The <code>AppIcon</code> encapsulates the information concerning
 *  the icon attached to the application
 */
public class AppIcon {
 
    /**
     * The constructor for the class. This constructor is intended for implementation
     * convenience and evolution of the specification and not for use by MHP applications. 
     * Applications should obtain instances of this class from <code>AppAttributes.getAppIcon</code>.
     * @see org.dvb.application.AppAttributes#getAppIcon
     */
    protected AppIcon() {}

    /**
     * This method returns the location of the directory containing the application icons.
     *
     * @return the location of the directory containing the application icons.
     * @since   MHP1.0
     */
    public org.davic.net.Locator getLocator() {return null;}

    
    /**
     * This method returns the flags identifying which icons are provided for the application.
     *
     * @return the icon flags encoded as a BitSet
     * @since   MHP1.0
     */
    public BitSet getIconFlags(){return null;};
}

