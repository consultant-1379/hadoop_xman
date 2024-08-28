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

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class FileManagerFactoryTest {

	private FileManagerFactory fmf;
	private String typeA;

	@Before
	public void setUp() throws Exception {
		fmf = new FileManagerFactory();
		typeA = "Call Failure";
	}

	@Test
	public void testGetListOfFileManagers() {
		Map<String, FileManager> map = fmf.getAllFileManagers();

		assertTrue("Size should be zero", map.isEmpty());
	}

	@Test
	public void testUsingSingleEventTypeMapShouldBeOne() {
		fmf.createFileManager(typeA);

		Map<String, FileManager> map = fmf.getAllFileManagers();
		assertTrue("Map should contain " + typeA, map.containsKey(typeA));
		assertTrue("Size should be one", map.size() == 1);
	}

	@Test
	public void testUsingSameEventTypeTwiceMapShouldStillBeOne() {
		fmf.createFileManager(typeA);
		fmf.createFileManager(typeA);

		Map<String, FileManager> fms = fmf.getAllFileManagers();
		assertTrue("Size should be one", fms.size() == 1);
	}

}
