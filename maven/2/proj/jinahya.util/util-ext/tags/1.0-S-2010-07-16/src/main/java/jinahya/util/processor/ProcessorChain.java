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


import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import jinahya.util.DependencyResolver;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <T> processing unit type
 */
public abstract class ProcessorChain<U extends ProcessingUnit> {


    /**
     * 
     */
    public static interface Sequencer {


        /**
         *
         */
        Vector<Vector<String>> generate(DependencyResolver<String> resolver);
    }


    /**
     *
     */
    public ProcessorChain(final Sequencer sequencer) {

        super();

        if (sequencer == null) {
            throw new IllegalArgumentException(
                "param:0:" + Sequencer.class + ": is null");
        }

        this.sequencer = sequencer;

        processors = new Hashtable<String, Processor<U>>();

        resolver = new DependencyResolver<String>();
    }


    /**
     *
     * @param unit
     * @throws ProcessorException
     */
    public void invoke(final U unit) throws ProcessorException {

        if (unit == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }


        synchronized (processors) {

            for (Processor<U> processor : processors.values()) {
                for (String prerequisiteId : processor.getPrerequisiteIds()) {
                    if (!processors.containsKey(prerequisiteId)) {
                        throw new ProcessorException(
                            "missing processor: " + prerequisiteId);
                    }
                }
            }

            final Vector<Vector<String>> processorIDGroups =
                sequencer.generate(resolver);

            final Vector<Vector<Processor<U>>> processorGroups =
                new Vector<Vector<Processor<U>>>();

            for (int i = 0; i < processorIDGroups.size(); i++) {

                final Vector<String> processorIdGroup =
                    processorIDGroups.elementAt(i);

                final Vector<Processor<U>> processorGroup =
                    new Vector<Processor<U>>();

                for (int j = 0; j < processorIdGroup.size(); j++) {
                    processorGroup.addElement(
                        processors.get(processorIdGroup.elementAt(j)));
                }

                processorGroups.addElement(processorGroup);
            }

            invoke(processorGroups, unit);
        }
    }


    /**
     *
     * @param groups
     * @param unit
     * @throws ProcessorException
     */
    protected abstract void invoke(final Vector<Vector<Processor<U>>> groups,
                                   final U unit)
        throws ProcessorException;


    /**
     *
     * @param processor
     * @return
     * @throws ProcessorException
     */
    public final Processor<U> addProcessor(final Processor<U> processor) {

        if (processor == null) {
            throw new IllegalArgumentException(
                "param:0:" + Processor.class + ": is null");
        }


        synchronized (processors) {

            final Processor<U> removed = removeProcessor(processor.getId());

            resolver.addDependencies(
                processor.getId(), processor.getPrerequisiteIds());

            processors.put(processor.getId(), processor);

            return removed;
        }
    }


    /**
     *
     */
    public final Processor<U> removeProcessor(final String processorId) {

        if (processorId == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        synchronized (processors) {
            final Processor<U> removed = processors.remove(processorId);
            if (removed != null) {
                resolver.removeSource(removed.getId());
            }
            return removed;
        }
    }


    /**
     *
     */
    public final void clear() {
        synchronized (processors) {
            processors.clear();
            resolver.clear();
        }
    }


    /**
     *
     * @param processorId
     * @return
     */
    public final boolean hasProcessor(final String processorId) {

        if (processorId == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        return processors.containsKey(processorId);
    }


    void print(final java.io.PrintStream out) {
        synchronized (processors) {
            final Enumeration<String> keys = processors.keys();
            while (keys.hasMoreElements()) {
                final String key = keys.nextElement();
                out.println(processors.get(key));
            }
        }
    }


    /**
     *
     * @param suppress
     * @return
     * @throws ProcessorException
     */
    public final Vector<String> getRequiredProcessorIds() {

        final Vector<String> requiredProcessorIds = new Vector<String>();

        synchronized (processors) {
            for (Processor<U> processor : processors.values()) {
                for (String prerequisiteId : processor.getPrerequisiteIds()) {
                    if (!processors.containsKey(prerequisiteId)) {
                        if (!requiredProcessorIds.contains(prerequisiteId)) {
                            requiredProcessorIds.add(prerequisiteId);
                        }
                    }
                }
            }
        }

        return requiredProcessorIds;
    }


    private Sequencer sequencer;

    private final Hashtable<String, Processor<U>> processors;

    private final DependencyResolver<String> resolver;
}
