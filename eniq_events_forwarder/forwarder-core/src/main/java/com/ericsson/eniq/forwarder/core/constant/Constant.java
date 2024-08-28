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
package com.ericsson.eniq.forwarder.core.constant;

public class Constant {

    public class FileManager {
        public static final String CSV_FILE_FORMAT = "csv";
        public static final String BINARY_FILE_FORMAT = "bin";

        public static final String FILE_FORMAT_PROPERTY = "file.format";
        public static final String FILE_LIMIT_PROPERTY = "file.limit";
        public static final String FILE_LOCATION_PROPERTY = "file.location";
		public static final String APP_ID = "app.id";
    }

    public class HazelCast {
        //name of the distributed topic
        public static final String DISTRIBUTED_TOPIC_NAME = "myTopic";
    }

    public class EventType {
        public static final String HANDOVER_EVENT = "HANDOVER_EVENT";
        public static final String CALLFAILURE_EVENT = "CALLFAILURE_EVENT";
    }
    
    public class Formatting{
    	public static final String NEW_LINE = "\n";
    	public static final String CSV_DELIMITER = ";";
		public static final String BIN_DELIMITER = ";";
		public static final String BIN_EXTENSION = ".bin";
		public static final String CSV_EXTENSION = ".csv";
    }
}
