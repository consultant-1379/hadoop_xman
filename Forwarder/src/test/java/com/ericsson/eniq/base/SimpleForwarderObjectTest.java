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

import java.util.List;

import org.junit.Test;

public class SimpleForwarderObjectTest {

	SampleClass test1 = new SampleClass(1, "John", "Smith", 12345.6f, 321.76f);
	SampleClass test2 = new SampleClass(2, "Jane", "Doe", 54321f, 123.45f);
	SampleClassSmall test3 = new SampleClassSmall(3, "Jack", "Jones");
	
	@Test
	public void testConstructor() {
		SimpleForwarderObject sfo = new SimpleForwarderObject("MySample", test1);
		
		assertEquals("MySample",sfo.getObjectType());
		assertEquals(test1,sfo.getRealObject());
	}
	
	@Test
	public void testGetList() {
		SimpleForwarderObject sfo = new SimpleForwarderObject("MySample", test1);

		List<ForwarderField> fieldList = sfo.toList();
		assertEquals("id", fieldList.get(0).getName());
		assertEquals("firstName", fieldList.get(1).getName());
		assertEquals("lastName", fieldList.get(2).getName());
		assertEquals("today", fieldList.get(3).getName());
		assertEquals("age", fieldList.get(4).getName());
		assertEquals("income", fieldList.get(5).getName());
		assertEquals("outgoings", fieldList.get(6).getName());
		assertEquals("balance", fieldList.get(7).getName());
		assertEquals("flags", fieldList.get(8).getName());
	}
	
	@Test
	public void testSetGetRealObject() {
		SimpleForwarderObject sfo = new SimpleForwarderObject("MySample", test1);
		assertEquals(test1,sfo.getRealObject());
		
		// change object
		sfo.setRealObject(test2);
		assertEquals(test2,sfo.getRealObject());
	}

	@Test
	public void testSetRealObjectUpdatesFieldList() {
		SimpleForwarderObject sfo = new SimpleForwarderObject("MySample", test1);
		assertEquals(test1,sfo.getRealObject());
		List<ForwarderField> fieldList = sfo.toList();
		assertEquals(1, fieldList.get(0).getValue());
		assertEquals("John", fieldList.get(1).getValue());
		assertEquals(9, fieldList.size());
		
		// change object
		sfo.setRealObject(test2);
		assertEquals(test2,sfo.getRealObject());
		fieldList = sfo.toList();
		assertEquals(2, fieldList.get(0).getValue());
		assertEquals("Jane", fieldList.get(1).getValue());
		assertEquals(9, fieldList.size());
		
		// change object to different class
		sfo.setRealObject(test3);
		assertEquals(test3,sfo.getRealObject());
		fieldList = sfo.toList();
		assertEquals(3, fieldList.get(0).getValue());
		assertEquals("Jack", fieldList.get(1).getValue());
		assertEquals(3, fieldList.size());
	}
}
