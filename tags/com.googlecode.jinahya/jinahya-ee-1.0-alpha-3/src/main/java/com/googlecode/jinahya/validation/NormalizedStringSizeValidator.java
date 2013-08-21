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


import com.googlecode.jinahya.validation.constraints.NormalizedStringSize;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class NormalizedStringSizeValidator
    implements ConstraintValidator<NormalizedStringSize, String> {


    @Override
    public void initialize(final NormalizedStringSize constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }


    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        final String collapsed = new CollapsedStringAdapter().unmarshal(value);

        return collapsed.length() > min && collapsed.length() < max;
    }


    private int min;


    private int max;


}

