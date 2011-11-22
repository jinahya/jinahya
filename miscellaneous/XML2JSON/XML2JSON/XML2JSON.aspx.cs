﻿using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Text.RegularExpressions;
using System.Web.SessionState;
using System.Web.UI;
using System.Xml;


namespace XML2JSON
{
    public partial class XML2JSON : Page
    {

        //private static TextWriter LogWriter = TextWriter.Synchronized(new StreamWriter("C:\\log\\XML2JSON.txt", true));
        private static TextWriter LogWriter = null;

        private static void element2json(XmlElement element, TextWriter writer)
        {
            writer.Write("{");
            Dictionary<String, Object> dictionary = new Dictionary<String, Object>();

            for (IEnumerator e = element.Attributes.GetEnumerator(); e.MoveNext(); )
            {
                XmlAttribute attribute = (XmlAttribute)e.Current;
                dictionary.Add("@" + attribute.Name, attribute.Value);
            }

            foreach (XmlNode childNode in element.ChildNodes)
            {
                Object list;
                switch (childNode.NodeType)
                {
                    case XmlNodeType.None:
                        break;
                    case XmlNodeType.Element:
                        if (!dictionary.TryGetValue(childNode.Name, out list))
                        {
                            list = new LinkedList<XmlNode>();
                            dictionary.Add(childNode.Name, list);
                        }
                        ((LinkedList<XmlNode>)list).AddLast(childNode);
                        break;
                    case XmlNodeType.Attribute:
                        break;
                    case XmlNodeType.Text:
                    case XmlNodeType.CDATA:
                        if (!dictionary.TryGetValue("#text", out list))
                        {
                            list = new LinkedList<XmlNode>();
                            dictionary.Add("#text", list);
                        }
                        ((LinkedList<XmlNode>)list).AddLast(childNode);
                        break;
                    case XmlNodeType.EntityReference:
                        break;
                    case XmlNodeType.Entity:
                        break;
                    case XmlNodeType.ProcessingInstruction:
                        break;
                    case XmlNodeType.Comment:
                        break;
                    case XmlNodeType.Document:
                        break;
                    case XmlNodeType.DocumentType:
                        break;
                    case XmlNodeType.DocumentFragment:
                        break;
                    case XmlNodeType.Notation:
                        break;
                    case XmlNodeType.Whitespace:
                        break;
                    case XmlNodeType.SignificantWhitespace:
                        break;
                    case XmlNodeType.EndElement:
                        break;
                    case XmlNodeType.EndEntity:
                        break;
                    case XmlNodeType.XmlDeclaration:
                        break;
                    default:
                        break;
                }
            }

            // ATTRIBUTES
            bool hasPrevious = false;
            foreach (KeyValuePair<String, Object> pair in dictionary)
            {
                if (!pair.Key.StartsWith("@"))
                {
                    continue;
                }
                if (hasPrevious)
                {
                    writer.Write(", ");
                }
                writer.Write("\"{0}\": \"{1}\"", pair.Key.Substring(1), pair.Value); // without "@"
                //writer.Write("\"{0}\": \"{1}\"", pair.Key, pair.Value);
                hasPrevious = true;
            }

            if (hasPrevious)
            {
                writer.Write(", ");
            }
            Object textNodeList;
            if (!dictionary.TryGetValue("#text", out textNodeList))
            {
                writer.Write("\"text\": null");
                //writer.Write("\"#text\": null");
            }
            else
            {
                writer.Write("\"text\": \"");
                //writer.Write("\"#text\": \"");

                foreach (XmlNode textNode in ((LinkedList<XmlNode>)textNodeList))
                {
                    if (textNode.NodeType == XmlNodeType.CDATA)
                    {
                        StringBuilder sb = new StringBuilder();
                        foreach (char c in textNode.Value)
                        {
                            switch (c)
                            {
                                case '\"':
                                    sb.Append("\\\"");
                                    break;
                                case '\\':
                                    sb.Append("\\\\");
                                    break;
                                case '\b':
                                    sb.Append("\\b");
                                    break;
                                case '\f':
                                    sb.Append("\\f");
                                    break;
                                case '\n':
                                    sb.Append("\\n");
                                    break;
                                case '\r':
                                    sb.Append("\\r");
                                    break;
                                case '\t':
                                    sb.Append("\\t");
                                    break;
                                default:
                                    int i = (int)c;
                                    if (i < 32 || i > 127)
                                    {
                                        sb.AppendFormat("\\u{0:X04}", i);
                                    }
                                    else
                                    {
                                        sb.Append(c);
                                    }
                                    break;
                            }
                        }
                        writer.Write(sb.ToString());
                    }
                    else
                    {
                        writer.Write(textNode.Value);
                    }
                }
                writer.Write("\"");
            }
            hasPrevious = true;

            foreach (KeyValuePair<String, Object> pair in dictionary)
            {
                if (pair.Key.StartsWith("@") || pair.Key.Equals("#text"))
                {
                    continue;
                }
                if (hasPrevious)
                {
                    writer.Write(", ");
                }
                writer.Write("\"{0}\": [", pair.Key);
                hasPrevious = false;
                Object elementNodeList;
                dictionary.TryGetValue(pair.Key, out elementNodeList);
                foreach (XmlNode elementNode in
                         ((LinkedList<XmlNode>)elementNodeList))
                {
                    if (hasPrevious)
                    {
                        writer.Write(", ");
                    }
                    element2json((XmlElement)elementNode, writer);
                    hasPrevious = true;
                }
                writer.Write("]");
                hasPrevious = true;
            }

            writer.Write("}");

        }


