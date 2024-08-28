package com.ericsson.eniq.forwarder.core.io;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ericsson.eniq.events.modelservice.api.codec.Codec;
import com.ericsson.eniq.events.modelservice.model.CALLFAILURE_EVENT;
import com.ericsson.eniq.events.modelservice.model.HANDOVER_EVENT;
import com.ericsson.eniq.forwarder.core.io.FileHandler;
import com.ericsson.eniq.forwarder.core.io.FileHandlerCSV;

public class FileHandlerCSVTest {
	
	String eventType;
	
	@Test
	public void testWriteHandoverEventToCSV() {
		HANDOVER_EVENT testObj=new HANDOVER_EVENT();

		eventType = testObj.getClass().getSimpleName();
		FileManager<HANDOVER_EVENT> fileManager = new FileManager<HANDOVER_EVENT>(eventType);
		FileHandler<HANDOVER_EVENT> fileHandler = new FileHandlerCSV<HANDOVER_EVENT>(fileManager.getStorageLocation(), fileManager.getFileFormat());
		
		
		fileHandler.init();
		
		Codec codec = CodecFactory.getCodec(eventType);
		
		fileHandler.write(codec, testObj);
		
		assertTrue("Problem saving to CSV", fileHandler.save());
	}
	
	@Test
	public void testWriteCallFailureEventToCSV() {
		CALLFAILURE_EVENT testObj=new CALLFAILURE_EVENT();

		eventType = testObj.getClass().getSimpleName();
		FileManager<CALLFAILURE_EVENT> fileManager = new FileManager<CALLFAILURE_EVENT>(eventType);
		FileHandler<CALLFAILURE_EVENT> fileHandler = new FileHandlerCSV<CALLFAILURE_EVENT>(fileManager.getStorageLocation(), fileManager.getFileFormat());
		
		
		fileHandler.init();
		
		Codec codec = CodecFactory.getCodec(eventType);
		
		fileHandler.write(codec, testObj);
		
		assertTrue("Problem saving to CSV", fileHandler.save());
	}

}
