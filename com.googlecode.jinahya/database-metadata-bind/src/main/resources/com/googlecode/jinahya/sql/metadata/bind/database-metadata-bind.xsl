<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : default.xsl
    Created on : October 27, 2011, 4:57 PM
    Author     : Jin Kwon
    Description:
        Purpose of transformation follows.
-->


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:h="http://www.w3.org/1999/xhtml"
                xmlns:b="http://jinahya.googlecode.com/sql/metadata/bind"
                version="1.0">
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
            width:100%; <xsl:comment>this is my addition :)</xsl:comment>
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
    <xsl:for-each select="b:catalog">
      <tr xmlns="http://www.w3.org/1999/xhtml">
        <td><!-- table -->
        </td>
        <td><!-- column -->
          <xsl:value-of select="b:columnName/text()"/>
        </td>
        <td><!-- type -->
          <xsl:value-of select="b:typeName/text()"/>
        </td>
        <td><!-- size -->
          <xsl:value-of select="b:columnSize/text()"/>
        </td>
        <td><!-- nullable -->
          <xsl:variable name="nullable" select="b:nullable/text()"/>
          <xsl:choose>
            <xsl:when test="$nullable=0">
              <xsl:text>false</xsl:text>
            </xsl:when>
            <xsl:when test="$nullable=1">
              <xsl:text>true</xsl:text>
            </xsl:when>
            <xsl:when test="$nullable=2">
              <xsl:text>unknwon</xsl:text>
            </xsl:when>
            <xsl:otherwise>
              <xsl:text>!</xsl:text>
            </xsl:otherwise>
          </xsl:choose>
        </td>
      </tr>
    </xsl:for-each>
  </xsl:template>
  <xsl:template match="b:dataTypes">
    <table xmlns="http://www.w3.org/1999/xhtml">
      <caption>
        <xsl:text>DATA TYPES</xsl:text>
      </caption>
      <thead>
        <tr>
          <th>TYPE_NAME</th>
          <th>DATA_TYPE</th>
          <th>PRECISION</th>
          <th>LITERAL_PREFIX</th>
          <th>LITERAL_SURFIX</th>
          <th>CREATE_PARAMS</th>
          <th>NULLABLE</th>
          <th>CASE_SENSITIVE</th>
          <th>SEARCHABLE</th>
          <th>UNSIGNED_ATTRIBUTE</th>
          <th>FIXED_PREC_SCALE</th>
          <th>AUTO_INCREMENT</th>
          <th>LOCAL_TYPE_NAME</th>
          <th>MINIMUM_SCALE</th>
          <th>MAXIMUM_SCALE</th>
          <th>SQL_DATA_TYPE</th>
          <th>SQL_DATETIME_SUB</th>
          <th>NUM_PREC_RADIX</th>
        </tr>
      </thead>
      <tbody>
        <xsl:for-each select="b:dataType">
          <tr>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TYPE_NAME']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='DATA_TYPE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='PRECISION']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='LITERNAL_PREFIX']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='LITERAL_SUFFIX']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='CREATE_PARAMS']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='NULLABLE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='CASE_SENSITIVE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='SEARCHABLE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='UNSIGNED_ATTRIBUTE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='FIXED_PREC_SCALE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='AUTO_INCREMENT']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='LOCAL_TYPE_NAME']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='MINIMUM_SCALE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='MAXIMUM_SCALE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='SQL_DATA_TYPE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='SQL_DATETIME_SUB']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='NUM_PREC_RADIX']"/>
            </td>
          </tr>
        </xsl:for-each>
      </tbody>
      <tfoot>
      </tfoot>
    </table>
  </xsl:template>
  <xsl:template match="b:tableTypes">
    <table xmlns="http://www.w3.org/1999/xhtml">
      <caption>
        <xsl:text>TABLE TYPES</xsl:text>        
      </caption>
      <thead>
        <tr>
          <th>TABLE_TYPE</th>
        </tr>
      </thead>
      <tbody>
        <xsl:for-each select="b:tableType">
          <tr>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_TYPE']"/>
            </td>
          </tr>
        </xsl:for-each>
      </tbody>
      <tfoot>
      </tfoot>
    </table>
  </xsl:template>
</xsl:stylesheet>
