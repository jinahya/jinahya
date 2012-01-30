package org.ocap.media;

import javax.tv.service.Service;

/**
 * This class allows an application to block presentation of A/V content
 * based on program content rating signaling or by specification of
 * {@link BlockedService}s.  It also allows an application to register
 * an application-provided {@link MediaAccessHandler} to block presentation.
 * This class also allows overriding (disabling/enabling) of blocking settings.
*/
public abstract class MediaAccessHandlerRegistrar
{
    /**
     * Constructor of MediaAccessHandlerRegistrar.  
     * An application must use the 
     * <code>{@link MediaAccessHandlerRegistrar#getInstance}</code> 
     * method to create an instance.
     */
    protected MediaAccessHandlerRegistrar()
    {
    }

    /**
     * This method returns the sole instance of the 
     * {@link MediaAccessHandlerRegistrar} class. The 
     * {@link MediaAccessHandlerRegistrar} instance 
     * is a singleton.
     * @return The <code>{@link MediaAccessHandlerRegistrar}</code>
     * 			instance.
     */
    public static MediaAccessHandlerRegistrar getInstance()
    {
        return null; 
    }

    /**
     * Registers the handler that can prevent the presentation of A/V service
     * components.
     * <p>
     * At most, only one instance of MediaAccessHandler can be set. Multiple
     * calls of this method replace the previous instance by a new one. 
     * By default, no MediaAccessHandler is set, in which case the 
     * MediaAccessHandler.checkMediaAccessAuthorization() method is not called.
     * 
     * @param mah The MediaAccessHandler to register. If null, the
     *            MediaAccessHandler instance will be removed.
     * @throws SecurityException if the caller does not have 
     * 			MonitorAppPermission("mediaAccess") permission.
     * @see MediaAccessHandler
     */
    public abstract void registerMediaAccessHandler(MediaAccessHandler mah);

    /**
     * Sets parental control blocking based on VBI parental control or
     * content_advisory_descriptor signaling.  This method MAY be used to
     * set separate blocking for TV and movie ratings.  The constants in
     * the {@link ParentalControlRatings} interface provide legal values
     * for the rating parameter.  The values for TV and MPAA ratings can
     * be OR'ed together to set both rating types at the same time.  A
     * non-zero value for either rating type will over-write the value
     * for that rating type.  A zero value for either rating type will 
     * not change the value of that rating type.  
     * <p>
     * This method will not block the TV_NONE or MPAA_NOT_RATED ratings.
     * Blocking not rated programs can be accomplished with the
     * <code>setSignaledNotRatedBlocking</code> method. The default setting
     * for the TV ratings is TV_NONE and for the movie ratings is MPAA_NA.
     * Setting the default values will turn off all rated and signaled
     * blocking.
     * <p>When this method is called, the
     * implementation SHALL re-evaluate blocking for immediate effect on
     * all presenting services.
     * 
     * @param rating The rating to set blocking at.  This can be 
     *      a TV_* constant or an MPAA_* constant, or both, ORed together.
     * 
     * @throws SecurityException if the caller does not have 
     *      MonitorAppPermission("mediaAccess") permission.
     * @throws IllegalArgumentException if rating is not equal to one of
     *      the constant values in <code>ParentalControlRatings</code>
     *      constants, or if rating is not equal to two of the constant
     *      values OR'ed together.  If two of the constants are OR'ed
     *      together and both are TV_* or both are MPAA_* constants.  If
     *      more than two of the constants are OR'ed together.
     */
    public abstract void setSignaledBlocking(int rating);

    /**
     * Gets the signaled rating blocking value set by 
     * <code>setSignaledBlocking</code> or the default defined by that
     * method.  The value returned is the TV rating blocking value
     * OR'ed together with the MPAA rating blocking value.  The TV
     * and MPAA rating blocking values can be seen in
     * {@link ParentalControlRatings}.
     * 
     * @return The TV and movie rating blocking values.
     */
    public abstract int getSignaledBlocking();

    /**
     * Sets signaled blocking override on or off.  Setting the override to on
     * SHALL over-ride all parental rating blocking that have been set by
     * calls to the <code>setSignaledBlocking</code> or
     * <code>setNotRatedSignaledBlocking</code> methods.  This does not
     * modifying a setting made by that method.  Setting the override to
     * off SHALL restore parental blocking.  Override is off by default.
     * <p>When this method is called, the
     * implementation SHALL re-evaluate blocking for immediate effect on
     * all presenting services.
     * 
     * @param action When true, sets override on; otherwise, sets override
     *      off. 
     * @throws SecurityException if the caller does not have 
     *      MonitorAppPermission("mediaAccess") permission.
     */
    public abstract void setSignaledBlockingOverride(boolean action);

    /**
     * Gets the signaled blocking override.
     * 
     * @return True if signaled blocking over-ride is on; otherwise, returns
     *      false.
     */
    public abstract boolean getSignaledBlockingOverride();


    /**
     * Sets blocking for TV blocking rating TV_NONE or movie blocking
     * rating MPAA_NOT_RATED.  By default these ratings are not blocked.
     * Regardless of rating blocking settings made by the 
     * <code>setSignaledBlocking</code> method, only the TV_NONE and
     * MPAA_NOT_RATED ratings can be blocked by this method.  When
     * the block parameter is true for a not rated rating, indicated by
     * the rating parameter, not rated programs signaled with that rating
     * via VBI parental control or content_advisory_descriptor signaling
     * SHALL be blocked.  This method SHALL NOT affect blocking of programs
     * with any other parental control rating.
     * <p>When this method is called, the
     * implementation SHALL re-evaluate blocking for immediate effect on
     * all presenting services.
     * 
     * @param rating Can be a value of
     *      {@link ParentalControlRatings#TV_NONE} or 
     *      {@link ParentalControlRatings#MPAA_NOT_RATED}.
     * @param block When set to true will block, otherwise will unblock.
     * 
     * @throws IllegalArgumentException if the rating parameter is not
     *      TV_NONE or MPAA_NOT_RATED.
     * @throws SecurityException if the caller does not have 
     *      MonitorAppPermission("mediaAccess") permission.
     */
    public abstract void setNotRatedSignaledBlocking(int rating, boolean block);

