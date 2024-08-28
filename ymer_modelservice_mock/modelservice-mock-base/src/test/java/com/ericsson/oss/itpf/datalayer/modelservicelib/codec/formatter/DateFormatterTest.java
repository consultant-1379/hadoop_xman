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

public class DateFormatterTest {

	static final boolean LITTLE_ENDIAN = true;
	static final boolean BIG_ENDIAN = false;
	private static final Object EXPECTED_LENGTH = 5;

	/*
	 * Result for big & little endian should be the same for empty string
	 */
	@Test
	public void testFormatEmptyString() {

		String expected = "0,0,0,0,1";

		/* Calling the tested method */
		byte[] i = DateFormatter.doFormat("", LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = DateFormatter.doFormat("", BIG_ENDIAN);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/*
	 * Result for big & little endian should be the same for null string
	 */
	@Test
	public void testFormatNullString() {

		String expected = "0,0,0,0,1";

		/* Calling the tested method */
		byte[] i = DateFormatter.doFormat(null, LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = DateFormatter.doFormat(null, BIG_ENDIAN);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/*
	 * Short-form doFormat should use native endian order
	 */
	@Test
	public void testDoFormatEndian() {
		String expected;

		if (BinFormatter.IS_LITTLE_ENDIAN) {
			expected =  "-7,49,11,0,0";
		} else {
			expected = "0,11,49,-7,0";
		}
		
		/* Calling the tested method */
		byte[] i = DateFormatter.doFormat("2008-10-09");
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatDateLittleEndian() throws Exception {

		String expected = "-7,49,11,0,0";

		/* Calling the tested method */
		byte[] i = DateFormatter.doFormat("2008-10-09", LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatDateBigEndian() throws Exception {

		String expected = "0,11,49,-7,0";

		/* Calling the tested method */
		byte[] i = DateFormatter.doFormat("2008-10-09", BIG_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatDateWithTimeLittleEndian() throws Exception {

		String expected = "-7,49,11,0,0";

		/* Calling the tested method */
		byte[] i = DateFormatter.doFormat("2008-10-09 10:24", LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatDateWithTimeBigEndian() throws Exception {

		String expected = "0,11,49,-7,0";

		/* Calling the tested method */
		byte[] i = DateFormatter.doFormat("2008-10-09 10:24", BIG_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}
	
	@Test (expected=NumberFormatException.class)
	public void testFormatBadDateLittleEndian() {
		/* Calling the tested method */
		DateFormatter.doFormat("text", LITTLE_ENDIAN);
		fail("Should not execute this line");
	}

	@Test (expected=NumberFormatException.class)
	public void testFormatBadDateBigEndian() {
		/* Calling the tested method */
		DateFormatter.doFormat("text", BIG_ENDIAN);
		fail("Should not execute this line");
	}
}
