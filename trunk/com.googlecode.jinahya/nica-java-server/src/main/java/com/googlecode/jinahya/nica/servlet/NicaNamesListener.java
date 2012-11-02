/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.nica.servlet;


import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;


/**
 * An abstract ServletRequestAttributeListener for Nica-Names. One of primary
 * goal of the implementing class is locating a secret key and add it to
 * specified ServletRequest as an attribute named
 * {@link NicaFilter#ATTRIBUTE_NICA_KEY}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaNamesListener extends NicaAttributeListener {


    @Override
    @SuppressWarnings("unchecked")
    public void attributeAdded(final ServletRequestAttributeEvent srae) {

        if (NicaFilter.ATTRIBUTE_NICA_NAMES.equals(srae.getName())) {
            nicaNamesAdded(srae.getServletRequest(),
                           (Map<String, String>) srae.getValue());
        }
    }


    /**
     * Notifies nica names added.
     *
     * @param request servlet request
     * @param names nica names
     */
    protected abstract void nicaNamesAdded(ServletRequest request,
                                           Map<String, String> names);


}

