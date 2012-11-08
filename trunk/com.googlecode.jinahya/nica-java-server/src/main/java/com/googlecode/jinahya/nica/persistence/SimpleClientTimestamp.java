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


package com.googlecode.jinahya.nica.persistence;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = "SIMPLE_CLIENT_TIMESTAMP")
public class SimpleClientTimestamp implements Serializable {


    public long getRequestTimetamp() {
        return requestTimestamp;
    }


    @Id
    @PrimaryKeyJoinColumns({
        @PrimaryKeyJoinColumn(name = "SIMPLE_CLIENT_PLATFORM_ID",
                              referencedColumnName = "PLATFORM_ID"),
        @PrimaryKeyJoinColumn(name = "SIMPLE_CLIENT_DEVICE_ID",
                              referencedColumnName = "DEVICE_ID"),
        @PrimaryKeyJoinColumn(name = "SIMPLE_CLIENT_SYSTEM_ID",
                              referencedColumnName = "SYSETM_ID")
    })
    @OneToOne(optional = false)
    @NotNull
    private SimpleClient simpleClient;


    @Basic(optional = false)
    @Column(name = "REQUEST_TIMESTAMP", nullable = false)
    private long requestTimestamp;


}

