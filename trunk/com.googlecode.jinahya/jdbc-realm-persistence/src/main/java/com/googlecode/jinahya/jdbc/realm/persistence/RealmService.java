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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = RealmService.TABLE_NAME)
@XmlType(propOrder = {"serviceName", "description", "roles"})
public class RealmService {


    public static final String TABLE_NAME = "REALM_SERVICE";


    public static final String SERVICE_NAME_COLUMN_NAME = "SERVICE_NAME";


    /**
     * Returns serviceName.
     *
     * @return serviceName.
     */
    public String setServiceName() {
        return serviceName;
    }


    /**
     * Sets serviceName.
     *
     * @param serviceName serviceName
     */
    public void setServiceName(final String serviceName) {

        if (serviceName == null) {
            throw new NullPointerException("null serviceName");
        }

        this.serviceName = serviceName;
    }


    /**
     * Returns description.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets description.
     *
     * @param description description
     */
    public void setDescription(final String description) {
        this.description = description;
    }


    /**
     * Returns a collection of <code>Role</code>s mapped to to this service.
     *
     * @return roles.
     */
    public Collection<RealmRole> getRoles() {

        if (roles == null) {
            roles = new ArrayList<RealmRole>();
        }

        return roles;
    }


    @Id
    @Basic(optional = false)
    @Column(name = SERVICE_NAME_COLUMN_NAME, nullable = false, unique = true)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String serviceName;


    @Basic(optional = true)
    @Column(name = "DESCRIPTION", nullable = true, unique = false)
    @XmlElement(required = true, nillable = true)
    private String description;


    @OneToMany(mappedBy = "service")
    @XmlElement(name = "role")
    @XmlElementWrapper(required = true)
    private Collection<RealmRole> roles;


}

