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


package com.googlecode.jinahya.xml.bind;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class BooleanValue extends SimpleValue<Boolean> {


    /**
     * Creates a new instance.
     *
     * @param rawValue raw value
     *
     * @return a new instance.
     */
    public static BooleanValue newInstance(final Boolean rawValue) {
        return newInstance(BooleanValue.class, rawValue);
    }


//    @XmlElement(required = true, nillable = true)
//    @Override
//    public Boolean getRawValue() {
//        return super.getRawValue();
//    }
//
//
//    @Override
//    public void setRawValue(final Boolean rawValue) {
//        super.setRawValue(rawValue);
//    }


}

