

package com.googlecode.jinahya.test;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class ImageSuffix extends ImageInfo {


    private static final long serialVersionUID = 974384781915801391L;


    public static ImageSuffix newInstance(final boolean canRead,
                                          final boolean canWrite,
                                          final String value) {

        return newInstance(ImageSuffix.class, canRead, canWrite, value);
    }


    protected ImageSuffix() {
        super();
    }


    private double hidden1 = Math.E;


    @XmlTransient
    private double hidden2 = Math.PI;


}

