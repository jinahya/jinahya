package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
 * {@link org.havi.ui.HMatte HMatte} is the base interface for all
 * matte classes.
 * <p>
 * Where pixels in a component already have an alpha value (e.g. from
 * an image), the alpha value from the component and the alpha value
 * from the {@link org.havi.ui.HMatte HMatte} are multiplied together
 * to obtain the actual alpha value to be used for that pixel.
 * <p>
 * The final displayed value of the component and its {@link
 * org.havi.ui.HMatte HMatte} is obviously subject to the capabilities
 * of the underlying hardware platform.
 */
public interface HMatte 
{
}





