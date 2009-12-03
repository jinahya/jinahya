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
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ARuntimeInvisibleParameterAnnotations extends Attribute {


    private static final long serialVersionUID = 6692501111172011516L;


    @Override
    protected void readInfo(DataInput in)
        throws IOException {

        int numparameters = in.readShort() & 0xFFFF;
        for (int i = 0; i < numparameters; i++) {
            Parameter instance = new Parameter();
            instance.read(in);
            getparameter().add(instance);
        }
    }


    @Override
    protected void writeInfo(DataOutput out) throws IOException {
        out.writeShort(getparameter().size());
        for (Parameter instance : getparameter()) {
            instance.write(out);
        }
    }


    @XmlElementWrapper(name = "parameters")
    @XmlElement
    public List<Parameter> getparameter() {
        if (parameter == null) {
            parameter = new ArrayList<Parameter>();
        }
        return parameter;
    }


    public void setparameter(List<Parameter> parameter) {
        this.parameter = parameter;
    }


    private List<Parameter> parameter;
}
