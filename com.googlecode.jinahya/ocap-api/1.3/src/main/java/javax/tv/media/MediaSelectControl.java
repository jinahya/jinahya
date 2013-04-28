/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.tv.media;

import javax.tv.locator.*;
import javax.tv.service.selection.*;

/** 
 * <code>MediaSelectControl</code> allows the selection of different
 * kinds of content in a running <code>Player</code>.  It serves as
 * a high level demultiplex control, where the selection is specified
 * by locators indicating one or more service components to
 * present. <p>
 *
 * If the <code>Player</code> on which a
 * <code>MediaSelectControl</code> operates is an instance of
 * <code>ServiceMediaHandler</code>, then
 * <code>MediaSelectControl</code> is restricted to operating only on
 * service components belonging to the service with which the
 * <code>ServiceMediaHandler</code> is associated (that is, the
 * <code>ServiceContext</code>'s currently selected service).
 * A <code>MediaSelectControl</code> which is not associated with
 * a <code>ServiceMediaHandler</code> may also have restrictions
 * on the set of services which it may access.
 * <p>
 *
 * Instances of <code>MediaSelectControl</code> may be obtained from a
 * JMF <code>Player</code> via the methods
 * <code>getControl(String)</code> and <code>getControls()</code>.
 * A Java TV API implementation may not always or ever
 * support <code>MediaSelectControl</code> for a given Player; in such
 * a case, the failure modes specified by the two aforementioned
 * methods will apply.
 * <p>
 * Applications should note that the set of selected service components
 * can be changed by entities other than themselves,
 * <span style="color: red;">
 * or as a side-effect of calls to
 * {@link javax.tv.service.selection.ServiceContext#select(Locator[])}.
 * </span>
 * Such changes will be reported to the <code>MediaSelectListener</code>s
 * currently registered.
 *
 * @see javax.media.Player
 * @see javax.media.Controller#getControls
 * @see ServiceMediaHandler
 * @see ServiceContext
 */
public interface MediaSelectControl extends javax.media.Control
{

    /** 
     * Selects a new service component for presentation.  If some
     * content is currently playing, it is replaced in its entirety by
     * the specified selection.  This is an asynchronous operation
     * that is completed upon receipt of a
     * <code>MediaSelectEvent</code>.  Note that for certain
     * selections that imply a different time base or otherwise change
     * synchronization relationships, a <code>RestartingEvent</code>
     * will be posted by the <code>Player</code>.
     * <p>
     * <span style="color: red;">
     * If the specified component is already being presented,
     * it will continue to be presented and will not be restarted.
     * A {@link MediaSelectSucceededEvent} is generated immediately.
     * </span>
     * 
     * @param component A locator representing an individual service
     * component to present.
     *
     * @throws InvalidLocatorException If the locator does not reference
     * a selectable service component.
     *
     * @throws InvalidServiceComponentException If the specified
     * service component is not part of the set of services
     * to which the <code>MediaSelectControl</code> is restricted, or if
     * it cannot be presented alone.
     *
     * @throws InsufficientResourcesException If the operation cannot be
     * completed due to a lack of system resources.
     *
     * @throws SecurityException If the caller does not have
     * <code>MediaSelectPermission(component)</code> permission.  
     */
    public void select(Locator component)
        throws InvalidLocatorException, InvalidServiceComponentException,
        InsufficientResourcesException, SecurityException;

    /** 
     * Selects one or more service components for presentation.  If
     * some content is currently playing, it is replaced in its
     * entirety by the specified selection. This is an asynchronous
     * operation that is completed on receipt of a
     * <code>MediaSelectEvent</code>.
     * <p>
     * <span style="color: red;">
     * If some of the specified components are already being presented,
     * these components continue to be presented and will not be
     * restarted.  Their selection is considered successful and is
     * immediately reported by a {@link MediaSelectSucceededEvent}.
     * </span>
     * If some of the specified components are successfully selected and
     * others are not, a <code>MediaSelectSucceededEvent</code> is
     * generated with the locator array containing only those
     * components that were successfully selected.
     * <p>
     * Note that for certain
     * selections that imply a different time base or otherwise change
     * synchronization relationships, a <code>RestartingEvent</code>
     * will be posted by the <code>Player</code>.
     *
     * @param components An array of locators representing a set of
     * individual service components to present together.
     *
     * @throws InvalidLocatorException If a locator provided does not
     * reference a selectable service component.
     *
     * @throws InvalidServiceComponentException If a specified service
     * component is not part of the set of services to which
     * the <code>MediaSelectControl</code> is restricted, if a
     * specified service component must be presented in conjunction
     * with another service component not contained in
     * <code>components</code>, if the specified set
     * of service components cannot be presented as a coherent whole,
     * or if the service components are not all available
     * simultaneously.
     *
     * @throws InsufficientResourcesException If the operation cannot be
     * completed due to a lack of system resources.
     *
     * @throws SecurityException If the caller does not have
     * <code>MediaSelectPermission(components[i])</code> permission
     * for any valid <code>i</code>.
     *
     * @throws IllegalArgumentException If <code>components</code> is a
     * zero-length array.
     *
     */
    public void select(Locator[] components)
        throws InvalidLocatorException, InvalidServiceComponentException,
        InsufficientResourcesException, SecurityException;

