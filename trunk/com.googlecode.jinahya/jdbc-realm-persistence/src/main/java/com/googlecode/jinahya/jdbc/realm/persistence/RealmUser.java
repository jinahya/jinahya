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


import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Access(AccessType.FIELD)
@Entity
@Table(name = RealmUser.TABLE_NAME)
@XmlType(propOrder = {"userName", "roles"})
public class RealmUser {


    /**
     * The table name.
     */
    public static final String TABLE_NAME = "REALM_USER";


    /**
     * The USER_NAME column name. This is the ID Column name.
     */
    public static final String USER_NAME_COLUMN_NAME = "USER_NAME";


    /**
     * The digest algorithm for password hashing.
     */
    public static final String PASSWORD_DIGEST_ALGORITHM = "SHA-512";


    /**
     * The charset name for password encoding.
     */
    public static final String PASSWORD_CHARSET_NAME = "UTF-8";


    /**
     * The charset for password encoding.
     */
    public static final Charset PASWORD_CHARSET =
        Charset.forName(PASSWORD_CHARSET_NAME);


    /**
     * Returns hashed password.
     *
     * @param passwordInPlainText the password in plain text
     * @return hashed password
     * @throws NoSuchAlgorithmException if {@value #PASSWORD_DIGEST_ALGORITHM}
     * is not recognized.
     */
    public static String hashPassword(final String passwordInPlainText)
        throws NoSuchAlgorithmException {

        if (passwordInPlainText == null) {
            throw new NullPointerException("null passwordInPlainText");
        }

        final MessageDigest digest =
            MessageDigest.getInstance(PASSWORD_DIGEST_ALGORITHM);

        final byte[] digested =
            digest.digest(passwordInPlainText.getBytes(PASWORD_CHARSET));

        final StringBuilder builder = new StringBuilder(digested.length * 2);
        for (byte b : digested) {
            final int i = b & 0xFF;
            if (i < 0x10) {
                builder.append('0');
            }
            builder.append(Integer.toHexString(i));
        }

        return builder.toString();
    }


    /**
     * Returns userName.
     *
     * @return userName.
     */
    public String getUserName() {
        return userName;
    }


    /**
     * Sets userName.
     *
     * @param userName userName
     */
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


    /**
     * Sets password with plain text.
     *
     * @param passwordInPlainText password in plain text.
     * @throws NoSuchAlgorithmException if {@value #PASSWORD_DIGEST_ALGORITHM}
     * is not recognized
     * @see #hashPassword(String)
     */
    public void setPasswordWithPlainText(final String passwordInPlainText)
        throws NoSuchAlgorithmException {

        if (passwordInPlainText == null) {
            throw new NullPointerException("null passwordInPlainText");
        }

        this.password = hashPassword(passwordInPlainText);
    }


    /**
     * Returns enabled.
     *
     * @return enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }


    /**
     * Sets enabled.
     *
     * @param enabled enabled
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }


    /**
     * Returns a collection of Roles mapped to this User.
     *
     * @return roles.
     */
    public Collection<RealmRole> getRoles() {

        if (roles == null) {
            roles = new ArrayList<RealmRole>();
        }

        return roles;
    }


    @Id
    @Basic(optional = false)
    @Column(name = USER_NAME_COLUMN_NAME, nullable = false, unique = true)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String userName;


    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, unique = false)
    //@XmlElement(required = true, nillable = false)
    //@XmlSchemaType(name = "token")
    @XmlTransient
    private String password;


    @Basic(optional = false)
    @Column(name = "IS_ENABLED", nullable = false, unique = false)
    @XmlAttribute(required = true)
    private boolean enabled;


    @JoinTable(name = RealmUser.TABLE_NAME + "_" + RealmRole.TABLE_NAME,
               joinColumns = {
        @JoinColumn(name = RealmUser.USER_NAME_COLUMN_NAME)},
               inverseJoinColumns = {
        @JoinColumn(name = RealmService.SERVICE_NAME_COLUMN_NAME),
        @JoinColumn(name = RealmRole.ROLE_NAME_COLUMN_NAME)})
    @ManyToMany
    @XmlElement(name = "role")
    @XmlElementWrapper(required = true)
    private Collection<RealmRole> roles;


}

