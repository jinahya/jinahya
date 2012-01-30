package org.ocap.application;

import org.dvb.application.AppID;
import org.dvb.application.AppsDatabaseFilter;
import java.util.Enumeration;

/**
 * <em>AppFilter</em> provides a means of filtering AppIDs.  As a
 * subclass of {@link AppsDatabaseFilter}, the method {@link #accept}
 * makes a <code>true</code>/<code>false</code> decision based on an AppID.
 *
 * <p>An AppFilter contains a list of zero or more {@link AppPattern}s.  Each
 * AppPattern has the attributes: <em>pattern</em>, <em>action</em>, and
 * <em>priority</em>.  <em>pattern</em> specifies a group of AppIDs with
 * a pair of ranges for organization ID and application ID.
 * <em>action</em> specifies an action assigned to the AppID group;
 * either {@link AppPattern#ALLOW}, {@link AppPattern#DENY}, or {@link
 * AppPattern#ASK}.  <em>priority</em> specifies this AppPattern's
 * position in the search order: the biggest number comes first.
 * Applications can insert an AppPattern anywhere in the search
 * order by using the priority attribute effectively
 * (<code>AppFilter.add</code>).  When two or more AppPatterns in an
 * AppFilter have the same priority, the search order among them is
 * undefined.  It is not recommendable to use AppPatterns that have the
 * same priority but different actions.
 *
 * <p>When <code>accept</code> is called, the given AppID is compared to
 * the AppID group of each AppPattern in the search order until a match
 * is found.  Then, it returns <code>true</code> or <code>false</code>
 * if the action of matching AppPattern is <code>ALLOW</code> or
 * <code>DENY</code> respectively.  If no match is found,
 * <code>accept</code> returns <code>true</code>.
 *
 * <p>If the action of matching AppPattern is <code>ASK</code>, then
 * AppFilter calls <code>AppFilterHandler.accept</code> for the final
 * decision; the matching AppPattern is handed over to this method.
 * Applications can specify the <code>AppFilterHandler</code> with
 * <code>AppFilter.setAskHandler</code>. If no AppFilterHandler is set,
 * AppFilter returns <code>true</code>.
 *
 *
 * <p>AppPatterns can have an expiration time and MSO-private
 * information (<em>expirationTime</em> and <em>info</em>).
 * <code>accept</code> and <code>getAppPatterns</code> methods ignore
 * AppPatterns that have expired.  The implementation may delete expired
 * AppPatterns from AppFilter.
 *
 * <p><b>Example:</b>
 *
 * <blockquote><pre>
 * import org.ocap.application.*;
 * import org.dvb.application.AppID;
 *
 * AppManagerProxy am = ...;
 * AppPattern[] patterns = {
 *     &#47;* note that search order is dictated by "priority" *&#47;
 *     new AppPattern("10-5f:1-ff", AppPattern.ALLOW, 40),     // #3
 *     new AppPattern("30:2c-34", AppPattern.ALLOW, 100),      // #1
 *     new AppPattern("20-40", AppPattern.DENY, 80),           // #2
 * };
 * AppFilter af = new AppFilter(patterns);
 * 
 * &#47;* false - matches "20-40" *&#47;
 * boolean badOne = af.accept(new AppID(0x30, 0x10));
 *
 * &#47;* true - matches "30:2c-34" *&#47;
 * boolean goodOne = af.accept(new AppID(0x30, 0x30));
 *
 * &#47;* will be the second entry: priority between 100 and 80 *&#47;
 * af.add(new AppPattern("40-4f:1000-1fff", DENY, 90));
 *
 * &#47;* register af with the system *&#47;
 * am.setAppFilter(af);
 * </pre></blockquote>
 *
 * @see AppPattern
 * @see AppFilterHandler
 * @see AppManagerProxy
 * @see org.dvb.application.AppID
 * @see org.dvb.application.AppsDatabaseFilter
 */
public class AppFilter extends AppsDatabaseFilter {

    /**
     * Constructs an empty AppFilter.
     */
    public AppFilter() {
    }

    /**
     * Constructs an AppFilter with initial AppPatterns.
     *
     * @param patterns AppPatterns to constitute an AppFilter.
     */
    public AppFilter(AppPattern[] patterns) {
    }

    /**
     * Returns the AppPatterns in this AppFilter.
     *
     * @return the enumeration of AppPatterns.  When this AppFilter
     * has no AppPattern, this method returns an empty Enumeration,
     * not <code>null</code>.
     */
    public Enumeration getAppPatterns() {
        return null;
    }

    /**
     * Returns whether this AppFilter accepts the given AppID.
     * 
     * @param appID an AppID to test.
     *
     * @return <code>true</code> if <code>appID</code> passes this
     * filter.
     */
    public boolean accept(AppID appID) {
        return true;
    }

    /**
     * Adds an AppPattern to this AppFilter.
     *
     * @param pattern the AppPattern to add
     */
    public void add(AppPattern pattern) {
    }

    /**
     * Removes an AppPattern that equals to <code>pattern</code> in this
     * AppFilter.  If this AppFilter does not contain
     * <code>pattern</code>, it is unchanged.
     *
     * @param pattern the AppPattern to remove.
     *
     * @return <code>true</code> if the AppFilter contained the
     * specified AppPattern.
     *
     * @see AppPattern#equals
     */
    public boolean remove(AppPattern pattern) {
        return false;
    }

    /**
     * Sets the handler to call when <code>accept</code> hits an
     * AppPatterns with action {@link AppPattern#ASK}.
     * 
     * <p>If a handler is already registered with this AppFilter, the
     * new handler replaces it.
     *
     * @param handler the handler to set.
     */
    public void setAskHandler(AppFilterHandler handler) {
    }

}

