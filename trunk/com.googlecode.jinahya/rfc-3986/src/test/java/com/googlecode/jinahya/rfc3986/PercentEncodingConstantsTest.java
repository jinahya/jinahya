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


package com.googlecode.jinahya.rfc3986;


import java.util.regex.Pattern;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PercentEncodingConstantsTest {


    @Test
    public static void testUNRESERVED_CHARACTER_REGEX() {
        final Pattern pattern = Pattern.compile(
            PercentEncodingConstants.UNRESERVED_REGEX);
        Assert.assertTrue(pattern.matcher("1").matches());
        Assert.assertTrue(pattern.matcher("9").matches());
        Assert.assertTrue(pattern.matcher("a").matches());
        Assert.assertTrue(pattern.matcher("z").matches());
        Assert.assertTrue(pattern.matcher("A").matches());
        Assert.assertTrue(pattern.matcher("Z").matches());
        Assert.assertTrue(pattern.matcher("-").matches());
        Assert.assertTrue(pattern.matcher(".").matches());
        Assert.assertTrue(pattern.matcher("_").matches());
        Assert.assertTrue(pattern.matcher("~").matches());
    }


}

