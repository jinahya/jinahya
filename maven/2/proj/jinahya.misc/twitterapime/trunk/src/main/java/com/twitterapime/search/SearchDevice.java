/*
 * SearchDevice.java
 * 23/09/2009
 * Twitter API Micro Edition
 * Copyright(c) Ernandes Mourao Junior (ernandes@gmail.com)
 * All rights reserved
 */
package com.twitterapime.search;

import java.io.IOException;

import com.twitterapime.io.HttpConnection;
import com.twitterapime.io.HttpConnector;
import com.twitterapime.io.HttpResponseCodeInterpreter;
import com.twitterapime.parser.Parser;
import com.twitterapime.parser.ParserException;
import com.twitterapime.parser.ParserFactory;
import com.twitterapime.search.handler.SearchResultHandler;

/**
 * <p>
 * This class is the entry point of Search API, which defines the methods
 * responsible for submitting a query to Twitter Search API.
 * </p>
 * <p>
 * <pre>
 * SearchDevice sd = SearchDevice.getInstance();
 * Query q1 = QueryComposer.from("twitteruser");
 * Query q2 = QueryComposer.containAny("search api");
 * Query q = QueryComposer.append(q1, q2);
 * Tweet[] ts = sd.searchTweets(q);
 * for (int i = 0; i < ts.length; i++) {
 *   list.append(ts[i].getString(MetadataSet.TWEET_CONTENT), null);
 * }
 * </pre>
 * </p>
 * 
 * @author Ernandes Mourao Junior (ernandes@gmail.com)
 * @version 1.1
 * @since 1.0
 * @see SearchDeviceListener
 * @see QueryComposer
 */
public final class SearchDevice {
	/**
	 * <p>
	 * Twitter Search API URI.
	 * </p>
	 */
	private static final String TWITTER_URL_ATOM =
		"http://search.twitter.com/search.atom";

	/**
	 * <p>
	 * Query prefix.
	 * </p>
	 */
	private static final String TWITTER_QUERY_STRING_PREFIX = "q=";

	/**
	 * <p>
	 * Single instance of this class.
	 * </p>
	 */
	private static SearchDevice device;
	
	/**
	 * <p>
	 * Time at which Twitter Search API was access by this class.
	 * </p>
	 */
	private static long lastCallTime;
	
	/**
	 * <p>
	 * Number of calls to Twitter Search API since this class was loaded.
	 * </p>
	 */
	private static int apiCallsCount;

	/**
	 * <p>
	 * Get an instance of SearchDevice class.
	 * </p>
	 * @return A SearchDevice object.
	 */
	public synchronized static SearchDevice getInstance() {
		if (device == null) {
			device = new SearchDevice();
		}
		//
		return device;
	}
	
	/**
	 * <p>
	 * Private constructor to avoid object instantiation.
	 * </p>
	 */
	private SearchDevice() {
	}

	/**
	 * <p>
	 * Search for tweets that match the given query. This method gets blocked
	 * until the search is completed or an exception is thrown.
	 * </p>
	 * @param query The query.
	 * @return The result.
	 * @throws IOException If an I/O error occurs.
	 * @throws LimitExceededException If the limit of access is exceeded.
	 * @throws IllegalArgumentException If query is null.
	 */
	public Tweet[] searchTweets(Query query) throws IOException,
		LimitExceededException {
		if (query == null) {
			throw new IllegalArgumentException("Query must not be null.");
		}
		//
		return searchTweets(query, null);
	}

	/**
	 * <p>
	 * Search for tweets that match the given query string. This method gets
	 * blocked until the search is completed or an exception is thrown.
	 * </p>
	 * @param queryString The query string.
	 * @return The result.
	 * @throws IOException If an I/O error occurs.
	 * @throws LimitExceededException If the limit of access is exceeded.
	 * @throws IllegalArgumentException If queryString is null/empty.
	 */
	public Tweet[] searchTweets(String queryString) throws IOException,
		LimitExceededException {
		if (queryString == null
				|| (queryString = queryString.trim()).length() == 0) {
			throw new IllegalArgumentException(
				"QueryString must not be null/empty.");
		}
		//
		return searchTweets(new Query(queryString), null);
	}
	
