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
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class AbstractTask implements Task {


    /**
     * Creates a new instance.
     *
     * @param id task id
     * @param matchers transition matchers
     */
    public AbstractTask(final String id, final TransitionMatcher... matchers) {
        super();

        if (id == null) {
            throw new NullPointerException("null id");
        }

        if (matchers == null) {
            throw new NullPointerException("null matchers");
        }

        if (matchers.length == 0) {
            throw new IllegalArgumentException("empty matchers");
        }

        this.id = id;
        this.matchers = matchers;
    }


    @Override
    public final String getId() {
        return id;
    }


    @Override
    public boolean matches(final Transition transition) {

        if (transition == null) {
            throw new NullPointerException("null transition");
        }

        return transition.matchesAny(matchers);
    }


    /** id. */
    private final String id;


    /** matchers. */
    private final TransitionMatcher[] matchers;
}
