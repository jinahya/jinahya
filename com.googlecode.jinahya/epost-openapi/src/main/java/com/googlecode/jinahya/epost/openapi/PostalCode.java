/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.epost.openapi;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"address", "code"})
public class PostalCode {


    public static final String CODE_RANGE_PATTERN_REGEX = " (\\d+)~(\\d+)";


    public static final Pattern CODE_RANGE_PATTERN =
        Pattern.compile(CODE_RANGE_PATTERN_REGEX);


    public static final String CODE_PATTERN_REGEX = "(\\d{3})-(\\d{3})";


    public static final Pattern CODE_PATTERN =
        Pattern.compile(CODE_PATTERN_REGEX);


    /**
     * Creates a new instance.
     *
     * @param address address
     * @param code code
     * @return a new instance
     */
    public static PostalCode newInstance(final String address,
                                         final String code) {

        if (address == null) {
            throw new IllegalArgumentException("null address");
        }

        if (code == null) {
            throw new IllegalArgumentException("null code");
        }

        final PostalCode instance = new PostalCode();

        instance.setAddress(address);
        instance.setCode(code);

        return instance;
    }


    // ----------------------------------------------------------------- ADDRESS
    /**
     * Returns address.
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }


    /**
     * Sets address.
     *
     * @param address address
     */
    public void setAddress(final String address) {

        if (address == null) {
            throw new IllegalArgumentException("null address");
        }

        this.address = address;
    }


    /**
     * Returns address as range removed.
     *
     * @return address
     */
    @Transient
    @XmlAttribute
    @XmlSchemaType(name = "token")
    public String getAddress1() {
        return CODE_RANGE_PATTERN.matcher(address).replaceAll("");
    }


    // -------------------------------------------------------------------- CODE
    /**
     * Returns code.
     *
     * @return code
     */
    public String getCode() {
        return code;
    }


    /**
     * Sets code.
     *
     * @param code code
     */
    public void setCode(final String code) {

        if (code == null) {
            throw new IllegalArgumentException("null code");
        }

        if (!CODE_PATTERN.matcher(code).matches()) {
            throw new IllegalArgumentException("illegal code");
        }

        this.code = code;
    }


    /**
     *
     * @return
     */
    @Transient
    @XmlAttribute
    @XmlSchemaType(name = "token")
    public String getCode1() {

        final Matcher matcher = CODE_PATTERN.matcher(code);
        assert matcher.matches();

        return matcher.group(1);

        //return code.substring(0, code.indexOf('-'));
    }


    @Transient
    @XmlAttribute
    @XmlSchemaType(name = "token")
    public String getCode2() {

        final Matcher matcher = CODE_PATTERN.matcher(code);
        assert matcher.matches();

        return matcher.group(2);

        //return code.substring(code.indexOf('-') + 1);
    }


    /**
     * address.
     */
    @Basic(optional = false)
    @Column(nullable = false)
    @NotNull
    @Size(min = 1)
    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    private String address;


    /**
     * code.
     */
    @Basic(optional = false)
    @Column(nullable = false)
    @NotNull
    @Size(min = 1)
    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    private String code;


}

