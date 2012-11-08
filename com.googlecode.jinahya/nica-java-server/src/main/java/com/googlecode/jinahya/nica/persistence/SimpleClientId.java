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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SimpleClientId implements Serializable {


    public static SimpleClientId newInstance(final String platformId,
                                             final String deviceId,
                                             final String systemId) {

        if (platformId == null) {
            throw new IllegalArgumentException("null platformId");
        }

        if (deviceId == null) {
            throw new IllegalArgumentException("null deviceId");
        }

        if (systemId == null) {
            throw new IllegalArgumentException("null systemId");
        }

        final SimpleClientId instance = new SimpleClientId();

        instance.platformId = platformId;
        instance.deviceId = deviceId;
        instance.systemId = systemId;

        return instance;
    }


    protected static <I extends SimpleClientId> I newInstance(
        final Class<I> idClass, final String platformId, final String deviceId,
        final String systemId) {

        if (idClass == null) {
            throw new IllegalArgumentException("null idClass");
        }

        if (platformId == null) {
            throw new IllegalArgumentException("null platformId");
        }

        if (deviceId == null) {
            throw new IllegalArgumentException("null deviceId");
        }

        if (systemId == null) {
            throw new IllegalArgumentException("null systemId");
        }

        final I instance;
        try {
            instance = idClass.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }

        ((SimpleClientId) instance).platformId = platformId;
        ((SimpleClientId) instance).deviceId = deviceId;
        ((SimpleClientId) instance).systemId = systemId;

        return instance;

    }


    @Override
    public int hashCode() {

        int hash = 7;

        hash = 17 * hash + platformId.hashCode();

        hash = 17 * hash + deviceId.hashCode();

        hash = 17 * hash + systemId.hashCode();

        return hash;
    }


    @Override
    public boolean equals(final Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final SimpleClientId other = (SimpleClientId) obj;

        if (!platformId.equals(other.platformId)) {
            return false;
        }

        if (!deviceId.equals(other.deviceId)) {
            return false;
        }

        if (!systemId.equals(other.systemId)) {
            return false;
        }

        return true;
    }


    public String getPlatformId() {
        return platformId;
    }


    public String getDeviceId() {
        return deviceId;
    }


    public String getSystemId() {
        return systemId;
    }


    private String platformId;


    private String deviceId;


    private String systemId;


}

