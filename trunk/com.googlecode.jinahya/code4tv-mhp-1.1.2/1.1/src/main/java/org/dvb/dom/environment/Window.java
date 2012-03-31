package org.dvb.dom.environment;

import org.dvb.dom.dvbhtml.*;

public class Window extends java.lang.Object {
	public Window(){}
		
	/**
	 * 
	 * @param document
	 * @param impl  is of type DVBDOMImpl, as the doc says but no specs.
	 * @param frames
	 * @param length
	 * @param navigator
	 * @param parent
	 * @param window
	 * @param top
	 */
	public Window(DVBHTMLDocument document, Object impl,
	DVBHTMLCollection frames, long length, Navigator navigator,
	Window parent, Window window, Window top){}
	public String getDefaultStatus(){
		return null;
	}
	public void setDefaultStatus(String defaultstatus){}
	public DVBHTMLDocument getDocument(){
		return null;
	}
	
	/**
	 * 
	 * @return is of type DVBDOMImpl, , as the doc says but no specs.
	 */
	public Object getDVBDOMImpl(){
		return null;
	}
	public DVBHTMLCollection getFranes(){
		return null;
	}
	public long getLength(){
		return 0;
	}
	public Location getLocation(){
		return null;
	}
	public void setLocation(Location location){}
	public String getName(){
		return null;
	}
	public void setName(String name){}
	public Navigator getNavigator(){
		return null;
	}
	public Window getParent(){
		return null;
	}
	public Window getWindow(){
		return null;
	}
	public Window getSelf(){
		return null;
	}
	public String getStatus(){
		return null;
	}
	public void setStatus(String status){}
	public Window getTop(){
		return null;
	}
	public void open(String url, String name, String features){}
}