	/**
	 * <p>
	 * Search for tweets that match the given query. This method does not wait
	 * for the search process is completed to return. To have access to this
	 * search's result, a SearchDeviceListener object must be registered. 
	 * </p>
	 * @param query The query.
	 * @param listener Listener object to be notified about the search's result.
	 * @throws IllegalArgumentException If query is null.
	 */
	public void startSearchTweets(Query query, SearchDeviceListener listener) {
		if (query == null) {
			throw new IllegalArgumentException("Query must not be null.");
		}
		//
		startSearchTweets(query.toString(), listener);
	}

	/**
	 * <p>
	 * Search for tweets that match the given query string. This method does not
	 * wait for the search process is completed to return. To have access to
	 * this search's result, a SearchDeviceListener object must be registered. 
	 * </p>
	 * @param queryString The query string.
	 * @param listener Listener object to be notified about the search's result.
	 * @throws IllegalArgumentException If queryString is null/empty.
	 */
	public void startSearchTweets(final String queryString,
		final SearchDeviceListener listener) {
		if (queryString == null || queryString.length() == 0) {
			throw new IllegalArgumentException(
				"QueryString must not be null/empty.");
		}
		//
		Runnable r = new Runnable() {
			public void run() {
				try {
					searchTweets(new Query(queryString), listener);
					if (listener != null) {
						listener.searchCompleted();
					}
				} catch (Exception e) {
					if (listener != null) {
						listener.searchFailed(e);
					}
				}
			}
		};
		//
		Thread t = new Thread(r);
		t.start();
	}

	/**
	 * <p>
	 * Get the call count submitted to Twitter Search API.
	 * </p>
	 * @return Call count.
	 */
	public int getAPICallsCount() {
		return apiCallsCount;
	}

	/**
	 * <p>
	 * Get the time at which the last call was submitted to Twitter Search API.
	 * </p>
	 * @return Time of last call.
	 */
	public long getLastAPICallTime() {
		return lastCallTime;
	}
	
	/**
	 * <p>
	 * Search for tweets that match the given query. If the listener parameter
	 * is not passed, this method gets blocked until the search is completed or
	 * an exception is thrown. Otherwise, the result is returned through the
	 * listener.
	 * </p>
	 * @param queryString The query string.
	 * @param l The listener object.
	 * @return The result.
	 * @throws IOException If an I/O error occurs.
	 * @throws LimitExceededException If the limit of access is exceeded.
	 */
	private Tweet[] searchTweets(Query query, final SearchDeviceListener l)
		throws IOException, LimitExceededException {
		updateAPIInfo();
		//
		HttpConnection conn = getHttpConn(query.toString());
		Parser parser = ParserFactory.getDefaultParser();
		SearchResultHandler handler = new SearchResultHandler();
		handler.setSearchDeviceListener(l);
		//
		try {
			parser.parse(conn.openInputStream(), handler);
			//
			return handler.getParsedTweets();
		} catch (ParserException e) {
			throw new IOException(e.getMessage());
		} finally {
			conn.close();
		}
	}
	
	/**
	 * <p>
	 * Get a Http connection to the given query string.
	 * </p>
	 * @param queryStr The query string.
	 * @return The Http connection object.
	 * @throws IOException If an I/O error occurs.
	 * @throws LimitExceededException If the limit of access is exceeded.
	 */
	private HttpConnection getHttpConn(String queryStr) throws IOException,
		LimitExceededException {
		if (queryStr == null || (queryStr = queryStr.trim()).length() == 0) {
			throw new IllegalArgumentException(
				"Query String must not be empty/null.");
		}
		//
		if (!queryStr.startsWith("?")) {
			if (!queryStr.startsWith(TWITTER_QUERY_STRING_PREFIX)) {
				queryStr = '?' + TWITTER_QUERY_STRING_PREFIX + queryStr;
			} else {
				queryStr = '?' + queryStr;
			}
		}
		//
		final String url = TWITTER_URL_ATOM + HttpConnector.encodeURL(queryStr);
		HttpConnection c = HttpConnector.open(url);
		boolean hasException = true;
		//
		try {
			c.setRequestMethod(HttpConnection.GET);
			//verify whether there is an error in the request.
			HttpResponseCodeInterpreter.perform(c);
			hasException = false;
		} finally {
			if (hasException) {
				c.close();
			}
		}
		//
		return c;
	}
	
	/**
	 * <p>
	 * Update some internal information regarding the API.
	 * </p>
	 */
	private void updateAPIInfo() {
		lastCallTime = System.currentTimeMillis();
		apiCallsCount++;
	}
}