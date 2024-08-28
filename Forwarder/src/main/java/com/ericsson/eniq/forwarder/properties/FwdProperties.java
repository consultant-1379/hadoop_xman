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

import java.io.*;
import java.util.Properties;

public class FwdProperties {

	private static final String NO_DEFAULT = null;

	private static String DEFAULT_PROPERTIES_FILE = "forwarder.ini";

	private static Properties properties = null;

	/**
	 * @param properties
	 *            the properties to set
	 */
	public static void setProperties(Properties properties) {
		FwdProperties.properties = properties;
	}

	/**
	 * If properties has not been initialise, initialise using default
	 * properties file, otherwise just return properties
	 * 
	 * @return
	 */
	private static Properties getProps() {
		if (properties == null) {
			initProperties();
		}

		return properties;
	}

	/**
	 * Initialise forwarder properties from default properties file.
	 */
	private static void initProperties() {
		initPropertiesFromFile(DEFAULT_PROPERTIES_FILE);
	}

	/**
	 * Get properties from the property file specified by filename
	 * 
	 * @param filename
	 *            name of file to load properties from
	 * @return loaded properties
	 */
	public static void initPropertiesFromFile(String filename) {
		properties = new Properties();

		try {
			properties.load(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set property value for key. If key already exists, overwrite, otherwise
	 * create new property
	 * 
	 * @param key
	 * @param value
	 */
	public static void setProperty(String key, String value) {
		getProps().setProperty(key, value);
	}

	/**
	 * Get property value for key.
	 * 
	 * @param key
	 * @return value of property associated with key or null if key not found
	 * @throws NullPointerException
	 */
	public static String getProperty(String key) throws NullPointerException {
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
	public static String getProperty(String key, String defaultValue) {
		return getProps().getProperty(key, defaultValue);
	}

}
