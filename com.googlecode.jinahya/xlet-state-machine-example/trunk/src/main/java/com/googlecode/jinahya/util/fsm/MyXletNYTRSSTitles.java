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


import java.net.MalformedURLException;
import java.net.URL;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class MyXletNYTRSSTitles extends MyXletRSSTitles {


    private static final URL URL;


    static {
        try {
            URL = new URL(
                "http://www.nytimes.com/services/xml/rss/nyt/GlobalHome.xml");
        } catch (MalformedURLException murle) {
            throw new InstantiationError(murle.getMessage());
        }
    }


    /** instance holder. */
    private static class InstanceHolder {


        /** instance. */
        private static final MyXletRSSTitles INSTANCE =
            new MyXletNYTRSSTitles(URL);
    }


    /**
     * Returns the instance.
     *
     * @return instance.
     */
    public static MyXletRSSTitles getInstance() {
        return InstanceHolder.INSTANCE;
    }


    /** private. */
    private MyXletNYTRSSTitles(final URL url) {
        super(url);
    }
}

