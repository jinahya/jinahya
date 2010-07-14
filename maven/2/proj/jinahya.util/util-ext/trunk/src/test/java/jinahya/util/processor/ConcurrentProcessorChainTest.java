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
            new ConcurrentProcessorChain(HitCounter.class, 2);

        for (int i = 1; i < 5; i++) {
            chain.addProcessor(new HitCounterProcessor(
                Integer.toString(i), Integer.toString(i - 1)));
        }

        for (int i = 6; i < 10; i++) {
            chain.addProcessor(new HitCounterProcessor(
                Integer.toString(i), Integer.toString(i - 1)));
        }

        for (String requiredProcessorId : chain.getRequiredProcessorIds()) {
            chain.addProcessor(new HitCounterProcessor(requiredProcessorId));
        }

        final HitCounter unit = new HitCounter();

        System.out.println("------------------------ invoking with one thread");
        chain.setSize(1);
        chain.invoke(unit);

        System.out.println("----------------------- invoking with two threads");
        chain.setSize(2);
        chain.invoke(unit);
    }
}
