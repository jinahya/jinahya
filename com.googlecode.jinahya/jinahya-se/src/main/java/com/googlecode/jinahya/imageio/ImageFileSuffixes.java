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
public class ImageFileSuffixes extends ImageIODescriptors<ImageFileSuffix> {


    public static ImageFileSuffixes newInstance() {

        final ImageFileSuffixes instance = new ImageFileSuffixes();

        for (final String readerFileSuffix : ImageIO.getReaderFileSuffixes()) {
            ImageFileSuffix imageFileSuffix =
                instance.getImageDescriptors().get(readerFileSuffix);
            if (imageFileSuffix == null) {
                imageFileSuffix = new ImageFileSuffix();
                instance.getImageDescriptors().put(
                    readerFileSuffix, imageFileSuffix);
            }
            imageFileSuffix.setReadable(true);
            imageFileSuffix.setKey(readerFileSuffix);
        }

        for (final String writerFileSuffix : ImageIO.getWriterFileSuffixes()) {
            ImageFileSuffix imageFileSuffix =
                instance.getImageDescriptors().get(writerFileSuffix);
            if (imageFileSuffix == null) {
                imageFileSuffix = new ImageFileSuffix();
                instance.getImageDescriptors().put(
                    writerFileSuffix, imageFileSuffix);
            }
            imageFileSuffix.setWritable(true);
            imageFileSuffix.setKey(writerFileSuffix);
        }

        return instance;
    }


    @XmlElement(name = "imageFileSuffix", nillable = true)
    private List<ImageFileSuffix> getImageFileSuffixList() {

        return getImageDescriptorList();
    }


    private void setImageFileSuffixList(
        final List<ImageFileSuffix> iamgeFileSuffixList) {

        setImageDescriptorList(iamgeFileSuffixList);
    }


    public Map<String, ImageFileSuffix> getImageFileSuffixes() {

        return getImageDescriptors();
    }


}
