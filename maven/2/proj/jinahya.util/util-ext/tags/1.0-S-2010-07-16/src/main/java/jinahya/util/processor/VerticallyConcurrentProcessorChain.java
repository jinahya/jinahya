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


import java.util.Vector;

import jinahya.util.DependencyResolver;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class VerticallyConcurrentProcessorChain<U extends ProcessingUnit>
    extends ConcurrentProcessorChain<U> {


    /**
     *
     */
    private static class VerticalSequencer implements Sequencer {

        @Override
        public Vector<Vector<String>> generate(
            final DependencyResolver<String> resolver) {

            return resolver.getVerticalGroups();
        }
    }


    /**
     *
     * @param type
     * @param size
     */
    public VerticallyConcurrentProcessorChain(final int size) {
        super(new VerticalSequencer(), size);
    }


    @Override
    protected void invoke(final Vector<Vector<Processor<U>>> groups,
                          final U unit)
        throws ProcessorException {

        for (int i = 0; i < groups.size(); i++) {
            final Vector<Processor<U>> group = groups.elementAt(i);

            final Thread[] threads = new Thread[getSize()];

            // ------------------------------------------------------------ init
            for (int j = 0; j < threads.length; j++) {
                threads[j] = new Thread() {
                    @Override
                    public void run() {
                        Processor<U> processor;
                        while (true) {
                            synchronized (group) {
                                if (group.isEmpty()) {
                                    return;
                                }
                                processor = group.elementAt(0);
                                group.removeElementAt(0);
                            }
                            try {
                                processor.process(unit);
                            } catch (final ProcessorException pe) {
                                addThrown(pe);
                            }
                        }
                    }
                };
            }

            // ----------------------------------------------------------- start
            for (Thread thread : threads) {
                thread.start();
            }

            // ------------------------------------------------------------ join
            InterruptedException interruptedException = null;
            for (int j = threads.length - 1; j >= 0; j--) {
                try {
                    threads[j].join();
                } catch (InterruptedException ie) {
                    if (interruptedException == null) {
                        interruptedException = ie;
                    }
                }
            }
            if (interruptedException != null) {
                throw new ProcessorException(interruptedException);
            }


            final ProcessorException[] thrown = getThrowns();
            if (thrown.length > 0) {
                throw thrown[0];
            }
            
        }
    }
}
