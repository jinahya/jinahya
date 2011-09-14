/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.util.fsm;


import com.googlecode.jinahya.util.DependencyResolver;
import com.googlecode.jinahya.util.DependencyResolverException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * A Finite State Machine.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Machine {


    /**
     * Creates a new instance.
     *
     * @param tasks tasks
     */
    public Machine(final Map<String, Task> tasks) {

        super();

        if (tasks == null) {
            throw new NullPointerException("null tasks");
        }

        this.tasks = tasks;
    }


    /**
     * Returns current state.
     *
     * @return state
     */
    public final synchronized State getState() {
        return state;
    }


    /**
     * Sets state.
     *
     * @param state new state
     * @throws FSMException if an error occurs.
     */
    public final synchronized void setState(final State state)
        throws FSMException {

        if (state == null) {
            throw new NullPointerException("null state");
        }

        if (this.state.equals(state)) {
            throw new IllegalStateException("same state");
        }

        if (isFinished()) {
            throw new IllegalStateException("already finished");
        }

        final State source = this.state;
        this.state = state;
        final State target = this.state;

        final Transition transition = new Transition(this, source, target);

        if (!isStarted()) {
            if (!isStarting(transition)) {
                throw new IllegalStateException("not started yet");
            }
            started = true;
        }

        if (isFinishing(transition)) {
            finished = true;
        }


        // prepare a dependency resolver and add all ids as sources
        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        final StringBuffer idBuffer = new StringBuffer();


        final TransitionContext transitionContext = new TransitionContext() {


            @Override
            public Transition getTransition() {
                return transition;
            }


            @Override
            public final void setPerformBefore(final String nextTaskId)
                throws FSMException {

                if (nextTaskId == null) {
                    throw new NullPointerException("null nextTaskId");
                }

                final String taskId = idBuffer.toString();
                if (taskId.isEmpty()) {
                    return;
                }

                try {
                    resolver.add(nextTaskId, taskId);
                } catch (DependencyResolverException dre) {
                    throw new FSMException(dre);
                }
            }


            @Override
            public final void setPerformAfter(final String previousTaskId)
                throws FSMException {

                if (previousTaskId == null) {
                    throw new NullPointerException("null previousTaskId");
                }

                final String taskId = idBuffer.toString();
                if (taskId.isEmpty()) {
                    return;
                }

                try {
                    resolver.add(taskId, previousTaskId);
                } catch (DependencyResolverException dre) {
                    throw new FSMException(dre);
                }
            }
        };

        // prepare
        for (Entry<String, Task> entry : tasks.entrySet()) {
            if (!entry.getValue().matches(transition)) {
                continue;
            }
            try {
                resolver.add(entry.getKey(), (String) null);
            } catch (DependencyResolverException dre) {
                throw new FSMException(dre);
            }
            idBuffer.delete(0, idBuffer.length());
            idBuffer.append(entry.getKey());
            entry.getValue().prepare(transitionContext);
        }
        idBuffer.delete(0, idBuffer.length());

        // perform
        for (List<String> idGroup : resolver.getVerticalGroups()) {
            final Task[] taskGroup = new Task[idGroup.size()];
            for (int i = 0; i < taskGroup.length; i++) {
                taskGroup[i] = tasks.get(idGroup.get(i));
            }
            perform(transitionContext, taskGroup);
        }
    }


    /**
     * Performs each of given <code>tasks</code> with specified
     * <code>context</code>. Each task can be performed concurrently. This
     * method must guarantee that all tasks are performed before return.
     *
     * @param context transition context
     * @param tasks tasks
     * @throws FSMException if an error occurs.
     */
    protected void perform(final TransitionContext context, Task... tasks)
        throws FSMException {

        for (Task task : tasks) {
            task.perform(context);
        }
    }


    /**
     * Returns <code>started</code> flag value.
     *
     * @return true if this machine has been started; false otherwise.
     */
    public final synchronized boolean isStarted() {
        return started;
    }


    /**
     * Returns <code>finished</code> flag value.
     *
     * @return true if this machine has been finished; false otherwise.
     */
    public final synchronized boolean isFinished() {
        return finished;
    }


    /**
     * Returns the property value mapped to given <code>name</code>.
     *
     * @param name property name
     * @return property value; may be null if the value itself is null or there
     *         is no value mapped to given <code>name</code>.
     */
    public final Object getProperty(final String name) {

        if (name == null) {
            throw new NullPointerException("null name");
        }
        
        return properties.get(name);
    }


    /**
     * Sets a property.
     *
     * @param name property name
     * @param value property value
     * @return previously mapped value
     */
    public final Object setProperty(final String name, final Object value) {

        if (name == null) {
            throw new NullPointerException("null name");
        }

        return properties.put(name, value);
    }


    /**
     * Checks if given <code>transition</code> is a starting condition.
     * Default implementation always returns <code>true</code>. Override this
     * method if customizations are needed.
     *
     * @param transition transition to check
     * @return true
     */
    protected abstract boolean isStarting(Transition transition);


    /**
     * Checks if given <code>transition</code> is allowed. Default
     * implementation always returns <code>true</code>. Override this method if
     * customizations are needed.
     *
     * @param transition transition to check.
     * @return true
     */
    protected abstract boolean isAllowed(Transition transition);


    /**
     * Checks if given <code>transition</code> is an finishing condition.
     * Default implementation always returns <code>false</code>. Override this
     * method if customizations are needed.
     *
     * @param transition transition to check
     * @return false 
     */
    protected abstract boolean isFinishing(Transition transition);


    /**
     * Adds given <code>listener</code> to this machine.
     *
     * @param listener the listener to be added
     */
    public void addTransitionListener(final TransitionListener listener) {

        if (listener == null) {
            throw new NullPointerException("null listener");
        }

        listeners.add(listener);
    }


    /**
     * Removes given <code>listener</code> from this machine.
     *
     * @param listener the listener to be removed
     */
    public void removeTransitionListener(final TransitionListener listener) {

        if (listener == null) {
            throw new NullPointerException("null listener");
        }

        listeners.remove(listener);
    }


    /** tasks. */
    private final Map<String, Task> tasks;


    /** flag for started. */
    private volatile boolean started = false;


    /** flag for finished. */
    private volatile boolean finished = false;


    /** current state. */
    private volatile State state = State.UNKNOWN;


    /** properties. */
    private Map<String, Object> properties =
        Collections.synchronizedMap(new HashMap<String, Object>());


    /** listeners. */
    private List<TransitionListener> listeners =
        new ArrayList<TransitionListener>();
}
