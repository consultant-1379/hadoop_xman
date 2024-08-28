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

import com.ericsson.oss.itpf.datalayer.ddl.columns.HiveDataType;

public class HiveDataTypeTest {

    @Test
    public void testHiveDataType() {

        final String[] dataTypeNames = { "TINYINT", "SMALLINT", "INT", "BIGINT", "BOOLEAN", "FLOAT", "DOUBLE", "STRING", "BINARY", "TIMESTAMP",
                "DECIMAL" };

        // check expected number of types is correct
        assertEquals(dataTypeNames.length, HiveDataType.values().length);

        // check all expected types exist and toString gives correct value
        for (final String dataTypeName : dataTypeNames) {
            final HiveDataType dataType = HiveDataType.valueOf(dataTypeName);
            assertEquals(dataTypeName.toLowerCase(), dataType.toString());
        }
    }
}
