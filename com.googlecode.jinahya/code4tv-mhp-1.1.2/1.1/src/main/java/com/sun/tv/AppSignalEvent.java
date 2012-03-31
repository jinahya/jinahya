/*
 * @(#)AppSignalEvent.java        1.27 00/08/27
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
 *
 */

package com.sun.tv;

import java.util.*;
import javax.tv.service.selection.*;

/**
 * This class represents an event that is sent from the service selection
 * subsystem to the Application Manager when an application is signaled
 * on a newly selected service. It will include the following information
 * from signaling descriptors:<p>
 * <code>application_control_code</code> (see table below).<p>
 * <code>base_directory_byte</code><p>
 * <code>class_extension_byte</code><p>
 * <code>initial_class_byte</code><p>
 *
 *
 * <b>application_control_code:</b> <i>This control code allows the
 * broadcaster to signal to the receiver what to do with the application
 * with regard to its lifecycle. The possible codes are list below.</i><p>
 *
 * <table BORDER COLS=3 WIDTH="100%" NOSAVE >
 * <tr>
 * <td VALIGN=TOP WIDTH="10%"><b>Code</b></td>
 *
 * <td WIDTH="10%"><b>Identifier</b></td>
 * 
 * <td><b>Semantics</b></td>
 * </tr>
 *
 * <tr>
 * <td><tt>0x00</tt></td>
 *
 * <td></td>
 * 
 * <td>reserved</td>
 * </tr>
 * 
 * <tr>
 * <td VALIGN=TOP><tt>0x01</tt></td>
 * 
 * <td VALIGN=TOP><b><tt>AUTOSTART</tt></b></td>
 * 
 * <td>The Object Carousel module containing the class implementing the Xlet
 * interface is loaded, The class implementing the Xlet is loaded into the
 * VM and an Xlet object is instanciated, and the application is started.</td>
 * </tr>
 * 
 * <tr>
 * <td VALIGN=TOP><tt>0x02</tt></td>
 * 
 * <td VALIGN=TOP><b><tt>PRESENT</tt></b></td>
 * 
 * <td>Indicates that the application is present in the service, but is not
 * autostarted.</td>
 * </tr>
 * 
 * <tr>
 * <td VALIGN=TOP><tt>0x03</tt></td>
 * 
 * <td VALIGN=TOP><b><tt>PREFETCH</tt></b></td>
 * 
 * <td VALIGN=TOP>Indicates that the receiver should try to prefetch the 
 * application.
 * Exact semantics to be defined. <b>NOTE: Not used in the DVB-IFA profile!
 * </b></td>
 * </tr>
 * 
 * <tr>
 * <td VALIGN=TOP><tt>0x04</tt></td>
 * 
 * <td VALIGN=TOP><b><tt>DESTROY</tt></b></td>
 * 
 * <td VALIGN=TOP>When the control code changes from <b><tt>AUTOSTART</tt></b>
 * or <b><tt>PRESENT</tt></b> to <b><tt>DESTROY</tt></b>, the destroy method
 * of the Xlet is called by the application manager and the application is
 * allowed to destroy itself gracefully.</td>
 * </tr>
 * 
 * <tr>
 * <td VALIGN=TOP><tt>0x05</tt></td>
 * 
 * <td VALIGN=TOP><b><tt>KILL</tt></b></td>
 * 
 * <td>When the control code changes to <b><tt>KILL</tt></b>, the application
 * is terminated by the application manager.</td>
 * </tr>
 * 
 * <tr>
 * <td><tt>0x06...0x0FF</tt></td>
 * 
 * <td></td>
 * 
 * <td>Reserved for future use.</td>
 * </tr>
 * </table>
 *
 * <p>
 * <b>base_directory_byte:</b> <i>These bytes contain a string specifying
 * a directory namer starting from the root of the carousel with directories
 * delimited by the slash character '/' (<code>0x2F</code>). This 
 * directory is used as a base directory for relative path names. This
 * base directory is automatically considered to form the first directory
 * in the class path (after the path to the systems classes).</i>
 * <p>
 *
 * <b>classpath_extension_byte:</b><i>These bytes contain a string
 * specifying a further extension for the Java runtime class path where
 * the classes of the application are searched in addition to the base 
 * directory. The class path extension string contains path names 
 * where the elements in the path are delimited by the semicolon
 * character ';' (<code>0xXX</code>). The elements of the path may be
 * either absolute paths starting from the root of the carousel or
 * they can be relative to the base directory. The directories are 
 * delimited by the slash character '/' (<code>0x2F</code>) and
 * absolute path names begin with the slash character '/' (<code>0x2F
 * </code>).</i><p>
 *
 * <b>initial_class_byte:</b> <i>These bytes contain a string specifying
 * the name of the object in the carousel that contains the class 
 * implementing the Xlet interface. This string is a Java class name
 * that is found in the class path. 
 * (e.g. <code>com.brodcaster.appA.MainClass</code>).</i>
 * <p>
 * <b>application_identifier_byte:</b> <i>The application identifier
 * byte will be used for an application identifier.</i><p>
 */

public class AppSignalEvent extends EventObject {
    
    /**
     * Indicates that the signalled application is <i>Autostart</i>
     */
    public static final byte AUTOSTART = 0x01;
    
    public static final byte STORE = 0x02;

    public static final byte START = 0x03;

    /**
     * Indicates that there is a non autostart application present
     * in the service.
     */
    public static final byte PRESENT = 0x04;

    /**
     * Indicates that the receiver should try to prefetch
     * the application.
     */
    public static final byte PREFETCH = 0x05;

    /**
     * Indicates that the app manager should call the Xlet's <code>
     * pauseXlet</code> method.
     */
    public static final byte PAUSE = 0x06;

    /**
     * Indicates that the Applcation Manager should call
     * the Xlet's <code>destroyXlet</code> method and allow
     * the application to exit gracefully.
     */
    public static final byte DESTROY = 0x07;

    /**
     * Indicates that the application manager should kill the 
     * the application.
     */
    public static final byte KILL = 0x08;

    private byte controlCode = 0x00;
    private String className = null;
    private String appID = null;
    private String baseDir = null;
    private String args[] = null;
    private ServiceContext context = null;

    public AppSignalEvent(
		Object source, byte controlCode, String baseDir,
		String className, String id, ServiceContext context,
		String args[]) {

	super(source);

	if (controlCode < AUTOSTART || controlCode > KILL) {
	    throw new IllegalArgumentException("wrong value for control");
	}

	this.controlCode = controlCode;
	this.baseDir = baseDir;
	this.className = className;
	this.appID = id;
	this.args = args;
	this.context = context;
    }

    /**
     * Get the control code.
     */
    public byte getControlCode() {
	return controlCode; 
    }

    /**
     * get base directory
     */
    public String getBaseDirectory() {
	return baseDir; 
    }

    /**
     * get Class name
     */
    public String getClassName() {
	return className;
    }

    /**
     * get Xlet args
     */
    public String[] getArgs() {
	return args;
    }

    /**
     * get unique identifier from broadcaster.
     */
    public String getApplicationIdentifier() {
	return appID;
    }

    public ServiceContext getServiceContext() {
	return this.context;
    }

    public String toString() {
	return appID+" "+baseDir+" "+className+" "+controlCode;
    }
}
