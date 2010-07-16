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


//import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>&nbsp;
 *         <form name="_xclick" action="https://www.paypal.com/cgi-bin/webscr"
 *               method="post">
 *           <input type="hidden" name="cmd" value="_xclick">
 *           <input type="hidden" name="business" value="jinahya@gmail.com">
 *           <input type="hidden" name="item_name" value="Jinahya">
 *           <input type="hidden" name="currency_code" value="EUR">
 *           <input type="hidden" name="amount" value="1.00">
 *           <input type="image"
 *                  src="http://www.paypal.com/en_US/i/btn/btn_donate_LG.gif"
 *                  border="0" name="submit" alt="PayPal!">
 *         </form>
 */
public class VerticallyConcurrentProcessorChainTest {


    private ProcessorChain<HitCounterUnit> newChain(final int size)
        throws ProcessorException {

        final ProcessorChain<HitCounterUnit> chain =
            new VerticallyConcurrentProcessorChain<HitCounterUnit>(size);


        for (int i = 0; i < 20; i++) {
            final int e = (i + 1) * 5;
            for (int j = i * 5 + 1; j < e; j++) {
                chain.addProcessor(new HitCounterProcessor(
                    Integer.toString(j), Integer.toString(j - 1)));
            }
        }

        for (String requiredProcessorId : chain.getRequiredProcessorIds()) {
            chain.addProcessor(new HitCounterProcessor(requiredProcessorId));
        }

        return chain;
    }


    @Test
    public void testVerticalConcurrency() throws ProcessorException {

        System.out.println("---------- VerticallyConcurrentProcessorChainTest");

        newChain(1).print(System.out);

        for (int i = 1; i < 10; i++) {
            System.out.print("---------------------------- thread count: " + i);
            final long start = System.currentTimeMillis();
            newChain(i).invoke(new HitCounterUnit());
            System.out.println(
                ": elapsed: " + (System.currentTimeMillis() - start));
        }
    }
}
