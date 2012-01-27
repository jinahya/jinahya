package org.dvb.application.storage;

import java.util.*;

/**
 * <p>
 * The <code>ExtendedAppAttributes</code> interface provides
 * additional attributes that are useful when application 
 * can be stored in the MHP terminal.</p>
 * <p>{@link org.dvb.application.AppAttributes} objects that are returned
 * from the {@link org.dvb.application.AppsDatabase} shall implement this
 * interface if and only if the terminal supports storing applications and
 * the total amount of memory for stored services and/or
 * cached applications is greater than zero.
 *
 * @since   MHP1.1
 */
public interface ExtendedAppAttributes
    extends org.dvb.application.AppAttributes
{


    /** Returns the optional version number currently signalled for 
      * this application.  (E.g. in the AIT).  If no version number
      * is signalled, -1 shall be returned.
      *
      * @return the version number signalled for this application.
      * @since MHP1.1
      */
    public int getCurrentVersionNumber();


    /** Returns <code>true</code> if this application is signalled as capable
     * of being added to a stored service.  I.e. if this application
     * could be added to a stored service without the
     * {@link org.dvb.application.storage.InvalidApplicationException}
     * being thrown.
     * <p>
     * For broadcast applications, returns <code>true</code> if and only
     * if this application is signalled as being storable and as capable
     * of running stand alone.  For applications that are part of a
     * stored service, this function returns <code>true</code>.
     *
     * @return <code>true</code> if this application is signalled as
     *         capable of being added to a stored service.
     * @since MHP1.1.2
     */
   public boolean canAddToStoredService();


   /** Returns <code>true</code> if this application is signalled as capable
     * of being cached.  I.e. if this application could be added to a
     * cache without the
     * {@link org.dvb.application.storage.InvalidApplicationException}
     * being thrown.
     * <p>
     * For broadcast applications, returns <code>true</code> if and only
     * if this application is signalled as being storable.  For
     * applications that are part of a  stored service, this function
     * returns <code>true</code>.
     *
     * @return <code>true</code> if this application is signalled as
     *         capable of being cached.
     * @since MHP1.1.2
     */
   public boolean canCache();


   /** Returns <code>true</code> if this application is signalled as not launchable
     * from broadcast.  I.e. for a broadcast application this method
     * will return <code>true</code> if and only if the application
     * is signalled with an application storage descriptor where
     * not_launchable_from_broadcast is "1".
     * If this application is part of a stored service, this
     * function returns <code>true</code>.
     * <p>
     * Note that the return value of this method does not depend
     * on whether or not the application is currently stored
     * or cached.
     *
     * @return <code>true</code> if and only if this application
     *         is signalled as not launchable from broadcast.
     * @since MHP1.1.2
     */
   public boolean isStorageRequired();


   /** Returns <code>true</code> if this application is currently stored
     * or cached on the terminal.  Returns <code>true</code>
     * if and only if the application is signalled as being
     * storable and all the files signalled as having critical
     * priority have been stored or cached by the terminal.
     *
     * @return <code>true</code> if this application
     *         is currently stored or cached.
     * @since MHP1.1.2
     */
   public boolean isStored();


   /**
   * This method determines whether the application is startable or not.
   * An Application is not startable if any of the following apply.<ul>
   * <li>The application is transmitted on a remote connection,
   *    and either does not have an application storage descriptor,
   *    is not cached, or is not signalled as launchable completely
   *    from cache.</li>
   * <li>The caller of the method does not have the Permissions to start it.
   * <li>if the application is signalled with a control code which is
   *    neither AUTOSTART nor PRESENT.
   * <li>if the application is signalled with an application storage
   *    descriptor where not_launchable_from_broadcast is "1",
   *    and the application is not stored or cached.
   * </ul>
   * If none of the above apply, then the application is startable.
   * <p> The value returned by this method does not depend on whether the
   * application is actually running or not.
   *
   * @return true if an application is startable, false otherwise.
   *
   * @since   MHP1.0
   */
   public boolean isStartable();

}


