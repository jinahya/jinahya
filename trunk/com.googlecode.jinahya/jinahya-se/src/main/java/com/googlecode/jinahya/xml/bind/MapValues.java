/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.xml.bind;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;


/**
 * An abstract class.
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <T> map value type parameter
 */
@XmlTransient
public abstract class MapValues<T> {


    /**
     * Returns a list of values.
     *
     * @return a list of values.
     */
    protected List<T> getValues() {

        if (values == null) {
            values = new ArrayList<T>();
        }

        return values;
    }


    /**
     * values.
     */
    private List<T> values;


}
