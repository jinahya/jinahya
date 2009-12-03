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

package jinahya.jcff.attribute;


import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import jinahya.jcff.DataContainer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Parameter implements Serializable, DataContainer {


    private static final long serialVersionUID = 8572525066040653733L;


    @Override
    public void readData(DataInput in) throws IOException {
        int numAnnotations = in.readShort() & 0xFFFF;
        for (int i = 0; i < numAnnotations; i++) {
            Annotation instance = new Annotation();
            instance.readData(in);
            getAnnotation().add(instance);
        }
    }


    @Override
    public void writeData(DataOutput out) throws IOException {
        out.writeShort(getAnnotation().size());
        for (Annotation instance : getAnnotation()) {
            instance.writeData(out);
        }
    }


    @XmlElementWrapper(name="annotations")
    @XmlElement
    public List<Annotation> getAnnotation() {
        if (annotation == null) {
            annotation = new ArrayList<Annotation>();
        }
        return annotation;
    }


    public void setAnnotation(List<Annotation> annotation) {
        this.annotation = annotation;
    }


    private List<Annotation> annotation;
}
