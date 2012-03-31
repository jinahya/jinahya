/*
 * @(#)DisplayManager.java	1.6 99/07/08
 *
 * Copyright 2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

package com.sun.tv;

import java.awt.*;
import java.awt.event.*;

/**
 * This class implements the DisplayManager. The DisplayManager is a 
 * component to an MHP implementation that controls access to the screen. 
 * Currently this class assumes that the Xlets running on the platform are 
 * are displaying themselves in a root container. The DisplayManager's 
 * only client is the XletManager. <p>
 * 
 * The DisplayManager will create a single frame and then create Containers
 * which will be placed in the root frame.<p>
 *
 * SCCS Version: 99/07/08 @(#)DisplayManager.java	1.6
 *
 * @version ifa
 */

public class DisplayManager {

	private static DisplayManager displayManager = null;

    // Note that more likely this thing that is a Frame here
    // will be a java.awt.Window or HAVi Scene in the IFA demo
    //private Frame toplevel_container = null;

    private Container toplevel_container = null;
    private Container rootlevel_container = null;

    /**
     * Initialize the DisplayManager.
     */
	public DisplayManager(){

		// create RootFrame
	        toplevel_container = new Container(){
	            public Component add(Component c){
	                System.out.print("ROOTFRAME:  ");
	                if (c instanceof com.sun.media.amovie.VisualComponent) {
	                    System.out.println("VIDEO -1:"+c);
	                    //Add as the last component In Zorder 
                            //gets draw first.
	                    //Thread.dumpStack();
	                    super.add(c, -1);
	                } else {
	                    System.out.println("OTHER 0:"+c);
	                    //Add as the first component In Zorder gets draw last.
	                    super.add(c,0);
	                }
	                return c;
	            }
	        };
		toplevel_container.setLayout(new BorderLayout());
		toplevel_container.setSize(640,480); 
 		toplevel_container.validate();
                // to fix 4375018
                toplevel_container.addKeyListener(new MyKeyListener());

                try {
                   rootlevel_container = (Container)Class.forName(com.sun.tv.receiver.Settings.RootContainerClassName).newInstance();
                   // to fix 4375018
                   rootlevel_container.addKeyListener(new MyKeyListener());

		   rootlevel_container.setSize(640,480); 
                   rootlevel_container.add(toplevel_container);
 		   rootlevel_container.validate();
		   rootlevel_container.setVisible(true);

                } catch (Exception e) { e.printStackTrace(); } 

		// wait for requests
	}

	public static DisplayManager createInstance() {
		if (displayManager != null) {
			return displayManager;
		}

		DisplayManager.displayManager = new DisplayManager();

		return displayManager;
	}

	//public Frame getRootFrame() {
	public Container getRootFrame() {
		return (Container)toplevel_container;
	}

    /**
     * Create a new XletContainer. Will create a new
     * XletContainer for an Xlet to display it's content.
     */
    public XletContainer createXletContainer(){
	XletContainer container = new XletContainer();

	// fix for 4335057
	container.setVisible(false);

	container.setBackground(Color.white);
	container.setForeground(Color.black);
        container.setEnabled(true);
	toplevel_container.add(container);	
	
	return container;
    }

    /**
     *
     *
     */
    public void destroyXletContainer(XletContainer container) {
	if (container != null) {
		toplevel_container.remove(container);	
	}
    }

    /**
     * Show the XletContainer.<p>
     * 0) First, remove the existing component if there 
     *    is one.
     * <p>
     * 1) Then add the new one.
     */
    public void showXletContainer(XletContainer container){
	toplevel_container.removeAll();
	toplevel_container.add(container);	
    }
    /**
     * Hide the XletContainer
     * This time we will just remove it.
     */
    public void hideXletContainer(XletContainer container){
	toplevel_container.removeAll();
    }
    /**
     * Signal the user that a Non Auto Start Xlet is
     * can be executed.
     */
    public void signalUserNonAutoStartXlet(){
	System.out.println("DisplayManager.signalUserNonAutoStartXlet() not implemented!!!!");
    }

// to fix 4375018. There must be a better way. 

   class MyKeyListener implements KeyListener{
      public void keyPressed(KeyEvent e) {
         passEvent(e);
      }
      public void keyReleased(KeyEvent e) {
         passEvent(e);
      }
      public void keyTyped(KeyEvent e) {
         passEvent(e);
      }
   
      void passEvent(KeyEvent e) {
         Object o = e.getSource();
         if (o instanceof Container) {
            Container ctn = (Container)o;
            Component[] c = ctn.getComponents();
            for (int i = 0; i < c.length; i++ ) {
               if (c[i] instanceof Container) {
                  c[i].dispatchEvent(
                     new KeyEvent(c[i], e.getID(), e.getWhen(), e.getModifiers(), e.getKeyCode(), e.getKeyChar()));
               }
            }
         }
      }
   }
}   
