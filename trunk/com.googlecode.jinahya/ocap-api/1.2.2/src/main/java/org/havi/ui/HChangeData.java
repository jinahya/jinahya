package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
 * A class used as argument for {@link org.havi.ui.HLook#widgetChanged
 *  widgetChanged}. The hint constants are defined on {@link
 *  org.havi.ui.HVisible HVisible}.
 */
public class HChangeData
{
   
/**
 * The hint for this HChangeData. The hint constants are defined on {@link
 *  org.havi.ui.HVisible HVisible}.
 */   
    public int hint;
   
   
/**
 * The data object for this HChangeData. The types of this object for the 
 * different hints are defined on {@link
 *  org.havi.ui.HVisible HVisible}.
 */    
    public Object data;

    /**
     * Creates an HChangeData object.  
     * @param hint the hint constant for this HChangeData. 
     * @param data the data object for this HChangeData. 
     */ 

    public HChangeData(int hint, Object data)
    {
        
    }

}










