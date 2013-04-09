

package com.googlecode.jinahya.test;


import com.googlecode.jinahya.xml.bind.Plural;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlSeeAlso(ImageFormat.class)
public class ImageFormats extends Plural<ImageFormat> {


    private static final long serialVersionUID = -6948922813147231705L;


}

