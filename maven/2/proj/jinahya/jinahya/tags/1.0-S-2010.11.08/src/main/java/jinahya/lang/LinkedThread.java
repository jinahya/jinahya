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
 */
public abstract class LinkedThread extends Thread {


    /**
     * 
     * @param parent
     */
    public LinkedThread(final Thread parent) {
        super();

        this.parent = parent;
    }


    @Override
    public final void run() {

        try {
            runThread();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        if (parent != null) {
            try {
                parent.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace(System.out);
            }
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
     * 
     */
    protected abstract void runThread();


    private final Thread parent;
}
