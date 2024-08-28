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

import org.junit.Test;

public class HiveTypedColumnTest {

    /**
     * 
     */
    private static final String VALID_NAME_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
    private static final String TEST_COL_NAME = "testCol";
    private static final String TEST_COMMENT = "this is a test comment";

    @Test
    public void testConstructor() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, TEST_COMMENT);

        assertEquals(TEST_COL_NAME, testCol.getName());
        assertEquals(HiveDataType.BIGINT, testCol.getDataType());
        assertEquals(TEST_COMMENT, testCol.getComment());
    }

    @Test
    public void testNoCommentConstructor() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT);

        assertEquals(TEST_COL_NAME, testCol.getName());
        assertEquals(HiveDataType.BIGINT, testCol.getDataType());
        assertNull(testCol.getComment());
    }

    @Test
    public void testConstructorValidNameCharacters() {
        try {
            final HiveTypedColumn testCol = new HiveTypedColumn(VALID_NAME_CHARS, HiveDataType.BIGINT, TEST_COMMENT);
            assertNotNull(testCol);
        } catch (final IllegalArgumentException e) {
            fail("Valid name characters caused IllegalArgumentException");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullNameInvalid() {
        new HiveTypedColumn(null, HiveDataType.BIGINT, TEST_COMMENT);
        fail("Shouldn't be able to create HiveColumn with null name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmptyNameInvalid() {
        new HiveTypedColumn("", HiveDataType.BIGINT, TEST_COMMENT);
        fail("Shouldn't be able to create HiveColumn with empty name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullDataTypeInvalid() {
        new HiveTypedColumn(TEST_COL_NAME, null, TEST_COMMENT);
        fail("Shouldn't be able to create HiveColumn with null data type");
    }

    @Test
    public void testConstructorNullCommentValid() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, null);
        assertNotNull(testCol);
    }

    @Test
    public void testConstructorEmptyCommentValid() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, "");
        assertNotNull(testCol);
    }

    @Test
    public void testSetGetDataType() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, TEST_COMMENT);

        final HiveDataType updatedType = HiveDataType.STRING;
        testCol.setDataType(updatedType);
        assertEquals(updatedType, testCol.getDataType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullDataTypeInvalid() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, TEST_COMMENT);
        testCol.setDataType(null);
    }

    @Test
    public void testSetGetComment() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, TEST_COMMENT);

        final String updatedComment = "A different comment";
        testCol.setComment(updatedComment);
        assertEquals(updatedComment, testCol.getComment());
    }

    @Test
    public void testSetCommentNull() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, TEST_COMMENT);

        testCol.setComment(null);
        assertNull(testCol.getComment());
    }

    @Test
    public void testSetCommentEmpty() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, TEST_COMMENT);

        testCol.setComment("");
        assertEquals("", testCol.getComment());
    }

    @Test
    public void testSetBadNameCharacters() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, TEST_COMMENT);

        for (char idx = 0; idx < 255; idx++) {

            final String testName = String.valueOf(idx);

            // try setting name to any character that is not in the list of valid characters 
            if (VALID_NAME_CHARS.indexOf(testName) < 0) {
                try {
                    testCol.setName(String.valueOf(testName));
                    fail("setName() allowed invalid character [" + testName + "]");
                } catch (final IllegalArgumentException e) {
                    // ignore expected exception
                }
            }
        }
    }

    @Test
    public void testGetDDL() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, TEST_COMMENT);

        String expected = TEST_COL_NAME + " bigint COMMENT '" + TEST_COMMENT + "'";
        assertEquals(expected, testCol.getDDL());

        testCol.setDataType(HiveDataType.STRING);
        expected = TEST_COL_NAME + " string COMMENT '" + TEST_COMMENT + "'";
        assertEquals(expected, testCol.getDDL());

        testCol.setName("anotherCol");
        expected = "anotherCol string COMMENT '" + TEST_COMMENT + "'";
        assertEquals(expected, testCol.getDDL());
    }

    @Test
    public void testGetDDLNoComment() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, null);

        final String expected = TEST_COL_NAME + " bigint";
        assertEquals(expected, testCol.getDDL());
    }

    @Test
    public void testGetDDLEmptyComment() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, "");

        final String expected = TEST_COL_NAME + " bigint";
        assertEquals(expected, testCol.getDDL());
    }

    @Test
    public void testGetDDLCommentWithSingleQuotes() {
        final HiveTypedColumn testCol = new HiveTypedColumn(TEST_COL_NAME, HiveDataType.BIGINT, "Test comment with 'single quotes'");

        final String expected = TEST_COL_NAME + " bigint COMMENT 'Test comment with \\'single quotes\\''";
        assertEquals(expected, testCol.getDDL());
    }
}
