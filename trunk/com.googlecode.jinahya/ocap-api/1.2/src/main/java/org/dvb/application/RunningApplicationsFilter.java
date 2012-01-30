package org.dvb.application ;

/**
 * Instances of <code>RunningApplicationsFilter</code> 
 * are used to set a filter on the list of applications that are retrieved
 * from the AppsDatabase (See methods getAppsAttributes and getAppsIDs).<p>
 * A <code>RunningApplicationsFilter</code> is used to indicate that only applications
 * that are running as part of the current service shall be 
 * returned by the getAppsAttributes and getAppIDs methods of AppsDatabase.
 * Externally authorized applications in the AIT shall be returned if they are
 * currently running in the same service context as the caller.
 * Running applications whose visibility is '00' shall not be returned.
 * Subclasses of RunningApplicationsFilter can override the accept method so as
 * to implement their own filter criteria on the AppIDs values.
 * 
 * @since MHP 1.0
 */

public class RunningApplicationsFilter extends AppsDatabaseFilter {

 /**
  * public Constructor of the RunningApplicationsFilter
  **/
  public RunningApplicationsFilter() {super();}

 /**
  * 
  * Test if a specified appid should be included in the Enumeration.
  * @param appid the specified appid to test.
  * @return  true if the application with identifier appid should be listed, false otherwise.
  * @since   MHP1.0
  */
    public boolean accept(AppID appid) { return false;}

}

