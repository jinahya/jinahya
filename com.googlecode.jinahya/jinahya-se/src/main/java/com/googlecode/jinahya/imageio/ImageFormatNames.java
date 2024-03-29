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


import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@XmlRootElement
public class ImageFormatNames extends ImageFeatures<ImageFormatName> {


    public static ImageFormatNames newInstance() {

        final ImageFormatNames instance = new ImageFormatNames();

        for (final String readerFormatName : ImageIO.getReaderFormatNames()) {
            ImageFormatName imageFormatName =
                instance.getImageFeatures().get(readerFormatName);
            if (imageFormatName == null) {
                imageFormatName = new ImageFormatName();
                instance.getImageFeatures().put(
                    readerFormatName, imageFormatName);
            }
            imageFormatName.setReadable(true);
            imageFormatName.setValue(readerFormatName);
        }

        for (final String writerFileSuffix : ImageIO.getWriterFileSuffixes()) {
            ImageFormatName imageFormatName =
                instance.getImageFeatures().get(writerFileSuffix);
            if (imageFormatName == null) {
                imageFormatName = new ImageFormatName();
                instance.getImageFeatures().put(
                    writerFileSuffix, imageFormatName);
            }
            imageFormatName.setWritable(true);
            imageFormatName.setValue(writerFileSuffix);
        }

        return instance;
    }


    @XmlElement(name = "imageFormatName")
    private List<ImageFormatName> getImageFormatNameList() {

        return getImageFeatureList();
    }


    private void setImageFormatNameList(
        final List<ImageFormatName> imageFormatNameList) {

        setImageFeatureList(imageFormatNameList);
    }


    public Map<String, ImageFormatName> getImageFormatNames() {

        return getImageFeatures();
    }


}
