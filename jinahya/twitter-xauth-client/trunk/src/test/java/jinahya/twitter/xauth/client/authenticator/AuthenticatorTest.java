/*
 * Copyright 2011 Jin Kwon.
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


package jinahya.twitter.xauth.client.authenticator;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class AuthenticatorTest<T extends Authenticator> {


    public AuthenticatorTest(final T authenticator) {
        super();

        if (authenticator == null) {
            throw new IllegalArgumentException("null authenticator");
        }

        this.authenticator = authenticator;
    }


    private static final Object[][] RFC2202_TEST_CASES = new Object[][]{
        new Object[]{
            new byte[]{0x0b, 0x0b, 0x0b, 0x0b, 0x0b, 0x0b, 0x0b, 0x0b, 0x0b,
                       0x0b, 0x0b, 0x0b, 0x0b, 0x0b, 0x0b, 0x0b, 0x0b, 0x0b,
                       0x0b, 0x0b},
            20,
            "Hi There",
            8,
            new byte[]{(byte) 0xb6, (byte) 0x17, (byte) 0x31, (byte) 0x86,
                       (byte) 0x55, (byte) 0x05, (byte) 0x72, (byte) 0x64,
                       (byte) 0xe2, (byte) 0x8b, (byte) 0xc0, (byte) 0xb6,
                       (byte) 0xfb, (byte) 0x37, (byte) 0x8c, (byte) 0x8e,
                       (byte) 0xf1, (byte) 0x46, (byte) 0xbe, (byte) 0x00}
        },
        new Object[]{
            "Jefe",
            4,
            "what do ya want for nothing?",
            28,
            new byte[]{(byte) 0xef, (byte) 0xfc, (byte) 0xdf, (byte) 0x6a,
                       (byte) 0xe5, (byte) 0xeb, (byte) 0x2f, (byte) 0xa2,
                       (byte) 0xd2, (byte) 0x74, (byte) 0x16, (byte) 0xd5,
                       (byte) 0xf1, (byte) 0x84, (byte) 0xdf, (byte) 0x9c,
                       (byte) 0x25, (byte) 0x9a, (byte) 0x7c, (byte) 0x79}
        },
        new Object[]{
            new byte[]{(byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa},
            20,
            new byte[]{(byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd,
                       (byte) 0xdd, (byte) 0xdd},
            50,
            new byte[]{(byte) 0x12, (byte) 0x5d, (byte) 0x73, (byte) 0x42,
                       (byte) 0xb9, (byte) 0xac, (byte) 0x11, (byte) 0xcd,
                       (byte) 0x91, (byte) 0xa3, (byte) 0x9a, (byte) 0xf4,
                       (byte) 0x8a, (byte) 0xa1, (byte) 0x7b, (byte) 0x4f,
                       (byte) 0x63, (byte) 0xf1, (byte) 0x75, (byte) 0xd3}
        },
        new Object[]{
            new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
                       0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12,
                       0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19},
            25,
            new byte[]{(byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd, (byte) 0xcd, (byte) 0xcd,
                       (byte) 0xcd, (byte) 0xcd},
            50,
            new byte[]{(byte) 0x4c, (byte) 0x90, (byte) 0x07, (byte) 0xf4,
                       (byte) 0x02, (byte) 0x62, (byte) 0x50, (byte) 0xc6,
                       (byte) 0xbc, (byte) 0x84, (byte) 0x14, (byte) 0xf9,
                       (byte) 0xbf, (byte) 0x50, (byte) 0xc8, (byte) 0x6c,
                       (byte) 0x2d, (byte) 0x72, (byte) 0x35, (byte) 0xda}
        },
        new Object[]{
            new byte[]{0x0c, 0x0c, 0x0c, 0x0c, 0x0c, 0x0c, 0x0c, 0x0c, 0x0c,
                       0x0c, 0x0c, 0x0c, 0x0c, 0x0c, 0x0c, 0x0c, 0x0c, 0x0c,
                       0x0c, 0x0c},
            20,
            "Test With Truncation",
            20,
            new byte[]{(byte) 0x4c, (byte) 0x1a, (byte) 0x03, (byte) 0x42,
                       (byte) 0x4b, (byte) 0x55, (byte) 0xe0, (byte) 0x7f,
                       (byte) 0xe7, (byte) 0xf2, (byte) 0x7b, (byte) 0xe1,
                       (byte) 0xd5, (byte) 0x8b, (byte) 0xb9, (byte) 0x32,
                       (byte) 0x4a, (byte) 0x9a, (byte) 0x5a, (byte) 0x04},
            new byte[]{(byte) 0x4c, (byte) 0x1a, (byte) 0x03, (byte) 0x42,
                       (byte) 0x4b, (byte) 0x55, (byte) 0xe0, (byte) 0x7f,
                       (byte) 0xe7, (byte) 0xf2, (byte) 0x7b, (byte) 0xe1}
        },
        new Object[]{
            new byte[]{(byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa},
            80,
            "Test Using Larger Than Block-Size Key - Hash Key First",
            54,
            new byte[]{(byte) 0xaa, (byte) 0x4a, (byte) 0xe5, (byte) 0xe1,
                       (byte) 0x52, (byte) 0x72, (byte) 0xd0, (byte) 0x0e,
                       (byte) 0x95, (byte) 0x70, (byte) 0x56, (byte) 0x37,
                       (byte) 0xce, (byte) 0x8a, (byte) 0x3b, (byte) 0x55,
                       (byte) 0xed, (byte) 0x40, (byte) 0x21, (byte) 0x12}
        },
        new Object[]{
            new byte[]{(byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa,
                       (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa},
            80,
            "Test Using Larger Than Block-Size Key and Larger Than One "
            + "Block-Size Data",
            73,
            new byte[]{(byte) 0xe8, (byte) 0xe9, (byte) 0x9d, (byte) 0x0f,
                       (byte) 0x45, (byte) 0x23, (byte) 0x7d, (byte) 0x78,
                       (byte) 0x6d, (byte) 0x6b, (byte) 0xba, (byte) 0xa7,
                       (byte) 0x96, (byte) 0x5c, (byte) 0x78, (byte) 0x08,
                       (byte) 0xbb, (byte) 0xff, (byte) 0x1a, (byte) 0x91}
        }
    };


    @Test
    public void testRFC2022() throws Exception {

        for (Object[] vector : RFC2202_TEST_CASES) {
            byte[] key;
            if (vector[0] instanceof String) {
                key = ((String) vector[0]).getBytes();
            } else {
                key = (byte[]) vector[0];
            }
            Assert.assertEquals(key.length, ((Integer) vector[1]).intValue());

            final byte[] input;
            if (vector[2] instanceof String) {
                input = ((String) vector[2]).getBytes();
            } else {
                input = (byte[]) vector[2];
            }

            Assert.assertEquals(input.length, ((Integer) vector[3]).intValue());

            final byte[] output = (byte[]) vector[4];

            Assert.assertEquals(authenticator.authenticate(key, input), output);
        }
    }


    private T authenticator;
}
