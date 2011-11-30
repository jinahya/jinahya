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


package com.googlecode.jinahya.jdbc.realm.persistence;


import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class Constants {


    /**
     * Target XML namesapce name.
     */
    public static final String TARGET_NAMESPACE =
        "http://jinahya.googlecode.com/jdbc/realm/persistence";


    /**
     * The JAXBContext.
     */
    public static final JAXBContext JAXB_CONTEXT;


    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(
                Constants.class.getPackage().getName());
        } catch (JAXBException jaxbe) {
            throw new InstantiationError(jaxbe.getMessage());
        }
    }


    public static final void generateSchema(final OutputStream outputStream,
                                            final String systemId)
        throws IOException {

        final Result output = new StreamResult(outputStream) {


            @Override
            public String getSystemId() {
                return systemId;
            }


        };

        generateSchema(output);
    }


    public static final void generateSchema(final Result output)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("null output");
        }

        final SchemaOutputResolver outputResolver = new SchemaOutputResolver() {


            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                throws IOException {

                System.out.println("namespaceUri: " + namespaceUri);
                System.out.println("suggestedFileName: " + suggestedFileName);
                
                return output;
            }


        };

        JAXB_CONTEXT.generateSchema(outputResolver);
    }


    /**
     * Creates a new instance.
     */
    private Constants() {
        super();
    }


}

