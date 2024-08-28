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
package com.ericsson.oss.output.adaptor.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileHandler {

	private File file;
	private FileWriter fileWriter;
	private BufferedWriter writer;
	protected String storageLocation;
	protected String eventType;

	public FileHandler(String storageLocation, String eventType) {
		this.storageLocation = storageLocation;
		this.eventType = eventType;
	}

	public void init() {
		file = new File(new File(this.storageLocation), constructFileName());

		try {
			fileWriter = new FileWriter(file);
			writer = new BufferedWriter(fileWriter);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void write() {
		// TODO Auto-generated method stub
	}

	public void writeFileEnd() {
		// TODO Auto-generated method stub
	}

	public void rollOver() {
		if (save()) {
			init();
		}
	}

	public boolean save() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public String constructFileName() {
		// TODO Add file Extension to file name
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String date = dateFormat.format(new Date(System.currentTimeMillis()));
		return eventType + "_" + date;
	}
}
