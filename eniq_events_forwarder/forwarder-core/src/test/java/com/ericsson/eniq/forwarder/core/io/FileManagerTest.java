package com.ericsson.eniq.forwarder.core.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.modelservice.model.HANDOVER_EVENT;

public class FileManagerTest {

	private FileManager<HANDOVER_EVENT> fileManager;
	private static final HANDOVER_EVENT TEST_EVENT = new HANDOVER_EVENT();

	@Before
	public void setUp() {
		fileManager = new FileManager<HANDOVER_EVENT>("HANDOVER_EVENT");
	}

	@Test
	public void testGetStorageLocation() {
		String location = fileManager.getStorageLocation();
		assertTrue(!location.isEmpty());
	}
	
	@Test
	public void testGetFileFormat() {
		String format = fileManager.getFileFormat();
		assertTrue(!format.isEmpty());
	}
	
	@Test
	public void testGetFileLimit(){
		String limit = fileManager.getFileLimit();
		assertTrue(!limit.isEmpty());
	}
	
	@Test
	public void testWriteCSVFile(){
		for(int i = 0; i < 1000; i++){
			fileManager.onEvent(TEST_EVENT);
		}
	}

}
