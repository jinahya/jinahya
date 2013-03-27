/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.ws.rs.core;


import java.io.IOException;
import java.io.OutputStream;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SchemaStreamingOutput implements StreamingOutput {


    public SchemaStreamingOutput(final JAXBContext context) {
        super();

        if (context == null) {
            throw new NullPointerException("null context");
        }

        this.context = context;
    }


    @Override
    public void write(final OutputStream output) throws IOException {

        context.generateSchema(new SchemaOutputResolver() {


            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                throws IOException {

                return new StreamResult(output) {


                    @Override
                    public String getSystemId() {
                        return suggestedFileName;
                    }


                };
            }


        });
    }


    private final JAXBContext context;


}

