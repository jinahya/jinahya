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


import android.app.Activity;


/**
 * States for Activity.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public enum ActivityState implements State {


    /**
     * State when {@link Activity#onCreate(android.os.Bundle)} called from
     * {@link #UNKNOWN} or {@link Activity#onStop()} called from
     * {@link #ACTIVE}.
     */
    STOPPED,
    /**
     * 
     */
    PAUSED,
    /**
     * State when {@link Activity#onResume()} called.
     */
    ACTIVE,
    /**
     * State when {@link Activity#onRestart()} called.
     */
    RESTARTING,
    /**
     * State when {@link Activity#onDestroy()} called.
     */
    DESTROYED;


    @Override
    public int getCode() {
        return ordinal();
    }


    @Override
    public String getName() {
        return name();
    }


    @Override
    public String toString() {
        return "ActivityState(" + getName() + "(" + getCode() + "))";
    }
}
