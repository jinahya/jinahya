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


import java.io.Serializable;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Access(AccessType.FIELD)
@Entity
@Table(name = User.TABLE_NAME)
@XmlRootElement
@XmlType(propOrder = {"name", "password", "roles"})
public class User implements Serializable {


    /**
     * GENERATED.
     */
    private static final long serialVersionUID = -7188812932037976918L;


    /**
     * table name.
     */
    static final String TABLE_NAME = "REALM_USER";


    /**
     * id column name.
     */
    static final String ID_COLUMN_NAME = "ID";


    /**
     * id generator name.
     */
    static final String ID_GENERATOR_NAME = ID_COLUMN_NAME + "_GENERATOR";


    /**
     * digest algorithm for password hashing.
     */
    public static final String PASSWORD_HASH_ALGORITHM = "SHA-512";


    /**
     * output size.
     */
    private static final int PASSWORD_HASH_SIZE = 64; // in bytes; 512 bits


    /**
     * Charset name for password encoding.
     */
    public static final String PASSWORD_CHARSET_NAME = "UTF-8";


    /**
     * Charset for password encoding.
     */
    public static final Charset PASWORD_CHARSET =
        Charset.forName(PASSWORD_CHARSET_NAME);


    /**
     * Returns hashed password.
     *
     * @param plain the password in plain text
     * @return hashed password
     * @throws NoSuchAlgorithmException if {@value #PASSWORD_DIGEST_ALGORITHM}
     * is not recognized.
     */
    public static String hash(final String plain)
        throws NoSuchAlgorithmException {

        if (plain == null) {
            throw new NullPointerException("null passwordInPlainText");
        }

        return hex(digest(plain.getBytes(PASWORD_CHARSET)));
    }


    public static String hex(final byte[] bytes) {

        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }

        final StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            if (b >= 0 && b < 0x10) {
                builder.append('0');
            }
            builder.append(Integer.toHexString(b & 0xFF));
        }

        return builder.toString();
    }


    public static byte[] digest(final byte[] bytes)
        throws NoSuchAlgorithmException {

        if (bytes == null) {
            throw new NullPointerException("null bytes");
        }

        final MessageDigest digest =
            MessageDigest.getInstance(PASSWORD_HASH_ALGORITHM);

        return digest.digest(bytes);
    }


    /**
     * Returns name.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }


    /**
     * Sets name.
     *
     * @param name name.
     */
    public void setName(String name) {

        if (name == null) {
            throw new NullPointerException("null name");
        }

        name = name.trim();

        if (name.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }
        
        this.name = name;
    }


    /**
     * Returns password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets password.
     *
     * @param password password
     */
    public void setPassword(final String password) {

        if (password == null) {
            throw new NullPointerException("null password");
        }

        if (password.length() != PASSWORD_HASH_SIZE * 2) {
            throw new IllegalArgumentException(
                "password.length != " + (PASSWORD_HASH_SIZE * 2));
        }

        for (int i = 0; i < password.length(); i += 2) {
            try {
                Integer.parseInt(password.substring(i * 2, (i + 1) * 2), 16);
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("illegal password");
            }
        }

        this.password = password;
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

        setPassword(hash(passwordInPlainText));
    }


    /**
     * Returns a collection of Roles mapped to this User.
     *
     * @return roles.
     */
    public Collection<Role> getRoles() {

        if (roles == null) {
            roles = new ArrayList<Role>();
        }

        return roles;
    }


    /**
     * id.
     */
    @Id
    @Column(name = ID_COLUMN_NAME)
    @TableGenerator(name = ID_GENERATOR_NAME,
                    table = GeneratedId.TABLE_NAME,
                    pkColumnName = GeneratedId.PK_COLUMN_NAME,
                    valueColumnName = GeneratedId.VALUE_COLUMN_NAME,
                    pkColumnValue = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.TABLE,
                    generator = ID_GENERATOR_NAME)
    @XmlAttribute
    private Long id;


    /**
     * name.
     */
    @Basic(optional = false)
    @Column(name = "USER_NAME", nullable = false, unique = true)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String name;


    /**
     * password.
     */
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, unique = false)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String password;


    /**
     * roles.
     */
    @ManyToMany
    @JoinTable(name = UserRole.TABLE_NAME,
               joinColumns = {
        @JoinColumn(name = User.ID_COLUMN_NAME)},
               inverseJoinColumns = {
        @JoinColumn(name = Role.ID_COLUMN_NAME)})
    @XmlElement(name = "role")
    @XmlElementWrapper(required = true)
    private Collection<Role> roles;


}

