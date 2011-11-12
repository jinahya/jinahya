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


package com.googlecode.jinahya.xmlpull.xs;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Abstract XSTypeAdapter for temporal types.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class XSTemporalAdapter implements XSTypeAdapter<Date> {


    /**
     * Creates a list of DateFormats with given <code>patterns</code>.
     *
     * @param patterns data format patterns
     * @return a list of DataFormats.
     */
    protected static List<DateFormat> createFormats(final String[] patterns) {

        if (patterns == null) {
            throw new NullPointerException("null patterns");
        }

        if (patterns.length == 0) {
            throw new IllegalArgumentException("empty patterns");
        }

        final List<DateFormat> formats =
            new ArrayList<DateFormat>(patterns.length);
        for (String pattern : patterns) {
            try {
                formats.add(new SimpleDateFormat(pattern));
            } catch (IllegalArgumentException ie) {
                //ie.printStackTrace(System.err);
            }
        }

        return formats;
    }


    /**
     * Parses a XML Schema temporal value.
     *
     * @param formats formats to use
     * @param string string to be parsed
     * @return a Date representation
     */
    static Date parseXSTemporal(final List<DateFormat> formats,
                                final String string) {

        if (formats == null) {
            throw new NullPointerException("null formats");
        }

        if (string == null) {
            throw new NullPointerException("null value");
        }

        for (DateFormat format : formats) {
            try {
                synchronized (format) {
                    return format.parse(string);
                }
            } catch (ParseException pe) {
                //pe.printStackTrace(System.err);
            }
        }

        throw new IllegalArgumentException("failed to parse from " + string);
    }


    /**
     * Serializes given <code>value</code> with specified <code>formats</code>.
     *
     * @param formats formats
     * @param value value
     * @return String representation
     */
    static String serializeXSTemporal(final List<DateFormat> formats,
                                      final Date value) {

        if (formats == null) {
            throw new NullPointerException("null formats");
        }

        if (value == null) {
            throw new NullPointerException("null value");
        }

        for (DateFormat format : formats) {
            synchronized (format) {
                return format.format(value);
            }
        }

        throw new IllegalArgumentException("failed to serialize from " + value);
    }


}

