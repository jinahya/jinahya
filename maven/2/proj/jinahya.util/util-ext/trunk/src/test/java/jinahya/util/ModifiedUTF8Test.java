/*
 *  Copyright 2010 onacit.
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

package jinahya.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import jinahya.util.ModifiedUTF8.Acceptor;

import static org.testng.Assert.*;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ModifiedUTF8Test {


    private static final Acceptor NON_ISO_CONTROL = new Acceptor() {


        @Override
        public boolean accept(char ch) {
            return !Character.isISOControl(ch);
        }
    };


    @Test(invocationCount = 1024)
    public void testGenerate() throws IOException {
        final PrintStream out = new PrintStream(System.out, true, "UTF-8");
        final Random random = new Random();
        out.println("GENERATED: " + ModifiedUTF8.generate(
            random.nextInt(80) + 1, random, NON_ISO_CONTROL));
    }
}
