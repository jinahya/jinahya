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

import javax.tv.locator.*;

import javax.tv.service.Service;

/** 
 * <code>LocatorFilter</code> represents a <code>ServiceFilter</code>
 * based on a set of locators.  A <code>ServiceList</code> resulting
 * from this filter will include only services matching the specified
 * locators.
 *
 * @see Locator
 * @see ServiceList 
 */
public final class LocatorFilter extends ServiceFilter
{

    /** 
     * Constructs the filter based on a set of locators.
     *
     * @param locators An array of locators representing services
     * to be included in a resulting <code>ServiceList</code>.
     *
     * @throws InvalidLocatorException If one of the given
     * <code>locators</code> does not reference a valid
     * <code>Service</code>.
     */
    public LocatorFilter(Locator[] locators) throws InvalidLocatorException { }

    /** 
     * Reports the locators used to create this filter.
     *
     * @return The array of locators used to create this filter.
     */
    public Locator[] getFilterValue() {
        return null;
    }

    /** 
     * Tests if the given service passes the filter.
     * The service passes the filter if, for one or more Locators
     * specified in the filter constructor,
     * <code>locator.equals(Service.getLocator())</code> returns
     * <code>true</code>.
     *
     * @param service An individual <code>Service</code> to be evaluated
     * against the filtering algorithm.
     *
     * @return <code>true</code> if <code>service</code> passes the filter;
     * <code>false</code> otherwise.
     *
     * @see javax.tv.service.Service#getLocator
     * @see javax.tv.locator.Locator#equals
     * @see javax.tv.locator.Locator#toExternalForm
     */
    public boolean accept(Service service) {
        return false;
    }
}
