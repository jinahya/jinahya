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



  


package javax.tv.xlet;

/** 
 * An interface that provides methods allowing an Xlet to discover
 * information about its environment. An XletContext is passed
 * to an Xlet when the Xlet is initialized. It provides an Xlet with a
 * mechanism to retrieve properties, as well as a way to signal
 * internal state changes.
 * <p>
 * Critical resources (such as an Xlet's parent container and service
 * context) can be obtained by means of an <code>XletContext</code>
 * instance.  Therefore, an Xlet's <code>XletContext</code> instance
 * should only be accessible to other code that is highly trusted.
 *
 * @see javax.tv.xlet.Xlet
 * @see javax.tv.graphics.TVContainer
 * @see javax.tv.service.selection.ServiceContextFactory#getServiceContext
 */
public interface XletContext
{
    /** 
     * The property key used to obtain initialization arguments for the
     * Xlet.  The call
     * <code>XletContext.getXletProperty(XletContext.ARGS)</code> will
     * return the arguments as an array of Strings.  If there are
     * no arguments, then an array of length 0 will be returned.
     *
     * @see #getXletProperty
     */
    public static final String ARGS = "javax.tv.xlet.args";

    /** 
     * Used by an application to notify its manager that it
     * has entered into the
     * <i>Destroyed</i> state.  The application manager will not
     * call the Xlet's <code>destroy</code> method, and all resources
     * held by the Xlet are considered eligible for immediate reclamation.  
     * Before calling this method,
     * the Xlet must perform the same operations
     * (clean up, releasing of resources etc.) as it would in response
     * to call to {@link Xlet#destroyXlet}.
     * <p>
     * If this method is called during the execution of one of the
     * {@link Xlet} state transition methods, the Xlet will immediately
     * transition into the <i>Destroyed</i> state and the Xlet state
     * transition method is considered to have completed
     * <em>unsuccesfully</em>.
     */
    public void notifyDestroyed();

    /** 
     * Notifies the manager that the Xlet does not want to be active and has
     * entered the <i>Paused</i> state.  Invoking this method will
     * have no effect if the Xlet is destroyed, or if it has not
     * yet been started.
     * <p>
     * If an Xlet calls <code>notifyPaused()</code>, in the
     * future it may receive an <i>Xlet.startXlet()</i> call to request
     * it to become active, or an <i>Xlet.destroyXlet()</i> call to request
     * it to destroy itself.
     * 
     */
    public void notifyPaused();

    /** 
     * Provides an Xlet with a mechanism to retrieve named
     * properties from the XletContext.
     *
     * @param key The name of the property.
     *
     * @return A reference to an object representing the property.
     * <code>null</code> is returned if no value is available for key.
     */
    public Object getXletProperty(String key);

    /** 
     * Provides the Xlet with a mechanism to indicate that it is
     * interested in entering the <i>Active</i> state. Calls to this
     * method can be used by an application manager to determine which
     * Xlets to move to <i>Active</i> state.  Any subsequent call to
     * <code>Xlet.startXlet()</code> as a result of this method will
     * be made via a different thread than the one used to call
     * <code>resumeRequest()</code>.
     * 
     * @see Xlet#startXlet
     */
    public void resumeRequest();
}
