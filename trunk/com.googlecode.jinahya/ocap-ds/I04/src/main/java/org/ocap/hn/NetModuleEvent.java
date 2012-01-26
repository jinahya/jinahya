/*
 * Copyright Matsushita Electric Industrial Co., Ltd.2004
 *  All Rights Reserved
 */

package org.ocap.hn;

/**
 * NetModuleEvent
 *
 * @author			Luyang Li (lly@research.panasonic.com)
 * @version			1.0
 *
 */

/**
 * Entity for NetModule Event. There are two types of NetModule events:
 * one that is generated by the NetManager when a NetModule is added or removed
 * from the home network. Application may register as a listener to NetManager
 * to receive such events. The other NetModuleEvent is generated by the 
 * NetModule itself when its internal state changes. Application should register
 * as a listener with a particular NetModule for such events. In both scenarios, 
 * the NetModule that was the source of the event is returned.
 */
public class NetModuleEvent extends java.util.EventObject {
    /**
     * A constant indicating new module is registered to home network.
     */
    public static final int MODULE_ADDED = 100;

    /**
     * A constant indicating a module is removed from home network.
     */
    public static final int MODULE_REMOVED = 101;

    /**
     * A constant indicating a module is updated from home network.
     */
    public static final int MODULE_UPDATED = 102;
    
    /**
     * A constant indicating a module is busy and cannot respond to request now.
     */
    public static final int MODULE_BUSY = 103;

    /**
     * A constant indicating a module's internal status changed.
     */
    public static final int STATE_CHANGE = 201;

    private int m_type;

    private Object m_source;

    /**
     * Constructs a NetModuleEvent by specifying type and source.
     * 
     * @param type
     *            NetModule change type, allowed type are defined in
     *            <code>NetModuleEvent</code>
     * @param source
     *            NetModule where the change happens.
     */
    public NetModuleEvent(int type, Object source) {
        super(source);
        m_type = type;
        m_source = source;
    }

    /**
     * Returns module event type, as defined in <code>NetModuleEvent</code>.
     * 
     * @return module event type
     */
    public int getType() {
        return m_type;
    }

    /**
     * Returns module event source, which is always a NetModule.
     * 
     * @return module event source
     */
    public Object getSource() {
        return m_source;
    }
}