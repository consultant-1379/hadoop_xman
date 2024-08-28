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
package com.ericsson.oss.itpf.datalayer.modelservicelib.codec.formatter;

import static org.junit.Assert.*;

import org.junit.Test;

public class TinyintFormatterTest {

	static final boolean NULL_FIELD = true;
	static final boolean NON_NULL_FIELD = false;
	private static final Object EXPECTED_LENGTH = 2;

	/*
	 * Short-form doFormat should treat negative numbers as null
	 */
	@Test
	public void testDoFormatNull() {
		String expected = "0,1";

		/* Calling the tested method */
		byte[] i = TinyintFormatter.doFormat((byte) -10);
		String actual = i[0] + "," + i[1];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatNull() {

		String expected = "0,1";

		/* Calling the tested method */
		byte[] i = TinyintFormatter.doFormat((byte) 0, NULL_FIELD);
		String actual = i[0] + "," + i[1];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatZero() {

		String expected = "0,0";

		/* Calling the tested method */
		byte[] i = TinyintFormatter.doFormat((byte) 0, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatByte() {

		String expected = "10,0";

		/* Calling the tested method */
		byte[] i = TinyintFormatter.doFormat((byte) 10, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatNegativeByte() {

		String expected = "-30,0";

		/* Calling the tested method */
		byte[] i = TinyintFormatter.doFormat((byte) -30, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}
}