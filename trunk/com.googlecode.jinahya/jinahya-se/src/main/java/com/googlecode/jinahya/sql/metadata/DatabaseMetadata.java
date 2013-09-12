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


import com.googlecode.jinahya.xml.bind.MapValues;
import com.googlecode.jinahya.xml.bind.MapValuesAdapter;
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
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class DatabaseMetadata implements Retrievable {


    public static class DeletesAreDetected {


        @XmlAttribute
        private int type;


        @XmlValue
        private boolean value;


    }


    public static class DeletesAreDetectedValues
        extends MapValues<DeletesAreDetected> {


        @XmlElement
        public List<DeletesAreDetected> getDeletesAreDetected() {

            return super.getValues();
        }


    }


    public static class DeletesAreDeletectedValuesAdapter
        extends MapValuesAdapter<DeletesAreDetectedValues, Integer, DeletesAreDetected> {


        public DeletesAreDeletectedValuesAdapter() {

            super(DeletesAreDetectedValues.class);
        }


        @Override
        protected Integer getKey(final DeletesAreDetected value) {

            return value.type;
        }


    }


    public static class UpdatesAreDetected {


        @XmlAttribute
        private int type;


        @XmlValue
        private boolean value;


    }


    public static class UpdatesAreDetectedValues
        extends MapValues<UpdatesAreDetected> {


        @XmlElement
        public List<UpdatesAreDetected> getUpdatesAreDetected() {

            return super.getValues();
        }


    }


    public static class UpdatesAreDetectedValuesAdapter
        extends MapValuesAdapter<UpdatesAreDetectedValues, Integer, UpdatesAreDetected> {


        public UpdatesAreDetectedValuesAdapter() {

            super(UpdatesAreDetectedValues.class);
        }


        @Override
        protected Integer getKey(final UpdatesAreDetected value) {

            return value.type;
        }


    }


    public static class SupportsTransactionIsolationLevel {


        @XmlAttribute
        private int level;


        @XmlValue
        private boolean value;


    }


    public static class SupportsTransactionIsolationLevelValues
        extends MapValues<SupportsTransactionIsolationLevel> {


        @XmlElement
        public List<SupportsTransactionIsolationLevel> getSupportsTransactionIsolationLevel() {

            return super.getValues();
        }


    }


    public static class SupportsTransactionIsolationLevelValuesAdapter
        extends MapValuesAdapter<SupportsTransactionIsolationLevelValues, Integer, SupportsTransactionIsolationLevel> {


        public SupportsTransactionIsolationLevelValuesAdapter() {

            super(SupportsTransactionIsolationLevelValues.class);
        }


        @Override
        protected Integer getKey(final SupportsTransactionIsolationLevel value) {

            return value.level;
        }


    }


    public static DatabaseMetadata newInstance(
        final DatabaseMetaData databaseMetaData) {

        final DatabaseMetadata instance = new DatabaseMetadata();



        return instance;
    }


    @Override
    public void retrieve(final DatabaseMetaData databaseMetaData)
        throws SQLException {

        // ------------------------------------------------------------ versions
        databaseMajorVersion = databaseMetaData.getDatabaseMajorVersion();
        databaseMinorVersion = databaseMetaData.getDatabaseMinorVersion();
        driverMajorVersion = databaseMetaData.getDriverMajorVersion();
        driverMinorVersion = databaseMetaData.getDriverMinorVersion();

        // ------------------------------------------------------------ catalogs
        {
            final ResultSet resultSet = databaseMetaData.getCatalogs();
            try {
                while (resultSet.next()) {
                    
                }
            } finally {
                resultSet.close();
            }
        }

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


    public Map<Integer, Boolean> getDeletesAreDetected() {

        if (deletesAreDetected == null) {
            deletesAreDetected = new HashMap<Integer, Boolean>();
        }

        return deletesAreDetected;
    }


    public boolean getDeletesAreDetected(final int type) {

        return getDeletesAreDetected().get(type);
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


    @XmlElement
    private boolean allProceduresAreCallable;


    @XmlElement
    private boolean allTablesAreSelectable;


    @XmlElement
    private boolean autoCommitFailureClosesAllResultSets;


    @XmlElement
    private boolean dataDefinitionCausesTransactionCommit;


    @XmlElement
    private boolean dataDefinitionIgnoredInTransactions;


    @XmlElement
    @XmlJavaTypeAdapter(DeletesAreDeletectedValuesAdapter.class)
    private Map<Integer, Boolean> deletesAreDetected;


    @XmlElement
    private boolean doesMaxRowSizeIncludeBlobs;


    @XmlElement
    private boolean generatedKeyAlwaysReturned;


    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CatalogValuesAdapter.class)
    private Map<String, Catalog> catalogs;


    @XmlElement
    private String catalogSeparator;


    @XmlElement
    private String catalogTerm;


    @XmlElement
    @XmlJavaTypeAdapter(ClientInfoPropertyValuesAdapter.class)
    private Map<String, ClientInfoProperty> clientInfoProperties;

    // getColumnPrivileges

    // getColumns
    // getCrossReference
    @XmlElement
    private int databaseMajorVersion;


    @XmlElement
    private int databaseMinorVersion;


    @XmlElement
    private String databaseProductName;


    @XmlElement
    private String databaseProductVersion;


    @XmlElement
    private int defaultTransactionIsolation;


    @XmlElement
    private int driverMajorVersion;


    @XmlElement
    private int driverMinorVersion;


    @XmlElement
    private String driverName;


    @XmlElement
    private String driverVersion;


    // exported keys
    @XmlElement
    private String extraNameCharacters;

    // getFunctionColumns

    // getFunctions
    @XmlElement
    private String identifierQuoteString;

    // getImportedKeys

    @XmlElement
    private int jdbcMajorVersion;


    @XmlElement
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


    private Map<Integer, Integer> supportedConverts;


    @XmlElement
    private boolean supportsCoreSQLGrammar;


    @XmlElement
    private boolean supportsCorrelatedSubqueries;


    @XmlElement
    private boolean supportsDataDefinitionAndDataManipulationTransactions;


    @XmlElement
    private boolean supportsDataManipulationTransactionsOnly;


    @XmlElement
    private boolean supportsDifferentTableCorrelationNames;


    @XmlElement
    private boolean supportsExpressionsInOrderBy;


    @XmlElement
    private boolean supportsExtendedSQLGrammar;


    @XmlElement
    private boolean supportsFullOuterJoins;


    @XmlElement
    private boolean supportsGetGeneratedKeys;


    @XmlElement
    private boolean supportsGroupBy;


    @XmlElement
    private boolean supportsGroupByBeyondSelect;


    @XmlElement
    private boolean supportsGroupByUnrelated;


    @XmlElement
    private boolean supportsIntegrityEnhancementFacility;


    @XmlElement
    private boolean supportsLikeEscapeClause;


    @XmlElement
    private boolean supportsLimitedOuterJoins;


    @XmlElement
    private boolean supportsMinimumSQLGrammar;


    @XmlElement
    private boolean supportsMixedCaseIdentifiers;


    @XmlElement
    private boolean supportsMixedCaseQuotedIdentifiers;


    @XmlElement
    private boolean supportsMultipleOpenResults;


    @XmlElement
    private boolean supportsMultipleResultSets;


    @XmlElement
    private boolean supportsMultipleTransactions;


    @XmlElement
    private boolean supportsNamedParameters;


    @XmlElement
    private boolean supportsNonNullableColumns;


    @XmlElement
    private boolean supportsOpenCursorsAcrossCommit;


    @XmlElement
    private boolean supportsOpenCursorsAcrossRollback;


    @XmlElement
    private boolean supportsOpenStatementsAcrossCommit;


    @XmlElement
    private boolean supportsOpenStatementsAcrossRollback;


    @XmlElement
    private boolean supportsOrderByUnrelated;


    @XmlElement
    private boolean supportsOuterJoins;


    @XmlElement
    private boolean supportsPositionedDelete;


    @XmlElement
    private boolean supportsPositionedUpdate;


    private Map<Integer, Integer> supportsResultSetConcurrency;


    private Map<Integer, Boolean> supportsResultSetHoldability;


    private Map<Integer, Boolean> supportsResultSetType;


    @XmlElement
    private boolean supportsSavepoints;


    @XmlElement
    private boolean supportsSchemasInDataManipulation;


    @XmlElement
    private boolean supportsSchemasInIndexDefinitions;


    @XmlElement
    private boolean supportsSchemasInPrivilegeDefinitions;


    @XmlElement
    private boolean supportsSchemasInProcedureCalls;


    @XmlElement
    private boolean supportsSchemasInTableDefinitions;


    @XmlElement
    private boolean supportsSelectForUpdate;


    @XmlElement
    private boolean supportsStatementPooling;


    @XmlElement
    private boolean supportsStoredFunctionsUsingCallSyntax;


    @XmlElement
    private boolean supportsStoredProcedures;


    @XmlElement
    private boolean supportsSubqueriesInComparisons;


    @XmlElement
    private boolean supportsSubqueriesInExists;


    @XmlElement
    private boolean supportsSubqueriesInIns;


    @XmlElement
    private boolean supportsSubqueriesInQuantifieds;


    @XmlElement
    private boolean supportsTableCorrelationNames;


    @XmlElement
    @XmlJavaTypeAdapter(SupportsTransactionIsolationLevelValuesAdapter.class)
    private Map<Integer, SupportsTransactionIsolationLevel> supportsTransactionIsolationLevel;


    @XmlElement
    private boolean supportsTransactions;


    @XmlElement
    private boolean supportsUnion;


    @XmlElement
    private boolean supportsUnionAll;


    @XmlElement
    @XmlJavaTypeAdapter(UpdatesAreDetectedValuesAdapter.class)
    private Map<Integer, UpdatesAreDetected> updatesAreDetected;


    @XmlElement
    private boolean usesLocalFilePerTable;


    @XmlElement
    private boolean usesLocalFiles;


}
