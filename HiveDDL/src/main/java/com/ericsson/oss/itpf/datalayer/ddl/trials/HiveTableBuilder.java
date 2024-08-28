package com.ericsson.oss.itpf.datalayer.ddl.trials;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.Logger;

import com.ericsson.oss.itpf.datalayer.ddl.HiveDDLException;
import com.ericsson.oss.itpf.datalayer.ddl.columns.*;
import com.ericsson.oss.itpf.datalayer.ddl.table.*;

/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

public class HiveTableBuilder {

    /**
     * 
     */
    private static final int SORT_ORDER = 1;
    /**
     * 
     */
    private static final int SORT_COL_NAME = 0;
    private final int COL_NAME = 0;
    private final int COL_TYPE = 1;
    private final int COL_COMMENT = 2;

    private final Map<Object, Object> orderedProperties = new TreeMap<Object, Object>();
    private String dbName = null;
    private String tableName = null;
    private String fullTableName = null;
    private String tableComment = null;
    private final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
    private final HiveColumnList<HiveTypedColumn> partitions = new HiveColumnList<HiveTypedColumn>();
    private final HiveClusterBy clusterBy = new HiveClusterBy();
    private HiveTable table = null;

    private static Logger logger = Logger.getLogger(HiveJdbcClient.class);

    /**
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) {

        if (args.length > 0) {
            final String filename = args[0];

            try {
                final Properties tableProperties = new Properties();
                tableProperties.load(new FileReader(filename));

                final HiveTableBuilder tableBuilder = new HiveTableBuilder(tableProperties);
                tableBuilder.createTableInHive();
            } catch (final Exception e) {
                logger.error("Problem creating table in Hive.", e);
            }
        } else {
            System.out.println("Usage:\njava HiveCreateTable <table-definition-file>");
        }
    }

    /**
     * Build a table from the specified properties
     * 
     * @param properties
     *            Java properties with details of table to be built
     */
    public HiveTableBuilder(final Properties properties) {
        orderedProperties.putAll(properties);
        buildTable();
    }

    /**
     * Creates the defined table in Hive. If the table already exists or there is a problem executing the DDL, a HiveDDLException will be thrown
     */
    public void createTableInHive() {
        try {
            runCreateTable();
        } catch (final ClassNotFoundException e) {
            logger.error("Problem connecting to hive.", e);
            throw new HiveDDLException("Problem connecting to hive.", e);
        } catch (final SQLException e) {
            logger.error("Unable to create table in hive: " + fullTableName, e);
            throw new HiveDDLException("Unable to create table in hive: " + fullTableName, e);
        }
    }

    /**
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void runCreateTable() throws ClassNotFoundException, SQLException {
        final HiveJdbcClient hive = new HiveJdbcClient();
        if (hive.tableExists(fullTableName)) {
            logger.warn("Attempt to create table that already exists in Hive: " + fullTableName);
            throw new HiveDDLException("Attempt to create table that already exists in Hive: " + fullTableName);
        }
        hive.runStatement(table.getDDL());
        logger.info("Created table in Hive: " + fullTableName);
    }

    /**
     * Get property for value key.
     * 
     * @param key
     * @return Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     */
    private String getProperty(final String key) {
        return (String) orderedProperties.get(key);
    }

    /**
     * @return key set of orderedProperties
     */
    private Set<Object> getPropertyKeySet() {
        return orderedProperties.keySet();
    }

    private void buildTable() {
        buildBasicTable();
        buildColumnList();
        buildPartitionList();
        buildClusterBy();
    }

    private void buildBasicTable() {
        setBasicTablesDetails();
        setDelimiter();
        setFileFormat();
    }

    /**
     * set the basic table details from the properties
     */
    private void setBasicTablesDetails() {
        dbName = getProperty("dbname");
        tableName = getProperty("name");
        fullTableName = getFullTableName();
        tableComment = getProperty("comment");
        table = new HiveTable(dbName, tableName, tableComment);
    }

    /**
     * Set the field delimiter used in the table's the data files
     */
    private void setDelimiter() {
        final String delimiter = getProperty("file.delimiter");
        if (delimiter != null && !delimiter.isEmpty()) {
            table.setDelimiter(delimiter);
        }
    }

