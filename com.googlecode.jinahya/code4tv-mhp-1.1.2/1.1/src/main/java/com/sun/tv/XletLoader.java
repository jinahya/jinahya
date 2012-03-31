/*
 * @(#)XletLoader.java	1.4 99/07/09
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

import java.io.*;
import java.lang.ClassLoader; 

/**
 * XletLoader:
 * 
 * Assume we have a carousel string that provides a path to get a carousel.
 * We then read in something of a File style and generate a FileInputStream 
 * from this path.
 *
 * The carousel String might turn to a DSMCCObject later.
 *
 */

public class XletLoader extends ClassLoader {
    private String myCarousel = null;
    private String baseDir = null;

    protected XletLoader(String carousel, String baseDir) throws SecurityException {
	// more security check, access control go here etc.
	myCarousel = carousel;
	this.baseDir = baseDir;
    }

    public Class loadClass(String className) throws ClassNotFoundException {
	return this.loadClass(className, true);
    }

    public Class loadClass(String className, boolean link) 
	throws ClassNotFoundException {

	try {
	    Class newClass = findLoadedClass(className);
	    if (newClass == null) { // not defined yet
		try {
		    newClass = findSystemClass(className); //see if it's system class
		    if (newClass != null) 
			return newClass;
		} catch (ClassNotFoundException e) {
		} 

		// class not found, need to load it from Carousel

		// Rewrite classname to file name "." -> "/"
		String fileName = className.replace('.',File.separatorChar)+".class";
		if (baseDir != null) {
			fileName = baseDir+fileName;
		}

		if (myCarousel != null) {
			fileName = myCarousel+fileName;
		}

		// Open a file on the file name from one of the 
		// "classPaths" we've been given.
		// The mechanism to load the class from carousel might be
		// DSMCCObject dsmccObj;
		// File class = new DSMCCObject(dsmccObj, "relative path");
		// FileInputStream classStream = new FileInputStream(class);
		//
		FileInputStream classStream = new FileInputStream(fileName);

		// Read the bytes from the inputstream.
		// Might need to read more than once.
		byte[] classBytes = new byte[classStream.available()];
		classStream.read(classBytes);
		newClass = defineClass(className, classBytes, 0, classBytes.length);
	    }

	    if ( link && (newClass != null) ) {
		resolveClass(newClass);
	    }

	    return newClass;

	} catch (IOException e) {
	    throw new ClassNotFoundException(className);
	}

    }
}
