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
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = StorageReference.NQ_COUNT,
                query = "SELECT COUNT(r) FROM StorageReference AS r"),
    @NamedQuery(name = StorageReference.NQ_LIST,
                query = "SELECT r FROM StorageReference AS r"
                        + " ORDER BY r.createdAt ASC, r.id ASC")
})
@Table(name = "STORAGE_REFERENCE",
       uniqueConstraints = {
    @UniqueConstraint(columnNames = {"STORAGE_LOCATOR_ID"},
                      name = "UNIQUE_STORAGE_LOCATOR")})
@XmlRootElement
public class StorageReference extends MappedStorageReference<StorageLocator> {


    public static final String NQ_COUNT = "StorageReference.NQ_COUNT";


    public static final String NQ_LIST = "StorageReference.NQ_LIST";


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(StorageReference.class);


    /**
     * Creates a new instance with given {@code storageLocator}.
     *
     * @param storageLocator storageLocator
     *
     * @return a new instance
     */
    public static StorageReference newInstance(
        final StorageLocator storageLocator) {

        return newInstance(StorageReference.class, storageLocator);
    }


    protected StorageReference() {

        super();
    }


    // -------------------------------------------------------------- CREATED_AT
    /**
     * Returns createdAt.
     *
     * @return createdAt.
     */
    public Date getCreatedAt() {

        return createdAt == null ? null : new Date(createdAt.getTime());
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
    /**
     * Returns {@code storageLocator}'s id.
     *
     * @return storageLocator's id.
     */
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

        createdAt = new Date();
    }


    // -------------------------------------------------------------- @PreRemove
    @PreRemove
    protected void _PreRemove() {

        getStorageLocator().setDeletedAt(new Date());
    }


    @Override
    public String toString() {
        return super.toString()
               + "?createdAt=" + createdAt
               + "&id=" + String.valueOf(id)
               + "&storageLocator=" + String.valueOf(getStorageLocator());
    }


    /**
     *
     * @return @throws JAXBException
     * @throws IOException
     */
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
     * createdAt.
     */
    @Basic(optional = false)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @XmlAttribute
    private Date createdAt;


    /**
     * id.
     */
    @Column(name = "ID", nullable = false, updatable = false)
    @GeneratedValue(generator = "STORAGE_REFERENCE_ID_GENERATOR",
                    strategy = GenerationType.TABLE)
    @Id
    @TableGenerator(initialValue = Pkv.INITIAL_VALUE,
                    name = "STORAGE_REFERENCE_ID_GENERATOR",
                    pkColumnName = Pkv.PK_COLUMN_NAME,
                    pkColumnValue = "STORAGE_REFERENCE_ID",
                    table = Pkv.TABLE,
                    valueColumnName = Pkv.VALUE_COLUMN_NAME)
    @NotNull
    @XmlAttribute
    private Long id;


}
