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

public class FloatFormatterTest {

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
			expected = "0,0,0,65,0";
		} else {
			expected = "65,0,0,0,0";
		}
		
		/* Calling the tested method */
		byte[] i = FloatFormatter.doFormat(8f);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/*
	 * Short-form doFormat should treat Float.MIN_VALUE as null
	 */
	@Test
	public void testDoFormatNull() {
		String expected = "0,0,0,0,1";

		/* Calling the tested method */
		byte[] i = FloatFormatter.doFormat(Float.MIN_VALUE);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}		
	
	
	@Test
	public void testFormatFloatLittleEndian() throws Exception {

		String expected = "0,0,0,65,0";

		/* Calling the tested method */
		byte[] i = FloatFormatter.doFormat(8f, LITTLE_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatFloatBigEndian() throws Exception {

		String expected = "65,0,0,0,0";

		/* Calling the tested method */
		byte[] i = FloatFormatter.doFormat(8f, BIG_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatFloatWithDecimalLittleEndian() throws Exception {

		String expected = "102,-26,-82,66,0";

		/* Calling the tested method */
		byte[] i = FloatFormatter.doFormat(87.45f, LITTLE_ENDIAN,
				NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatFloatWithDecimalBigEndian() throws Exception {

		String expected = "66,-82,-26,102,0";

		/* Calling the tested method */
		byte[] i = FloatFormatter.doFormat(87.45f, BIG_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatNegativeFloatWithDecimalLittleEndian() throws Exception {

		String expected = "102,-26,-82,-62,0";

		/* Calling the tested method */
		byte[] i = FloatFormatter.doFormat(-87.45f, LITTLE_ENDIAN,
				NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatNegativeFloatWithDecimalBigEndian() throws Exception {

		String expected = "-62,-82,-26,102,0";

		/* Calling the tested method */
		byte[] i = FloatFormatter.doFormat(-87.45f, BIG_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Result for big & little endian should be the same for zero
	 */
	@Test
	public void testFormatFloatZero() throws Exception {

		String expected = "0,0,0,0,0";

		/* Calling the tested method */
		byte[] i = FloatFormatter.doFormat(0f, LITTLE_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = FloatFormatter.doFormat(0f, BIG_ENDIAN, NON_NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Result for big & little endian should be the same for null
	 */
	@Test
	public void testFormatFloatNull() throws Exception {

		String expected = "0,0,0,0,1";

		/* Calling the tested method */
		byte[] i = FloatFormatter.doFormat(123f, LITTLE_ENDIAN, NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = FloatFormatter.doFormat(456f, BIG_ENDIAN, NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}
}
