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

public class HiveTableTestBasic {

    private static final String VALID_NAME_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
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

    private static final HiveColumnList<HiveColumn> singleClusterColList = new HiveColumnList<HiveColumn>();
    private static final HiveColumnList<HiveColumn> multipleClusterColList = new HiveColumnList<HiveColumn>();

    private static final HiveSortColumn TEST_SORT1 = new HiveSortColumn(TEST_COLUMN.getName());
    private static final HiveSortColumn TEST_SORT2 = new HiveSortColumn(TEST_COLUMN2.getName(), HiveSortOrder.DESC);
    private static final HiveSortColumn TEST_SORT3 = new HiveSortColumn(TEST_COLUMN3.getName(), HiveSortOrder.ASC);
    private static final HiveColumnList<HiveSortColumn> singleSortColList = new HiveColumnList<HiveSortColumn>();
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

        singleClusterColList.add(TEST_COLUMN);

        multipleClusterColList.add(TEST_COLUMN);
        multipleClusterColList.add(TEST_COLUMN2);
        multipleClusterColList.add(TEST_COLUMN3);

        singleSortColList.add(TEST_SORT1);

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
    public void testConstructor() {
        assertEquals(TEST_DB_NAME, testTable.getDbName());
        assertEquals(TEST_TABLE_NAME, testTable.getTableName());
        assertEquals(TEST_COMMENT, testTable.getComment());
    }

    @Test
    public void testNoCommentConstructor() {
        testTable = new HiveTable(TEST_DB_NAME, TEST_TABLE_NAME);

        assertEquals(TEST_DB_NAME, testTable.getDbName());
        assertEquals(TEST_TABLE_NAME, testTable.getTableName());
        assertNull(testTable.getComment());
    }

    @Test
    public void testTableNameConstructor() {
        testTable = new HiveTable(TEST_TABLE_NAME);

        assertNull(testTable.getDbName());
        assertEquals(TEST_TABLE_NAME, testTable.getTableName());
        assertNull(testTable.getComment());
    }

    @Test
    public void testConstructorValidDbNameCharacters() {
        try {
            testTable = new HiveTable(VALID_NAME_CHARS, TEST_TABLE_NAME, TEST_COMMENT);
            assertNotNull(testTable);
        } catch (final IllegalArgumentException e) {
            fail("Valid name characters caused BadColumnNameException");
        }
    }

    @Test
    public void testConstructorNullDbNameIsValid() {
        testTable = new HiveTable(null, TEST_TABLE_NAME, TEST_COMMENT);
        assertNotNull(testTable);
    }

    @Test
    public void testConstructorEmptyDbNameIsValid() {
        testTable = new HiveTable("", TEST_TABLE_NAME, TEST_COMMENT);
        assertNotNull(testTable);
    }

    @Test
    public void testConstructorValidTableNameCharacters() {
        try {
            testTable = new HiveTable(TEST_DB_NAME, VALID_NAME_CHARS, TEST_COMMENT);
            assertNotNull(testTable);
        } catch (final IllegalArgumentException e) {
            fail("Valid name characters caused BadColumnNameException");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullTableNameInvalid() {
        new HiveTable(TEST_DB_NAME, null, TEST_COMMENT);
        fail("Shouldn't be able to create HiveTable with null name.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmptyTableNameInvalid() {
        new HiveTable(TEST_DB_NAME, "", TEST_COMMENT);
        fail("Shouldn't be able to create HiveTable with empty name.");
    }

    @Test
    public void testConstructorNullCommentValid() {
        testTable = new HiveTable(TEST_DB_NAME, TEST_TABLE_NAME, null);
        assertNotNull(testTable);
    }

    @Test
    public void testConstructorEmptyCommentValid() {
        testTable = new HiveTable(TEST_DB_NAME, TEST_TABLE_NAME, "");
        assertNotNull(testTable);
    }

    @Test
    public void testSetGetDbName() {
        final String updatedDbName = "UpdatedDBName";
        testTable.setDbName(updatedDbName);
        assertEquals(updatedDbName, testTable.getDbName());
    }

    @Test
    public void testSetNullDbNameIsValid() {
        testTable.setDbName(null);
        assertNull(testTable.getDbName());
    }

    @Test
    public void testSetEmptyDbNameIsValid() {
        testTable.setDbName("");
        assertNull(testTable.getDbName());
    }

    @Test
    public void testSetGetTableName() {
        final String updatedName = "UpdatedName";
        testTable.setTableName(updatedName);
        assertEquals(updatedName, testTable.getTableName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullTableNameIsInvalid() {
        testTable.setTableName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyTableNameIsInvalid() {
        testTable.setTableName("");
    }

    @Test
    public void testSetGetComment() {
        final String updatedComment = "A different comment";
        testTable.setComment(updatedComment);
        assertEquals(updatedComment, testTable.getComment());
    }

    @Test
    public void testSetCommentNull() {
        testTable.setComment(null);
        assertNull(testTable.getComment());
    }

    @Test
    public void testSetCommentEmpty() {
        testTable.setComment("");
        assertEquals("", testTable.getComment());
    }

    @Test
    public void testSetBadDbNameCharacters() {
        for (char idx = 0; idx < 255; idx++) {
            final String testName = String.valueOf(idx);

            // try setting name to any character that is not in the list of valid characters 
            if (VALID_NAME_CHARS.indexOf(testName) < 0) {
                try {
                    testTable.setDbName(String.valueOf(testName));
                    fail("setName() allowed invalid character [" + testName + "]");
                } catch (final IllegalArgumentException e) {
                    // ignore expected exception
                }
            }
        }
    }

    @Test
    public void testSetBadTableNameCharacters() {
        for (char idx = 0; idx < 255; idx++) {
            final String testName = String.valueOf(idx);

            // try setting name to any character that is not in the list of valid characters 
            if (VALID_NAME_CHARS.indexOf(testName) < 0) {
                try {
                    testTable.setTableName(String.valueOf(testName));
                    fail("setName() allowed invalid character [" + testName + "]");
                } catch (final IllegalArgumentException e) {
                    // ignore expected exception
                }
            }
        }
    }
}
