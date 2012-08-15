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


package com.googlecode.jinahya.stackoverflow.q11956071;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Item {


    public static Item newInstance(final int key, final String value) {

        final Item instance = new Item();

        instance.setKey(key);
        instance.setValue(value);

        return instance;
    }


    public int getKey() {
        return key;
    }


    public void setKey(final int key) {
        this.key = key;
    }


    public String getValue() {
        return value;
    }


    public void setValue(final String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "key=" + key + "&value=" + value;
    }


    @XmlAttribute
    private int key;


    @XmlValue
    private String value;


}

