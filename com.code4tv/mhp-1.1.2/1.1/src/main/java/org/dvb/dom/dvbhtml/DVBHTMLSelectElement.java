package org.dvb.dom.dvbhtml;

import org.w3c.dom.*;

public interface DVBHTMLSelectElement extends DVBHTMLElement {
	public String getType();
	public long getSelectedIndex();
	public void setSelectedIndex(long index);
	public String getValue();
	public void setvalue(String value);
	public long getLength();
	public DVBHTMLFormElement getForm();
	public DVBHTMLCollection getOptions();
	public boolean isDisabled();
	public void setDisabled(Boolean disabled);
	public boolean isMultiple();
	public String getName();
	public void setName(String name);
	public long getSize();
	public void setSize(long index);
	public long getTabIndex();
	public void setTabIndex(long index);
	public void add(DVBHTMLElement element, DVBHTMLElement before) throws DOMException;
	public void remove(long index);
	public void blur();
	public void focus();
}