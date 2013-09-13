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


package com.googlecode.jinahya.imageio;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@XmlTransient
public abstract class ImageFeature {


//    protected static <I extends ImageAspect> I newInstance(
//        final Class<I> type, final boolean readable, final boolean writable,
//        final String key) {
//
//        try {
//            final I instance = type.newInstance();
//            instance.setReadable(readable);
//            instance.setWritable(writable);
//            instance.setKey(key);
//            return instance;
//        } catch (InstantiationException ie) {
//            throw new RuntimeException(ie);
//        } catch (IllegalAccessException iae) {
//            throw new RuntimeException(iae);
//        }
//    }
//    protected ImageAspect() {
//        super();
//    }
    public boolean isReadable() {

        return readable;
    }


    public void setReadable(final boolean readable) {

        this.readable = readable;
    }


    public boolean isWritable() {

        return writable;
    }


    public void setWritable(final boolean writable) {

        this.writable = writable;
    }


    public String getValue() {

        return value;
    }


    public void setValue(final String value) {

        this.value = value;
    }


    @Override
    public String toString() {
        return super.toString()
               + "?readable=" + readable
               + "&writable=" + writable
               + "&value=" + value;
    }


    @XmlAttribute
    private boolean readable;


    @XmlAttribute
    private boolean writable;


    @XmlValue
    private String value;


}
