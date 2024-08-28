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
package com.ericsson.eniq.base;

import static org.junit.Assert.*;

import org.junit.Test;

public class ForwarderFieldTest {

	@Test
	public void testThreeFieldConstructor() {
		Integer testObject = 123;

		ForwarderField ff = new ForwarderField("Test1", testObject,
				FieldType.INT);
		assertEquals("Test1", ff.getName());
		assertEquals(testObject, ff.getValue());
		assertEquals(FieldType.INT, ff.getType());
		assertEquals(0, ff.getSize());
	}

	@Test
	public void testFourFieldConstructor() {
		String testObject = "abc123";

		ForwarderField ff = new ForwarderField("Test2", testObject,
				FieldType.VARCHAR, 6);
		assertEquals("Test2", ff.getName());
		assertEquals(testObject, ff.getValue());
		assertEquals(FieldType.VARCHAR, ff.getType());
		assertEquals(6, ff.getSize());
	}
	
	@Test
	public void testGettersAndSetters() {
		String testObject = "abc123";
		String testObject2 = "def456";

		ForwarderField ff = new ForwarderField("Test2", testObject,
				FieldType.VARCHAR, 6);
		
		ff.setName("Test3");
		ff.setValue(testObject2);
		ff.setType(FieldType.BINARY);
		ff.setSize(20);

		assertEquals("Test3", ff.getName());
		assertEquals(testObject2, ff.getValue());
		assertEquals(FieldType.BINARY, ff.getType());
		assertEquals(20, ff.getSize());
	}
	
	@Test
	public void testToStringNoSizeSet() {
		Integer testObject = 123;

		ForwarderField ff = new ForwarderField("Test1", testObject,
				FieldType.INT);
		
		assertEquals("Test1:INT:123", ff.toString());
	}

	@Test
	public void testToStringSizeSet() {
		String testObject = "abc123";

		ForwarderField ff = new ForwarderField("Test2", testObject,
				FieldType.VARCHAR, 6);
		
		assertEquals("Test2:VARCHAR(6):abc123", ff.toString());
	}
}
