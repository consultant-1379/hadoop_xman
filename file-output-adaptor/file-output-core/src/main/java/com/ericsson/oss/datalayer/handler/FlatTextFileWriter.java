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
package com.ericsson.oss.datalayer.handler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ericsson.oss.datalayer.event.Event;
import com.ericsson.oss.datalayer.util.PropertyManager;

public class FlatTextFileWriter extends AbstractFileWriter<String> {
	private static final String ENCODING = PropertyManager.getProperty(
			"flatTextFileWriter.charSet", "UTF-8");
	private static final Charset CHAR_SET = Charset.forName(ENCODING);
	private static final String LINE_END = PropertyManager.getProperty(
			"flatTextFileWriter.lineEnd", "\n");
	private final transient SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMdd_HHmmss");
	private transient BufferedWriter writer;
	private transient String fileName;
	private String rootFileName;

	/**
	 * Create a new FlatTextFileWriter with using given root file name.
	 * 
	 * @param rootFileName
	 */
	public FlatTextFileWriter(final String rootFileName) {
		super();
		setRootFileName(rootFileName);
		initialiseWriter();
	}

	@Override
	protected void onEventInternal(final Event event) {
		final String line = getCodec().encode(event) + LINE_END;
		appendLineToFile(line);
		updateStats(1, getByteCount(line));
	}

	private void initialiseWriter() {
		buildFileName();
		openWriter();
	}

	private long getByteCount(final String line) {
		return line.getBytes(CHAR_SET).length;
	}

	private String getRootFileName() {
		return rootFileName;
	}

	private void setRootFileName(final String rootFileName) {
		this.rootFileName = rootFileName;
	}

	/**
	 * Build actual file name based on rootFileName
	 */
	private void buildFileName() {
		this.fileName = getRootFileName() + "_" + currentDatetimeString();
	}

	public String getFileName() {
		return fileName;
	}

	/**
	 * @return current date & time as text
	 */
	private String currentDatetimeString() {
		return dateFormat.format(new Date(System.currentTimeMillis()));
	}

	@Override
	protected void rollover() {
		try {
			closeWriter();
			initialiseWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void appendLineToFile(final String line) {
		try {
			writer.write(line);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void openWriter() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName), ENCODING));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes a currently open writer, flushing any buffered content to file
	 * 
	 * @throws IOException
	 */
	public void closeWriter() throws IOException {
		writer.close();
	}
}
