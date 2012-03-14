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


package com.googlecode.jinahya.validation;


import com.googlecode.jinahya.validation.constraints.CollapsedStringSize;
import com.googlecode.jinahya.validation.constraints.NormalizedStringSize;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Flower {


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public String getMeaning() {
        return meaning;
    }


    public void setMeaning(final String meaning) {
        this.meaning = meaning;
    }


    @CollapsedStringSize(min = 1, max = 40)
    private String name;


    @NormalizedStringSize(min = 0, max = 40)
    private String meaning;


}