    /**
     * Set the file format used to store the table data
     */
    private void setFileFormat() {
        final String fileFormat = getProperty("file.format");
        if (fileFormat != null && !fileFormat.isEmpty()) {
            table.setFileFormat(HiveFileFormat.valueOf(fileFormat));
        }
    }

    private void buildColumnList() {
        for (final Object key : getPropertyKeySet()) {
            if (key.toString().startsWith("column")) {
                columns.add(createColFromProperty(key));
            }
        }
        table.setColumns(columns);
    }

    private void buildPartitionList() {
        for (final Object key : getPropertyKeySet()) {
            if (key.toString().startsWith("partition")) {
                partitions.add(createColFromProperty(key));
            }
        }
        table.setPartitions(partitions);
    }

    private void buildClusterBy() {
        if (isClusteringDefined()) {
            setBucketsFromProperties();
            buildClusterColsFromProperties();
            buildSortColsFromProperties();
            table.setClusterBy(clusterBy);
        }
    }

    /**
     * @return true if clustering has been defined in the properties, otherwise false
     */
    private boolean isClusteringDefined() {
        return getProperty("clusterBy") != null && !getProperty("clusterBy").isEmpty();
    }

    private void buildSortColsFromProperties() {
        final String sortByCols = getProperty("sortBy");
        if (sortByCols != null) {
            buildSortCols(sortByCols);
        }
    }

    /**
     * @param sortByCols
     */
    private void buildSortCols(final String sortByCols) {
        final HiveColumnList<HiveSortColumn> sortCols = new HiveColumnList<HiveSortColumn>();
        for (final String col : sortByCols.split(",")) {
            addSortCol(sortCols, col);
        }
        clusterBy.setSortCols(sortCols);
    }

    /**
     * Sets number of buckets for clustering.
     * 
     * @param buckets
     *            String with number of buckets. If buckets is null, method does nothing.
     */
    private void setBucketsFromProperties() {
        final String buckets = getProperty("buckets");
        if (buckets != null) {
            clusterBy.setNumberOfBuckets(Integer.parseInt(buckets));
        }
    }

    private void addSortCol(final HiveColumnList<HiveSortColumn> sortCols, final String col) {
        final String[] sortColData = col.trim().split(" ");
        if (hasDefaultSortOrder(sortColData)) {
            sortCols.add(new HiveSortColumn(sortColData[SORT_COL_NAME].trim()));
        } else {
            sortCols.add(new HiveSortColumn(sortColData[SORT_COL_NAME].trim(), HiveSortOrder.valueOf(sortColData[SORT_ORDER].trim())));
        }
    }

    private boolean hasDefaultSortOrder(final String[] sortColData) {
        return sortColData.length == 1;
    }

    private void buildClusterColsFromProperties() {
        final String clusterByCols = getProperty("clusterBy");
        if (clusterByCols != null) {
            buildClusterCols(clusterByCols);
        }
    }

    /**
     * @param clusterByCols
     */
    private void buildClusterCols(final String clusterByCols) {
        final HiveColumnList<HiveColumn> clusterCols = new HiveColumnList<HiveColumn>();
        for (final String col : clusterByCols.split(",")) {
            clusterCols.add(new HiveColumn(col.trim()));
        }
        clusterBy.setClusterCols(clusterCols);
    }

    /**
     * Builds the full table name combining the database name (if provided) with the table name
     * 
     * @return the full table name in the format dbname.tablename
     */
    private String getFullTableName() {
        if (isDbNameDefined()) {
            return dbName + "." + tableName;
        } else {
            return tableName;
        }
    }

    /**
     * Check if database name has been defined.
     * 
     * @return true if dbName is not null and not empty, otherwise false
     */
    private boolean isDbNameDefined() {
        return dbName != null && !dbName.isEmpty();
    }

    /**
     * Creates column from specified property
     * 
     * @param key
     *            key of property with details of column definition as comma separated string: <column name>,<column type>,<column comment>
     * @return the column
     */
    private HiveTypedColumn createColFromProperty(final Object key) {
        final String[] colDefn = getProperty((String) key).split(",");
        if (colDefn.length > 2) {
            return new HiveTypedColumn(colDefn[COL_NAME], HiveDataType.valueOf(colDefn[COL_TYPE]), colDefn[COL_COMMENT]);
        } else {
            return new HiveTypedColumn(colDefn[COL_NAME], HiveDataType.valueOf(colDefn[COL_TYPE]));
        }
    }
}
