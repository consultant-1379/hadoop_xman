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

import static org.junit.Assert.*;

import org.junit.*;

import ericsson.hadoop.hive.table.columns.*;

public class HiveTableTestDDL {

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

    private static final HiveTableClusterBy TEST_CLUSTER_BY = new HiveTableClusterBy(NUMBER_OF_TEST_BUCKETS);

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
    public void testGetDDL() {
        testTable.setColumns(multiColumnList);

        final String expected = "create table " + TEST_DB_NAME + "." + TEST_TABLE_NAME
                + " (Column1 bigint COMMENT 'Comment', Column2 smallint COMMENT 'Comment 2', Column3 int COMMENT 'Comment 3')"
                + new HiveComment(TEST_COMMENT).getDDL();
        assertEquals(expected, testTable.getDDL());
    }

    @Test(expected = HiveDDLException.class)
    public void testGetDDLNoColumns() {
        final HiveTable testTable = new HiveTable(TEST_DB_NAME, TEST_TABLE_NAME, TEST_COMMENT);
        testTable.getDDL();
    }

    @Test
    public void testGetDDLNoDbName() {
        testTable = new HiveTable(null, TEST_TABLE_NAME, TEST_COMMENT);
        testTable.setColumns(multiColumnList);

        final String expected = "create table " + TEST_TABLE_NAME
                + " (Column1 bigint COMMENT 'Comment', Column2 smallint COMMENT 'Comment 2', Column3 int COMMENT 'Comment 3')"
                + new HiveComment(TEST_COMMENT).getDDL();
        assertEquals(expected, testTable.getDDL());
    }

    @Test
    public void testGetDDLSingleColumn() {
        testTable.setColumns(singleColumnList);

        final String expected = "create table " + TEST_DB_NAME + "." + TEST_TABLE_NAME + " (Column1 bigint COMMENT 'Comment')"
                + new HiveComment(TEST_COMMENT).getDDL();
        assertEquals(expected, testTable.getDDL());
    }

    @Test
    public void testGetDDLNoTableComment() {
        testTable = new HiveTable(TEST_DB_NAME, TEST_TABLE_NAME, null);
        testTable.setColumns(multiColumnList);

        final String expected = "create table " + TEST_DB_NAME + "." + TEST_TABLE_NAME
                + " (Column1 bigint COMMENT 'Comment', Column2 smallint COMMENT 'Comment 2', Column3 int COMMENT 'Comment 3')";
        assertEquals(expected, testTable.getDDL());
    }

    @Test
    public void testGetDDLWithPatitions() {
        testTable.setColumns(multiColumnList);
        testTable.setPartitions(multiPartitionList);

        final String expected = "create table "
                + TEST_DB_NAME
                + "."
                + TEST_TABLE_NAME
                + " (Column1 bigint COMMENT 'Comment', Column2 smallint COMMENT 'Comment 2', Column3 int COMMENT 'Comment 3')"
                + new HiveComment(TEST_COMMENT).getDDL()
                + " PARTITIONED BY (Partition1 bigint COMMENT 'Partition Comment', Partition2 smallint COMMENT 'Partition Comment 2', Partition3 int COMMENT 'Partition Comment 3')";
        assertEquals(expected, testTable.getDDL());
    }

    @Test
    public void testGetDDLWithSinglePatition() {
        testTable.setColumns(multiColumnList);
        testTable.setPartitions(singlePartitionList);

        final String expected = "create table " + TEST_DB_NAME + "." + TEST_TABLE_NAME
                + " (Column1 bigint COMMENT 'Comment', Column2 smallint COMMENT 'Comment 2', Column3 int COMMENT 'Comment 3')"
                + new HiveComment(TEST_COMMENT).getDDL() + " PARTITIONED BY (Partition1 bigint COMMENT 'Partition Comment')";
        assertEquals(expected, testTable.getDDL());
    }

    @Test
    public void testGetDDLWithClusterBy() {
        final HiveTableClusterBy clusterBy = new HiveTableClusterBy(NUMBER_OF_TEST_BUCKETS);
        clusterBy.setClusterCols(multipleClusterColList);

        testTable.setColumns(multiColumnList);
        testTable.setClusterBy(clusterBy);

        final String expected = "create table " + TEST_DB_NAME + "." + TEST_TABLE_NAME
                + " (Column1 bigint COMMENT 'Comment', Column2 smallint COMMENT 'Comment 2', Column3 int COMMENT 'Comment 3')"
                + new HiveComment(TEST_COMMENT).getDDL() + " CLUSTERED BY (Column1, Column2, Column3) INTO " + NUMBER_OF_TEST_BUCKETS + " BUCKETS";
        assertEquals(expected, testTable.getDDL());
    }

    @Test
    public void testGetDDLWithClusterByWithSort() {
        testTable.setColumns(multiColumnList);
        testTable.setClusterBy(TEST_CLUSTER_BY);

        final String expected = "create table " + TEST_DB_NAME + "." + TEST_TABLE_NAME
                + " (Column1 bigint COMMENT 'Comment', Column2 smallint COMMENT 'Comment 2', Column3 int COMMENT 'Comment 3')"
                + new HiveComment(TEST_COMMENT).getDDL()
                + " CLUSTERED BY (Column1, Column2, Column3) SORTED BY (Column1 ASC, Column2 DESC, Column3 ASC) INTO " + NUMBER_OF_TEST_BUCKETS
                + " BUCKETS";
        assertEquals(expected, testTable.getDDL());
    }

    @Test
    public void testGetDDLPartitionAndClusterBy() {
        testTable.setColumns(multiColumnList);
        testTable.setPartitions(multiPartitionList);
        testTable.setClusterBy(TEST_CLUSTER_BY);

        final String expected = "create table "
                + TEST_DB_NAME
                + "."
                + TEST_TABLE_NAME
                + " (Column1 bigint COMMENT 'Comment', Column2 smallint COMMENT 'Comment 2', Column3 int COMMENT 'Comment 3')"
                + new HiveComment(TEST_COMMENT).getDDL()
                + " PARTITIONED BY (Partition1 bigint COMMENT 'Partition Comment', Partition2 smallint COMMENT 'Partition Comment 2', Partition3 int COMMENT 'Partition Comment 3')"
                + " CLUSTERED BY (Column1, Column2, Column3) SORTED BY (Column1 ASC, Column2 DESC, Column3 ASC) INTO " + NUMBER_OF_TEST_BUCKETS
                + " BUCKETS";
        assertEquals(expected, testTable.getDDL());
    }
}
