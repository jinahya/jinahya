package org.dvb.dom.dvbhtml;

import org.w3c.dom.*;

public interface DVBHTMLElement extends Element {
	public String getClassName();
	public void setClassName();
	public String getDir();
	public void setDir();
	public String getId();
	public void setId(String id);
	public String getLang();
	public void setLang(String lang);
	public String getTitle();
	public void setTitle(String t);
}
