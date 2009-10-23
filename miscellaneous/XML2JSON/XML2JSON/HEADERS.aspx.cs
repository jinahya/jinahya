using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace XML2JSON
{
    public partial class HEADERS : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Response.Clear();
            Response.ContentType = "text/plain";
            Response.Charset = "UTF-8";
            Response.BufferOutput = true;
            Response.StatusCode = 200;

            Response.Write("{");

            NameValueCollection headers = Request.Headers;
            String[] names = headers.AllKeys;
            for (int i = 0; i < names.Length; i++)
            {
                if (i > 0)
                {
                    Response.Write(",");
                }
                Response.Write("\"" + names[i] + "\":[");
                String[] values = headers.GetValues(names[i]);
                for (int j = 0; j < values.Length; j++)
                {
                    if (j > 0)
                    {
                        Response.Write(",");
                    }
                    Response.Write("\"" + values[j] + "\"");
                }
                Response.Write("]");
            }

            String stbHeaderName = "X-kt-stb-version";
            String[] stbValues = headers.GetValues(stbHeaderName);
            if (stbValues != null)
            {
                String stbValuePrefix = "megatvdnp";
                for (int i = 0; i < stbValues.Length; i++)
                {
                    if (stbValues[i].StartsWith(stbValuePrefix)) {

                        Regex regex0 = new Regex(stbValuePrefix + "\\((.+)\\)");
                        MatchCollection matches0 = regex0.Matches(stbValues[i]);
                        if (matches0.Count == 0)
                        {
                            break;
                        }
                        String pairValue = matches0[0].Groups[1].Value;
                        Response.Write(",\"" + stbHeaderName + "." + stbValuePrefix + ".value\":\"" + pairValue + "\"");

                        Regex regex = new Regex("([^;]+):([^;]+)");
                        MatchCollection matches = regex.Matches(pairValue);
                        Response.Write(",\"" + stbHeaderName + "." + stbValuePrefix + ".count\":" + matches.Count);
                        foreach (Match match in matches)
                        {
                            String key = match.Groups[1].Value;
                            String val = match.Groups[2].Value;
                            Response.Write(",\"" + stbHeaderName + "." + stbValuePrefix + "." + key + "\":\"" + val +"\"");
                        }

                        break;
                    }
                }
            }


            Response.Write("}");

            Response.Flush();
            Response.End();
        }
    }
}
