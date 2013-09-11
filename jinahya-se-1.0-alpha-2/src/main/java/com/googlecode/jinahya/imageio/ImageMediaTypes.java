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
public class ImageMediaTypes extends ImageFeatures<ImageMediaType> {


    public static ImageMediaTypes newInstance() {

        final ImageMediaTypes instance = new ImageMediaTypes();

        for (final String readerMIMEType : ImageIO.getReaderMIMETypes()) {
            ImageMediaType imageMediaType =
                instance.getImageFeatures().get(readerMIMEType);
            if (imageMediaType == null) {
                imageMediaType = new ImageMediaType();
                instance.getImageFeatures().put(
                    readerMIMEType, imageMediaType);
            }
            imageMediaType.setReadable(true);
            imageMediaType.setKey(readerMIMEType);
        }

        for (final String writerMIMEType : ImageIO.getWriterMIMETypes()) {
            ImageMediaType imageMediaType =
                instance.getImageFeatures().get(writerMIMEType);
            if (imageMediaType == null) {
                imageMediaType = new ImageMediaType();
                instance.getImageFeatures().put(
                    writerMIMEType, imageMediaType);
            }
            imageMediaType.setWritable(true);
            imageMediaType.setKey(writerMIMEType);
        }

        return instance;
    }


    @XmlElement(name = "imageMediaType", nillable = true)
    private List<ImageMediaType> getImageMediaTypeList() {

        return getImageFeatureList();
    }


    private void setImageMediaTypeList(
        final List<ImageMediaType> imageMediaTypeList) {

        setImageFeatureList(imageMediaTypeList);
    }


    public Map<String, ImageMediaType> getImageMediaTypes() {

        return getImageFeatures();
    }


}
