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
 *  under the License.
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


    private static final Logger LOGGER =
        Logger.getLogger(ProcessorChainTest.class.getPackage().getName());


    static {
        LOGGER.setUseParentHandlers(false);
        final Handler handler = new ConsoleHandler();
        handler.setFormatter(new VerySimpleFormatter());
        LOGGER.addHandler(handler);
    }


    /**
     *
     */
    private static class EchoProcessor extends Processor<String> {


        /**
         * 
         * @param id
         * @param prerequisites
         */
        public EchoProcessor(final String id, final String... prerequisites) {
            super(String.class, id, prerequisites);
        }


        /**
         * 
         * @param unit
         * @throws ProcessorException
         */
        @Override
        public void process(final String unit) throws ProcessorException {
            System.out.println(getId() + ": processing " + unit);
        }
    }


    @Test
    public void testAdd() {

        final ProcessorChain<String> chain =
            new ProcessorChain<String>(String.class);

        final String id = "A";

        final Processor<String> processor = new EchoProcessor(id, "B", "C");

        chain.add(processor);

        Assert.assertEquals(chain.hasProcessor(id), true);

        Assert.assertEquals(chain.remove(id), processor);

        Assert.assertEquals(chain.hasProcessor(id), false);
    }


    @Test
    public void testHasProcessor() {

        final ProcessorChain<String> chain =
            new ProcessorChain<String>(String.class);

        final String id = "A";

        final Processor<String> processor = new EchoProcessor(id, "B", "C");

        Assert.assertEquals(chain.hasProcessor(id), false);

        Assert.assertNull(chain.add(processor));

        Assert.assertEquals(chain.hasProcessor(id), true);
    }


    @Test
    public void testRemoveProcessor() {

        final ProcessorChain<String> chain =
            new ProcessorChain<String>(String.class);

        final String id = "A";

        final EchoProcessor processor = new EchoProcessor(id, "B", "C");

        Assert.assertEquals(chain.hasProcessor(id), false);

        Assert.assertNull(chain.remove(id));

        chain.add(processor);

        Assert.assertEquals(chain.hasProcessor(id), true);

        Assert.assertEquals(chain.remove(id), processor);
    }


    @Test
    public void testInvoke() throws ProcessorException {

        final ProcessorChain<String> chain =
            new ProcessorChain<String>(String.class);

        chain.add(new EchoProcessor("A", "B", "C", "D"));

        chain.add(new EchoProcessor("B", "D"));

        chain.add(new EchoProcessor("C", "D", "E", "I"));

        chain.add(new EchoProcessor("E", "F"));

        chain.add(new EchoProcessor("I", "F"));

        chain.add(new EchoProcessor("D"));

        chain.add(new EchoProcessor("F"));

        chain.invoke("unit");
    }


    @Test
    public void testClear() {
        LOGGER.info("LOGGING...................");
    }
}

