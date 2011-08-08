/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.xml;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <D> document type parameter
 */
public abstract class FamilyTest<D> {


    private static final byte[] XML;


    static {

        final InputStream input =
            FamilyTest.class.getResourceAsStream("family.xml");
        if (input == null) {
            throw new InstantiationError("no resource for 'family.xml'");
        }
        try {
            try {
                final ByteArrayOutputStream output =
                    new ByteArrayOutputStream();
                final byte[] buffer = new byte[1024];
                for (int read = -1; (read = input.read(buffer)) != -1;) {
                    output.write(buffer, 0, read);
                }
                output.flush();
                XML = output.toByteArray();
            } finally {
                input.close();
            }
        } catch (IOException ioe) {
            throw new InstantiationError(ioe.getMessage());
        }
    }


    protected abstract D parseDocument(InputStream source, String charsetName)
        throws Exception;


    protected abstract ElementLocator parseLocator(D document) throws Exception;


    protected abstract void printLocator(ElementLocator locator, D document)
        throws Exception;


    protected abstract void printDocument(D document, OutputStream target,
                                          String charsetName)
        throws Exception;


    @Test
    public void test() throws Exception {

        final D document = parseDocument(
            new ByteArrayInputStream(XML), "UTF-8");
        final ElementLocator locator = parseLocator(document);

        Family.doFamilyXML(locator);

        printLocator(locator, document);

        printDocument(document, System.out, "UTF-8");
    }
}

