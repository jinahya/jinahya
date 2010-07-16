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
public class HorizontallyConcurrentProcessorChain<U extends ProcessingUnit>
    extends ConcurrentProcessorChain<U> {


    /**
     *
     */
    private static class HorizontalSequencer implements Sequencer {

        @Override
        public Vector<Vector<String>> generate(
            final DependencyResolver<String> resolver) {

            return resolver.getHorizontalGroups();
        }
    }


    /**
     *
     * @param type
     * @param size
     */
    public HorizontallyConcurrentProcessorChain(final int size) {
        super(new HorizontalSequencer(), size);
    }


    @Override
    protected void invoke(final Vector<Vector<Processor<U>>> groups,
                          final U unit)
        throws ProcessorException {

        final Thread[] threads = new Thread[getSize()];

        // ---------------------------------------------------------------- init
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    Vector<Processor<U>> group;
                    while (true) {
                        synchronized (groups) {
                            if (groups.isEmpty()) {
                                return;
                            }
                            group = groups.elementAt(0);
                            groups.removeElementAt(0);
                        }
                        for (int i = 0; i < group.size(); i++) {
                            try {
                                group.elementAt(i).process(unit);
                            } catch (final ProcessorException pe) {
                                addThrown(pe);
                            }
                        }
                    }
                }
            };
        }

        // --------------------------------------------------------------- start
        for (Thread thread : threads) {
            thread.start();
        }

        // ---f------------------------------------------------------------ join
        InterruptedException interruptedException = null;
        for (int i = threads.length - 1; i >= 0; i--) {
            try {
                threads[i].join();
            } catch (InterruptedException ie) {
                if (interruptedException == null) {
                    interruptedException = ie;
                }
            }
        }
        if (interruptedException != null) {
            throw new ProcessorException(interruptedException);
        }
    }
}
