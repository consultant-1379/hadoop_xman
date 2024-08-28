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

import java.util.HashMap;
import java.util.Map;

public class FileManagerSet<T> {

	private final Map<String, FileManager<T>> fileManagerMap = new HashMap<String, FileManager<T>>();

	/**
	 * Get fileManager for event's class. Create new fileManager if none exists
	 * 
	 * @param event
	 * @return fileManager for event
	 */
	public FileManager<T> getFileManager(final T event) {
		FileManager<T> fileManager;

		final String eventType = event.getClass().getSimpleName();

		if (fileManagerMap.containsKey(eventType)) {
			fileManager = fileManagerMap.get(eventType);
		} else {
			fileManager = new FileManager<T>(eventType);
			fileManagerMap.put(eventType, fileManager);
		}

		return fileManager;
	}

	/**
	 * Set fileManager for given evenType
	 * 
	 * @param eventType
	 * @param fileManager
	 */
	public void setFileManager(final String eventType,
			final FileManager<T> fileManager) {
		fileManagerMap.put(eventType, fileManager);
	}
	
	/**
	 * @return number of fileManagers in set
	 */
	public int size() {
		return fileManagerMap.size();
	}
}
