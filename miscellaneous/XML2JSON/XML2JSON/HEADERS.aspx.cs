using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
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

            Response.Write("}");

            Response.Flush();
            Response.End();
        }
    }
}
