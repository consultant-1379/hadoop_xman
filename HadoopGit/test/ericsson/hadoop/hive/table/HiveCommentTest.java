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

import org.junit.Test;


public class HiveCommentTest {

    final static String TEST_COMMENT = "This is a test comment";
    final static String SINGLE_QUOTES_COMMENT = "This is a test comment with 'single quotes'";

    @Test
    public void testConstructor() {
        final HiveComment testComment = new HiveComment(TEST_COMMENT);
        assertEquals(TEST_COMMENT, testComment.getComment());
    }

    @Test
    public void testConstructorNullCommentIsValid() {
        final HiveComment testComment = new HiveComment(null);
        assertNotNull(testComment);
    }

    @Test
    public void testConstructorEmptyCommentIsValid() {
        final HiveComment testComment = new HiveComment("");
        assertNotNull(testComment);
    }

    @Test
    public void testSetGetComment() {
        final HiveComment testComment = new HiveComment(TEST_COMMENT);
        final String updatedComment = "This is an updated comment";
        testComment.setComment(updatedComment);
        assertEquals(updatedComment, testComment.getComment());
    }

    @Test
    public void testGetDDL() {
        final HiveComment testComment = new HiveComment(TEST_COMMENT);
        assertEquals(" COMMENT '" + TEST_COMMENT + "'", testComment.getDDL());
    }

    @Test
    public void testGetDDLNullComment() {
        final HiveComment testComment = new HiveComment(null);
        assertEquals("", testComment.getDDL());
    }

    @Test
    public void testGetDDLEmptyComment() {
        final HiveComment testComment = new HiveComment("");
        assertEquals("", testComment.getDDL());
    }

    @Test
    public void testGetDDLSingleQuoteComment() {
        final HiveComment testComment = new HiveComment(SINGLE_QUOTES_COMMENT);
        assertEquals(" COMMENT 'This is a test comment with \\'single quotes\\''", testComment.getDDL());
    }
}
