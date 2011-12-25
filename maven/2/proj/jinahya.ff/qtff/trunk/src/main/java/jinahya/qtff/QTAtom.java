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

package jinahya.qtff;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class QTAtom extends Atom {


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public List<QTAtom> getChild() {
        if (child == null) {
            child = new ArrayList<QTAtom>();
        }
        return child;
    }


    public void setChild(List<QTAtom> child) {
        this.child = child;
    }
    

    private int id;
    private List<QTAtom> child;
}