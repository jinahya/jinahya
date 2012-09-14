/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.ucloud.storage;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = "STORAGE_LOCATOR",
       uniqueConstraints = {
           @UniqueConstraint(columnNames={"CONTAINER_NAME", "OBJECT_NAME"},
                             name="UNIQUE_OBJECT_NAME_BY_CONTAINER_NAME")
       })
class StorageLocator extends MappedStorageLocator {


    // ---------------------------------------------------------- DELETED_MILLIS
    /**
     * Returns deletedMillis.
     *
     * @return
     */
    public Long getDeletedMillis() {
        return deletedMillis;
    }


    /**
     * Sets deletedMillis.
     *
     * @param deletedMillis deletedMillis
     */
    public void setDeletedMillis(final Long deletedMillis) {

        if (deletedMillis != null && this.deletedMillis != null) {
            return;
        }

        this.deletedMillis = deletedMillis;
    }


    // ---------------------------------------------------------------------- ID
    /**
     * Returns id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }


    /**
     * deletedMillis.
     */
    @Basic
    @Column(name = "DELETED_MILLIS")
    @XmlAttribute
    private Long deletedMillis;


    /**
     * id.
     */
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    @Id
    @NotNull
    @XmlAttribute
    private Long id;


}

