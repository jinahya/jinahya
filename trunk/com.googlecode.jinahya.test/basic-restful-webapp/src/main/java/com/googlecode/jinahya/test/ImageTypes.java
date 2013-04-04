

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
public class ImageTypes implements Serializable {


    private static final long serialVersionUID = -2400207807647247615L;


    @XmlAttribute
    public Boolean isEmpty() {
        return getImageType().isEmpty() ? Boolean.TRUE : null;
    }


    @XmlElement(name = "imageType")
    private List<ImageType> getImageTypes() {
        return new ArrayList<>(getImageType().values());
    }


    public Map<String, ImageType> getImageType() {

        if (imageType == null) {
            imageType = new HashMap<>();
        }

        return imageType;
    }


    private Map<String, ImageType> imageType;


}

