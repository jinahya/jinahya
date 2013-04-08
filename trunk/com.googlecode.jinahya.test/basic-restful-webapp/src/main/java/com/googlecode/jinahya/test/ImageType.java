

package com.googlecode.jinahya.test;


import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class ImageType extends ImageInfo {


    private static final long serialVersionUID = 6573097485181578355L;


    public static ImageType newInstance(final boolean canRead,
                                        final boolean canWrite,
                                        final String value) {

        return newInstance(ImageType.class, canRead, canWrite, value);
    }


}

