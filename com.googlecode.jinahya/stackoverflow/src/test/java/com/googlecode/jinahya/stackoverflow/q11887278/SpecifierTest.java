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


package com.googlecode.jinahya.stackoverflow.q11887278;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SpecifierTest {


    @Test//(invocationCount = 100)
    public void testEquals() {

        final Specifier s1 = new Specifier();
        s1.value1 = "value1";
        s1.value2 = null;
        s1.value3 = null;

        final Specifier s2 = new Specifier();
        s2.fromString("value1,null,null");
        //s2.value1 = "value1";
        //s2.value2 = null;
        //s2.value3 = null;

        Assert.assertEquals(s2, s1);
    }


}

