

package com.googlecode.jinahya.test;


import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"name", "stock"})
public class Item implements Serializable {


    private static final long serialVersionUID = -3104855615755133457L;


    private static final AtomicLong ATOMIC_ID = new AtomicLong();


    public static Item newInstance(final String name, final int stock) {

        final Item instance = new Item();

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


    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + hashCode()
               + "?id=" + id
               + "&name=" + name
               + "&stock=" + stock;
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
    @XmlAttribute
    public Long getId() {
        return id;
    }


    @XmlAttribute
    protected Date claimedAt;


    protected Date createdAt;


    protected Date updatedAt;


    protected Long id;


    @XmlElement(required = true)
    protected String name;


    @XmlElement(required = true)
    protected int stock;


    private double hidden1 = Math.PI;


    @XmlTransient
    private double hidden2 = Math.PI;


}

