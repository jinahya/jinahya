package org.dvb.dom.dvbhtml;

import org.w3c.dom.*;

public interface DVBHTMLCollection {
	//Methods
	public Node getIdentifiedItem(String name);
	public Node getItem(Number index);
	public Number getLength();
	}