package org.dvb.dom.dvbhtml;

import org.w3c.dom.Document;

public interface DVBHTMLFrameElement extends DVBHTMLElement {
	public Document getContentDocument();
	public String getFrameBorder();
	public void setFraneBorder(String border);
	public String getLongDesc();
	public void setLongDesc(String longdesc);
	public String getMarginHeight();
	public void setMarginHeight(String marginheight);
	public String getMarginWidth();
	public void setMarginWidth(String marginwidth);
	public String getScrolling();
	public void setScrolling(String scrolling);
	public String getSrc();
	public void setSrc(String src);
}