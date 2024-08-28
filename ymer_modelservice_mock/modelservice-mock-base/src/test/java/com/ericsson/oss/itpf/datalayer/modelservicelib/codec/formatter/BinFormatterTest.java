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

import java.nio.ByteOrder;

import org.junit.Test;

public class BinFormatterTest {

	private static final int EXPECTED_LENGTH = 4;

	@Test
	public void testIsLittleEndian() {
		boolean isLittleEndian = (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN);
		assertEquals(isLittleEndian, BinFormatter.isLittleEndian());
	}

	@Test
	public void testIsLittleEndian2() {
		boolean isLittleEndian = (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN);
		assertEquals(isLittleEndian, BinFormatter.IS_LITTLE_ENDIAN);
	}

	@Test
	public void testCreateEmptyNumericFieldNotNull() {
		String expected = "0,0,0,0";

		/* Calling the tested method */
		byte[] i = BinFormatter.createEmptyByteField(EXPECTED_LENGTH,
				BinFormatter.IS_NOT_NULL);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testCreateEmptyNumericFieldNull() {
		String expected = "0,0,0,1";

		/* Calling the tested method */
		byte[] i = BinFormatter.createEmptyByteField(EXPECTED_LENGTH,
				BinFormatter.IS_NULL);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testCreateEmptyCharFieldNotNull() {
		String expected = "32,32,32,0";

		/* Calling the tested method */
		byte[] i = BinFormatter.createEmptyCharField(EXPECTED_LENGTH,
				BinFormatter.IS_NOT_NULL);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testCreateEmptyCharFieldNull() {
		String expected = "32,32,32,1";

		/* Calling the tested method */
		byte[] i = BinFormatter.createEmptyCharField(EXPECTED_LENGTH,
				BinFormatter.IS_NULL);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}
}
