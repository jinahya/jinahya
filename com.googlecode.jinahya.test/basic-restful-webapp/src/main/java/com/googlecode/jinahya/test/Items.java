

package com.googlecode.jinahya.test;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(namespace = "http://jinahya.googlecode.com/test")
public class Items implements Serializable {


    private static final long serialVersionUID = 5775071328874654134L;


    public Collection<Item> getItems() {

        if (items == null) {
            items = new ArrayList<>();
        }

        return items;
    }


    @XmlElement(name = "item")
    private Collection<Item> items;


}

