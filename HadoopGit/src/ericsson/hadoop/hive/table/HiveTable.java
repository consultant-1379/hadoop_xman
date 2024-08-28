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
package ericsson.hadoop.hive.table;

import ericsson.hadoop.hive.table.columns.*;

public class HiveTable {

    private HiveName dbName;
    private HiveName tableName;
    private HiveComment comment;
    private HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
    private HiveColumnList<HiveTypedColumn> partitions = new HiveColumnList<HiveTypedColumn>();
    private HiveTableClusterBy clusterBy = new HiveTableClusterBy();

    /**
     * @param tableName
     *            Cannot be null or empty. If an invalid name is supplied, IllegalArgumentException will be thrown.
     */
    public HiveTable(final String tableName) {
        this(null, tableName, null);
    }

    /**
     * @param dbName
     *            Can be null or empty.
     * @param tableName
     *            Cannot be null or empty. If an invalid name is supplied, IllegalArgumentException will be thrown.
     */
    public HiveTable(final String dbName, final String tableName) {
        this(dbName, tableName, null);
    }

    /**
     * @param dbName
     *            Can be null or empty.
     * @param tableName
     *            Cannot be null or empty. If an invalid name is supplied, IllegalArgumentException will be thrown.
     * @param comment
     */
    public HiveTable(final String dbName, final String tableName, final String comment) {
        super();
        setDbName(dbName);
        setTableName(tableName);
        setComment(comment);
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        if (dbName == null) {
            return null;
        } else {
            return dbName.getName();
        }
    }

    /**
     * @param dbName
     *            the dbName to set. Can be null. Setting dbName to "" (empty string) results in dbName being set to null.
     * @throws HiveNameException
     *             if dbName is invalid
     */
    public void setDbName(final String dbName) {
        if (dbName == null || dbName.isEmpty()) {
            this.dbName = null;
        } else {
            this.dbName = new HiveName(dbName);
        }
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName.getName();
    }

    /**
     * @param tableName
     *            the tableName to set. Cannot be null
     * @throws HiveNameException
     *             if tableName is invalid
     */
    public void setTableName(final String tableName) {
        this.tableName = new HiveName(tableName);
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment.getComment();
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(final String comment) {
        this.comment = new HiveComment(comment);
    }

    /**
     * set the columns
     */
    public void setColumns(final HiveColumnList<HiveTypedColumn> columns) {
        if (columns == null) {
            throw new IllegalArgumentException("Trying to set columns to null.");
        } else {
            setNonNullColumns(columns);
        }
    }

    /**
     * @param columns
     */
    private void setNonNullColumns(final HiveColumnList<HiveTypedColumn> columns) {
        if (partitions.columnsExist(columns)) {
            throw new IllegalArgumentException("Trying to set columns with list that includes existing partion(s).");
        } else {
            this.columns = columns;
        }
    }

    /**
     * @return the columns
     */
    public HiveColumnList<HiveTypedColumn> getColumns() {
        return columns;
    }

    /**
     * set the partitions
     */
    public void setPartitions(final HiveColumnList<HiveTypedColumn> partitions) {
        if (partitions == null) {
            throw new IllegalArgumentException("Trying to set partitions to null.");
        } else {
            setNonNullPartitions(partitions);
        }
    }

    /**
     * @param partitions2
     */
    private void setNonNullPartitions(final HiveColumnList<HiveTypedColumn> partitions) {
        if (columns.columnsExist(partitions)) {
            throw new IllegalArgumentException("Trying to set partion with list that includes existing column(s).");
        } else {
            this.partitions = partitions;
        }
    }

    /**
     * @return the partitions
     */
    public HiveColumnList<HiveTypedColumn> getPartitions() {
        return partitions;
    }

    /**
     * Builds "create table" DDL for table.
     * 
     * @return DDL for table
     */
    public String getDDL() {
        if (columns.size() == 0) {
            throw new HiveDDLException("Can't create hive table DDL if no columns have been defined.");
        } else {
            final String ddl = "create table " + ddlDbName() + getTableName() + columns.getDDL() + comment.getDDL() + getPartionDDL()
                    + clusterBy.getDDL();
            return ddl;
        }
    }

    /**
     * @return ddl for dbName
     */
    private String ddlDbName() {
        if (dbName != null) {
            return getDbName() + ".";
        } else {
            return "";
        }
    }

    /**
     * Creates DDL for column list as used in create table statement partitioned by section. If columns in list are not HiveTypedColumns a
     * HiveDDLException will be thrown
     * 
     * @return DDL for column list including enclosing brackets prefixed with "PARTITIONED BY", or empty string if column list is empty
     */
    public String getPartionDDL() {
        if (partitions.size() > 0) {
            return " PARTITIONED BY" + partitions.getDDL();
        } else {
            return "";
        }
    }

    /**
     * @return the clusterBy
     */
    public HiveTableClusterBy getClusterBy() {
        return clusterBy;
    }

    /**
     * @param clusterBy
     *            the clusterBy to set
     */
    public void setClusterBy(final HiveTableClusterBy clusterBy) {
        if (clusterBy == null) {
            throw new IllegalArgumentException("Trying to set clusterBy to null.");
        } else {
            setNonNullClusterBy(clusterBy);
        }
    }

    /**
     * Set the clusterBy clause. If any columns in clusterBy are not defined as table columns or partitions, an IllegalArgumentException exception
     * will be thrown.
     * 
     * @param clusterBy
     */
    private void setNonNullClusterBy(final HiveTableClusterBy clusterBy) {
        if (hasUnmatchedCols(clusterBy.getClusterCols()) || hasUnmatchedCols(clusterBy.getSortCols())) {
            throw new IllegalArgumentException("Trying to set clusterBy for undefined columns.");
        } else {
            this.clusterBy = clusterBy;
        }
    }

    /**
     * @param clusterBy
     * @return
     */
    private boolean hasUnmatchedCols(final HiveColumnList<? extends HiveColumn> columnList) {
        for (final HiveColumn column : columnList.getColumnList()) {
            if (!columns.columnExists(column.getName())) {
                return true;
            }
        }

        return false;
    }
}
