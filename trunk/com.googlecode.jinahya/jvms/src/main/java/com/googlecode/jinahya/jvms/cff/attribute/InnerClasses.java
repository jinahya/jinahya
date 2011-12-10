/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.jvms.cff.attribute;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @see <a href="http://goo.gl/MpYsa">4.7.5 The InnerClasses Attribute</a>
 */
//@XmlType(name = "SourceFile")
public class InnerClasses extends Attribute {


    @Override
    protected void readContent(final DataInput input) throws IOException {

        getInnerClasses().clear();
        final int numberOfClasses = input.readUnsignedShort();
        for (int i = 0; i < numberOfClasses; i++) {
            final InnerClass innerClass = new InnerClass();
            innerClass.read(input);
            getInnerClasses().add(innerClass);
        }
    }


    @Override
    protected void writeContent(final DataOutput output) throws IOException {

        output.writeShort(getInnerClasses().size());
        for (InnerClass innerClass : getInnerClasses()) {
            innerClass.write(output);
        }
    }


    private List<InnerClass> getInnerClasses() {

        if (innerClasses == null) {
            innerClasses = new ArrayList<InnerClass>();
        }

        return innerClasses;
    }


    @XmlElement(name="innerClass")
    private List<InnerClass> innerClasses;


}

