package org.dvb.dom.dvbhtml;

public interface DVBHTMLOptionElement extends DVBHTMLElement {
	public boolean isDefaultSelected();
	public void setDefaultSelected(boolean defaultselected);
	public boolean isSelected();
	public void setSelected(boolean selected);
	public boolean isDisabled();
	public void setDisabled(boolean disabled);
	public DVBHTMLFormElement getForm();
	public long getIndex();
	public String getLabel();
	public void setLabel(String Lable);
	public String getText();
	public void setText(String text);
	public String getValue();
	public void setValue(String value);
}