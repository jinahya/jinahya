using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace XML2JSON
{
    public partial class RELAY : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            String src = Request.Params.Get("src");
            if (src == null)
            {
                Response.StatusCode = 400;
                Response.StatusDescription = "Missing request parameter: src";
                //Response.Redirect("~/ErrorPages/BadRequest.htm");
                Response.End();
                return;
            }

            Response.Clear();
            Response.ContentType = "application/octet-stream";
            //Response.BufferOutput = true;
            Response.StatusCode = 200;

            Stream source = new WebClient().OpenRead(src);
            byte[] buffer = new byte[8192];
            for (int read = -1; (read = source.Read(buffer, 0, buffer.Length)) > 0; ) {
                Response.OutputStream.Write(buffer, 0, read);
            }
            Response.OutputStream.Flush();
            Response.Flush();
            Response.End();
        }
    }
}
