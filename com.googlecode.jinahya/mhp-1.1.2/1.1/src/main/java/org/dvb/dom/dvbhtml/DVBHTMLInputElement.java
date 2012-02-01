package org.dvb.dom.dvbhtml;

public interface DVBHTMLInputElement extends DVBHTMLElement {
	public boolean isChecked();
	public void setChecked(boolean checked);
	public boolean isDefaultChecked();
	public void setDefaultChecked(boolean defaultchecked);
	public boolean isDisabled();
	public void setDisabled(boolean disabled);
	public boolean isReadOnly();
	public void setReadOnly(boolean readonly);
	public String getAccept();
	public void setAccept(String accept);
	public String getAccessKey();
	public void setAccessKey(String accesskey);
	public String getAlt();
	public void setAlt(String alt);
	public String getDefaultValue();
	public void setDefaultValue(String value);
	public DVBHTMLFormElement getForm();
	public long getMaxLength();
	public void setMaxLength(long length);
	public String getName();
	public void setName(String name);
	public String getSize();
	public void setSize(String size);
	public String getSrc();
	public void setSrc(String src);
	public String getType();
	public void setType(String type);
	public String getUseMap();
	public String getValue();
	public void setValue(String value);
	public void blur();
	public void click();
	public void focus();
	public void select();
}