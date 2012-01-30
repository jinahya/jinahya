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
 * This class defines a factory for the creation of
 * <code>Locator</code> objects.
 *
 * @see javax.tv.locator.Locator 
 */
public abstract class LocatorFactory
{

    /** 
     * Creates the <code>LocatorFactory</code> instance.
     */
    protected LocatorFactory() { }

    /** 
     * Provides an instance of <code>LocatorFactory</code>.
     * 
     * @return A <code>LocatorFactory</code> instance.
     */
    public static LocatorFactory getInstance() {
        return null;
    }

    /** 
     * Creates a <code>Locator</code> object from the specified locator
     * string.  The format of the locator string may be entirely
     * implementation-specific.
     *
     * @param locatorString The string form of the <code>Locator</code>
     * to be created.
     *
     * @return A <code>Locator</code> object representing the resource
     * referenced by the given locator string.
     *
     * @throws MalformedLocatorException If an incorrectly formatted
     * locator string is detected.
     *
     * @see Locator#toExternalForm 
     */
    public abstract Locator createLocator(String locatorString)
        throws MalformedLocatorException;

    /** 
     * Transforms a <code>Locator</code> into its respective collection
     * of transport dependent <code>Locator</code> objects. A
     * transformation on a transport dependent <code>Locator</code>
     * results in an identity transformation, i.e. the same locator is
     * returned in a single-element array.
     *
     * @param source The <code>Locator</code> to transform.
     *
     * @return An array of transport dependent <code>Locator</code>
     * objects for the given <code>Locator</code>.
     * 
     * @throws InvalidLocatorException If <code>source</code> is not a valid
     * Locator.  
     */
    public abstract Locator[] transformLocator(Locator source)
        throws InvalidLocatorException;
}
