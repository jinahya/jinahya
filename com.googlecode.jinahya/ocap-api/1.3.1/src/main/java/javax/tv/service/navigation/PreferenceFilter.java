/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.tv.service.navigation;

import javax.tv.service.Service;

/** 
 * <code>PreferenceFilter</code> represents a
 * <code>ServiceFilter</code> based on a user preference for favorite
 * services. A <code>ServiceList</code> resulting from this filter
 * will include only user favorite services contained in the specified
 * preference.
 *
 * @see FavoriteServicesName
 * @see ServiceList 
 */
public final class PreferenceFilter extends ServiceFilter
{

    /** 
     * Constructs the filter based on a particular user preference
     * for favorite services.
     *
     * @param preference A named user preference, obtained from
     * the <code>listPreferences()</code> method, representing favorite
     * Services to be included in a resulting service list.
     *
     * @throws IllegalArgumentException If the specified preference is
     * not obtainable from the <code>listPreferences()</code> method.
     *
     * @see #listPreferences
     */
    public PreferenceFilter(FavoriteServicesName preference) { }

    /** 
     * Reports the available favorite service preferences that
     * can be used to create this filter.
     *
     * @return An array of preferences for favorite services.  If none
     * exist or are supported, an empty array is returned.
     */
    public static FavoriteServicesName[] listPreferences() {
        return null;
    }

    /** 
     * Reports the user preference used to create this filter.
     *
     * @return The user preference representing the favorite services
     * by which the filter was constructed.
     */
    public FavoriteServicesName getFilterValue() {
        return null;
    }

    /** 
     * Tests if the given service passes the filter.
     *
     * @param service An individual <code>Service</code> to be evaluated
     * against the filtering algorithm.
     *
     * @return <code>true</code> if <code>service</code> is part of the
     * favorite services indicated by the filter value; <code>false</code>
     * otherwise.  
     */
    public boolean accept(Service service) {
        return false;
    }
}
