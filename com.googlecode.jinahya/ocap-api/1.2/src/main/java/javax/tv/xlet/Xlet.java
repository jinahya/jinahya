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
 * This interface allows an application manager to create,
 * initialize, start, pause, and destroy an Xlet.
 * An Xlet is an application or service designed to be run and
 * controlled by an application manager via this lifecycle interface.
 * The lifecycle states allow the application manager to manage
 * the activities of multiple Xlets within a runtime environment
 * by selecting which Xlets are active at a given time.
 * The application manager maintains the state of the Xlet and
 * calls the lifecycle methods of the <code>Xlet</code> interface.
 * The Xlet implements these methods to update its internal activities
 * and resource usage as directed by the application manager.
 * The <code>Xlet</code> interface methods signal state changes in
 * the application lifecycle, so they should be implemented to return
 * quickly.  A state transition is not complete until the state change
 * method has returned.
 * <p>
 * The Xlet can initiate some state changes itself and informs
 * the application manager of those state changes  
 * by invoking methods on <code>XletContext</code>.
 * <p>
 * In order to support interoperability between Xlets and application
 * managers, all Xlet classes must provide a public no-argument
 * constructor.
 * <p>
 * The application manager directs the lifecycle of Xlets in light of
 * their access to any scarce resources that must be shared within the
 * system, such as as media players, tuners and section filters. In
 * some systems, screen real estate may also be considered such a shared
 * resource. Xlets are responsible for minimizing their use of shared
 * resources while in any state other than the <em>active</em> state.
 * An Xlet that holds shared resources improperly is eligible for
 * forceful termination by the application manager.
 * 
 * @see XletContext
 */
public interface Xlet
{

    /** 
     * Signals the Xlet to initialize itself and enter the 
     * <i>Paused</i> state.
     * The Xlet shall initialize itself in preparation for providing service.
     * It should not hold shared resources but should be prepared to provide 
     * service in a reasonable amount of time.
     * <p>
     * After this method returns successfully, the Xlet
     * is in the <i>Paused</i> state and should be quiescent. <p>
     * <b>Note:</b> This method is called only once.<p>
     *
     * @param ctx The <code>XletContext</code> for use by this Xlet, by which
     * it may obtain initialization arguments and runtime properties.
     *
     * @exception XletStateChangeException If the Xlet cannot be
     * initialized.
     *
     * @see javax.tv.xlet.XletContext#ARGS
     * @see javax.tv.xlet.XletContext#getXletProperty
     */
    public void initXlet(XletContext ctx) throws XletStateChangeException;

    /** 
     * Signals the Xlet to start providing service and
     * enter the <i>Active</i> state.
     * In the <i>Active</I> state the Xlet may hold shared resources.
     * The method will only be called when
     * the Xlet is in the <i>paused</i> state.
     * <p>
     *      
     * @exception XletStateChangeException  is thrown if the Xlet
     *		cannot start providing service. 
     */
    public void startXlet() throws XletStateChangeException;

    /** 
     * Signals the Xlet to stop providing service and
     * enter the <i>Paused</i> state.
     * In the <i>Paused</i> state the Xlet must stop providing
     * service, and should release all shared resources
     * and become quiescent. This method will only be called
     * called when the Xlet is in the <i>Active</i> state.
     *
     */
    public void pauseXlet();

    /** 
     * Signals the Xlet to terminate and enter the <i>Destroyed</i> state.
     * In the destroyed state the Xlet must release
     * all resources and save any persistent state. This method may
     * be called from the <i>Loaded</i>, <i>Paused</i> or 
     * <i>Active</i> states. <p>
     * Xlets should
     * perform any operations required before being terminated, such as
     * releasing resources or saving preferences or
     * state. <p>
     *
     * <b>NOTE:</b> The Xlet can request that it not enter the <i>Destroyed</i>
     * state by throwing an <code>XletStateChangeException</code>. This 
     * is only a valid response if the <code>unconditional</code>
     * flag is set to <code>false</code>. If it is <code>true</code>
     * the Xlet is assumed to be in the <i>Destroyed</i> state
     * regardless of how this method terminates. If it is not an 
     * unconditional request, the Xlet can signify that it wishes
     * to stay in its current state by throwing the Exception.
     * This request may be honored and the <code>destroyXlet()</code>
     * method called again at a later time. 
     *
     *
     * @param unconditional If <code>unconditional</code> is true when this
     * method is called, requests by the Xlet to not enter the
     * destroyed state will be ignored.
     *   
     * @exception XletStateChangeException is thrown if the Xlet
     *		wishes to continue to execute (Not enter the <i>Destroyed</i>
     *          state). 
     *          This exception is ignored if <code>unconditional</code> 
     *          is equal to <code>true</code>.
     * 
     * 
     */
    public void destroyXlet(boolean unconditional)
        throws XletStateChangeException;
}
