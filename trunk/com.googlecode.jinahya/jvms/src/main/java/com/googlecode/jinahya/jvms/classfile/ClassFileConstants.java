/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.jvms.classfile;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ClassFileConstants {


    public static final JAXBContext JAXB_CONSTEXT;


    static {
        try {
            JAXB_CONSTEXT = JAXBContext.newInstance(
                "com.googlecode.jinahya.jvms.classfile"
                + ":com.googlecode.jinahya.jvms.classfile.attribute"
                + ":com.googlecode.jinahya.jvms.classfile.attribute.stackmap"
                + ":com.googlecode.jinahya.jvms.classfile.constant");
        } catch (JAXBException jaxbe) {
            jaxbe.printStackTrace(System.err);
            throw new InstantiationError(jaxbe.getMessage());
        }
    }


    private ClassFileConstants() {
        super();
    }


}

