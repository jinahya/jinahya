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


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = "STORAGE_REFERENCE",
       uniqueConstraints = {
    @UniqueConstraint(columnNames = {"STORAGE_LOCATOR_ID"},
                      name = "UNIQUE_STORAGE_LOCATORE")})
public class StorageReference extends MappedStorageReference<StorageLocator> {


    /**
     * Creates a new instance with given
     * <code>storageLocator</code>.
     *
     * @param storageLocator storageLocator
     *
     * @return a new instance
     */
    public static StorageReference newInstance(
        final StorageLocator storageLocator) {

        return newInstance(StorageReference.class, storageLocator);
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


    // -------------------------------------------------------------- @PreRemove
    @PreRemove
    protected void _PreRemove() {
        getStorageLocator().setDeletedMillis(System.currentTimeMillis());
    }


    /**
     * id.
     */
    @GeneratedValue
    @Id
    @NotNull
    @XmlAttribute
    private Long id;


}

