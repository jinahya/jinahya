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


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.ObjectName;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = "PREIXED_STORAGE_LOCATOR",
       uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CONTAINER_NAME", "OBJECT_NAME"},
                      name = "UNIQUE_OBJECT_NAME_BY_CONTAINER_NAME")
})
@XmlRootElement
@XmlType(propOrder = {"containerName", "objectName"})
public class PrefixedStorageLocator extends PrefixedMappedStorageLocator {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(PrefixedMappedStorageLocator.class.getName());


    // ---------------------------------------------------------- CREATED_MILLIS
    /**
     * Returns createdMillis.
     *
     * @return createdMillis
     */
    public long getCreatedMillis() {
        return createdMillis;
    }


    // ---------------------------------------------------------- DELETED_MILLIS
    /**
     * Returns deletedMillis.
     *
     * @return
     */
    public Long getDeletedMillis() {
        return deletedMillis;
    }


    /**
     * Sets deletedMillis.
     *
     * @param deletedMillis deletedMillis
     */
    public void setDeletedMillis(final Long deletedMillis) {

        LOGGER.log(Level.INFO, "setDeletedMillis({0})", deletedMillis);

        if (deletedMillis != null && this.deletedMillis != null) {
            return;
        }

        this.deletedMillis = deletedMillis;
    }


    // ---------------------------------------------------------------------- ID
    /**
     * Returns id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }


    // ------------------------------------------------------------- @PrePersist
    @PrePersist
    protected void _PrePersist() {
        createdMillis = System.currentTimeMillis();
    }


    @Override
    public String toString() {
        return super.toString()
               + "?createdMillis=" + createdMillis
               + "&deletedMillis=" + String.valueOf(deletedMillis)
               + "&id=" + String.valueOf(id)
               + "&containerNamePrefix="
               + String.valueOf(getContainerNamePrefix())
               + "&objectNamePrefix=" + String.valueOf(getObjectNamePrefix())
               + "&containerName=" + String.valueOf(getContainerName())
               + "&objectName=" + String.valueOf(getObjectName());
    }


    public String toXml() throws JAXBException, IOException {

        final JAXBContext context =
            JAXBContext.newInstance(PrefixedMappedStorageLocator.class);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream output = new ByteArrayOutputStream();

        marshaller.marshal(this, output);
        output.flush();

        final String encoding =
            (String) marshaller.getProperty(Marshaller.JAXB_ENCODING);

        return new String(output.toByteArray(), encoding);
    }


    @Override
    protected Long getSequenceNumber() {
        return id;
    }


    /**
     * createdMillis.
     */
    @Basic(optional = false)
    @Column(name = "CREATED_MILLIS", nullable = false, updatable = false)
    @XmlAttribute
    private long createdMillis;


    /**
     * deletedMillis.
     */
    @Basic
    @Column(name = "DELETED_MILLIS")
    @XmlAttribute
    private Long deletedMillis;


    /**
     * id.
     */
    @Column(name = "ID", nullable = false, updatable = false)
    @GeneratedValue
    @Id
//    @NotNull // Hibernate doesn't like this!
    @XmlAttribute
    private Long id;


}

