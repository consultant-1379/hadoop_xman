package com.ericsson.eniq.forwarder.core.properties;
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


import java.io.*;
import java.util.Properties;

public final class PropertyManager {

	private static final String NO_DEFAULT = null;

	private static final String DEFAULT_FILE = "forwarder.ini";

	private static Properties properties = null;
	
	/**
	 * Private constructor - class should not be instantiated.
	 */
	private PropertyManager() {}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public static void setProperties(final Properties properties) {
		PropertyManager.properties = properties;
	}

	/**
	 * If properties has not been initialize, initialize using default
	 * properties file, otherwise just return properties
	 * 
	 * @return
	 * @throws IOException 
	 */
	private static Properties getProps() {
		if (properties == null) {
			try {
				initProperties();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return properties;
	}

	/**
	 * Initialise forwarder properties from default properties file.
	 * @throws IOException 
	 */
	private static void initProperties() throws IOException {
		initPropertiesFromFile(DEFAULT_FILE);
	}

	/**
	 * Get properties from the property file specified by filename
	 * 
	 * @param filename
	 *            name of file to load properties from
	 * @return loaded properties
	 * @throws IOException 
	 */
	public static void initPropertiesFromFile(final String filename) throws IOException {
		properties = new Properties();
		properties.load(new FileReader(filename));
	}

	/**
	 * Set property value for key. If key already exists, overwrite, otherwise
	 * create new property
	 * 
	 * @param key
	 * @param value
	 */
	public static void setProperty(final String key, final String value) {
		getProps().setProperty(key, value);
	}

	/**
	 * Get property value for key.
	 * 
	 * @param key
	 * @return value of property associated with key or null if key not found
	 * @throws NullPointerException
	 */
	public static String getProperty(final String key) throws NullPointerException {
		return getProps().getProperty(key, NO_DEFAULT);
	}

	/**
	 * Get property value for key.
	 * 
	 * @param key
	 * @param default value
	 * @return value of property associated with key or defaultValue if key not
	 *         found
	 */
	public static String getProperty(final String key, final String defaultValue) {
		return getProps().getProperty(key, defaultValue);
	}

}
