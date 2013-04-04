

package com.googlecode.jinahya.test;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class Items implements Serializable {


    private static final long serialVersionUID = 5775071328874654134L;


    public Collection<Item> getItem() {

        if (item == null) {
            item = new ArrayList<>();
        }

        return item;
    }


    @XmlElement
    private Collection<Item> item;


}

