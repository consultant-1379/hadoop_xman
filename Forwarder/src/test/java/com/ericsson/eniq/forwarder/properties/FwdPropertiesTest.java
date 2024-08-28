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
package com.ericsson.eniq.forwarder.properties;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FwdPropertiesTest {

	private static final String APP_NAME_KEY = "app.name";
	private static final String APP_NAME_VALUE = "Eniq Forwarder";

	private static final String TEST_KEY = "testKey";
	private static final String TEST_VALUE = "Test Value";

	private static final String TEST_DOTTED_KEY = "test.dotted.key";
	private static final String TEST_DOTTED_VALUE = "Test Dotted Value";
	
	private static final String UNKNOWN_PROPERTY_KEY = "unknown";
	private static final String UNKNOWN_PROPERTY_DEFAULT = "default property value";
	
	private static final String NULL_PROPERTY_KEY = null;

	private static final String TEST_PROPERTIES_FILE = "testProperties.ini";
	private static final Object REPLACEMENT_APP_NAME_VALUE = "Test Forwarder";
	
	@Before
	public void setup() {
		FwdProperties.setProperties(null);
	}
	
	/**
	 * Set property and get it back
	 */
	@Test
	public void testGetSetPropertyReturnsValue() {
		FwdProperties.setProperty(TEST_KEY, TEST_VALUE);
		
		assertEquals(TEST_VALUE, FwdProperties.getProperty(TEST_KEY));
	}

	/**
	 * Set property.with.dotted.key and get it back
	 */
	@Test
	public void testGetSetDottedPropertyKeyReturnsValue() {
		FwdProperties.setProperty(TEST_DOTTED_KEY, TEST_DOTTED_VALUE);
		
		assertEquals(TEST_DOTTED_VALUE, FwdProperties.getProperty(TEST_DOTTED_KEY));
	}

	/**
	 * Getting property that hasn't been set returns null
	 */
	@Test
	public void testGetUnknownPropertyReturnsNull() {
		assertEquals(null, FwdProperties.getProperty(UNKNOWN_PROPERTY_KEY));
	}
	
	/**
	 * Getting property that hasn't been set returns the specified default value
	 */
	@Test
	public void testGetUnknownPropertyReturnsDefault() {
		assertEquals(UNKNOWN_PROPERTY_DEFAULT, FwdProperties.getProperty(UNKNOWN_PROPERTY_KEY, UNKNOWN_PROPERTY_DEFAULT));
	}
	
	/**
	 * Attempts to get a property using a null key throws an NullPointerException exception
	 */
	@Test (expected=NullPointerException.class)
	public void testGetNullPropertyThrowsException() {
		assertEquals(null, FwdProperties.getProperty(NULL_PROPERTY_KEY));
	}
	
	/**
	 * Check that properties are automatically loaded from a default property file
	 */
	@Test
	public void testLoadsDefaultPropertyFile() {
		assertEquals(APP_NAME_VALUE, FwdProperties.getProperty(APP_NAME_KEY));
	}
	
	/**
	 * Check that properties are loaded from a specified property file
	 */
	@Test
	public void testLoadsAlternatePropertyFile() {
		FwdProperties.initPropertiesFromFile(TEST_PROPERTIES_FILE);
		assertEquals(REPLACEMENT_APP_NAME_VALUE, FwdProperties.getProperty(APP_NAME_KEY));
	}
}
