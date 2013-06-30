
package org.ocap.hn;

import java.util.EventListener;

import org.ocap.hn.content.ContentContainer;
import org.ocap.hn.content.ContentEntry;

/**
 * Listener interface for classes which are interested in changes to a ContentServerNetModule.
 */
public interface ContentServerListener extends EventListener{

	/**
	 * Called when a {@link ContentEntry} has been added, changed or removed
	 * from the {@link org.ocap.hn.ContentServerNetModule}
	 * @param evt the {@link ContentServerEvent}
	 */
	public void contentUpdated(ContentServerEvent evt);
	
}
