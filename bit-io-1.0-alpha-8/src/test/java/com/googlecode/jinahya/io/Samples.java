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


package com.googlecode.jinahya.io;


import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Samples extends AbstractBitCollectable<Sample> {


    public Samples() {
        super(Sample.class);
    }


    @Override
    protected Sample createAccessible() {
        return Sample.newInstance("unknown", 1);
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Samples)) {
            return false;
        }

        final Samples samples = (Samples) obj;

        return getAccessibles().equals(samples.getAccessibles());
    }


    @Override
    public int hashCode() {

        int result = 17;

        result = 37 * result + getAccessibles().hashCode();

        return result;
    }


    public void print(final PrintStream writer) {
        for (Sample sample : getAccessibles()) {
            writer.println(sample);
        }
    }


    public void write(final DataOutput output) throws IOException {
        output.writeInt(getAccessibles().size());
        for (Sample sample : getAccessibles()) {
            sample.write(output);
        }
    }


}

