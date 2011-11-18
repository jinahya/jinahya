<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:h="http://www.w3.org/1999/xhtml"
                xmlns:b="http://jinahya.googlecode.com/sql/metadata/bind"
                version="1.0">

  <xsl:import href="dataTypes.xsl"/>
  <xsl:import href="entries.xsl"/>

  <xsl:output method="xml" media-type="application/xhtml+xml"
              doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
              doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" encoding="UTF-8"
              indent="yes"/>
  <xsl:param name="head.title"/>
  <xsl:param name="table.catption"/>
  <xsl:param name="stylesheet"/>
  <xsl:template match="/b:metadata">
    <html xmlns="http://www.w3.org/1999/xhtml"> <!-- xml:lang="en" -->
      <head>
        <title>
          <xsl:value-of select="$head.title"/>
        </title>
        <!--link rel="stylesheet" href="http://www.456bereastreet.com/lab/css-table-gallery/coffee-with-milk.css" type="text/css"/-->
        <xsl:comment>http://www.456bereastreet.com/lab/css-table-gallery/coffee-with-milk.css</xsl:comment>
        <style type="text/css">
          table {
          font:normal 76%/150% "Lucida Grande", "Lucida Sans Unicode", Verdana, Arial, Helvetica, sans-serif;
          border-collapse:separate;
          border-spacing:0;
          margin:0 0 1em;
          color:#000;
          width:100%; 
          <xsl:comment>this is my addition :)</xsl:comment>
          }
          table a {
          color:#523A0B;
          text-decoration:none;
          border-bottom:1px dotted;
          }
          table a:visited {
          color:#444;
          font-weight:normal;
          }
          table a:visited:after {
          content:"\00A0\221A";
          }
          table a:hover {
          border-bottom-style:solid;
          }
          thead th,
          thead td,
          tfoot th,
          tfoot td {
          border:1px solid #523A0B;
          border-width:1px 0;
          background:#EBE5D9;
          }
          th {
          font-weight:bold;
          line-height:normal;
          padding:0.25em 0.5em;
          text-align:left;
          }
          tbody th,
          td {
          padding:0.25em 0.5em;
          text-align:left;
          vertical-align:top;
          }
          tbody th {
          font-weight:normal;
          white-space:nowrap;
          }
          tbody th a:link,
          tbody th a:visited {
          font-weight:bold;
          }
          tbody td,
          tbody th {
          border:1px solid #fff;
          border-width:1px 0;
          }
          tbody tr.odd th,
          tbody tr.odd td {
          border-color:#EBE5D9;
          background:#F7F4EE;
          }
          tbody tr:hover td,
          tbody tr:hover th {
          background:#ffffee;
          border-color:#523A0B;
          }
          caption {
          font-family:Georgia,Times,serif;
          font-weight:normal;
          font-size:1.4em;
          text-align:left;
          margin:0;
          padding:0.5em 0.25em;
          }
        </style>
      </head>
      <body>
        <xsl:apply-templates select="b:properties"/>
        <xsl:apply-templates select="b:catalogs"/>
        <xsl:apply-templates select="b:dataTypes"/>
        <xsl:apply-templates select="b:schemas"/>
        <xsl:apply-templates select="b:tables"/>
        <xsl:apply-templates select="b:tableTypes"/>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="b:properties">
    <xsl:for-each select="b:property">
      <table>
        <caption>
          <xsl:text>PROPERTIES</xsl:text>
        </caption>
        <thead>
          <tr>
            <th>NAME</th>
            <th>MAX_LEN</th>
            <th>DEAULT_VALUE</th>
            <th>DESCRIPION</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <xsl:value-of select="b:NAME"/>
            </td>
            <td>
              <xsl:value-of select="b:MAX_LEN/text()"/>
            </td>
            <td>
              <xsl:value-of select="b:DEAULT_VALUE/text()"/>
            </td>
            <td>
              <xsl:value-of select="b:DESCRIPTION/text()"/>
            </td>
          </tr>
        </tbody>
        <tfoot>
        </tfoot>
      </table>
    </xsl:for-each>
  </xsl:template>
  <xsl:template match="b:catalogs">
    <table xmlns="http://www.w3.org/1999/xhtml">
      <caption>
        <xsl:text>CATALOGS</xsl:text>
      </caption>
      <thead>
        <tr>
          <th>TABLE_CAT</th>
        </tr>
      </thead>
      <tbody>
        <xsl:for-each select="b:catalog">
          <tr>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_CAT']"/>
            </td>
          </tr>
        </xsl:for-each>
      </tbody>
      <tfoot>
      </tfoot>
    </table>
  </xsl:template>
  <xsl:template match="b:dataTypes">
    <xsl:call-template name="dataTypes-to-table"/>
  </xsl:template>

  <xsl:template match="b:schemas">
    <!--
    <xsl:call-template name="entries-parents-to-table">
      <xsl:with-param name="entries" select="b:entries"/>
    </xsl:call-template>
    -->
    <table xmlns="http://www.w3.org/1999/xhtml">
      <caption>
        <xsl:text>SCHEMAS</xsl:text>
      </caption>
      <thead>
        <tr>
          <th>TABLE_CATALOG</th>
          <th>TABLE_SCHEM</th>
        </tr>
      </thead>
      <tbody>
        <xsl:for-each select="b:schema">
          <tr>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_CATALOG']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_SCHEM']"/>
            </td>
          </tr>
        </xsl:for-each>
      </tbody>
      <tfoot>
      </tfoot>
    </table>
  </xsl:template>

  <xsl:template match="b:tables">
  </xsl:template>

  <xsl:template match="b:tableTypes">
    <xsl:call-template name="entries-to-table">
      <xsl:with-param name="caption" select="'TABLE TYPES'"/>
    </xsl:call-template>
    <!--table xmlns="http://www.w3.org/1999/xhtml">
      <caption>
        <xsl:text>TABLE TYPES</xsl:text>        
      </caption>
      <thead>
        <xsl:for-each select="b:tableType/b:entries">
          <xsl:if test="position()=1">
            <xsl:call-template name="entries-to-thead-row"/>
          </xsl:if>
        </xsl:for-each>
      </thead>
      <tbody>
        <xsl:for-each select="b:tableType/b:entries">
          <xsl:call-template name="entries-to-tbody-row"/>
        </xsl:for-each>
      </tbody>
      <tfoot>
      </tfoot>
    </table-->
  </xsl:template>

  <xsl:template match="b:entries">                            <!-- b:entries -->
    <xsl:param name="caption"/>
    <table xmlns="http://www.w3.org/1999/xhtml">
      <xsl:if test="$caption">
        <caption>
          <xsl:value-of select="$caption"/>
        </caption>
      </xsl:if>
      <thead>
        <tr>
          <xsl:for-each select="b:entry">
            <th><xsl:value-of select="@key"/></th>
          </xsl:for-each>
        </tr>
      </thead>
      <tbody>
        <tr>
          <xsl:for-each select="b:entry">
            <th><xsl:value-of select="text()"/></th>
          </xsl:for-each>
        </tr>
      </tbody>
      <tfoot>
      </tfoot>
    </table>
  </xsl:template>
</xsl:stylesheet>
