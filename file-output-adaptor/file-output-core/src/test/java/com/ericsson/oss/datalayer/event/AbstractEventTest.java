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

package com.ericsson.oss.datalayer.event;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractEventTest {

	AbstractEvent abstractEvent;
	Header testHeader;
	long testTypeId;

	@Before
	public void setup() {
		abstractEvent = new AbstractEvent();
		testHeader = new Header();
		testTypeId = 1234567;
	}

	@Test
	public void testSetHeader() {
		abstractEvent.setHeader(testHeader);
		assertTrue("Headers should be equal",
				abstractEvent.getHeader().equals(testHeader));
	}

	@Test
	public void testHasHeader() {
		abstractEvent.setHeader(testHeader);
		assertTrue("Headers should exist", abstractEvent.hasHeader());
	}

	@Test
	public void testSetTypeId() {
		abstractEvent.setTypeId(testTypeId);
		assertTrue("typeId should be set",
				abstractEvent.getTypeId() == testTypeId);
	}

	@Test
	public void testHasRopId() {
		assertTrue("RopId should have a value", abstractEvent.hasRopId());
	}
}
