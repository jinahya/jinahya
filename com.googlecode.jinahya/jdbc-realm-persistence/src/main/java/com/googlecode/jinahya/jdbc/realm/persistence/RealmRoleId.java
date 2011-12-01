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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * An embeddable id for RealmRoles.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Embeddable
@XmlType(propOrder = {"serviceName", "roleName"})
public class RealmRoleId implements Serializable {


    /**
     * Returns serviceName.
     *
     * @return serviceName
     */
    public String getServiceName() {
        return serviceName;
    }


    /**
     * Sets serviceName.
     *
     * @param serviceName serviceName
     */
    public void setServiceName(final String serviceName) {

        if (serviceName == null) {
            throw new NullPointerException("null serviceName");
        }

        this.serviceName = serviceName;
    }


    /**
     * Returns roleName.
     *
     * @return roleName.
     */
    public String getRoleName() {
        return roleName;
    }


    /**
     * Sets roleName.
     *
     * @param roleName roleName
     */
    public void setRoleName(final String roleName) {

        if (roleName == null) {
            throw new NullPointerException("null roleName");
        }

        this.roleName = roleName;
    }


    @Override
    public boolean equals(final Object obj) {

        if (!(obj instanceof RealmRoleId)) {
            return false;
        }

        final RealmRoleId casted = (RealmRoleId) obj;

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


    @Column(name = RealmService.SERVICE_NAME_COLUMN_NAME, nullable = false)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String serviceName;


    @Column(name = RealmRole.ROLE_NAME_COLUMN_NAME, nullable = false)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String roleName;


}

