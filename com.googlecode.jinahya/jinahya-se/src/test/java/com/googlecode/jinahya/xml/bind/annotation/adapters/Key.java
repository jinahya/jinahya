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


package com.googlecode.jinahya.xml.bind.annotation.adapters;


import java.io.Serializable;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Key implements Comparable<Key>, Serializable {


    public long getId() {
        return id;
    }


    public void setId(final long id) {
        this.id = id;
    }


    @Override
    public int compareTo(final Key o) {

        if (o == null) {
            throw new NullPointerException("null o");
        }

        //final int idCompared = Long.compare(id, o.id); // jdk7
        final int idCompared = Long.valueOf(id).compareTo(o.id);
        if (idCompared != 0) {
            return idCompared;
        }

        return 0;
    }


    @Override
    public int hashCode() {

        int result = 17;

        result = 37 * result + (int) (id ^ (id >>> 32));

        return result;
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Key)) {
            return false;
        }

        final Key key = (Key) obj;

        return id == key.id;
    }


    private long id;


}

