/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.googlecode.jinahya.test;


import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class StringLongAdapter extends XmlAdapter<String, Long> {


    @Override
    public String marshal(final Long bound) throws Exception {
        return DatatypeConverter.printLong(bound);

    }


    @Override
    public Long unmarshal(final String value) throws Exception {
        return DatatypeConverter.parseLong(value);
    }


}

