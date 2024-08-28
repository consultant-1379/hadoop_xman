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
package com.ericsson.oss.datalayer.handler;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ericsson.oss.datalayer.event.Event;
import com.ericsson.oss.datalayer.util.PropertyManager;
import com.ericsson.oss.modelservice.codec.Codec;

public class AbstractFileWriterTest {

	private static final String ONE_MB = "1048576";
	private static final String DEFAULT_EVENT_COUNT_LIMIT = "5001";
	private final static long HIGH_BYTE_COUNT = Long.parseLong(PropertyManager
			.getProperty("byteCountLimit", ONE_MB)) + 1;
	private final static long LOW_BYTE_COUNT = 1;
	private final static long HIGH_EVENT_COUNT = Long.parseLong(PropertyManager
			.getProperty("eventCountLimit", DEFAULT_EVENT_COUNT_LIMIT)) + 1;
	private final static long LOW_EVENT_COUNT = 1;

	@Test
	public void testSetCodec() {
		AbstractFileWriter<String> mockWriter = new MockAbstractFileWriter();
		Codec<Event, String> mockCodec = new MockCodec();
		mockWriter.setCodec(mockCodec);
		Codec<Event, String> returnedCodec = mockWriter.getCodec();
		assertEquals(mockCodec, returnedCodec);
	}

	@Test
	public void testSetByteCount() {
		MockAbstractFileWriter testAbstract = new MockAbstractFileWriter();
		testAbstract.setByteCount(1);
		long returnedByteCount = testAbstract.getByteCount();
		assertEquals(1, returnedByteCount);
	}

	@Test
	public void testSetEventCount() {
		MockAbstractFileWriter testAbstract = new MockAbstractFileWriter();
		testAbstract.setEventCount(1);
		long returnedEventCount = testAbstract.getEventCount();
		assertEquals(1, returnedEventCount);
	}

	@Test
	public void testShouldRollOverByteCountOverLimit() {
		MockAbstractFileWriter testAbstract = new MockAbstractFileWriter();
		testAbstract.setByteCount(HIGH_BYTE_COUNT);
		assertTrue(testAbstract.shouldRollover());
	}

	@Test
	public void testShouldRollOverByteCountUnderLimit() {
		MockAbstractFileWriter testAbstract = new MockAbstractFileWriter();
		testAbstract.setByteCount(LOW_BYTE_COUNT);
		assertFalse(testAbstract.shouldRollover());
	}

	@Test
	public void testShouldRollOverEventOverLimit() {
		MockAbstractFileWriter testAbstract = new MockAbstractFileWriter();
		testAbstract.setEventCount(HIGH_EVENT_COUNT);
		assertTrue(testAbstract.shouldRollover());
	}

	@Test
	public void testShouldRollOverEventUnderLimit() {
		MockAbstractFileWriter testAbstract = new MockAbstractFileWriter();
		testAbstract.setEventCount(LOW_EVENT_COUNT);
		assertFalse(testAbstract.shouldRollover());
	}

	@Test
	public void testOnEventCallsEventInternal() {
		MockAbstractFileWriter testAbstract = new MockAbstractFileWriter();
		testAbstract.onEvent(new MockEvent());
		assertTrue(testAbstract.onEventInternalCheck);
	}

	@Test
	public void testOnEventCallsRollover() {
		MockAbstractFileWriter testAbstract = new MockAbstractFileWriter();
		testAbstract.setByteCount(HIGH_BYTE_COUNT);
		testAbstract.onEvent(new MockEvent());
		assertTrue(testAbstract.rolloverCheck);
	}

	@Test
	public void testOnEventNoRollover() {
		MockAbstractFileWriter testAbstract = new MockAbstractFileWriter();
		testAbstract.onEvent(new MockEvent());
		assertFalse(testAbstract.rolloverCheck);
	}

	@Test
	public void testStatsUpdate() {
		MockAbstractFileWriter testAbstract = new MockAbstractFileWriter();
		testAbstract.setEventCount(1);
		testAbstract.setByteCount(20);
		testAbstract.updateStats(5, 10);
		long returnedEventCount = testAbstract.getEventCount();
		long returnedByteCount = testAbstract.getByteCount();
		assertEquals(6, returnedEventCount);
		assertEquals(30, returnedByteCount);
	}

	private class MockAbstractFileWriter extends AbstractFileWriter<String> {
		boolean onEventInternalCheck = false;
		boolean rolloverCheck = false;

		@Override
		protected void onEventInternal(Event event) {
			onEventInternalCheck = true;
		}

		@Override
		protected void rollover() {
			rolloverCheck = true;
		}
	}

}
