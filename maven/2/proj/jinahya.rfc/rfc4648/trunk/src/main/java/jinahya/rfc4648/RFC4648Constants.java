/*
 *  Copyright 2009 Jin Kwon.
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
 *  under the License.
 */

package jinahya.rfc4648;


/**
 * Constants for rfc4648.
 *
 * @author <a href="mailto:jinahya@gmail.com>Jin Kwon</a>
 */
public final class RFC4648Constants {


    /** Default pad character. '=' */
    public static final char PAD = '=';


    /** Alphabet for 'base64'. */
    public static final String BASE64 =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZabcdef" +
        "ghijklmnopqrstuv" +
        "wxyz0123456789+/";


    /** Alphabet for 'base64url'. */
    public static final String BASE64URL =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZabcdef" +
        "ghijklmnopqrstuv" +
        "wxyz0123456789-_";


    /** Alphabet for 'base32'. */
    public static final String BASE32 =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZ234567";


    /** Alphabet for 'base32hex'. */
    public static final String BASE32HEX =
        "0123456789ABCDEF" +
        "GHIJKLMNOPQRSTUV";


    /** Alphabet for 'base16'. */
    public static final String BASE16 =
        "0123456789ABCDEF";


    private RFC4648Constants() {
        super();
    }
}
