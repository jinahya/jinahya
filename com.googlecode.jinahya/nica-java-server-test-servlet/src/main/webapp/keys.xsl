<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : indexedKeys.xsl
    Created on : November 10, 2012, 6:50 PM
    Author     : Jin Kwon <jinahya at gmail.com>
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="http://www.w3.org/1999/xhtml"
                xmlns:nc="http://jinahya.googlecode.com/nica/test"
                exclude-result-prefixes="nc">
  <xsl:output method="html"/>
  <xsl:template match="/">
    <html>
      <head>
        <title>keys</title>
        <link rel="stylesheet" type="text/css" href="stylesheet/keys.css"/>
      </head>
      <body>
        <table class="gridtable">
          <thead>
            <tr>
              <th>index</th>
              <th>bytes</th>
            </tr>
          </thead>
          <tbody>
            <xsl:for-each select="nc:keys/nc:key">
              <tr>
                <td>
                  <code>
                    <xsl:value-of select="@index"/>
                  </code>
                </td>
                <td>
                  <code>
                    <xsl:value-of select="."/>
                  </code>
                </td>
              </tr>
            </xsl:for-each>
          </tbody>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
