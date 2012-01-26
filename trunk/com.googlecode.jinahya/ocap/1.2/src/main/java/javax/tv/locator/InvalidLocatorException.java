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
 * This exception is thrown when a <code>Locator</code> is not valid
 * in a particular context.  A <code>Locator</code> can be invalid or
 * several reasons, including:
 *
 * <ul>
 *
 * <li> The <code>Locator</code> refers to a resource that is not
 * valid at the time of usage.
 *
 * <li> The <code>Locator</code> refers to a type of resource that is
 * not appropriate for usage as a particular method parameter.
 *
 * <li> The <code>Locator</code> refers to a type of
 * resource whose usage is not supported on this system.
 *
 * </ul> 
 */
public class InvalidLocatorException extends Exception
{

    /** 
     * Constructs an <code>InvalidLocatorException</code> with no
     * detail message.
     *
     * @param locator The offending <code>Locator</code>.
     */
    public InvalidLocatorException(Locator locator) { }

    /** 
     * Constructs an <code>InvalidLocatorException</code> with the
     * specified detail message.
     *
     * @param locator The offending <code>Locator</code>.
     * @param reason The reason this <code>Locator</code> is invalid.
     */
    public InvalidLocatorException(Locator locator, String reason) { }

    /** 
     * Returns the offending <code>Locator</code> instance.
     *
     * @return The locator that caused the exception.
     */
    public Locator getInvalidLocator() {
        return null;
    }
}
