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
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = StorageConsumer.NQ_COUNT,
                query = "SELECT COUNT(c) FROM StorageConsumer AS c"),
    @NamedQuery(name = StorageConsumer.NQ_LIST,
                query = "SELECT c FROM StorageConsumer AS c"
                        + " ORDER BY c.createdAt ASC, c.id ASC")
})
@Table(name = "STORAGE_CONSUMER")
@XmlRootElement
public class StorageConsumer {


    public static final String NQ_COUNT = "StorageConsumer.NQ_COUNT";


    public static final String NQ_LIST = "StorageConsumer.NQ_LIST";


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(StorageConsumer.class.getName());


    // -------------------------------------------------------------- CREATED_AT
    /**
     * Returns createdAt.
     *
     * @return createdAt.
     */
    public Date getCreatedMillis() {

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


    // ------------------------------------------------------ STORAGE_REFERENCE1
    public StorageReference getStorageReference1() {

        return storageReference1;
    }


    public void setStorageReference1(final StorageReference storageReference1) {

        this.storageReference1 = storageReference1;
    }


    @Transient
    @XmlAttribute
    public Long getStorageReference1Id() {

        return storageReference1 == null ? null : storageReference1.getId();
    }


    // ------------------------------------------------------ STORAGE_REFERENCE2
    public StorageReference getStorageReference2() {

        return storageReference2;
    }


    public void setStorageReference2(final StorageReference storageReference2) {

        this.storageReference2 = storageReference2;
    }


    @Transient
    @XmlAttribute
    public Long getStorageReference2Id() {

        return storageReference2 == null ? null : storageReference2.getId();
    }


    // ------------------------------------------------------------- @PrePersist
    @PrePersist
    protected void _PrePersist() {

        createdAt = new Date();
    }


    @Override
    public String toString() {
        return super.toString()
               + "?createdAt=" + String.valueOf(createdAt)
               + "&id=" + String.valueOf(id)
               + "&storageReference1=" + String.valueOf(storageReference1)
               + "&storageReference2=" + String.valueOf(storageReference2);
    }


    public String toXml() throws JAXBException, IOException {

        final JAXBContext context =
            JAXBContext.newInstance(StorageConsumer.class);

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
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @NotNull
    @XmlAttribute
    private Date createdAt;


    /**
     * id.
     */
    @Column(name = "ID", nullable = false, updatable = false)
    @GeneratedValue(generator = "STORAGE_CONSUMER_ID_GENERATOR",
                    strategy = GenerationType.TABLE)
    @Id
    @TableGenerator(initialValue = Pkv.INITIAL_VALUE,
                    name = "STORAGE_CONSUMER_ID_GENERATOR",
                    pkColumnName = Pkv.PK_COLUMN_NAME,
                    pkColumnValue = "STORAGE_CONSUMER_ID",
                    table = Pkv.TABLE,
                    valueColumnName = Pkv.VALUE_COLUMN_NAME)
    @NotNull
    @XmlAttribute
    private Long id;


    /**
     * storage reference 1.
     */
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "STORAGE_REFERENCE1")
    @XmlTransient
    private StorageReference storageReference1;


    /**
     * storage reference 2.
     */
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "STORAGE_REFERENCE2")
    @XmlTransient
    private StorageReference storageReference2;


}
