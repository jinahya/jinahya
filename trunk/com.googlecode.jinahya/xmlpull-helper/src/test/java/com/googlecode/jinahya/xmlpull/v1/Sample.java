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


package com.googlecode.jinahya.xmlpull.v1;


import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Sample implements XmlAccessible {


    public static final String LOCAL_NAME = "sample";


    public Sample() {
        this(null);
    }


    protected Sample(final String namespaceURI) {
        super();

        this.namespaceURI = namespaceURI;
    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, namespaceURI, LOCAL_NAME);

        id = XmlPullParserHelper.getLongAttribute(parser, null, "id");

        parser.nextTag();
        name = XmlPullParserHelper.nextNillable(parser);

        parser.nextTag();
        age = XmlPullParserHelper.nextInt(parser);

        parser.nextTag();
        parser.require(XmlPullParser.END_TAG, namespaceURI, LOCAL_NAME);

    }


    @Override
    public void serialize(final XmlSerializer serializer) throws IOException {

        throw new UnsupportedOperationException("Not supported yet.");
    }


    public Long getId() {
        return id;
    }


    public void setId(final Long id) {

        if (id == null) {
            throw new NullPointerException("null id");
        }

        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public Integer getAge() {
        return age;
    }


    public void setAge(final Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return id + "/" + name + "/" + age;
    }


    private final String namespaceURI;


    private Long id;


    private String name;


    private Integer age;


}

