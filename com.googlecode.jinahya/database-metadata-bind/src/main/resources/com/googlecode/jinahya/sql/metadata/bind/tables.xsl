<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : tables.xsl
    Created on : October 27, 2011, 4:57 PM
    Author     : Jin Kwon
    Description:
        Purpose of transformation follows.
-->


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://jinahya.googlecode.com/sql/metadata/bind"
                version="1.0">
  <xsl:output method="xml" media-type="application/xhtml+xml"
              doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
              doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" encoding="UTF-8"
              indent="yes"/>
  <xsl:param name="head.title"/>
  <xsl:param name="head.stylesheetHref"/>
  <xsl:param name="table.catption"/>
  <xsl:param name="stylesheet"/>
  <xsl:template match="/">
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
      <head>
        <title><xsl:value-of select="$head.title"/></title>
        <link rel="stylesheet" href="{$head.stylesheetHref}" type="text/css"/>
      </head>
      <body>
        <table>
          <caption>
            <xsl:value-of select="$table.caption"/>
          </caption>
          <thead>
            <tr>
              <th>table</th>
              <th>column</th>
              <th>type</th>
              <th>size</th>
              <th>nullable</th>
            </tr>
          </thead>
          <tbody>
            <xsl:for-each select="b:tables/b:table">
              <tr>
                <td>
                  <xsl:value-of select="b:tableName"/>
                </td>
                <td><!-- column -->
                </td>
                <td><!-- size -->
                  <xsl:value-of select="b:tableType/text()"/>
                </td>
                <td><!-- size -->
                </td>
                <td><!-- nullable -->
                </td>
              </tr>
              <xsl:apply-templates select="b:columns"/>
            </xsl:for-each>
          </tbody>
          <tfoot>
          </tfoot>
        </table>
      </body>
    </html>
  </xsl:template>
  <xsl:template match="b:columns">
    <xsl:for-each select="b:column">
      <tr>
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
</xsl:stylesheet>
