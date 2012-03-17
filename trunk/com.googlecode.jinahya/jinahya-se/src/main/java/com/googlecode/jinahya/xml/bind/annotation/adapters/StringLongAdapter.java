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


package com.googlecode.jinahya.xml.bind.annotation.adapters;


import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class StringLongAdapter extends XmlAdapter<String, Long> {


    @Override
    public String marshal(final Long bound) throws Exception {
        return DatatypeConverter.printLong(bound);
    }


    @Override
    public Long unmarshal(final String value) throws Exception {
        return DatatypeConverter.parseLong(value);
    }


}

