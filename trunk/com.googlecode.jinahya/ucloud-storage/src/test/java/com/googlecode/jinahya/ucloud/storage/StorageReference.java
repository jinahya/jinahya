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
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = StorageReference.NQ_COUNT,
                query = "SELECT COUNT(r)"
                        + " FROM StorageReference AS r"),
    @NamedQuery(name = StorageReference.NQ_LIST,
                query = "SELECT r"
                        + " FROM StorageReference AS r"
                        + " ORDER BY r.createdMillis ASC, r.id ASC")
})
@Table(name = "STORAGE_REFERENCE",
       uniqueConstraints = {
    @UniqueConstraint(columnNames = {"STORAGE_LOCATOR_ID"},
                      name = "UNIQUE_STORAGE_LOCATORE")})
@XmlRootElement
public class StorageReference extends MappedStorageReference<StorageLocator> {


    public static final String NQ_COUNT = "StorageReference.NQ_COUNT";


    public static final String NQ_LIST = "StorageReference.NQ_LIST";


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(StorageReference.class.getName());


    /**
     * Creates a new instance with given
     * <code>storageLocator</code>.
     *
     * @param storageLocator storageLocator
     *
     * @return a new instance
     */
    public static StorageReference newInstance(
        final StorageLocator storageLocator) {

        return newInstance(StorageReference.class, storageLocator);
    }


    // ---------------------------------------------------------- CREATED_MILLIS
    /**
     * Returns createdMillis.
     *
     * @return createdMillis
     */
    public long getCreatedMillis() {
        return createdMillis;
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


    // ------------------------------------------------------ STORAGE_LOCATOR_ID
    @Transient
    @XmlAttribute
    @SuppressWarnings({"unused"})
    private Long getStorageLocatorId() {

        final StorageLocator storageLocator = getStorageLocator();
        return storageLocator == null ? null : storageLocator.getId();
    }


    // ------------------------------------------------------------- @PrePersist
    @PrePersist
    protected void _PrePersist() {

        createdMillis = System.currentTimeMillis();
    }


    // -------------------------------------------------------------- @PreRemove
    @PreRemove
    protected void _PreRemove() {

        getStorageLocator().setDeletedMillis(System.currentTimeMillis());
    }


    @Override
    public String toString() {
        return super.toString()
               + "?createdMillis=" + createdMillis
               + "&id=" + String.valueOf(id)
               + "&storageLocator=" + String.valueOf(getStorageLocator());
    }


    public String toXml() throws JAXBException, IOException {

        final JAXBContext context =
            JAXBContext.newInstance(StorageReference.class);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream output = new ByteArrayOutputStream();

        marshaller.marshal(this, output);
        output.flush();

        final String encoding =
            (String) marshaller.getProperty(Marshaller.JAXB_ENCODING);

        return new String(output.toByteArray(), encoding);
    }


    /**
     * createdMillis.
     */
    @Basic(optional = false)
    @Column(name = "CREATED_MILLIS", nullable = false, updatable = false)
    @XmlAttribute
    private long createdMillis;


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

