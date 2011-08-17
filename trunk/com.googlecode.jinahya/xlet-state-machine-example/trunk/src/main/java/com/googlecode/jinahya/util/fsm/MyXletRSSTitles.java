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
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class MyXletRSSTitles {


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
     */
    public final void update() throws IOException {

        synchronized (titles) {
            titles.clear();
            // update
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

