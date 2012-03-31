package org.dvb.application ;

/**
 * Instances of <code>CurrentServiceFilter</code> 
 * are used to set a filter on the list of applications that are retrieved
 * from the AppsDatabase (See methods getAppsAttributes and getAppsIDs).<p>
 * A <code>CurrentServiceFilter</code> is used to indicate that only applications
 * that signalled as part of the current service shall be 
 * returned by the getAppsAttributes and getAppIDs methods of AppsDatabase.
 * Externally authorized applications in the AIT are not considered to be signalled as 
 * part of the current service for this filter.
 * Subclasses of CurrentServiceFilter can override the accept method so as
 * to implement their own filter criteria on the AppIDs values.
 * 
 * @since MHP 1.0
 */

public class CurrentServiceFilter extends AppsDatabaseFilter {

 /**
  * public Constructor of the CurrentServiceFilter
  **/
  public CurrentServiceFilter() {super();}

 /**
  * 
  * Test if a specified appid should be included in the Enumeration.
  * @param appid the specified appid to test.
  * @return  true if the application with identifier appid should be listed, false otherwise.
  * @since   MHP1.0
  */
    public boolean accept(AppID appid) { return false;}

}

