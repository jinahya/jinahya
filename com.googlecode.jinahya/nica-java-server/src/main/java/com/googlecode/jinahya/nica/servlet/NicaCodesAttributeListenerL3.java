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


import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletResponse;


/**
 * An abstract ServletRequestAttributeListener for
 * {@link NicaFilter#ATTRIBUTE_CODES}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaCodesAttributeListenerL3
    extends AbstractNicaCodesAttributeListener {


    public NicaCodesAttributeListenerL3() {

        super(NicaFilter.ATTRIBUTE_NICA_CODES_L3);
    }


    protected String getNicaClientIdentifier(
        final ServletRequestAttributeEvent event) {

        if (event == null) {
            throw new NullPointerException("event");
        }

        final String nicaClientIdentity =
            (String) event.getServletRequest().getAttribute(
            AbstractNicaClientIdentifier.ATTRIBUTE_NICA_CLIENT_IDENTITY);

        if (nicaClientIdentity == null) {
            final String message = "no nica client identity in the context";
            setResponseError(
                event, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
            throw new RuntimeException(message);
        }

        return nicaClientIdentity;
    }


}
