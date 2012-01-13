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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = Role.TABLE_NAME)
@XmlRootElement
@XmlType(propOrder = {"name"})
public class Role implements Serializable {


    /**
     * GENERATED.
     */
    private static final long serialVersionUID = -1471578012270653477L;


    /**
     * table name.
     */
    static final String TABLE_NAME = "REALM_ROLE";


    /**
     * id column name.
     */
    static final String ID_COLUMN_NAME = "ID";


    /**
     * id generator name.
     */
    static final String ID_GENERATOR_NAME = ID_COLUMN_NAME + "_GENERATOR";


    public void afterUnmarshal(final Object target, final Object parent) {
        System.out.println("afterUnmarshal(" + target + ", " + parent + ")");
        service = (Service) parent;
    }


    public void afterUnmarshal(final Unmarshaller unmarshaller,
                               final Object parent) {
        System.out.println("afterUnmarshal(" + unmarshaller + ", " + parent + ")");
        service = (Service) parent;
    }


    /**
     * Returns id.
     *
     * @return id.
     */
    public Long getId() {
        return id;
    }


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
     * @param name name.
     */
    public void setName(final String name) {
        this.name = name;
    }


    /**
     * Returns service.
     *
     * @return service.
     */
    public Service getService() {
        return service;
    }


    /**
     * Sets service.
     *
     * @param service service
     */
    public void setService(final Service service) {
        this.service = service;
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
    @Basic(optional = true)
    @Column(name = "ROLE_NAME", nullable = true, unique = false)
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "token")
    private String name;


    /**
     * service.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = Service.ID_COLUMN_NAME, nullable = false)
    @XmlTransient
    private Service service;


}

