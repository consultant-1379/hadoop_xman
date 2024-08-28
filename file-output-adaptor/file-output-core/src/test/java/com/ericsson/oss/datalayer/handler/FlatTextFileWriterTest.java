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

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.*;

import com.ericsson.oss.datalayer.event.Event;
import com.ericsson.oss.datalayer.util.PropertyManager;
import com.ericsson.oss.modelservice.codec.Codec;

public class FlatTextFileWriterTest {
	private final static String LINE_END = PropertyManager.getProperty(
			"flatTextFileWriter.lineEnd", "\n");;
	private static final File TEMP_DIR = new File(
			System.getProperty("java.io.tmpdir"), "FileOutputWriterTest");
	private final static String TEMP_PATH = TEMP_DIR.getPath() + File.separator;
	private final static String TEST_NAME = TEMP_PATH + "TestName";
	static FlatTextFileWriter testFileWriter;

	@BeforeClass
	public static void setUpClass() {
		TEMP_DIR.mkdirs();
	}

	@AfterClass
	public static void tearDownClass() {
		TEMP_DIR.deleteOnExit();
	}

	private static void deleteFile(String fileName) {
		File file = new File(fileName);
		file.delete();
	}

	private static String getContents(File aFile) throws IOException {
		StringBuilder contents = new StringBuilder();
		BufferedReader input = new BufferedReader(new FileReader(aFile));
		String line = null;
		while ((line = input.readLine()) != null) {
			contents.append(line);
			contents.append(LINE_END);
		}
		input.close();
		return contents.toString();
	}

	private static void cleanUp(FlatTextFileWriter mockFlatTextFileWriter)
			throws IOException {
		mockFlatTextFileWriter.closeWriter();
		deleteFile(mockFlatTextFileWriter.getFileName());
	}

	@Before
	public void setUp() {
		testFileWriter = new FlatTextFileWriter(TEST_NAME);
		Codec<Event, String> mockCodec = new MockCodec();
		testFileWriter.setCodec(mockCodec);
	}

	@After
	public void tearDown() throws IOException {
		cleanUp(testFileWriter);
	}

	@Test
	public void testFlatTextFileWriter() {
		assertNotNull(testFileWriter.getFileName());
	}

	@Test
	public void testOnEvent() throws IOException {
		File mockFile = new File(testFileWriter.getFileName());
		testFileWriter.onEventInternal(new MockEvent());
		testFileWriter.closeWriter();
		assertEquals("TestString" + LINE_END, getContents(mockFile));
	}

	@Test(expected = RuntimeException.class)
	public void testOnEventAfterClose() throws IOException {
		testFileWriter.closeWriter();
		testFileWriter.onEventInternal(new MockEvent());
		fail("Should throw exception when event is received after closeing writer.");
	}

	@Test
	public void testUpStats() {
		long byteCount = testFileWriter.getByteCount();
		long eventCount = testFileWriter.getEventCount();
		testFileWriter.onEventInternal(new MockEvent());
		assertEquals(eventCount + 1, testFileWriter.getEventCount());
		assertEquals(byteCount + 11, testFileWriter.getByteCount());
	}

	@Test
	public void testRollOver() throws InterruptedException {
		String orginalFileName = testFileWriter.getFileName();
		Thread.sleep(1000);
		testFileWriter.rollover();
		deleteFile(orginalFileName);
		assertNotEquals(orginalFileName, testFileWriter.getFileName());
	}

	@Test
	public void testOnEventAfterRollOver() throws IOException,
			InterruptedException {
		Thread.sleep(1000);
		String orginalFileName = testFileWriter.getFileName();
		testFileWriter.rollover();
		deleteFile(orginalFileName);
		File mockFile = new File(testFileWriter.getFileName());
		testFileWriter.onEventInternal(new MockEvent());
		testFileWriter.closeWriter();
		assertEquals("TestString" + LINE_END, getContents(mockFile));
	}

}
