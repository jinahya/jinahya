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


package com.googlecode.jinahya.sql;


import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class JDXBTest {


    private static final JAXBContext JAXB_CONTEXT;


    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(
                "com.googlecode.jinahya.sql");
        } catch (JAXBException jaxbe) {
            throw new InstantiationError(jaxbe.getMessage());
        }
    }


    @Test
    public void printSchema() throws IOException {

        final SchemaOutputResolver resolver = new SchemaOutputResolver() {


            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                throws IOException {

                return new StreamResult(System.out) {


                    public String getSystemId() {
                        return "System.out";
                    }


                };
            }


        };

        
        JAXB_CONTEXT.generateSchema(resolver);
    }


}

