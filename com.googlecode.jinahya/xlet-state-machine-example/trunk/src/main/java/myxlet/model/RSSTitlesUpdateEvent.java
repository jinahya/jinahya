/*
 * Copyright 2011 onacit.
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


package myxlet.model;


import java.util.EventObject;
import java.util.List;


/**
 *
 * @author onacit
 */
public class RSSTitlesUpdateEvent extends EventObject {


    /** GENERATED. */
    private static final long serialVersionUID = -7526369309661115502L;


    /**
     * Creates a new instance.
     *
     * @param source source object
     */
    public RSSTitlesUpdateEvent(final RSSTitles source) {
        super(source);

        if (source == null) {
            throw new NullPointerException("null source");
        }
    }


    /**
     * 
     * @return 
     */
    public List<String> getTitles() {
        return ((RSSTitles) getSource()).getTitles();
    }
}
