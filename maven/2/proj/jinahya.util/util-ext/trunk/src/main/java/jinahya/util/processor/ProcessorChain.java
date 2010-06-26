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


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jinahya.util.DependencyResolver;


/**
 *
 * @author onacit
 * @param <T> processing unit type
 */
public class ProcessorChain<T> {


    public ProcessorChain(final Class<T> type) {
        super();

        if (type == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ": is null");
        }

        this.type = type;

        processors =
            Collections.synchronizedMap(new HashMap<String, Processor<T>>());

        resolver = new DependencyResolver<String>(String.class);
    }


    /**
     *
     * @param unit
     * @throws ProcessorException
     */
    public void invoke(final T unit) throws ProcessorException {
        synchronized (processors) {

            for (Processor<T> processor : processors.values()) {
                for (String prerequisite : processor.getPrerequisites()) {
                    if (!processors.containsKey(prerequisite)) {
                        throw new ProcessorException(
                            "a processor(" + prerequisite
                            + ") prerequsite to the processor("
                            + processor.getId() + " is missing");
                    }
                }
            }

            for (String processorId : resolver.getFlatten()) {
                processors.get(processorId).process(unit);
            }
        }
    }


    /**
     *
     * @param processor
     * @return
     * @throws ProcessorException
     */
    public final Processor<T> add(final Processor<T> processor) {

        if (processor == null) {
            throw new IllegalArgumentException(
                "param:0:" + Processor.class + " is null");
        }

        if (!type.equals(processor.getType())) {
            throw new IllegalArgumentException("wrong processing unit type");
        }

        synchronized (processors) {

            final Processor<T> removed = remove(processor.getId());

            resolver.addDependency(processor.getId(), null);
            for (String prerequisite: processor.getPrerequisites()) {
                resolver.addDependency(processor.getId(), prerequisite);
            }

            processors.put(processor.getId(), processor);

            return removed;
        }
    }


    /**
     *
     */
    public final Processor<T> remove(final String processorId) {

        if (processorId == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        synchronized (processors) {
            final Processor removed =  processors.remove(processorId);
            if (removed != null) {
                for (String prerequisite : removed.getPrerequisites()) {
                    resolver.removeDependency(removed.getId(), prerequisite);
                }
                resolver.removeDependency(removed.getId(), null);
            }
            return removed;
        }
    }


    /**
     *
     * @return
     */
    public final String[] getProcessorIds() {
        synchronized (processors) {
            return processors.keySet().toArray(new String[processors.size()]);
        }
    }


    /**
     *
     */
    public final void clear() {
        synchronized (processors) {
            processors.clear();
            resolver.reset();
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
                "param:1:" + String.class + ": is null");
        }

        synchronized (processors) {
            return processors.containsKey(processorId);
        }
    }


    /**
     * Returns the processing unit type.
     *
     * @return processing unit type
     */
    public final Class<T> getType() {
        return type;
    }


    private final Class<T> type;

    private final Map<String, Processor<T>> processors;

    private final DependencyResolver<String> resolver;
}
