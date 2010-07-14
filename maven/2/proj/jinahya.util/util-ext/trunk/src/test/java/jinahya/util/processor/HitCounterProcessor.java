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


import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class HitCounterProcessor extends Processor<HitCounter> {


    private static final Random RANDOM = new Random();


    public static synchronized HitCounterProcessor newInstance() {

        final int id = RANDOM.nextInt(128);

        final List<String> prerequisiteIdList = new LinkedList<String>();

        final int size = RANDOM.nextInt(5);
        for (int i = 0; i < size; i++) {
            prerequisiteIdList.add(0, Integer.toString(RANDOM.nextInt(512)));
        }

        final String[] prerequisiteIds = new String[prerequisiteIdList.size()];

        prerequisiteIdList.toArray(prerequisiteIds);

        return new HitCounterProcessor(Integer.toString(id), prerequisiteIds);
    }


    public HitCounterProcessor(final String id,
                               final String[] prerequisiteIds) {

        super(HitCounter.class, id, prerequisiteIds);
    }


    @Override
    public void process(final HitCounter unit) throws ProcessorException {
        unit.hit();
    }
}
