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


package com.googlecode.jinahya.util.fsm;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class MyXletRSSTitles {


    private static final XmlPullParserFactory FACTORY;


    static {
        try {
            FACTORY = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException xppe) {
            throw new InstantiationError(xppe.getMessage());
        }
    }


    /**
     * 
     * @param url RSS feed URL
     */
    public MyXletRSSTitles(final URL url) {
        super();

        if (url == null) {
            throw new NullPointerException("null url");
        }

        this.url = url;

        titles = Collections.synchronizedList(new ArrayList<String>());
    }


    /**
     * Updates titles.
     *
     * @throws IOException 
     * @throws XmlPullParserException 
     */
    public final void update() throws IOException, XmlPullParserException {

        synchronized (titles) {
            titles.clear();

            final InputStream stream = url.openStream();
            try {
                final XmlPullParser parser = FACTORY.newPullParser();
                parser.setInput(stream, null);

                boolean item = false;
                boolean title = false;

                while (parser.next() != XmlPullParser.END_DOCUMENT) {

                    switch (parser.getEventType()) {
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("item")) {
                                item = true;
                            } else if (parser.getName().equals("title")) {
                                title = true;
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item")) {
                                item = false;
                            } else if (parser.getName().equals("title")) {
                                title = false;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            if (item && title) {
                                titles.add(parser.getText());
                            }
                            break;
                        default:
                            break;
                    }
                }
            } finally {
                stream.close();
            }

            for (int i = 0; i < titles.size(); i++) {
                System.out.println("title[" + i + "]: " + titles.get(i));
            }
        }
    }


    /**
     * Returns titles.
     *
     * @return titles
     */
    public final List<String> getTitles() {

        synchronized (titles) {
            return new ArrayList<String>(titles);
        }
    }


    /** RSS feed URL. */
    private final URL url;


    /** title list. */
    private final List<String> titles;
}

