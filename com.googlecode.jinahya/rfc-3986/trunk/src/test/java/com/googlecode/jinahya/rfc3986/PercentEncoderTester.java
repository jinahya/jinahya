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


package com.googlecode.jinahya.rfc3986;


import java.io.IOException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentEncoderTester {


    public static void test(final PercentEncoder encoder) throws IOException {
        new PercentEncoderTester((encoder)).test();
    }


    protected PercentEncoderTester(final PercentEncoder encoder) {
        super();

        if (encoder == null) {
            throw new NullPointerException("null encoder");
        }

        this.encoder = encoder;
    }


    protected void test() throws IOException {
    }


    private final PercentEncoder encoder;
}

