/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.awt;


import java.awt.FontMetrics;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class StringChopper {


    /**
     * Calculates offsets.
     *
     * @param string string to be calculated.
     * @param metrics font metrics
     * @param width target width
     * @param max the maximum number of offsets, 0 for unlimited
     * @return an list of offsets
     */
    public static List<Integer> offsets(final String string,
                                        final FontMetrics metrics,
                                        final int width, final int max) {

        if (string == null) {
            throw new IllegalArgumentException("null string");
        }

        if (metrics == null) {
            throw new IllegalArgumentException("null metrics");
        }

        if (width <= 0) {
            throw new IllegalArgumentException(
                "illegal width: " + width + " <= 0");
        }

        if (max < 0) {
            throw new IllegalArgumentException("negative max");
        }

        return offsets(string, metrics, width, new ArrayList<Integer>(), max);
    }


    /**
     * Calculate offsets.
     *
     * @param string string to be chopped
     * @param metrics font metrics
     * @param width target width
     * @param offsets list to which offsets are added
     * @param max the maximum number of offsets, 0 for unlimited
     * @return given <code>offset</code>
     */
    public static List<Integer> offsets(final String string,
                                        final FontMetrics metrics,
                                        final int width,
                                        final List<Integer> offsets,
                                        final int max) {

        if (string == null) {
            throw new IllegalArgumentException("null string");
        }

        if (metrics == null) {
            throw new IllegalArgumentException("null metrics");
        }

        if (width <= 0) {
            throw new IllegalArgumentException(
                "illegal width: " + width + " <= 0");
        }

        if (offsets == null) {
            throw new IllegalArgumentException("null offsets");
        }

        if (max < 0) {
            throw new IllegalArgumentException("negative max");
        }


        /*
        int sum = 0;
        for (int i = 0; i < string.length(); i++) {
            sum += metrics.charWidth(string.charAt(i));
            if (sum > width) {
                if (i == offsets.get(offsets.size() - 1)) {
                    throw new IllegalArgumentException(
                        "illegal width: " + width + " is too small");
                }
                offsets.add(i);
                sum = 0;
            }
        }
         */


        /*
        outer:
        while (true) {

            int last = offsets.get(offsets.size() - 1);
            for (int i = last + 1; i <= string.length(); i++) {
                if (metrics.stringWidth(string.substring(last, i)) > width) {
                    if (i - 1 == last) {
                        throw new IllegalArgumentException(
                            "illegal width: " + width + " is too small");
                    }
                    offsets.add((i - 1));
                    continue outer;
                }
            }

            break;
        }
         */


        /*
        offsets.add(0);
        int sum = 0; // 128
        for (int i = 0; i < string.length(); i++) {
            sum += metrics.charWidth(string.charAt(i));
            if (sum > width) {
                final int l = offsets.get(offsets.size() - 1);
                while (i > l) {
                    if (metrics.stringWidth(string.substring(l, i)) <= width) {
                        break;
                    }
                    i--;
                }
                if (i == offsets.get(offsets.size() - 1)) {
                    throw new IllegalArgumentException(
                        "illegal width: " + width + " is too small");
                }
                offsets.add(i);
                if (max != 0 && offsets.size() == max) {
                    break;
                }
                sum = 0;
            }
        }
         */


        int start = 0;
        int sum = 0;
        for (int i = start; i < string.length(); i++) {
            sum += metrics.charWidth(string.charAt(i));
            if (sum >= width) {
                for (; i > start; i--) {
                    if (metrics.stringWidth(
                        string.substring(start, i + 1)) <= width) {
                        break;
                    }
                }
                if (i == start) {
                    throw new IllegalArgumentException("too small width");
                }
                offsets.add(i + 1);
                if (max != 0 && offsets.size() == max) {
                    break;
                }
                start = i + 1;
                sum = 0;
            }
        }

        if (offsets.isEmpty()) {
            offsets.add(string.length());
        } else {
            if (offsets.get(offsets.size() - 1) != string.length()) {
                if (max == 0) {
                    offsets.add(string.length());
                } else {
                    if (offsets.size() < max) {
                        offsets.add(string.length());
                    }
                }
            }
        }

        return offsets;
    }



    /**
     *
     * @param string string to be chopped
     * @param metrics metrics
     * @param width width
     * @param max the maximum number of chopped strings, 0 for unlimited
     * @return a list of chopped strings
     */
    public static List<String> chop(final String string,
                                    final FontMetrics metrics,
                                    final int width, final int max) {

        if (string == null) {
            throw new IllegalArgumentException("null string");
        }

        if (metrics == null) {
            throw new IllegalArgumentException("null metrics");
        }

        if (width <= 0) {
            throw new IllegalArgumentException(
                "illegal width: " + width + " <= 0");
        }

        if (max < 0) {
            throw new IllegalArgumentException("negative max");
        }

        return chop(string, metrics, width, new ArrayList<String>(), max);
    }


    /**
     * Chop given string.
     *
     * @param string string to be chopped
     * @param metrics the font metrics
     * @param width target width
     * @param max the maximum number of chopped strings, 0 for unlimited
     * @param chopped the list to which chopped strings are added
     * @return given <code>chopped</code>
     */
    public static List<String> chop(final String string,
                                    final FontMetrics metrics, final int width,
                                    final List<String> chopped, final int max) {

        if (string == null) {
            throw new IllegalArgumentException("null string");
        }

        if (metrics == null) {
            throw new IllegalArgumentException("null metrics");
        }

        if (width <= 0) {
            throw new IllegalArgumentException(
                "illegal width: " + width + " <= 0");
        }

        if (chopped == null) {
            throw new IllegalArgumentException("null chopped");
        }

        if (max < 0) {
            throw new IllegalArgumentException("negative max");
        }

        final List<Integer> offsets = offsets(string, metrics, width, max);
        int start = 0;
        for (int i = 0; i < offsets.size(); i++) {
            chopped.add(string.substring(start, offsets.get(i)));
            start = offsets.get(i);
        }

        return chopped;
    }


    /**
     *
     * @param reader reader
     * @param metrics metrics
     * @param width width
     * @param max the maximum number of chopped strings, 0 for unlimited
     * @return a list of chopped strings
     * @throws IOException if an I/O error occurs
     */
    public static List<String> chop(final BufferedReader reader,
                                    final FontMetrics metrics, final int width,
                                    final int max)
        throws IOException {

        if (reader == null) {
            throw new IllegalArgumentException("null reader");
        }

        if (metrics == null) {
            throw new IllegalArgumentException("null metrics");
        }

        if (width <= 0) {
            throw new IllegalArgumentException(
                "illegal width: " + width + " <= 0");
        }

        if (max < 0) {
            throw new IllegalArgumentException("negative max");
        }

        return chop(reader, metrics, width, new ArrayList<String>(), max);
    }


    /**
     *
     * @param reader input
     * @param metrics metrics
     * @param width width
     * @param chopped list for chopped strings
     * @param max maximum number of chopped strings, 0 for unlimited
     * @return given <code>chopped</code>
     * @throws IOException if an I/O error occurs
     */
    public static List<String> chop(final BufferedReader reader,
                                    final FontMetrics metrics, final int width,
                                    final List<String> chopped, final int max)
        throws IOException {

        if (reader == null) {
            throw new IllegalArgumentException("null reader");
        }

        if (chopped == null) {
            throw new IllegalArgumentException("null chopped");
        }

        if (metrics == null) {
            throw new IllegalArgumentException("null metrics");
        }

        if (width <= 0) {
            throw new IllegalArgumentException(
                "illegal width: " + width + " <= 0");
        }

        if (max < 0) {
            throw new IllegalArgumentException("negative max");
        }

        for (String string = null; (string = reader.readLine()) != null;) {
            chop(string, metrics, width, chopped, max);
        }

        return chopped;
    }


    /** private. */
    private StringChopper() {
        super();
    }
}
