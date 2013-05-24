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


import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <R> raw value type parameter
 */
@XmlTransient
public abstract class SimpleValue<R> implements Serializable {


    private static final long serialVersionUID = -4379691091470442003L;


    /**
     * Create a new typed instance.
     *
     * @param <V> simple value type parameter
     * @param <R> raw value type parameter
     * @param simpleValueType simple value type
     * @param rawValue raw value
     *
     * @return
     */
    public static <V extends SimpleValue<R>, R> V newInstance(
        final Class<V> simpleValueType, final R rawValue) {

        Objects.requireNonNull(simpleValueType, "null simpleValueType");

        try {
            final V instance = simpleValueType.newInstance();
            instance.setRawValue(rawValue);
            return instance;
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }
    }


    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        final SimpleValue<R> other = (SimpleValue<R>) obj;
        if (this.rawValue != other.rawValue
            && (this.rawValue == null
                || !this.rawValue.equals(other.rawValue))) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash
               + (this.rawValue != null ? this.rawValue.hashCode() : 0);
        return hash;
    }


    /**
     * Returns raw value.
     *
     * @return raw value
     */
    public R getRawValue() {
        return rawValue;
    }


    /**
     * Sets raw value.
     *
     * @param rawValue raw value
     */
    public void setRawValue(final R rawValue) {
        this.rawValue = rawValue;
    }


    /**
     * raw value.
     */
    private R rawValue;


}

