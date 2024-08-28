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

import org.junit.Test;

import com.ericsson.oss.itpf.datalayer.ddl.table.HiveFileFormat;

public class HiveFileFormatTest {

    @Test
    public void testHiveSortOrder() {

        final String[] fileFormatNames = { "SEQUENCEFILE", "TEXTFILE", "RCFILE", "ORC", "INPUTFORMAT" };

        // check expected number of types is correct
        assertEquals(fileFormatNames.length, HiveFileFormat.values().length);

        // check all expected types exist and toString gives correct value
        for (final String fileFormatName : fileFormatNames) {
            final HiveFileFormat fileFormat = HiveFileFormat.valueOf(fileFormatName);
            assertEquals(fileFormatName, fileFormat.toString());
        }
    }

}
