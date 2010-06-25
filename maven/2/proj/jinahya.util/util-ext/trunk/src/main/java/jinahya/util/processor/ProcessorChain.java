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
import java.util.Set;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ProcessorChain<T> {


    /**
     * Creates a new instnace.
     *
     * @param spec the processor spec or null for no spec.
     */
    public ProcessorChain(final ProcessorChainSpec spec) {
        super();

        this.spec = spec;
    }


    /**
     *
     * @throws ProcessorChainException
     */
    public void invoke() throws ProcessorException {
        synchronized (processorMap) {
        }
    }


    /**
     *
     * @param processor
     * @return
     * @throws ProcessorException
     */
    public Processor<T> addProcessor(final Processor<T> processor)
        throws ProcessorException {

        if (processor == null) {
            throw new IllegalArgumentException(
                "param:0:" + Processor.class + " is null");
        }

        if (spec != null && !spec.isAllowed(processor.getId())) {
            throw new ProcessorException(processor + " is not allowed by spec");
        }

        synchronized (processorMap) { // Do I really have to do this?
            return processorMap.put(processor.getId(), processor);
        }
    }


    /**
     *
     */
    public Processor<T> removeProcessor(final String processorId) {
        synchronized (processorMap) { // Do I really have to do this?
            return processorMap.remove(processorId);
        }
    }


    /**
     *
     * @return
     */
    public String[] getProcessorIds() {
        synchronized (processorMap) {
            return processorMap.keySet().toArray(
                new String[processorMap.size()]);
        }
    }


    private ProcessorChainSpec spec;

    private final Map<String, Processor<T>> processorMap =
        Collections.synchronizedMap(new HashMap<String, Processor<T>>());
}
