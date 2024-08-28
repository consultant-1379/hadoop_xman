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
package ericsson.hadoop.hive.test;

import ericsson.hadoop.hive.table.HiveTable;
import ericsson.hadoop.hive.table.HiveTableClusterBy;
import ericsson.hadoop.hive.table.columns.*;

public class HiveTableDDLTests {

    static final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
    static final HiveColumnList<HiveTypedColumn> columns2 = new HiveColumnList<HiveTypedColumn>();
    static final HiveColumnList<HiveTypedColumn> partitions = new HiveColumnList<HiveTypedColumn>();

    public static void main(final String[] args) {

        columns.add(new HiveTypedColumn("id", HiveDataType.INT, null));
        columns.add(new HiveTypedColumn("first_name", HiveDataType.STRING, null));
        columns.add(new HiveTypedColumn("last_name", HiveDataType.STRING, null));
        columns.add(new HiveTypedColumn("address", HiveDataType.STRING, null));

        final HiveTable table1 = new HiveTable(null, "jb_test_ddl_1", "Test generation for create table ddl.");
        table1.setColumns(columns);

        System.out.println(table1.getDDL());

        columns2.add(new HiveTypedColumn("id", HiveDataType.INT, null));
        columns2.add(new HiveTypedColumn("col2", HiveDataType.BIGINT, "some value"));
        columns2.add(new HiveTypedColumn("col3", HiveDataType.TINYINT, "another value"));
        columns2.add(new HiveTypedColumn("col4", HiveDataType.STRING, "a string value"));

        partitions.add(new HiveTypedColumn("datetime", HiveDataType.TIMESTAMP, "what's the time?"));

        final HiveTable table2 = new HiveTable("jb_test_ddl_2");
        table2.setColumns(columns2);
        table2.setPartitions(partitions);

        System.out.println(table2.getDDL());

        testClusterBy();
    }

    public static void testClusterBy() {
        final HiveColumn TEST_CLUSTER1 = new HiveColumn("id");
        final HiveColumn TEST_CLUSTER2 = new HiveColumn("col2");
        final HiveColumnList<HiveColumn> singleClusterColList = new HiveColumnList<HiveColumn>();
        final HiveColumnList<HiveColumn> multipleClusterColList = new HiveColumnList<HiveColumn>();

        final HiveSortColumn TEST_SORT1 = new HiveSortColumn("col3");
        final HiveSortColumn TEST_SORT2 = new HiveSortColumn("col4", HiveSortOrder.DESC);
        final HiveColumnList<HiveSortColumn> singleSortColList = new HiveColumnList<HiveSortColumn>();
        final HiveColumnList<HiveSortColumn> multipleSortColList = new HiveColumnList<HiveSortColumn>();

        singleClusterColList.add(TEST_CLUSTER1);

        multipleClusterColList.add(TEST_CLUSTER1);
        multipleClusterColList.add(TEST_CLUSTER2);

        singleSortColList.add(TEST_SORT1);

        multipleSortColList.add(TEST_SORT1);
        multipleSortColList.add(TEST_SORT2);

        final HiveTableClusterBy clusterBy = new HiveTableClusterBy();
        clusterBy.setClusterCols(multipleClusterColList);
        clusterBy.setSortCols(multipleSortColList);

        final HiveTable clusteredTable = new HiveTable("jb_test_cluster_ddl");
        clusteredTable.setColumns(columns2);
        clusteredTable.setPartitions(partitions);
        clusteredTable.setClusterBy(clusterBy);

        System.out.println(clusteredTable.getDDL());
    }
}
