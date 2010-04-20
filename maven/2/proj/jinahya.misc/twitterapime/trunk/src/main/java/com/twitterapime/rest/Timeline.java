/*
 * Timeline.java
 * 11/04/2010
 * Twitter API Micro Edition
 * Copyright(c) Ernandes Mourao Junior (ernandes@gmail.com)
 * All rights reserved
 */
package com.twitterapime.rest;

import java.io.IOException;
import java.util.Hashtable;

import com.twitterapime.io.HttpConnection;
import com.twitterapime.io.HttpResponseCodeInterpreter;
import com.twitterapime.parser.Handler;
import com.twitterapime.parser.Parser;
import com.twitterapime.parser.ParserFactory;
import com.twitterapime.rest.handler.DirectMessageHandler;
import com.twitterapime.rest.handler.TimelineHandler;
import com.twitterapime.search.Query;
import com.twitterapime.search.QueryComposer;
import com.twitterapime.search.SearchDeviceListener;

/**
 * <p>
 * This class defines the methods responsible for accessing the main timelines
 * (i.e. public, home, user, mentions and DMs) provided by Twitter.
 * </p>
 * <p>
 * <pre>
 * Credential c = new Credential("username", "password");
 * UserAccountManager uam = UserAccountManager.getInstance(c)
 * Timeline ter = Timeline.getInstance(uam);
 * 
 * Query q = QueryComposer.count(5); //return only 5 latest tweets.
 * 
 * ter.startGetHomeTweets(q, new SearchDeviceListener() {
 *     public void searchCompleted() {}
 *     public void searchFailed(Throwable cause) {}
 *     public void tweetFound(Tweet tweet) {
 *         System.out.println(tweet);
 *     }
 * });
 * </pre>
 * </p>
 * 
 * @author Ernandes Mourao Junior (ernandes@gmail.com)
 * @version 1.0
 * @since 1.2
 * @see UserAccountManager
 * @see SearchDeviceListener
 * @see QueryComposer
 */
public final class Timeline {
	/**
	 * <p>
	 * Twitter REST API public timeline URI.
	 * </p>
	 */
	private static final String TWITTER_URL_PUBLIC_TIMELINE =
		"http://api.twitter.com/1/statuses/public_timeline.xml";
	
	/**
	 * <p>
	 * Twitter REST API home timeline URI.
	 * </p>
	 */
	private static final String TWITTER_URL_HOME_TIMELINE =
		"http://api.twitter.com/1/statuses/home_timeline.xml";

	/**
	 * <p>
	 * Twitter REST API user timeline URI.
	 * </p>
	 */
	private static final String TWITTER_URL_USER_TIMELINE =
		"http://api.twitter.com/1/statuses/user_timeline.xml";

	/**
	 * <p>
	 * Twitter REST API mentions URI.
	 * </p>
	 */
	private static final String TWITTER_URL_MENTIONS =
		"http://api.twitter.com/1/statuses/mentions.xml";
	
	/**
	 * <p>
	 * Twitter REST API received Direct Messages URI.
	 * </p>
	 */
	private static final String TWITTER_URL_RECEIVED_DIRECT_MESSAGE =
		"http://api.twitter.com/1/direct_messages.xml";
	
	/**
	 * <p>
	 * Twitter REST API sent Direct Messages URI.
	 * </p>
	 */
	private static final String TWITTER_URL_SENT_DIRECT_MESSAGE =
		"http://api.twitter.com/1/direct_messages/sent.xml";

	/**
	 * <p>
	 * Timeline pool used to cache instanced associated to user accounts.
	 * </p>
	 */
	private static Hashtable timelinePool;
	
	/**
	 * <p>
	 * Single instance of this class.
	 * </p>
	 */
	private static Timeline singleInstance;
	
	/**
	 * <p>
	 * Get an instance of Timeline class and associate it to a given user
	 * account.
	 * </p>
	 * @param uam User account manager.
	 * @return Timeline instance.
	 * @throws IllegalArgumentException If UserAccountManager is null.
	 * @throws SecurityException If UserAccountManager is not verified.
	 */
	public synchronized static Timeline getInstance(UserAccountManager uam) {
		if (uam == null) {
			throw new IllegalArgumentException(
				"UserAccountManager must not be null.");
		}
		//
		if (!uam.isVerified()) {
			throw new SecurityException("User's credential must be verified.");
		}
		//
		if (timelinePool == null) {
			timelinePool = new Hashtable();
		}
		//
		Timeline ter = (Timeline)timelinePool.get(uam);
		if (ter == null) {
			ter = new Timeline(uam);
			timelinePool.put(uam, ter);
		}
		//
		return ter;
	}

