/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.epost.openapi;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class EpostOpenApiClientTest {


    private static final String[] QUERIES = new String[]{
        "횡성군",
        "서원면",
        "유현1리",
        "독산1동"
    };


    @BeforeClass()
    public void readKey() {

        authKey = System.getProperty("authKey");
        if (authKey == null) {
            throw new SkipException("no authKey");
        }
    }


    @Test
    public void testCreateContainer()
        throws IOException, ParserConfigurationException, SAXException {

        final EpostOpenApiClient client = new EpostOpenApiClient(authKey);

        final Map<String, String> results = new LinkedHashMap<String, String>();

        for (String query : QUERIES) {
            results.clear();
            System.out.println("query: " + query);
            client.query(query, results);
            for (Entry<String, String> entry : results.entrySet()) {
                System.out.println("\t" + entry.getValue() + " "
                                   + entry.getKey());
            }
        }
    }


    private String authKey;


}

