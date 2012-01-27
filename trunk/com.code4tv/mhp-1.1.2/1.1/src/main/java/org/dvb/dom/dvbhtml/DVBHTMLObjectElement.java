package org.dvb.dom.dvbhtml;


import org.w3c.dom.*;

public interface DVBHTMLObjectElement extends DVBHTMLElement {
	public boolean isDeclare();
	public void setDeclare(boolean declare);
	public DVBHTMLFormElement getForm();
	public String getCode();
	public void setCode(String code);
	public String getArchive();
	public void setArchive(String archive);
	public String getCodeBase();
	public void setCodeBase(String codebase);
	public String getCodeType();
	public void setCodeType(String codetype);
	public String getData();
	public void setData(String data);
	public String getHeight();
	public void setHeight(String height);
	public String getStandby();
	public void setStandby(String standby);
	public long getTabIndex();
	public void setTabIndex(String index);
	public String getType();
	public void setType(String type);
	public String getUseMap();
	public void setUseMap(String usemap);
	public String getWidth();
	public void setWidth(String width);
	public Document getContentDocument();
}