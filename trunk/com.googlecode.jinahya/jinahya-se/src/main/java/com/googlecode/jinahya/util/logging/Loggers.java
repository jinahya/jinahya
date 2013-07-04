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


import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class Loggers {


    public static <H extends Handler> void setHandlersLevel(
        final Logger logger, final Class<H> type, final Level level) {

        if (logger == null) {
            throw new NullPointerException("logger");
        }

        if (type == null) {
            throw new NullPointerException("type");
        }

        if (level == null) {
            throw new NullPointerException("level");
        }

        if (logger.getUseParentHandlers()) {
            final Logger parent = logger.getParent();
            if (parent != null) {
                setHandlersLevel(parent, type, level);
                return;
            }
        }

        for (Handler handler : logger.getHandlers()) {
            if (type.isInstance(handler)) {
                handler.setLevel(level);
            }
        }
    }


    public static void setConsoleHandlersLevel(final Logger logger,
                                               final Level level) {

        setHandlersLevel(logger, ConsoleHandler.class, level);
    }


    public static void setConsoleHandlerLevel(final Logger logger,
                                              final Level level) {

        if (logger == null) {
            throw new NullPointerException("logger");
        }

        if (level == null) {
            throw new NullPointerException("level");
        }

        if (logger.getUseParentHandlers()) {
            final Logger parent = logger.getParent();
            if (parent != null) {
                setConsoleHandlerLevel(parent, level);
                return;
            }
        }

        for (Handler handler : logger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                handler.setLevel(level);
                return;
            }
        }
    }


    private Loggers() {
        super();
    }


}

