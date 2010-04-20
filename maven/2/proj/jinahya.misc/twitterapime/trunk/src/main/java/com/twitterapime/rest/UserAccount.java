/*
 * UserAccount.java
 * 11/11/2009
 * Twitter API Micro Edition
 * Copyright(c) Ernandes Mourao Junior (ernandes@gmail.com)
 * All rights reserved
 */
package com.twitterapime.rest;

import java.util.Hashtable;

import com.twitterapime.model.DefaultEntity;
import com.twitterapime.model.MetadataSet;
import com.twitterapime.search.Tweet;

/**
 * <p>
 * This class defines an entity that represents a user's account.
 * </p>
 * 
 * @author Ernandes Mourao Junior (ernandes@gmail.com)
 * @version 1.0
 * @since 1.1
 * @see UserAccountManager
 */
public final class UserAccount extends DefaultEntity {
	/**
	 * <p>
	 * Create an instance of UserAccount class.
	 * </p>
	 */
	public UserAccount() {
	}

	/**
	 * <p>
	 * Create an instance of UserAccount class.
	 * </p>
	 * @param data The initial attributes/values.
	 */
	public UserAccount(Hashtable data) {
		super(data);
	}
	
	/**
	 * <p>
	 * Create an instance of UserAccount class.<br/>
	 * This constructor is used when the UserAccount object is going to be
	 * used for Follow or Unfollow operations on {@link UserAccountManager}.
	 * </p>
	 * @param userNameOrID Username or ID.
	 * @throws IllegalArgumentException If userNameOrID is empty/null.
	 */
	public UserAccount(String userNameOrID) {
		if (userNameOrID == null
				|| (userNameOrID = userNameOrID.trim()).length() == 0) {
			throw new IllegalArgumentException(
				"Username/ID must not be empty/null.");
		}
		//
		Hashtable data = new Hashtable();
		data.put(MetadataSet.USERACCOUNT_ID, userNameOrID);
		data.put(MetadataSet.USERACCOUNT_USER_NAME, userNameOrID);
		setData(data);
	}
	
	/**
	 * <p>
	 * Get the last tweet posted by the user.
	 * </p>
	 * @return Tweet.
	 */
	public Tweet getLastTweet() {
		return (Tweet)getObject(MetadataSet.USERACCOUNT_LAST_TWEET);
	}
}