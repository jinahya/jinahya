package org.ocap.application;

import org.dvb.application.AppID;
import org.dvb.application.AppsDatabaseFilter;

/**
 * Application programs can provide an implementation of this
 * interface to an {@link AppFilter} to make part of decision
 * for <code>AppFilter.accept</code>.
 *
 * @see AppFilter#setAskHandler
 */
public interface AppFilterHandler {
    /**
     * This method is called by {@link AppFilter#accept} when it finds a
     * matching <code>AppPattern</code> whose action is
     * <code>ASK</code>.
     *
     * <p>The return value of this method will be the return value of
     * <code>AppFilter.accept</code>.  The semantics of this method is
     * identical to {@link AppsDatabaseFilter#accept} except that the
     * additional parameter <code>matchingItem</code> could be used as a
     * hint.
     *
     * @param appID an AppID to test.
     *
     * @param matchingItem the <code>AppPattern</code> in
     * <code>AppFilter</code> that matched <code>appID</code>
     *
     * @return <code>true</code> if <code>appID</code> passes this
     * filter.
     */
    public boolean accept(AppID appID, AppPattern matchingItem);
}
