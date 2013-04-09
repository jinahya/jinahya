

package com.googlecode.jinahya.test;


import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class ImageFormat extends ImageInfo {


    private static final long serialVersionUID = -7398103404241304565L;


    public static ImageFormat newInstance(final boolean canRead,
                                          final boolean canWriter,
                                          final String value) {

        return newInstance(ImageFormat.class, canRead, canWriter, value);
    }


}

