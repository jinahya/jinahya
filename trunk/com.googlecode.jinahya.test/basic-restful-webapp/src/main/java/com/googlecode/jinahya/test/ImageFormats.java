

package com.googlecode.jinahya.test;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class ImageFormats implements Serializable {


    private static final long serialVersionUID = 6596844099674732717L;


    @XmlAttribute
    public Boolean isEmpty() {
        return getImageFormat().isEmpty() ? Boolean.TRUE : null;
    }


    @XmlElement(name = "imageFormat")
    private List<ImageFormat> getImageFormats() {
        return new ArrayList<>(getImageFormat().values());
    }


    public Map<String, ImageFormat> getImageFormat() {

        if (imageFormat == null) {
            imageFormat = new HashMap<>();
        }

        return imageFormat;
    }


    private Map<String, ImageFormat> imageFormat;


}

