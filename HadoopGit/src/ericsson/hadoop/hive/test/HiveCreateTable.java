package ericsson.hadoop.hive.test;

import java.io.FileReader;
import java.util.*;

import ericsson.hadoop.hive.table.HiveTable;
import ericsson.hadoop.hive.table.HiveTableClusterBy;
import ericsson.hadoop.hive.table.columns.*;

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

public class HiveCreateTable {

    /**
     * 
     */
    private static final int COL_COMMENT = 2;
    /**
     * 
     */
    private static final int COL_TYPE = 1;
    /**
     * 
     */
    private static final int COL_NAME = 0;
    private static Properties properties = null;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {

        if (args.length > 0) {

            final String filename = args[0];

            properties = new Properties();
            properties.load(new FileReader(filename));

            final Map orderedProperties = new TreeMap();
            orderedProperties.putAll(properties);

            final String dbName = properties.getProperty("dbname");
            final String tableName = properties.getProperty("name");
            final String tableComment = properties.getProperty("comment");

            String fullTableName = tableName;
            if (dbName != null && !dbName.isEmpty()) {
                fullTableName = dbName + "." + fullTableName;
            }

            if (HiveJdbcClient.tableExists(fullTableName)) {
                System.out.println("Table " + fullTableName + " already exists in Hive.\n\nAborting table creation.");
                System.exit(1);
            }

            final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();

            for (final Object key : orderedProperties.keySet()) {
                if (key.toString().startsWith("column")) {
                    final String[] colData = orderedProperties.get(key).toString().split(",");

                    final HiveTypedColumn col;
                    if (colData.length > 2) {
                        col = new HiveTypedColumn(colData[COL_NAME], HiveDataType.valueOf(colData[COL_TYPE]), colData[COL_COMMENT]);
                    } else {
                        col = new HiveTypedColumn(colData[COL_NAME], HiveDataType.valueOf(colData[COL_TYPE]));
                    }
                    columns.add(col);
                }
            }

            final HiveColumnList<HiveTypedColumn> partitions = new HiveColumnList<HiveTypedColumn>();
            for (final Object key : orderedProperties.keySet()) {
                if (key.toString().startsWith("partition")) {
                    final String[] partitionData = orderedProperties.get(key).toString().split(",");

                    final HiveTypedColumn col = new HiveTypedColumn(partitionData[COL_NAME], HiveDataType.valueOf(partitionData[COL_TYPE]),
                            partitionData[COL_COMMENT]);
                    partitions.add(col);
                }
            }

            final String buckets = properties.getProperty("buckets");
            final String clusterByCols = properties.getProperty("clusterBy");
            final String sortByCols = properties.getProperty("sortBy");

            final HiveTableClusterBy clusterBy = new HiveTableClusterBy();
            if (buckets != null) {
                final int numBuckets = Integer.parseInt(buckets);
                clusterBy.setNumberOfBuckets(numBuckets);
            }

            final HiveColumnList<HiveColumn> clusterCols = new HiveColumnList<HiveColumn>();
            if (clusterByCols != null) {
                for (final String col : clusterByCols.split(",")) {
                    clusterCols.add(new HiveColumn(col.trim()));
                }
                clusterBy.setClusterCols(clusterCols);
            }

            final HiveColumnList<HiveSortColumn> sortCols = new HiveColumnList<HiveSortColumn>();
            if (sortByCols != null) {
                for (final String col : sortByCols.split(",")) {
                    final String[] sortColData = col.trim().split(" ");
                    if (sortColData.length == 1) {
                        sortCols.add(new HiveSortColumn(sortColData[0].trim()));
                    } else {
                        final String sortOrder = sortColData[1].trim();
                        sortCols.add(new HiveSortColumn(sortColData[0].trim(), HiveSortOrder.valueOf(sortOrder)));
                    }
                }
                clusterBy.setSortCols(sortCols);
            }

            final HiveTable table = new HiveTable(dbName, tableName, tableComment);
            table.setColumns(columns);
            table.setPartitions(partitions);
            table.setClusterBy(clusterBy);

            final String createTableSql = table.getDDL();
            HiveJdbcClient.runStatement(createTableSql);
        } else {
            System.out.println("Usage:\njava HiveCreateTable <table-definition-file>");
        }

    }
}
