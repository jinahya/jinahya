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


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ProcessorChainTest {


    /**
     *
     */
    private static class EchoProcessor extends Processor<Object> {


        /**
         * 
         * @param id
         * @param prerequisites
         */
        public EchoProcessor(final String id, final String... prerequisites) {
            super(id, prerequisites);
        }


        /**
         * 
         * @param unit
         * @throws ProcessorException
         */
        @Override
        public void process(final Object unit) throws ProcessorException {
            System.out.println("processing " + getId());
        }
    }


    @Test
    public void testAdd() {

        final ProcessorChain<Object> chain = new ProcessorChain<Object>();

        final String id = "A";

        final Processor<Object> processor =
            new Processor<Object>(id, new String[] {"B", "C"}) {
                @Override
                public void process(final Object unit) {
                }
            };

        chain.add(processor);

        Assert.assertEquals(chain.hasProcessor(id), true);

        Assert.assertEquals(chain.remove(id), processor);

        Assert.assertEquals(chain.hasProcessor(id), false);
    }


    @Test
    public void testHasProcessor() {

        final ProcessorChain<Object> chain = new ProcessorChain<Object>();

        final String id = "A";

        final Processor<Object> processor =
            new Processor<Object>(id, new String[] {"B", "C"}) {
                @Override
                public void process(final Object unit) {
                }
            };

        Assert.assertEquals(chain.hasProcessor(id), false);

        Assert.assertNull(chain.add(processor));

        Assert.assertEquals(chain.hasProcessor(id), true);
    }


    @Test
    public void testRemoveProcessor() {

        final ProcessorChain<Object> chain = new ProcessorChain<Object>();

        final String id = "A";

        final Processor<Object> processor =
            new Processor<Object>(id, new String[] {"B", "C"}) {
                @Override
                public void process(final Object unit) {
                }
            };

        Assert.assertEquals(chain.hasProcessor(id), false);

        Assert.assertNull(chain.remove(id));

        chain.add(processor);

        Assert.assertEquals(chain.hasProcessor(id), true);

        Assert.assertEquals(chain.remove(id), processor);
    }


    @Test
    public void testInvoke() throws ProcessorException {

        final ProcessorChain<Object> chain = new ProcessorChain<Object>();

        chain.add(new EchoProcessor("A", "B", "C", "D"));

        chain.add(new EchoProcessor("B", "D"));

        chain.add(new EchoProcessor("C", "D", "E", "I"));

        chain.add(new EchoProcessor("E", "F"));

        chain.add(new EchoProcessor("I", "F"));

        chain.add(new EchoProcessor("D"));

        chain.add(new EchoProcessor("F"));

        chain.invoke("unit");
    }
}
