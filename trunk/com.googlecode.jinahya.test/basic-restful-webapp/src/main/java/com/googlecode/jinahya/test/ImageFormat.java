/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.googlecode.jinahya.test;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class ImageFormat {


    @XmlAttribute
    protected boolean canRead;


    @XmlAttribute
    protected boolean canWrite;


    @XmlValue
    protected String name;


}

