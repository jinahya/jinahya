package org.dvb.dom.dvbhtml;

import org.w3c.dom.*;

public interface DVBHTMLDocument extends Document {
//Methods
	public DVBHTMLCollection getAnchors();
	public DVBHTMLElement getBody();
	public String getCookie();
	public String getDomain();
	public DVBHTMLCollection getForms();
	public DVBHTMLCollection getImages();
	public DVBHTMLCollection getLinks();
	public String getReference();
	public String getTitle();
	public void setTitle(String title);
	public String getURL();
	public void open() throws IllegalStateException;
	public void close() throws IllegalStateException;
	public void write(String text) throws IllegalStateException;
	public void writeIn(String text) throws IllegalStateException;
}