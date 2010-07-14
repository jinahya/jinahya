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
public class ProcessorChain<T> {


    /**
     *
     * @param type
     */
    public ProcessorChain(final Class<T> type) {
        super();

        if (type == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ": is null");
        }

        this.type = type;

        processors = new Hashtable<String, Processor<T>>();

        resolver = new DependencyResolver<String>(String.class);
    }


    /**
     *
     * @param unit
     * @throws ProcessorException
     */
    public void invoke(final T unit) throws ProcessorException {

        if (unit == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (!type.isInstance(unit)) {
            throw new IllegalArgumentException(
                "param:0:" + unit.getClass() + ": is not an instance of "
                + type);
        }

        synchronized (processors) {

            getRequiredProcessorIds(false);

            final Vector<Vector<String>> processorIDGroups =
                resolver.getHorizontalGroups();

            final Vector<Processor[]> processorGroups =
                new Vector<Processor[]>();

            for (int i = 0; i < processorIDGroups.size(); i++) {

                final Vector<String> processorIdGroup =
                    processorIDGroups.elementAt(i);

                final Processor[] processorGroup =
                    new Processor[processorIdGroup.size()];

                for (int j = 0; j < processorGroup.length; j++) {
                    processorGroup[j] =
                        processors.get(processorIdGroup.elementAt(j));
                }

                processorGroups.addElement(processorGroup);
            }

            invoke(processorGroups, unit);

            /*
            for (String processorId : resolver.getFlatten()) {
                processors.get(processorId).process(unit);
            }
             */
        }
    }


    /**
     *
     * @param group
     * @param processor
     * @param unit
     * @throws ProcessorException
     */
    protected void invoke(final Vector<Processor[]> processorGroups,
                          final T unit)
        throws ProcessorException {

        for (int i = 0; i < processorGroups.size(); i++) {
            for (int j = 0; j < processorGroups.elementAt(i).length; j++) {
                processorGroups.elementAt(i)[j].process(unit);
            }
        }
    }


    /**
     *
     * @param processor
     * @return
     * @throws ProcessorException
     */
    public final Processor<T> addProcessor(final Processor<T> processor) {

        if (processor == null) {
            throw new IllegalArgumentException(
                "param:0:" + Processor.class + ": is null");
        }

        if (!type.equals(processor.getType())) {
            throw new IllegalArgumentException("wrong processing unit type");
        }

        synchronized (processors) {

            final Processor<T> removed = removeProcessor(processor.getId());

            resolver.addDependency(processor.getId(), null);
            if (processor.getPrerequisiteIds() != null) {
                resolver.addDependencies(processor.getId(),
                                         processor.getPrerequisiteIds());
            }
            processors.put(processor.getId(), processor);

            return removed;
        }
    }


    /**
     *
     */
    public final Processor<T> removeProcessor(final String processorId) {

        if (processorId == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        synchronized (processors) {
            final Processor removed = processors.remove(processorId);
            if (removed != null) {
                if (removed.getPrerequisiteIds() != null) {
                    resolver.removeDependencies(removed.getId(),
                                                removed.getPrerequisiteIds());
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
            final String[] processorIds = new String[processors.size()];
            final Enumeration<String> keys = processors.keys();
            for (int i = 0; i < processorIds.length; i++) {
                processorIds[i] = keys.nextElement();
            }
            return processorIds;
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


    void print(final java.io.PrintStream out) {
        final Enumeration<String> keys = processors.keys();
        while (keys.hasMoreElements()) {
            final String key = keys.nextElement();
            out.print(key + " -> " + processors.get(key));
        }
    }


    Vector<Vector<String>> getHorizontalGroups() {
        return resolver.getHorizontalGroups();
    }


    /**
     *
     * @param suppress
     * @return
     * @throws ProcessorException
     */
    public Vector<String> getRequiredProcessorIds() throws ProcessorException {

        return getRequiredProcessorIds(true);
    }


    private Vector<String> getRequiredProcessorIds(final boolean suppress)
        throws ProcessorException {

        final Vector<String> requiredProcessorIds = new Vector<String>();

        synchronized (processors) {
            for (Processor<T> processor : processors.values()) {
                for (String prerequisiteId : processor.getPrerequisiteIds()) {
                    if (!processors.containsKey(prerequisiteId)) {
                        if (!suppress) {
                            throw new ProcessorException(
                                "a required processor(" + prerequisiteId +
                                ") is missing");
                        }
                        if (!requiredProcessorIds.contains(prerequisiteId)) {
                            requiredProcessorIds.add(prerequisiteId);
                        }
                    }
                }
            }
        }

        return requiredProcessorIds;
    }


    private final Class<T> type;

    private final Hashtable<String, Processor<T>> processors;

    private final DependencyResolver<String> resolver;
}
