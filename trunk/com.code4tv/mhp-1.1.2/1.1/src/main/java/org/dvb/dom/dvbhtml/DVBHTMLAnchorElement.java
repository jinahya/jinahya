package org.dvb.dom.dvbhtml;

public interface DVBHTMLAnchorElement extends DVBHTMLElement {
	public String getAccessKey();
	public void setAccessKey(String accesskey);
	public String getCharSet();
	public void setCharSet(String charset);
	public String getHRef();
	public void setHRef(String href);
	public String getHRefLang();
	public void setHRefLang(String hreflang);
	public String getTarget();
	public void setTarget(String target);
	public String getType();
	public void setType(String type);
	public void blur();
	public void focus();
}
