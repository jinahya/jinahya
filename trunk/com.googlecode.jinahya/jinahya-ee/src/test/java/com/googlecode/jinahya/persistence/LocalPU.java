/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.persistence;


import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class LocalPU {


    private static final Logger LOGGER =
        Logger.getLogger(LocalPU.class.getName());


    private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
        Persistence.createEntityManagerFactory("localPU");


    public static EntityManager createEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }


    public static void print(final ConstraintViolationException cve) {
        for (ConstraintViolation violation : cve.getConstraintViolations()) {
            System.err.println("rootBeanClass: "
                               + violation.getRootBeanClass());
            System.err.println("leafBean: " + violation.getLeafBean());
            System.err.println("propertyPath: " + violation.getPropertyPath());
            System.err.println("constraintDescriptor: "
                               + violation.getConstraintDescriptor());
            System.err.println("message: " + violation.getMessage());
        }
    }


    public static void printConstraintViolation(final Exception e) {
        if (!ConstraintViolationException.class.isInstance(e)) {
            return;
        }
        print((ConstraintViolationException) e);
    }


    /**
     *
     * @param min inclusive
     * @param max inclusive
     *
     * @return
     */
    public static int random(final int min, final int max) {

        if (max < min) {
            throw new IllegalArgumentException(
                "max(" + max + ") < min(" + min + ")");
        }

        return min + (ThreadLocalRandom.current().nextInt() & (max - min));
    }


    public static long random(final long min, final long max) {

        if (max < min) {
            throw new IllegalArgumentException(
                "max(" + max + ") < min(" + min + ")");
        }

        return min + (ThreadLocalRandom.current().nextLong() & (max - min));
    }


    protected LocalPU() {
        super();
    }


}

