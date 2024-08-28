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

public class VarcharFormatterTest {

	@Test
	public void testFormatEmptyString() throws Exception {

		String expected = "32,32,0";

		/* Calling the tested method */
		byte[] i = VarcharFormatter.doFormat("", 2);
		String actual = i[0] + "," + i[1] + "," + i[2];

		assertEquals(expected, actual);
		assertEquals(3, i.length);
	}

	@Test
	public void testFormatNullString() throws Exception {

		String expected = "32,32,1";

		/* Calling the tested method */
		byte[] i = VarcharFormatter.doFormat(null, 2);
		String actual = i[0] + "," + i[1] + "," + i[2];

		assertEquals(expected, actual);
		assertEquals(3, i.length);
	}

	@Test
	public void testFormatString() throws Exception {

		String expected = "116,101,120,116,0";

		/* Calling the tested method */
		byte[] i = VarcharFormatter.doFormat("text", 4);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(5, i.length);
	}

	@Test
	public void testFormatShortString() throws Exception {

		String expected = "116,32,32,32,0";

		/* Calling the tested method */
		byte[] i = VarcharFormatter.doFormat("t", 4);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4];

		assertEquals(expected, actual);
		assertEquals(5, i.length);
	}

	@Test
	public void testFormatLongString() throws Exception {

		String expected = "116,101,120,116,0";

		/* Calling the tested method */
		byte[] i = VarcharFormatter.doFormat("textual", 4);
		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + "," + i[4];

		assertEquals(expected, actual);
		assertEquals(5, i.length);
	}

}