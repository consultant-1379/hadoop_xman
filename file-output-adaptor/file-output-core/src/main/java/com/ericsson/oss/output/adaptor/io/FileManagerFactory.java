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

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Responsible for creating a new FileManager when a new is received.
 * 
 * If the event was processed before then the existing FileManager will be returned. 
 * 
 */
public class FileManagerFactory {

	private Map <String, FileManager> fileManagerMap = new HashMap<>(); 
	
	/**
	 * Ensure that FileManager can be reused if eventType is in the map.
	 * 
	 * @return fileManager
	 */
	public FileManager createFileManager(String eventType) {
		FileManager myFileManager = null;
		
		if(fileManagerMap.containsKey(eventType)){
			myFileManager = fileManagerMap.get(eventType);
		}
		else{
			myFileManager = new FileManager();
			fileManagerMap.put(eventType, myFileManager);
		}
		
		return myFileManager;
	}

	/**
	 * Get map with all File Managers being used.
	 * 
	 * @return Map of FileManagers
	 */
	public Map<String, FileManager> getAllFileManagers() {
		return fileManagerMap;
	}

}
