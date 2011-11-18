<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:h="http://www.w3.org/1999/xhtml"
                xmlns:b="http://jinahya.googlecode.com/sql/metadata/bind"
                xmlns:exsl="http://exslt.org/common"
                extension-element-prefixes="exsl"
                version="1.0">
  <xsl:output method="xml" media-type="application/xhtml+xml"
              doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
              doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" encoding="UTF-8"
              indent="yes"/>

  <xsl:template name="entries-to-table">
    <xsl:param name="caption"/>
    <table xmlns="http://www.w3.org/1999/xhtml">
      <xsl:if test="$caption">
        <caption>
          <xsl:value-of select="$caption"/>
        </caption>
      </xsl:if>
      <thead>
        <xsl:apply-templates mode="entries-to-thead-row" select="*[1]/b:entries"/>
      </thead>
      <tbody>
        <xsl:for-each select="*/b:entries">
          <xsl:apply-templates mode="entries-to-tbody-row" select="."/>
        </xsl:for-each>
      </tbody>
      <tfoot>
      </tfoot>
    </table>
  </xsl:template>

  <xsl:template match="b:entries" mode="entries-to-thead-row"  name="entries-to-thead-row">
    <tr xmlns="http://www.w3.org/1999/xhtml">
      <xsl:for-each select="b:entry">
        <th><xsl:value-of select="@key"/></th>
      </xsl:for-each>
    </tr>
  </xsl:template>

  <xsl:template match="b:entries" mode="entries-to-tbody-row" name="entries-to-tbody-row">
    <tr xmlns="http://www.w3.org/1999/xhtml">
      <xsl:for-each select="b:entry">
        <td><xsl:value-of select="text()"/></td>
      </xsl:for-each>
    </tr>
  </xsl:template>

</xsl:stylesheet>