	/**
	 * <p>
	 * Get a single instance of Timeline class, which is NOT associated to any
	 * user account.
	 * </p>
	 * @return Timeline single instance.
	 */
	public synchronized static Timeline getInstance() {
		if (singleInstance == null) {
			singleInstance = new Timeline();
		}
		//
		return singleInstance;
	}
	
	/**
	 * <p>
	 * User account manager.
	 * </p>
	 */
	private UserAccountManager userAccountMngr;

	/**
	 * <p>
	 * Create an instance of Timeline class.
	 * </p>
	 * <p>
	 * Private constructor to avoid object instantiation.
	 * </p>
	 */
	private Timeline() {
	}
	
	/**
	 * <p>
	 * Create an instance of Timeline class.
	 * </p>
	 * <p>
	 * Private constructor to avoid object instantiation.
	 * </p>
	 * @param uam User account manager.
	 */
	private Timeline(UserAccountManager uam) {
		userAccountMngr = uam;
	}
	
	/**
	 * <p>
	 * Get most recent tweets from non-protected users who have set a custom
	 * user icon.
	 * </p>
	 * <p>
	 * This method does not wait for the search process is completed to return.
	 * To have access to this search's result, a SearchDeviceListener object
	 * must be registered. 
	 * </p>
	 * @param l Listener object to be notified about the search's result.
	 * @throws IllegalArgumentException If listener is null.
	 */
	public void startGetPublicTweets(SearchDeviceListener l) {
		TimelineHandler h = new TimelineHandler();
		h.setSearchDeviceListener(l);
		//
		startGet(TWITTER_URL_PUBLIC_TIMELINE, null, l, h, false);
	}
	
	/**
	 * <p>
	 * Get most recent tweets, including retweets, posted by the
	 * authenticating user and that user's friends according to given filter.
	 * This is the equivalent of timeline home on the Web.
	 * </p>
	 * <p>
	 * This method does not wait for the search process is completed to return.
	 * To have access to this search's result, a SearchDeviceListener object
	 * must be registered. 
	 * </p>
	 * <p>
	 * In order to create the query, only the following methods can be used as
	 * filters:
	 * <ul>
	 * <li>{@link QueryComposer#sinceID(String)}</li>
	 * <li>{@link QueryComposer#maxID(String)}</li>
	 * <li>{@link QueryComposer#count(int)}</li>
	 * <li>{@link QueryComposer#page(int)}</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>This method requires user authentication.</b>
	 * </p>
	 * @param q The filter query. If null all tweets are returned.
	 * @param l Listener object to be notified about the search's result.
	 * @throws IllegalArgumentException If listener is null.
	 */
	public void startGetHomeTweets(Query q, SearchDeviceListener l) {
		TimelineHandler h = new TimelineHandler();
		h.setSearchDeviceListener(l);
		//
		startGet(TWITTER_URL_HOME_TIMELINE, q, l, h, true);
	}
	
	/**
	 * <p>
	 * Get most recent tweets posted from the authenticating user
	 * and that user's friends according to given filter. This is the equivalent
	 * of the Web user page for your own user.
	 * </p>
	 * <p>
	 * This method does not wait for the search process is completed to return.
	 * To have access to this search's result, a SearchDeviceListener object
	 * must be registered. 
	 * </p>
	 * <p>
	 * In order to create the query, only the following methods can be used as
	 * filters:
	 * <ul>
	 * <li>{@link QueryComposer#sinceID(String)}</li>
	 * <li>{@link QueryComposer#maxID(String)}</li>
	 * <li>{@link QueryComposer#count(int)}</li>
	 * <li>{@link QueryComposer#page(int)}</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>This method requires user authentication.</b>
	 * </p>
	 * @param q The filter query. If null all tweets are returned.
	 * @param l Listener object to be notified about the search's result.
	 * @throws IllegalArgumentException If listener is null.
	 */
	public void startGetUserTweets(Query q, SearchDeviceListener l) {
		TimelineHandler h = new TimelineHandler();
		h.setSearchDeviceListener(l);
		//
		startGet(TWITTER_URL_USER_TIMELINE, q, l, h, true);
	}
	
