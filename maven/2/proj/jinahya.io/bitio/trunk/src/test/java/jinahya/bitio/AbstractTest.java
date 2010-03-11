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

package jinahya.bitio;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Random;

import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;



/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class AbstractTest {


    protected static final int COUNT = 100;

    protected static final Random RANDOM = new Random();


    @Before
    public void openStreams() throws IOException {

        pis = new PipedInputStream(1048576);
        input = new BitInput(pis);

        pos = new PipedOutputStream();
        output = new BitOutput(pos);

        pis.connect(pos);
    }


    protected void alignAndFlush() throws IOException {
        output.align();
        pos.flush();
        //System.gc();
    }


    @After
    public void closeStreams() throws IOException {
        pis.close();
        pos.close();
    }


    private PipedInputStream pis;
    private PipedOutputStream pos;

    protected BitInput input;
    protected BitOutput output;
}
