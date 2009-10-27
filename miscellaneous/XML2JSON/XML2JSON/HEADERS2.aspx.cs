using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace XML2JSON
{
    public partial class HEADERS2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            String uri = Request.Params.Get("uri");
            if (uri == null)
            {
                Response.StatusCode = 400;
                Response.StatusDescription = "Missing request parameter: uri";
                //HttpContext.Current.ApplicationInstance.CompleteRequest();
                Response.End();
                return;
            }

            UriBuilder builder = new UriBuilder(new Uri(uri).AbsoluteUri);

            NameValueCollection headers = Request.Headers;

            String stbHeaderName = "X-kt-stb-version";
            String[] stbValues = headers.GetValues(stbHeaderName);
            if (stbValues != null)
            {
                String stbValuePrefix = "megatvdnp";
                for (int i = 0; i < stbValues.Length; i++)
                {
                    if (stbValues[i].StartsWith(stbValuePrefix))
                    {

                        Regex regex0 = new Regex(stbValuePrefix + "\\((.+)\\)");
                        MatchCollection matches0 = regex0.Matches(stbValues[i]);
                        if (matches0.Count == 0)
                        {
                            break;
                        }
                        String pairValue = matches0[0].Groups[1].Value;

                        Regex regex = new Regex("([^;]+):([^;]+)");
                        MatchCollection matches = regex.Matches(pairValue);
                        foreach (Match match in matches)
                        {
                            String key = match.Groups[1].Value;
                            String val = match.Groups[2].Value;
                            string queryToAppend = key + "=" + val;
                            if (builder.Query != null && builder.Query.Length > 1)
                            {
                                builder.Query = builder.Query.Substring(1) + "&" + queryToAppend;
                            }
                            else
                            {
                                builder.Query = queryToAppend;
                            }
                        }

                        break;
                    }
                }
            }

            Response.Redirect(builder.Uri.OriginalString);
        }
    }
}
