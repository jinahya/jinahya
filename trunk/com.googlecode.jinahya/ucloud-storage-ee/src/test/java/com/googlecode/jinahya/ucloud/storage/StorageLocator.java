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


import com.googlecode.jinahya.persistence.Pkv;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = StorageLocator.NQ_COUNT,
                query = "SELECT COUNT(l) FROM StorageLocator AS l"),
    @NamedQuery(name = StorageLocator.NQ_LIST,
                query = "SELECT l FROM StorageLocator AS l"
                        + " ORDER BY l.createdAt ASC, l.id ASC"),
    @NamedQuery(name = StorageLocator.NQ_COUNT_DELETED,
                query = "SELECT COUNT(l) FROM StorageLocator AS l"
                        + " WHERE l.deletedAt IS NOT NULL"),
    @NamedQuery(name = StorageLocator.NQ_LIST_DELETED,
                query = "SELECT l FROM StorageLocator AS l"
                        + " WHERE l.deletedAt IS NOT NULL"
                        + " ORDER BY l.createdAt ASC, l.id ASC")
})
@Table(name = "STORAGE_LOCATOR",
       uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CONTAINER_NAME", "OBJECT_NAME"},
                      name = "UNIQUE_OBJECT_NAME_BY_CONTAINER_NAME")
})
@XmlRootElement
@XmlType(propOrder = {"containerName", "objectName"})
public class StorageLocator extends MappedStorageLocator {


    public static final String NQ_COUNT = "StorageLocator.NQ_COUNT";


    public static final String NQ_LIST = "StorageLocator.NQ_LIST";


    public static final String NQ_COUNT_DELETED =
        "StorageLocator.NQ_COUNT_DELETED";


    public static final String NQ_LIST_DELETED =
        "StorageLocator.NQ_LIST_DELETED";


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(StorageLocator.class);


    // -------------------------------------------------------------- CREATED_AT
    /**
     * Returns createdMillis.
     *
     * @return createdMillis
     */
    public Date getCreatedMillis() {

        return createdAt == null ? null : new Date(createdAt.getTime());
    }


    // -------------------------------------------------------------- LOCATED_AT
    public Date getLocatedAt() {

        return locatedAt == null ? null : new Date(locatedAt.getTime());
    }


    public void setLocatedAt(final Date locatedAt) {

        this.locatedAt =
            locatedAt == null ? null : new Date(locatedAt.getTime());
    }


    // -------------------------------------------------------------- UPDATED_AT
    public Date getUpdatedAt() {

        return updatedAt == null ? null : new Date(updatedAt.getTime());
    }


    public void setUpdatedAt(final Date updatedAt) {

        this.updatedAt =
            updatedAt == null ? null : new Date(updatedAt.getTime());
    }


    // -------------------------------------------------------------- DELETED_AT
    /**
     * Returns deletedAt.
     *
     * @return deletedAt
     */
    public Date getDeletedAt() {

        return deletedAt == null ? null : new Date(deletedAt.getTime());
    }


    /**
     * Sets deletedMillis.
     *
     * @param deletedAt deletedMillis
     */
    public void setDeletedAt(final Date deletedAt) {

        LOGGER.debug("setDeletedMillis({})", deletedAt);

        this.deletedAt =
            deletedAt == null ? null : new Date(deletedAt.getTime());
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

        createdAt = new Date();
    }


    // -------------------------------------------------------------- @PreUpdate
    @PreUpdate
    protected void _PreUpdate() {

        updatedAt = new Date();
    }


    @Override
    public String toString() {
        return super.toString()
               + "?createdMillis=" + createdAt
               + "&deletedMillis=" + String.valueOf(deletedAt)
               + "&id=" + String.valueOf(id)
               + "&containerName=" + String.valueOf(getContainerName())
               + "&objectName=" + String.valueOf(getObjectName());
    }


    /**
     * Prints this instance as XML.
     *
     * @return an XML presentation
     *
     * @throws JAXBException if a JAXB error occurs
     * @throws IOException if an I/O error occurs
     */
    public String toXml() throws JAXBException, IOException {

        final JAXBContext context =
            JAXBContext.newInstance(StorageLocator.class);

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
     * createdAt.
     */
    @Basic(optional = false)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @XmlAttribute
    private Date createdAt;


    /**
     * locatedAt.
     */
    @Basic
    @Column(name = "LOCATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlAttribute
    private Date locatedAt;


    /**
     * updatedAt.
     */
    @Basic
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    /**
     * deletedAt.
     */
    @Basic
    @Column(name = "DELETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlAttribute
    private Date deletedAt;


    /**
     * id.
     */
    @Column(name = "ID", nullable = false, updatable = false)
    @GeneratedValue(generator = "STORAGE_LOCATOR_ID_GENERATOR",
                    strategy = GenerationType.TABLE)
    @Id
    @TableGenerator(initialValue = Pkv.INITIAL_VALUE,
                    name = "STORAGE_LOCATOR_ID_GENERATOR",
                    pkColumnName = Pkv.PK_COLUMN_NAME,
                    pkColumnValue = "STORAGE_LOCATOR_ID",
                    table = Pkv.TABLE,
                    valueColumnName = Pkv.VALUE_COLUMN_NAME)
    @NotNull
    @XmlAttribute
    private Long id;


}
