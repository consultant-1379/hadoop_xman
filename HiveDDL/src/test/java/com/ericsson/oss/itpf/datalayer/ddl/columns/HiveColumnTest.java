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
package com.ericsson.oss.itpf.datalayer.ddl.columns;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ericsson.oss.itpf.datalayer.ddl.columns.HiveColumn;

public class HiveColumnTest {

    private static final String TEST_COL_NAME = "TestCol";

    @Test
    public void testHiveColumn() {
        final HiveColumn testCol = new HiveColumn(TEST_COL_NAME);
        assertEquals(TEST_COL_NAME, testCol.getName());
    }

    @Test
    public void testSetGetNameForValidName() {
        final HiveColumn testCol = new HiveColumn(TEST_COL_NAME);
        final String updatedName = "UpdatedName";
        testCol.setName(updatedName);
        assertEquals(updatedName, testCol.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullNameInvalid() {
        final HiveColumn testCol = new HiveColumn(TEST_COL_NAME);
        testCol.setName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyNameInvalid() {
        final HiveColumn testCol = new HiveColumn(TEST_COL_NAME);
        testCol.setName("");
    }

    @Test
    public void testGetDDL() {
        final HiveColumn testCol = new HiveColumn(TEST_COL_NAME);
        assertEquals(TEST_COL_NAME, testCol.getDDL());
    }

}