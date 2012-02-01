/*
 * @(#)XletContainer.java	1.2 99/07/08
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
import java.awt.Component; 
import java.awt.Container; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 

/**
 * This class is an AWT container that can be used by an Xlet. In 
 * particular, the <code>getParent()</code> method on the Xlet
 * will be overridden to prevent the Xlet from gaining access to the
 * root frame.
 */
public class XletContainer extends Container implements KeyListener {
    /**
     * Normally would return the parent, but here it will
     * return <code>null</code> always.<p>
     * <b>Note:</b> That the main purpose of this class
     * is to prevent Xlet UIs from accessing their AWT
     * parents.
     */

    public XletContainer() {
       super();
       System.out.println("XletContainer constructor");
    }

    public Component add(Component c) {
       System.out.print("XLETCONTAINER:  ");
       if (c instanceof com.sun.media.amovie.VisualComponent) {
          System.out.println("VIDEO -1:"+c);
          //Add as the last component In Zorder gets draw first.
          //Thread.dumpStack();
          super.add(c, -1);
       } else {
          System.out.println("OTHER 0:"+c);
          //Add as the first component In Zorder gets draw last.
          super.add(c,0);
       }
       System.out.println("XletContainer done adding");
       return c;
    }

   public void keyPressed(KeyEvent e) {
//      System.out.println("Key pressed!");
   }
   public void keyReleased(KeyEvent e) {
//      System.out.println("Key released!");
   }
   public void keyTyped(KeyEvent e) {
//      System.out.println("Key typed!");
   }

}
