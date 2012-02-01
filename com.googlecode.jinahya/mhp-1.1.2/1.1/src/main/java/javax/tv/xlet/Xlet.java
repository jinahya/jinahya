/*
 * @(#)Xlet.java	1.20 00/08/23
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.tv.xlet;


/** 
 * This interface allows an application manager to create,
 * initialize, start, pause, and destroy an Xlet.
 * An Xlet is an application or service designed to be run and
 * controlled by an application manager via this lifecycle interface.
 * The lifecycle states allow the application manager to manage
 * the activities of multiple Xlets within a runtime environment
 * selecting which Xlets are active at a given time.
 * The application manager maintains the state of the Xlet and
 * invokes method on the Xlet via the lifecycle methods.  The Xlet
 * implements these methods to update its internal activities and
 * resource usage as directed by the application manager. 
 * The Xlet can initiate some state changes itself and informs
 * the application manager of those state changes  
 * by invoking methods on <code>XletContext</code>.<p>
 *
 * In order to support interoperability between Xlets and application
 * managers, all Xlet classes must provide a public no-argument
 * constructor.<p>
 * 
 * <b>Note:</b> The methods on this interface are meant to signal state 
 * changes. The state change is not considered complete until the state
 * change method has returned. It is intended that these methods return
 * quickly.<p>
 *
 * @see XletContext
 */
public interface Xlet {

    /**      
     * Signals the Xlet to initialize itself and enter the 
     * <i>Paused</i> state.
     * The Xlet shall initialize itself in preparation for providing service.
     * It should not hold shared resources but should be prepared to provide 
     * service in a reasonable amount of time. <p>
     * An <code>XletContext</code> is used by the Xlet to access
     * properties associated with its runtime environment.
     * After this method returns successfully, the Xlet
     * is in the <i>Paused</i> state and should be quiescent. <p>
     * <b>Note:</b> This method shall only be called once.<p>
     *
     * @param ctx The <code>XletContext</code> of this Xlet.
     * @exception XletStateChangeException If the Xlet cannot be
     * initialized.
     * @see javax.tv.xlet.XletContext
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

    /*
     * Two kinds of failures can prevent the service from starting,
     * transient and non-transient.  For transient failures the
     * <code>XletStateChangeException</code> exception should be thrown.
     * For non-transient failures the <code>XletContext.notifyDestroyed</code>
     * method should be called.
     *
     * @see XletContext#notifyDestroyed
     */
    public void startXlet() throws XletStateChangeException;
    
    /**
     *
     * Signals the Xlet to stop providing service and
     * enter the <i>Paused</i> state.
     * In the <i>Paused</i> state the Xlet must stop providing
     * service, and might release all shared resources
     * and become quiescent. This method will only be called
     * called when the Xlet is in the <i>Active</i> state. <p>
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



