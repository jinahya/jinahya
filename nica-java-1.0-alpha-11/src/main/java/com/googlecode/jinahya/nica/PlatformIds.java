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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class PlatformIds {


    private static final String NAMESPACE =
        "http://jinahya.googlecode.com/nica/platforms";


    /**
     * Android.
     */
    public static final String ANDROID = NAMESPACE + "/android";


    /**
     * BlackBerry.
     */
    public static final String BLACKBERRY = NAMESPACE + "/blackberry";


    /**
     * iOS.
     */
    public static final String IOS = NAMESPACE + "/ios";


    /**
     * Java TV.
     */
    public static final String JAVA_TV = NAMESPACE + "/java_tv";


    /**
     * Tizen.
     */
    public static final String TIZEN = NAMESPACE + "/tizen";


    /**
     * Unknown.
     */
    public static final String UNKNOWN = NAMESPACE + "/unknown";


    /**
     * Windows Phone.
     */
    public static final String WINDOWS_PHONE = NAMESPACE + "/windows_phone";


    /**
     * Creates a new instance.
     */
    private PlatformIds() {
        super();
    }


}
