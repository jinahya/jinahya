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


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ConcurrentProcessorChain<T> extends ProcessorChain<T> {


    /**
     * 
     * @param type
     */
    public ConcurrentProcessorChain(final Class<T> type, final int size) {
        super(type);

        if (size <= 0) {
            throw new IllegalArgumentException(
                "param:1:" + int.class + ":" + size + " <= 0");
        }

        this.size = size;

        throwns = new Vector<ProcessorException>();
    }


    @Override
    protected void invoke(final Vector<Processor[]> processorGroups,
                          final T unit)
        throws ProcessorException {

        final Thread[] threads = new Thread[size];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    Processor[] processorGroup;
                    while (true) {
                        synchronized (processorGroups) {
                            if (processorGroups.isEmpty()) {
                                return;
                            }
                            processorGroup = processorGroups.elementAt(0);
                            processorGroups.removeElementAt(0);
                        }
                        for (Processor processor : processorGroup) {
                            try {
                                processor.process(unit);
                            } catch (final ProcessorException pe) {
                                addThrown(pe);
                            }
                        }
                    }
                }
            };
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (int i = threads.length - 1; i >= 0; i--) {
            try {
                threads[i].join();
            } catch (InterruptedException ie) {
                throw new ProcessorException(ie);
            }
        }
    }


    protected void addThrown(final ProcessorException thrown) {

        if (thrown == null) {
            throw new IllegalArgumentException(
                "param:0:" + ProcessorException.class + ": is null");
        }

        throwns.addElement(thrown);
    }


    public ProcessorException[] getThrowns() {
        synchronized (throwns) {
            return throwns.toArray(new ProcessorException[throwns.size()]);
        }
    }


    void setSize(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(
                "param:0:" + int.class + ":" + size + " <= 0");
        }
        this.size = size;
    }


    //private final int size;
    private int size;

    private final transient Vector<ProcessorException> throwns;
}
