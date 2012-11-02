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
 * An abstract ServletRequestAttributeListener for Nica-Codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaCodesListener extends NicaAttributeListener {


    @Override
    @SuppressWarnings("unchecked")
    public void attributeAdded(final ServletRequestAttributeEvent srae) {

        if (NicaFilter.ATTRIBUTE_NICA_CODES.equals(srae.getName())) {
            nicaCodesAdded(srae.getServletRequest(),
                           (Map<String, String>) srae.getValue());
        }
    }


    /**
     * Notifies nica codes added.
     *
     * @param request servlet request
     * @param codes nica codes
     */
    protected abstract void nicaCodesAdded(ServletRequest request,
                                           Map<String, String> codes);


}

