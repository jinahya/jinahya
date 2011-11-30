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


package com.googlecode.jinahya.jdbc.realm.persistence;


import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"serviceName", "description"})
public class Service {

    public static final String SERVICE_NAME_COLUMN_NAME = "SERVICE_NAME";

    public String setServiceName() {
        return serviceName;
    }


    public void setServiceName(final String serviceName) {

        if (serviceName == null) {
            throw new NullPointerException("null serviceName");
        }

        this.serviceName = serviceName;
    }


    public boolean getEnabled() {
        return enabled;
    }


    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(final String description) {
        this.description = description;
    }


    public Collection<Role> getRoles() {

        if (roles == null) {
            roles = new ArrayList<Role>();
        }

        return roles;
    }


    @Id
    @Basic(optional = false)
    @Column(name = SERVICE_NAME_COLUMN_NAME, nullable = false, unique = true)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String serviceName;


    @Basic(optional = false)
    @Column(name = "ENABLED", nullable = false, unique = false)
    @XmlAttribute(required = true)
    private boolean enabled;


    @Basic(optional = true)
    @Column(name = "DESCRIPTION", nullable = true, unique = false)
    @XmlElement(required = true, nillable = true)
    private String description;


    @OneToMany(mappedBy = "SERVICE_NAME")
    private Collection<Role> roles;


}

