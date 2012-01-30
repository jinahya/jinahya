
package org.dvb.ui;

/** 
  * The TextOverflowListener is an interface that an application 
  * may implement and register in the DVBTextLayoutManager.
  * This listener will be notified if the text string does not fit within the 
  * component as a result of a call to the render method.
  * It is the rendering process which triggers this event to be dispatched.
  * The timing of this is 
  * implementation dependent. 
  *
  */

public interface TextOverflowListener extends java.util.EventListener {
    
    /** This method is called by the DVBTextLayoutManager
      * if the text does not fit within the component
      * @param markedUpString the string that was successfully rendered within the component
      * @param v the HVisible object that was being rendered
      * @param overflowedHorizontally true if the text overflowed the bounds of 
      *                               the component in the horizontal 
      *                               direction; otherwise false
      * @param overflowedVertically true if the text overflowed the bounds of 
      *                             the component in the vertical direction;
      *                             otherwise false
      */
    public void notifyTextOverflow(java.lang.String markedUpString,
				   org.havi.ui.HVisible v,
				   boolean overflowedHorizontally,
				   boolean overflowedVertically);
}



