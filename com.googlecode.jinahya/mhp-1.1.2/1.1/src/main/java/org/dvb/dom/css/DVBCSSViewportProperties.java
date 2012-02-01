package org.dvb.dom.css;

public interface DVBCSSViewportProperties {
	public String getPseudoClass();
	public String getArea();
	public void setArea(String area);
	public String[] getBackgroundVideoTransform();
	public void setBackgroundVideoTransform(String transform[]);
	public String[] getBackgroundVideo();
	public void setBackgroundVideo(String video[]);
	public String[] getBackgroundVideoPreserveAspect();
	public void setBackgroundVideoPreserveAspect(String aspect[]);
	public String[] getBackgroundVideoClip();
	public void setBackgroundVideoClip(String clip[]);
	public String[] getBackgroundVideoRectangle();
	public void setBackgroundVideoRectangle(String rectangle[]);
	public String getBackground();
	public void setBackground(String background);
	public String getBackgroundImageRectangle();
	public void setBackgroundImageRectangle(String rect);
	public String getInitial();
	public void setInitial(String initial);
	public int getVerticalResolution();
	public void setVerticalResolution(int res);
	public int getHorizontalResolution();
	public void setHorizontalResolution(int res);
	public String getScene();
	public void setScene(String scene);
}