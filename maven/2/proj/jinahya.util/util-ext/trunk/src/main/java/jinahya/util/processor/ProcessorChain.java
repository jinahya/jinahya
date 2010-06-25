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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jinahya.util.DependencyResolver;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ProcessorChain<T> {



    /**
     *
     * @param unit
     * @throws ProcessorException
     */
    public void invoke(final T unit) throws ProcessorException {
        synchronized (processorMap) {

            for (Processor<T> processor : processorMap.values()) {
                for (String prerequisite : processor.getPrerequisites()) {
                    if (!processorMap.containsKey(prerequisite)) {
                        throw new ProcessorException(
                            "a processor(" + prerequisite
                            + ") prerequsite to the processor("
                            + processor.getId() + " is missing");
                    }
                }
            }

            for (String processorId : resolver.getFlatten()) {
                processorMap.get(processorId).process(unit);
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

        synchronized (processorMap) {

            final Processor<T> removed = remove(processor.getId());

            resolver.addDependency(processor.getId(), null);
            for (String prerequisite: processor.getPrerequisites()) {
                resolver.addDependency(processor.getId(), prerequisite);
            }

            processorMap.put(processor.getId(), processor);

            return removed;
        }
    }


    /**
     *
     */
    public final Processor<T> remove(final String processorId) {

        if (processorId == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + " is null");
        }

        synchronized (processorMap) {
            final Processor removed =  processorMap.remove(processorId);
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
        synchronized (processorMap) {
            return processorMap.keySet().toArray(
                new String[processorMap.size()]);
        }
    }



    public final void clear() {
        synchronized (processorMap) {
            processorMap.clear();
            resolver.reset();
        }
    }


    public final boolean hasProcessor(final String processorId) {
        if (processorId == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + " is null");
        }

        synchronized (processorMap) {
            return processorMap.containsKey(processorId);
        }
    }


    private final Map<String, Processor<T>> processorMap =
        Collections.synchronizedMap(new HashMap<String, Processor<T>>());

    private final DependencyResolver<String> resolver =
        new DependencyResolver<String>();
}
