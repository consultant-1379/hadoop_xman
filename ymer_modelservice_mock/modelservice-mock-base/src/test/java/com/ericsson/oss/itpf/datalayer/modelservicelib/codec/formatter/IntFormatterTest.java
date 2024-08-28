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

public class IntFormatterTest {

	static final boolean LITTLE_ENDIAN = true;
	static final boolean BIG_ENDIAN = false;

	static final boolean NULL_FIELD = true;
	static final boolean NON_NULL_FIELD = false;
	private static final Object EXPECTED_LENGTH = 5;

	/*
	 * Short-form doFormat should use native endian order
	 */
	@Test
	public void testDoFormatEndian() {
		String expected;

		if (BinFormatter.IS_LITTLE_ENDIAN) {
			expected = "8,0,0,0,0";
		} else {
			expected = "0,0,0,8,0";
		}
		
		/* Calling the tested method */
		byte[] i = IntFormatter.doFormat(8);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/*
	 * Short-form doFormat should treat negative numbers as null
	 */
	@Test
	public void testDoFormatNull() {
		String expected = "0,0,0,0,1";

		/* Calling the tested method */
		byte[] i = IntFormatter.doFormat(Integer.MIN_VALUE);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}	
	
	@Test
	public void testFormatIntLittleEndian() throws Exception {

		String expected = "8,0,0,0,0";

		/* Calling the tested method */
		byte[] i = IntFormatter.doFormat(8, LITTLE_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatIntBigEndian() throws Exception {

		String expected = "0,0,0,8,0";

		/* Calling the tested method */
		byte[] i = IntFormatter.doFormat(8, BIG_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatNegativeIntLittleEndian() throws Exception {

		String expected = "-87,-1,-1,-1,0";

		/* Calling the tested method */
		byte[] i = IntFormatter.doFormat(-87, LITTLE_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatNegativeIntBigEndian() throws Exception {

		String expected = "-1,-1,-1,-87,0";

		/* Calling the tested method */
		byte[] i = IntFormatter.doFormat(-87, BIG_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Result for big & little endian should be the same for zero
	 */
	@Test
	public void testFormatIntZero() throws Exception {

		String expected = "0,0,0,0,0";

		/* Calling the tested method */
		byte[] i = IntFormatter.doFormat(0, LITTLE_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = IntFormatter.doFormat(0, BIG_ENDIAN, NON_NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Result for big & little endian should be the same for null
	 */
	@Test
	public void testFormatIntNull() throws Exception {

		String expected = "0,0,0,0,1";

		/* Calling the tested method */
		byte[] i = IntFormatter.doFormat(123, LITTLE_ENDIAN, NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = IntFormatter.doFormat(456, BIG_ENDIAN, NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}
}
