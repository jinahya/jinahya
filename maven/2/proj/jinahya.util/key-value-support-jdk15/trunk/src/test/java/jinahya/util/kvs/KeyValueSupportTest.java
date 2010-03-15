/*
 *  Copyright 2010 Jin Kwon.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.util.kvs;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KeyValueSupportTest {



    private static final Random RANDOM = new Random();


    @Before
    public void clearBefore() {
        support.clear();
    }


    private interface ValueGenerator<T> {
        public T generate();
    }


    /**
     *
     * @param <T>
     * @param type
     * @param generator
     */
    private <T> void test(final Class<T> type,
                          final ValueGenerator<T> generator) {

        // ------------------------------------------------------------------put
        {
            support.clear();
            int count = RANDOM.nextInt(100);
            for (int i = 0; i < count; i++) {
                support.put(type, String.valueOf(i), generator.generate());
            }
            assertEquals(count, support.size(type));
        }

        // ----------------------------------------------------------------- get
        {
            support.clear();
            int count = RANDOM.nextInt(100);
            Map<String, T> map = new HashMap<String, T>();
            for (int i = 0; i < count; i++) {
                String key = String.valueOf(i);
                T val = generator.generate();
                map.put(key, val);
                support.put(type, key, val);
            }

            for (Map.Entry<String, T> entry : map.entrySet()) {
                assertEquals(entry.getValue(),
                             support.get(type, entry.getKey(), null));
            }
        }

        // ----------------------------------------------------------------- remove
        {
            support.clear();
            final int count = RANDOM.nextInt(100);
            for (int i = 0; i < count; i++) {
                support.put(type, String.valueOf(i), generator.generate());
            }
            assertEquals(count, support.size(type));
            for (int i = 0; i < count; i++) {
                support.remove(type, String.valueOf(i));
            }
            assertEquals(0, support.size(type));
        }

    }


    /**
     *
     */
    @Test
    public void testBoolean() {
       test(Boolean.TYPE, new ValueGenerator<Boolean>() {
            public Boolean generate() {
                return ((RANDOM.nextInt() % 2) == 0);
            }
        });
        test(Boolean.class, new ValueGenerator<Boolean>() {
            public Boolean generate() {
                return Boolean.valueOf((RANDOM.nextInt() % 2) == 0);
            }
        });
    }


    /**
     *
     */
    @Test
    public void testByteArray() {
        test(byte[].class, new ValueGenerator<byte[]>() {
            public byte[] generate() {
                final byte[] value = new byte[RANDOM.nextInt(100)];
                RANDOM.nextBytes(value);
                return value;
            }
        });
        test(Byte[].class, new ValueGenerator<Byte[]>() {
            public Byte[] generate() {
                final Byte[] value = new Byte[RANDOM.nextInt(100)];
                for (int i = 0; i < value.length; i++) {
                    value[i] = Byte.valueOf((byte) (RANDOM.nextInt() & 0xFF));
                }
                return value;
            }
        });

    }


    /**
     *
     */
    @Test
    public void testInteger() {
       test(Integer.TYPE, new ValueGenerator<Integer>() {
            public Integer generate() {
                return RANDOM.nextInt();
            }
        });
        test(Integer.class, new ValueGenerator<Integer>() {
            public Integer generate() {
                return RANDOM.nextInt();
            }
        });
    }


    /**
     *
     */
    @Test
    public void testLong() {
       test(Long.TYPE, new ValueGenerator<Long>() {
            public Long generate() {
                return RANDOM.nextLong();
            }
        });
        test(Long.class, new ValueGenerator<Long>() {
            public Long generate() {
                return RANDOM.nextLong();
            }
        });
    }


    /**
     *
     */
    @Test
    public void testFloat() {
        test(Float.TYPE, new ValueGenerator<Float>() {
            public Float generate() {
                return RANDOM.nextFloat();
            }
        });
        test(Float.class, new ValueGenerator<Float>() {
            public Float generate() {
                return RANDOM.nextFloat();
            }
        });
    }


    /**
     *
     */
    @Test
    public void testDouble() {
        test(Double.TYPE, new ValueGenerator<Double>() {
            public Double generate() {
                return RANDOM.nextDouble();
            }
        });
        test(Double.class, new ValueGenerator<Double>() {
            public Double generate() {
                return RANDOM.nextDouble();
            }
        });
    }


    /**
     *
     */
    private final KeyValueSupport support =
        KeyValueSupport.getInstance(KeyValueSupportTest.class);
}
