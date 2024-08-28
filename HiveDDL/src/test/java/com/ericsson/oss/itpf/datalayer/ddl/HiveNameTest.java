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
package com.ericsson.oss.itpf.datalayer.ddl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ericsson.oss.itpf.datalayer.ddl.HiveName;

public class HiveNameTest {

    /**
     * 
     */
    private static final String TEST_NAME = "TestName";
    private static final String VALID_NAME_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";

    @Test
    public void testConstructor() {
        final HiveName testName = new HiveName(TEST_NAME);
        assertEquals(TEST_NAME, testName.getName());
    }

    @Test
    public void testSetGetName() {
        final HiveName testName = new HiveName(TEST_NAME);
        final String updatedName = "DifferentName";
        testName.setName(updatedName);
        assertEquals(updatedName, testName.getName());
    }

    @Test
    public void testValidNameCharacters() {
        final HiveName testName = new HiveName(VALID_NAME_CHARS);
        assertNotNull(testName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullNameInvalid() {
        new HiveName(null);
        fail("Shouldn't be able to create HiveName object with null name.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyNameInvalid() {
        new HiveName("");
        fail("Shouldn't be able to create HiveName object with empty name.");
    }

    @Test
    public void testSetBadNameCharacters() {
        final HiveName testName = new HiveName(VALID_NAME_CHARS);

        for (char idx = 0; idx < 255; idx++) {

            final String testString = String.valueOf(idx);

            // try setting name to any character that is not in the list of valid characters 
            if (VALID_NAME_CHARS.indexOf(testString) < 0) {
                try {
                    testName.setName(String.valueOf(testString));
                    fail("setName() allowed invalid character [" + testString + "]");
                } catch (final IllegalArgumentException e) {
                    // ignore expected exception
                }

            }
        }
    }
}
