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


import java.lang.reflect.Modifier;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <R> raw value type parameter
 */
@XmlTransient
public abstract class NillableValue<R> {


    public static <V extends NillableValue<R>, R> V newInstance(
        final Class<V> type, final R raw) {

        if (type == null) {
            throw new IllegalArgumentException("null type");
        }

        if (Modifier.isAbstract(type.getModifiers())) {
            throw new IllegalArgumentException("abstract type");
        }

        try {
            final V instance = type.newInstance();
            instance.setRaw(raw);
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
        final NillableValue<R> other = (NillableValue<R>) obj;
        if (this.raw != other.raw
            && (this.raw == null || !this.raw.equals(other.raw))) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.raw != null ? this.raw.hashCode() : 0);
        return hash;
    }


    /**
     * Returns raw value.
     *
     * @return raw value
     */
    public R getRaw() {
        return raw;
    }


    /**
     * Sets raw value.
     *
     * @param raw raw value
     */
    public void setRaw(final R raw) {
        this.raw = raw;

    }


    private R raw;


}

