/*
 * SAXParser.java
 * 14/11/2009
 * Twitter API Micro Edition
 * Copyright(c) Ernandes Mourao Junior (ernandes@gmail.com)
 * All rights reserved
 */
package impl.android.com.twitterapime.parser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.twitterapime.parser.Handler;
import com.twitterapime.parser.Parser;
import com.twitterapime.parser.ParserException;
import com.twitterapime.parser.XMLHandler;

/**
 * <p>
 * This class implements a parser based on SAXParser library.
 * </p>
 * 
 * @author Ernandes Mourao Junior (ernandes@gmail.com)
 * @version 1.0
 * @since 1.1
 */
public final class SAXParser extends Parser {
	/**
	 * @see com.twitterapime.parser.Parser#parse(java.io.InputStream, com.twitterapime.parser.Handler)
	 */
	public void parse(InputStream stream, Handler handler) throws IOException,
		ParserException {
		if (stream == null) {
			throw new NullPointerException("stream must not be null.");
		}
		if (handler == null) {
			throw new NullPointerException("handler must not be null.");
		} else if (!(handler instanceof XMLHandler)) {
			throw new ClassCastException(
				"handler must be an instace of XMLHandler.");
		}
		//
		javax.xml.parsers.SAXParserFactory factory =
			javax.xml.parsers.SAXParserFactory.newInstance();
		//
		try {
			javax.xml.parsers.SAXParser parser = factory.newSAXParser();
			//
			parser.parse(stream, new WrapperHandler((XMLHandler)handler));
		} catch (ParserConfigurationException e) {
			throw new ParserException(e.getMessage());
		} catch (SAXException e) {
			if (e.getCause() instanceof ParserException) {
				throw (ParserException)e.getCause();
			} else {
				throw new ParserException(e.getMessage());
			}
		} catch (FactoryConfigurationError e) {
			throw new ParserException(e.getMessage());
		}
	}
	
	/**
	 * <p>
	 * This class defines a default handler wrapper to translate the SAX events.
	 * </p>
	 * 
	 * @author Ernandes Mourao Junior (ernandes@gmail.com)
	 * @version 1.0
	 * @since 1.1
	 */
	private static final class WrapperHandler extends DefaultHandler {
		/**
		 * <p>
		 * XML handler object;
		 * </p>
		 */
		private XMLHandler handler;
		
		/**
		 * <p>
		 * SAX attributes object;
		 * </p>
		 */
		private SAXAttributes attrs;
		
		/**
		 * 
		 */
		private StringBuilder text;
		
		/**
		 * <p>
		 * Create an instance of WrapperHandler class.
		 * </p>
		 * @param handler Handler.
		 */
		public WrapperHandler(XMLHandler handler) {
			this.handler = handler;
			attrs = new SAXAttributes();
			text = new StringBuilder();
		}
		
		/**
		 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
		 */
		public void startDocument() throws SAXException {
			try {
				handler.startDocument();
			} catch (ParserException e) {
				throw new SAXException(e.getMessage(), e);
			}
		}
		
		/**
		 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
		 */
		public void endDocument() throws SAXException {
			try {
				handler.endDocument();
			} catch (ParserException e) {
				throw new SAXException(e.getMessage(), e);
			}
		}
		
		/**
		 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
		 */
		public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
			try {
				attrs.loadAttributes(attributes);
				handler.startElement(uri, localName, qName, attrs);
			} catch (ParserException e) {
				throw new SAXException(e.getMessage(), e);
			}
		}
		
		/**
		 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
		 */
		public void endElement(String uri, String localName, String qName)
			throws SAXException {
			try {
				if (text.length() > 0) {
					handler.text(text.toString().trim());
					text.setLength(0);
				}
				//
				handler.endElement(uri, localName, qName);
			} catch (ParserException e) {
				throw new SAXException(e.getMessage(), e);
			}
		}
		
		/**
		 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
		 */
		public void characters(char[] ch, int start, int length)
			throws SAXException {
			text.append(new String(ch, start, length));
		}
	}
}