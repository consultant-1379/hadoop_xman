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
package com.ericsson.oss.itpf.datalayer.ddl.table;

import static org.junit.Assert.*;

import org.junit.*;

import com.ericsson.oss.itpf.datalayer.ddl.columns.*;
import com.ericsson.oss.itpf.datalayer.ddl.table.HiveClusterBy;
import com.ericsson.oss.itpf.datalayer.ddl.table.HiveTable;


public class HiveTableColumnTest {

    private static final String TEST_DB_NAME = "testDB";
    private static final String TEST_TABLE_NAME = "testTable";
    private static final String TEST_COMMENT = "this is a test comment";
    private static final int NUMBER_OF_TEST_BUCKETS = 3;

    private static final HiveTypedColumn TEST_COLUMN = new HiveTypedColumn("Column1", HiveDataType.BIGINT, "Comment");
    private static final HiveTypedColumn TEST_COLUMN2 = new HiveTypedColumn("Column2", HiveDataType.SMALLINT, "Comment 2");
    private static final HiveTypedColumn TEST_COLUMN3 = new HiveTypedColumn("Column3", HiveDataType.INT, "Comment 3");
    private static HiveColumnList<HiveTypedColumn> singleColumnList = new HiveColumnList<HiveTypedColumn>();
    private static HiveColumnList<HiveTypedColumn> multiColumnList = new HiveColumnList<HiveTypedColumn>();

    private static final HiveTypedColumn TEST_PARTITION = new HiveTypedColumn("Partition1", HiveDataType.BIGINT, "Partition Comment");
    private static final HiveTypedColumn TEST_PARTITION2 = new HiveTypedColumn("Partition2", HiveDataType.SMALLINT, "Partition Comment 2");
    private static final HiveTypedColumn TEST_PARTITION3 = new HiveTypedColumn("Partition3", HiveDataType.INT, "Partition Comment 3");
    private static HiveColumnList<HiveTypedColumn> singlePartitionList = new HiveColumnList<HiveTypedColumn>();
    private static HiveColumnList<HiveTypedColumn> multiPartitionList = new HiveColumnList<HiveTypedColumn>();

    private static final HiveColumnList<HiveColumn> multipleClusterColList = new HiveColumnList<HiveColumn>();

    private static final HiveSortColumn TEST_SORT1 = new HiveSortColumn(TEST_COLUMN.getName());
    private static final HiveSortColumn TEST_SORT2 = new HiveSortColumn(TEST_COLUMN2.getName(), HiveSortOrder.DESC);
    private static final HiveSortColumn TEST_SORT3 = new HiveSortColumn(TEST_COLUMN3.getName(), HiveSortOrder.ASC);
    private static final HiveColumnList<HiveSortColumn> multipleSortColList = new HiveColumnList<HiveSortColumn>();

    private static final HiveClusterBy TEST_CLUSTER_BY = new HiveClusterBy(NUMBER_OF_TEST_BUCKETS);

    private static HiveTable testTable = null;

    @BeforeClass
    public static void setUpClass() {
        singleColumnList.add(TEST_COLUMN);

        multiColumnList.add(TEST_COLUMN);
        multiColumnList.add(TEST_COLUMN2);
        multiColumnList.add(TEST_COLUMN3);

        singlePartitionList.add(TEST_PARTITION);

        multiPartitionList.add(TEST_PARTITION);
        multiPartitionList.add(TEST_PARTITION2);
        multiPartitionList.add(TEST_PARTITION3);

        multipleClusterColList.add(TEST_COLUMN);
        multipleClusterColList.add(TEST_COLUMN2);
        multipleClusterColList.add(TEST_COLUMN3);

        multipleSortColList.add(TEST_SORT1);
        multipleSortColList.add(TEST_SORT2);
        multipleSortColList.add(TEST_SORT3);

        TEST_CLUSTER_BY.setClusterCols(multipleClusterColList);
        TEST_CLUSTER_BY.setSortCols(multipleSortColList);
    }

    @Before
    public void setUp() {
        testTable = new HiveTable(TEST_DB_NAME, TEST_TABLE_NAME, TEST_COMMENT);
    }

    @Test
    public void testGetColumnsNoneAdded() {
        assertEquals(0, testTable.getColumns().size());
    }

