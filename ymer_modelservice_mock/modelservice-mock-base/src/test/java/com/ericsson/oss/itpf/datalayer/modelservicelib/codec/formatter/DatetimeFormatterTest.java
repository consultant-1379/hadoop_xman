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

public class DatetimeFormatterTest {

	static final boolean LITTLE_ENDIAN = true;
	static final boolean BIG_ENDIAN = false;
	private static final Object EXPECTED_LENGTH = 9;

	/**
	 * Result for big & little endian should be the same for empty string
	 */
	@Test
	public void testFormatEmptyString() {

		String expected = "0,0,0,0,0,0,0,0,1";

		/* Calling the tested method */
		byte[] i = DatetimeFormatter.doFormat("", LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = DatetimeFormatter.doFormat("", BIG_ENDIAN);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Result for big & little endian should be the same for null string
	 */
	@Test
	public void testFormatNullString() {

		String expected = "0,0,0,0,0,0,0,0,1";

		byte[] i = DatetimeFormatter.doFormat(null, LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = DatetimeFormatter.doFormat(null, BIG_ENDIAN);

		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

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
			expected =  "-128,23,-15,10,-111,53,-31,0,0";
		} else {
			expected = "0,-31,53,-111,10,-15,23,-128,0";
		}
		
		/* Calling the tested method */
		byte[] i = DatetimeFormatter.doFormat("2008-10-09 10:20:30");
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}
	
	@Test
	public void testFormatDateTimeLittleEndian() {
		String expected = "-128,23,-15,10,-111,53,-31,0,0";

		byte[] i = DatetimeFormatter.doFormat("2008-10-09 10:20:30",
				LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatDateTimeBigEndian() {
		String expected = "0,-31,53,-111,10,-15,23,-128,0";

		byte[] i = DatetimeFormatter.doFormat("2008-10-09 10:20:30",
				BIG_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}


	@Test
	public void testFormatDateTimeWithMillisLittleEndian() {
		String expected = "-64,-77,-15,10,-111,53,-31,0,0";

		byte[] i = DatetimeFormatter.doFormat("2008-10-09 10:20:30.40",
				LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatDateTimeWithMillisBigEndian() {
		String expected = "0,-31,53,-111,10,-15,-77,-64,0";

		byte[] i = DatetimeFormatter.doFormat("2008-10-09 10:20:30.40",
				BIG_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}
	/**
	 * Date time should be in format YYYY-MM-DD HH:mm:ss.ms If only date is
	 * specified, DatetimeFormatter will throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFormatDateOnlyLittleEndian() {
		/* Calling the tested method */
		DatetimeFormatter.doFormat("2008-10-09", LITTLE_ENDIAN);
		fail("Should not execute this line");
	}

	/**
	 * Date time should be in format YYYY-MM-DD HH:mm:ss.ms If only date is
	 * specified, DatetimeFormatter will throw an IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFormatDateOnlyBigEndian() {
		/* Calling the tested method */
		DatetimeFormatter.doFormat("2008-10-09", BIG_ENDIAN);
		fail("Should not execute this line");
	}

	/**
	 * Date time should be in format YYYY-MM-DD HH:mm:ss.ms If not all time
	 * elements are specified, DatetimeFormatter should throw an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFormatBadTimeLittleEndian() {
		/* Calling the tested method */
		DatetimeFormatter.doFormat("2008-10-09 10:20", LITTLE_ENDIAN);
		fail("Should not execute this line");
	}

	/**
	 * Date time should be in format YYYY-MM-DD HH:mm:ss.ms If not all time
	 * elements are specified, DatetimeFormatter should throw an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFormatBadTimeBigEndian() {
		/* Calling the tested method */
		DatetimeFormatter.doFormat("2008-10-09 10:20", BIG_ENDIAN);
		fail("Should not execute this line");
	}

	/**
	 * Trying to format a non-date time value should throw an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFormatBadDateTimeLittleEndian() {
		/* Calling the tested method */
		DatetimeFormatter.doFormat("text adsdasdas", LITTLE_ENDIAN);
		fail("Should not execute this line");
	}

	/**
	 * Trying to format a non-date time value should throw an
	 * IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFormatBadDateTimeBigEndian() {
		/* Calling the tested method */
		DatetimeFormatter.doFormat("text", BIG_ENDIAN);
		fail("Should not execute this line");
	}
}
