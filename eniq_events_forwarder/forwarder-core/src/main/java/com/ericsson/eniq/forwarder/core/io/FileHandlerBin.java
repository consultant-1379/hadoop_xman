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

public class FileHandlerBin<T> extends FileHandler<T> {

    private File file;
    private OutputStream fsStream;
    private ObjectOutputStream objectOutputStream;

    @Override
    public void init() {

        file = new File(new File(this.storageLocation), constructFileName());

        try {
            fsStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fsStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileHandlerBin(String storageLocation, String eventType) {
        this.storageLocation = storageLocation;
        this.eventType = eventType;

    }

    @Override
    public void write(Codec codec, T event) {
        try {
            objectOutputStream.write(codec.encode(event));
            objectOutputStream.write(Constant.Formatting.NEW_LINE.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeFileEnd(Codec codec, T event) {
        try {
            objectOutputStream.write(codec.encode(event));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean save() {
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private String constructFileName() {
        String fileExtension = Constant.Formatting.BIN_EXTENSION;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String date = dateFormat.format(new Date(System.currentTimeMillis()));
        return eventType + "_" + date + fileExtension.toLowerCase();
    }

}
