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

package jinahya.lang;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <B> result type of {@link #runBefore()}
 * @param <A> result type of {@link #runAfter()}
 */
public abstract class LinkedThread<B, A> extends Thread {


    /**
     * Creates a new instance.
     */
    public LinkedThread() {
        this(null);
    }


    /**
     * Creates a new instance.
     *
     * @param parent parent thread to join
     */
    public LinkedThread(final Thread parent) {
        this(parent, 0L);
    }


    /**
     * 
     * @param parent parent thread to join
     * @param millis the time to wait in milliseconds
     */
    public LinkedThread(final Thread parent, final long millis) {
        super();

        this.parent = parent;
        this.millis = millis;
    }


    @Override
    public void run() {

        try {
            runBeforeResult = runBefore();
        } catch (Exception e) {
            runBeforeThrown = e;
        }

        if (parent != null) {
            try {
                parent.join(millis);
                joined = true;
            } catch (InterruptedException ie) {
                ie.printStackTrace(System.out);
            }
        }

        try {
            runAfterResult = runAfter();
        } catch (Exception e) {
            runAfterThrown = e;
        }
    }


    @Override
    public void interrupt() {

        if (parent != null) {
            parent.interrupt();
        }

        super.interrupt();
    }

    
    /**
     * Executes desired tasks before joining parent.
     */
    protected abstract B runBefore();


    /**
     * Executes desired tasks after joining parent.
     */
    protected abstract A runAfter();

    
    /**
     * Returns any thrown exception while executing {@link #runBefore()}.
     *
     * @return thrown exception
     */
    public Throwable getRunBeforeThrown() {
        return runBeforeThrown;
    }


    /**
     * 
     * @return
     */
    public Throwable getRunAfterThrown() {
        return runAfterThrown;
    }


    /**
     * Returns whether successfully joined or not.
     *
     * @return joined
     */
    public boolean getJoined() {
        return joined;
    }


    /**
     * Returns the return value of {@link #runBefore()}.
     *
     * @return runBefore result
     * @see #runBefore()
     */
    public B getRunBeforeResult() {
        return runBeforeResult;
    }


    /**
     * Returns the return value of {@link #runAfter()}.
     *
     * @return runAfter result
     * @see #runAfter()
     */
    public A getRunAfterResult() {
        return runAfterResult;
    }


    private final Thread parent;
    private final long millis;

    private volatile Exception runBeforeThrown = null;
    private volatile Exception runAfterThrown = null;
    private volatile boolean joined = false;

    private B runBeforeResult = null;
    private A runAfterResult = null;
}
