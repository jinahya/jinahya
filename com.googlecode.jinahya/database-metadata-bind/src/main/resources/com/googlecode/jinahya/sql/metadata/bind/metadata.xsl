<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:h="http://www.w3.org/1999/xhtml"
                xmlns:b="http://jinahya.googlecode.com/sql/metadata/bind"
                version="1.0">

  <xsl:import href="dataTypes.xsl"/>
  <xsl:import href="entries.xsl"/>
  <xsl:import href="tables.xsl"/>

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
        <h1>Properties</h1>
        <xsl:apply-templates select="b:properties"/>
        <h1>Catalogs</h1>
        <xsl:apply-templates select="b:catalogs"/>
        <h1>Data Types</h1>
        <xsl:apply-templates select="b:dataTypes"/>
        <h1>Schemas</h1>
        <xsl:apply-templates select="b:schemas"/>
        <h1>Tables</h1>
        <xsl:apply-templates select="b:tables"/>
        <h1>Table Types</h1>
        <xsl:apply-templates select="b:tableTypes"/>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="b:properties">
    <xsl:call-template name="entries-to-table">
      <xsl:with-param name="caption" select="'PROPERTIES'"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="b:catalogs">
    <xsl:call-template name="entries-to-table">
      <xsl:with-param name="caption" select="'Entries'"/>
    </xsl:call-template>
    <xsl:apply-templates select="functionColumns"/>
  </xsl:template>

  <xsl:template match="b:functionColumns">
    <xsl:call-template name="entries-to-table">
      <xsl:with-param name="caption" select="'Function Columns'"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="b:dataTypes">
    <xsl:call-template name="dataTypes-to-table"/>
  </xsl:template>

  <xsl:template match="b:schemas">
    <xsl:call-template name="entries-to-table">
      <xsl:with-param name="caption" select="'SCHEMAS'"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="b:tables">
    <xsl:call-template name="tables-to-table">
      <xsl:with-param name="caption" select="'TABLES'"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="b:tableTypes">
    <xsl:call-template name="entries-to-table">
      <xsl:with-param name="caption" select="'TABLE TYPES'"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="b:entries">
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
