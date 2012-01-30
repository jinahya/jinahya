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



  


package javax.tv.locator;

/** 
 * The <code>Locator</code> interface provides an opaque reference to
 * the location information of objects which are addressable within the
 * Java TV API. A given locator may represent a transport independent
 * object and have multiple mappings to transport dependent locators.
 * Methods are provided for discovery of such circumstances and for
 * transformation to transport dependent locators.
 *
 * @see javax.tv.locator.LocatorFactory
 * @see javax.tv.locator.LocatorFactory#transformLocator
 */
public interface Locator
{

    /** 
     * Generates a canonical, string-based representation of this
     * <code>Locator</code>. The string returned may be entirely
     * platform-dependent.  If two locators have identical external
     * forms, they refer to the same resource.  However, two locators
     * that refer to the same resource may have different external
     * forms.<p>
     *
     * This method returns the canonical
     * form of the string that was used to create the Locator (via
     * <code>LocatorFactory.createLocator()</code>).  In generating
     * canonical external forms, the implementation will make its best
     * effort at resolving locators to one-to-one relationships
     * with the resources that they reference.<p>
     *
     * The result of this method can be used to create new
     * <code>Locator</code> instances as well as other types of
     * locators, such as JMF <code>MediaLocator</code>s and
     * <code>URL</code>s.
     *
     * @return A string-based representation of this Locator.
     * 
     * @see LocatorFactory#createLocator
     * @see javax.media.MediaLocator javax.media.MediaLocator 
     * @see java.net.URL 
     */
    public String toExternalForm();

    /** 
     * Indicates whether this <code>Locator</code> has a mapping to
     * multiple transports.
     *
     * @return <code>true</code> if multiple transformations exist for
     * this <code>Locator</code>, false otherwise.  
     */
    public boolean hasMultipleTransformations();

    /** 
     * Compares this <code>Locator</code> with the specified object for
     * equality.  The result is <code>true</code> if and only if the
     * specified object is also a <code>Locator</code> and has an
     * external form identical to the external form of this
     * <code>Locator</code>.
     *
     * @param o The object against which to compare this <code>Locator</code>.
     *
     * @return <code>true</code> if the specified object is equal to this
     * <code>Locator</code>.
     *
     * @see java.lang.String#equals(Object)
     */
    public boolean equals(Object o);

    /** 
     * Generates a hash code value for this <code>Locator</code>.
     * Two <code>Locator</code> instances for which <code>Locator.equals()</code>
     * is <code>true</code> will have identical hash code values.
     *
     * @return The hash code value for this <code>Locator</code>.
     *
     * @see #equals(Object)
     */
    public int hashCode();

    /** 
     * Returns the string used to create this locator.
     *
     * @return The string used to create this locator.
     *
     * @see LocatorFactory#createLocator
     */
    public String toString();
}
