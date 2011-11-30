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


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"userName", "password"})
public class Role {


    public Service getService() {
        return service;
    }


    public void setService(final Service service) {

        if (service == null) {
            throw new NullPointerException("null service");
        }

        this.service = service;
    }


    public String getRoleName() {
        return roleName;
    }


    public void setRoleName(String roleName) {

        if (roleName == null) {
            throw new NullPointerException("null roleName");
        }

        roleName = roleName.trim();

        if (roleName.isEmpty()) {
            throw new IllegalArgumentException("empty roleName");
        }

        this.roleName = roleName;
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


    @JoinColumn(name = Service.SERVICE_NAME_COLUMN_NAME, nullable=false)
    @ManyToOne(optional = false)
    private Service service;


    @Id
    @Basic(optional = false)
    @Column(name = "ROLE_NAME", nullable = false, unique = true)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String roleName;


    @Basic(optional = false)
    @Column(name = "ENABLED", nullable = false, unique = false)
    @XmlAttribute(required = true)
    private boolean enabled;


    @Basic(optional = false)
    @Column(name = "DESCRIPTION", nullable = true, unique = false)
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "token")
    private String description;


}

