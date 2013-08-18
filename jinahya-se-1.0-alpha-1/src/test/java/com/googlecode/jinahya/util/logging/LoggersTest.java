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


package com.googlecode.jinahya.util.logging;


import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class LoggersTest {


    @Test
    public static void testSetHandlersLevel() {

        final Logger leaf = Logger.getLogger(LoggersTest.class.getName());
        final Logger root = Logger.getLogger("");

        final Handler handler = new StreamHandler();

        Assert.assertEquals(handler.getLevel(), Level.INFO);

        root.addHandler(handler);

        final Level expected = Level.WARNING;
        Loggers.setHandlersLevel(leaf, StreamHandler.class, expected);
        Assert.assertEquals(handler.getLevel(), expected);
    }


    @Test
    public static void testSetConsoleHandlerLevel() {

        final Logger leaf = Logger.getLogger(LoggersTest.class.getName());
        final Logger root = Logger.getLogger("");

        Loggers.setConsoleHandlerLevel(leaf, Level.OFF);
    }


}

