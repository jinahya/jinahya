

package com.googlecode.jinahya.test;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Items implements Serializable {


    private static final long serialVersionUID = 5775071328874654134L;


    @XmlAttribute
    public Boolean isEmpty() {
        return getItem().isEmpty() ? Boolean.TRUE : null;
    }


    public Collection<Item> getItem() {

        if (item == null) {
            item = new ArrayList<>();
        }

        return item;
    }


    @XmlElement
    private Collection<Item> item;


}

