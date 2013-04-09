

package com.googlecode.jinahya.test;


import com.googlecode.jinahya.xml.bind.Plural;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlSeeAlso(ImageType.class)
public class ImageTypes extends Plural<ImageType> {


    private static final long serialVersionUID = -6575982090487265149L;


}

