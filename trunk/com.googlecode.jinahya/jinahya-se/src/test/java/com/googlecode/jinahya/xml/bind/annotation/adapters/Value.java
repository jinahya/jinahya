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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"key", "name", "age"})
public class Value implements Comparable<Value>, Serializable {


    /**
     * Returns key.
     *
     * @return key
     */
    public Key getKey() {
        return key;
    }


    /**
     * Sets key.
     *
     * @param key key
     */
    public void setKey(final Key key) {
        this.key = key;
    }


    /**
     * Returns name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets name.
     *
     * @param name name
     */
    public void setName(final String name) {
        this.name = name;
    }


    /**
     * Returns age.
     *
     * @return age
     */
    public int getAge() {
        return age;
    }


    /**
     * Sets age.
     * 
     * @param age age
     */
    public void setAge(final int age) {
        this.age = age;
    }


    @Override
    public int compareTo(final Value o) {

        if (o == null) {
            throw new NullPointerException("null o");
        }

        if (key == null) {
            if (o.key != null) {
                return -1;
            }
        } else { // key != null
            if (o.key == null) {
                return 1;
            } else {
                final int keyCompared = key.compareTo(key);
                if (keyCompared != 0) {
                    return keyCompared;
                }
            }
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

        //final int ageCompared = Integer.compare(age, o.age); // jdk7
        final int ageCompared = Integer.valueOf(age).compareTo(o.age);

        if (ageCompared != 0) {
            return ageCompared;
        }

        return 0;
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Value)) {
            return false;
        }

        final Value casted = (Value) obj;

        if (!(key == casted.key
              || (key != null && key.equals(casted.key)))) {
            return false;
        }

        if (!(name == casted.name)
            || (name != null && name.equals(casted.name))) {
            return false;
        }

        if (age != casted.age) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int result = 17;

        result = result * 37 + (key == null ? 0 : key.hashCode());

        result = result * 37 + (name == null ? 0 : name.hashCode());

        result = result * 37 + age;

        return result;
    }


    @Override
    public String toString() {
        return (super.toString()
                + " key=" + key
                + ", name=" + name
                + ", age=" + age);
    }


    @XmlElement(required = true)
    private Key key;


    @XmlElement(required = true)
    private String name;


    @XmlElement(required = true)
    private int age;


}

