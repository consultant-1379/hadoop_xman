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

import java.math.BigInteger;

import org.junit.Test;

public class UnsignedbigintFormatterTest {

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
			expected = "100,0,0,0,0,0,0,0,0";
		} else {
			expected = "0,0,0,0,0,0,0,100,0";
		}
		
		/* Calling the tested method */
		BigInteger bigInt = new BigInteger("100");
		byte[] i = UnsignedbigintFormatter.doFormat(bigInt);
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
		BigInteger bigInt = new BigInteger("-10");
		byte[] i = UnsignedbigintFormatter.doFormat(bigInt);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}		

	@Test
	public void testFormatBigIntLittleEndian() {

		String expected = "100,0,0,0,0,0,0,0,0";

		/* Calling the tested method */
		BigInteger bigInt = new BigInteger("100");
		byte[] i = UnsignedbigintFormatter.doFormat(bigInt, LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = UnsignedbigintFormatter.doFormat(bigInt, LITTLE_ENDIAN,
				NON_NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatBigIntBigEndian() {

		String expected = "0,0,0,0,0,0,0,100,0";

		/* Calling the tested method */
		BigInteger bigInt = new BigInteger("100");
		byte[] i = UnsignedbigintFormatter.doFormat(bigInt, BIG_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = UnsignedbigintFormatter
				.doFormat(bigInt, BIG_ENDIAN, NON_NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatVeryBigIntLittleEndian() {

		String expected = "-79,-12,-111,98,84,-36,43,0,0";

		/* Calling the tested method */
		BigInteger bigInt = new BigInteger("12345678987654321");
		byte[] i = UnsignedbigintFormatter.doFormat(bigInt, LITTLE_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = UnsignedbigintFormatter.doFormat(bigInt, LITTLE_ENDIAN,
				NON_NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatVeryBigIntBigEndian() {

		String expected = "0,43,-36,84,98,-111,-12,-79,0";

		/* Calling the tested method */
		BigInteger bigInt = new BigInteger("12345678987654321");
		byte[] i = UnsignedbigintFormatter.doFormat(bigInt, BIG_ENDIAN);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = UnsignedbigintFormatter
				.doFormat(bigInt, BIG_ENDIAN, NON_NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Result for big & little endian should be the same for null strings
	 */
	@Test
	public void testFormatZero() {
		String expected = "0,0,0,0,0,0,0,0,0";

		/* Calling the tested method */
		BigInteger bigInt = new BigInteger("0");
		byte[] i = UnsignedbigintFormatter.doFormat(bigInt, LITTLE_ENDIAN, NON_NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = UnsignedbigintFormatter
				.doFormat(bigInt, BIG_ENDIAN, NON_NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	/**
	 * Result for big & little endian should be the same for null strings
	 */
	@Test
	public void testFormatNull() {
		String expected = "0,0,0,0,0,0,0,0,1";

		/* Calling the tested method */
		BigInteger bigInt = new BigInteger("12345678987654321");
		byte[] i = UnsignedbigintFormatter.doFormat(bigInt, LITTLE_ENDIAN, NULL_FIELD);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4] + "," + i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);

		i = UnsignedbigintFormatter
				.doFormat(bigInt, BIG_ENDIAN, NULL_FIELD);
		actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4] + ","
				+ i[5] + "," + i[6] + "," + i[7] + "," + i[8];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}
}
