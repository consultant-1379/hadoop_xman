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

import com.ericsson.oss.itpf.datalayer.ddl.columns.HiveSortOrder;

public class HiveSortOrderTest {

    @Test
    public void testHiveSortOrder() {

        final String[] sortOrderNames = { "ASC", "DESC" };

        // check expected number of types is correct
        assertEquals(sortOrderNames.length, HiveSortOrder.values().length);

        // check all expected types exist and toString gives correct value
        for (final String sortOrderName : sortOrderNames) {
            final HiveSortOrder sortOrder = HiveSortOrder.valueOf(sortOrderName);
            assertEquals(sortOrderName, sortOrder.toString());
        }
    }

}
