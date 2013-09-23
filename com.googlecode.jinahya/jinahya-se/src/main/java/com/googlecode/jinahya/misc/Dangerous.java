/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.misc;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import sun.misc.Unsafe;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public final class Dangerous {


    /**
     * The {@code Unsafe} instance.
     */
    public static final Unsafe UNSAFE;


    static {
        /*
         * try { final Field field = Unsafe.class.getDeclaredField("theUnsafe");
         * if (!field.isAccessible()) { field.setAccessible(true); } try {
         * UNSAFE = (Unsafe) field.get(null); } catch (IllegalAccessException
         * iae) { throw new InstantiationError(iae.getMessage()); } } catch
         * (NoSuchFieldException nsfe) { throw new
         * InstantiationError(nsfe.getMessage()); }
         */
        Unsafe unsafe = null;
        for (final Field field : Unsafe.class.getDeclaredFields()) {
            final int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers)) {
                continue;
            }
            if (!Unsafe.class.equals(field.getType())) {
                continue;
            }
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                unsafe = (Unsafe) field.get(null);
                break;
            } catch (IllegalAccessException iae) {
                continue;
            }
        }
        if (unsafe == null) {
            throw new InstantiationError("unable to locate unsafe");
        }
        UNSAFE = unsafe;
    }


    /**
     *
     * @param o
     * @param offset
     *
     * @return
     *
     * @see Unsafe#getInt(java.lang.Object, long)
     */
    public static long getUnsignedInt(final Object o, final long offset) {

        return UNSAFE.getInt(o, offset) & 0xFFFFFFFFL;
    }


    /**
     *
     * @param o
     * @param offset
     * @param x
     *
     * @see Unsafe#putInt(java.lang.Object, long, int)
     */
    public static void putUnsignedInt(final Object o, final long offset,
                                      final long x) {

        UNSAFE.putInt(o, offset, (int) (x & 0xFFFFFFFFL));
    }


    private Dangerous() {

        super();
    }


}
