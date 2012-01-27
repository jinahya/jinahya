package org.dvb.media;

import org.davic.net.tuning.NetworkInterface;
import javax.media.Control;

/**
 * The presence of this Control on a Player indicates that the
 * Player can be used as either a background Player or a
 * component-based Player.  Players for broadcast streaming formats
 * that do not provide this control will always return null from
 * their getVisualComponent() method. <p>
 *
 * A Player that provides this control must continue to provide an
 * instance of it (not necessarily the same instance) after any
 * transitions from background to component-based modes and
 * vice-versa. </p>
 */
public interface ComponentBasedPlayerControl extends Control
{
    /**
     * Transition the associated Player from a component-based Player
     * to a background Player.  Also releases the ownership of the
     * visual component to allow other applications to acquire it.<p>
     *
     * The calling application must own the visual component - i.e. it
     * must have obtained a non-null visual component through
     * Player.getVisualComponent() on the Player associated with this
     * control. </p><p>
     *
     * If the application has called paint() on that visual component,
     * then this method transitions the Player from a component-based
     * Player to a background Player, and releases the visual
     * component.  Otherwise, the Player is still a background Player
     * so this method merely releases the visual component. </p><p>
     *
     * If this call transitions the Player from a component-based
     * Player to a background Player, then the video is intially sized
     * and positioned as close to the last location of the visual
     * component as possible, given the limitations exposed after the
     * transition by org.dvb.media.BackgroundVideoPresentationControl
     * .getClosestMatch() or (equivalently) 
     * javax.tv.media.AWTVideoSizeControl.checkSize(). </p><p>
     *
     * After the transition, applications shall re-acquire the list of
     * JMF controls for the player.  It is implementation dependent
     * whether any previous JMF controls are re-used.  Applications
     * shall not use any previous JMF controls which are not in the
     * new list of JMF controls.  </p><p>
     *
     * The application should ensure that the visual component is
     * removed from the AWT hierarchy before making
     * this call.  After this call, interoperable applications should
     * not make any calls to the visual component aquired before this
     * call.  However, such calls will not affect the Player in any
     * way.  (For example, calling paint() on that visual component
     * will not cause the Player to become a component-based player).
     * The appearence of the visual component if it is
     * displayed after this call is implementation-dependant. </p><p>
     *
     * Future calls to Player.getVisualComponent() will return a
     * visual component that is different from the visual component
     * returned before this call. </p>
     *
     * @throws java.lang.IllegalStateException If the calling
     *     application does not own the visual component.
     * @return true iff the Player has changed from component-based
     *     to background mode.
     */
    public boolean releaseVisualComponent() throws
        java.lang.IllegalStateException;
}


