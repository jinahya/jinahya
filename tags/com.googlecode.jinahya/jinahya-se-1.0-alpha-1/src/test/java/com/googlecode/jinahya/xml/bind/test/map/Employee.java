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


package com.googlecode.jinahya.xml.bind.test.map;


import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Employee {


    public static Employee newInstance(final long id, final String name,
                                       final int age) {

        final Employee employee = new Employee();

        employee.id = id;
        employee.name = name;
        employee.age = age;

        return employee;
    }


    public long getId() {

        return id;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 31 * hash + this.age;
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
        final Employee other = (Employee) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.name == null)
            ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        return true;
    }


    @XmlElement
    private long id;


    @XmlElement
    private String name;


    @XmlElement
    private int age;


}

