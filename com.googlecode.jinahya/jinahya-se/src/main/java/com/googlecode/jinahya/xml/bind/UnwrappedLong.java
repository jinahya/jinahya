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
public class UnwrappedLong extends UnwrappedValue<Long> {


    public static UnwrappedLong newInstance(final Long rawValue) {

        if (rawValue == null) {
            throw new NullPointerException("rawValue");
        }

        return newInstance(UnwrappedLong.class, rawValue);
    }


    @XmlValue
    @Override
    public Long getRawValue() {

        return super.getRawValue();
    }


    @Override
    public void setRawValue(final Long rawValue) {

        super.setRawValue(rawValue);
    }


}