    /**
     * Gets blocking setting for TV blocking rating TV_NONE or movie
     * blocking rating MPAA_NOT_RATED.
     * 
     * @param rating Can be a value of
     *      {@link ParentalControlRatings#TV_NONE} or 
     *      {@link ParentalControlRatings#MPAA_NOT_RATED}.
     * 
     * @return True if the parameter rating is currently blocked, otherwise
     *      returns false.
     * 
     * @throws IllegalArgumentException if the rating parameter is not
     *      TV_NONE or MPAA_NOT_RATED.
     */
    public abstract boolean getNotRatedSignalBlocking(int rating);

    /**
     * Sets services to be blocked.  If the start time of a blocked service
     * entry in the parameter has a duration in the past and the corresponding
     * content is not in a buffer the entry MAY be ignored.  The
     * implementation SHALL evaluate the parameter for immediate effect on
     * all presenting services.
     * 
     * @throws SecurityException if the caller does not have 
     * 			MonitorAppPermission("mediaAccess") permission.
     */
    public abstract void setServiceBlocking(BlockedService [] blockedServices);

    /**
     * Sets service blocking override on or off, based on the level associated
     * with a <code>BlockedService</code>.  Setting the override to on
     * SHALL override all parental rating blocking that have been set by
     * calls to the <code>setServiceBlocking</code> method for a specific
     * set of levels.  This includes all levels equal to and in between
     * the startLevel and endLevel parameters.  This does not modify a
     * settings made by the <code>setServiceBlocking</code> method.
     * Setting the override to off SHALL restore parental blocking for the
     * set of levels.  All override values are set to false before evaluating
     * the parameters.  Override is turned off completely by passing a
     * false action and any start and end levels.  Override is off by default
     * for all levels.
     * <p>
     * When this method is called, the implementation SHALL re-evaluate
     * blocking for immediate effect on all presenting services.
     * 
     * @param startLevel The first level in the set of levels to override.
     * @param endLevel The last level in the set of levels to override.
     * @param action When true sets over-ride on, otherwise sets override
     *      off. 
     * 
     * @throws SecurityException if the caller does not have 
     *      MonitorAppPermission("mediaAccess") permission.
     */
    public abstract void setServiceBlockingOverride(int startLevel,
                                                    int endLevel,
                                                    boolean action);

    /**
     * Gets the service blocking override for a specific level.
     * 
     * @param level The level to get override blocking for.
     * 
     * @return True if service blocking override is on for the level;
     *      otherwise, returns false.
     */
    public abstract boolean getServiceBlockingOverride(int level);

    /**
     * Removes services to be blocked that were set by the
     * <code>setServiceBlocking</code> method.  If an entry in the blocked
     * services parameter is not currently blocked it MAY be ignored.  The
     * implementation SHALL evaluate the parameter for immediate effect on
     * all presenting services.
     * 
     * @throws SecurityException if the caller does not have 
     * 			MonitorAppPermission("mediaAccess") permission.
     */
    public abstract void removeServiceBlocking(
                                            BlockedService [] blockedServices);

    /**
     * Sets the implementation behavior for services that have not been blocked
     * for any parental rating reason.  The default behavior is a registered
     * <code>MediaAccessHandler</code> is notified and the implementation
     * SHALL call the <code>checkMediaAccessAuthorization</code> method
     * in that case.  When the <code>notifyMediaAccessHandler</code>
     * parameter is false it indicates the <code>MediaAccessHandler</code>
     * is not to be informed when a service that is not blocked is selected
     * or changes. 
     *  
     * @param notifyMediaAccessHandler When true indicates a registered
     *      <code>MediaAccessHandler</code> SHALL be informed when service
     *      component presentation changes and the service has not been blocked;
     *      otherwise, a registered <code>MediaAccessHandler</code> is not
     *      informed.
     * 
     * @throws SecurityException if the caller does not have 
     * 			MonitorAppPermission("mediaAccess") permission.
     */
    public abstract void setNotifyCondition(boolean notifyMediaAccessHandler);

    /**
     * Gets the <code>MediaAccessHandler</code> notify condition set by the
     * <code>setNotifyCondition</code> method.
     * 
     * @return Value set by the <code>setNotifyCondition</code> method.  If the
     *      <code>setNotifyCondition</code> method has not been called this
     *      method returns the default value of true.
     */
    public abstract boolean getNotifyCondition();

    /**
     * Grants media access authorization for a service by removing
     * presentation denial caused by a previous call to
     * {@link MediaAccessHandler#checkMediaAccessAuthorization}
     * for all components in a service.  If any component in the
     * <code>Service</code> parameter was not denied by a previous call
     * to <code>checkMediaAccessAuthorization</code> this method does
     * nothing successfully.  This method does not affect blocking
     * setup by other <code>MediaAccessHandlerRegistrar</code>
     * methods.
     * 
     * @param service The service to grant access to.
     */
    public abstract void grantMediaAccessAuthorization(Service service);
}
