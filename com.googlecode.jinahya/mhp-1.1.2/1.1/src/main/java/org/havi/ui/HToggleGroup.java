package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   {@link org.havi.ui.HToggleButton HToggleButtons} within the same
   {@link org.havi.ui.HToggleGroup HToggleGroup} will behave so that a
   maximum of one {@link org.havi.ui.HToggleButton HToggleButton} has
   switchable state true, as returned by {@link
   org.havi.ui.HToggleButton#getSwitchableState getSwitchableState},
   so as to achieve a &quot;radio button&quot; effect. When an {@link
   org.havi.ui.HToggleButton HToggleButton} is acted upon to change
   its switchable state to true, then if any other {@link
   org.havi.ui.HToggleButton HToggleButton} within the {@link
   org.havi.ui.HToggleGroup HToggleGroup} currently has switchable
   state true, it will have its switchable state set to
   false. Similarly, if an {@link org.havi.ui.HToggleButton
   HToggleButton} is added which has switchable state true, then any
   current {@link org.havi.ui.HToggleButton HToggleButton} within the
   {@link org.havi.ui.HToggleGroup HToggleGroup} with switchable state
   true, shall have its switchable state modified to false.
   <p> 
   If the forced selection mode for the {@link
   org.havi.ui.HToggleGroup HToggleGroup} is set via a call to
   <code>setForcedSelection(true)</code> then there will always be one
   {@link org.havi.ui.HToggleButton HToggleButton} selected (i.e. with
   switchable state <code>true</code>), and if necessary the {@link
   org.havi.ui.HToggleGroup HToggleGroup} will automatically force a
   selection to ensure this. If forced selection mode is not set it
   is valid for there to be no selection, i.e. all {@link
   org.havi.ui.HToggleButton HToggleButtons} may have switchable state
   <code>false</code>. By default forced selection mode is not set.
   <p>  
   Note that when an {@link org.havi.ui.HToggleButton HToggleButton}
   has switchable state <code>true</code> this implies that the
   interaction state as returned by {@link
   org.havi.ui.HVisible#getInteractionState getInteractionState} will
   be either the {@link org.havi.ui.HState#ACTIONED_STATE ACTIONED_STATE} or
   {@link org.havi.ui.HState#ACTIONED_FOCUSED_STATE ACTIONED_FOCUSED_STATE} state.
   <p>
   Similarly, a switchable state of <code>false</code> implies that
   the interaction state is any other state for which the {@link
   org.havi.ui.HState#ACTIONED_STATE_BIT ACTIONED_STATE_BIT} is not
   set. See the {@link org.havi.ui.HSwitchable HSwitchable} class
   description for more information about the valid interaction
   states.

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr><td colspan=5>None.</td></tr>
  </table>
  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
   <tr>
       <td> Enable or disable buttons in group</td>
       <td> <code>enable</code></td>
       <td> {@link org.havi.ui.HToggleGroup#setEnabled setEnabled}</td>
       <td> {@link org.havi.ui.HToggleGroup#isEnabled isEnabled}</td>
   </tr>
   <tr>
       <td> Forced selection mode.</td>
       <td> <code>false</code></td>
       <td> {@link org.havi.ui.HToggleGroup#setForcedSelection 
       setForcedSelection}</td>
       <td> {@link org.havi.ui.HToggleGroup#getForcedSelection
       getForcedSelection}</td>
   </tr>
  </table>

*/

public class HToggleGroup
{

    /**
     * Creates a new version instance of an {@link
     * org.havi.ui.HToggleGroup HToggleGroup} 
     */    
    public HToggleGroup()
    {
    }

    /**
     * Returns the {@link org.havi.ui.HToggleButton HToggleButton}
     * from this {@link org.havi.ui.HToggleGroup HToggleGroup} which
     * has state true, or null otherwise, for example, if there are no
     * {@link org.havi.ui.HToggleButton HToggleButtons} associated
     * with this {@link org.havi.ui.HToggleGroup HToggleGroup}, or if
     * all {@link org.havi.ui.HToggleButton HToggleButtons} within
     * this {@link org.havi.ui.HToggleGroup HToggleGroup} have state
     * false.
     * 
     * @return the currently selected {@link org.havi.ui.HToggleButton
     * HToggleButton} or null if no such HToggleButton exists.
     */
    public HToggleButton getCurrent()
    {
        return (null);
    }

    /**
     * If the specified {@link org.havi.ui.HToggleButton
     * HToggleButton} is a member of this {@link
     * org.havi.ui.HToggleGroup HToggleGroup}, then it is selected,
     * its state is set to true and consequently any other {@link
     * org.havi.ui.HToggleButton HToggleButtons} within the {@link
     * org.havi.ui.HToggleGroup HToggleGroup} will have their states
     * set to false.  
     * <p>      
     * If the specified {@link org.havi.ui.HToggleButton
     * HToggleButton} is not a member of this {@link
     * org.havi.ui.HToggleGroup HToggleGroup}, then no actions are
     * performed.
     *
     * @param selection the {@link org.havi.ui.HToggleButton
     * HToggleButton} to be set as the currently selected item within
     * the {@link org.havi.ui.HToggleGroup HToggleGroup}.  
     */
    public void setCurrent(HToggleButton selection)
    {
    }

    /**
     * Set the forced selection mode of the group.  If
     * <code>forceSelection</code> is true and no {@link
     * org.havi.ui.HToggleButton HToggleButton} is currently selected
     * in the group the first {@link org.havi.ui.HToggleButton
     * HToggleButton} automatically has its switchable state set to
     * true. If no {@link org.havi.ui.HToggleButton HToggleButton}
     * components have been added to this group there will be no
     * visual indication of the mode change.
     *
     * @param forceSelection if this parameter is <code>true</code>
     * the group is forced to always have one {@link
     * org.havi.ui.HToggleButton HToggleButton} selected (i.e. with
     * switchable state <code>true</code>). Otherwise, the group may
     * have either one or zero {@link org.havi.ui.HToggleButton
     * HToggleButton(s)} selected.
     */
    public void setForcedSelection(boolean forceSelection)
    {
    }
    
    /** 
     * Return the current forced selection mode of the group.
     * 
     * @return the current forced selection mode. If this value is
     * <code>true</code> the group is forced to always have one
     * HToggleButton selected (i.e. with switchable state
     * <code>true</code>). Otherwise, the group may have either one or
     * zero {@link org.havi.ui.HToggleButton HToggleButton(s)}
     * selected.  
     */
    public boolean getForcedSelection()
    {
	return (false);
    }
            
    /** 
     * Enables or disables the group, depending on the value of the
     * parameter <code>enable</code>.  An enabled group's {@link
     * org.havi.ui.HToggleButton HToggleButtons} can respond to user
     * input and generate events. An {@link org.havi.ui.HToggleGroup
     * HToggleGroup} is initially enabled by default.
     * <p>
     * Enabling or disabling an {@link org.havi.ui.HToggleGroup
     * HToggleGroup} enables or disables all the {@link
     * org.havi.ui.HToggleButton HToggleButton} components in the
     * group by calling their <code>setEnabled</code> methods.
     * <p>
     * Whether or not a group is enabled does not affect the adding or
     * removing of {@link org.havi.ui.HToggleButton HToggleButtons}
     * from that group.
     * 
     * @param enable <code>true</code> to enable all the {@link
     * org.havi.ui.HToggleButton HToggleButton} components in the
     * group, <code>false</code> to disable them.
     * @see HToggleGroup#isEnabled 
     */
    public void setEnabled(boolean enable)
    {
    }
    
    /**
     * Determines whether the {@link org.havi.ui.HToggleGroup
     * HToggleGroup} is enabled. {@link org.havi.ui.HToggleGroup
     * HToggleGroups} are enabled initially by default.  A group
     * may be enabled or disabled by calling its
     * <code>setEnabled</code> method.
     * 
     * @return <code>true</code> if the component is enabled;
     * <code>false</code> otherwise.
     * @see HToggleGroup#setEnabled(boolean)
     */
    public boolean isEnabled()
    {
	return (true);
    }

    /**
     * Add the specified {@link org.havi.ui.HToggleButton
     * HToggleButton} to this {@link org.havi.ui.HToggleGroup
     * HToggleGroup}. The {@link org.havi.ui.HToggleButton
     * HToggleButton} will be enabled or disabled as necessary to
     * match the current state of the {@link org.havi.ui.HToggleGroup
     * HToggleGroup} as determined by the {@link
     * org.havi.ui.HToggleGroup#isEnabled isEnabled} method.
     * <p>
     * If the {@link org.havi.ui.HToggleGroup HToggleGroup} is empty
     * and forced selection mode is active then the {@link
     * org.havi.ui.HToggleButton HToggleButton} will automatically
     * have its switchable state set to <code>true</code>.  Note that
     * any ActionListeners registered with the {@link
     * org.havi.ui.HToggleButton HToggleButton} are not called as a 
     * result of this operation.
     * <p>
     * This method is protected to allow the platform to override it
     * in subclasses of {@link org.havi.ui.HToggleGroup HToggleGroup}.
     * It is not intended for use by the application and conformant
     * applications shall not use this method.
     * 
     * @param button the {@link org.havi.ui.HToggleButton
     * HToggleButton} to add to the {@link org.havi.ui.HToggleGroup
     * HToggleGroup}.  
     */
    protected void add(HToggleButton button)
    {
    }

    /**
     * Remove the specified {@link org.havi.ui.HToggleButton
     * HToggleButton} to this {@link org.havi.ui.HToggleGroup
     * HToggleGroup}. If <code>button</code> is not part of this
     * {@link org.havi.ui.HToggleGroup HToggleGroup} this method 
     * throws a <code>java.lang.IllegalArgumentException</code>.
     * <p>
     * If the {@link org.havi.ui.HToggleButton HToggleButton} is the
     * currently selected button in this group and forced selection
     * mode is set the first remaining {@link
     * org.havi.ui.HToggleButton HToggleButton} will automatically
     * have its switchable state set to true. Note that
     * any ActionListeners registered with the {@link
     * org.havi.ui.HToggleButton HToggleButton} are not called as a 
     * result of this operation.
     * <p>
     * This method is protected to allow the platform to override it
     * in subclasses of HToggleGroup.  It is not intended for
     * use by the application and conformant applications shall not
     * use this method.
     * 
     * @param button the {@link org.havi.ui.HToggleButton
     * HToggleButton} to remove from the {@link org.havi.ui.HToggleGroup
     * HToggleGroup}.  
     * @exception java.lang.IllegalArgumentException if
     * <code>button</code> is not a member of this {@link
     * org.havi.ui.HToggleGroup HToggleGroup}.  
     */
    protected void remove(HToggleButton button)
    {
    }
    
}


