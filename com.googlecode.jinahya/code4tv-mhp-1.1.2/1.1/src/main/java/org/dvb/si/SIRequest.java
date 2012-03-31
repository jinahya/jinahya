package org.dvb.si;

/** Object instances of this class represent SI retrieval requests
  * made by the application. The application may cancel the request using
  * this object.
  * 
  */

public class SIRequest {

  /**
    * This constructor is provided for the use of implementations and specifications which 
    * extend the present document. Applications shall not define sub-classes of this class.
    * Implementations are not required to behave correctly if any such application defined 
    * sub-classes are used.
    */
  protected SIRequest() {
  }
  
  /** Returns whether the information will be returned from cache
    * or from the stream
    * @return true if the information is available in the cache and will be returned from 
    * there otherwise false
    */
  public boolean isAvailableInCache() {
    return true;
  }

  /** Cancels the retrieval request. <p>
    * @return true if the request was cancelled and an 
    *          SIRequestCancelledEvent will be delivered to the listener, 
    *          false if the request has already completed (either 
    *          successfully, with an error or due to a prior cancel method 
    *          call) 
    */  
  public boolean cancelRequest() {
    return true;
  }
  
  
}


