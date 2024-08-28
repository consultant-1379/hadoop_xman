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

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class FieldTypeTest {

	@Test
	public void testClassToFieldSeesBytesAsTinyInt() {
		Byte test1 = 1;
		byte test2 = 2;
		Object test2AsObj = test2; // wrap as object
		
		assertEquals(FieldType.TINYINT,FieldType.classToFieldType(test1.getClass()));
		assertEquals(FieldType.TINYINT,FieldType.classToFieldType(test2AsObj.getClass()));
	}

	@Test
	public void testClassToFieldSeesShortsAsSmallInt() {
		Short test1 = 1;
		short test2 = 2;
		Object test2AsObj = test2; // wrap as object
		
		assertEquals(FieldType.SMALLINT,FieldType.classToFieldType(test1.getClass()));
		assertEquals(FieldType.SMALLINT,FieldType.classToFieldType(test2AsObj.getClass()));
	}
	
	@Test
	public void testClassToFieldSeesIntegersAsInt() {
		Integer test1 = 1;
		int test2 = 2;
		Object test2AsObj = test2; // wrap as object
		
		assertEquals(FieldType.INT,FieldType.classToFieldType(test1.getClass()));
		assertEquals(FieldType.INT,FieldType.classToFieldType(test2AsObj.getClass()));
	}

	@Test
	public void testClassToFieldSeesStringsAsVarchar() {
		String test1 = "xyz";
		
		assertEquals(FieldType.VARCHAR,FieldType.classToFieldType(test1.getClass()));
	}

	@Test
	public void testClassToFieldSeesByteArraysAsBinary() {
		byte[] test1 = new byte[10];
		Byte[] test2 = new Byte[10];
		
		assertEquals(FieldType.BINARY,FieldType.classToFieldType(test1.getClass()));
		assertEquals(FieldType.BINARY,FieldType.classToFieldType(test2.getClass()));
	}

	@Test
	public void testClassToFieldSeesDatesAsDate() {
		Date test1 = new Date();
		
		assertEquals(FieldType.DATE,FieldType.classToFieldType(test1.getClass()));
	}

	@Test
	public void testClassToFieldSeesTimestampsAsTimestamp() {
		Timestamp test1 = new Timestamp(1234567);
		
		assertEquals(FieldType.TIMESTAMP,FieldType.classToFieldType(test1.getClass()));
	}

	@Test
	public void testClassToFieldSeesFloatsAsFloat() {
		Float test1 = 1f;
		float test2 = 2f;
		Object test2AsObj = test2; // wrap as object
		
		assertEquals(FieldType.FLOAT,FieldType.classToFieldType(test1.getClass()));
		assertEquals(FieldType.FLOAT,FieldType.classToFieldType(test2AsObj.getClass()));
	}

	@Test
	public void testClassToFieldSeesDoublesAsFloat() {
		Double test1 = 1d;
		double test2 = 2f;
		Object test2AsObj = test2; // wrap as object
		
		assertEquals(FieldType.FLOAT,FieldType.classToFieldType(test1.getClass()));
		assertEquals(FieldType.FLOAT,FieldType.classToFieldType(test2AsObj.getClass()));
	}
	
	@Test
	public void testClassToFieldSeesBooleanAsBit() {
		Boolean test1 = true;
		boolean test2 = false;
		Object test2AsObj = test2; // wrap as object
		
		assertEquals(FieldType.BIT,FieldType.classToFieldType(test1.getClass()));
		assertEquals(FieldType.BIT,FieldType.classToFieldType(test2AsObj.getClass()));
	}
	
	@Test
	public void testClassToFieldSeesOtherObjectsAsUndefined() {
		Object test1 = new Object();
		
		assertEquals(FieldType.UNDEFINED,FieldType.classToFieldType(test1.getClass()));
	}

	
}
