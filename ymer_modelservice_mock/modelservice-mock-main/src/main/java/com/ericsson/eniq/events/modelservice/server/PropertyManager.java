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
package com.ericsson.eniq.events.modelservice.server;

import java.io.*;
import java.util.Properties;

public class PropertyManager {
    private static PropertyManager instance;

    private String propFile;

    private Properties props;

    private PropertyManager() {

        propFile = System.getProperty("conf.file");

        if (null == propFile || propFile.isEmpty()) {

            propFile = getClass().getClassLoader().getResource("etc/conf.properties").getFile();
        }

        props = loadProps(propFile);

    }

    public static PropertyManager getInstance() {

        if (null == instance) {

            instance = new PropertyManager();

        }

        return instance;
    }

    public int getRegistryPort() {

        return Integer.valueOf(props.getProperty("registry.port"));
    }

    private static Properties loadProps(final String fileName) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(fileName));
            final Properties props = new Properties();
            props.load(fileReader);
            System.out.println("Configuration loaded from file '" + fileName + "' with following properties: " + props.toString());
            return props;
        } catch (final IOException e) {
            throw new RuntimeException("Unable to load configuration from file '" + fileName + "'");
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (final Throwable t) {
                }
            }
        }
    }

}
