
package org.davic.net;

/** Locator that encapsulates an URL into an object
  */

public abstract class Locator implements javax.tv.locator.Locator {

  private Locator() {
  }

  /** Constructor for the locator 
    * @param url a URL string
    */
  public Locator(String url) {
  }

  /** Returns the URL string representation
    * @return a URL string
    */
  public String toString() {
    return (null);
  }

  /** Indicates whether this locator maps to multiple 
    * transport dependent locators
    * @return true, if and only if this locator maps to more than one
    *         transport dependent locator
    */
  public boolean hasMultipleTransformations() {
    return false;
  }

  /** Returns a URL string corresponding to the locator
    * If the instance of <code>Locator</code> has been created using 
    * <code>Locator(java.lang.String url)</code> and the URL is a
    * non-null invalid URL the behaviour is implementation dependent.
    * @return a URL string
    */
  public String toExternalForm() {
    return null;
  }

}


