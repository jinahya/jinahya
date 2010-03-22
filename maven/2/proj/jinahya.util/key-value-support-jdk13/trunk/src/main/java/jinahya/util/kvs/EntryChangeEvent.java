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

package jinahya.util.kvs;


import java.util.EventObject;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class EntryChangeEvent extends EventObject {


    /** GENERATED. */
    private static final long serialVersionUID = -7581628322640088137L;


    //public static final int ADDED = 0x01;
    //public static final int REMOVED = ADDED << 1;
    //public static final int CHANGED =


    /**
     *
     * @param source
     * @param key
     * @param oldValue
     * @param newValue
     */
    public EntryChangeEvent(final KeyValueSupport source, final String key,
                            final String oldValue, final String newValue) {

        super(source);

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (oldValue == null && newValue == null) {
            throw new NullPointerException("both values are null");
        }

        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }


    public String getKey() {
        return key;
    }


    public String getOldValue() {
        return oldValue;
    }


    public String getNewValue() {
        return newValue;
    }


    private String key;
    private String oldValue;
    private String newValue;
}
