package org.dvb.application;

import java.util.*;

/**
 * The <code>AppAttributes</code> class is a mapping of various information about a registered application. For applications which are signalled in an AIT, the mapping between the values returned by  methods in this class and the fields and descriptors of the AIT shall be as  specified in the main body of the present document.<p> Instances of objects implementing this interface are immutable and populated before the instance is first returned to an application.
 * @since    MHP1.0
 */
public interface AppAttributes{

    /**
    The DVB registered value for all DVB-J applications.
    */
    public static final int DVB_J_application = 1; 

    /**
    The DVB registered value for all DVB-HTML applications.
    */
    public static final int DVB_HTML_application = 2;

    /**
     * This method returns the type of the application (as registered by DVB).
     *
     * @return the type of the application (as registered by DVB).
     * @since MHP1.0
     */
    public int getType();

    /**
     * This method returns the name of the application. If the default language
     * (as specified in user preferences) is in the set of available language / name pairs
     * then the name in that language shall be returned. Otherwise
     * this method will return a name which appears in that set on a "best-effort basis".
     * If no application names are signalled, an empty string shall be returned.
     * @return the name of the application
     *
     * @since   MHP1.0
     */
    public String getName();

    /**
     * This method returns the name of the application in the language which 
     * is specified by the parameter passed as an argument. If the language
     * specified is not in the set of available language /name pairs then an
     * exception shall be thrown.
     *
     * @param iso639code the specified language, encoded as per ISO 639.
     * @return returns the name of the application in the specified language
     * @throws LanguageNotAvailableException if the name is not available in the language specified or if the parameter passed is null
     *
     * @since   MHP1.0
     */
    public String getName(String iso639code) 
	throws LanguageNotAvailableException;

    /**
     * This method returns all the available names for the application
     * together with their ISO 639 language code.  
     * If no application names are signalled, an array of length zero shall be returned.
     *
     * @return the possible names of the application, along with 
     * their ISO 639 language code. The first string in each sub-array is the 
     * ISO 639 language code.
     * The second string in each sub-array is the corresponding application name.
     * @since   MHP1.0
     */
    public String[][] getNames () ;

    /**
     * This method returns those minimum profiles required for the application
     * to execute. Profile
     * names shall be encoded using the same encoding specified elsewhere in this
     * specification as input for use with the <code>java.lang.System.getProperty</code>
     * method to query if a profile is supported by this platform. <p>For example,
     * for implementations conforming to the first version of the 
     * specification, the translation from AIT signaling values to strings
     * shall be as follows:
     * <ul>
     * <li> '1' in the signaling will be translated into 
     * 'mhp.profile.enhanced_broadcast'
     * <li> '2' in the signaling will be translated into 
     * 'mhp.profile.interactive_broadcast'
     * </ul>
     *
     * Only profiles supported by this particular MHP terminal shall be returned.
     * Hence the method can return an array of size zero where all the profiles
     * on which an application can execute are unknown.
     *
     * @return an array of Strings, each String describing a profile.
     *
     * @since   MHP1.0
     */
    public String[] getProfiles();
    
    /**
     * This method returns an array of integers containing the version
     * number of the specification required to run this application
     * at the specified profile. 
     *
     * @param profile a profile encoded as described in the main body of
     * the present document for use with <code>java.lang.System.getProperty</code>.
     *
     * @return an array of integers, containing the major, minor 
     * and micro values (in that order) required for the specified profile. 
     * @throws IllegalProfileParameterException thrown if the profile specified
     * is not one of the minimum profiles required for the application to execute
     * or if the parameter passed in is null
     * @since   MHP1.0
     */
    public int[] getVersions(String profile) 
	throws IllegalProfileParameterException ;

    /**
     * This method determines whether the application is bound to a single service.
     *
     * @return true if the application is bound to a single service, false otherwise. 
     * @since   MHP1.0
     */
    public boolean getIsServiceBound () ;

    /**
     * This method determines whether the application is startable or not.
     * An Application is not startable if any of the following apply.<ul>

     * <li>The application is transmitted on a remote connection.

     * <li>The caller of the method does not have the Permissions to start it.
     * <li>if the application is signalled with a control code which is neither AUTOSTART nor PRESENT.
     * </ul>
     * If none of the above apply, then the application is startable.
     * <p> The value returned by this method does not depend on whether the 
     * application is actually running or not.
     * 
     * @return true if an application is startable, false otherwise.
     *
     * @since   MHP1.0
     */
    public boolean isStartable () ;

    /**
     * This method returns the application identifier. 
     *
     * @return the application identifier
     * @since   MHP1.0
     */
    public AppID getIdentifier () ;

    /**
     * This method returns an object encapsulating the information about the
     * icon(s) for the application.
     *
     * @return the information related to the icons that
     * are attached to the application or null if no icon information is available
     * @since   MHP1.0
     */
    public AppIcon getAppIcon () ;

    /**
     * This method returns the priority of the application.
     *
     * @return the priority of the application.
     *
     * @since   MHP1.0
     */
    public int getPriority();

    /**
     * This method returns the locator of the Service describing the application. 
     * For an application transmitted on a remote connection, the returned
     * locator shall be the service for that remote connection. For applications
     * not transmitted on a remote connection, the service returned shall be the
     * currently selected service of the service context within which the application 
     * calling the method is running.
     * 
     * @return the locator of the Service describing the application.
     * @since   MHP1.0
     */
    public org.davic.net.Locator getServiceLocator();


    /**
     * The following method is included for properties that do not have 
     * explicit property accessors. The naming of properties and their return
     * values are described in the main body of the present document.
     *
     * @since   MHP1.0
     * 
     * @param index a property name
     * @return either the return value corresponding to the property name or null
     * if the property name is unknown or null
     */
    public Object getProperty (String index) ;


    /**
     * This method determines whether the application is marked as being
     * visible to users. An inter-operable application shall honour this
     * visibility setting. Thus a generic launching application shall list
     * applications that are marked as visible and shall not list
     * applications that are not marked as visible.
     *
     * @return  <code>true</code> if this application is marked as being
     *          visible to users, <code>false</code> otherwise.
     * @since   MHP1.0.3
     */
    public boolean isVisible();

}

