package org.davic.net.ca;

/** Class representing a List MMI object. 
  */
public class List extends MMIObject {


  /* For javadoc to hide the non-public constructor */
  List() {}

  /** Returns the title text. 
    * An empty string is returned, if a title text does not exist.
    * @return the title
    */
  public String getTitleText() {
    return null;
  }
  
  /** Returns the sub-title text. 
    * An empty string is returned, if a subtitle text does not exist.
    * @return the sub-title
    */
  public String getSubTitleText() {
    return null;
  }

  /** Returns the bottom text. 
    * An empty string is returned, if a bottom text does not exist.
    * @return the bottom text
    */
  public String getBottomText(){
    return null;
  }

  /** Returns the list. 
    * An empty array is returned, if a list does not exist.
    * @return an array of strings containing the list items
    */ 
  public String[] getList() {
    return null;
  }
}

