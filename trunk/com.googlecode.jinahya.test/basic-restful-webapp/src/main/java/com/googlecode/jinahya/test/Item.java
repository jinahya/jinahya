

package com.googlecode.jinahya.test;


import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = "ITEM")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(namespace = "http://jinahya.googlecode.com/test")
@XmlType(propOrder = {"name", "stock"})
public class Item implements Serializable {


    private static final long serialVersionUID = -3104855615755133457L;


    private static final AtomicLong ATOMIC_ID = new AtomicLong();


    public static Item newInstance(final String name, final int stock) {

        final Item instance = new Item();

        putCreatedAtAndId(instance);

        instance.name = name;
        instance.stock = stock;

        return instance;
    }


    public static Item putCreatedAtAndId(final Item item) {

        item.createdAt = new Date();
        item.id = ATOMIC_ID.getAndIncrement();

        return item;
    }


    protected Item() {
        super();
    }


    // -------------------------------------------------------------- CREATED_AT
    @XmlAttribute
    public Date getCreatedAt() {
        return createdAt;
    }


    // -------------------------------------------------------------- UPDATED_AT
    @XmlAttribute
    public Date getUpdatedAt() {
        return updatedAt;
    }


    // ---------------------------------------------------------------------- ID
    public Long getId() {
        return id;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createdAt;


    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;


    @Id
    private Long id;


    @Basic(optional = false)
    @Column(name = "NAME", nullable = false)
    @NotNull
    @Size(min = 0, max = 255)
    @XmlElement(required = true, nillable = true)
    protected String name;


    @Basic(optional = false)
    @Column(name = "STOCK", nullable = false)
    @Min(0)
    @Max(99)
    @XmlElement(required = true, nillable = true)
    protected int stock;


}

