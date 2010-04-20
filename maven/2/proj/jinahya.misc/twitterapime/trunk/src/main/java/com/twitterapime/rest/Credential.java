/*
 * Credential.java
 * 11/11/2009
 * Twitter API Micro Edition
 * Copyright(c) Ernandes Mourao Junior (ernandes@gmail.com)
 * All rights reserved
 */
package com.twitterapime.rest;

import java.util.Hashtable;

import com.twitterapime.model.DefaultEntity;
import com.twitterapime.model.MetadataSet;

/**
 * <p>
 * This class defines an entity that represents a user's credential.
 * </p>
 * 
 * @author Ernandes Mourao Junior (ernandes@gmail.com)
 * @version 1.0
 * @since 1.1
 * @see UserAccountManager
 */
public final class Credential extends DefaultEntity {
	/**
	 * <p>
	 * Create an instance of Credential class.
	 * </p>
	 * @param username Username.
	 * @param password Password.
	 * @throws IllegalArgumentException If username/password is empty or null.
	 */
	public Credential(String username, String password) {
		if (username == null || (username = username.trim()).length() == 0) {
			throw new IllegalArgumentException(
				"Username must not be empty/null");
		}
		if (password == null || (password = password.trim()).length() == 0) {
			throw new IllegalArgumentException(
				"Password must not be empty/null");
		}
		//
		Hashtable credtls = new Hashtable(2);
		credtls.put(MetadataSet.CREDENTIAL_USERNAME, username);
		credtls.put(MetadataSet.CREDENTIAL_PASSWORD, password);
		setData(credtls);
	}
	
	/**
	 * <p>
	 * Get the credential in HttpAuth format (e.g. username:password).
	 * </p>
	 * @return Credential.
	 */
	String getBasicHttpAuthCredential() {
		return getString(MetadataSet.CREDENTIAL_USERNAME) +
		       ':' +
		       getString(MetadataSet.CREDENTIAL_PASSWORD);
	}
}