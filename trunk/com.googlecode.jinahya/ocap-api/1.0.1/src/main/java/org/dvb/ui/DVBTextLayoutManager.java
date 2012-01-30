package org.dvb.ui;

import org.havi.ui.HVisible;

/** 
  * The DVBTextLayoutManager provides a text rendering layout mechanism 
  * for the org.havi.ui.HStaticText org.havi.ui.HText and 
  * org.havi.ui.HTextButton classes.
  * <p>
  * The semantics of the rendering behaviour and the settings are
  * specified in the "Text presentation" annex of the present document. The 
  * DVBTextLayoutManager renders the text according to the semantics described
  * in that annex.
  *
  */

public class DVBTextLayoutManager implements org.havi.ui.HTextLayoutManager {
    
    /* Inherited methods from HTextLayoutManager */

    /**
     * Render the string. The {@link org.havi.ui.HTextLayoutManager
     * HTextLayoutManager} should use the passed {@link
     * org.havi.ui.HVisible HVisible} object to determine any
     * additional information required to render the string,
     * e.g. <code>Font</code>, <code>Color</code> etc. 
     * <p>
     * The text should be laid out in the layout area, which is
     * defined by the bounds of the specified {@link
     * org.havi.ui.HVisible HVisible}, after subtracting the 
     * insets. If the insets are <code>null</code> the full bounding
     * rectangle is used as the area to render text into.
     * <p> 
     * The {@link org.havi.ui.HTextLayoutManager
     * HTextLayoutManager} should not modify the clipping rectangle of
     * the <code>Graphics</code> object.
     * 
     * @param markedUpString the string to render.
     * @param g the graphics context, including a clipping rectangle
     * which encapsulates the area within which rendering is
     * permitted. If a valid insets value is passed to this method then
     * text must only be rendered into the bounds of the widget after
     * the insets are subtracted. If the insets value is <code>null</code>
     * then text is rendered into the entire bounding area of the {@link
     * org.havi.ui.HVisible HVisible}. It is implementation specific whether
     * or not the renderer takes into account the intersection of the
     * clipping rectangle in each case for optimization purposes.
     * @param v the {@link org.havi.ui.HVisible HVisible} into which
     * to render. 
     * @param insets the insets  to
     * determine the area in which to layout the text, or <code>null</code>.   
     */
    public void render(String markedUpString, java.awt.Graphics g, HVisible v, java.awt.Insets insets)
	{}

    /* DVBTextLayoutManager */

    /** The text should be aligned horizontally
      * to the horizontal start side (e.g. when start corner is 
      * upper left and line orientation horizontal, meaning text 
      * that is read left to right from top to bottom, this 
      * implies alignment to left).
      */
    public static final int HORIZONTAL_START_ALIGN = 1;

    /** The text should be horizontally 
      * to the horizontal end side (e.g. when start corner is 
      * upper left and line orientation horizontal, meaning 
      * text that is read left to right from top to bottom, 
      * this implies alignment to right).
      */
    public static final int HORIZONTAL_END_ALIGN = 2;

    /** The text should be centered horizontally.
      */
    public static final int HORIZONTAL_CENTER = 3;

    /** The text should be aligned vertically
      * to the vertical start side (e.g. when start corner is 
      * upper left and line orientation horizontal, meaning text 
      * that is read left to right from top to bottom, this 
      * implies alignment to top). <p>This is defined by the clause 
      * "Vertical limits" in the "Text presentation" annex of the present document.
      */
    public static final int VERTICAL_START_ALIGN = 4;

    /** The text should be aligned vertically 
      * to the vertical end side (e.g. when start corner is 
      * upper left and line orientation horizontal, meaning 
      * text that is read left to right from top to bottom, 
      * this implies alignment to bottom).<p>
      * This is defined by the clause "Vertical limits" in the
      * "Text presentation" annex of the present document.
      */
    public static final int VERTICAL_END_ALIGN = 5;

    /** The text should be centered vertically.
     */
    public static final int VERTICAL_CENTER = 6;
    
    /** Horizontal line orientation.
      */
    public static final int LINE_ORIENTATION_HORIZONTAL = 10;

    /** Vertical line orientation.
      */
    public static final int LINE_ORIENTATION_VERTICAL = 11;

    /** Upper left text start corner.
      */
    public static final int START_CORNER_UPPER_LEFT = 20;
    
    /** Upper right text start corner.
      */
    public static final int START_CORNER_UPPER_RIGHT = 21;
    
    /** Lower left text start corner.
      */
    public static final int START_CORNER_LOWER_LEFT = 22;
    
     /** Lower right text start corner.
       */
    public static final int START_CORNER_LOWER_RIGHT = 23;
    

    /** Constructs a DVBTextLayoutManager object with default 
      * parameters (HORIZONTAL_START_ALIGN, VERTICAL_START_ALIGN,
      * LINE_ORIENTATION_HORIZONTAL,
      * START_CORNER_UPPER_LEFT, wrap = true, 
      * linespace = (point size of the default font for HVisible) + 7,
      * letterspace = 0, horizontalTabSpace = 56) 
      */
    public  DVBTextLayoutManager() {
    }

    /**  Constructs a DVBTextLayoutManager object.
      * @param horizontalAlign Horizontal alignment setting
      * @param verticalAlign Vertical alignment setting
      * @param lineOrientation Line orientation setting
      * @param startCorner Starting corner setting
      * @param wrap Text wrapping setting 
      * @param linespace Line spacing setting expressed in points
      * @param letterspace Letterspacing adjustment relative to the 
      *                    default letterspacing. Expressed in units of 
      *                    1/256th point as the required increase in 
      *                    the spacing between consecutive characters.
      *                    May be either positive or negative.
      * @param horizontalTabSpace Horizontal tabulation setting in points
      */
    public  DVBTextLayoutManager(int horizontalAlign, 
				 int verticalAlign,
				 int lineOrientation,
				 int startCorner,
				 boolean wrap,
				 int linespace,
				 int letterspace,
				 int horizontalTabSpace) {
    }
    
