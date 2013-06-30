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



  


package javax.tv.service.selection;

/** 
 * This class serves as a factory for the creation of
 *<code>ServiceContext</code> objects.
 */
public abstract class ServiceContextFactory
{

    /** 
     * Creates a <code>ServiceContextFactory</code>.
     */
    protected ServiceContextFactory() { }

    /** 
     * Provides an instance of <code>ServiceContextFactory</code>.
     *
     * @return An instance of <code>ServiceContextFactory</code>.
     */
    public static ServiceContextFactory getInstance() {
        return null;
    }

    /** 
     * Creates a <code>ServiceContext</code> object.  The new
     * <code>ServiceContext</code> is created in the <em>not
     * presenting</em> state.
     *
     * <p>Due to resource restrictions,
     * implementations may limit the total number of simultaneous
     * <code>ServiceContext</code> objects.  In such a case,
     * <code>InsufficientResourcesException</code> is thrown.
     *
     * @return A new <code>ServiceContext</code> object.
     *
     * @throws InsufficientResourcesException If the receiver lacks
     * the resources to create this <code>ServiceContext</code>.
     *
     * @throws SecurityException if the caller doesn't have
     * <code>ServiceContextPermission("create", "own")</code>.
     * 
     * @see ServiceContextPermission
     */
    public abstract ServiceContext createServiceContext()
        throws InsufficientResourcesException, SecurityException;

    /** 
     * Provides the <code>ServiceContext</code> instances to which the
     * caller of the method is permitted access.  If the caller has
     * <code>ServiceContextPermission("access","*")</code>, then all
     * current (i.e., undestroyed) <code>ServiceContext</code> instances
     * are returned.  If the application making this call is running in
     * a <code>ServiceContext</code> and has
     * <code>ServiceContextPermission("access","own")</code>, its own
     * <code>ServiceContext</code> will be included in the returned
     * array.  If no <code>ServiceContext</code> instances are
     * accessible to the caller, a zero-length array is returned.  No
     * <code>ServiceContext</code> instances in the <em>destroyed</em>
     * state are returned by this method.
     *
     * @return An array of accessible <code>ServiceContext</code> objects.
     *
     * @see ServiceContextPermission
     */
    public abstract ServiceContext[] getServiceContexts();

    /** 
     * Reports the <code>ServiceContext</code> in which the
     * <code>Xlet</code> corresponding to the specified
     * <code>XletContext</code> is running.  The returned
     * <code>ServiceContext</code> is the one from which the
     * <code>Service</code> carrying the <code>Xlet</code> was selected,
     * and is the Xlet's "primary" <code>ServiceContext</code>.
     *
     * @param ctx The <code>XletContext</code> of the <code>Xlet</code>
     * of interest.
     *
     * @return The <code>ServiceContext</code> in which the <code>Xlet</code>
     * corresponding to <code>ctx</code> is running.
     *
     * @throws SecurityException If the caller does not have
     * <code>ServiceContextPermission("access", "own")</code>.
     *
     * @throws ServiceContextException If the
     * <code>Xlet</code> corresponding to <code>ctx</code> is not running
     * within a <code>ServiceContext</code>.
     *
     * @see ServiceContextPermission
     */
    public abstract ServiceContext getServiceContext(javax.tv.xlet.XletContext
        ctx) throws SecurityException, ServiceContextException;
}
