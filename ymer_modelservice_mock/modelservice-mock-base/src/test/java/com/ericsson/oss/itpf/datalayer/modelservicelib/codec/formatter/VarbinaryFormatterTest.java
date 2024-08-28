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

public class VarbinaryFormatterTest {

	@Test
	public void testFormatNullBytes() {

		String expected = "0,0,1";

		/* Calling the tested method */
		byte[] nullTest = null;
		byte[] i = VarbinaryFormatter.doFormat(nullTest, 2);
		String actual = i[0] + "," + i[1] + "," + i[2];

		assertEquals(expected, actual);
		assertEquals(3, i.length);
	}

	@Test
	public void testFormatNullString() {

		String expected = "0,0,1";

		/* Calling the tested method */
		String nullTest = null;
		byte[] i = VarbinaryFormatter.doFormat(nullTest, 2);
		String actual = i[0] + "," + i[1] + "," + i[2];

		assertEquals(expected, actual);
		assertEquals(3, i.length);
	}

	@Test
	public void testFormatEmptyString() {

		String expected = "0,0,1";

		/* Calling the tested method */
		String nullTest = "";
		byte[] i = VarbinaryFormatter.doFormat(nullTest, 2);
		String actual = i[0] + "," + i[1] + "," + i[2];

		assertEquals(expected, actual);
		assertEquals(3, i.length);
	}

	@Test
	public void testFormatByteArray() throws Exception {
		String expected = "116,101,120,116,0";

		/* Calling the tested method */
		byte[] testBytes = "text".getBytes();
		byte[] i = VarbinaryFormatter.doFormat(testBytes, 4);

		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(5, i.length);
	}
	
	@Test
	public void testFormatString() throws Exception {
		String expected = "116,101,120,116,0";

		/* Calling the tested method */
		byte[] i = VarbinaryFormatter.doFormat("text", 4);

		String actual = i[0] + "," + i[1] + "," + i[2] + "," + i[3] + ","
				+ i[4];

		assertEquals(expected, actual);
		assertEquals(5, i.length);
	}

	@Test
	public void testFormatTooShortString() throws Exception {

		String expected = "0,0,1";

		/* Calling the tested method */
		byte[] i = VarbinaryFormatter.doFormat("t", 2);
		String actual = i[0] + "," + i[1] + "," + i[2];

		assertEquals(expected, actual);
		assertEquals(3, i.length);
	}

	@Test
	public void testFormatTooLongString() throws Exception {

		String expected = "0,0,1";

		/* Calling the tested method */
		byte[] i = VarbinaryFormatter.doFormat("test", 2);
		String actual = i[0] + "," + i[1] + "," + i[2];

		assertEquals(expected, actual);
		assertEquals(3, i.length);
	}
}
