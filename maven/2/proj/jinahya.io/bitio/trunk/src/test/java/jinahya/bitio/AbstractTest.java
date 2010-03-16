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


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;



/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class AbstractTest {


    protected static final int COUNT = 1024;

    protected static final Random RANDOM = new Random();


    protected static DataInputStream dis;
    protected static DataOutputStream dos;

    private static PipedInputStream pis;
    private static PipedOutputStream pos;

    protected static BitInput input;
    protected static BitOutput output;


    @BeforeClass
    public static void openStreamsBeforeClass() throws IOException {

        pis = new PipedInputStream(1048576);
        dis = new DataInputStream(pis);
        input = new BitInput(pis);

        pos = new PipedOutputStream();
        dos = new DataOutputStream(pos);
        output = new BitOutput(pos);

        pis.connect(pos);
    }


    @Before
    public void alignBitIOBeforeTest() throws IOException {
        //System.out.println("output aligned with " + output.align() + " bits");
        //System.out.println("input aligned with " + input.align() + " bits");
        output.align();
        input.align();
    }


    protected final void alignAndFlush() throws IOException {
        //System.out.println("output aligned with " + output.align() + " bits");
        output.align();

        dos.flush();
        pos.flush();
    }


    @AfterClass
    public static void closeStreamsAfterClass() throws IOException {
        pis.close();
        dis.close();
        pos.close();
        dos.close();
    }
}
