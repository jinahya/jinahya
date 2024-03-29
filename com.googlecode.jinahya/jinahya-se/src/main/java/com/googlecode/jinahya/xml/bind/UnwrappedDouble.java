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
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@XmlRootElement
public class UnwrappedDouble extends UnwrappedValue<Double> {


    public static UnwrappedDouble newInstance(final Double rawValue) {

        if (rawValue == null) {
            throw new NullPointerException("rawValue");
        }

        return newInstance(UnwrappedDouble.class, rawValue);
    }


    @XmlValue
    @Override
    public Double getRawValue() {

        return super.getRawValue();
    }


    @Override
    public void setRawValue(final Double rawValue) {

        super.setRawValue(rawValue);
    }


}
