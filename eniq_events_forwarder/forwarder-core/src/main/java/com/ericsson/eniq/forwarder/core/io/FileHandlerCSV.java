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

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ericsson.eniq.events.modelservice.api.codec.Codec;
import com.ericsson.eniq.forwarder.core.constant.Constant;

public class FileHandlerCSV<T> extends FileHandler<T> {

    private File file;
    private FileWriter fileWriter;
    private BufferedWriter writer;

    public FileHandlerCSV(String storageLocation, String eventType) {
        this.storageLocation = storageLocation;
        this.eventType = eventType;
    }

    @Override
    public void init() {

        file = new File(new File(this.storageLocation), constructFileName());

        try {
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Codec codec, T event) {

        try {
            writer.write(codec.encodeAsString(event) + Constant.Formatting.CSV_DELIMITER + Constant.Formatting.NEW_LINE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeFileEnd(Codec codec, T event) {
        try {
            writer.write(codec.encodeAsString(event) + Constant.Formatting.CSV_DELIMITER);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean save() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private String constructFileName() {
        String fileExtension = Constant.Formatting.CSV_EXTENSION;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String date = dateFormat.format(new Date(System.currentTimeMillis()));
        return eventType + "_" + date + fileExtension.toLowerCase();
    }

}
