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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;


/**
 * Person.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlAccessorType(XmlAccessType.NONE)
//@XmlType(propOrder = {"id", "name"})
public class Staff implements Comparable<Staff>, Serializable {


    /** GENERATED. */
    private static final long serialVersionUID = -1696007229340041164L;


    /**
     * Creates a new person with given <code>id</code> and <code>name</code>.
     *
     * @param id person's id
     * @param name person's name
     * @return a new person
     */
    public static Staff newInstance(final long id, final String name) {

        final Staff instance = new Staff();
        instance.setId(id);
        instance.setName(name);

        return instance;
    }


    public long getId() {
        return id;
    }


    public void setId(final long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    @Override
    public int compareTo(final Staff o) {

        if (o == null) {
            throw new NullPointerException("null o");
        }

//        final int idCompared = Integer.compare(id, o.id); // jdk7
        final int idCompared = Long.valueOf(id).compareTo(o.id);
        if (idCompared != 0) {
            return idCompared;
        }

        if (name == null) {
            if (o.name != null) {
                return -1;
            }
        } else { // key != null
            if (o.name == null) {
                return 1;
            } else {
                final int nameCompared = name.compareTo(o.name);
                if (nameCompared != 0) {
                    return nameCompared;
                }
            }
        }

        return 0;
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Staff)) {
            return false;
        }

        final Staff casted = (Staff) obj;

        if (id != casted.id) {
            return false;
        }

        if (!(name == casted.name)
            || (name != null && name.equals(casted.name))) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int result = 17;

        result = result * 37 + (int) (id ^ (id >>> 32));

        result = result * 37 + (name == null ? 0 : name.hashCode());

        return result;
    }


    @Override
    public String toString() {
        return (super.toString() + " id=" + id + ", name=" + name);
    }


    @XmlAttribute(required = true)
    private long id;


    @XmlValue
    private String name;


}

