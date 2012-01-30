package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
 * The  {@link org.havi.ui.HOrientable  HOrientable}  interface provides support for components which have an orientation. <p> Widgets of HAVi compliant applications implementing the <code>HOrientable</code> interface must have <code>HComponent</code> in their inheritance tree.
 */

public interface HOrientable
{
    /** 
     * A constant which specifies that the {@link org.havi.ui.HOrientable
     * HOrientable} shall be rendered with a horizontal orientation,
     * with the minimum value on the left side, and the maximum
     * value on the right side.  
     */
    public static final int ORIENT_LEFT_TO_RIGHT = 0;

    /** 
     * A constant which specifies that the {@link org.havi.ui.HOrientable
     * HOrientable} shall be rendered with a horizontal orientation,
     * with the minimum value on the right side, and the maximum value
     * on the left side.
     */
    public static final int ORIENT_RIGHT_TO_LEFT = 1;
    
    /** 
     * A constant which specifies that the {@link org.havi.ui.HOrientable
     * HOrientable} shall be rendered with a vertical orientation, with
     * the minimum value on the top, and the maximum of the range
     * on the bottom.  
     */
    public static final int ORIENT_TOP_TO_BOTTOM = 2;
    
    /** 
     * A constant which specifies that the {@link org.havi.ui.HOrientable
     * HOrientable} shall be rendered with a vertical orientation, with
     * the minimum value on the bottom, and the maximum value on
     * the top.  
     */
    public static final int ORIENT_BOTTOM_TO_TOP = 3;

    /**
	 * Retrieve the orientation of the  {@link org.havi.ui.HOrientable  HOrientable} . The orientation controls the layout of the component.
	 * @return  one of  {@link org.havi.ui.HOrientable#ORIENT_LEFT_TO_RIGHT  ORIENT_LEFT_TO_RIGHT}  ,  {@link org.havi.ui.HOrientable#ORIENT_RIGHT_TO_LEFT  ORIENT_RIGHT_TO_LEFT}  ,  {@link org.havi.ui.HOrientable#ORIENT_TOP_TO_BOTTOM  ORIENT_TOP_TO_BOTTOM}  , or  {@link org.havi.ui.HOrientable#ORIENT_BOTTOM_TO_TOP  ORIENT_BOTTOM_TO_TOP}  .
	 * @uml.property  name="orientation"
	 */
    public int getOrientation();

    /**
	 * Set the orientation of the  {@link org.havi.ui.HOrientable  HOrientable} . The orientation controls the layout of the component.
	 * @param orient  one of  {@link org.havi.ui.HOrientable#ORIENT_LEFT_TO_RIGHT  ORIENT_LEFT_TO_RIGHT}  ,  {@link org.havi.ui.HOrientable#ORIENT_RIGHT_TO_LEFT  ORIENT_RIGHT_TO_LEFT}  ,  {@link org.havi.ui.HOrientable#ORIENT_TOP_TO_BOTTOM  ORIENT_TOP_TO_BOTTOM}  , or  {@link org.havi.ui.HOrientable#ORIENT_BOTTOM_TO_TOP  ORIENT_BOTTOM_TO_TOP}  .
	 * @uml.property  name="orientation"
	 */
    public void setOrientation(int orient);
}

