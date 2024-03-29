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


/**
 * An abstract servlet attribute listener for
 * {@link NicaFilter#ATTRIBUTE_NICA_CODES_L2}. All subclasses extending this
 * class will be notified after all listeners extending
 * {@link NicaCodesAttributeListener} and before any listeners extending
 * {@link NicaCodesAttributeListenerL3}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaCodesAttributeListenerL2
    extends AbstractNicaCodesAttributeListener {


    /**
     * Creates a new instance.
     */
    public NicaCodesAttributeListenerL2() {

        super(NicaFilter.ATTRIBUTE_NICA_CODES_L2);
    }


}
