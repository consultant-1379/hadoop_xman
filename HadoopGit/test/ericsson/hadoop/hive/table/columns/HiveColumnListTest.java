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
package ericsson.hadoop.hive.table.columns;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class HiveColumnListTest {

    private static final HiveTypedColumn TEST_COLUMN = new HiveTypedColumn("Column1", HiveDataType.BIGINT, "Comment");
    private static final HiveTypedColumn TEST_COLUMN2 = new HiveTypedColumn("Column2", HiveDataType.SMALLINT, "Comment 2");
    private static final HiveTypedColumn TEST_COLUMN3 = new HiveTypedColumn("Column3", HiveDataType.INT, "Comment 3");

    @Test
    public void testNewListIsEmpty() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        assertEquals(0, columns.size());
    }

    @Test
    public void testAddColumn() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);
        assertEquals(1, columns.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullColumn() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(null);
    }

    @Test
    public void testAddMultipleColumns() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);
        columns.add(TEST_COLUMN2);
        columns.add(TEST_COLUMN3);
        assertEquals(3, columns.size());
    }

    @Test
    public void testColumnsRetainOrder() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);
        columns.add(TEST_COLUMN2);
        columns.add(TEST_COLUMN3);

        final List<HiveTypedColumn> colList = columns.getColumnList();
        assertEquals(TEST_COLUMN.getName(), colList.get(0).getName());
        assertEquals(TEST_COLUMN2.getName(), colList.get(1).getName());
        assertEquals(TEST_COLUMN3.getName(), colList.get(2).getName());
    }

    @Test
    public void testColumnExists() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);
        columns.add(TEST_COLUMN2);
        columns.add(TEST_COLUMN3);

        assertTrue(columns.columnExists(TEST_COLUMN.getName()));
        assertTrue(columns.columnExists(TEST_COLUMN2.getName()));
        assertTrue(columns.columnExists(TEST_COLUMN3.getName()));
    }

    @Test
    public void testColumnExistsColumnNotInList() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);
        columns.add(TEST_COLUMN2);
        columns.add(TEST_COLUMN3);

        assertFalse(columns.columnExists("nonExistantColumn"));
    }

    @Test
    public void testColumnExistsEmptyList() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        assertFalse(columns.columnExists("nonExistantColumn"));
    }

    @Test
    public void testColumnsExist() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);
        columns.add(TEST_COLUMN2);
        columns.add(TEST_COLUMN3);

        final HiveColumnList<HiveTypedColumn> checkList = new HiveColumnList<HiveTypedColumn>();
        checkList.add(TEST_COLUMN);

        assertTrue(columns.columnsExist(checkList));
    }

    @Test
    public void testColumnsExistMultiColList() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);
        columns.add(TEST_COLUMN2);

        final HiveColumnList<HiveTypedColumn> checkList = new HiveColumnList<HiveTypedColumn>();
        checkList.add(TEST_COLUMN3);
        checkList.add(TEST_COLUMN);

        assertTrue(columns.columnsExist(checkList));
    }

    @Test
    public void testColumnsExistEmptyCheckList() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);
        columns.add(TEST_COLUMN2);

        final HiveColumnList<HiveTypedColumn> checkList = new HiveColumnList<HiveTypedColumn>();

        assertFalse(columns.columnsExist(checkList));
    }

    @Test
    public void testColumnsExistEmptyList() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();

        final HiveColumnList<HiveTypedColumn> checkList = new HiveColumnList<HiveTypedColumn>();
        checkList.add(TEST_COLUMN3);
        checkList.add(TEST_COLUMN);

        assertFalse(columns.columnsExist(checkList));
    }

    @Test
    public void testColumnsExistNoMatch() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN2);

        final HiveColumnList<HiveTypedColumn> checkList = new HiveColumnList<HiveTypedColumn>();
        checkList.add(TEST_COLUMN3);
        checkList.add(TEST_COLUMN);

        assertFalse(columns.columnsExist(checkList));
    }

    // Adding a column with the same name as an existing column should fail
    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateColumn() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);

        final HiveTypedColumn dupColumn = new HiveTypedColumn("Column1", HiveDataType.STRING, "some comment");
        columns.add(dupColumn);
    }

    @Test
    public void testGetDDLBasicColumn() {
        final HiveColumnList<HiveColumn> columns = new HiveColumnList<HiveColumn>();
        columns.add(new HiveColumn("Column1"));
        columns.add(new HiveColumn("Column2"));
        columns.add(new HiveColumn("Column3"));

        final String expectedDDL = " (Column1, Column2, Column3)";
        assertEquals(expectedDDL, columns.getDDL());
    }

    @Test
    public void testGetDDLTypedColumn() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);
        columns.add(TEST_COLUMN2);
        columns.add(TEST_COLUMN3);

        final String expectedDDL = " (Column1 bigint COMMENT 'Comment', Column2 smallint COMMENT 'Comment 2', Column3 int COMMENT 'Comment 3')";
        assertEquals(expectedDDL, columns.getDDL());
    }

    @Test
    public void testGetDDLSortColumn() {
        final HiveColumnList<HiveSortColumn> columns = new HiveColumnList<HiveSortColumn>();
        columns.add(new HiveSortColumn("Column1"));
        columns.add(new HiveSortColumn("Column2", HiveSortOrder.DESC));
        columns.add(new HiveSortColumn("Column3", HiveSortOrder.ASC));

        final String expectedDDL = " (Column1 ASC, Column2 DESC, Column3 ASC)";
        assertEquals(expectedDDL, columns.getDDL());
    }

    @Test
    public void testGetDDLForSingleColumn() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);

        final String expectedDDL = " (Column1 bigint COMMENT 'Comment')";
        assertEquals(expectedDDL, columns.getDDL());
    }

    @Test
    public void testGetDDLForEmptyList() {
        final HiveColumnList<HiveColumn> columns = new HiveColumnList<HiveColumn>();

        final String expectedDDL = "";
        assertEquals(expectedDDL, columns.getDDL());
    }

    @Test
    public void testGetSimpleDDLBasicColumn() {
        final HiveColumnList<HiveColumn> columns = new HiveColumnList<HiveColumn>();
        columns.add(new HiveColumn("Column1"));
        columns.add(new HiveColumn("Column2"));
        columns.add(new HiveColumn("Column3"));

        final String expectedDDL = " (Column1, Column2, Column3)";
        assertEquals(expectedDDL, columns.getSimpleDDL());
    }

    @Test
    public void testGetSimpleDDLTypedColumn() {
        final HiveColumnList<HiveTypedColumn> columns = new HiveColumnList<HiveTypedColumn>();
        columns.add(TEST_COLUMN);
        columns.add(TEST_COLUMN2);
        columns.add(TEST_COLUMN3);

        final String expectedDDL = " (Column1, Column2, Column3)";
        assertEquals(expectedDDL, columns.getSimpleDDL());
    }

}
