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


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@MappedSuperclass
public class SimpleClientEntity {


    public String getPlatformId() {
        return platformId;
    }


    public String getDeviceId() {
        return deviceId;
    }


    public String getSystemId() {
        return systemId;
    }


    @Column(name = "PLATFORM_ID", nullable = false, updatable = false)
    @Id
    @NotNull
    private String platformId;


    @Column(name = "DEVICE_ID", nullable = false, updatable = false)
    @Id
    @NotNull
    private String deviceId;


    @Column(name = "SYSTEM_ID", nullable = false, updatable = false)
    @Id
    @NotNull
    private String systemId;


}

