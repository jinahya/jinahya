/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.persistence;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Shadow.NQ_FIND_BY_USERNAME,
                query = "SELECT s FROM Shadow AS s"
                        + " WHERE s.username = :username")
})
@Table(name = "SHADOW")
public class Shadow implements Serializable {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(Shadow.class.getName());


    static {
        LOGGER.setLevel(Level.OFF);
    }


    public static final String NQ_FIND_BY_USERNAME =
        "Shadow.NQ_FIND_BY_USERNAME";


    public static final int USERNAME_SIZE_MIN = 1;


    public static final int USERNAME_SIZE_MAX = 255;


    public static Shadow newInstance(final String username,
                                     final byte[] password) {

        Objects.requireNonNull(username, "null username");

        Objects.requireNonNull(password, "null password");

        if (password.length == 0) {
            throw new IllegalArgumentException("empty password");
        }

        final Shadow instance = new Shadow();

        instance.username = username;
        instance.passsalt = new Morton();
        instance.passcode = instance.passsalt.salty(password);

        return instance;
    }


    /**
     * Creates a new instance.
     */
    protected Shadow() {
        super();
    }


    // --------------------------------------------------------------- Et cetera
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + hashCode()
               + "?id=" + id
               + "&username=" + username
               + "&passcode=" + DatatypeConverter.printHexBinary(passcode);
    }


    // ---------------------------------------------------------------------- ID
    @XmlAttribute
    public Long getId() {
        return id;
    }


    public boolean nassword(final Shadow reference, final byte[] password,
                            final byte[] nassword) {

        LOGGER.log(Level.INFO, "nassword({0}, {1}, {2})",
                   new Object[]{reference, password, nassword});

        if (reference == null) {
            throw new IllegalArgumentException("null reference");
        }

        if (this != reference) {
            throw new IllegalArgumentException("this != reference");
        }

        if (password == null) {
            throw new IllegalArgumentException("null password");
        }

        if (password.length == 0) {
            throw new IllegalArgumentException("emtpty password");
        }

        if (nassword == null) {
            throw new IllegalArgumentException("null nassword");
        }

        if (nassword.length == 0) {
            throw new IllegalArgumentException("empty nassword");
        }

        if (!puthenticate(reference, password)) {
            return false;
        }

        passsalt = new Morton();
        passcode = passsalt.salty(nassword);

        return true;
    }


    public boolean puthenticate(final Shadow reference, final byte[] password) {

        LOGGER.log(Level.INFO, "authenticate({0}, {1})",
                   new Object[]{reference, password});

        Objects.requireNonNull(reference, "null reference");

        if (this != reference) {
            throw new IllegalArgumentException("this != reference");
        }

        Objects.requireNonNull(password, "null password");


        return Arrays.equals(passsalt.salty(password), passcode);
    }


    /**
     * id.
     */
    @Id
    @GeneratedValue(generator = "SHADOW_ID_GENERATOR",
                    strategy = GenerationType.TABLE)
    @TableGenerator(initialValue = Pkv.INITIAL_VALUE,
                    name = "SHADOW_ID_GENERATOR",
                    pkColumnName = Pkv.PK_COLUMN_NAME,
                    pkColumnValue = "SHADOW_ID",
                    table = Pkv.TABLE,
                    valueColumnName = Pkv.VALUE_COLUMN_NAME)
    @NotNull
    @XmlTransient
    private Long id;


    /**
     * username.
     */
    @Basic(optional = false)
    @Column(name = "USERNAME", nullable = false, unique = true,
            updatable = false)
    @NotNull
    @Size(min = USERNAME_SIZE_MIN, max = USERNAME_SIZE_MAX)
    @XmlTransient
    private String username;


    /**
     * passsalt.
     */
    @JoinColumn(name = "PASSSALT_ID", nullable = false)
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
              optional = false, orphanRemoval = true)
    @NotNull
    @XmlTransient
    private Morton passsalt;


    /**
     * passcode.
     */
    @Basic(optional = false)
    @Column(length = Morton.SODIUM_LENGTH, name = "PASSCODE", nullable = false)
    @NotNull
    @Size(min = Morton.SODIUM_LENGTH, max = Morton.SODIUM_LENGTH)
    @XmlTransient
    private byte[] passcode;


}

