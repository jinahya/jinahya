

package com.googlecode.jinahya.test;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public abstract class ImageInfo implements Serializable {


    private static final long serialVersionUID = 4856352468042450056L;


    public static <I extends ImageInfo> I newInstance(final Class<I> type,
                                                      final boolean canRead,
                                                      final boolean canWrite,
                                                      final String value) {

        try {
            final I instance = type.newInstance();
            instance.canRead = canRead;
            instance.canWrite = canWrite;
            instance.value = value;
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    protected ImageInfo() {
        super();
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + hashCode()
               + "?canRead=" + canRead
               + "&canWrite=" + canWrite
               + "&value=" + value;
    }


    @XmlAttribute
    protected boolean canRead;


    @XmlAttribute
    protected boolean canWrite;


    @XmlValue
    protected String value;


}

