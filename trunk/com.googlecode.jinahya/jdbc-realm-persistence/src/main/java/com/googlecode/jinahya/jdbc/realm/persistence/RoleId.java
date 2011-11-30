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


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Embeddable
@XmlType(propOrder = {"serviceName", "roleName"})
public class RoleId implements Serializable {


    public String getServiceName() {
        return serviceName;
    }


    public void setServiceName(final String serviceName) {

        if (serviceName == null) {
            throw new NullPointerException("null serviceName");
        }

        this.serviceName = serviceName;
    }


    public String getRoleName() {
        return roleName;
    }


    public void setRoleName(final String roleName) {

        if (roleName == null) {
            throw new NullPointerException("null roleName");
        }

        this.roleName = roleName;
    }


    @Override
    public boolean equals(final Object obj) {

        if (!(obj instanceof RoleId)) {
            return false;
        }

        final RoleId casted = (RoleId) obj;

        if (!(serviceName == casted.serviceName
              || (serviceName != null
                  && serviceName.equals(casted.serviceName)))) {
            return false;
        }

        if (!(roleName == casted.roleName
              || (roleName != null && roleName.equals(casted.roleName)))) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int result = 17;

        result = 37 * result + serviceName == null ? 0 : serviceName.hashCode();

        result = 37 * result + roleName == null ? 0 : roleName.hashCode();

        return result;
    }


    @Column(name = "SERVICE_NAME", nullable = false)
    @XmlElement(required = true, nillable = false)
    private String serviceName;


    @Column(name = "ROLE_NAME", nullable = false)
    @XmlElement(required = true, nillable = false)
    private String roleName;


}

