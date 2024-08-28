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
package com.ericsson.eniq.forwarder.core.io;

import static org.junit.Assert.*;
import com.ericsson.eniq.events.modelservice.api.event.Event;

import org.junit.Before;
import org.junit.Test;

public class FileManagerSetTest {

	private static final String TEST_EVENT_TYPE = TestEvent.class
			.getSimpleName();

	private FileManagerSet<Event> fmsTest;

	@Before
	public void setUp() {
		fmsTest = new FileManagerSet<Event>();
	}

	@Test
	public void testSetFileManager() {

		// check file manager set is empty before starting
		assertEquals(0, fmsTest.size());

		FileManager<Event> testFileManager = new FileManager<Event>(TEST_EVENT_TYPE);
		fmsTest.setFileManager(TEST_EVENT_TYPE, testFileManager);
		FileManager<Event> returnedFileManager = fmsTest
				.getFileManager(new TestEvent());

		// check testFileManager was added to set for TEST_EVENT_TYPE
		assertEquals(1, fmsTest.size());
		assertEquals(testFileManager, returnedFileManager);
	}

	@Test
	public void testGetFileManagerNotPreviouslyAdded() {
		/*
		 * TODO: get file manager for an event type that hasn't already been
		 * added. Will cause instatiation of new FileManager - which requires an
		 * RMI connection to Model Service and will throw an exception if model
		 * service isn't running... Which causes test to fail
		 * 
		 * Need to decouple this somehow???
		 */

		// check testFileManager was added to set for TEST_EVENT_TYPE
		FileManager<Event> returnedFileManager = fmsTest
				.getFileManager(new TestEvent());
		assertEquals(1, fmsTest.size());
		// check file manager is for EVENT_TYPE assertEquals(????,
		// returnedFileManager);
	}

	/**
	 * dummy event class for testing
	 */
	class TestEvent extends Event {

	}
}
