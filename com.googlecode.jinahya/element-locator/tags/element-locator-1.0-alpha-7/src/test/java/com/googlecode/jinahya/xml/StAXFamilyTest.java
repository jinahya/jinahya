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


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class StAXFamilyTest {


    private static final XMLInputFactory INPUT_FACTORY =
        XMLInputFactory.newInstance();


    private static final XMLOutputFactory OUTPUT_FACTORY =
        XMLOutputFactory.newInstance();


    @Test
    public void test() throws IOException, XMLStreamException {

        final Reader source =
            new InputStreamReader(Family.family(), "UTF-8");
        final XMLStreamReader reader =
            INPUT_FACTORY.createXMLStreamReader(source);

        final ElementLocator locator = StAXElementLocator.parse(reader);

        reader.close();
        source.close();

        Family.family(locator);

        final Writer target = new OutputStreamWriter(System.out, "UTF-8");
        final XMLStreamWriter writer =
            OUTPUT_FACTORY.createXMLStreamWriter(target);

        StAXElementLocator.print(locator, writer);
        writer.flush();

        writer.close();

        target.flush();
        target.close();
    }
}

