/*
 * HttpConnector.java
 * 16/08/2009
 * Twitter API Micro Edition
 * Copyright(c) Ernandes Mourao Junior (ernandes@gmail.com)
 * All rights reserved
 */
package com.twitterapime.io;

import java.io.IOException;

import com.twitterapime.platform.PlatformProvider;
import com.twitterapime.platform.PlatformProviderSelector;

/**
 * <p>
 * This is factory class for creating new HttpConnection objects.
 * </p>
 * <p>
 * The creation of HttpConnection is performed dynamically by looking up the
 * current platform provider from PlatformProviderSelector class. For each
 * supported platform, there is a specific implementation class.
 * </p>
 * <p>
 * The parameter string that describes the target should conform to the Http URL
 * format as described in RFC 1738.
 * </p>
 * 
 * @author Ernandes Mourao Junior (ernandes@gmail.com)
 * @version 1.1
 * @since 1.0
 * @see HttpConnection
 */
public final class HttpConnector {
	/**
	 * <p>
	 * Create and open a HttpConnection.
	 * </p>
	 * 
	 * @param url The URL for the connection.
	 * @return A new HttpConnection object.
	 * @throws IOException If an I/O error occurs.
	 * @throws IllegalArgumentException If url is null or empty.
	 */
	public static HttpConnection open(String url) throws IOException {
		if (url == null || url.trim().length() == 0) {
			throw new IllegalArgumentException("URL must not be null/empty.");
		}
		//
		final String JAVA_ME_HTTP_IMPL_CLASS =
			"impl.javame.com.twitterapime.io.HttpConnectionImpl";
		final String JAVA_ME_HTTP_USER_AGENT =
			"Twitter API ME/1.1 (compatible; Java ME; MIDP-2.0; CLDC-1.0)";
		final String ANDROID_HTTP_IMPL_CLASS =
			"impl.android.com.twitterapime.io.HttpConnectionImpl";
		final String ANDROID_HTTP_USER_AGENT =
			"Twitter API ME/1.1 (compatible; Android 1.1)";
		//
		final long PPID = PlatformProviderSelector.getCurrentProvider().getID();
		//
		HttpConnection conn = null;
		String userAgent = null;
		//
		if (PPID == PlatformProvider.PPID_JAVA_ME) {
			conn = newInstance(JAVA_ME_HTTP_IMPL_CLASS);
			userAgent = JAVA_ME_HTTP_USER_AGENT;
		} else if (PPID == PlatformProvider.PPID_ANDROID) {
			conn = newInstance(ANDROID_HTTP_IMPL_CLASS);
			userAgent = ANDROID_HTTP_USER_AGENT;
		} else {
			throw new IllegalArgumentException("Unknown platform ID: " + PPID);
		}
		//
		// #ifdef DEBUG
		System.out.println(url);
		// #endif
		conn.open(url);
		conn.setRequestProperty("User-Agent", userAgent);
		//
		return conn;
	}

	/**
	 * <p>
	 * Encode the given URL.
	 * </p>
	 * 
	 * @param url The URL to be encoded.
	 * @return An encoded URL.
	 * @throws IllegalArgumentException If url is null or empty.
	 */
	public static String encodeURL(String url) {
		if (url == null) {
			throw new IllegalArgumentException("URL must not be null.");
		}
		//
		StringBuffer eURL = new StringBuffer(url.length());
		char[] urlChars = url.toCharArray();
		//
		for (int i = 0; i < urlChars.length; i++) {
			switch (urlChars[i]) {
			case '!':
				eURL.append("%21");
				break;
			case '*':
				eURL.append("%2A");
				break;
			case '"':
				eURL.append("%22");
				break;
			case '\'':
				eURL.append("%27");
				break;
			case '(':
				eURL.append("%28");
				break;
			case ')':
				eURL.append("%29");
				break;
			case ';':
				eURL.append("%3B");
				break;
			// case ':':
			// eURL.append("%3A");
			// break;
			case '@':
				eURL.append("%40");
				break;
			// case '&':
			// eURL.append("%26");
			// break;
			// case '=':
			// eURL.append("%3D");
			// break;
			case '+':
				eURL.append("%2B");
				break;
			case '$':
				eURL.append("%24");
				break;
			case ',':
				eURL.append("%2C");
				break;
			// case '/':
			// eURL.append("%2F");
			// break;
			// case '?':
			// eURL.append("%3F");
			// break;
			case '%':
				eURL.append("%25");
				break;
			case '#':
				eURL.append("%23");
				break;
			case '[':
				eURL.append("%5B");
				break;
			case ']':
				eURL.append("%5D");
				break;
			case ' ':
				eURL.append("%20");
				break;
			default:
				eURL.append(urlChars[i]);
				break;
			}
		}
		//
		return eURL.toString();
	}

	/**
	 * <p>
	 * Encode a given string to Base 64.
	 * </p>
	 * <p>
	 * <i>This implementation is adopted from Kenneth Ballard's HttpClient
	 * package. Released under LGPL.</i>
	 * </p>
	 * @param str String to encode.
	 * @return Base64 encoded string.
	 * @throws IllegalArgumentException If url is null or empty.
	 */
	public static String encodeBase64(String str) {
		if (str == null) {
			throw new IllegalArgumentException("Str must not be null.");
		}
		//
		final String c =
			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		//
		byte[] code = c.getBytes();
		byte[] s = str.getBytes();
		int x;
		int y = str.length() - (str.length() % 3);
		byte[] coded = new byte[4];
		StringBuffer dest = new StringBuffer();
		//
		for (x = 0; x < y; x += 3) {
			coded[3] = code[s[x + 2] % 64];
			coded[0] = code[s[x] >> 2];
			coded[1] = new Integer((s[x] % 4) << 4).byteValue();
			coded[1] += s[x + 1] >> 4;
			coded[1] = code[coded[1]];
			coded[2] = new Integer((s[x + 1] % 16) << 2).byteValue();
			coded[2] += s[x + 2] / 64;
			coded[2] = code[coded[2]];
			//
			dest.append(new String(coded));
		}
		//
		x = y;
		//
		if (s.length % 3 == 0) {
			return dest.toString();
		}
		if (s.length % 3 == 1) {
			coded[2] = '=';
			coded[3] = '=';
			coded[0] = code[s[x] >> 2];
			coded[1] = code[new Integer((s[x] % 4) << 4).byteValue()];
			//
			dest.append(new String(coded));
		}
		if (s.length % 3 == 2) {
			coded[3] = '=';
			coded[0] = code[s[x] >> 2];
			coded[1] = new Integer((s[x] % 4) << 4).byteValue();
			coded[1] += s[x + 1] >> 4;
			coded[1] = code[coded[1]];
			coded[2] = code[new Integer((s[x + 1] % 16) << 2).byteValue()];
			//
			dest.append(new String(coded));
		}
		//
		return dest.toString();
	}
	
	/**
	 * <p>
	 * Create a new instance of a HttpConnection subclass.
	 * </p>
	 * @param className Subclass's full name.
	 * @return New HttpConnection instance.
	 */
	private static HttpConnection newInstance(String className) {
		try {
			return (HttpConnection)Class.forName(className).newInstance();
		} catch (IllegalAccessException e) {
		} catch (InstantiationException e) {
		} catch (ClassNotFoundException e) {
		}
		//
		return null;
	}

	/**
	 * <p>
	 * Private constructor to avoid object instantiation.
	 * </p>
	 */
	private HttpConnector() {
	}
}