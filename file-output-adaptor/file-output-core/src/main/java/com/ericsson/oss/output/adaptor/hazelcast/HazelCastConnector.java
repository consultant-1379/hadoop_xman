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
package com.ericsson.oss.output.adaptor.hazelcast;

import com.ericsson.oss.output.adaptor.io.FileManager;
import com.ericsson.oss.output.adaptor.io.FileManagerFactory;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;


/**
 * Create connection to hazelcast topic and send events to our system.
 * 
 * This class may become defunct later if we directly connect to EPS.
 */
public class HazelCastConnector<T> {

	private Config cfg;
    private HazelcastInstance instance;
    private ITopic<T> topic;
    private FileManagerFactory fmf;
	
	public HazelCastConnector(String topicName) {
		cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
        topic = instance.getTopic(topicName);
	}

	/**
	 * 
	 */
	public void startListeningEvents() {
		topic.addMessageListener(new MessageListener<T>() {

			@Override
			public void onMessage(Message event) {
				String eventType = event.getClass().getSimpleName();
				FileManager fileManager = fmf.createFileManager(eventType);
				fileManager.onEvent(event.getMessageObject());
			}
		});
		
	}

	
	
	
}
