
/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.googlecode.jinahya.validation;


import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class CollapsedStringSizeValidatorTest {


    private static final ValidatorFactory VALIDATOR_FACTORY =
        Validation.buildDefaultValidatorFactory();


    @Test
    public void testMin() {

        final Flower flower = new Flower();
        flower.setName("        ");

        final Set<ConstraintViolation<Flower>> violation =
            VALIDATOR_FACTORY.getValidator().validate(flower);

        Assert.assertEquals(violation.size(), 1);
        System.out.println(violation.iterator().next());
    }


    @Test
    public void testMax() {

        final Flower flower = new Flower();
        flower.setName("aaaaaaaaaa" + "aaaaaaaaaa" + "aaaaaaaaaa" + "aaaaaaaaaa"
                       + "b");

        final Set<ConstraintViolation<Flower>> violation =
            VALIDATOR_FACTORY.getValidator().validate(flower);

        Assert.assertEquals(violation.size(), 1);
        System.out.println(violation.iterator().next());
    }


    public void test() {
    }


}

