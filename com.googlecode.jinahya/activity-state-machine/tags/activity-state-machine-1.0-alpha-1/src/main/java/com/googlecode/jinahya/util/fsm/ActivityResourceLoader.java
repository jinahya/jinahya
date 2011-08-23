/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.util.fsm;


import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;


/**
 * A <code>ResourceLoader</code> implementation uses {@link AssetManager}s for
 * loading resources.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ActivityResourceLoader implements ResourceLoader {


    /**
     * Creates a new instance.
     *
     * @param assetManager the <code>AssetManager</code> for opening asset file.
     */
    public ActivityResourceLoader(final AssetManager assetManager) {
        super();

        if (assetManager == null) {
            throw new NullPointerException("null assetManager");
        }

        this.assetManager = assetManager;
    }


    @Override
    public InputStream load(final String resourceName)
        throws IOException, FSMException {

        return assetManager.open(resourceName);
    }


    /** asset manager. */
    private final AssetManager assetManager;
}

