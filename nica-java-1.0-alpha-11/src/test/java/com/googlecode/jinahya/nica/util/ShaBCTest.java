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


package com.googlecode.jinahya.nica.util;


import org.junit.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ShaBCTest extends ShaTest<ShaBC> {


    @Override
    protected ShaBC create() {
        return new ShaBC();
    }


    @Test
    public void testHashAgainstJCA() {

        final byte[] unhashed = newBytesData();

        final byte[] actual = create().hash(unhashed);

        final byte[] expected = new ShaJCA().hash(unhashed);

        Assert.assertArrayEquals(actual, expected);
    }


    @Test
    public void testHashToStringAgainstJCA() {

        final byte[] unhashed = newBytesData();

        final String actual = create().hashToString(unhashed);

        final String expected = new ShaJCA().hashToString(unhashed);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testHashWithStringAgainstJCA() {

        final String unhashed = newStringData();

        final byte[] actual = create().hash(unhashed);

        final byte[] expected = new ShaJCA().hash(unhashed);

        Assert.assertArrayEquals(actual, expected);
    }


    @Test
    public void testHashToStringWithStringAgainstJCA() {

        final String unhashed = newStringData();

        final String actual = create().hashToString(unhashed);

        final String expected = new ShaJCA().hashToString(unhashed);

        Assert.assertEquals(actual, expected);
    }


}
