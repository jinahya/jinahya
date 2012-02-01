package org.davic.net.ca;

/** Class representing an enquiry MMI object. 
  */
public class Enquiry extends Text {

  /* For javadoc to hide the non-public constructor */
  Enquiry() {}

  /**
    * @return true if the answer should not be visible while being entered, otherwise false
    */
  public boolean getBlindAnswer() {
    return false;
  }

  /**
    * @return the expected length of the answer in characters
    */
  public short getAnswerLength() {
    return (short) 0;
  }

  /** Submits the answer. 
    * @param answer The answer string. If null, it means that the user 
    *               aborted the dialogue.
    * @exception InvalidSetException raised if the application calls
    *            this method with an invalid value or more than once
    */
  final public void setAnswer(String answer) throws CAException {
  }
}

