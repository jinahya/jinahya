/*
 *  Copyright 2010 Jin Kwon.
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
 */

package jinahya.util.processor;



import java.util.Vector;

import jinahya.util.DependencyResolver;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <U> processing unit type
 */
public class FlattenProcessorChain<U extends ProcessingUnit>
    extends ProcessorChain<U> {


    /**
     *
     */
    private static final class FlattenSequencer implements Sequencer {


        @Override
        public Vector<Vector<String>> generate(
            final DependencyResolver resolver) {

            final Vector<Vector<String>> sequence =
                new Vector<Vector<String>>();
            sequence.addElement(resolver.getFlatten());
            return sequence;
        }
    }


    public FlattenProcessorChain() {
        super(new FlattenSequencer());
    }


    @Override
    protected void invoke(final Vector<Vector<Processor<U>>> groups,
                          final U unit)
        throws ProcessorException {

        for (int i = 0; i < groups.size(); i++) {
            final Vector<Processor<U>> group = groups.elementAt(i);
            for (int j = 0; j < group.size(); j++) {
                group.elementAt(j).process(unit);
            }
        }
    }
}