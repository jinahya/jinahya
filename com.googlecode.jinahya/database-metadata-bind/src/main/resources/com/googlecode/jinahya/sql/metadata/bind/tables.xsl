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

  <xsl:template match="b:tables" name="tables-to-table">
    <xsl:param name="caption" select="'TABLES'"/>
    <table xmlns="http://www.w3.org/1999/xhtml">
      <caption>
        <xsl:value-of select="$caption"/>
      </caption>
      <thead>
        <tr>
          <th>TABLE_CAT</th>
          <th>TABLE_SCHEM</th>
          <th>TABLE_NAME</th>
          <th>TABLE_TYPE</th>
          <th>REMARKS</th>
          <th>TYPE_CAT</th>
          <th>TYPE_SCHEM</th>
          <th>TYPE_NAME</th>
          <th>SELF_REFERENCING_COL_NAME</th>
          <th>REF_GENERATION</th>
        </tr>
      </thead>
      <tbody>
        <xsl:for-each select="b:table">
          <tr>
            <xsl:attribute name="id">
            <xsl:text>data-type</xsl:text><xsl:value-of select="b:entries/b:entry[@key='DATA_TYPE']"/>
            </xsl:attribute>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_CAT']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_SCHEM']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_NAME']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TABLE_TYPE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='REMARKS']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TYPE_CAT']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TYPE_SCHEM']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TYPE_NAME']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='SELF_REFERENCING_COL_NAME']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='REF_GENERATION']"/>
            </td>
          </tr>
        </xsl:for-each>
      </tbody>
      <tfoot>
      </tfoot>
    </table>
  </xsl:template>
</xsl:stylesheet>
