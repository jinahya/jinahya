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

package jinahya.util.processor;


import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import jinahya.util.logging.VerySimpleFormatter;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ProcessorChainTest {


    @Test
    public void testAddProcessor() {

        final ProcessorChain<EchoProcessingUnit> chain =
            new FlattenProcessorChain<EchoProcessingUnit>();

        final String id = "A";

        final Processor<EchoProcessingUnit> processor =
            new EchoProcessor(id, "B", "C");

        chain.addProcessor(processor);

        Assert.assertEquals(chain.hasProcessor(id), true);

        Assert.assertEquals(chain.removeProcessor(id), processor);

        Assert.assertEquals(chain.hasProcessor(id), false);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddProcessorWithNull() {
        new FlattenProcessorChain<EchoProcessingUnit>().addProcessor(null);
    }


    @Test
    public void testHasProcessor() {

        final ProcessorChain<EchoProcessingUnit> chain =
            new FlattenProcessorChain<EchoProcessingUnit>();

        final String id = "A";

        final Processor<EchoProcessingUnit> processor =
            new EchoProcessor(id, "B", "C");

        Assert.assertEquals(chain.hasProcessor(id), false);

        Assert.assertNull(chain.addProcessor(processor));

        Assert.assertEquals(chain.hasProcessor(id), true);

        Assert.assertNotNull(chain.addProcessor(processor));
    }


    @Test
    public void testRemoveProcessor() {

        final ProcessorChain<EchoProcessingUnit> chain =
            new FlattenProcessorChain<EchoProcessingUnit>();

        final String id = "A";

        final Processor<EchoProcessingUnit> processor =
            new EchoProcessor(id, "B", "C");

        Assert.assertEquals(chain.hasProcessor(id), false);

        Assert.assertNull(chain.removeProcessor(id));

        chain.addProcessor(processor);

        Assert.assertEquals(chain.hasProcessor(id), true);

        Assert.assertEquals(chain.removeProcessor(id), processor);
    }


    @Test
    public void testInvoke() throws ProcessorException {

        final ProcessorChain<EchoProcessingUnit> chain =
            new FlattenProcessorChain<EchoProcessingUnit>();

        chain.addProcessor(new EchoProcessor("A", "B", "C", "D"));

        chain.addProcessor(new EchoProcessor("B", "D"));

        chain.addProcessor(new EchoProcessor("C", "D", "E", "I"));

        chain.addProcessor(new EchoProcessor("E", "F"));

        chain.addProcessor(new EchoProcessor("I", "F"));

        chain.addProcessor(new EchoProcessor("D"));

        chain.addProcessor(new EchoProcessor("F"));

        chain.invoke(new EchoProcessingUnit());
    }


    @Test
    public void testClear() {

        final ProcessorChain<EchoProcessingUnit> chain =
            new FlattenProcessorChain<EchoProcessingUnit>();

        chain.clear();
    }
}