	/**
	 * <p>
	 * Get most recent mentions (status containing @username) for the
	 * authenticating user according to given filter.
	 * </p>
	 * <p>
	 * This method does not wait for the search process is completed to return.
	 * To have access to this search's result, a SearchDeviceListener object
	 * must be registered. 
	 * </p>
	 * <p>
	 * In order to create the query, only the following methods can be used as
	 * filters:
	 * <ul>
	 * <li>{@link QueryComposer#sinceID(String)}</li>
	 * <li>{@link QueryComposer#maxID(String)}</li>
	 * <li>{@link QueryComposer#count(int)}</li>
	 * <li>{@link QueryComposer#page(int)}</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>This method requires user authentication.</b>
	 * </p>
	 * @param q The filter query. If null all tweets are returned.
	 * @param l Listener object to be notified about the search's result.
	 * @throws IllegalArgumentException If listener is null.
	 */
	public void startGetMentions(Query q, SearchDeviceListener l) {
		TimelineHandler h = new TimelineHandler();
		h.setSearchDeviceListener(l);
		//
		startGet(TWITTER_URL_MENTIONS, q, l, h, true);
	}
	
	/**
	 * <p>
	 * Get all Direct Messages from the authenticating user according to given
	 * filter.
	 * </p>
	 * <p>
	 * This method does not wait for the search process is completed to return.
	 * To have access to this search's result, a SearchDeviceListener object
	 * must be registered. 
	 * </p>
	 * <p>
	 * In order to create the query, only the following methods can be used as
	 * filters:
	 * <ul>
	 * <li>{@link QueryComposer#sinceID(String)}</li>
	 * <li>{@link QueryComposer#maxID(String)}</li>
	 * <li>{@link QueryComposer#count(int)}</li>
	 * <li>{@link QueryComposer#page(int)}</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>This methods requires user authentication.</b>
	 * </p>
	 * @param q The filter query. If null all DMs are returned.
	 * @param l Listener object to be notified about the search's result.
	 * @param received Return received DMs (true).
	 * @throws IllegalArgumentException If listener is null.
	 */
	public void startGetDirectMessages(Query q, boolean received, 
		SearchDeviceListener l) {
		final String url = 
			received
				? TWITTER_URL_RECEIVED_DIRECT_MESSAGE
				: TWITTER_URL_SENT_DIRECT_MESSAGE;
		DirectMessageHandler h = new DirectMessageHandler();
		h.setSearchDeviceListener(l);
		//
		startGet(url, q, l, h, true);
	}
		
	/**
	 * <p>
	 * Start a retrieval on a given URL according to a filter.
	 * </p>
	 * @param url The URL.
	 * @param q The filter query.
	 * @param l Listener object to be notified about the search's result.
	 * @param h Content handler.
	 * @param checkAuth Authentication required (true).
	 */
	private void startGet(final String url, final Query q,
		final SearchDeviceListener l, final Handler h, final boolean checkAuth){
		if (url == null || url.trim().length() == 0) {
			throw new IllegalArgumentException("Url must not be empty/null.");
		}
		if (l == null) {
			throw new IllegalArgumentException("Listener must not be null.");
		}
		if (h == null) {
			throw new IllegalArgumentException("Handler must not be null.");
		}
		//
		if (checkAuth) {
			checkUserAuth();
		}
		//
		Runnable r = new Runnable() {
			public void run() {
				HttpConnection conn = null;
				//
				try {
					conn = getConn(q != null ? url + '?' + q.toString() : url);
					HttpResponseCodeInterpreter.perform(conn);
					//
					Parser parser = ParserFactory.getDefaultParser();
					parser.parse(conn.openInputStream(), h);
					//
					l.searchCompleted();
				} catch (Exception e) {
					l.searchFailed(e);
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (IOException e) {}
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
	 * Check if the user's is authenticated.
	 * </p>
	 * @throws SecurityException User is not authenticated.
	 */
	private void checkUserAuth() {
		if (userAccountMngr == null || !userAccountMngr.isVerified()) {
			throw new SecurityException(
			    "User's credential must be entered to perform this operation.");
		}
	}
	
	/**
	 * <p>
	 * Get HTTP connection for the given URL.
	 * </p>
	 * @param url URL.
	 * @return Connection.
	 * @throws IOException If an I/O error occurs.
	 */
	private HttpConnection getConn(String url) throws IOException {
		if (userAccountMngr != null) {
			return UserAccountManager.getHttpConn(
				url, userAccountMngr.getCredential());
		} else {
			return UserAccountManager.getHttpConn(url, null);
		}
	}
}
