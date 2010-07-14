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
 * @param <T> processing unit type
 */
public abstract class Processor<T> {


    /**
     * Creates a new instance.
     *
     * @param type processing unit type.
     * @param id processor id.
     * @param prerequisiteIds the prerequisite processors' id array or null.
     */
    public Processor(final Class<T> type, final String id,
                     final String[] prerequisiteIds) {

        super();

        if (type == null) {
            throw new IllegalArgumentException(
                "param:0:" + Class.class + ": is null");
        }

        if (id == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        this.type = type;
        this.id = id;

        if (prerequisiteIds == null) {
            this.prerequisiteIds = new String[0];
        } else {
            this.prerequisiteIds = prerequisiteIds;
        }
    }


    /**
     *
     * @return
     */
    public final Class<T> getType() {
        return type;
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
    public final String[] getPrerequisiteIds() {
        return this.prerequisiteIds;
    }


    /**
     *
     * @param unit processing unit
     * @throws ProcessorException if any error occurs.
     */
    public abstract void process(T unit) throws ProcessorException;


    private Class<T> type;
    private String id;
    private String[] prerequisiteIds;
}
