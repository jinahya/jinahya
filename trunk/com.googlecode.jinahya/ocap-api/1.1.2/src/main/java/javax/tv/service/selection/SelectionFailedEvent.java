/*
 * @(#)SelectionFailedEvent.java	1.13 00/08/28
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.tv.service.selection;

/**
 * <code>SelectionFailedEvent</code> is generated when a service
 * selection operation fails.  <code>SelectionFailedEvent</code> is
 * not generated when a service selection fails with an exception. <p>
 *
 * Presentation failures enforced via a conditional access system may
 * be reported by this event (with the reason code CA_REFUSAL) or by
 * <code>AlternativeContentEvent.</code> Which of these is used
 * depends on the precise nature of the conditional access
 * system. Applications must allow for both modes of failure.
 *
 * @see AlternativeContentEvent
 **/

public class SelectionFailedEvent extends ServiceContextEvent {

  /**
   * Reason code : Selection has been interrupted by another selection
   * request.
   */
  public final static int INTERRUPTED = 1;
  
  /**
   *
   * Reason code : Selection failed due to the CA system refusing to
   * permit it.
   */
  public final static int CA_REFUSAL = 2 ;
  
  /**
   * Reason code : Selection failed because the requested content
   * could not be found in the network.
   */
  public final static int CONTENT_NOT_FOUND = 3;
  
  /**
   * Reason code : Selection failed due to absence of a 
   * <code>ServiceContentHandler</code> required to present the requested
   * service.
   *
   * @see ServiceContentHandler
   */
  public final static int MISSING_HANDLER = 4;
  
  /**
   * Reason code : Selection failed due to problems with tuning.
   */
  public final static int TUNING_FAILURE = 5;
  
  /**
   *
   * Reason code : Selection failed due to a lack of resources required to
   * present this service.
   */
  public final static int INSUFFICIENT_RESOURCES = 6;

  
  /**
   * Constructs the event with a reason code.
   *
   * @param source The <code>ServiceContext</code> that generated the event.
   * @param reason The reason why the selection failed.
   */  
  public SelectionFailedEvent(ServiceContext source, int reason)
  {
    super(null);
  }

  /**
   *
   * Reports the reason why the selection failed.
   *
   * @return The reason why the selection failed.
   */
  public int getReason()
  {
    return 0;
  }

}	

