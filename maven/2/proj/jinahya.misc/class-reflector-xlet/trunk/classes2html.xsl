<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
  <xsl:output method="xml" indent="yes" encoding="UTF-8" doctype-public="-//W3C//DTD XHTML 1.1//EN" doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"/>

  <xsl:template match="/classes">
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
      <head>
        <title>Minimal XHTML 1.1 Document</title>
      </head>
      <body>
        <p>This is a minimal <a href="http://www.w3.org/TR/xhtml11">XHTML 1.1</a> document.</p>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
