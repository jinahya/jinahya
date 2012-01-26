package org.ocap.application;

import java.util.Date;

/**
 * AppPattern is an element that constitutes an {@link AppFilter}.  An
 * AppPattern has the following attributes:
 *
 * <ul>
 *
 * <li><em>idPattern</em> - a group of AppIDs.
 *
 * <li><em>action</em> - an action (ALLOW, DENY, or ASK) for matching
 * applications.
 *
 * <li><em>priority</em> - a priority that determines the search order
 * position in an AppFilter.  The highest priority is 255, the lowest is
 * 0.
 *
 * <li><em>expirationTime</em> - An expiration time.  Optional.
 *
 * <li><em>info</em> - an MSO-private data.  Optional.  Could be a
 * String.  {@link AppFilterHandler} may use it for making a decision.
 *
 * </ul>
 *
 * <p><code>idPattern</code> specifies an AppID group with a String: a
 * pair of ranges for Organization IDs and Application IDs.  The syntax
 * is:
 *
 * <blockquote><code>"oid1[-oid2][:aid1[-aid2]]"</code></blockquote>
 *
 * <ul>
 *
 * <li><code>oid1</code> and <code>oid2</code> specify a range of
 * Organization IDs inclusive.  Each of them must be a 32-bit value.
 *
 * <li><code>aid1</code> and <code>aid2</code> specify a range of
 * Application IDs inclusive.  Each of them must be a 16-bit value.
 *
 * <li><code>oid2</code> and <code>aid2</code> must be greater than
 * <code>oid1</code> and <code>aid1</code>, respectively.
 *
 * <li>The encoding of these IDs follows <em>14.5 Text encoding of
 * application identifiers</em> of <em>DVB-MHP 1.0.2 [11]</em>;
 * hexadecimal, lower case, no leading zeros.
 *
 * <li>Symbols in brackets are optional.
 *
 * <li>When <code>oid2</code> is omitted, only <code>oid1</code> is in
 * the range.
 *
 * <li>When <code>aid2</code> is omitted, only <code>aid1</code> is in
 * the range.
 *
 * <li>When both <code>aid1</code> and <code>aid2</code> are omitted,
 * all Application IDs are in the range.
 *
 * </ul>
 *
 * <p>See {@link AppFilter} for the examples.
 * 
 * @see AppFilter
 * @see AppFilterHandler
 */
public class AppPattern {

    /**
     * When <code>AppFilter.accept</code> finds a matching
     * <code>AppPattern</code> with this action, it returns
     * <code>true</code>.
     *
     * @see AppFilter#accept
     */
    public static final int ALLOW = 1;

    /**
     * When <code>AppFilter.accept</code> finds a matching
     * <code>AppPattern</code> with this action, it returns
     * <code>false</code>.
     *
     * @see AppFilter#accept
     */
    public static final int DENY = 2;

    /**
     * When <code>AppFilter.accept</code> finds a matching
     * <code>AppPattern</code> with this action, it asks
     * <code>AppFilterHandler.accept</code> for the decision.
     *
     * @see AppFilter#accept
     * @see AppFilterHandler#accept
     */
    public static final int ASK = 3;


    /**
     * Constructs a new AppPattern with no expiration.
     *
     * @param idPattern a String to specify an AppID group.
     *
     * @param action an action.
     *
     * @param priority a search order priority.
     *
     * @throws IllegalArgumentException <code>idPattern</code> has a bad
     * format, <code>action</code> or <code>priority</code> is out of
     * range.
     */
    public AppPattern(String idPattern, int action, int priority) {
    }

    /**
     * Constructs a new AppPattern with an expiration time and MSO
     * private information.
     *
     * @param idPattern a String to specify an AppID group.
     *
     * @param action an action.
     *
     * @param priority a search order priority.
     *
     * @param expirationTime time for this AppPattern to expire.  <code>null</code>
     * it never expires.
     *
     * @param info MSO specific information.  Can be <code>null</code>.
     *
     * @throws IllegalArgumentException <code>idPattern</code> has a bad
     * format, <code>action</code> or <code>priority</code> is out of
     * range.
     */
    public AppPattern(String idPattern, int action, int priority,
                      Date expirationTime, Object info) {
    }

    /**
     * Returns the pattern string that specifies a group of AppIDs.
     *
     * @return the pattern string.
     */
    public String getAppIDPattern() {
        return null;
    }

    /**
     * Returns the action associated with this AppPattern.
     *
     * @return the action.
     */
    public int getAction() {
        return 0;
    }

    /**
     * Returns the search order priority of this AppPattern.
     *
     * @return the search order priority.
     */
    public int getPriority() {
        return 0;
    }

    /**
     * Returns the time for this AppPattern to expire or
     * <code>null</code> if it never expires.
     *
     * @return the expiration time or <code>null</code>.
     */
    public Date getExpirationTime() {
        return null;
    }

    /**
     * Returns MSO-private information of this AppPattern.
     *
     * @return the MSO private information.
     */
    public Object getPrivateInfo() {
        return null;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * <p>This method does not factor in <code>expirationTime</code> or
     * <code>info</code> attributes, but does compare
     * <code>idPattern</code>, <code>action</code>, and
     * <code>priority</code> attributes.
     */
    public boolean equals(Object that) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

}
