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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = "STORAGE_REFERENCE")
public class StorageReference extends MappedStorageReference {


    public static StorageReference newInstance(
        final String containerName, final String objectName,
        final StorageLocator storageLocator) {

        final StorageReference instance =
            newInstance(StorageReference.class, containerName, objectName);

        instance.storageLocator = storageLocator;

        return instance;
    }


    @PreRemove
    protected void _PreRemove() {
    }


    @GeneratedValue
    @Id
    private Long id;


    /**
     * storage locator.
     */
    @JoinColumn(name = "STORAGE_LOCATOR_ID", nullable = false)
    @OneToOne(optional = false)
    @NotNull
    @XmlTransient
    private StorageLocator storageLocator;


}

