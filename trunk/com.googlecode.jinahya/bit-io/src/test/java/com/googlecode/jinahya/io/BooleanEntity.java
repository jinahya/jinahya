/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.io;


import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BooleanEntity extends SingleValueEntity<Boolean> {


    public BooleanEntity() {
        super();

        value = ThreadLocalRandom.current().nextBoolean();
    }


    @Override
    public void read(final BitInput input) throws IOException {

        value = Boolean.valueOf(input.readBoolean());
        System.out.println("read.value: " + value);
    }


    @Override
    public void write(final BitOutput output) throws IOException {

        System.out.println("write.value: " + value);
        output.writeBoolean(value.booleanValue());
    }


}

