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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SimpleClientNonceId extends SimpleClientId {


    public static SimpleClientNonceId newInstance(
        final String platformId, final String deviceId, final String systemId,
        final String requestNonce) {

        final SimpleClientNonceId instance = newInstance(
            SimpleClientNonceId.class, platformId, deviceId, systemId);

        instance.requestNonce = requestNonce;

        return instance;
    }


    @Override
    public int hashCode() {

        int hash = super.hashCode();

        hash = 67 * hash + requestNonce.hashCode();

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

        if (!super.equals(obj)) {
            return false;
        }

        final SimpleClientNonceId other = (SimpleClientNonceId) obj;

        if (!requestNonce.equals(other.requestNonce)) {
            return false;
        }

        return true;
    }


    public String getRequestNonce() {
        return requestNonce;
    }


    private String requestNonce;


}

