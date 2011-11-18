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

  <xsl:param name="title" select="'DATA TYPES'"/>
  <xsl:param name="catption" select="'DATA TYPES'"/>

  <xsl:param name="stylesheet"/>

  <xsl:template match="b:dataType" name="dataTypes-to-table">
    <xsl:param name="caption" select="'DATA TYPES'"/>
    <table xmlns="http://www.w3.org/1999/xhtml">
      <caption>
        <xsl:value-of select="$caption"/>
      </caption>
      <thead>
        <tr>
          <th>TYPE_NAME</th>
          <!--th>DATA_TYPE</th--> <!-- remarked -->
          <th>PRECISION</th>
          <!--th>LITERAL_PREFIX</th--> <!-- remarked -->
          <!--th>LITERAL_SURFIX</th--> <!-- remarked -->
          <!--th>CREATE_PARAMS</th--> <!-- remarked -->
          <th>NULLABLE</th>
          <th>CASE_SENSITIVE</th>
          <th>SEARCHABLE</th>
          <th>UNSIGNED_ATTRIBUTE</th>
          <th>FIXED_PREC_SCALE</th>
          <th>AUTO_INCREMENT</th>
          <!--th>LOCAL_TYPE_NAME</th--> <!-- remarked -->
          <th>MINIMUM_SCALE</th>
          <th>MAXIMUM_SCALE</th>
          <!--th>SQL_DATA_TYPE</th--> <!-- unused -->
          <!--th>SQL_DATETIME_SUB</th--> <!-- unused -->
          <!--th>NUM_PREC_RADIX</th--> <!-- remarked -->
        </tr>
      </thead>
      <tbody>
        <xsl:for-each select="b:dataType">
          <tr>
            <xsl:attribute name="id">
            <xsl:text>data-type</xsl:text><xsl:value-of select="b:entries/b:entry[@key='DATA_TYPE']"/>
            </xsl:attribute>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='TYPE_NAME']"/>
            </td>
            <!--td>
              <xsl:value-of select="b:entries/b:entry[@key='DATA_TYPE']"/>
            </td-->
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='PRECISION']"/>
            </td>
            <!--td>
              <xsl:value-of select="b:entries/b:entry[@key='LITERNAL_PREFIX']"/>
            </td-->
            <!--td>
              <xsl:value-of select="b:entries/b:entry[@key='LITERAL_SUFFIX']"/>
            </td-->
            <!--td>
              <xsl:value-of select="b:entries/b:entry[@key='CREATE_PARAMS']"/>
            </td-->
            <xsl:variable name="NULLABLE" select="b:entries/b:entry[@key='NULLABLE']"/>
            <xsl:variable name="NULLABLE_TITLE">
              <xsl:choose>
                <xsl:when test="$NULLABLE=0">
                  <xsl:text>typeNoNulls</xsl:text>
                </xsl:when>
                <xsl:when test="$NULLABLE=1">
                  <xsl:text>typeNullable</xsl:text>
                </xsl:when>
                <xsl:when test="$NULLABLE=2">
                  <xsl:text>typeNullableUnknown</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:text>@@?</xsl:text>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <td>
              <xsl:attribute name="title"><xsl:value-of select="$NULLABLE_TITLE"/></xsl:attribute>
              <xsl:value-of select="$NULLABLE"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='CASE_SENSITIVE']"/>
            </td>
            <xsl:variable name="SEARCHABLE" select="b:entries/b:entry[@key='SEARCHABLE']"/>
            <xsl:variable name="SEARCHABLE_TITLE">
              <xsl:choose>
                <xsl:when test="$SEARCHABLE=0">
                  <xsl:text>typePredNone (No support)</xsl:text>
                </xsl:when>
                <xsl:when test="$SEARCHABLE=1">
                  <xsl:text>typePredChar (Only supported with WHERE .. LIKE)</xsl:text>
                </xsl:when>
                <xsl:when test="$SEARCHABLE=2">
                  <xsl:text>typePredBasic (Supported except for WHERE .. LIKE)</xsl:text>
                </xsl:when>
                <xsl:when test="$SEARCHABLE=3">
                  <xsl:text>typeSearchable (Supported for all WHERE ..)</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:text>@@?</xsl:text>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <td>
              <xsl:attribute name="title"><xsl:value-of select="$SEARCHABLE_TITLE"/></xsl:attribute>
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
            <!--td>
              <xsl:value-of select="b:entries/b:entry[@key='LOCAL_TYPE_NAME']"/>
            </td-->
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='MINIMUM_SCALE']"/>
            </td>
            <td>
              <xsl:value-of select="b:entries/b:entry[@key='MAXIMUM_SCALE']"/>
            </td>
            <!--td>
                <xsl:value-of select="b:entries/b:entry[@key='SQL_DATA_TYPE']"/>
                </td-->
            <!--td>
                <xsl:value-of select="b:entries/b:entry[@key='SQL_DATETIME_SUB']"/>
                </td-->
            <!--td>
              <xsl:value-of select="b:entries/b:entry[@key='NUM_PREC_RADIX']"/>
            </td-->
          </tr>
        </xsl:for-each>
      </tbody>
      <tfoot>
      </tfoot>
    </table>
  </xsl:template>
</xsl:stylesheet>
