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


package com.googlecode.jinahya.persistence;


import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@MappedSuperclass
@XmlTransient
public abstract class Clud {


    // -------------------------------------------------------------- CREATED_AT
    public Date getCreatedAt() {

        return createdAt == null ? null : new Date(createdAt.getTime());
    }


    // -------------------------------------------------------------- LOCATED_AT
    public Date getLocatedAt() {

        return locatedAt == null ? null : new Date(locatedAt.getTime());
    }


    public void setLocatedAt(final Date locatedAt) {

        this.locatedAt =
            locatedAt == null ? null : new Date(locatedAt.getTime());
    }


    // -------------------------------------------------------------- UPDATED_AT
    public Date getUpdatedAt() {

        return updatedAt == null ? null : new Date(updatedAt.getTime());
    }


    public void setUpdatedAt(final Date updatedAt) {

        this.updatedAt =
            updatedAt == null ? null : new Date(updatedAt.getTime());
    }


    // -------------------------------------------------------------- DELETED_AT
    public Date getDeletedAt() {

        return deletedAt == null ? null : new Date(deletedAt.getTime());
    }


    public void setDeletedAt(final Date deletedAt) {

        this.deletedAt =
            deletedAt == null ? null : new Date(deletedAt.getTime());
    }


    @Basic(optional = false)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    @Basic
    @Column(name = "LOCATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date locatedAt;


    @Basic
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    @Basic
    @Column(name = "DELETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;


}
