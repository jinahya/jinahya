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


package com.googlecode.jinahya.test;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class Source15Test {


    public Source15Test(final int minorVersion, final int majorVersion) {

        super();

        this.minorVersion = minorVersion;
        this.majorVersion = majorVersion;
    }


    @Test
    public void checkVersion() throws IOException {

        try (final InputStream resource =
            Source15Test.class.getResourceAsStream("Source15.class")) {

            Assert.assertNotNull(resource);

            try (final DataInputStream dis = new DataInputStream(resource)) {
                Assert.assertEquals(dis.readInt(), 0xCAFEBABE);
                Assert.assertEquals(dis.readShort(), minorVersion);
                Assert.assertEquals(dis.readShort(), majorVersion);
                System.out.println("class version matched : "
                                   + (majorVersion + "." + minorVersion));
            }
        }
    }


    protected final int minorVersion;


    protected final int majorVersion;


}
