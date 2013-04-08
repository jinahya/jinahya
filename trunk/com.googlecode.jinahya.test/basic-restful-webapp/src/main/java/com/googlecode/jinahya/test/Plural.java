

package com.googlecode.jinahya.test;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public class Plural<S> implements Serializable {


    private static final long serialVersionUID = -8729038027664361663L;


    @XmlAttribute
    private Boolean isEmpty() {
        return getSingulars().isEmpty() ? Boolean.TRUE : null;
    }


    @XmlAnyElement(lax = true)
    public Collection<S> getSingulars() {

        if (singulars == null) {
            singulars = new ArrayList<>();
        }

        return singulars;
    }


    private Collection<S> singulars;


}

