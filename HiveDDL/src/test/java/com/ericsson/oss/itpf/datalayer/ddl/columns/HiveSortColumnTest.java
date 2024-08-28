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

import com.ericsson.oss.itpf.datalayer.ddl.columns.HiveSortColumn;
import com.ericsson.oss.itpf.datalayer.ddl.columns.HiveSortOrder;

public class HiveSortColumnTest {

    private static final String TEST_COL_NAME = "TestCol";

    @Test
    public void testConstructorNameOnlyDefaultSort() {
        final HiveSortColumn sortColumn = new HiveSortColumn(TEST_COL_NAME);
        assertEquals(TEST_COL_NAME, sortColumn.getName());
        assertEquals("Default sort order should be ASC", HiveSortOrder.ASC, sortColumn.getSortOrder());
    }

    @Test
    public void testConstructorAsc() {
        final HiveSortColumn sortColumn = new HiveSortColumn(TEST_COL_NAME, HiveSortOrder.ASC);
        assertEquals(TEST_COL_NAME, sortColumn.getName());
        assertEquals(HiveSortOrder.ASC, sortColumn.getSortOrder());
    }

    @Test
    public void testConstructorDesc() {
        final HiveSortColumn sortColumn = new HiveSortColumn(TEST_COL_NAME, HiveSortOrder.DESC);
        assertEquals(TEST_COL_NAME, sortColumn.getName());
        assertEquals(HiveSortOrder.DESC, sortColumn.getSortOrder());
    }

    @Test
    public void testSetGetSortOrder() {
        final HiveSortColumn sortColumn = new HiveSortColumn(TEST_COL_NAME, HiveSortOrder.ASC);
        assertEquals(TEST_COL_NAME, sortColumn.getName());
        sortColumn.setSortOrder(HiveSortOrder.DESC);
        assertEquals(HiveSortOrder.DESC, sortColumn.getSortOrder());
    }

    @Test
    public void testGetDDL() {
        final HiveSortColumn sortColumn = new HiveSortColumn(TEST_COL_NAME, HiveSortOrder.ASC);
        assertEquals(TEST_COL_NAME + " ASC", sortColumn.getDDL());

        sortColumn.setSortOrder(HiveSortOrder.DESC);
        assertEquals(TEST_COL_NAME + " DESC", sortColumn.getDDL());
    }
}
