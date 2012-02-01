package org.dvb.dom.dvbhtml;

import org.w3c.dom.*;

public interface DVBHTMLIFrameElement extends DVBHTMLElement {
	public String getAlign();
	public void setAlign(String align);
	public Document getContentDocument();
	public String getFrameBorder();
	public void setFraneBorder(String border);
	public String getHeight();
	public void setHeight(String height);
	public String getLongDesc();
	public void setLongDesc(String longdesc);
	public String getMarginHeight();
	public void setMargineHeight(String marginheight);
	public String getMarginWidth();
	public void setMarginWidth(String marginwidth);
	public String getName();
	public void setName(String name);
	public String getScrolling();
	public void setScrolling(String scrolling);
	public String getSrc();
	public void setSrc(String src);
	public String getWidth();
	public void setWidth(String width);
}