    @Test
    public void testSetGetColumns() {
        assertEquals("Columns should be empty to start with", 0, testTable.getColumns().size());

        testTable.setColumns(multiColumnList);
        assertEquals(multiColumnList, testTable.getColumns());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullColumns() {
        testTable.setColumns(null);
    }

    @Test
    public void testGetPartitionsNoneAdded() {
        assertEquals(0, testTable.getPartitions().size());
    }

    @Test
    public void testSetGetPartitions() {
        assertEquals("Partitions should be empty to start with", 0, testTable.getPartitions().size());

        testTable.setPartitions(multiPartitionList);
        assertEquals(multiPartitionList, testTable.getPartitions());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullPartitions() {
        testTable.setPartitions(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetColumnsWithSameNameAsPartition() {
        testTable.setPartitions(multiPartitionList);
        testTable.setColumns(singlePartitionList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPartionsWithSameNameAsColumns() {
        testTable.setColumns(multiColumnList);
        testTable.setPartitions(singleColumnList);
    }

    @Test
    public void testSetGetClusterBy() {
        testTable.setColumns(multiColumnList);
        testTable.setClusterBy(TEST_CLUSTER_BY);
        assertEquals(TEST_CLUSTER_BY, testTable.getClusterBy());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullClusterBy() {
        testTable.setClusterBy(null);
    }

    // try setting cluster by when no cluster cols have been defined
    @Test(expected = IllegalArgumentException.class)
    public void testSetClusterByNoClusterCols() {
        final HiveClusterBy clusterBy = new HiveClusterBy();
        testTable.setClusterBy(clusterBy);
    }

    // try setting cluster by when no columns or partitions have been defined
    @Test(expected = IllegalArgumentException.class)
    public void testSetClusterByNoTableColsDefined() {
        testTable.setClusterBy(TEST_CLUSTER_BY);
    }

    // try setting cluster by columns that aren't defined as columns
    @Test(expected = IllegalArgumentException.class)
    public void testSetClusterByUnmatchedClusterCol() {
        testTable.setColumns(multiColumnList);

        final HiveColumnList<HiveColumn> badClusterCols = new HiveColumnList<HiveColumn>();
        badClusterCols.add(new HiveColumn("UnmatchedColumn"));
        final HiveClusterBy badClusterBy = new HiveClusterBy();
        badClusterBy.setClusterCols(badClusterCols);

        testTable.setClusterBy(badClusterBy);
    }

    // try setting cluster by sort columns that aren't defined as columns
    @Test(expected = IllegalArgumentException.class)
    public void testSetClusterByUnmatchedSortCol() {
        testTable.setColumns(multiColumnList);

        final HiveColumnList<HiveSortColumn> badSortCols = new HiveColumnList<HiveSortColumn>();
        badSortCols.add(new HiveSortColumn("UnmatchedColumn"));
        final HiveClusterBy badClusterBy = new HiveClusterBy();
        badClusterBy.setClusterCols(multipleClusterColList);
        badClusterBy.setSortCols(badSortCols);

        testTable.setClusterBy(badClusterBy);
    }

    // try setting cluster by columns to a partition rather than a column
    @Test(expected = IllegalArgumentException.class)
    public void testSetClusterByClusterColIsPartition() {
        testTable.setColumns(multiColumnList);
        testTable.setPartitions(multiPartitionList);

        final HiveColumnList<HiveColumn> badClusterCols = new HiveColumnList<HiveColumn>();
        badClusterCols.add(new HiveColumn(TEST_PARTITION.getName()));
        final HiveClusterBy badClusterBy = new HiveClusterBy();
        badClusterBy.setClusterCols(badClusterCols);

        testTable.setClusterBy(badClusterBy);
    }

    // try setting cluster by sort column to a partition rather than a column
    @Test(expected = IllegalArgumentException.class)
    public void testSetClusterBySortColIsPartition() {
        testTable.setColumns(multiColumnList);
        testTable.setPartitions(multiPartitionList);

        final HiveColumnList<HiveSortColumn> badSortCols = new HiveColumnList<HiveSortColumn>();
        badSortCols.add(new HiveSortColumn(TEST_PARTITION.getName()));
        final HiveClusterBy badClusterBy = new HiveClusterBy();
        badClusterBy.setClusterCols(multipleClusterColList);
        badClusterBy.setSortCols(badSortCols);

        testTable.setClusterBy(badClusterBy);
    }

    // try setting cluster by when sort is defined but cluster cols aren't
    @Test(expected = IllegalArgumentException.class)
    public void testSetClusterBySortNoClusterCols() {
        final HiveClusterBy clusterBy = new HiveClusterBy();
        clusterBy.setSortCols(multipleSortColList);
        testTable.setClusterBy(clusterBy);
    }
}
