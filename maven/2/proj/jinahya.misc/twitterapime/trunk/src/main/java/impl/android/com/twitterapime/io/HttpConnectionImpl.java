/*
 * HttpConnectionImpl.java
 * 14/10/2009
 * Twitter API Micro Edition
 * Copyright(c) Ernandes Mourao Junior (ernandes@gmail.com)
 * All rights reserved
 */
package impl.android.com.twitterapime.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.twitterapime.io.HttpConnection;

/**
 * <p>
 * This class defines the implementation of HttpConnection for Android platform.
 * </p>
 * 
 * @author Ernandes Mourao Junior (ernandes@gmail.com)
 * @version 1.1
 * @since 1.0
 */
public final class HttpConnectionImpl implements HttpConnection {
	/**
	 * <p>
	 * Http connection object.
	 * </p>
	 */
	private HttpURLConnection conn;

	/**
	 * @see com.twitterapime.io.HttpConnection#close()
	 */
	public void close() throws IOException {
		conn.disconnect();
	}

	/**
	 * @see com.twitterapime.io.HttpConnection#getHeaderField(java.lang.String)
	 */
	public String getHeaderField(String name) throws IOException {
		return conn.getHeaderField(name);
	}

	/**
	 * @see com.twitterapime.io.HttpConnection#getResponseCode()
	 */
	public int getResponseCode() throws IOException {
		return conn.getResponseCode();
	}

	/**
	 * @see com.twitterapime.io.HttpConnection#open(java.lang.String)
	 */
	public void open(String url) throws IOException {
		conn = (HttpURLConnection)new URL(url).openConnection();
	}

	/**
	 * @see com.twitterapime.io.HttpConnection#openInputStream()
	 */
	public InputStream openInputStream() throws IOException {
		if (!conn.getDoInput()) {
			conn.setDoInput(true);
		}
		//
		if (conn.getResponseCode() == HTTP_OK) {
			return conn.getInputStream();
		} else {
			return conn.getErrorStream();
		}
	}

	/**
	 * @see com.twitterapime.io.HttpConnection#openOutputStream()
	 */
	public OutputStream openOutputStream() throws IOException {
		if (!conn.getDoOutput()) {
			conn.setDoOutput(true);
		}
		//
		return conn.getOutputStream();
	}

	/**
	 * @see com.twitterapime.io.HttpConnection#setRequestMethod(java.lang.String)
	 */
	public void setRequestMethod(String method) throws IOException {
		conn.setRequestMethod(method);
	}

	/**
	 * @see com.twitterapime.io.HttpConnection#setRequestProperty(java.lang.String, java.lang.String)
	 */
	public void setRequestProperty(String key, String value) throws IOException{
		conn.setRequestProperty(key, value);
	}
	
	/**
	 * @see com.twitterapime.io.HttpConnection#getRequestProperty(java.lang.String)
	 */
	public String getRequestProperty(String key) throws IOException {
		return conn.getRequestProperty(key);
	}
}