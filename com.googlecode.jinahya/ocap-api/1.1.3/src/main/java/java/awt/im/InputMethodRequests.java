/*
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.awt.im;

import java.awt.Rectangle;
import java.awt.font.TextHitInfo;
import java.text.AttributedCharacterIterator;
import java.text.AttributedCharacterIterator.Attribute;

/** 
 * InputMethodRequests defines the requests that a text editing component
 * has to handle in order to work with input methods. The component
 * can implement this interface itself or use a separate object that
 * implements it. The object implementing this interface must be returned
 * from the component's getInputMethodRequests method.
 *
 * <p>
 * The text editing component also has to provide an input method event
 * listener.
 *
 * <p>
 * The interface is designed to support one of two input user interfaces:
 * <ul>
 * <li><em>on-the-spot</em> input, where the composed text is displayed as part
 *     of the text component's text body.
 * <li><em>below-the-spot</em> input, where the composed text is displayed in
 *     a separate composition window just below the insertion point where
 *     the text will be inserted when it is committed. Note that, if text is
 *     selected within the component's text body, this text will be replaced by
 *     the committed text upon commitment; therefore it is not considered part
 *     of the context that the text is input into.
 * </ul>
 *
 * @see java.awt.Component#getInputMethodRequests
 * @see java.awt.event.InputMethodListener
 *
 * @version 	1.17, 01/23/03
 * @author JavaSoft Asia/Pacific
 * @since 1.2
 */
public interface InputMethodRequests
{

    /** 
     * Gets the location of a specified offset in the current composed text,
     * or of the selection in committed text.
     * This information is, for example, used to position the candidate window
     * near the composed text, or a composition window near the location
     * where committed text will be inserted.
     *
     * <p>
     * If the component has composed text (because the most recent
     * InputMethodEvent sent to it contained composed text), then the offset is
     * relative to the composed text - offset 0 indicates the first character
     * in the composed text. The location returned should be for this character.
     *
     * <p>
     * If the component doesn't have composed text, the offset should be ignored,
     * and the location returned should reflect the beginning (in line
     * direction) of the highlight in the last line containing selected text.
     * For example, for horizontal left-to-right text (such as English), the
     * location to the left of the left-most character on the last line
     * containing selected text is returned. For vertical top-to-bottom text,
     * with lines proceding from right to left, the location to the top of the
     * left-most line containing selected text is returned.
     *
     * <p>
     * The location is represented as a 0-thickness caret, that is, it has 0
     * width if the text is drawn horizontally, and 0 height if the text is
     * drawn vertically. Other text orientations need to be mapped to
     * horizontal or vertical orientation. The rectangle uses absolute screen
     * coordinates.
     *
     * @param offset the offset within the composed text, if there is composed
     * text; null otherwise
     * @return a rectangle representing the screen location of the offset
     */
    public Rectangle getTextLocation(TextHitInfo offset);

    /** 
     * Gets the offset within the composed text for the specified absolute x
     * and y coordinates on the screen. This information is used, for example
     * to handle mouse clicks and the mouse cursor. The offset is relative to
     * the composed text, so offset 0 indicates the beginning of the composed
     * text.
     *
     * <p>
     * Return null if the location is outside the area occupied by the composed
     * text.
     *
     * @param x the absolute x coordinate on screen
     * @param y the absolute y coordinate on screen
     * @return a text hit info describing the offset in the composed text.
     */
    public TextHitInfo getLocationOffset(int x, int y);

    /** 
     * Gets the offset of the insert position in the committed text contained
     * in the text editing component. This is the offset at which characters
     * entered through an input method are inserted. This information is used
     * by an input method, for example, to examine the text surrounding the
     * insert position.
     * 
     * @return the offset of the insert position
     */
    public int getInsertPositionOffset();

    /** 
     * Gets an iterator providing access to the entire text and attributes
     * contained in the text editing component except for uncommitted
     * text. Uncommitted (composed) text should be ignored for index
     * calculations and should not be made accessible through the iterator.
     *
     * <p>
     * The input method may provide a list of attributes that it is
     * interested in. In that case, information about other attributes that
     * the implementor may have need not be made accessible through the
     * iterator. If the list is null, all available attribute information
     * should be made accessible.
     *
     * @param beginIndex the index of the first character
     * @param endIndex the index of the character following the last character
     * @param attributes a list of attributes that the input method is
     * interested in
     * @return an iterator providing access to the text and its attributes
     */
    public AttributedCharacterIterator getCommittedText(int beginIndex, int
        endIndex, java.text.AttributedCharacterIterator.Attribute[] attributes);

    /** 
     * Gets the length of the entire text contained in the text
     * editing component except for uncommitted (composed) text.
     *
     * @return the length of the text except for uncommitted text
     */
    public int getCommittedTextLength();

    /** 
     * Gets the latest committed text from the text editing component and
     * removes it from the component's text body.
     * This is used for the "Undo Commit" feature in some input methods, where
     * the committed text reverts to its previous composed state. The composed
     * text will be sent to the component using an InputMethodEvent.
     *
     * <p>
     * Generally, this feature should only be supported immediately after the
     * text was committed, not after the user performed other operations on the
     * text. When the feature is not supported, return null.
     *
     * <p>
     * The input method may provide a list of attributes that it is
     * interested in. In that case, information about other attributes that
     * the implementor may have need not be made accessible through the
     * iterator. If the list is null, all available attribute information
     * should be made accessible.
     *
     * @param attributes a list of attributes that the input method is
     * interested in
     * @return the latest committed text, or null when the "Undo Commit"
     * feature is not supported
     */
    public AttributedCharacterIterator
        cancelLatestCommittedText(java.text.AttributedCharacterIterator.Attribute[] attributes);

    /** 
     * Gets the currently selected text from the text editing component.
     * This may be used for a variety of purposes.
     * One of them is the "Reconvert" feature in some input methods.
     * In this case, the input method will typically send an input method event
     * to replace the selected text with composed text. Depending on the input
     * method's capabilities, this may be the original composed text for the
     * selected text, the latest composed text entered anywhere in the text, or
     * a version of the text that's converted back from the selected text.
     *
     * <p>
     * The input method may provide a list of attributes that it is
     * interested in. In that case, information about other attributes that
     * the implementor may have need not be made accessible through the
     * iterator. If the list is null, all available attribute information
     * should be made accessible.
     *
     * @param attributes a list of attributes that the input method is
     * interested in
     * @return the currently selected text
     */
    public AttributedCharacterIterator
        getSelectedText(java.text.AttributedCharacterIterator.Attribute[]
        attributes);
}
