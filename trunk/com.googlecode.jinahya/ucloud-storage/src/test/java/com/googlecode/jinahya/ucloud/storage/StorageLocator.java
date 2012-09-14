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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
class StorageLocator extends MappedStorageLocator {


    public Long getDeletedMillis() {
        return deletedMillis;
    }


    public void setDeletedMillis(final Long deletedMillis) {

        if (deletedMillis != null && this.deletedMillis != null) {
            return;
        }

        this.deletedMillis = deletedMillis;
    }


    @Column(name = "ID", nullable = false)
    @GeneratedValue
    @Id
    private Long id;


    @Basic
    @Column(name = "DELETED_MILLIS")
    private Long deletedMillis;


}

