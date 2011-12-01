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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = RealmRole.TABLE_NAME)
@XmlType(propOrder = {"id", "description"})
public class RealmRole {


    public static final String TABLE_NAME = "REALM_ROLE";


    public static final String ROLE_NAME_COLUMN_NAME = "ROLE_NAME";


    /**
     * Returns id.
     *
     * @return id.
     */
    public RealmRoleId getId() {
        return id;
    }


    /**
     * Sets id.
     *
     * @param id id
     */
    public void setId(final RealmRoleId id) {

        if (id == null) {
            throw new NullPointerException("null id");
        }

        this.id = id;
    }


    /**
     * Returns service.
     *
     * @return service.
     */
    public RealmService getService() {
        return service;
    }


    /**
     * Sets service.
     *
     * @param service service
     */
    public void setService(final RealmService service) {

        if (service == null) {
            throw new NullPointerException("null service");
        }

        this.service = service;
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
     * @param description description.
     */
    public void setDescription(final String description) {

        this.description = description;
    }


    /**
     * Returns a collection of <code>RealmUser</code>s mapped to this role.
     *
     * @return users
     */
    public Collection<RealmUser> getUsers() {

        if (users == null) {
            users = new ArrayList<RealmUser>();
        }

        return users;
    }


    @EmbeddedId
    @XmlElement(required = true, nillable = false)
    private RealmRoleId id;


    //@JoinColumn(name = Service.SERVICE_NAME_COLUMN_NAME, nullable = false)
    @ManyToOne(optional = false)
    @MapsId("serviceName")
    @XmlTransient
    private RealmService service;


    @Basic(optional = true)
    @Column(name = "DESCRIPTION", nullable = true, unique = false)
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "token")
    private String description;


    @ManyToMany(mappedBy = "roles")
    @XmlTransient
    private Collection<RealmUser> users;


}

