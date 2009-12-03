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

package jinahya.jcff.constant;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class CMethodref extends Cref {


    private static final long serialVersionUID = -7172537409874518955L;


    public CMethodref() {
        super(CONSTANT_Methodref);
    }


    @Override
    public String toString() {
        return "CONSTANT_Methodref: classIndex(" + getClassIndex() +
            "), nameAndTypeIndex(" + getNameAndTypeIndex() + ")";
    }
}