        protected void Page_Load(object sender, EventArgs e)
        {
            String xml = Request.Params.Get("xml");
            if (xml == null)
            {
                Log("No parameter for 'xml'");

                Response.StatusCode = 400;
                Response.StatusDescription = "Missing request parameter: xml";
                //Response.Redirect("~/ErrorPages/BadRequest.htm");
                Response.End();
                return;
            }

            Log("xml: " + xml);

            Response.Clear();
            //Response.ContentType = "application/json";
            Response.ContentType = "text/plain";
            Response.Charset = "UTF-8";
            Response.BufferOutput = true;
            Response.StatusCode = 200;

            XmlDocument document = new XmlDocument();

            //XmlReaderSettings settings = new XmlReaderSettings();
            //settings.XmlResolver = null;
            //settings.ProhibitDtd = false;
            //XmlReader reader = XmlTextReader.Create(xml);
            //document.Load(reader);

            document.XmlResolver = null;
            try
            {
                document.Load(xml);
                Log("xml document has been loaded");
            }
            catch (Exception exception)
            {
                Log("failed to load xml document from " + xml, exception);
            }
            //document.Load(new WebClient().OpenRead(xml)));

            foreach (XmlNode childNode in document.ChildNodes)
            {
                if (childNode.NodeType != XmlNodeType.Element)
                {
                    continue;
                }
                TextWriter writer = Response.Output;
                writer.Write("{");
                writer.Write("\"{0}\": ", childNode.Name);
                element2json((XmlElement)childNode, writer);
                writer.WriteLine("}");
                break;
            }

            Log("conversion has been finished");

            // Send the output to the client.
            Response.Flush();
            Log("flushed");
            Response.End();
        }

        
        protected void Page_Init(object sender, EventArgs e)
        {
            //LogWriter = TextWriter.Synchronized(new StreamWriter(this.MapPathSecure("log.txt")));
        }


        protected void Page_Disposed(object sender, EventArgs e)
        {
            //LogWriter.Flush();
            //LogWriter.Close();
        }

             
        private void Log(String message)
        {
            if (LogWriter == null)
            {
                return;
            }
            Log(message, null);
        }


        private void Log(String message, Exception exception)
        {
            if (LogWriter == null)
            {
                return;
            }
            LogWriter.WriteLine("{0}: {1}", Session.SessionID, message);
            LogWriter.Flush();
            if (exception != null)
            {
                Log(exception);
            }
        }


        private void Log(Exception exception)
        {
            if (LogWriter == null)
            {
                return;
            }
            LogWriter.WriteLine("\t{0}: {1}", exception.GetType(), exception.Message);
            Exception innerException = exception.InnerException;
            if (innerException != null)
            {
                Log(innerException);
            }
        }

    }
}