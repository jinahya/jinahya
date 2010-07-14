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


import java.util.Random;
import java.util.Vector;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ConcurrentProcessorChainTest {


    private static final Random RANDOM = new Random();


    @Test
    public void test() throws ProcessorException {

        final ConcurrentProcessorChain<HitCounter> chain =
            new ConcurrentProcessorChain(HitCounter.class, 1);

        final int count = RANDOM.nextInt(128);
        for (int i = 0; i < count; i++) {
            Processor<HitCounter> processor = HitCounterProcessor.newInstance();
            chain.addProcessor(processor);
        }

        for (String requiredId : chain.getRequiredProcessorIds()) {
            chain.addProcessor(new HitCounterProcessor(requiredId, null));
        }

        final Vector<Vector<String>> groups = chain.getHorizontalGroups();

        final int size1 = groups.size();
        chain.setSize(size1);

        long start1 = System.currentTimeMillis();
        chain.invoke(new HitCounter());

        final long elapsed1 = System.currentTimeMillis() - start1;


        int size2 = RANDOM.nextInt(size1) + 1;
        if (size2 > 1) {
            size2--;
        }
        chain.setSize(size2);

        long start2 = System.currentTimeMillis();
        chain.invoke(new HitCounter());
        final long elapsed2 = System.currentTimeMillis() - start2;


        System.out.println(groups.size());
        System.out.println(
            size1 + ":" + elapsed1 + " / " + size2 + ":" + elapsed2);

        //Assert.assertTrue((elapsed1 <= elapsed2));
    }
}
