package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
   This interface is implemented for all HAVi component containers
   that support the manipulation of the z-ordering of their children. 
   
   @see HScene
   @see HContainer
*/

public interface HComponentOrdering
{
    /**      
     * Adds a <code>java.awt.Component</code> to this {@link
     * org.havi.ui.HComponentOrdering HComponentOrdering} directly in front of a
     * previously added <code>java.awt.Component</code>.
     * <p>
     * If <code>component</code> has already been added to this
     * container, then <code>addBefore</code> moves
     * <code>component</code> in front of <code>behind</code>.  If
     * <code>behind</code> and <code>component</code> are the same
     * component which was already added to this container,
     * <code>addBefore</code> does not change the ordering of the
     * components and returns <code>component</code>.
     * <p>
     * This method affects the Z-order of the
     * <code>java.awt.Component</code> children within the {@link
     * org.havi.ui.HComponentOrdering HComponentOrdering}, and may also implicitly
     * change the numeric ordering of those children.
     * 
     * @param component is the <code>java.awt.Component</code> to be
     * added to the {@link org.havi.ui.HComponentOrdering HComponentOrdering}
     * @param behind is the <code>java.awt.Component</code>, which
     * <code>component</code> will be placed in front of, i.e.
     * <code>behind</code> will be directly behind the added
     * <code>java.awt.Component</code>
     * @return If the <code>java.awt.Component</code> is successfully
     * added or was already present, then it will be returned from
     * this call. If the
     * <code>java.awt.Component</code> is not successfully added,
     * e.g. <code>behind</code> is not a
     * <code>java.awt.Component</code> currently added to the {@link
     * org.havi.ui.HComponentOrdering HComponentOrdering}, then <code>null</code> will
     * be returned.  <p> This method must be implemented in a thread
     * safe manner.
     */
    public java.awt.Component addBefore(java.awt.Component component, java.awt.Component behind);

    /**
     * Adds a <code>java.awt.Component</code> to this {@link
     * org.havi.ui.HComponentOrdering HComponentOrdering} directly behind a previously
     * added <code>java.awt.Component</code>.     
     * <p>
     * If <code>component</code> has already been added to this
     * container, then addAfter moves <code>component</code> behind
     * <code>front</code>.  If <code>front</code> and
     * <code>component</code> are the same component which was already
     * added to this container, <code>addAfter</code> does not change
     * the ordering of the components and returns
     * <code>component</code>.
     * <p> 
     * This method affects the Z-order of the
     * <code>java.awt.Component</code> children within the {@link
     * org.havi.ui.HComponentOrdering HComponentOrdering}, and may also implicitly
     * change the numeric ordering of those children.
     *     
     * @param component is the <code>java.awt.Component</code> to be
     * added to the {@link org.havi.ui.HComponentOrdering HComponentOrdering}
     * @param front is the <code>java.awt.Component</code>, which
     * <code>component</code> will be placed behind, i.e.
     * <code>front</code> will be directly in front of the added
     * <code>java.awt.Component</code>
     * @return If the <code>java.awt.Component</code> is successfully
     * added or was already present, then it will be returned from
     * this call. If the
     * <code>java.awt.Component</code> is not successfully added,
     * e.g. front is not a <code>java.awt.Component</code> currently
     * added to the {@link org.havi.ui.HComponentOrdering HComponentOrdering}, then
     * <code>null</code> will be returned. 
     * <p> 
     * This method must be implemented in a thread safe manner. 
     */
    public java.awt.Component addAfter(java.awt.Component component, java.awt.Component front);
    
    /**
     * Brings the specified <code>java.awt.Component</code> to the
     * &quot;front&quot; of the Z-order in this {@link
     * org.havi.ui.HComponentOrdering HComponentOrdering}.
     * <p>
     * If <code>component</code> is already at the front of the
     * Z-order, the order is unchanged and <code>popToFront</code>
     * returns <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to bring to the
     * &quot;front&quot; of the Z-order of this {@link org.havi.ui.HComponentOrdering
     * HComponentOrdering}.
     * 
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when the
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HComponentOrdering HComponentOrdering}. If this method
     * fails, the Z-order is unchanged.  
     */
    public boolean popToFront(java.awt.Component component);
    
