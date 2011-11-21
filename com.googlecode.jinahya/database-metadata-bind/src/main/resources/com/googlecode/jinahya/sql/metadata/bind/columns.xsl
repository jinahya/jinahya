<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:h="http://www.w3.org/1999/xhtml"
                xmlns:b="http://jinahya.googlecode.com/sql/metadata/bind"
                version="1.0">

  <xsl:import href="entries.xsl"/>

  <xsl:output method="xml" media-type="application/xhtml+xml"
              doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
              doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" encoding="UTF-8"
              indent="yes"/>

  <xsl:template match="b:columns" name="columns-to-table">
    <xsl:param name="caption" select="'COLUMNS'"/>
    <table xmlns="http://www.w3.org/1999/xhtml">
      <caption>
        <xsl:value-of select="$caption"/>
      </caption>
      <thead>
        <tr>
          <th>TABLE_CAT</th>
          <th>TABLE_SCHEM</th>
          <th>TABLE_NAME</th>
          <th>COLUMN_NAME</th>
          <th>DATA_TYPE</th>
          <th>TYPE_NAME</th>
          <th>COLUMN_SIZE</th>
          <th>BUFFER_LENGTH</th>
          <th>DECIMAL_DIGITS</th>
          <th>NUM_PREC_RADIX</th>
          <th>NULLABLE</th>
          <th>REMARKS</th>
          <th>COLUMN_DEF</th>
          <th>SQL_DATA_TYPE</th>
          <th>CHAR_OCTET_LENGTH</th>
          <th>ORDINAL_POSITION</th>
          <th>IS_NULLABLE</th>
          <th>SCOPE_CATLOG</th>
          <th>SCOPE_SCHEMA</th>
          <th>SCOPE_TABLE</th>
          <th>SOURCE_DATA_TYPE</th>
          <th>IS_AUTOINCREMENT</th>
        </tr>
      </thead>
      <tbody>
        <xsl:for-each select="b:table">
          <tr>
            <!--
            <xsl:attribute name="id">
              <xsl:text>data-type</xsl:text><xsl:value-of select="b:entries/b:entry[@key='DATA_TYPE']"/>
            </xsl:attribute>
            -->
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_CAT']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_CAT']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_SCHEM']"/>
            </td>
            <td><xsl:value-of select="b:entries/b:entry[@key='TABLE_NAME']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='COLUMN_NAME']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='DATA_TYPE']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='TYPE_NAME']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='COLUMN_SIZE']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='BUFFER_LENGTH']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='DECIMAL_DIGITS']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='NUM_PREC_RADIX']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='NULLABLE']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='REMARKS']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='COLUMN_DEF']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='SQL_DATA_TYPE']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='CHAR_OCTET_LENGTH']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='ORDINAL_POSITION']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='IS_NULLABLE']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='SCOPE_CATLOG']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='SCOPE_SCHEMA']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='SCOPE_TABLE']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='SOURCE_DATA_TYPE']"/></td>
            <td><xsl:value-of select="b:entries/b:entry[@key='IS_AUTOINCREMENT']"/></td>
          </tr>
        </xsl:for-each>
      </tbody>
      <tfoot>
      </tfoot>
    </table>
  </xsl:template>
</xsl:stylesheet>
