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
package com.ericsson.eniq.forwarder.core.io;

import com.ericsson.eniq.events.modelservice.api.codec.Codec;
import com.ericsson.eniq.forwarder.core.constant.Constant;
import com.ericsson.eniq.forwarder.core.properties.PropertyManager;
import com.ericsson.eniq.forwarder.event.handler.EventHandler;

public class FileManager<T> implements EventHandler<T> {

    private FileHandler<T> fileHandler = null;
    private int eventCount = 0;
    private Codec codec = null;
    private String eventType = null;
    public static String storageLocation = PropertyManager.getProperty(Constant.FileManager.FILE_LOCATION_PROPERTY);
    private String fileFormat = PropertyManager.getProperty(Constant.FileManager.FILE_FORMAT_PROPERTY);
    private String fileLimit = PropertyManager.getProperty(Constant.FileManager.FILE_LIMIT_PROPERTY);

    public FileManager(String classType) {
        this.eventType = classType;
        codec = CodecFactory.getCodec(eventType);
        createHandler();

    }

    public void onEvent(T obj) {

        int limit = Integer.parseInt(fileLimit);
        
		if (eventCount < limit) {
            if (eventCount != limit - 1) {
                fileHandler.write(codec, obj);
            } else {
                fileHandler.writeFileEnd(codec, obj);
            }
            eventCount++;
        } else {
            eventCount = 0;
            fileHandler.rollOver();
        }
    }

    private void createHandler() {

        if (fileFormat.equalsIgnoreCase(Constant.FileManager.CSV_FILE_FORMAT)) {
            fileHandler = new FileHandlerCSV<T>(storageLocation, eventType);
        } else if (fileFormat.equalsIgnoreCase(Constant.FileManager.BINARY_FILE_FORMAT)) {
            fileHandler = new FileHandlerBin<T>(storageLocation, eventType);
        } else {
            System.out.println("Unrecognised format, exiting...");
        }
        fileHandler.init();
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public String getFileLimit() {
        return fileLimit;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public String getEventType() {
        return eventType;
    }

}
