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

public class TimeFormatterTest {

	static final boolean LITTLE_ENDIAN = true;
	static final boolean BIG_ENDIAN = false;
	private static final Object EXPECTED_LENGTH = 9;

	/*
	 * Short-form doFormat should use native endian order
	 */
	@Test
	public void testDoFormatEndian() {
		String expected;

		if (BinFormatter.IS_LITTLE_ENDIAN) {
			expected = "-128,-73,20,-85,8,0,0,0,0";
		} else {
			expected = "0,0,0,8,-85,20,-73,-128,0";
		}
		
		/* Calling the tested method */
		byte[] i = TimeFormatter.doFormat("10:20:30");
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];


		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}	
	
	/**
	 * Result for big & little endian should be the same for empty string
	 */
	@Test
	public void testFormatEmptyString() {

		String expected = "0,0,0,0,0,0,0,0,1";

		/* Calling the tested method */
		byte[] i = TimeFormatter.doFormat("", LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = TimeFormatter.doFormat("", BIG_ENDIAN);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Result for big & little endian should be the same for null strings
	 */
	@Test
	public void testFormatNullString() {

		String expected = "0,0,0,0,0,0,0,0,1";

		byte[] i = TimeFormatter.doFormat(null, LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = TimeFormatter.doFormat(null, BIG_ENDIAN);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatTimeLittleEndian() {

		String expected = "-128,-73,20,-85,8,0,0,0,0";

		/* Calling the tested method */
		byte[] i = TimeFormatter.doFormat("10:20:30", LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatTimeBigEndian() {

		String expected = "0,0,0,8,-85,20,-73,-128,0";

		/* Calling the tested method */
		byte[] i = TimeFormatter.doFormat("10:20:30", BIG_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatTimeMillisLittleEndian() {

		String expected = "-64,83,21,-85,8,0,0,0,0";

		/* Calling the tested method */
		byte[] i = TimeFormatter.doFormat("10:20:30.40", LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatTimeMillisBigEndian() {

		String expected = "0,0,0,8,-85,21,83,-64,0";

		/* Calling the tested method */
		byte[] i = TimeFormatter.doFormat("10:20:30.40", BIG_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Time should be in format HH:mm:ss.ms If not all time
	 * elements are specified, TimeFormatter should throw an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFormatBadTimeLittleEndian() {
		/* Calling the tested method */
		TimeFormatter.doFormat("10:20", LITTLE_ENDIAN);
		fail("Should not execute this line");
	}

	/**
	 * Time should be in format HH:mm:ss.ms If not all time
	 * elements are specified, TimeFormatter should throw an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFormatBadTimeBigEndian() {
		/* Calling the tested method */
		TimeFormatter.doFormat("10:20", BIG_ENDIAN);
		fail("Should not execute this line");
	}

	/**
	 * Trying to format a non-date time value should throw an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFormatBadDateTimeLittleEndian() {
		/* Calling the tested method */
		TimeFormatter.doFormat("text", LITTLE_ENDIAN);
		fail("Should not execute this line");
	}

	/**
	 * Trying to format a non-date time value should throw an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFormatBadDateTimeBigEndian() {
		/* Calling the tested method */
		TimeFormatter.doFormat("text", BIG_ENDIAN);
		fail("Should not execute this line");
	}
}
