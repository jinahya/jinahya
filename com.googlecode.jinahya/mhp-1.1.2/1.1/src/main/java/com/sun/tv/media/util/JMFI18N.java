/*
 * @(#)JMFI18N.java	1.6 98/11/19
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv.media.util;

import java.util.*;
import com.sun.tv.media.util.locale.*;
//A.D.import com.sun.tv.media.BuildInfo;

/**
 * This class is a part of the porting layer implementation for JavaTV.
 * This class specifies the resource bundle class, i.e. class that contains locale specific objects.
*/

public class JMFI18N {
    private static java.util.ResourceBundle bundle = null;

    public static synchronized String getResource(String key){

	Locale currentLocale = java.util.Locale.getDefault();
	String value;

	if (bundle == null) {
	    try{
/*A.D.	    	if (BuildInfo.usePureJava()) {
		    bundle = new com.sun.tv.media.util.locale.JMFProps();
                } else {
	A.D.*/	    bundle = java.util.ResourceBundle.getBundle("com.sun.tv.media.util.locale.JMFProps", currentLocale);
  //A.D.            }
	    } catch(java.util.MissingResourceException e){
				bundle = new com.sun.tv.media.util.locale.JMFProps();
	    }
        }

	try{
	    value = (String)bundle.getObject(key);
        } catch (java.util.MissingResourceException e){
	    value = null;
	}

        return value;
    }
}
    