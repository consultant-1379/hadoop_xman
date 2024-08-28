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

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BigintFormatterTest {

	static final boolean LITTLE_ENDIAN = true;
	static final boolean BIG_ENDIAN = false;

	static final boolean NULL_FIELD = true;
	static final boolean NON_NULL_FIELD = false;
	private static final Object EXPECTED_LENGTH = 9;

	/*
	 * Short-form doFormat should use native endian order
	 */
	@Test
	public void testDoFormatEndian() {
		String expected;

		if (BinFormatter.IS_LITTLE_ENDIAN) {
			expected = "-128,-106,-104,0,0,0,0,0,0";
		} else {
			expected = "0,0,0,0,0,-104,-106,-128,0";
		}
		
		/* Calling the tested method */
		byte[] i = BigintFormatter.doFormat(10000000);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/*
	 * Short-form doFormat should treat negative numbers as null
	 */
	@Test
	public void testDoFormatNull() {
		String expected = "0,0,0,0,0,0,0,0,1";

		/* Calling the tested method */
		byte[] i = BigintFormatter.doFormat(-10);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}	 
	
	@Test
	public void testFormatPositiveValueLittleEndian() {
		String expected = "-128,-106,-104,0,0,0,0,0,0";

		/* Calling the tested method */
		byte[] i = BigintFormatter.doFormat(10000000, LITTLE_ENDIAN,
				NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatPositiveValueBigEndian() {
		String expected = "0,0,0,0,0,-104,-106,-128,0";

		/* Calling the tested method */
		byte[] i = BigintFormatter.doFormat(10000000, BIG_ENDIAN,
				NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatNegativeValueLittleEndian() {
		String expected = "-128,105,103,-1,-1,-1,-1,-1,0";

		/* Calling the tested method */
		byte[] i = BigintFormatter.doFormat(-10000000, LITTLE_ENDIAN,
				NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatNegativeValueBigEndian() {
		String expected = "-1,-1,-1,-1,-1,103,105,-128,0";

		/* Calling the tested method */
		byte[] i = BigintFormatter.doFormat(-10000000, BIG_ENDIAN,
				NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Result for big & little endian should be the same for zero
	 */
	@Test
	public void testFormatZero() {
		String expected = "0,0,0,0,0,0,0,0,0";

		/* Calling the tested method */
		byte[] i = BigintFormatter.doFormat(0, LITTLE_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = BigintFormatter.doFormat(0, BIG_ENDIAN, NON_NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Result for big & little endian should be the same for null values
	 */
	@Test
	public void testFormatNullValue() {
		String expected = "0,0,0,0,0,0,0,0,1";

		/* Calling the tested method for little endian */
		byte[] i = BigintFormatter
				.doFormat(10000000, LITTLE_ENDIAN, NULL_FIELD);

		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		/* Calling the tested method for big endian */
		i = BigintFormatter.doFormat(10000000, BIG_ENDIAN, NULL_FIELD);

		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

}