    /**
     * Place the specified <code>java.awt.Component</code> at the
     * &quot;back&quot; of the Z-order in this {@link
     * org.havi.ui.HComponentOrdering HComponentOrdering}.
     * <p>
     * If <code>component</code> is already at the back the Z-order is
     * unchanged and <code>pushToBack</code> returns <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to place
     * at the &quot;back&quot; of the Z-order of this {@link org.havi.ui.HComponentOrdering
     * HComponentOrdering}.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when the
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HComponentOrdering HComponentOrdering}. If the component was
     * not added to the container <code>pushToBack</code> does not change the
     * Z-order.  
     */
    public boolean pushToBack(java.awt.Component component);
    
    /**
     * Moves the specified <code>java.awt.Component</code> one
     * component nearer in the Z-order, i.e. swapping it with the
     * <code>java.awt.Component</code> that was directly in front of
     * it.     
     * <p>
     * If <code>component</code> is already at the front of the
     * Z-order, the order is unchanged and <code>pop</code> returns
     * <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to be moved.
     * @return returns <code>true</code> on success, <code>false</code> on failure, for example
     * if the <code>java.awt.Component</code> has yet to be added to
     * the {@link org.havi.ui.HComponentOrdering HComponentOrdering}.  
     */
    public boolean pop(java.awt.Component component);
    
    /**
     * Moves the specified <code>java.awt.Component</code> one
     * component further away in the Z-order, i.e. swapping it with
     * the <code>java.awt.Component</code> that was directly behind
     * it.
     * <p>
     * If <code>component</code> is already at the back of the
     * Z-order, the order is unchanged and <code>push</code> returns
     * <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to be
     * moved.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example if the
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HComponentOrdering HComponentOrdering}.  
     */
    public boolean push(java.awt.Component component);
    
    /**       
     * Puts the specified <code>java.awt.Component</code> in front of
     * another <code>java.awt.Component</code> in the Z-order of this
     * {@link org.havi.ui.HComponentOrdering HComponentOrdering}.
     * <p>
     * If <code>move</code> and <code>behind</code> are the same
     * component which has been added to the container
     * <code>popInFront</code> does not change the Z-order and returns
     * <code>true</code>.
     * 
     * @param move The <code>java.awt.Component</code> to be moved
     * directly in front of the &quot;behind&quot; Component in the
     * Z-order of this {@link org.havi.ui.HComponentOrdering HComponentOrdering}.
     * @param behind The <code>java.awt.Component</code> which the
     * &quot;move&quot; Component should be placed directly in front
     * of.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when either
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HComponentOrdering HComponentOrdering}. If this method
     * fails, the Z-order is unchanged.  
     */
    public boolean popInFrontOf(java.awt.Component move, java.awt.Component behind);
    
    /**       
     * Puts the specified <code>java.awt.Component</code> behind
     * another <code>java.awt.Component</code> in the Z-order of this
     * {@link org.havi.ui.HComponentOrdering HComponentOrdering}. 
     * <p>
     * If <code>move</code> and <code>front</code> are the same
     * component which has been added to the container
     * <code>pushBehind</code> does not change the Z-order and returns
     * <code>true</code>.
     * 
     * @param move The <code>java.awt.Component</code> to be moved
     * directly behind the &quot;front&quot; Component in the Z-order
     * of this {@link org.havi.ui.HComponentOrdering HComponentOrdering}.
     * @param front The <code>java.awt.Component</code> which the
     * &quot;move&quot; Component should be placed directly behind.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when either
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HComponentOrdering HComponentOrdering}.  
     */
    public boolean pushBehind(java.awt.Component move, java.awt.Component front);
}

