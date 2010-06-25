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


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Processor<T> {


    /**
     * Creates a new instance.
     *
     * @param id processor id.
     * @param prerequisites the prerequisite processors' id array or null.
     */
    public Processor(final String id, final String[] prerequisites) {
        super();

        if (id == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + " is null");
        }

        if (prerequisites == null) {
            throw new IllegalArgumentException(
                "param:1:" + String[].class + " is null");
        }

        this.id = id;
        this.prerequisites = prerequisites;
    }


    /**
     *
     * @return
     */
    public final String getId() {
        return id;
    }


    /**
     *
     * @return
     */
    public final String[] getPrerequisites() {
        return prerequisites;
    }


    /**
     *
     * @param unit
     * @throws ProcessorException
     */
    public abstract void process(T unit) throws ProcessorException;


    private String id;
    private String[] prerequisites;
}
