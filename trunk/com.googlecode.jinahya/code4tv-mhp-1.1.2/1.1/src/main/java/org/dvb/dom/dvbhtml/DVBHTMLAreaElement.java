package org.dvb.dom.dvbhtml;

public interface DVBHTMLAreaElement extends DVBHTMLElement {
	public boolean isNoHRef();
	public void setNoHRef(boolean nohref);
	public String getAccessKey();
	public void setAccessKey(String accesskey);
	public String getAlt();
	public void setAlt(String alt);
	public String getHRef();
	public void setHRef(String href);
	public String getTarget();
	public void setTarget(String target);
}