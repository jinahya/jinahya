/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.codec;


import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;
import java.util.logging.Logger;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Test(groups = {"benchmark"}, singleThreaded = true)
public class BossVsEngineerTestCaliperDecode extends SimpleBenchmark {


  private static final Logger LOGGER =
      Logger.getLogger(BossVsEngineerTestCaliperDecode.class.getName());


  public static void main(final String[] args) {
    Runner.main(BossVsEngineerTestCaliperDecode.class, args);
  }


  @Test
  public void main() {
    main(new String[0]);
  }


  @Override
  protected void setUp() throws Exception {
    super.setUp();

    encoded = HexCodecTestUtils.newEncodedBytes();
  }


  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }


  public void timeDecodeLikeABoss(final int reps) {

    for (int i = 0; i < reps; i++) {
      new HexDecoder().decodeLikeABoss(encoded);
    }
  }


  public void timeDecodeLikeAnEngineer(final int reps) {

    for (int i = 0; i < reps; i++) {
      new HexDecoder().decodeLikeAnEngineer(encoded);
    }
  }


  private byte[] encoded;


}

