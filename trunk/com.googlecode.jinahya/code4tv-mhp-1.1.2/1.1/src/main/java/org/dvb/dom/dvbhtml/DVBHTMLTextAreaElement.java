package org.dvb.dom.dvbhtml;

public interface DVBHTMLTextAreaElement extends DVBHTMLElement {
	public boolean isDisabled();
	public void setDisabled(boolean disabled);
	public String getAccessKey();
	public void setAccessKey(String accesskey);
	public String getDefaultValue();
	public void setDefaultValue(String value);
	public DVBHTMLFormElement getForm();
	public String getName();
	public void setName(String name);
	public String getType();
	public void setType(String type);
	public String getValue();
	public void setValue(String value);
}