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


import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Department.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@XmlType(propOrder = {"name", "staffs"})
public class Department {


    /**
     * Creates a new instance of Department with given <code>name</code>.
     *
     * @param name the name of new department
     * @param staffs staffs of new department
     * @return new department
     */
    public static Department newInstance(final String name,
                                         final Staff... staffs) {

        final Department instance = new Department();
        instance.setName(name);
        for (Staff staff : staffs) {
            instance.getStaffs().put(staff.getId(), staff);
        }

        return instance;
    }


    /**
     * Returns the name of this department.
     *
     * @return the name of this department
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name of this department.
     *
     * @param name the name of this department
     */
    public void setName(final String name) {
        this.name = name;
    }


    /**
     * Returns a Map of people. For each entries, the key is a person's id and
     * the value is the person mapped to the key.
     *
     * @return a Map of &lt;Person#id&gt;, &lt;Person&gt;.
     */
    public Map<Long, Staff> getStaffs() {

        if (staffs == null) {
            staffs = new HashMap<Long, Staff>();
        }

        return staffs;
    }


    /**
     * Returns the person mapped to given <code>id</code>.
     *
     * @param id person's id.
     * @return the person mapped to given <code>id</code> or null if not found
     */
    public Staff getStaff(final long id) {
        return getStaffs().get(id);
    }


    /** department name. */
    @XmlElement(required = true)
    private String name;


    /** staffs of this department. */
    @XmlJavaTypeAdapter(CrowdAdapter.class)
    private Map<Long, Staff> staffs;


}

