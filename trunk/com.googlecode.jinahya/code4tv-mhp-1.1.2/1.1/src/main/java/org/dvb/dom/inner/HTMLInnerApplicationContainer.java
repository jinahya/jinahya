package org.dvb.dom.inner;

import java.net.URL;

import java.io.IOException;

import org.dvb.application.inner.InnerApplicationContainer;

import org.dvb.dom.bootstrap.DocumentAction;

/**
 * Represents embedding of an DVB-HTML inner application within the user 
 * interface of a DVB-J application.
 */

public class HTMLInnerApplicationContainer extends InnerApplicationContainer {
	/**
	 * Construct an instance of this class with a DVB-HTML application
	 * as its content. The instance is initialized to present the
	 * entry point document of the application.
 	 * 
	 * @param a the DVB-HTML application
	 * @exception IOException if an error occurred while reading the
 	 * code or data for the inner application
	 */
	public HTMLInnerApplicationContainer( HTMLApplication a )
		throws IOException { super(a);}

	    /**
             * Perform an action on the DVB-HTML document.
             *
             * @param act  The action to perform. It will be called by
             *             the system either synchronously, or on a system
             *             thread.
             **/
            public void performAction(DocumentAction act){}
}	

