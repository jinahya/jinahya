/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.jdbc.realm.persistence;


import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"userName", "password"})
public class User {


    private static final String PASSWORD_DIGEST_ALGORITHM = "SHA-512";


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {

        if (userName == null) {
            throw new NullPointerException("null userName");
        }

        userName = userName.trim();

        if (userName.isEmpty()) {
            throw new IllegalArgumentException("empty userName");
        }

        this.userName = userName;
    }


    public boolean getEnabled() {
        return enabled;
    }


    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {

        if (password == null) {
            throw new NullPointerException("null password");
        }

        password = password.trim();

        if (password.isEmpty()) {
            throw new IllegalArgumentException("empty password");
        }

        this.password = password;
    }


    public void setPasswordWithPlainText(String password)
        throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if (password == null) {
            throw new NullPointerException("null password");
        }

        password = password.trim();

        if (password.isEmpty()) {
            throw new IllegalArgumentException("empty password");
        }

        setPassword(password.getBytes("UTF-8"));
    }


    public void setPassword(final byte[] password)
        throws NoSuchAlgorithmException {

        if (password == null) {
            throw new NullPointerException("null password");
        }

        if (password.length == 0) {
            throw new IllegalArgumentException("empty password");
        }

        final MessageDigest digest =
            MessageDigest.getInstance(PASSWORD_DIGEST_ALGORITHM);

        final byte[] digested = digest.digest(password);

        final StringBuilder builder = new StringBuilder(digested.length * 2);
        for (byte b : digested) {
            if (b < 0x10) {
                builder.append('0');
            }
            builder.append(Integer.toHexString(b & 0xFF));
        }

        setPassword(builder.toString());
    }


    @Id
    @Basic(optional = false)
    @Column(name = "USER_NAME", nullable = false, unique = true)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String userName;


    @Basic(optional = false)
    @Column(name = "ENABLED", nullable = false, unique = false)
    @XmlAttribute(required = true)
    private boolean enabled;


    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, unique = false)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String password;


}

