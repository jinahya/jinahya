package org.havi.ui;

import java.awt.Image;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   {@link org.havi.ui.HListElement HListElement} is a holder for
   content used with the {@link org.havi.ui.HListGroup HListGroup}
   component. It must contain a text string, and may also contain a
   single graphical image.

   <p>
   Applications should <b>not</b> directly manipulate
   <code>HListElement</code> objects. They are intended
   to be used in conjunction with an {@link org.havi.ui.HListGroup}
   which maintains a list of them, and is responsible for
   their rendering via the {@link org.havi.ui.HListGroupLook}
   class. The methods <code>setIcon()</code> and
   <code>setLabel()</code> of <code>HListElement</code> shall not be
   used for elements, which are part of <code>HListGroup</code>. If an
   application requires to alter the content, it shall either replace
   the entire element, or remove it temporarily and re-add it after
   the content was changed.

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr>
     <td>label</td>
     <td>text content of this item</td>
     <td>no default</td>
     <td>-</td>
     <td>{@link org.havi.ui.HListElement#getLabel getLabel}</td>
  </tr>
  <tr>
     <td>icon</td>
     <td>image content of this item</td>
     <td>null</td>
     <td>-</td>
     <td>{@link org.havi.ui.HListElement#getIcon getIcon}</td>
  </tr>
  </table>
  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr><td colspan=4>None.</td></tr>
  </table>

   @see HListGroup
   @see HListGroupLook
*/

public class HListElement
{
    /**
     * Creates an HListElement object. See the class description for
     * details of constructor parameters and default values.
     * @param label The label for this HListElement.
     */
    public HListElement(String label)
    {
    }

    /**
     * Creates an HListElement object. See the class description for
     * details of constructor parameters and default values.
     * @param icon The icon for this HListElement.
     * @param label The label for this HListElement.
     */
    public HListElement(Image icon, String label)
    {
    }

    /**
     * Retrieve the label for this HListElement.
     *
     * @return the text label for this HListElement.
     */
    public String getLabel()
    {
	return (null);
    }

    /**
     * Retrieve the icon for this HListElement.
     *
     * @return the graphical icon for this HListElement, or
     * <code>null</code> if no icon was set.
     */
    public Image getIcon()
    {
	return (null);
    }

    /**
     * Set the label for this HListElement.
     * @param label The label for this HListElement.
     */
    public void setLabel(String label)
    {
    }

    /**
     * Set the icon for this HListElement. If icon is null,
     * the HListElement will be in the same state as if no icon was set.
     * @param icon The icon for this HListElement.
     */
    public void setIcon(Image icon)
    {
    }
}

