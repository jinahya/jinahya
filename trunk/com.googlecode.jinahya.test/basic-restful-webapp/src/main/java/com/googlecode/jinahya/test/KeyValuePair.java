

package com.googlecode.jinahya.test;


import java.util.Map.Entry;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class KeyValuePair {


    public static KeyValuePair newInstance(final String key,
                                           final String value) {

        final KeyValuePair instance = new KeyValuePair();

        instance.key = key;
        instance.value = value;

        return instance;
    }


    public static KeyValuePair newInstance(final Entry<String, String> entry) {

        return newInstance(entry.getKey(), entry.getValue());
    }


    @XmlAttribute(required = true)
    private String key;


    @XmlValue
    private String value;


}

