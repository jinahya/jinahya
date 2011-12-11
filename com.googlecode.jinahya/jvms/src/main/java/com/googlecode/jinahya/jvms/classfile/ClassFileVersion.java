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


package com.googlecode.jinahya.jvms.classfile;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlEnum
public enum ClassFileVersion {


    /**
     * 1.1.
     */
    @XmlEnumValue("1.1")
    V11(3, 45),
    /**
     * 1.2.
     */
    @XmlEnumValue("1.2")
    V12(0, 46),
    /**
     * 1.3.
     */
    @XmlEnumValue("1.3")
    V13(0, 47),
    /**
     * 1.4.
     */
    @XmlEnumValue("1.4")
    V14(0, 48),
    /**
     * 1.5.
     */
    @XmlEnumValue("1.5")
    V15(0, 49),
    /**
     * 1.6.
     */
    @XmlEnumValue("1.6")
    V16(0, 50),
    /**
     * 1.7.
     */
    @XmlEnumValue("1.7")
    V17(0, 51);
    //T18(0, 52),
    //T19(0, 53);


    /**
     * Finds constant for given versions.
     *
     * @param minorVersion minor version
     * @param majorVersion major version
     * @return matched constant
     */
    public static ClassFileVersion valueOf(final int minorVersion,
                                  final int majorVersion) {

        for (ClassFileVersion value : values()) {
            if (value.minor == minorVersion && value.major == majorVersion) {
                return value;
            }
        }

        throw new IllegalArgumentException(
            "illegal version: " + minorVersion + "/" + majorVersion);
    }


    /**
     * Creates a new instance.
     *
     * @param minor minor version
     * @param major major version
     */
    private ClassFileVersion(final int minor, final int major) {
        this.minor = minor;
        this.major = major;
    }


    /** minor version. */
    protected final int minor;


    /** major version. */
    protected final int major;


}

