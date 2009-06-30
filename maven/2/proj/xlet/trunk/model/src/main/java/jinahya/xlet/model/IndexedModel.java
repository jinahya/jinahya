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

package jinahya.xlet.model;


import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jinahya.xlet.bind.Bind;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class IndexedModel extends Model {


    public IndexedModel(Class clazz, String name, String read)
        throws InstantiationException, IllegalAccessException,
               NoSuchMethodException, InvocationTargetException {

        super(clazz);

        this.name = name;
        this.read = clazz.getMethod(read, new Class[0]);

        value = this.read.invoke(getBind(), new Object[0]);
        index = Array.getLength(value) == 0 ? -1 : 0;
    }


    //@Override
    public synchronized void setBind(Bind bind) {
        super.setBind(bind);
    }


    public synchronized Object getIndexedValue() {
        if (index < 0 || Array.getLength(value) == 0) {
            return null;
        }
        return Array.get(value, index);
    }


    public synchronized int getLength() {
        return Array.getLength(value);
    }


    private String name;
    private Method read;

    private Object value;
    private int index;
}