/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.sql.metadata;


import java.lang.reflect.Method;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Metadata implements Retrievable {


    public static Metadata newInstance(
        final DatabaseMetaData databaseMetaData) {

        final Metadata instance = new Metadata();



        return instance;
    }


    @Override
    public void retrieve(final DatabaseMetaData databaseMetaData)
        throws SQLException {

        allProceduresAreCallable = databaseMetaData.allProceduresAreCallable();

        allTablesAreSelectable = databaseMetaData.allTablesAreSelectable();

        autoCommitFailureClosesAllResultSets =
            databaseMetaData.autoCommitFailureClosesAllResultSets();

        dataDefinitionCausesTransactionCommit =
            databaseMetaData.dataDefinitionCausesTransactionCommit();

        dataDefinitionIgnoredInTransactions =
            databaseMetaData.dataDefinitionIgnoredInTransactions();

        deletesAreDetected.put(
            ResultSet.TYPE_FORWARD_ONLY,
            databaseMetaData.deletesAreDetected(ResultSet.TYPE_FORWARD_ONLY));
        deletesAreDetected.put(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            databaseMetaData.deletesAreDetected(
            ResultSet.TYPE_SCROLL_INSENSITIVE));
        deletesAreDetected.put(
            ResultSet.TYPE_SCROLL_SENSITIVE,
            databaseMetaData.deletesAreDetected(
            ResultSet.TYPE_SCROLL_SENSITIVE));

        doesMaxRowSizeIncludeBlobs =
            databaseMetaData.doesMaxRowSizeIncludeBlobs();

        generatedKeyAlwaysReturned =
            databaseMetaData.generatedKeyAlwaysReturned();


        try {
            final Method method = DatabaseMetaData.class.getMethod(
                "catCatalogs", (Class<?>[]) null);
            final List<Catalog> list = Retrievables.retrieve(
                Catalog.class, databaseMetaData, method, (Object[]) null);
            for (Catalog element : list) {
                getCatalogs().put(element.getTableCat(), element);
            }
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }

        catalogSeparator = databaseMetaData.getCatalogSeparator();

        catalogTerm = databaseMetaData.getCatalogTerm();

        try {
            final Method method = DatabaseMetaData.class.getMethod(
                "getClientInfoProperties", (Class<?>[]) null);
            final List<ClientInfoProperty> list = Retrievables.retrieve(
                ClientInfoProperty.class, databaseMetaData, method,
                (Object[]) null);
            for (ClientInfoProperty element : list) {
                getClientInfoProperties().put(element.getName(), element);
            }
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }


    public boolean deletesAreDetected(final int type) {

        return deletesAreDetected.get(type);
    }


    public Map<String, Catalog> getCatalogs() {

        if (catalogs == null) {
            catalogs = new HashMap<String, Catalog>();
        }

        return catalogs;
    }


    public Map<String, ClientInfoProperty> getClientInfoProperties() {

        if (clientInfoProperties == null) {
            clientInfoProperties = new HashMap<String, ClientInfoProperty>();
        }

        return clientInfoProperties;
    }


    @XmlAttribute
    private boolean allProceduresAreCallable;


    @XmlAttribute
    private boolean allTablesAreSelectable;


    @XmlAttribute
    private boolean autoCommitFailureClosesAllResultSets;


    @XmlAttribute
    private boolean dataDefinitionCausesTransactionCommit;


    @XmlElement
    private boolean dataDefinitionIgnoredInTransactions;


    private final Map<Integer, Boolean> deletesAreDetected =
        new HashMap<Integer, Boolean>();


    @XmlElement
    private boolean doesMaxRowSizeIncludeBlobs;


    @XmlElement
    private boolean generatedKeyAlwaysReturned;


    @XmlElement(required = true)
    @XmlJavaTypeAdapter(Catalog.CatalogsMapAdapter.class)
    private Map<String, Catalog> catalogs;


    private String catalogSeparator;


    private String catalogTerm;


    @XmlElement
    @XmlJavaTypeAdapter(ClientInfoPropertiesMapAdapter.class)
    private Map<String, ClientInfoProperty> clientInfoProperties;


    private int databaseMajorVersion;


    private int databaseMinorVersion;


    private String databaseProductName;


    private String databaseProductVersion;


    private int defaultTransactionIsolation;


    private int driverMajorVersion;


    private int driverMinorVersion;


    private String driverName;


    private String driverVersion;


    private String extraNameCharacters;


    private String identifierQuoteString;


    private int jdbcMajorVersion;


    private int jdbcMinorVersion;


    private int maxBinaryLiteralLength;


    private int maxCatalogNameLength;


    private int maxCharLeteralLength;


    private int maxColumnNameLength;


    private int maxColumnsInGroupBy;


    private int maxColumnsInIndex;


    private int maxColumnsInOrderBy;


    private int maxColumnsInSelect;


    private int maxColumnsInTable;


    private int maxConnections;


    private int maxCursorNameLength;


    private int maxIndexLength;


    private int maxProcedureNameLength;


    private int maxRowSize;


    private int maxSchemaNameLength;


    private int maxStatementLength;


    private int maxStatements;


    private int maxTableNameLength;


    private int maxTablesInSelect;


    private int maxUserNameLength;


    private List<String> numericFunctions;


    private String procedureTerm;


    private int resultSetHoldability;

    // rowIdLifetime

    private String schemaTerm;


    private String searchStringEscape;


    private List<String> sqlKeywords;


    private int sqlStateType;


    private List<String> stringFunctions;


    private List<String> systemFunctions;


    private Collection<TableType> tableTypes;


    private List<String> timeDataFunctions;


    private Map<String, TypeInfo> typeInfo;


    private String url;


    private String userName;


    private Map<Integer, Boolean> insertsAreDetected;


    private boolean catalogAtStart;


    private boolean readOnly;


    private boolean locatorsUpdateCopy;


    private boolean nullPlusNonNullIsNull;


    private boolean nullsAreSortedAtEnd;


    private boolean nullsAreSortedAtStart;


    private boolean nullsAreSortedHigh;


    private boolean nullaAreSortedLow;


    private Map<Integer, Boolean> othersDeletesAreVisible;


    private Map<Integer, Boolean> othersInsertsAreVisible;


    private Map<Integer, Boolean> othersUpdatesAreVisible;


    private Map<Integer, Boolean> ownDeletesAreVisible;


    private Map<Integer, Boolean> ownInsertsAreVisible;


    private Map<Integer, Boolean> ownUpddatesAreVisible;

private boolean storesLowerCaseIdentifiers;

private boolean storesLowerCaseQuotedIdentifiers;

private boolean storesMixedCaseIdentifiers;

private boolean storesMixedCaseQuotedIdentifiers;

private boolean storesUpperCaseIdentifiers;

private boolean storesUpperCaseQuotedIdentifiers;

private boolean supportsAlterTableWithAddColumn;

private boolean supportsAlterTableWithDropColumn;
private boolean supportsANSI92EntryLevelSQL;

private boolean supportsANSI92FullSQL;

private boolean supportsANSI92IntermediateSQL;

private boolean supportsBatchUpdates;

private boolean supportsCatalogsInDataManipulation;

private boolean supportsCatalogsInIndexDefinitions;

private boolean supportsCatalogsInPrivilegeDefinitions;

private boolean supportsCatalogsInProcedureCalls;

private boolean supportsCatalogsInTableDefinitions;

private boolean supportsColumnAliasing;

private boolean supportsConvert;

private Map<Integer, Integer>	supportedConverts;

 private boolean supportsCoreSQLGrammar;

 private boolean supportsCorrelatedSubqueries; 

private boolean supportsDataDefinitionAndDataManipulationTransactions;

private boolean supportsDataManipulationTransactionsOnly;

private  boolean supportsDifferentTableCorrelationNames;
 private boolean	supportsExpressionsInOrderBy; 

 private boolean	supportsExtendedSQLGrammar; 

 private boolean	supportsFullOuterJoins; 

 private boolean	supportsGetGeneratedKeys; 

 private boolean	supportsGroupBy; 

 private boolean	supportsGroupByBeyondSelect; 

 private boolean	supportsGroupByUnrelated; 

 private boolean	supportsIntegrityEnhancementFacility; 

 private boolean	supportsLikeEscapeClause; 

 private boolean	supportsLimitedOuterJoins; 

 private boolean	supportsMinimumSQLGrammar; 

 private boolean	supportsMixedCaseIdentifiers; 

 private boolean	supportsMixedCaseQuotedIdentifiers; 

 private boolean	supportsMultipleOpenResults; 

 private boolean	supportsMultipleResultSets; 

 private boolean	supportsMultipleTransactions; 

 private boolean	supportsNamedParameters; 

 private boolean	supportsNonNullableColumns; 

 private boolean	supportsOpenCursorsAcrossCommit; 

 private boolean	supportsOpenCursorsAcrossRollback; 

 private boolean	supportsOpenStatementsAcrossCommit; 

 private boolean	supportsOpenStatementsAcrossRollback; 

 private boolean	supportsOrderByUnrelated; 

 private boolean	supportsOuterJoins; 

 private boolean	supportsPositionedDelete; 

 private boolean	supportsPositionedUpdate; 

 private Map<Integer, Integer> supportsResultSetConcurrency;

 private Map<Integer, Boolean> supportsResultSetHoldability;

 private Map<Integer, Boolean> supportsResultSetType;

 private boolean	supportsSavepoints; 

 private boolean	supportsSchemasInDataManipulation; 

 private boolean	supportsSchemasInIndexDefinitions; 

 private boolean	supportsSchemasInPrivilegeDefinitions; 

 private boolean	supportsSchemasInProcedureCalls; 

 private boolean	supportsSchemasInTableDefinitions; 

 private boolean	supportsSelectForUpdate; 

 private boolean	supportsStatementPooling; 

 private boolean	supportsStoredFunctionsUsingCallSyntax; 

    private boolean supportsStoredProcedures;


    private boolean supportsSubqueriesInComparisons;


    private boolean supportsSubqueriesInExists;


    private boolean supportsSubqueriesInIns;


    private boolean supportsSubqueriesInQuantifieds;


    private boolean supportsTableCorrelationNames;


    private Map<Integer, Boolean> supportsTransactionIsolationLevel;


    private boolean supportsTransactions;


    private boolean supportsUnion;


    private boolean supportsUnionAll;


    private Map<Integer, Boolean> updatesAreDetected;


    private boolean usesLocalFilePerTable;


    private boolean usesLocalFiles;


}
