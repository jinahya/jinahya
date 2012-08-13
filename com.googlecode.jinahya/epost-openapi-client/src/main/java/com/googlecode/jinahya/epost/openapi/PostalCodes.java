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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class PostalCodes {


    /**
     * Creates a new instance with given
     * <code>results</code>.
     *
     * @param results results
     * @return a new instance
     */
    public static PostalCodes newInstance(final Map<String, String> results) {

        final PostalCodes instance = new PostalCodes();

        for (Entry<String, String> entry : results.entrySet()) {
            instance.getPostalCodes().add(
                PostalCode.newInstance(entry.getKey(), entry.getValue()));
        }

        return instance;
    }


    /**
     * Returns postalCodes.
     *
     * @return postalCodes
     */
    public Collection<PostalCode> getPostalCodes() {

        if (postalCodes == null) {
            postalCodes = new ArrayList<PostalCode>();
        }

        return postalCodes;
    }


    /**
     * postalCodes.
     */
    @XmlElement(name = "postalCode")
    private Collection<PostalCode> postalCodes;


}

