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


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"address", "code"})
public class PostalCode {


    public static PostalCode newInstance(final String address,
                                         final String code) {

        final PostalCode instance = new PostalCode();

        instance.setAddress(address);
        instance.setCode(code);

        return instance;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(final String address) {

        if (address == null) {
            throw new IllegalArgumentException("null address");
        }

        this.address = address;
    }


    public String getCode() {
        return code;
    }


    public void setCode(final String code) {

        if (code == null) {
            throw new IllegalArgumentException("null code");
        }

        this.code = code;
    }


    @XmlElement(required = true)
    private String address;


    @XmlElement(required = true)
    private String code;


}