    /**
      * Set the horizontal alignment. The setting shall be one of <code>HORIZONTAL_CENTER</code>,
      * <code>HORIZONTAL_END_ALIGN</code> or <code>HORIZONTAL_START_ALIGN</code>. The failure mode 
      * if other values are used is implementation dependent.
      *
      * @param horizontalAlign Horizontal alignment setting
      */
    public void setHorizontalAlign(int horizontalAlign) {
    }
    
    /**
      * Set the vertical alignment. The setting shall be one of <code>VERTICAL_CENTER</code>,
      * <code>VERTICAL_END_ALIGN</code> or <code>VERTICAL_START_ALIGN</code>. The failure mode 
      * if other values are used is implementation dependent.
      *
      * @param verticalAlign Vertical alignment setting
      */
    public void setVerticalAlign(int verticalAlign) {
    }

    /**
      * Set the line orientation. The setting shall be one of <code>LINE_ORIENTATION_VERTICAL</code>,
      * <code>LINE_ORIENTATION_HORIZONTAL</code>. The failure mode 
      * if other values are used is implementation dependent.
      *
      * @param lineOrientation Line orientation setting
      */
    public void setLineOrientation(int lineOrientation) {
    }

    /**
      * Set the starting corner. The setting shall be one of <code>START_CORNER_UPPER_LEFT</code>,
      * <code>START_CORNER_UPPER_RIGHT<code>, <code>START_CORNER_LOWER_LEFT</code> or
      * <code>START_CORNER_LOWER_RIGHT</code>. The failure mode 
      * if other values are used is implementation dependent.
      *
      * @param startCorner Starting corner setting
      */
    public void setStartCorner(int startCorner) {
    }

    /**
      * Set the text wrapping setting.
      *
      * @param wrap Text wrapping setting
      */
    public void setTextWrapping(boolean wrap) {
    }


    /**
      * Set the line space setting. Using -1 as the line space setting shall cause the
      * line spacing to be determined from the size of the default font.
      * 
      * @param lineSpace line space setting
      */
    public void setLineSpace(int lineSpace) {
    }

    /**
      * Set the letter space setting.
      * 
      * This is a 16 bit signed integer specifying in units of 1/256th point the required 
      * increase in the spacing between consecutive characters. It corresponds to the 
      * "track" parameter in the MHP text rendering rules.
      * @param letterSpace letter space setting
      */
    public void setLetterSpace(int letterSpace) {
    }

    /**
      * Set the horizontal tabulation spacing.
      * 
      * @param horizontalTabSpace tab spacing in points
      */
    public void setHorizontalTabSpacing(int horizontalTabSpace) {
    }

    /**
      * Get the horizontal alignment.
      *
      * @return Horizontal alignment setting
      */
    public int getHorizontalAlign() {
	return (HORIZONTAL_START_ALIGN);
    }

    /**
      * Get the vertical alignment.
      *
      * @return Vertical alignment setting
      */
    public int getVerticalAlign() {
	return (VERTICAL_START_ALIGN);
    }

    /**
      * Get the line orientation.
      *
      * @return Line orientation setting
      */
    public int getLineOrientation() {
	return (LINE_ORIENTATION_HORIZONTAL);
    }

    /**
      * Get the starting corner.
      *
      * @return Starting corner setting
      */
    public int getStartCorner() {
	return (START_CORNER_UPPER_LEFT);
    }

    /**
      * Get the text wrapping setting.
      *
      * @return text wrapping setting
      */
    public boolean getTextWrapping() {
	return true;
    }

    /**
      * Get the line space setting.
      * 
      * @return line space setting or -1, if the default line spacing is determined 
      * from the size of the default font used.
      */
    public int getLineSpace() {
	return 27;
    }

    /**
      * Get the letter space setting.
      * This is a 16 bit signed integer specifying in units of 1/256th point the required 
      * increase in the spacing between consecutive characters. It corresponds to the 
      * "track" parameter in the MHP text rendering rules.
      * 
      * @return letter space setting
      */
    public int getLetterSpace() {
	return 0;
    }

    /**
      * Get the horizontal tabulation spacing.
      * 
      * @return the horizontal tabulation spacing
      */
    public int getHorizontalTabSpacing() {
	return 0;
    }

    /** 
      * Sets the insets which shall be used by this DVBTextLayoutManager to provide a 
      * "virtual margin". These shall be added to the insets passed to the <code>Render</code>
      * method (which are to be considered as "bounds"). If this method is not called,
      * the default insets are 0 at each edge.
      *
      * @param insets Insets that should be used
      */
    public void setInsets(java.awt.Insets insets) {
    }

	/**
	 * Returns the insets set by the setInsets method. These Insets are added
	 * to the ones passed to the <code>render</code> method for rendering the
	 * text. When not previously set, zero Insets are returned.
	 *
	 * @return Insets set by the setInsets method
	 */
    public java.awt.Insets getInsets() {
        return null;
    }  

    /**
      * Register a TextOverflowListener that will be notified
      * if the text string does not fit in the component when
      * rendering.
      * @param l a listener object
      */
    public void addTextOverflowListener(TextOverflowListener l) { 
    }


    /**
      * Removes a TextOverflowListener that has been registered
      * previously. 
      * @param l a listener object
      */
    public void removeTextOverflowListener(TextOverflowListener l) { 
    }

}