    /** 
     * Adds a service component (for example, subtitles) to the
     * presentation. This is an asynchronous operation that is
     * completed on receipt of a <code>MediaSelectEvent</code>.
     * Components whose addition would require Player
     * resynchronization are not permitted.  If the specified service
     * component is already part of the presentation, this method does
     * nothing.
     * 
     * @param component The locator representing an individual service
     * component to add to the presentation.
     *
     * @throws InvalidLocatorException If the specified locator does
     * not reference a selectable service component.
     *
     * @throws InvalidServiceComponentException If the addition of the
     * service component would require resynchronization of the
     * <code>Player</code>, if the service component
     * is not part of the set of services to which the
     * <code>MediaSelectControl</code> is restricted, or if the service
     * component must be presented in conjunction with another service
     * component that is not part of the current presentation.
     *
     * @throws InsufficientResourcesException If the operation cannot be
     * completed due to a lack of system resources.
     *
     * @throws SecurityException If the caller does not have
     * <code>MediaSelectPermission(component)</code> permission.
     */
    public void add(Locator component)
        throws InvalidLocatorException, InvalidServiceComponentException,
        InsufficientResourcesException, SecurityException;

    /** 
     * Removes a service component from the presentation. This is an
     * asynchronous operation that is completed on receipt of a
     * <code>MediaSelectEvent</code>. Components whose removal would
     * require Player resynchronization are not permitted.
     * 
     * @param component The locator representing an individual service
     * component to remove from the presentation.
     *
     * @throws InvalidLocatorException If the specified locator does
     * not reference a service component in the current selection.
     *
     * @throws InvalidServiceComponentException If removal of the
     * specified service component would require resynchronization of
     * the <code>Player</code>, or if another service component in
     * the current presentation must be presented in conjunction with
     * <code>component</code>.
     *
     * @throws SecurityException If the caller does not have
     * <code>MediaSelectPermission(component)</code> permission.
     */
    public void remove(Locator component)
        throws InvalidLocatorException, InvalidServiceComponentException,
        SecurityException;

    /** 
     * Replaces a service component in the presentation. This is an
     * asynchronous operation that is completed on receipt of a
     * <code>MediaSelectEvent</code>. Components whose replacement
     * would require Player resynchronization are not permitted.
     * 
     * @param fromComponent The locator that represents the service
     * component to remove from the presentation.
     *
     * @param toComponent The locator that represents the service
     * component to add to the presentation.
     *
     * @throws InvalidLocatorException If <code>fromComponent</code>
     * does not reference a service component in the current
     * selection, or if <code>toComponent</code> does not reference a
     * selectable service component.
     *
     * @throws InvalidServiceComponentException If
     * <code>toComponent</code> references a service component that is
     * not part of the set of services to which the
     * <code>MediaSelectControl</code> is restricted, if
     * <code>fromComponent</code> or <code>toComponent</code> reference
     * service components for which this operation would require
     * resynchronization of the <code>Player</code>, if another service
     * component in the current presentation must be presented in
     * conjunction with <code>fromComponent</code>, or if
     * <code>toComponent</code> must be presented in conjunction with a
     * service component not in the resulting presentation.
     *
     * @throws InsufficientResourcesException If the operation cannot be
     * completed due to a lack of system resources.
     *
     * @throws SecurityException If the caller does not have
     * <code>MediaSelectPermission(fromComponent)</code> and
     * <code>MediaSelectPermission(toComponent)</code> permission.
     */
    public void replace(Locator fromComponent, Locator toComponent)
        throws InvalidLocatorException, InvalidServiceComponentException,
        InsufficientResourcesException, SecurityException;

    /** 
     * Subscribes the specified <code>MediaSelectListener</code> to
     * receive events related to media selection on this Player.
     * <p>
     * No action is performed if the specified <code>MediaSelectListener</code>
     * is already subscribed.
     *
     * @param listener The <code>MediaSelectListener</code> to which
     * to send events.
     */
    public void addMediaSelectListener(MediaSelectListener listener);

    /** 
     * Unsubscribes the specified <code>MediaSelectListener</code> from
     * receiving events related to media selection on this Player.
     * <p>
     * No action is performed if the specified <code>MediaSelectListener</code>
     * is not subscribed.
     *
     * @param listener The <code>MediaSelectListener</code> to unsubscribe.
     */
    public void removeMediaSelectListener(MediaSelectListener listener);

    /** 
     * Reports the components of the current selection.
     *
     * @return An array of locators representing the service components
     * in the current selection.
     */
    public Locator[] getCurrentSelection();
}
