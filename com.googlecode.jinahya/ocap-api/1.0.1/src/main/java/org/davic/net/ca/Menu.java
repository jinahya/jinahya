package org.davic.net.ca;


/** Class representing a Menu MMI. 

  */

public class Menu extends List {


  /* For javadoc to hide the non-public constructor */

  Menu() {}

  

  /** Submit the user's choice from the menu. 

    * @param choice the number of the users choice. The first item in the 

    *               list corresponds to value 1. Value 0 means that the 

    *               user exited the menu without making a choice.

    * @exception InvalidSetException raised if the application calls

    *            this method with an invalid value or more than once

    */

  final public void setChoice(short choice) throws CAException {

  }

}


