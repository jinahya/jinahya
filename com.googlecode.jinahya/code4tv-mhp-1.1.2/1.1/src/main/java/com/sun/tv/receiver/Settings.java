/*
 * @(#)Settings.java	1.00 2000/04/05
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
 */

package com.sun.tv.receiver;

import java.io.*;
import java.util.*;
import com.sun.tv.*;
import com.sun.tv.si.*;

public class Settings {
	public static final int REQ_GENERAL			= 0;
	public static final int REQ_SERVICE_COMPONENT		= 1;
	public static final int REQ_SERVICE_DESCRIPTION		= 2;
	public static final int REQ_CURRENT_PROGRAM_EVENT	= 3;
	public static final int REQ_FUTURE_PROGRAM_EVENT	= 4;
	public static final int REQ_FUTURE_PROGRAM_EVENTS	= 5;
	public static final int REQ_PROGRAM_EVENT		= 6;
	public static final int REQ_NEXT_PROGRAM_EVENT		= 7;
	public static final int REQ_TRANSPORT_STREAM		= 8;

	public static long REQUEST_DURATION = 15 * 1000; // 15 seconds

	public static String PreferredLanguage = "eng";

	public static String ReceiverRatingNames[] = new String[2];
	public static String ReceiverRatingLevels[] = new String[2];

	public static String AWTVideoSizeControlClassName =
			"com.sun.tv.media.AWTVideoSizeControlImpl";

	public static String MediaSelectControlClassName =
			"com.sun.tv.media.MediaSelectControlImpl";

	public static String RootContainerClassName =
			"java.awt.Frame";

	public static String SampleClass = "SampleData_01";
//	public static String SampleClass = "com.sun.tv.receiver.ReceiverFile";
	public static String SampleFile = "lib/JavaTVServiceFile.xml";
	public static int SampleInterval = 1000;

	public static String _defaultProperties = "lib/JavaTV.properties";

	public static int ContextServiceLimit = 50;

	static {
    //		SIManagerImpl.putFavoriteServices("SERV5", SIEmulator.toStrings("SERV5", ","));
		ReceiverRatingNames[0] = "MPAA";
		ReceiverRatingLevels[0] = "Parental Guidance under 13";

		ReceiverRatingNames[1] = "YAMPAA";
		ReceiverRatingLevels[1] = "Phoney desc for D";

		Load(_defaultProperties);
	}

	public static void Load(String filename) {
		try {
			Properties p = new Properties();

			FileInputStream file = new FileInputStream(filename);
			p.load(file);

			SampleClass = p.getProperty("ServiceFileHandler", SampleClass);

			SampleFile = p.getProperty("ServiceFile", SampleFile);

			RootContainerClassName = p.getProperty("RootContainerClassName", RootContainerClassName);

			REQUEST_DURATION = getProperty(p, "RequestDuration", REQUEST_DURATION);

			PreferredLanguage = p.getProperty("PreferredLanguage", PreferredLanguage);

			ContextServiceLimit = getProperty(p, "ContextServiceLimit", ContextServiceLimit);

			loadProperty(p, "FavoriteServicesNames");

			file.close();

			System.out.println("ServiceFile: " + SampleClass);
		} catch(IOException e) {
			System.err.println("Could not load properties from file " + filename);
			e.printStackTrace();
		}
	}

	private static long toLong(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
	
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			;
	        }
		return 0;
	}

	private static long getProperty(Properties p, String key, long defaultValue) {
		String str = p.getProperty(key);
		return (str == null) ? defaultValue : toLong(str);
	}

	private static int getProperty(Properties p, String key, int defaultValue) {
		String str = p.getProperty(key);
		return (str == null) ? defaultValue : (int)toLong(str);
	}

	private static void loadProperty(Properties p, String key) {
		String str = p.getProperty(key);
		if (str == null)
			return;

		String strs[] = SIEmulator.toStrings(str, ",");
		if (strs == null || strs.length == 0)
			return;
	
		for (int i = 0; i < strs.length; i++) {	
			String name = strs[i];
			String names = p.getProperty("List-" +name);
			if (names == null)
				continue;

    			SIManagerImpl.putFavoriteServices(name, SIEmulator.toStrings(names, ","));
		}
	}
}
