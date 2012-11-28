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


package com.googlecode.jinahya.nica;


import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.AesBC;
import com.googlecode.jinahya.nica.util.Hac;
import com.googlecode.jinahya.nica.util.HacBC;
import com.googlecode.jinahya.nica.util.ParME;
import java.util.Hashtable;
import javax.tv.xlet.XletContext;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class JavaTVHeaders extends HeadersME {


    /**
     *
     * @param names
     * @param context
     * @param key
     * @return
     */
    public static AbstractHeaders newInstance(final Hashtable names,
                                      final XletContext context,
                                      final byte[] key) {

        return new JavaTVHeaders(ParME.encode(names), new JavaTVCodes(context),
                                 new AesBC(key), new HacBC(key));
    }


    /**
     *
     * @param name
     * @param codes
     * @param aes
     * @param hac
     */
    public JavaTVHeaders(final String name, final CodesME codes, final Aes aes,
                         final Hac hac) {
        super(name, codes, aes, hac);
    }


}

