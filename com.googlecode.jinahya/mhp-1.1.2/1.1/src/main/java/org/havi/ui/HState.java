package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   The {@link org.havi.ui.HState HState} interface encapsulates
   constants for component states which are used in the various {@link
   org.havi.ui.HVisible HVisible} setContent and getContent methods
   to indicate which state the specified content is to be set.

   <p>
   There are two sets of constants defined in this interface. The
   first set are mutually exclusive state bits, which define
   properties of the component. The order of the states is important;
   each state has precedence over the one before it when considering
   the effect on the component. For example, the
   {@link org.havi.ui.HState#DISABLED_STATE_BIT DISABLED_STATE_BIT}
   property is considered the most significant property of a state. The
   state bits are shown in the table below.

   <p><table border>
   <tr><th>Name</th><th>Interpretation</th></tr>
   <tr><td>{@link org.havi.ui.HState#FOCUSED_STATE_BIT
   FOCUSED_STATE_BIT}</td><td>component has focus</td></tr>
   <tr><td>{@link org.havi.ui.HState#ACTIONED_STATE_BIT
   ACTIONED_STATE_BIT}</td><td>component is actioned</td></tr>
   <tr><td>{@link org.havi.ui.HState#DISABLED_STATE_BIT
   DISABLED_STATE_BIT}</td><td>component is disabled</td></tr>
   </table><p>

   The state bits combine to provide the actual interaction states
   which {@link org.havi.ui.HVisible HVisible} components may be
   in. Content for components is set on these states, not the state
   bits shown above. The interaction states are shown in the table
   below.

   <p><table border>
   <tr><th>State</th><th>DISABLED_BIT</th><th>ACTIONED_BIT</th>
   <th>FOCUSED_BIT</th></tr>
   <tr><td>{@link org.havi.ui.HState#NORMAL_STATE NORMAL_STATE}
   </td><td>0</td><td>0</td><td>0</td></tr>
   <tr><td>{@link org.havi.ui.HState#FOCUSED_STATE FOCUSED_STATE}
   </td><td>0</td><td>0</td><td>1</td></tr>
   <tr><td>{@link org.havi.ui.HState#ACTIONED_STATE ACTIONED_STATE}
   </td><td>0</td><td>1</td><td>0</td></tr>
   <tr><td>{@link org.havi.ui.HState#ACTIONED_FOCUSED_STATE
   ACTIONED_FOCUSED_STATE}</td><td>0</td><td>1</td><td>1</td></tr>
   <tr><td>{@link org.havi.ui.HState#DISABLED_STATE DISABLED_STATE}
   </td><td>1</td><td>0</td><td>0</td></tr>
   <tr><td>{@link org.havi.ui.HState#DISABLED_FOCUSED_STATE
   DISABLED_FOCUSED_STATE}</td><td>1</td><td>0</td><td>1</td></tr>
   <tr><td>{@link org.havi.ui.HState#DISABLED_ACTIONED_STATE
   DISABLED_ACTIONED_STATE}</td><td>1</td><td>1</td><td>0</td></tr>
   <tr><td>{@link org.havi.ui.HState#DISABLED_ACTIONED_FOCUSED_STATE
   DISABLED_ACTIONED_FOCUSED_STATE}</td><td>1</td><td>1</td><td>1</td></tr>
   </table><p>

   @see HVisible#setTextContent
   @see HVisible#getTextContent
   @see HVisible#setGraphicContent
   @see HVisible#getGraphicContent
   @see HVisible#setAnimateContent
   @see HVisible#getAnimateContent   
   @see HVisible#setContent
   @see HVisible#getContent
   @see HVisible#setInteractionState
   @see HVisible#getInteractionState */

public interface HState
{
    /** 
     * This state bit indicates that the widget has the input
     * focus. This state is only valid for widgets implementing
     * HNavigable. If state-based content is not used, the associated
     * look should visually distinguish components with this bit set
     * e.g. by highlighting them.  
     */
    public static final int FOCUSED_STATE_BIT   = 0x01;
    
    /** 
     * This state bit indicates that the widget has been
     * actioned. {@link org.havi.ui.HActionable HActionable} components
     * only have this bit set for the duration of the calls to their
     * registered ActionListeners, whereas {@link org.havi.ui.HSwitchable
     * HSwitchable} components may remain with the ACTIONED bit set
     * until further user input causes them to leave it. If
     * state-based content is not used, the associated look should
     * visually distinguish components with this bit set e.g. by
     * drawing them as "pushed in".  
     */
    public static final int ACTIONED_STATE_BIT  = 0x02;
    
    /** 
     * This state bit indicates that the component is
     * disabled. Regardless of other bits being set it
     * shall not respond to user action, shortcut keys or
     * mouse clicks. However the component is only
     * disabled at the HAVi level. It may still receive
     * AWT events directly, which interoperable components
     * must ignore.  If state-based content is not used
     * the associated look should visually distinguish
     * components with this bit set e.g. by graying them
     * out.  
     */
    public static final int DISABLED_STATE_BIT  = 0x04;

    /**
     * Constant used to indicate the value of the first (builtin)
     * component state. 
     */
    public static final int FIRST_STATE = 0x80;

    /** 
     * This constant (i.e. no state bits set) indicates that the
     * widget is in its normal state. This state is applicable to all
     * {@link org.havi.ui.HVisible HVisible} components.
     * 
     * @see HVisible 
     */
    public static final int NORMAL_STATE  = 0x80;
    
    /**
     * This state indicates that the widget has input focus. This
     * state is applicable to all {@link org.havi.ui.HNavigable
     * HNavigable} components.
     * 
     * @see HNavigable 
     */
    public static final int FOCUSED_STATE  = 0x81;
    
    /** 
     * This state indicates that the widget has been actioned, but
     * does not have focus. {@link org.havi.ui.HSwitchable HSwitchable}
     * components may stay in this state until they are actioned
     * again.  This state is applicable to all
     * {@link org.havi.ui.HActionable HActionable} and
     * {@link org.havi.ui.HSwitchable HSwitchable} components.
     * 
     * @see HActionable 
     * @see HSwitchable 
     */
    public static final int ACTIONED_STATE  = 0x82;
    
    /**
     * This state indicates that the widget has been actioned, and
     * has focus. {@link org.havi.ui.HSwitchable HSwitchable}
     * components may stay in this state until they are actioned
     * again. This state is applicable to all
     * {@link org.havi.ui.HActionable HActionable} and
     * {@link org.havi.ui.HSwitchable HSwitchable} components.
     * 
     * @see HActionable 
     * @see HSwitchable 
     */
    public static final int ACTIONED_FOCUSED_STATE  = 0x83;
    
    /**
     * This state indicates that the widget is
     * disabled.  This state is applicable to all
     * {@link org.havi.ui.HVisible HVisible} components.
     * 
     * @see HVisible 
     */
    public static final int DISABLED_STATE  = 0x84;
    
    /** 
     * This state indicates that the widget has input focus but is
     * disabled.  This state is applicable to all {@link
     * org.havi.ui.HNavigable HNavigable} components.
     * 
     * @see HNavigable 
     */
    public static final int DISABLED_FOCUSED_STATE  = 0x85;
	
    /** 
     * This state indicates that the widget has been actioned but is
     * disabled.  This state is applicable to all {@link
     * org.havi.ui.HSwitchable HSwitchable} components.
     * 
     * @see HSwitchable
     */
    public static final int DISABLED_ACTIONED_STATE  = 0x86;
    
    /**
     * This state indicates that the widget has been actioned and has
     * input focus but is disabled.  This state is applicable to all
     * {@link org.havi.ui.HSwitchable HSwitchable} components.
     * 
     * @see HSwitchable 
     */
    public static final int DISABLED_ACTIONED_FOCUSED_STATE  = 0x87;

    /**
     * Constant used to indicate all of the applicable states for a
     * given component.
     * <p>
     * Note that the <code>ALL_STATES</code> constant should only be
     * used in setting content
     * <ul>
     * {@link org.havi.ui.HVisible#setTextContent
     * HVisible.setTextContent}
     * {@link org.havi.ui.HVisible#setGraphicContent
     * HVisible.setGraphicContent}
     * {@link org.havi.ui.HVisible#setAnimateContent
     * HVisible.setAnimateContent}
     * {@link org.havi.ui.HVisible#setContent HVisible.setContent}
     * </ul>
     * <p>
     * The <code>ALL_STATES</code> constant should not be used for
     * retrieving content:
     * <ul>
     * {@link org.havi.ui.HVisible#getTextContent
     * HVisible.getTextContent}
     * {@link org.havi.ui.HVisible#getGraphicContent
     * HVisible.getGraphicContent}
     * {@link org.havi.ui.HVisible#getAnimateContent
     * HVisible.getAnimateContent}
     * {@link org.havi.ui.HVisible#getContent HVisible.getContent}
     * </ul> 
     */
    public static final int ALL_STATES = 0x07;

    /**
     * Constant used to indicate the value of the last (builtin)
     * component state.  
     */
    public static final int LAST_STATE = 0x87;

}











