/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.xml.bind.test;


import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Item {


    public static Item newInstance() {
        return newInstance(ThreadLocalRandom.current());
    }


    public static Item newInstance(final Random random) {
        final Item instance = new Item();
        if (random.nextBoolean()) {
            instance.id = random.nextLong();
        }
        if (random.nextBoolean()) {
            instance.name = "name";
        }
        return instance;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + hashCode()
               + "?id=" + id + "&name=" + name;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }


    @XmlAttribute
    private Long id;


//    @XmlValue
    @XmlElement(nillable = true)
    private String name;


}

