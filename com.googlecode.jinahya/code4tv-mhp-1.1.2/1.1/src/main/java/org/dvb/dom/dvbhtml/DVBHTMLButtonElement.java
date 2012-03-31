package org.dvb.dom.dvbhtml;

public interface DVBHTMLButtonElement extends DVBHTMLElement {
	public String getAccessKey();
	public void setAccessKey(String key);
	public boolean getDisabled();
	public void setDisabled(boolean disabled);
	public DVBHTMLFormElement getForm();
	public String getName();
	public void setName(String name);
	public String getType();
	public void setValue(String value);
	public String getValue();
	public void blur();
	public void focus();
}
