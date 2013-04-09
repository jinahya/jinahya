

package com.googlecode.jinahya.test;


import com.googlecode.jinahya.xml.bind.Plural;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlSeeAlso(ImageSuffix.class)
public class ImageSuffixes extends Plural<ImageSuffix> {


    private static final long serialVersionUID = -6461003798452348964L;


}

