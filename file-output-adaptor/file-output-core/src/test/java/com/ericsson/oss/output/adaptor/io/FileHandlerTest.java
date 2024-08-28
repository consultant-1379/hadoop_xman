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
package com.ericsson.oss.output.adaptor.io;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class FileHandlerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	/**
	 * Tests that a bad location will cause init() to fail
	 */
	public void testIncorrectLocation() {
		FileHandler fileHandler = new FileHandler("Wrong Storage Location", "NoEvent");
		try {
			fileHandler.init();
		} catch (Exception e) {
			assertTrue("Cant Write to an incorrect location", true);
		}
	}

	@Test
	public void testConstructFileName() {
		FileHandler fileHandler = new FileHandler("None", "TestEvent");
		// TODO Add File Extension to test
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String date = dateFormat.format(new Date(System.currentTimeMillis()));

		assertTrue("Fail: " + ("TestEvent" + "_" + date) + " is not equal to "
				+ fileHandler.constructFileName(),
				("TestEvent" + "_" + date).equals(fileHandler
						.constructFileName()));
	}

}
