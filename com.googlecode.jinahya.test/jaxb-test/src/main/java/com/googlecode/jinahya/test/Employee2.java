

package com.googlecode.jinahya.test;


import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Employee2 {


    public Long getId() {
        return id;
    }


    public void setId(final Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public Employee2 getManager() {
        return manager;
    }


    public void setManager(final Employee2 manager) {
        this.manager = manager;
    }


    public Collection<Employee2> getSubordinates() {
        if (subordinates == null) {
            subordinates = new ArrayList<Employee2>();
        }
        return subordinates;
    }


    @Id
    @XmlAttribute
    @XmlID
    @XmlJavaTypeAdapter(StringLongAdapter.class)
    private Long id;


    @XmlElement
    private String name;


    @ManyToOne
    @XmlIDREF
    private Employee2 manager;


    @OneToMany(mappedBy = "manager")
    @XmlElement(name = "subordinate")
    @XmlIDREF
    private Collection<Employee2> subordinates;


}

