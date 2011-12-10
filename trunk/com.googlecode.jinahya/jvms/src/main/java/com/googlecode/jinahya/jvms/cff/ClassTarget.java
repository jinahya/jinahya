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


package com.googlecode.jinahya.jvms.cff;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlEnum
public enum ClassTarget {


    /**
     * 1.1.
     */
    @XmlEnumValue("1.1")
    T11(3, 45),
    /**
     * 1.2.
     */
    @XmlEnumValue("1.2")
    T12(0, 46),
    /**
     * 1.3.
     */
    @XmlEnumValue("1.3")
    T13(0, 47),
    /**
     * 1.4.
     */
    @XmlEnumValue("1.4")
    T14(0, 48),
    /**
     * 1.5.
     */
    @XmlEnumValue("1.5")
    T15(0, 49),
    /**
     * 1.6.
     */
    @XmlEnumValue("1.6")
    T16(0, 50),
    /**
     * 1.7.
     */
    @XmlEnumValue("1.7")
    T17(0, 51);
    //T18(0, 52),
    //T19(0, 53);


    /**
     * Finds constant for given versions.
     *
     * @param minorVersion minor version
     * @param majorVersion major version
     * @return matched constant
     */
    public static ClassTarget valueOf(final int minorVersion,
                                       final int majorVersion) {

        for (ClassTarget value : values()) {
            if (value.minorVersion == minorVersion
                && value.majorVersion == majorVersion) {
                return value;
            }
        }

        throw new IllegalArgumentException(
            "illegal version: " + minorVersion + "/" + majorVersion);
    }


    /**
     * Creates a new instance.
     *
     * @param minorVersion minorVersion
     * @param majorVersion majorVersion
     */
    private ClassTarget(final int minorVersion, final int majorVersion) {
        this.minorVersion = minorVersion;
        this.majorVersion = majorVersion;
    }


    /** minorVersion. */
    protected final int minorVersion;


    /** majorVersion. */
    protected final int majorVersion;


}
