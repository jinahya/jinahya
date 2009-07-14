/*
 *  Copyright 2009 Jin Kwon.
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
 *  under the License.
 */

package jinahya.xlet.util.prefs;


import java.util.EventObject;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com>Jin Kwon</a>
 */
public class PreferenceChangeEvent extends EventObject {


    public PreferenceChangeEvent(Preferences node, Class valueClass, String key,
                                 Object newValue) {

        super(node);

        this.node = node;
        this.valueClass = valueClass;
        this.key = key;
        this.newValue = newValue;
    }


    public Preferences getNode() {
        return node;
    }


    public Class getValueClass() {
        return valueClass;
    }


    public String getKey() {
        return key;
    }


    public Object getNewValue() {
        return newValue;
    }


    private Preferences node;
    private Class valueClass;
    private String key;
    private Object newValue;
}
