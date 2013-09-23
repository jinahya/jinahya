/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.xml.bind;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


/**
 * An unwrapped value for integers.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@XmlRootElement
public class UnwrappedInteger extends UnwrappedValue<Integer> {


    /**
     * Creates a new instance with given raw value.
     *
     * @param rawValue the raw value
     *
     * @return a new instance
     */
    public static UnwrappedInteger newInstance(final Integer rawValue) {

        if (rawValue == null) {
            throw new NullPointerException("rawValue");
        }

        return newInstance(UnwrappedInteger.class, rawValue);
    }


    @XmlValue
    @Override
    public Integer getRawValue() {

        return super.getRawValue();
    }


    @Override
    public void setRawValue(final Integer rawValue) {

        super.setRawValue(rawValue);
    }


}
