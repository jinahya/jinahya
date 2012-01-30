package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
   The {@link org.havi.ui.HMultilineEntry HMultilineEntry} is a user interface
   component used to receive multiple lines of alphanumeric entry from
   the user.
   <p>
   A call to the inherited method
   <code>setDefaultLook(HSinglelineEntry)</code> shall behave the same
   as a call to {@link HSinglelineEntry#setDefaultLook}.

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
  <td>x</td>
  <td>x-coordinate of top left hand corner of this component in pixels, 
  relative to its parent container (subject to layout management).</td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>y</td>
  <td>y-coordinate of top left hand corner of this component in pixels,
  relative to its parent container (subject to layout management).</td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>width</td>
  <td>width of this component in pixels (subject to layout management). 
  </td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>height</td>
  <td>height of this component in pixels (subject to layout management). 
  </td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>

  

<tr>
  <td>text</td> 
  <td>The text within this {@link org.havi.ui.HMultilineEntry},
  to be used as the displayed and editable content 
  for all states.</td>
  <td>null</td>
  <td>{@link org.havi.ui.HVisible#setTextContent}</td>
  <td>{@link org.havi.ui.HVisible#getTextContent}</td>
</tr>

<tr>
  <td>maxChars</td>
  <td>The maximum number of characters allowed in this {@link
  org.havi.ui.HMultilineEntry}.</td>
  <td>16 characters</td>
  <td>{@link org.havi.ui.HSinglelineEntry#setMaxChars}</td>
  <td>{@link org.havi.ui.HSinglelineEntry#getMaxChars}</td>
 </tr>
 
<tr>
  <td>font</td>
  <td>The font to be used for this component.</td>
  <td>---</td>
  <td><code>java.awt.Component#setFont</code>.</td>
  <td><code>java.awt.Component#getFont</code>.</td>
 </tr>
 
<tr>
  <td>color</td>
  <td>The color to be used for this component.</td>
  <td>---</td>
  <td><code>java.awt.Component#setForeground</code>.</td>
  <td><code>java.awt.Component#getForeground</code>.</td>
 </tr>

  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr> 
  <td>Associated matte ({@link org.havi.ui.HMatte HMatte}).</td> 
  <td>none (i.e. getMatte() returns <code>null</code>)</td> 
  <td>{@link org.havi.ui.HComponent#setMatte setMatte}</td> 
  <td>{@link org.havi.ui.HComponent#getMatte getMatte}</td> 
  </tr>
   <tr>
       <td> The text layout manager responsible for text
       formatting.</td>
       <td> An {@link org.havi.ui.HDefaultTextLayoutManager}
       object.</td>
       <td> {@link org.havi.ui.HVisible#setTextLayoutManager}
       </td>
       <td> {@link org.havi.ui.HVisible#getTextLayoutManager}
       </td>
   </tr>

   <tr>
       <td>The background painting mode</td>
       <td>{@link org.havi.ui.HVisible#NO_BACKGROUND_FILL}</td>

       <td>{@link org.havi.ui.HVisible#setBackgroundMode}</td>
       <td>{@link org.havi.ui.HVisible#getBackgroundMode}</td>
   </tr>

   <tr>
       <td>The default preferred size</td>
       <td>not set (i.e. NO_DEFAULT_SIZE) unless specified by <code>width</code>
       and <code>height</code> parameters</td>
       <td>{@link org.havi.ui.HVisible#setDefaultSize}</td>
       <td>{@link org.havi.ui.HVisible#getDefaultSize}</td>
   </tr>

   <tr>
       <td>The horizontal content alignment</td>
       <td>{@link org.havi.ui.HVisible#HALIGN_CENTER}</td>
       <td>{@link org.havi.ui.HVisible#setHorizontalAlignment}</td>
       <td>{@link org.havi.ui.HVisible#getHorizontalAlignment}</td>
   </tr>

   <tr>
       <td>The vertical content alignment</td>
       <td>{@link org.havi.ui.HVisible#VALIGN_CENTER}</td>
       <td>{@link org.havi.ui.HVisible#setVerticalAlignment}</td>
       <td>{@link org.havi.ui.HVisible#getVerticalAlignment}</td>
   </tr>

   <tr>
       <td>The content scaling mode</td>
       <td>{@link org.havi.ui.HVisible#RESIZE_NONE}</td>
       <td>{@link org.havi.ui.HVisible#setResizeMode}</td>
       <td>{@link org.havi.ui.HVisible#getResizeMode}</td>
   </tr>

<tr>
    <td>The border mode</td>
    <td><code>true</code></td>
    <td>{@link org.havi.ui.HVisible#setBordersEnabled}</td>
    <td>{@link org.havi.ui.HVisible#getBordersEnabled}</td>
</tr>




<tr>
  <td>Caret position</td>
  <td>At the end of the current text string</td>
  <td>{@link HSinglelineEntry#setCaretCharPosition}</td>
  <td>{@link HSinglelineEntry#getCaretCharPosition}</td>
</tr>
 
<tr>
  <td>Input type</td>
  <td>{@link HSinglelineEntry#INPUT_ANY}</td>
  <td>{@link HSinglelineEntry#setType}</td>
  <td>{@link HSinglelineEntry#getType}</td>
</tr>
 
<tr>
  <td>Customized input range</td>
  <td>null</td>
  <td>{@link HSinglelineEntry#setValidInput}</td>
  <td>{@link HSinglelineEntry#getValidInput}</td>
</tr>

<tr>
  <td>Password protection (the echo character)</td>
  <td>Zero (ASCII NUL), i.e. not password protected.</td>
  <td>{@link HSinglelineEntry#setEchoChar}</td>
  <td>{@link HSinglelineEntry#getEchoChar} and 
  {@link HSinglelineEntry#echoCharIsSet}</td>
</tr>
<tr>
  <td>The default &quot;look&quot; for this class.</td>
  <td>A platform specific {@link org.havi.ui.HMultilineEntryLook HMultilineEntryLook}</td>
  <td>{@link org.havi.ui.HMultilineEntry#setDefaultLook HMultilineEntry.setDefaultLook}</td>
  <td>{@link org.havi.ui.HMultilineEntry#getDefaultLook HMultilineEntry.getDefaultLook}</td>
</tr>

<tr>
  <td>The &quot;look&quot; for this object.</td>
  <td>The {@link org.havi.ui.HMultilineEntryLook HMultilineEntryLook} returned from
  HMultilineEntry.getDefaultLook when this object was created.</td>
  <td>{@link org.havi.ui.HMultilineEntry#setLook HMultilineEntry.setLook}</td>
  <td>{@link org.havi.ui.HMultilineEntry#getLook HMultilineEntry.getLook}</td>
</tr>
<tr>
  <td>The gain focus sound.</td>
  <td>null </td>
  <td>{@link org.havi.ui.HMultilineEntry#setGainFocusSound setGainFocusSound}</td>
  <td>{@link org.havi.ui.HMultilineEntry#getGainFocusSound getGainFocusSound}</td>
</tr>
<tr>
  <td>The lose focus sound. </td>
  <td>null </td>
  <td>{@link org.havi.ui.HMultilineEntry#setLoseFocusSound setLoseFocusSound}</td>
  <td>{@link org.havi.ui.HMultilineEntry#getLoseFocusSound getLoseFocusSound}</td>
</tr>
  </table>

*/

public class HMultilineEntry
    extends HSinglelineEntry
{   
    /**
     * Creates an {@link org.havi.ui.HMultilineEntry HMultilineEntry}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HMultilineEntry()
    {
    }

    /**
     * Creates an {@link org.havi.ui.HMultilineEntry HMultilineEntry}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HMultilineEntry(String text, int x, int y, int width, int height,
                           int maxChars, java.awt.Font font,
                           java.awt.Color color)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HMultilineEntry HMultilineEntry}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HMultilineEntry(int x, int y, int width, int height, int maxChars)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HMultilineEntry HMultilineEntry}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HMultilineEntry(String text, int maxChars, 
                           java.awt.Font font, java.awt.Color color)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HMultilineEntry HMultilineEntry}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HMultilineEntry(int maxChars)
    {
    }

    /**
     * Sets the default look for further {@link
     * org.havi.ui.HMultilineEntry HMultilineEntry} Components.
     *
     * @param look The look that will be used by default when creating
     * a new {@link org.havi.ui.HMultilineEntry HMultilineEntry}
     * component.
     * Note that this parameter may be null, in which case newly
     * created components shall not draw themselves until a non-null
     * look is set using the {@link
     * org.havi.ui.HSinglelineEntry#setLook setLook} method.
     */
    public static void setDefaultLook(HMultilineEntryLook look)
    {
    }
    
    /**
     * Returns the currently set default look for {@link
     * org.havi.ui.HMultilineEntry HMultilineEntry} components.
     *
     * @return The {@link org.havi.ui.HMultilineEntryLook
     * HMultilineEntryLook} that is used by default when creating a
     * new {@link org.havi.ui.HMultilineEntry HMultilineEntry}
     * component, cast to an {@link org.havi.ui.HSinglelineEntryLook 
     * HSinglelineEntryLook}.
     */
    public static HSinglelineEntryLook getDefaultLook()
    {
        return (null);
    }

    /**
     * Sets the {@link org.havi.ui.HLook HLook} for this component.
     * 
     * @param hlook The {@link org.havi.ui.HLook HLook} that is to be
     * used for this component.
     * @exception HInvalidLookException If the Look is not an {@link
     * org.havi.ui.HMultilineEntryLook HMultilineEntryLook}.  
     */
    public void setLook(HLook hlook)
	throws HInvalidLookException
    {
    }



    /**
     * Move the caret to the same column position on the next line.
     * If the caret would be past the end of the text on the line the
     * new caret position will be at the end of the line.  
     */
    public void caretNextLine()
    {
    }

    /**
     * Move the caret to the same column position on the previous
     * line.  If the caret would be past the end of the text on the
     * line the new caret position will be at the end of the line.  
     */
    public void caretPreviousLine()
    {
    }
}

