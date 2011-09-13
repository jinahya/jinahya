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


/**
 * Task for switching conditions.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class SwitchTask extends Task {


    /**
     * Creates a new instance.
     *
     * @param id task id
     * @param onMatchers matchers for switching on; none null; none empty
     * @param offMatchers matchers for switching off; none null; none empty
     */
    public SwitchTask(final String id, final TransitionMatcher[] onMatchers,
                      final TransitionMatcher[] offMatchers) {
        super(id);

        if (onMatchers == null) {
            throw new NullPointerException("null onMatchers");
        }

        if (onMatchers.length == 0) {
            throw new NullPointerException("empty onMatchers");
        }

        if (offMatchers == null) {
            throw new NullPointerException("null offMatchers");
        }

        if (offMatchers.length == 0) {
            throw new NullPointerException("empty offMatchers");
        }

        this.onMatchers = onMatchers;
        this.offMatchers = offMatchers;
    }


    @Override
    public void prepare(final TransitionContext context) throws FSMException {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        final Transition transition = context.getTransition();

        synchronized (this) {
            if (on) {
                if (transition.matchesAny(offMatchers)) {
                    prepareOff(context);
                }
            } else { // off
                if (transition.matchesAny(onMatchers)) {
                    prepareOn(context);
                }
            }
        }
    }


    /**
     * Prepares for switching on.
     *
     * @param context context
     * @throws FSMException if an error occurs.
     */
    protected abstract void prepareOn(TransitionContext context)
        throws FSMException;


    /**
     * Prepares for switching off.
     *
     * @param context context
     * @throws FSMException if an error occurs.
     */
    protected abstract void prepareOff(TransitionContext context)
        throws FSMException;


    @Override
    public void perform(final TransitionContext context) throws FSMException {

        if (context == null) {
            throw new NullPointerException("null context");
        }

        final Transition transition = context.getTransition();

        synchronized (this) {
            if (on) {
                if (transition.matchesAny(offMatchers)) {
                    on = false;
                    performOff(context);
                }
            } else { // off
                if (transition.matchesAny(onMatchers)) {
                    on = true;
                    performOn(context);
                }
            }
        }
    }


    /**
     * Performs for switching on.
     *
     * @param context context
     * @throws FSMException if an error occurs.
     */
    protected abstract void performOn(TransitionContext context)
        throws FSMException;


    /**
     * Performs for switching off.
     *
     * @param context context
     * @throws FSMException if an error occurs.
     */
    protected abstract void performOff(TransitionContext context)
        throws FSMException;


    /** matchers for switching on. */
    private final TransitionMatcher[] onMatchers;


    /** matchers for switching off. */
    private final TransitionMatcher[] offMatchers;


    /** on flag. */
    private volatile boolean on = false;
}
