package org.dvb.application.plugins;

import javax.tv.xlet.Xlet;
import org.dvb.application.AppAttributes;
import org.dvb.application.inner.InnerApplication;

/**
 * This interface allows an application or service manager to control
 * the lifecycle of applications being executed by an inter-operable
 * plug-in. It shall be implemented by inter-operable plug-ins and
 * shall be called by implementation of the application manager in
 * an MHP terminal. It shall not be called by MHP applications.
 *
 * The usage model for this interface is the reuse of the 
 * <code>javax.tv.xlet.Xlet</code> interface to represent applications 
 * in formats other than DVB-J. 
 * It is the responsibility of the plug-in to provide implementations of the
 * methods in that interface and to perform the translation between the
 * semantics specified for DVB-J applications and the semantics of the
 * application format which the plug-in supports.
 *
 * The plug-in is initialized by the application manager calling the
 * no-argument constructor of the object implementing this interface.
 * Plug-ins are not expected to perform any time consuming activities
 * or hold any scarce resources in this call.
 */

public interface Plugin {
	/**
	 * Test whether this plug-in is able to support a particular
	 * application. This method shall work regardless of whether the
	 * plug-in has been initialized or not.
	 * 
	 * @param app an instance of <code>AppAttributes</code> for the
	 * application which is to be started.
	 * @return true if the plug-in can support this application otherwise
	 * false
	 */
	public boolean isSupported( AppAttributes app);

	/**
	 * Request the plug-in to prepare for executing an instance of an
	 * application in a format which it supports. If the application
	 * cannot be prepared then null shall be returned. While the plug-in 
	 * is in the terminated state then this method shall always return null.
	 * The application shall only be considered to be started when the
	 * application manager calls the methods on the Xlet interface returned
	 * by this method.
 	 *
	 * @param app an instance of <code>AppAttributes</code> for the
	 * application which is to be started.
	 * @return an object implementing the Xlet interface or null if
	 * the application cannot be started.
	 * @throws InvalidApplicationException if the application to be started
	 * is not valid for this plug-in
	 */
	public Xlet initApplication(AppAttributes app) throws InvalidApplicationException;

	/**
	 * initialize the plug-in. Any time consuming initializations
	 * should be performed during this method call. This method shall
	 * be called after the no-argument constructor of the object
	 * implementing this interface and before any calls to the
	 * <code>startApplication</code>. It may also be called after a call
	 * to <code>terminatePlugin</code> if a plug-in was not removed from
	 * the virtual machine where it was executing. The behaviour of an
	 * application manager should a plug-in fail to initialize is
	 * intentionally unspecified.
	 *
	 * @return true if the plug-in initialized successfully otherwise false
	 */
	public boolean initPlugin();

	/**
	 * Terminate the plug-in. This method shall only be called after
	 * all applications being executed by this plug-in have been
	 * terminated. The implementation of this method shall release 
	 * all resources held by the plug-in. Once this method has returned,
	 * the plug-in shall either be unloaded from the virtual machine
	 * where it was executing or the <code>initPlugin</code> method
	 * shall be called again to put the plug-in again in an initialized
	 * condition. A plug-in may not refuse to terminate and throwing a
	 * runtime exception or error from this method shall result in the
	 * plug-in being removed from the virtual machine where it was
	 * executing.
	 */
	public void terminatePlugin();

/**
 * Request the plug-in to prepare for executing an instance of an application in a format 
 * which it supports.If the application cannot be prepared then null shall be returned.
 * While the plug-in is in the terminated state then this method shall always return null.
 * The application shall only be considered to be started when the application manager calls 
 * the methods on the Xlet interface returned by this method.
 *
 * @param app an instance of InnerApplication for the application which is to be started
 * @return an object implementing the Xlet interface or null if the application cannot 
 * be started
 * @throws InvalidApplicationException if the application to be started is not valid for 
 * this plug-in
 */
public javax.tv.xlet.Xlet initApplication(InnerApplication app) 
    throws InvalidApplicationException;
}



