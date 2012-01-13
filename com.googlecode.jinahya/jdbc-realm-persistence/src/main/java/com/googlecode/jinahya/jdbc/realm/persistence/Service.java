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
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = Service.TABLE_NAME)
@XmlRootElement
@XmlType(propOrder = {"name", "roles"})
public class Service implements Serializable {


    /**
     * GENERATED.
     */
    private static final long serialVersionUID = -3069188431329850227L;


    /**
     * table name.
     */
    static final String TABLE_NAME = "REALM_SERVICE";


    /**
     * id column name.
     */
    static final String ID_COLUMN_NAME = "ID";


    /**
     * id generator name.
     */
    static final String ID_GENERATOR_NAME = ID_COLUMN_NAME + "_GENERATOR";


    /**
     * Returns name.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }


    /**
     * Sets name.
     *
     * @param name name
     */
    public void setName(final String name) {
        this.name = name;
    }


    /**
     * Returns a collection of
     * <code>Role</code>s mapped to to this service.
     *
     * @return roles.
     */
    public Collection<Role> getRoles() {

        if (roles == null) {
            roles = new ArrayList<Role>();
        }

        return roles;
    }


    /**
     * id.
     */
    @Id
    @Column(name = ID_COLUMN_NAME)
    @TableGenerator(name = ID_GENERATOR_NAME,
                    table = GeneratedId.TABLE_NAME,
                    pkColumnName = GeneratedId.PK_COLUMN_NAME,
                    valueColumnName = GeneratedId.VALUE_COLUMN_NAME,
                    pkColumnValue = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.TABLE,
                    generator = ID_GENERATOR_NAME)
    @XmlAttribute
    private Long id;


    /**
     * name.
     */
    @Basic(optional = false)
    @Column(name = "SERVICE_NAME", nullable = false, unique = true)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String name;


    /**
     * roles.
     */
    @OneToMany(mappedBy = "service")
    @XmlElement(name = "role")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Role> roles;


}

