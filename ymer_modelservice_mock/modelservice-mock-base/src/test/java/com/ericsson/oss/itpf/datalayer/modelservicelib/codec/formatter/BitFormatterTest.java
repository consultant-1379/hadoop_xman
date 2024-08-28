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

public class BitFormatterTest {

	private static final Object EXPECTED_LENGTH = 2;

	@Test
	public void testFormatZero() {

		String expected = "0,0";

		/* Calling the tested method */
		byte test = 0;
		byte[] i = BitFormatter.doFormat(test);
		String actual = i[0] + "," + i[1];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatOne() {

		String expected = "1,0";

		/* Calling the tested method */
		byte test = 1;
		byte[] i = BitFormatter.doFormat(test);
		String actual = i[0] + "," + i[1];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}

	@Test
	public void testFormatValueOtherThanOneOrZero() {

		String expected = "1,0";

		/* Calling the tested method */
		byte test = 30;
		byte[] i = BitFormatter.doFormat(test);
		String actual = i[0] + "," + i[1];

		assertEquals(expected, actual);
		assertEquals(EXPECTED_LENGTH, i.length);
	}
}
