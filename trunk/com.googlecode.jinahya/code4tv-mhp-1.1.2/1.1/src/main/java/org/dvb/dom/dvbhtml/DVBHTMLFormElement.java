package org.dvb.dom.dvbhtml;

public interface DVBHTMLFormElement extends DVBHTMLElement {
	public String getAction();
	public void setAction(String action);
	public String getCharSet();
	public void setCharSet(String charset);
	public DVBHTMLCollection getElements();
	public String getEncType();
	public void setEncType(String action);
	public long getLength();
	public String getMethod();
	public void setMethod(String action);
	public String getTarget();
	public void setTarget(String action);
	public void submit();
	public void reset();
}