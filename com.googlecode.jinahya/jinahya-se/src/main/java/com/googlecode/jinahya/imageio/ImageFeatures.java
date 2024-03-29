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


package com.googlecode.jinahya.imageio;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <T>
 */
@XmlTransient
public abstract class ImageFeatures<T extends ImageFeature> {


    List<T> getImageFeatureList() {

        final Collection<T> values = getImageFeatures().values();

        if (values instanceof List) {
            return (List<T>) values;
        }

        return new ArrayList<T>(values);
    }


    void setImageFeatureList(final List<T> imageFeatureList) {

        for (T imageFeature : imageFeatureList) {
            getImageFeatures().put(imageFeature.getValue(), imageFeature);
        }
    }


    Map<String, T> getImageFeatures() {

        if (imageFeatures == null) {
            imageFeatures = new TreeMap<String, T>();
        }

        return imageFeatures;
    }


    private Map<String, T> imageFeatures;


}
