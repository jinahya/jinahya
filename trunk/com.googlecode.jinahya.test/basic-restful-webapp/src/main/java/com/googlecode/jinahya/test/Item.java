

package com.googlecode.jinahya.test;


import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
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
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(namespace = "http://jinahya.googlecode.com/test")
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


    @XmlAttribute
    public Date getCreatedAt() {
        return createdAt;
    }


    @XmlAttribute
    public Date getUpdatedAt() {
        return updatedAt;
    }


    // ---------------------------------------------------------------------- ID
    @XmlAttribute
    public Long getId() {
        return id;
    }


    private Date createdAt;


    protected Date updatedAt;


    private Long id;


    @XmlElement(required = true, nillable = true)
    protected String name;


    @XmlElement(required = true, nillable = true)
    protected int stock;


}

